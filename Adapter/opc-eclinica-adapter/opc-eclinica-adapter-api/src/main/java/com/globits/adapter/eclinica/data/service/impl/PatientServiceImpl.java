package com.globits.adapter.eclinica.data.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.globits.adapter.eclinica.data.domain.Concept;
import com.globits.adapter.eclinica.data.domain.ConceptName;
import com.globits.adapter.eclinica.data.domain.Obs;
import com.globits.adapter.eclinica.data.domain.Patient;
import com.globits.adapter.eclinica.data.dto.AdapterObjectDto;
import com.globits.adapter.eclinica.data.dto.HbvDto;
import com.globits.adapter.eclinica.data.dto.HcvDto;
import com.globits.adapter.eclinica.data.dto.ObjectDto;
import com.globits.adapter.eclinica.data.dto.PatientDto;
import com.globits.adapter.eclinica.data.dto.PregnancyDto;
import com.globits.adapter.eclinica.data.dto.RegimenPatientDto;
import com.globits.adapter.eclinica.data.dto.SearchObjectDto;
import com.globits.adapter.eclinica.data.dto.TPTDto;
import com.globits.adapter.eclinica.data.repository.ConceptRepository;
import com.globits.adapter.eclinica.data.service.ObsService;
import com.globits.adapter.eclinica.data.service.PatientService;
import com.globits.adapter.eclinica.data.types.AdapterObjectType;
import com.globits.adapter.eclinica.utils.EclinicaConstant;
import com.globits.adapter.eclinica.utils.RestTemplateUtils;

@Service
public class PatientServiceImpl implements PatientService {

	public static final long EXECUTION_TIME = 5000L;
	public static int splitSize = 10;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ObsService obsSerivce;
	@Autowired
	private ConceptRepository conceptRepository;

	@Override
	public PatientDto getById(Integer id) {
		String hql = "from Patient p where p.id=:id";
//		Query q = entityManager.createQuery(hql);
		Query q = entityManager.createQuery(hql, Patient.class);
		q.setParameter("id", id);
		List<Patient> list = q.getResultList();
//		Object result = q.getSingleResult();
		if (list != null && list.size() > 0) {
			Patient p = list.get(0);
			PatientDto ret = new PatientDto(p);
//			List<Obs> listObs=obsSerivce.getListByPersonId(id);			
//			ret=this.convertObs(ret, listObs);
			return ret;
		}

		return null;
	}

	@Override
	public List<PatientDto> getList(SearchObjectDto dto) {
		String sql = "select new com.globits.adapter.eclinica.data.dto.PatientDto(p) from Patient p  where (p.voided is null or p.voided=0)";
		String whereClause = "";
		if (dto.getPatientId() != null) {
			whereClause += " and p.id =:id";
		}
		if (dto.getPatientUuid() != null) {
			whereClause += " and p.uuid =:uuid";
		}
		if (dto.getPatientIds() != null && dto.getPatientIds().size() > 0) {
			whereClause += " and p.id in (:ids)";
		}
		if (dto.getLastSynDate() != null) {
			whereClause += "AND p.dateChanged >= :lastSynDate";
		}

		sql += whereClause;

		Query q = entityManager.createQuery(sql, PatientDto.class);
		if (dto.getPatientId() != null) {
			q.setParameter("id", dto.getPatientId());
		}
		if (dto.getPatientUuid() != null) {
			q.setParameter("uuid", dto.getPatientUuid());
		}
		if (dto.getPatientIds() != null && dto.getPatientIds().size() > 0) {
			q.setParameter("ids", dto.getPatientIds());
		}
		if (dto.getLastSynDate() != null) {
			q.setParameter("lastSynDate", dto.getLastSynDate());
		}
		List<PatientDto> page = q.getResultList();
		return page;
	}

	@Override
	public Page<PatientDto> getPage(SearchObjectDto dto) {
		if (dto == null) {
			return null;
		}
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex = pageIndex - 1;
		else
			pageIndex = 0;
		Pageable pageable = new PageRequest(pageIndex, pageSize);

		String sql = "select new com.globits.adapter.eclinica.data.dto.PatientDto(p) from Patient p  where (p.voided is null or p.voided=0)";
		String sqlCount = "select count(p.id) from Patient p where (p.voided is null or p.voided=0)";
		String whereClause = "";
		if (dto.getPatientId() != null) {
			whereClause += " and p.id =:id";
		}
		if (dto.getPatientUuid() != null) {
			whereClause += " and p.uuid =:uuid";
		}
		if (dto.getPatientIds() != null && dto.getPatientIds().size() > 0) {
			whereClause += " and p.id in (:ids)";
		}
		if (dto.getLastSynDate() != null) {
			whereClause += "AND p.dateChanged >= :lastSynDate";
		}

		sql += whereClause;
//		sql += " order by ssm.student.firstName asc ";
		sqlCount += whereClause;

		Query q = entityManager.createQuery(sql, PatientDto.class);
		Query qCount = entityManager.createQuery(sqlCount);
		if (dto.getPatientId() != null) {
			q.setParameter("id", dto.getPatientId());
			qCount.setParameter("id", dto.getPatientId());
		}
		if (dto.getPatientUuid() != null) {
			q.setParameter("uuid", dto.getPatientUuid());
			qCount.setParameter("uuid", dto.getPatientUuid());
		}
		if (dto.getPatientIds() != null && dto.getPatientIds().size() > 0) {
			q.setParameter("ids", dto.getPatientIds());
			qCount.setParameter("ids", dto.getPatientIds());
		}
		if (dto.getLastSynDate() != null) {
			q.setParameter("lastSynDate", dto.getLastSynDate());
			qCount.setParameter("lastSynDate", dto.getLastSynDate());
		}

		q.setFirstResult((pageIndex) * pageSize);
		q.setMaxResults(pageSize);

		Long numberResult = (Long) qCount.getSingleResult();
		Page<PatientDto> page = new PageImpl<>(q.getResultList(), pageable, numberResult);
		return page;
	}

	public PatientDto convertObs(PatientDto ret, List<Obs> listObs) {
		List<Obs> listCD4 = new ArrayList<Obs>();// danh sách CD4
		List<Obs> listVL = new ArrayList<Obs>();// danh sách Tải lượng HIV
		List<Obs> listDateResult = new ArrayList<Obs>();// danh sách ngày có kết quả
		List<Obs> listDateResultVL = new ArrayList<Obs>();// danh sách ngày có kết quả tải lượng hiv
		Hashtable<String, RegimenPatientDto> hashRegimen = new Hashtable<String, RegimenPatientDto>();// danh sách ngày
																										// thay đổi phác
																										// đồ
		Hashtable<Integer, TPTDto> hashTPT = new Hashtable<Integer, TPTDto>();// danh sách dự phòng lao
		Hashtable<Integer, PregnancyDto> hashPregnancy = new Hashtable<Integer, PregnancyDto>();// danh sách thai nghén
		int indexRegimen = 0;
		if (listObs != null && listObs.size() > 0) {
			for (Obs obs : listObs) {
				if (obs.getVoided() != null && obs.getVoided()) {
					continue;
				}
				if (obs.getConcept() == null || (obs.getConcept() != null && obs.getConcept().getConceptId() == null)) {
					continue;
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_CD4_COUNT)) {
					listCD4.add(obs);
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_VL)) {
					listVL.add(obs);
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_TEST_RESULT_DATE)) {
					listDateResult.add(obs);
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_VIRAL_LOAD_TEST_DATE)) {
					listDateResultVL.add(obs);
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DATE_ARV_START)) {
					if (ret.getArvTreatmentDateStart() == null)
						ret.setArvTreatmentDateStart(obs.getValueDatetime());// ngày bắt đầu arv

				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_REGIMEN_ARV)) {
					// các lần thay đổi phác đồ
					if (obs.getValueText() != null) {
						RegimenPatientDto dto = hashRegimen.get(obs.getValueText());
						if (dto == null) {
							dto = new RegimenPatientDto();
							dto.setRegimenName(obs.getValueText());
							dto.setStartDate(obs.getObsDatetime());
							ret.getListRegimenHistory().add(dto);
							hashRegimen.put(obs.getValueText(), dto);
							if (indexRegimen == 0) {// Date 1st ARV regimen started
								ret.setFirstARVRegimenStartDate(obs.getObsDatetime());
							} else if (indexRegimen == 1) {// Date 2nd ARV regimen started
								ret.setSecondARVRegimenStartDate(obs.getObsDatetime());
							} else if (indexRegimen == 2) {// Date 3rd ARV regimen started
								ret.setThirdARVRegimenStartDate(obs.getObsDatetime());
							}
							indexRegimen++;
						}
					}

				}

				// khả năng kháng thuốc
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DRUG_RESISTANCE)) {

					if (obs.getValueCoded() != null && obs.getValueCoded().getConceptId() != null) {
						Concept c = conceptRepository.getOne(obs.getValueCoded().getConceptId());
						if (c != null && c.getNames() != null && c.getNames().size() > 0) {
							ObjectDto dto = new ObjectDto();
							String name = "";
							for (ConceptName cn : c.getNames()) {
								if (cn.getLocale() != null && cn.getLocale().equals("vi")
										&& cn.getLocalePreferred() != null && cn.getLocalePreferred()
										&& !cn.getVoided()) {
									name = cn.getName();
									break;
								}
							}
							dto.setValueText(name);
							dto.setSampleDate(obs.getObsDatetime());
							if (obs.getEncounter() != null && obs.getEncounter().getVisit() != null
									&& obs.getEncounter().getVisit().getStopDatetime() != null)
								dto.setResultDate(obs.getEncounter().getVisit().getStopDatetime());
							ret.getListDrugResistance().add(dto);
						}

					}
				}
				// lý do ngừng chăm sóc điều trị
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_REASON_EXITED_CARE)) {
					if (obs.getValueCoded() != null
							&& obs.getValueCoded().getConceptId().equals(EclinicaConstant.CONCEPT_LOSS)) {
						// bỏ trị
						ret.setArvTreatmentDateOfLossToFollowUp(obs.getObsDatetime());
					} else if (obs.getValueCoded() != null
							&& obs.getValueCoded().getConceptId().equals(EclinicaConstant.CONCEPT_DEATH)) {
						// tử vong
						ret.setDead(true);
						ret.setDeathDate(obs.getObsDatetime());// ngày tử vong
						ret.setCauseOfDeath(obs.getComments());// nguyên nhân tử vong
					}
				}
				// chuyển đi
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DATE_TRANSFER)) {
					ret.setArvTreatmentDateOfTransferredOut(obs.getValueDatetime());
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_PLACE_TRANSFER)) {
					ret.setArvTreatmentPlaceTransferredOut(obs.getValueText());
				}
				// dự phòng lao
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DATE_START_INH)) {
					if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
						TPTDto dto = hashTPT.get(obs.getEncounter().getEncounterId());
						if (dto == null) {
							dto = new TPTDto();
							dto.setStartDate(obs.getValueDatetime());
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							ret.getListTPT().add(dto);
							hashTPT.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (ret.getListTPT() != null && ret.getListTPT().size() > 0) {
								for (TPTDto tpt : ret.getListTPT()) {
									if (tpt.getEncounterId() != null
											&& tpt.getEncounterId().equals(obs.getEncounter().getEncounterId())) {
										tpt.setStartDate(obs.getValueDatetime());
										break;
									}
								}
							}
						}
					}
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DATE_END_INH)) {
					if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
						TPTDto dto = hashTPT.get(obs.getEncounter().getEncounterId());
						if (dto == null) {
							dto = new TPTDto();
							dto.setCompletedDate(obs.getValueDatetime());
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							ret.getListTPT().add(dto);
							hashTPT.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (ret.getListTPT() != null && ret.getListTPT().size() > 0) {
								for (TPTDto tpt : ret.getListTPT()) {
									if (tpt.getEncounterId() != null
											&& tpt.getEncounterId().equals(obs.getEncounter().getEncounterId())) {
										tpt.setCompletedDate(obs.getValueDatetime());
										break;
									}
								}
							}
						}
					}
				}
				// chuẩn đoán lao
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_TbTreatment)) {
					if (obs.getValueCoded().getConceptId().equals(160922)) {
						ret.getTbDiagnosisDate().add(obs.getObsDatetime());
					}
				}
				// hbv
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_HBV)) {
					if (obs.getValueCoded().getConceptId().equals(160922)) {
						HbvDto hbv = new HbvDto();
						hbv.setDiagnosisDate(obs.getObsDatetime());
						hbv.setTreatmentStartDate(obs.getObsDatetime());
						ret.getListHBV().add(hbv);
					}
				}
				// hcv
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_HCV)) {
					if (obs.getValueCoded().getConceptId().equals(160922)) {
						HcvDto hcv = new HcvDto();
						hcv.setDiagnosisDate(obs.getObsDatetime());
						ret.getListHCV().add(hcv);
					}
				}
				// thai nghén
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_PREGNANCY)) {
					if (obs.getValueCoded().getConceptId().equals(1065)) {
						if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
							PregnancyDto dto = hashPregnancy.get(obs.getEncounter().getEncounterId());
							if (dto == null) {
								dto = new PregnancyDto();
								dto.setDatePregnancyReported(obs.getObsDatetime());// ngày mang thai được báo
								dto.setEncounterId(obs.getEncounter().getEncounterId());
								ret.getListPregnancy().add(dto);
								hashPregnancy.put(obs.getEncounter().getEncounterId(), dto);
							} else {
								if (ret.getListPregnancy() != null && ret.getListPregnancy().size() > 0) {
									for (PregnancyDto tpt : ret.getListPregnancy()) {
										if (tpt.getEncounterId() != null
												&& tpt.getEncounterId().equals(obs.getEncounter().getEncounterId())) {
											tpt.setDatePregnancyReported(obs.getObsDatetime());
											break;
										}
									}
								}
							}
						}
					}
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_PREGNANCY_DATE)) {
					if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
						PregnancyDto dto = hashPregnancy.get(obs.getEncounter().getEncounterId());
						if (dto == null) {
							dto = new PregnancyDto();
							dto.setChildDeliveryDate(obs.getValueDatetime());// ngày dự sinh
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							ret.getListPregnancy().add(dto);
							hashPregnancy.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (ret.getListPregnancy() != null && ret.getListPregnancy().size() > 0) {
								for (PregnancyDto tpt : ret.getListPregnancy()) {
									if (tpt.getEncounterId() != null
											&& tpt.getEncounterId().equals(obs.getEncounter().getEncounterId())) {
										tpt.setChildDeliveryDate(obs.getValueDatetime());// ngày dự sinh
										break;
									}
								}
							}
						}
					}
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_PREGNANCY_AGE_CHILD)) {
					if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
						PregnancyDto dto = hashPregnancy.get(obs.getEncounter().getEncounterId());
						if (dto == null) {
							dto = new PregnancyDto();
							dto.setGestationalAge(obs.getValueNumeric());// tuổi thai
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							ret.getListPregnancy().add(dto);
							hashPregnancy.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (ret.getListPregnancy() != null && ret.getListPregnancy().size() > 0) {
								for (PregnancyDto tpt : ret.getListPregnancy()) {
									if (tpt.getEncounterId() != null
											&& tpt.getEncounterId().equals(obs.getEncounter().getEncounterId())) {
										tpt.setGestationalAge(obs.getValueNumeric());// tuổi thai
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		// CD4
		if (listCD4 != null && listCD4.size() > 0) {

			if (ret.getArvTreatmentDateStart() == null) {
				// trường hợp chưa bắt đầu ARV thì listCD4 sẽ là listCD4 trước ARV
				for (Obs obs : listCD4) {
					ObjectDto dto = new ObjectDto();
					dto.setResult(obs.getValueNumeric());
					dto.setSampleDate(obs.getObsDatetime());
					if (listDateResult != null && listDateResult.size() > 0) {
						for (Obs obs1 : listDateResult) {
							if (obs1.getEncounter() != null && obs1.getEncounter().getEncounterId() != null && obs1
									.getEncounter().getEncounterId().equals(obs.getEncounter().getEncounterId())) {
								dto.setResultDate(obs1.getValueDatetime());// ngày nhận kết quả
								break;
							}
						}
					}
					ret.getListCD4BeforeARV().add(dto);
				}

			} else {
				for (Obs obs : listCD4) {
					ObjectDto dto = new ObjectDto();
					dto.setResult(obs.getValueNumeric());
					dto.setSampleDate(obs.getObsDatetime());
					if (listDateResult != null && listDateResult.size() > 0) {
						for (Obs obs1 : listDateResult) {
							if (obs1.getEncounter() != null && obs1.getEncounter().getEncounterId() != null && obs1
									.getEncounter().getEncounterId().equals(obs.getEncounter().getEncounterId())) {
								dto.setResultDate(obs1.getValueDatetime());// ngày nhận kết quả
								break;
							}
						}
					}
					if (obs.getObsDatetime().before(ret.getArvTreatmentDateStart())) {
						ret.getListCD4BeforeARV().add(dto);
					} else {
						ret.getListCD4DuringARV().add(dto);
					}
				}

			}

		}
		// END CD4
		// Viral Load
		if (listVL != null && listVL.size() > 0) {
			for (Obs obs : listVL) {
				if (ret.getArvTreatmentDateStart() != null
						&& (obs.getObsDatetime().after(ret.getArvTreatmentDateStart())
								|| obs.getObsDatetime().equals(ret.getArvTreatmentDateStart()))) {
					ObjectDto dto = new ObjectDto();
					dto.setResult(obs.getValueNumeric());// kết quả vl
					dto.setSampleDate(obs.getObsDatetime());// ngày lấy mẫu
					if (listDateResultVL != null && listDateResultVL.size() > 0) {
						for (Obs obs1 : listDateResultVL) {
							if (obs1.getEncounter() != null && obs1.getEncounter().getEncounterId() != null && obs1
									.getEncounter().getEncounterId().equals(obs.getEncounter().getEncounterId())) {
								dto.setResultDate(obs.getValueDatetime());// ngày nhận kết quả
								break;
							}
						}
					}
					ret.getListVlResultDuringARV().add(dto);
				}
			}
		}
		return ret;
	}

	@Override
	public AdapterObjectDto getListObjectByIdAndLastSynDate(SearchObjectDto searchDto) {
		AdapterObjectDto ret = new AdapterObjectDto();
		Page<PatientDto> listResult = getPage(searchDto);
		ret.setObjectType(AdapterObjectType.LIST_DATA);
		ret.setListData(listResult.getContent());
		return ret;
	}

	@Override
	public ResponseEntity<String> splitListObject(SearchObjectDto searchDto) {
		AdapterObjectDto dto = getListObjectByIdAndLastSynDate(searchDto);

		if (dto.getListData() != null && dto.getListData().size() > 0) {
			if (dto.getListData().size() > splitSize) {
				if ((dto.getListData().size() % splitSize) == 0) {
					int part = ((int) dto.getListData().size() / splitSize);
					for (int i = 0; i < part; i++) {
						List<PatientDto> subListOpcAssistDto = dto.getListData().subList(splitSize * i,
								splitSize * (i + 1));
						AdapterObjectDto subDto = new AdapterObjectDto();
						subDto.setListData(subListOpcAssistDto);
						subDto.setObjectType(AdapterObjectType.LIST_DATA);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				} else {
					int part = ((int) dto.getListData().size() / splitSize) + 1;
					for (int i = 0; i < part; i++) {
						List<PatientDto> subListOpcAssistDto = new ArrayList<PatientDto>();
						if (i < (part - 1)) {
							subListOpcAssistDto = dto.getListData().subList(splitSize * i, splitSize * (i + 1));
						} else {
							subListOpcAssistDto = dto.getListData().subList(splitSize * i, dto.getListData().size());
						}
						AdapterObjectDto subDto = new AdapterObjectDto();
						subDto.setListData(subListOpcAssistDto);
						subDto.setObjectType(AdapterObjectType.LIST_DATA);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				}
			} else {
				ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
			}
		}
		return new ResponseEntity<String>("Success post data to Openhim!!", HttpStatus.OK);
	}
}

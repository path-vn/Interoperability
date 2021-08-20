package com.globits.adapter.eclinica.data.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.globits.adapter.eclinica.Employee;
import com.globits.adapter.eclinica.data.domain.ActiveList;
import com.globits.adapter.eclinica.data.domain.Obs;
import com.globits.adapter.eclinica.data.domain.Patient;
import com.globits.adapter.eclinica.data.domain.PatientIdentifier;
import com.globits.adapter.eclinica.utils.EclinicaConstant;
import com.globits.adapter.eclinica.utils.MapRegimenLine;

public class PatientDto extends PersonDto {
	private Integer patientId;
	protected Set<PatientIdentifierDto> identifiers;
	private String identifier;// mã thẻ
	private String locationDefault;// opc hien thoi
	private List<RapidTestDto> listRapidTest;// kiểm tra nhanh
	private List<RecencyDto> listRecency;// kiểm tra nhanh VL
	private List<ObjectDto> listCD4BeforeARV;// cd4 trước arv
	private List<ObjectDto> listCD4DuringARV;// cd4 trong arv

	private List<ObjectDto> listVlResultDuringARV;// tải lượng hiv

	private List<ObjectDto> listDrugResistance;// khả năng kháng thuốc
	private List<RegimenPatientDto> listRegimenHistory;// danh sách thay đổi phác đồ
	private Date arvTreatmentDateStart;// ngày bắt đầu điều trị ARV
	private String aRVTreatmentFacility;//Tên cơ sở điều trị arv
	private String aRVTreatmentFacilityCode;//Mã cơ sở điều trị arv

	private Date firstARVRegimenStartDate;
	private Date secondARVRegimenStartDate;
	private Date thirdARVRegimenStartDate;

	private Date arvTreatmentDateOfLossToFollowUp;// ngày bỏ trị
	private Date arvTreatmentDateOfTransferredOut;// ngày chuyển đi
	private String arvTreatmentPlaceTransferredOut;// nơi chuyển
	private String treatmentStopReason;
	private List<TPTDto> listTPT;// danh sách TPT- dự phòng lao
	private List<Date> tbDiagnosisDate;// ds ngày chuẩn đoán lao
	private List<TBTreatmentDto> listTBTreatment;// danh sách điều trị lao
	private List<HbvDto> listHBV;// ds HBV
	private List<HcvDto> listHCV;// danh sách HCV
	private List<PregnancyDto> listPregnancy;// mang thai


	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Set<PatientIdentifierDto> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<PatientIdentifierDto> identifiers) {
		this.identifiers = identifiers;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLocationDefault() {
		return locationDefault;
	}

	public void setLocationDefault(String locationDefault) {
		this.locationDefault = locationDefault;
	}

	public List<RecencyDto> getListRecency() {
		return listRecency;
	}

	public void setListRecency(List<RecencyDto> listRecency) {
		this.listRecency = listRecency;
	}

	public List<RapidTestDto> getListRapidTest() {
		return listRapidTest;
	}

	public void setListRapidTest(List<RapidTestDto> listRapidTest) {
		this.listRapidTest = listRapidTest;
	}

	public List<ObjectDto> getListCD4BeforeARV() {
		if(this.listCD4BeforeARV != null && this.listCD4BeforeARV.size()>0) {
			this.listCD4BeforeARV.sort(new Comparator<ObjectDto>() {
			    @Override
			    public int compare(ObjectDto emp1, ObjectDto emp2) {
			        return emp1.getResultDate().compareTo(emp2.getResultDate());
			    }
			});
		}
		return listCD4BeforeARV;
	}

	public void setListCD4BeforeARV(List<ObjectDto> listCD4BeforeARV) {
		this.listCD4BeforeARV = listCD4BeforeARV;
	}

	public List<ObjectDto> getListCD4DuringARV() {
		if(this.listCD4DuringARV != null && this.listCD4DuringARV.size()>0) {
			this.listCD4DuringARV.sort(new Comparator<ObjectDto>() {
			    @Override
			    public int compare(ObjectDto emp1, ObjectDto emp2) {
			        return emp1.getResultDate().compareTo(emp2.getResultDate());
			    }
			});
		}
		 
		return listCD4DuringARV;
	}

	public void setListCD4DuringARV(List<ObjectDto> listCD4DuringARV) {
		this.listCD4DuringARV = listCD4DuringARV;
	}

	public List<ObjectDto> getListVlResultDuringARV() {
		return listVlResultDuringARV;
	}

	public void setListVlResultDuringARV(List<ObjectDto> listVlResultDuringARV) {
		this.listVlResultDuringARV = listVlResultDuringARV;
	}

	public List<ObjectDto> getListDrugResistance() {
		return listDrugResistance;
	}

	public void setListDrugResistance(List<ObjectDto> listDrugResistance) {
		this.listDrugResistance = listDrugResistance;
	}

	public Date getArvTreatmentDateStart() {
		return arvTreatmentDateStart;
	}

	public void setArvTreatmentDateStart(Date arvTreatmentDateStart) {
		this.arvTreatmentDateStart = arvTreatmentDateStart;
	}

	public List<RegimenPatientDto> getListRegimenHistory() {
		return listRegimenHistory;
	}

	public void setListRegimenHistory(List<RegimenPatientDto> listRegimenHistory) {
		this.listRegimenHistory = listRegimenHistory;
	}

	public Date getArvTreatmentDateOfLossToFollowUp() {
		return arvTreatmentDateOfLossToFollowUp;
	}

	public void setArvTreatmentDateOfLossToFollowUp(Date arvTreatmentDateOfLossToFollowUp) {
		this.arvTreatmentDateOfLossToFollowUp = arvTreatmentDateOfLossToFollowUp;
	}

	public Date getArvTreatmentDateOfTransferredOut() {
		return arvTreatmentDateOfTransferredOut;
	}

	public void setArvTreatmentDateOfTransferredOut(Date arvTreatmentDateOfTransferredOut) {
		this.arvTreatmentDateOfTransferredOut = arvTreatmentDateOfTransferredOut;
	}

	public String getArvTreatmentPlaceTransferredOut() {
		return arvTreatmentPlaceTransferredOut;
	}

	public void setArvTreatmentPlaceTransferredOut(String arvTreatmentPlaceTransferredOut) {
		this.arvTreatmentPlaceTransferredOut = arvTreatmentPlaceTransferredOut;
	}

	public Date getFirstARVRegimenStartDate() {
		return firstARVRegimenStartDate;
	}

	public void setFirstARVRegimenStartDate(Date firstARVRegimenStartDate) {
		this.firstARVRegimenStartDate = firstARVRegimenStartDate;
	}

	public Date getSecondARVRegimenStartDate() {
		return secondARVRegimenStartDate;
	}

	public void setSecondARVRegimenStartDate(Date secondARVRegimenStartDate) {
		this.secondARVRegimenStartDate = secondARVRegimenStartDate;
	}

	public Date getThirdARVRegimenStartDate() {
		return thirdARVRegimenStartDate;
	}

	public void setThirdARVRegimenStartDate(Date thirdARVRegimenStartDate) {
		this.thirdARVRegimenStartDate = thirdARVRegimenStartDate;
	}

	public List<TPTDto> getListTPT() {
		return listTPT;
	}

	public void setListTPT(List<TPTDto> listTPT) {
		this.listTPT = listTPT;
	}

	public List<Date> getTbDiagnosisDate() {
		return tbDiagnosisDate;
	}

	public void setTbDiagnosisDate(List<Date> tbDiagnosisDate) {
		this.tbDiagnosisDate = tbDiagnosisDate;
	}

	public List<TBTreatmentDto> getListTBTreatment() {
		return listTBTreatment;
	}

	public void setListTBTreatment(List<TBTreatmentDto> listTBTreatment) {
		this.listTBTreatment = listTBTreatment;
	}

	public List<HbvDto> getListHBV() {
		return listHBV;
	}

	public void setListHBV(List<HbvDto> listHBV) {
		this.listHBV = listHBV;
	}

	public List<HcvDto> getListHCV() {
		return listHCV;
	}

	public void setListHCV(List<HcvDto> listHCV) {
		this.listHCV = listHCV;
	}

	public List<PregnancyDto> getListPregnancy() {
		return listPregnancy;
	}

	public void setListPregnancy(List<PregnancyDto> listPregnancy) {
		this.listPregnancy = listPregnancy;
	}

	public String getaRVTreatmentFacility() {
		return aRVTreatmentFacility;
	}

	public void setaRVTreatmentFacility(String aRVTreatmentFacility) {
		this.aRVTreatmentFacility = aRVTreatmentFacility;
	}

	public String getaRVTreatmentFacilityCode() {
		return aRVTreatmentFacilityCode;
	}

	public void setaRVTreatmentFacilityCode(String aRVTreatmentFacilityCode) {
		this.aRVTreatmentFacilityCode = aRVTreatmentFacilityCode;
	}


	public String getTreatmentStopReason() {
		return treatmentStopReason;
	}

	public void setTreatmentStopReason(String treatmentStopReason) {
		this.treatmentStopReason = treatmentStopReason;
	}

	public PatientDto() {
	}

	public PatientDto(Patient p) {
		super(p);
		this.patientId = p.getPersonId();

		if (p.getIdentifiers() != null && p.getIdentifiers().size() > 0) {
			this.identifiers = new HashSet<PatientIdentifierDto>();

			for (PatientIdentifier ex : p.getIdentifiers()) {
				if (ex.isVoided()) {
					continue;
				}
				PatientIdentifierDto exDto = new PatientIdentifierDto();
				exDto.setPatientIdentifierId(ex.getPatientIdentifierId());
				exDto.setUuid(ex.getUuid());
				exDto.setIdentifier(ex.getIdentifier());
				exDto.setIdentifierType(new PatientIdentifierTypeDto());
				exDto.getIdentifierType()
						.setPatientIdentifierTypeId(ex.getIdentifierType().getPatientIdentifierTypeId());
				exDto.getIdentifierType().setName(ex.getIdentifierType().getName());
				exDto.getIdentifierType().setUuid(ex.getIdentifierType().getUuid());
//				exDto.setPatient(new PatientDto());
				exDto.setPatientId(p.getPersonId());
				exDto.setPreferred(ex.isPreferred());
				exDto.setVoided(ex.isVoided());
				if (ex.isPreferred() && !ex.isVoided() && ex.getIdentifierType() != null
						&& ex.getIdentifierType().getPatientIdentifierTypeId() != null
						&& ex.getIdentifierType().getPatientIdentifierTypeId().equals(2)) {// trường hợp mã hiện thời
																							// dùng
					this.identifier = ex.getIdentifier();// mã thẻ
					if (ex.getLocation() != null && ex.getLocation().getName() != null) {
						this.locationDefault = ex.getLocation().getName();
					}
				}

				this.identifiers.add(exDto);
			}
		}
		// obs
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

		if (p.getObs() != null && p.getObs().size() > 0) {
			for (Obs obs : p.getObs()) {
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
					if (this.getArvTreatmentDateStart() == null) {
						this.setArvTreatmentDateStart(obs.getValueDatetime());// ngày bắt đầu arv
					}
					this.aRVTreatmentFacility = this.locationDefault;
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_REASON_EXITED_CARE)) {
					if (this.getTreatmentStopReason() == null) {
						this.setTreatmentStopReason(obs.getVoidReason());// Lí do ngừng điều trị  
					}
					
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_REGIMEN_ARV)) {
					// các lần thay đổi phác đồ
					if (obs.getValueText() != null) {
						RegimenPatientDto dto = hashRegimen.get(obs.getValueText());
						if (dto == null) {
							dto = new RegimenPatientDto();
							dto.setRegimenName(obs.getValueText());
							dto.setStartDate(obs.getObsDatetime());
							dto.setPlace(this.locationDefault);
							hashRegimen.put(obs.getValueText(), dto);
							Integer regimenLine = (Integer) MapRegimenLine.mapRegimenLine.get(obs.getValueText());
							if(regimenLine!=null) {
								dto.setArvRegimenLine(regimenLine);
							}else {
								dto.setArvRegimenLine(3);//nếu không tìm thấy trong mapregimenline thì sẽ là phác đồ bậc 3
							}
							
//							if (indexRegimen == 0) {// Date 1st ARV regimen started
//								dto.setArvRegimenLine(1);
//							} else if (indexRegimen == 1) {// Date 2nd ARV regimen started
//								dto.setArvRegimenLine(2);
//							} else if (indexRegimen == 2) {// Date 3rd ARV regimen started
//								dto.setArvRegimenLine(3);
//							}
							indexRegimen++;
							if (this.listRegimenHistory == null) {
								this.listRegimenHistory = new ArrayList<RegimenPatientDto>();
							}
							this.listRegimenHistory.add(dto);
//								this.getListRegimenHistory().add(dto);								
						}
					}

				}

				// khả năng kháng thuốc
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DRUG_RESISTANCE)) {

					if (obs.getValueCoded() != null && obs.getValueCoded().getConceptId() != null) {
						String name = "";
						if (obs.getValueCoded().getConceptId().equals(162015)) {
							name = "Điều trị ARV PLTMC";
						} else if (obs.getValueCoded().getConceptId().equals(162016)) {
							name = "Điều trị ARV phòng phơi nhiễm";
						} else if (obs.getValueCoded().getConceptId().equals(162017)) {
							name = "Gián đoạn điều trị";
						} else if (obs.getValueCoded().getConceptId().equals(162018)) {
							name = "Điều trị 3 thuốc nhóm NRTIs";
						} else if (obs.getValueCoded().getConceptId().equals(162019)) {
							name = "Điều trị Rifampicin + NVP";
						}
//						
						ObjectDto dto = new ObjectDto();
						dto.setPlace(this.locationDefault);
						dto.setValueText(name);
						dto.setSampleDate(obs.getObsDatetime());
						if (obs.getEncounter() != null && obs.getEncounter().getVisit() != null
								&& obs.getEncounter().getVisit().getStopDatetime() != null)
							dto.setResultDate(obs.getEncounter().getVisit().getStopDatetime());
						if (this.listDrugResistance == null) {
							this.listDrugResistance = new ArrayList<ObjectDto>();
						}
						this.listDrugResistance.add(dto);
//						this.getListDrugResistance().add(dto);

					}
				}
				// lý do ngừng chăm sóc điều trị
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_REASON_EXITED_CARE)) {
					if (obs.getValueCoded() != null
							&& obs.getValueCoded().getConceptId().equals(EclinicaConstant.CONCEPT_LOSS)) {
						// bỏ trị
						this.setArvTreatmentDateOfLossToFollowUp(obs.getObsDatetime());
					} else if (obs.getValueCoded() != null
							&& obs.getValueCoded().getConceptId().equals(EclinicaConstant.CONCEPT_DEATH)) {
						// tử vong
						this.setDead(true);
						this.setDeathDate(obs.getObsDatetime());// ngày tử vong
						this.setCauseOfDeath(obs.getComments());// nguyên nhân tử vong
					}
				}
				// chuyển đi
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DATE_TRANSFER)) {
					this.setArvTreatmentDateOfTransferredOut(obs.getValueDatetime());
				}
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_PLACE_TRANSFER)) {
					this.setArvTreatmentPlaceTransferredOut(obs.getValueText());
				}
				// dự phòng lao
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_DATE_START_INH)) {
					if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
						TPTDto dto = hashTPT.get(obs.getEncounter().getEncounterId());
						if (dto == null) {
							dto = new TPTDto();
							dto.setStartDate(obs.getValueDatetime());
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							dto.setPlace(this.locationDefault);
							if (this.listTPT == null) {
								this.listTPT = new ArrayList<TPTDto>();
							}
							this.listTPT.add(dto);
//							this.getListTPT().add(dto);
							hashTPT.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (this.getListTPT() != null && this.getListTPT().size() > 0) {
								for (TPTDto tpt : this.getListTPT()) {
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
							dto.setPlace(this.locationDefault);
							dto.setCompletedDate(obs.getValueDatetime());
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							if (this.listTPT == null) {
								this.listTPT = new ArrayList<TPTDto>();
							}
							this.listTPT.add(dto);
//							this.getListTPT().add(dto);
							hashTPT.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (this.getListTPT() != null && this.getListTPT().size() > 0) {
								for (TPTDto tpt : this.getListTPT()) {
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
						if (this.tbDiagnosisDate == null) {
							this.tbDiagnosisDate = new ArrayList<Date>();
						}
						this.tbDiagnosisDate.add(obs.getObsDatetime());
//						this.getTbDiagnosisDate().add(obs.getObsDatetime());
					}
				}
				// hbv
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_HBV)) {
					if (obs.getValueCoded().getConceptId().equals(160922)) {
						HbvDto hbv = new HbvDto();
						hbv.setPlace(this.locationDefault);
						hbv.setDiagnosisDate(obs.getObsDatetime());
						hbv.setTreatmentStartDate(obs.getObsDatetime());
						if (this.listHBV == null) {
							this.listHBV = new ArrayList<HbvDto>();
						}
						this.listHBV.add(hbv);
//						this.getListHBV().add(hbv);
					}
				}
				// hcv
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_HCV)) {
					if (obs.getValueCoded().getConceptId().equals(160922)) {
						HcvDto hcv = new HcvDto();
						hcv.setPlace(this.locationDefault);
						hcv.setDiagnosisDate(obs.getObsDatetime());
						if (this.listHCV == null) {
							this.listHCV = new ArrayList<HcvDto>();
						}
						this.listHCV.add(hcv);
//						this.getListHCV().add(hcv);
					}
				}
				// thai nghén
				if (obs.getConcept().getConceptId().equals(EclinicaConstant.CONCEPT_PREGNANCY)) {
					if (obs.getValueCoded().getConceptId().equals(1065)) {
						if (obs.getEncounter() != null && obs.getEncounter().getEncounterId() != null) {
							PregnancyDto dto = hashPregnancy.get(obs.getEncounter().getEncounterId());
							if (dto == null) {
								dto = new PregnancyDto();
								dto.setPlacePregnancyReported(this.locationDefault);
								dto.setDatePregnancyReported(obs.getObsDatetime());// ngày mang thai được báo
								dto.setEncounterId(obs.getEncounter().getEncounterId());
								if (this.listPregnancy == null) {
									this.listPregnancy = new ArrayList<PregnancyDto>();
								}
								this.listPregnancy.add(dto);
								hashPregnancy.put(obs.getEncounter().getEncounterId(), dto);
							} else {
								if (this.getListPregnancy() != null && this.getListPregnancy().size() > 0) {
									for (PregnancyDto tpt : this.getListPregnancy()) {
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
							dto.setPlacePregnancyReported(this.locationDefault);
							dto.setChildDeliveryDate(obs.getValueDatetime());// ngày dự sinh
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							if (this.listPregnancy == null) {
								this.listPregnancy = new ArrayList<PregnancyDto>();
							}
							this.listPregnancy.add(dto);
							hashPregnancy.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (this.getListPregnancy() != null && this.getListPregnancy().size() > 0) {
								for (PregnancyDto tpt : this.getListPregnancy()) {
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
							dto.setPlacePregnancyReported(this.locationDefault);
							dto.setGestationalAge(obs.getValueNumeric());// tuổi thai
							dto.setEncounterId(obs.getEncounter().getEncounterId());
							if (this.listPregnancy == null) {
								this.listPregnancy = new ArrayList<PregnancyDto>();
							}
							this.listPregnancy.add(dto);
							hashPregnancy.put(obs.getEncounter().getEncounterId(), dto);
						} else {
							if (this.getListPregnancy() != null && this.getListPregnancy().size() > 0) {
								for (PregnancyDto tpt : this.getListPregnancy()) {
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

			if (this.getArvTreatmentDateStart() == null) {
				// trường hợp chưa bắt đầu ARV thì listCD4 sẽ là listCD4 trước ARV
				for (Obs obs : listCD4) {
					ObjectDto dto = new ObjectDto();
					dto.setPlace(this.locationDefault);
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
					if (this.listCD4BeforeARV == null) {
						this.listCD4BeforeARV = new ArrayList<ObjectDto>();
					}
					this.listCD4BeforeARV.add(dto);
				}

			} else {
				for (Obs obs : listCD4) {
					ObjectDto dto = new ObjectDto();
					dto.setPlace(this.locationDefault);
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
					if (obs.getObsDatetime().before(this.getArvTreatmentDateStart())) {
						if (this.listCD4BeforeARV == null) {
							this.listCD4BeforeARV = new ArrayList<ObjectDto>();
						}
						this.listCD4BeforeARV.add(dto);
//						this.getListCD4BeforeARV().add(dto);
					} else {
						if (this.listCD4DuringARV == null) {
							this.listCD4DuringARV = new ArrayList<ObjectDto>();
						}
						this.listCD4DuringARV.add(dto);
//						this.getListCD4DuringARV().add(dto);
					}
				}

			}

		}
		// END CD4
		// Viral Load
		if (listVL != null && listVL.size() > 0) {
			for (Obs obs : listVL) {
				if (this.getArvTreatmentDateStart() != null
						&& (obs.getObsDatetime().after(this.getArvTreatmentDateStart())
								|| obs.getObsDatetime().equals(this.getArvTreatmentDateStart()))) {
					ObjectDto dto = new ObjectDto();
					dto.setPlace(this.locationDefault);
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
					if (this.listVlResultDuringARV == null) {
						this.listVlResultDuringARV = new ArrayList<ObjectDto>();
					}
					this.listVlResultDuringARV.add(dto);
				}
			}
		}
		// điều trị lao - active list
		if (p.getActiveLists() != null && p.getActiveLists().size() > 0) {
			for (ActiveList ac : p.getActiveLists()) {
				if (ac.getActiveListType() != null && ac.getActiveListType().getActiveListTypeId() != null
						&& ac.getActiveListType().getActiveListTypeId().equals(3)) {
					if (this.listTBTreatment == null) {
						this.listTBTreatment = new ArrayList<TBTreatmentDto>();
					}
					TBTreatmentDto dto = new TBTreatmentDto();
					dto.setPlace(this.locationDefault);
					dto.setStartDate(ac.getStartDate());
					dto.setCompletedDate(ac.getEndDate());
					this.listTBTreatment.add(dto);
				}
			}
		}

	}
}

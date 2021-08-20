package com.globits.adapter.pdma.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.LongStream;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.pdma.domain.Case;
import com.globits.adapter.pdma.domain.CaseOrg;
import com.globits.adapter.pdma.domain.Dictionary;
import com.globits.adapter.pdma.domain.Hepatitis;
import com.globits.adapter.pdma.domain.LabTest;
import com.globits.adapter.pdma.domain.Pregnancy;
import com.globits.adapter.pdma.domain.Recency;
import com.globits.adapter.pdma.domain.RiskInterview;
import com.globits.adapter.pdma.domain.TBProphylaxis;
import com.globits.adapter.pdma.domain.TBTreatment;
import com.globits.adapter.pdma.domain.Treatment;
import com.globits.adapter.pdma.utils.CommonUtils;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer2;

public class CaseDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createDate;

	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime modifyDate;

	private UUID uid;

	private PersonDto person;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime hivScreenDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime hivConfirmDate;

	private String confirmLabName;

	private HIVConfirmLabDto confirmLab;

	private String hivConfirmId;

	private String hivInfoId;

	private Boolean hivInfoIdLocked;

	private String nationalHealthId;

	private String shiNumber;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime lastSyncDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime arvStartDate;
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime secondLineStartDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime thirdLineStartDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime fourthLineStartDate;
	private RegimenDto currentArvRegimen;

	private String currentArvRegimenName;

	private Integer currentArvRegimenLine;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime currentArvRegimenStartDate;


	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)

	private boolean deleted;

//	private int caseStatus;

	private Set<CaseOrgDto> caseOrgs = new LinkedHashSet<>();

	private Set<LabTestDto> labTests = new LinkedHashSet<>();

	private Set<TreatmentDto> treatments = new LinkedHashSet<>();

	private Set<RiskInterviewDto> riskInterviews = new LinkedHashSet<>();

	private Set<PregnancyDto> pregnancies = new LinkedHashSet<>();

	private Set<HepatitisDto> hepatitises = new LinkedHashSet<>();

	private Set<RecencyDto> recencies = new LinkedHashSet<>();

	private Set<TBProphylaxisDto> tbpros = new LinkedHashSet<>();

	private Set<TBTreatmentDto> tbtxs = new LinkedHashSet<>();

	private int mmdStatus;

	public CaseDto() {
	}
	public CaseDto(Case entity,boolean collapse) {
		super();

		if (entity == null) {
			return;
		}
		this.id = entity.getId();

		this.uid = entity.getUid();
		this.hivConfirmDate = entity.getHivConfirmDate();
		this.confirmLabName = entity.getConfirmLabName();
		this.hivConfirmId = entity.getHivConfirmId();
		this.nationalHealthId = entity.getNationalHealthId();
		this.lastSyncDate = entity.getLastSyncDate();
		this.arvStartDate = entity.getArvStartDate();
		this.secondLineStartDate = entity.getSecondLineStartDate();
		this.thirdLineStartDate = entity.getThirdLineStartDate();
		this.fourthLineStartDate = entity.getFourthLineStartDate();
		this.currentArvRegimenName = entity.getCurrentArvRegimenName();
		this.currentArvRegimenLine = entity.getCurrentArvRegimenLine();
		this.currentArvRegimenStartDate = entity.getCurrentArvRegimenStartDate();
		this.secondLineStartDate = entity.getSecondLineStartDate();
		this.thirdLineStartDate = entity.getThirdLineStartDate();
		this.fourthLineStartDate = entity.getFourthLineStartDate();
//		this.caseStatus = entity.getCaseStatus();
		if (entity.getPerson() != null) {
			this.person = new PersonDto(entity.getPerson());
		}

		if (entity.getConfirmLab() != null) {
			this.confirmLab = new HIVConfirmLabDto(entity.getConfirmLab());
		}

		if (entity.getCurrentArvRegimen() != null) {
			this.currentArvRegimen = new RegimenDto(entity.getCurrentArvRegimen());
		}

		if (entity.getLabTests() != null) {
			entity.getLabTests().parallelStream().forEachOrdered(s -> {
				LabTestDto ldto = new LabTestDto();
				ldto.setId(s.getId());
				ldto.setId(s.getId());
				ldto.setTestType(s.getTestType());
				ldto.setSampleDate(s.getSampleDate());
				ldto.setResultDate(s.getResultDate());
				ldto.setResultNumber(s.getResultNumber());
				ldto.setResultText(s.getResultText());

				this.labTests.add(ldto);
			});
		}

		if (!CommonUtils.isEmpty(entity.getRiskInterviews())) {
			RiskInterview e = entity.getRiskInterviews().toArray(new RiskInterview[0])[0];
			RiskInterviewDto rdto = new RiskInterviewDto();
			rdto.setId(e.getId());
			rdto.setInterviewDate(e.getInterviewDate());
			rdto.setOtherRiskGroupText(e.getOtherRiskGroupText());

			List<DictionaryDto> risks = new ArrayList<>();

			for (Dictionary d : e.getRisks()) {
				risks.add(new DictionaryDto(d));
			}

			rdto.getRisks().addAll(risks);

			this.riskInterviews.add(rdto);
		}
		if (!CommonUtils.isEmpty(entity.getRecencies())) {
			Recency e = entity.getRecencies().toArray(new Recency[0])[0];
			RecencyDto rdto = new RecencyDto();
			rdto.setId(e.getId());
			rdto.setScreenSampleDate(e.getScreenSampleDate());
			rdto.setScreenTestDate(e.getScreenTestDate());
			rdto.setScreenResult(e.getScreenResult());
			rdto.setConfirmResult(e.getConfirmResult());

			this.recencies.add(rdto);
		}

		if (!CommonUtils.isEmpty(entity.getTbpros())) {
			TBProphylaxis e = entity.getTbpros().toArray(new TBProphylaxis[0])[0];
			TBProphylaxisDto tdto = new TBProphylaxisDto();
			tdto.setId(e.getId());
			tdto.setStartDate(e.getStartDate());
			tdto.setEndDate(e.getEndDate());
			tdto.setResult(e.getResult());

			this.tbpros.add(tdto);
		}

		if (!CommonUtils.isEmpty(entity.getTbtxs())) {
			TBTreatment e = entity.getTbtxs().toArray(new TBTreatment[0])[0];
			TBTreatmentDto tdto = new TBTreatmentDto();
			tdto.setId(e.getId());
			tdto.setDiagnoseDate(e.getDiagnoseDate());
			tdto.setTxStartDate(e.getTxStartDate());
			tdto.setTxEndDate(e.getTxEndDate());
			tdto.setFacilityName(e.getFacilityName());

			this.tbtxs.add(tdto);
		}

		if (!CommonUtils.isEmpty(entity.getHepatitises())) {
			List<HepatitisDto> list = new ArrayList<>();
			for (Hepatitis e : entity.getHepatitises()) {
				HepatitisDto dto = new HepatitisDto();
				dto.setId(e.getId());

				dto.setTestPositive(e.isTestPositive());
				dto.setTestType(e.getTestType());
				dto.setTestDate(e.getTestDate());

				dto.setOnTreatment(e.isOnTreatment());
				dto.setTxStartDate(e.getTxStartDate());
				dto.setTxEndDate(e.getTxEndDate());

				list.add(dto);
			}

			this.hepatitises.addAll(list);
		}

		if (!CommonUtils.isEmpty(entity.getTreatments())) {
			final List<TreatmentDto> list = new ArrayList<>();

			entity.getTreatments().forEach(e -> {
				TreatmentDto dto = new TreatmentDto();
				dto.setId(e.getId());
				dto.setStartDate(e.getStartDate());
				dto.setRegimenName(e.getRegimenName());
				dto.setRegimenLine(e.getRegimenLine());

				list.add(dto);
			});

			this.treatments.addAll(list);
		}
		if(!collapse) {
			if (!CommonUtils.isEmpty(entity.getPregnancies())) {
				Pregnancy e = entity.getPregnancies().toArray(new Pregnancy[0])[0];
				PregnancyDto pdto = new PregnancyDto();
				pdto.setId(e.getId());
				pdto.setPregnant(e.isPregnant());
				pdto.setAttendedAnc(e.isAttendedAnc());
				pdto.setDueDate(e.getDueDate());
				pdto.setPregResult(e.getPregResult());
				pdto.setChildDob(e.getChildDob());
				pdto.setChildHIVStatus(e.getChildHIVStatus());
				pdto.setChildDiagnosedDate(e.getChildDiagnosedDate());

				this.pregnancies.add(pdto);
			}
			
		}
	}
	
	public CaseDto(Case entity, List<Long> orgIds) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();

		// only set ID if there is no granted organization data
		if (orgIds == null || CommonUtils.isEmpty(orgIds)) {
			return;
		}

		this.uid = entity.getUid();
		this.hivConfirmDate = entity.getHivConfirmDate();
		this.confirmLabName = entity.getConfirmLabName();
		this.hivConfirmId = entity.getHivConfirmId();
		this.nationalHealthId = entity.getNationalHealthId();
		this.lastSyncDate = entity.getLastSyncDate();
		this.arvStartDate = entity.getArvStartDate();
		this.secondLineStartDate = entity.getSecondLineStartDate();
		this.thirdLineStartDate = entity.getThirdLineStartDate();
		this.fourthLineStartDate = entity.getFourthLineStartDate();
		this.currentArvRegimenName = entity.getCurrentArvRegimenName();
		this.currentArvRegimenLine = entity.getCurrentArvRegimenLine();
		this.currentArvRegimenStartDate = entity.getCurrentArvRegimenStartDate();
		this.secondLineStartDate = entity.getSecondLineStartDate();
		this.thirdLineStartDate = entity.getThirdLineStartDate();
		this.fourthLineStartDate = entity.getFourthLineStartDate();
//		this.caseStatus = entity.getCaseStatus();


		if (entity.getPerson() != null) {
			this.person = new PersonDto(entity.getPerson());
		}

		if (entity.getConfirmLab() != null) {
			this.confirmLab = new HIVConfirmLabDto(entity.getConfirmLab());
		}

		if (entity.getCurrentArvRegimen() != null) {
			this.currentArvRegimen = new RegimenDto(entity.getCurrentArvRegimen());
		}

		if (entity.getCaseOrgs() != null) {
			long[] ids = orgIds.stream().mapToLong(i -> i.longValue()).toArray();
			entity.getCaseOrgs().parallelStream()
					.filter(co -> LongStream.of(ids).anyMatch(x -> x == co.getOrganization().getId()))
					.filter(co -> CommonUtils.isTrue(co.isLatestRelationship())).forEachOrdered(s -> {
						this.caseOrgs.add(new CaseOrgDto(s));
					});
		}

		if (entity.getLabTests() != null) {
			entity.getLabTests().parallelStream().forEachOrdered(s -> {
				LabTestDto ldto = new LabTestDto();
				ldto.setId(s.getId());
				ldto.setId(s.getId());
				ldto.setTestType(s.getTestType());
				ldto.setSampleDate(s.getSampleDate());
				ldto.setResultDate(s.getResultDate());
				ldto.setResultNumber(s.getResultNumber());
				ldto.setResultText(s.getResultText());

				this.labTests.add(ldto);
			});
		}

		if (!CommonUtils.isEmpty(entity.getRiskInterviews())) {
			RiskInterview e = entity.getRiskInterviews().toArray(new RiskInterview[0])[0];
			RiskInterviewDto rdto = new RiskInterviewDto();
			rdto.setId(e.getId());
			rdto.setInterviewDate(e.getInterviewDate());
			rdto.setOtherRiskGroupText(e.getOtherRiskGroupText());

			List<DictionaryDto> risks = new ArrayList<>();

			for (Dictionary d : e.getRisks()) {
				risks.add(new DictionaryDto(d));
			}

			rdto.getRisks().addAll(risks);

			this.riskInterviews.add(rdto);
		}
		if (!CommonUtils.isEmpty(entity.getPregnancies())) {
			Pregnancy e = entity.getPregnancies().toArray(new Pregnancy[0])[0];
			PregnancyDto pdto = new PregnancyDto();
			pdto.setId(e.getId());
			pdto.setPregnant(e.isPregnant());
			pdto.setAttendedAnc(e.isAttendedAnc());
			pdto.setDueDate(e.getDueDate());
			pdto.setPregResult(e.getPregResult());
			pdto.setChildDob(e.getChildDob());
			pdto.setChildHIVStatus(e.getChildHIVStatus());
			pdto.setChildDiagnosedDate(e.getChildDiagnosedDate());

			this.pregnancies.add(pdto);
		}

		if (!CommonUtils.isEmpty(entity.getRecencies())) {
			Recency e = entity.getRecencies().toArray(new Recency[0])[0];
			RecencyDto rdto = new RecencyDto();
			rdto.setId(e.getId());
			rdto.setScreenSampleDate(e.getScreenSampleDate());
			rdto.setScreenTestDate(e.getScreenTestDate());
			rdto.setScreenResult(e.getScreenResult());
			rdto.setConfirmResult(e.getConfirmResult());

			this.recencies.add(rdto);
		}

		if (!CommonUtils.isEmpty(entity.getTbpros())) {
			TBProphylaxis e = entity.getTbpros().toArray(new TBProphylaxis[0])[0];
			TBProphylaxisDto tdto = new TBProphylaxisDto();
			tdto.setId(e.getId());
			tdto.setStartDate(e.getStartDate());
			tdto.setEndDate(e.getEndDate());
			tdto.setResult(e.getResult());

			this.tbpros.add(tdto);
		}

		if (!CommonUtils.isEmpty(entity.getTbtxs())) {
			TBTreatment e = entity.getTbtxs().toArray(new TBTreatment[0])[0];
			TBTreatmentDto tdto = new TBTreatmentDto();
			tdto.setId(e.getId());
			tdto.setDiagnoseDate(e.getDiagnoseDate());
			tdto.setTxStartDate(e.getTxStartDate());
			tdto.setTxEndDate(e.getTxEndDate());
			tdto.setFacilityName(e.getFacilityName());

			this.tbtxs.add(tdto);
		}

		if (!CommonUtils.isEmpty(entity.getHepatitises())) {
			List<HepatitisDto> list = new ArrayList<>();
			for (Hepatitis e : entity.getHepatitises()) {
				HepatitisDto dto = new HepatitisDto();
				dto.setId(e.getId());

				dto.setTestPositive(e.isTestPositive());
				dto.setTestType(e.getTestType());
				dto.setTestDate(e.getTestDate());

				dto.setOnTreatment(e.isOnTreatment());
				dto.setTxStartDate(e.getTxStartDate());
				dto.setTxEndDate(e.getTxEndDate());

				list.add(dto);
			}

			this.hepatitises.addAll(list);
		}

		if (!CommonUtils.isEmpty(entity.getTreatments())) {
			final List<TreatmentDto> list = new ArrayList<>();

			entity.getTreatments().forEach(e -> {
				TreatmentDto dto = new TreatmentDto();
				dto.setId(e.getId());
				dto.setStartDate(e.getStartDate());
				dto.setRegimenName(e.getRegimenName());
				dto.setRegimenLine(e.getRegimenLine());

				list.add(dto);
			});

			this.treatments.addAll(list);
		}
	}

	public Case toEntity() {
		Case entity = new Case();
		
		entity.setId(id);
		entity.setUid(uid);
		entity.setHivConfirmDate(hivConfirmDate);
		entity.setConfirmLabName(confirmLabName);
		entity.setHivConfirmId(hivConfirmId);
		entity.setNationalHealthId(nationalHealthId);
		entity.setLastSyncDate(lastSyncDate);
		entity.setArvStartDate(arvStartDate);
		entity.setSecondLineStartDate(secondLineStartDate);
		entity.setThirdLineStartDate(thirdLineStartDate);
		entity.setFourthLineStartDate(fourthLineStartDate);
		entity.setCurrentArvRegimenName(currentArvRegimenName);
		entity.setCurrentArvRegimenLine(currentArvRegimenLine);
		entity.setCurrentArvRegimenStartDate(currentArvRegimenStartDate);
//		entity.setCaseStatus(caseStatus);


		if (confirmLab != null) {
			entity.setConfirmLab(confirmLab.toEntity());
		}

		if (person != null) {
			entity.setPerson(person.toEntity());
		}

		if (currentArvRegimen != null) {
			entity.setCurrentArvRegimen(currentArvRegimen.toEntity());
		}

		if (caseOrgs != null) {
			Set<CaseOrg> entities = new HashSet<>();

			caseOrgs.parallelStream().forEachOrdered(dto -> {
				entities.add(dto.toEntity());
			});

			entity.getCaseOrgs().clear();
			entity.getCaseOrgs().addAll(entities);
		}

		if (labTests != null) {
			Set<LabTest> entities = new LinkedHashSet<>();

			labTests.parallelStream().forEachOrdered(dto -> {
				entities.add(dto.toEntity());
			});

			entity.getLabTests().clear();
			entity.getLabTests().addAll(entities);
		}

		if (treatments != null) {
			Set<Treatment> entities = new HashSet<>();

			treatments.parallelStream().forEachOrdered(dto -> {
				entities.add(dto.toEntity());
			});

			entity.getTreatments().clear();
			entity.getTreatments().addAll(entities);
		}

		if (riskInterviews != null) {
			Set<RiskInterview> entities = new HashSet<>();

			riskInterviews.parallelStream().forEachOrdered(dto -> {
				entities.add(dto.toEntity());
			});

			entity.getRiskInterviews().clear();
			entity.getRiskInterviews().addAll(entities);
		}
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}
	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	public LocalDateTime getHivScreenDate() {
		return hivScreenDate;
	}

	public void setHivScreenDate(LocalDateTime hivScreenDate) {
		this.hivScreenDate = hivScreenDate;
	}

	public LocalDateTime getHivConfirmDate() {
		return hivConfirmDate;
	}

	public void setHivConfirmDate(LocalDateTime hivConfirmDate) {
		this.hivConfirmDate = hivConfirmDate;
	}

	public String getHivConfirmId() {
		return hivConfirmId;
	}

	public void setHivConfirmId(String hivConfirmId) {
		this.hivConfirmId = hivConfirmId;
	}

	public String getHivInfoId() {
		return hivInfoId;
	}

	public void setHivInfoId(String hivInfoId) {
		this.hivInfoId = hivInfoId;
	}

	public Boolean getHivInfoIdLocked() {
		return hivInfoIdLocked;
	}

	public void setHivInfoIdLocked(Boolean hivInfoIdLocked) {
		this.hivInfoIdLocked = hivInfoIdLocked;
	}

	public String getNationalHealthId() {
		return nationalHealthId;
	}

	public void setNationalHealthId(String nationalHealthId) {
		this.nationalHealthId = nationalHealthId;
	}

	public String getShiNumber() {
		return shiNumber;
	}

	public void setShiNumber(String shiNumber) {
		this.shiNumber = shiNumber;
	}

	public LocalDateTime getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(LocalDateTime lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	public String getCurrentArvRegimenName() {
		return currentArvRegimenName;
	}

	public void setCurrentArvRegimenName(String currentArvRegimenName) {
		this.currentArvRegimenName = currentArvRegimenName;
	}

	public Integer getCurrentArvRegimenLine() {
		return currentArvRegimenLine;
	}

	public void setCurrentArvRegimenLine(Integer currentArvRegimenLine) {
		this.currentArvRegimenLine = currentArvRegimenLine;
	}

	public LocalDateTime getCurrentArvRegimenStartDate() {
		return currentArvRegimenStartDate;
	}

	public void setCurrentArvRegimenStartDate(LocalDateTime currentArvRegimenStartDate) {
		this.currentArvRegimenStartDate = currentArvRegimenStartDate;
	}

	public LocalDateTime getArvStartDate() {
		return arvStartDate;
	}

	public void setArvStartDate(LocalDateTime arvStartDate) {
		this.arvStartDate = arvStartDate;
	}

	public String getConfirmLabName() {
		return confirmLabName;
	}

	public void setConfirmLabName(String confirmLabName) {
		this.confirmLabName = confirmLabName;
	}

	public HIVConfirmLabDto getConfirmLab() {
		return confirmLab;
	}

	public void setConfirmLab(HIVConfirmLabDto confirmLab) {
		this.confirmLab = confirmLab;
	}

	public LocalDateTime getSecondLineStartDate() {
		return secondLineStartDate;
	}

	public void setSecondLineStartDate(LocalDateTime secondLineStartDate) {
		this.secondLineStartDate = secondLineStartDate;
	}

	public LocalDateTime getThirdLineStartDate() {
		return thirdLineStartDate;
	}

	public void setThirdLineStartDate(LocalDateTime thirdLineStartDate) {
		this.thirdLineStartDate = thirdLineStartDate;
	}

	public LocalDateTime getFourthLineStartDate() {
		return fourthLineStartDate;
	}

	public void setFourthLineStartDate(LocalDateTime fourthLineStartDate) {
		this.fourthLineStartDate = fourthLineStartDate;
	}
	public RegimenDto getCurrentArvRegimen() {
		return currentArvRegimen;
	}

	public void setCurrentArvRegimen(RegimenDto currentArvRegimen) {
		this.currentArvRegimen = currentArvRegimen;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

//	public int getCaseStatus() {
//		return caseStatus;
//	}
//
//	public void setCaseStatus(int caseStatus) {
//		this.caseStatus = caseStatus;
//	}
	public Set<CaseOrgDto> getCaseOrgs() {
		if (caseOrgs == null) {
			caseOrgs = new LinkedHashSet<>();
		}

		return caseOrgs;
	}

	public void setCaseOrgs(Set<CaseOrgDto> caseOrgs) {
		this.caseOrgs = caseOrgs;
	}

	public Set<LabTestDto> getLabTests() {

		if (labTests == null) {
			labTests = new LinkedHashSet<>();
		}

		return labTests;
	}

	public void setLabTests(Set<LabTestDto> labTests) {
		this.labTests = labTests;
	}

	public Set<TreatmentDto> getTreatments() {

		if (treatments == null) {
			treatments = new LinkedHashSet<>();
		}

		return treatments;
	}

	public void setTreatments(Set<TreatmentDto> treatments) {
		this.treatments = treatments;
	}

	public Set<RiskInterviewDto> getRiskInterviews() {

		if (riskInterviews == null) {
			riskInterviews = new LinkedHashSet<>();
		}

		return riskInterviews;
	}

	public void setRiskInterviews(Set<RiskInterviewDto> riskInterviews) {
		this.riskInterviews = riskInterviews;
	}

	public Set<PregnancyDto> getPregnancies() {

		if (pregnancies == null) {
			pregnancies = new LinkedHashSet<>();
		}

		return pregnancies;
	}

	public void setPregnancies(Set<PregnancyDto> pregnancies) {
		this.pregnancies = pregnancies;
	}
	public Set<RecencyDto> getRecencies() {

		if (recencies == null) {
			recencies = new LinkedHashSet<>();
		}

		return recencies;
	}

	public void setRecencies(Set<RecencyDto> recencies) {
		this.recencies = recencies;
	}

	public Set<TBProphylaxisDto> getTbpros() {

		if (tbpros == null) {
			tbpros = new LinkedHashSet<>();
		}

		return tbpros;
	}

	public void setTbpros(Set<TBProphylaxisDto> tbpros) {
		this.tbpros = tbpros;
	}

	public Set<TBTreatmentDto> getTbtxs() {

		if (tbtxs == null) {
			tbtxs = new LinkedHashSet<>();
		}

		return tbtxs;
	}

	public void setTbtxs(Set<TBTreatmentDto> tbtxs) {
		this.tbtxs = tbtxs;
	}

	public Set<HepatitisDto> getHepatitises() {

		if (hepatitises == null) {
			hepatitises = new LinkedHashSet<>();
		}

		return hepatitises;
	}

	public void setHepatitises(Set<HepatitisDto> hepatitises) {
		this.hepatitises = hepatitises;
	}

	public int getMmdStatus() {
		return mmdStatus;
	}

	public void setMmdStatus(int mmdStatus) {
		this.mmdStatus = mmdStatus;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}

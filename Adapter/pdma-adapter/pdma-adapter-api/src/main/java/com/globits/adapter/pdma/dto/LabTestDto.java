package com.globits.adapter.pdma.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.pdma.domain.LabTest;
import com.globits.adapter.pdma.types.ClinicalTestingType;
import com.globits.adapter.pdma.types.LabTestFundingSource;
import com.globits.adapter.pdma.types.LabTestReason;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer2;

public class LabTestDto  implements Serializable{
	private Long id;

	private OrganizationDto organization;

	private CaseDto theCase;

	private ClinicalTestingType testType;

	private LabTestReason reasonForTesting;

	private LabTestFundingSource fundingSource;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime sampleDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime resultDate;

	private Long resultNumber;

	private String resultText;

	private String sampleSite;

	private String labName;

	private Boolean needConsultation;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime consultation1;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime consultation2;

	private String note;

	public LabTestDto() {
	}

	public LabTestDto(LabTest entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.testType = entity.getTestType();
		this.sampleDate = entity.getSampleDate();
		this.resultDate = entity.getResultDate();
		this.resultNumber = entity.getResultNumber();
		this.resultText = entity.getResultText();
		this.sampleSite = entity.getSampleSite();
		this.labName = entity.getLabName();
		this.reasonForTesting = entity.getReasonForTesting();
		this.fundingSource = entity.getFundingSource();
		this.needConsultation = entity.getNeedConsultation();
		this.consultation1 = entity.getConsultation1();
		this.consultation2 = entity.getConsultation2();
		this.note = entity.getNote();

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
			this.organization.setName(entity.getOrganization().getName());
		}

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());

			PersonDto person = new PersonDto();

			if (entity.getTheCase().getPerson() != null) {
				person.setDob(entity.getTheCase().getPerson().getDob());
				person.setGender(entity.getTheCase().getPerson().getGender());
			}

			this.theCase.setPerson(person);
		}
	}

	public LabTest toEntity() {
		LabTest entity = new LabTest();

		entity.setId(id);
		entity.setTestType(testType);
		entity.setSampleDate(sampleDate);
		entity.setResultDate(resultDate);
		entity.setResultNumber(resultNumber);
		entity.setResultText(resultText);
		entity.setSampleSite(sampleSite);
		entity.setLabName(labName);
		entity.setReasonForTesting(reasonForTesting);
		entity.setFundingSource(fundingSource);
		entity.setNeedConsultation(needConsultation);
		entity.setConsultation1(consultation1);
		entity.setConsultation2(consultation2);
		entity.setNote(note);

		if (organization != null) {
			entity.setOrganization(organization.toEntity());
		}

		if (theCase != null) {
			entity.setTheCase(theCase.toEntity());
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public CaseDto getTheCase() {
		return theCase;
	}

	public void setTheCase(CaseDto theCase) {
		this.theCase = theCase;
	}

	public LabTestReason getReasonForTesting() {
		return reasonForTesting;
	}

	public void setReasonForTesting(LabTestReason reasonForTesting) {
		this.reasonForTesting = reasonForTesting;
	}

	public LabTestFundingSource getFundingSource() {
		return fundingSource;
	}

	public void setFundingSource(LabTestFundingSource fundingSource) {
		this.fundingSource = fundingSource;
	}

	public LocalDateTime getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(LocalDateTime sampleDate) {
		this.sampleDate = sampleDate;
	}

	public LocalDateTime getResultDate() {
		return resultDate;
	}

	public void setResultDate(LocalDateTime resultDate) {
		this.resultDate = resultDate;
	}

	public ClinicalTestingType getTestType() {
		return testType;
	}

	public void setTestType(ClinicalTestingType testType) {
		this.testType = testType;
	}

	public Long getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(Long resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getSampleSite() {
		return sampleSite;
	}

	public void setSampleSite(String sampleSite) {
		this.sampleSite = sampleSite;
	}

	public Boolean getNeedConsultation() {
		return needConsultation;
	}

	public void setNeedConsultation(Boolean needConsultation) {
		this.needConsultation = needConsultation;
	}

	public LocalDateTime getConsultation1() {
		return consultation1;
	}

	public void setConsultation1(LocalDateTime consultation1) {
		this.consultation1 = consultation1;
	}

	public LocalDateTime getConsultation2() {
		return consultation2;
	}

	public void setConsultation2(LocalDateTime consultation2) {
		this.consultation2 = consultation2;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}

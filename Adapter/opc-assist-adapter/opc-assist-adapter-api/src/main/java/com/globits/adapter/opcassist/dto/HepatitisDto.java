package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.opcassist.domain.Hepatitis;
import com.globits.adapter.opcassist.types.ClinicalTestingType;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer2;

public class HepatitisDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private OrganizationDto organization;

	private CaseDto theCase;

	private ClinicalTestingType testType;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime testDate;

	private boolean testPositive;

	private boolean onTreatment;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime txStartDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime txEndDate;

	public HepatitisDto() {
	}

	public HepatitisDto(Hepatitis entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.testType = entity.getTestType();
		this.testDate = entity.getTestDate();
		this.testPositive = entity.isTestPositive();
		this.onTreatment = entity.isOnTreatment();
		this.txStartDate = entity.getTxStartDate();
		this.txEndDate = entity.getTxEndDate();

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());
		}

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
		}
	}

	public Hepatitis toEntity() {
		Hepatitis entity = new Hepatitis();

		entity.setId(id);
		entity.setTestType(testType);
		entity.setTestDate(testDate);
		entity.setTestPositive(testPositive);
		entity.setOnTreatment(onTreatment);
		entity.setTxStartDate(txStartDate);
		entity.setTxEndDate(txEndDate);

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

	public ClinicalTestingType getTestType() {
		return testType;
	}

	public void setTestType(ClinicalTestingType testType) {
		this.testType = testType;
	}

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}

	public boolean isTestPositive() {
		return testPositive;
	}

	public void setTestPositive(boolean testPositive) {
		this.testPositive = testPositive;
	}

	public boolean isOnTreatment() {
		return onTreatment;
	}

	public void setOnTreatment(boolean onTreatment) {
		this.onTreatment = onTreatment;
	}

	public LocalDateTime getTxStartDate() {
		return txStartDate;
	}

	public void setTxStartDate(LocalDateTime txStartDate) {
		this.txStartDate = txStartDate;
	}

	public LocalDateTime getTxEndDate() {
		return txEndDate;
	}

	public void setTxEndDate(LocalDateTime txEndDate) {
		this.txEndDate = txEndDate;
	}
}

package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.opcassist.domain.TBTreatment;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer2;

public class TBTreatmentDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime testDate;

	private String labName;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime diagnoseDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime txStartDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime txEndDate;

	private String facilityName;

	private OrganizationDto organization;

	private CaseDto theCase;

	public TBTreatmentDto() {

	}

	public TBTreatmentDto(TBTreatment entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.testDate = entity.getTestDate();
		this.labName = entity.getLabName();
		this.diagnoseDate = entity.getDiagnoseDate();
		this.txStartDate = entity.getTxStartDate();
		this.txEndDate = entity.getTxEndDate();
		this.facilityName = entity.getFacilityName();

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
			this.organization.setName(entity.getOrganization().getName());
		}

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());
		}
	}

	public TBTreatment toEntity() {
		TBTreatment entity = new TBTreatment();

		entity.setId(id);
		entity.setTestDate(testDate);
		entity.setLabName(labName);
		entity.setDiagnoseDate(diagnoseDate);
		entity.setTxStartDate(txStartDate);
		entity.setTxEndDate(txEndDate);
		entity.setFacilityName(facilityName);

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

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public LocalDateTime getDiagnoseDate() {
		return diagnoseDate;
	}

	public void setDiagnoseDate(LocalDateTime diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
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

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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
}

package com.globits.adapter.pdma.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.pdma.domain.Case;
import com.globits.adapter.pdma.domain.CaseOrg;
import com.globits.adapter.pdma.domain.LabTest;
import com.globits.adapter.pdma.types.ClinicalTestingType;
import com.globits.adapter.pdma.types.EnrollmentType;
import com.globits.adapter.pdma.types.PatientStatus;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer2;

public class CaseOrgDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private CaseDto theCase;

	private OrganizationDto organization;

	private String organizationName;

	private String patientChartId;

	private PatientStatus prevStatus;

	private PatientStatus status;

	private boolean latestRelationship;

	private EnrollmentType enrollmentType;

	private String arvGroup;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime arvStartDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime startDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime endDate;

	private String endingReason;

	private Boolean current;

	private boolean refTrackingOnly;

	// Check if when a case is transferred out, the sending facility is able to
	// update the referral status
	private boolean updateable;

	// Check if when a case was transferred out and after sometime return to the
	// current OPC for continuum of care
	private boolean reEnrollable;

	// Check if the case org is editable (TRUE when the current logged in user is a
	// site manager and is granted EDIT access to the org in the case-org record)
	private boolean editable;

	public CaseOrgDto() {

	}

	public CaseOrgDto(CaseOrg entity) {
		super();

		if (entity == null) {
			return;
		}

		id = entity.getId();
		organizationName = entity.getOrganizationName();
		patientChartId = entity.getPatientChartId();
		arvStartDate = entity.getArvStartDate();
		startDate = entity.getStartDate();
		endDate = entity.getEndDate();
		endingReason = entity.getEndingReason();
		prevStatus = entity.getPrevStatus();
		status = entity.getStatus();
		latestRelationship = entity.isLatestRelationship();
		enrollmentType = entity.getEnrollmentType();
		arvGroup = entity.getArvGroup();
		current = entity.getCurrent();
		refTrackingOnly = entity.isRefTrackingOnly();

		if (entity.getTheCase() != null) {
			Case c = entity.getTheCase();

			theCase = new CaseDto();
			theCase.setId(c.getId());
			theCase.setArvStartDate(c.getArvStartDate());
			theCase.setHivConfirmDate(c.getHivConfirmDate());
			theCase.setConfirmLab(new HIVConfirmLabDto(c.getConfirmLab()));
			theCase.setConfirmLabName(c.getConfirmLabName());
			theCase.setSecondLineStartDate(c.getSecondLineStartDate());
			theCase.setCurrentArvRegimen(new RegimenDto(c.getCurrentArvRegimen()));
			theCase.setCurrentArvRegimenLine(c.getCurrentArvRegimenLine());
			theCase.setCurrentArvRegimenName(c.getCurrentArvRegimenName());
			theCase.setCurrentArvRegimenStartDate(c.getCurrentArvRegimenStartDate());
			theCase.setPerson(new PersonDto(c.getPerson()));

			List<LabTest> vlTests = c.getLabTests().parallelStream()
					.filter(t -> t.getTestType() == ClinicalTestingType.VIRAL_LOAD).collect(Collectors.toList());
			/*
			 * if (vlTests != null && vlTests.size() > 0) { LabTestDto latestTest = new
			 * LabTestDto(vlTests.get(0)); theCase.setLabTests(Sets.newHashSet(latestTest));
			 * }
			 */


		}

		if (entity.getOrganization() != null) {
			organization = new OrganizationDto();
			organization.setId(entity.getOrganization().getId());
			organization.setCode(entity.getOrganization().getCode());
			organization.setName(entity.getOrganization().getName());
		}
	}

	public CaseOrg toEntity() {

		CaseOrg entity = new CaseOrg();

		entity.setId(id);
		entity.setOrganizationName(organizationName);
		entity.setPatientChartId(patientChartId);
		entity.setArvStartDate(arvStartDate);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		entity.setEndingReason(endingReason);
		entity.setPrevStatus(prevStatus);
		entity.setStatus(status);
		entity.setLatestRelationship(latestRelationship);
		entity.setEnrollmentType(enrollmentType);
		entity.setArvGroup(arvGroup);
		entity.setCurrent(current);
		entity.setRefTrackingOnly(refTrackingOnly);

		if (theCase != null) {
			entity.setTheCase(theCase.toEntity());
		}

		if (organization != null) {
			entity.setOrganization(organization.toEntity());
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CaseDto getTheCase() {
		return theCase;
	}

	public void setTheCase(CaseDto theCase) {
		this.theCase = theCase;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPatientChartId() {
		return patientChartId;
	}

	public void setPatientChartId(String patientChartId) {
		this.patientChartId = patientChartId;
	}

	public LocalDateTime getArvStartDate() {
		return arvStartDate;
	}

	public void setArvStartDate(LocalDateTime arvStartDate) {
		this.arvStartDate = arvStartDate;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getEndingReason() {
		return endingReason;
	}

	public void setEndingReason(String endingReason) {
		this.endingReason = endingReason;
	}

	public PatientStatus getPrevStatus() {
		return prevStatus;
	}

	public void setPrevStatus(PatientStatus prevStatus) {
		this.prevStatus = prevStatus;
	}

	public PatientStatus getStatus() {
		return status;
	}

	public void setStatus(PatientStatus status) {
		this.status = status;
	}

	public boolean isLatestRelationship() {
		return latestRelationship;
	}

	public void setLatestRelationship(boolean latestRelationship) {
		this.latestRelationship = latestRelationship;
	}

	public boolean isRefTrackingOnly() {
		return refTrackingOnly;
	}

	public void setRefTrackingOnly(boolean refTrackingOnly) {
		this.refTrackingOnly = refTrackingOnly;
	}

	public EnrollmentType getEnrollmentType() {
		return enrollmentType;
	}

	public void setEnrollmentType(EnrollmentType enrollmentType) {
		this.enrollmentType = enrollmentType;
	}

	public String getArvGroup() {
		return arvGroup;
	}

	public void setArvGroup(String arvGroup) {
		this.arvGroup = arvGroup;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public boolean isUpdateable() {
		return updateable;
	}

	public void setUpdateable(boolean updateable) {
		this.updateable = updateable;
	}

	public boolean isReEnrollable() {
		return reEnrollable;
	}

	public void setReEnrollable(boolean reEnrollable) {
		this.reEnrollable = reEnrollable;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}

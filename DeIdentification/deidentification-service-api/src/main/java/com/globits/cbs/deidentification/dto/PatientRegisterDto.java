package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class PatientRegisterDto {
/*
 * Patient treatment history (transfer-in, transfer-out)
 */
	private Date startDate;
	private Date endDate;
	private OrganizationDto organization;//Registered Organization
	private Boolean isInitialOrg;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public Boolean getIsInitialOrg() {
		return isInitialOrg;
	}
	public void setIsInitialOrg(Boolean isInitialOrg) {
		this.isInitialOrg = isInitialOrg;
	}
	
}

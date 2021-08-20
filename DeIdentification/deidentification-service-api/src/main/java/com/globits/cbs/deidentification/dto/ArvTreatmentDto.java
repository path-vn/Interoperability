package com.globits.cbs.deidentification.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ArvTreatmentDto {
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date arvStartDate;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date arvStopDate;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lossFollowUpDate;
	private OrganizationDto org;//Registered Organization of the Patient
	private OrganizationDto placeOfTransferOut;
	public Date getArvStartDate() {
		return arvStartDate;
	}
	public void setArvStartDate(Date arvStartDate) {
		this.arvStartDate = arvStartDate;
	}
	public Date getArvStopDate() {
		return arvStopDate;
	}
	public void setArvStopDate(Date arvStopDate) {
		this.arvStopDate = arvStopDate;
	}
	public Date getLossFollowUpDate() {
		return lossFollowUpDate;
	}
	public void setLossFollowUpDate(Date lossFollowUpDate) {
		this.lossFollowUpDate = lossFollowUpDate;
	}
	public OrganizationDto getOrg() {
		return org;
	}
	public void setOrg(OrganizationDto org) {
		this.org = org;
	}
	public OrganizationDto getPlaceOfTransferOut() {
		return placeOfTransferOut;
	}
	public void setPlaceOfTransferOut(OrganizationDto placeOfTransferOut) {
		this.placeOfTransferOut = placeOfTransferOut;
	}
	
}

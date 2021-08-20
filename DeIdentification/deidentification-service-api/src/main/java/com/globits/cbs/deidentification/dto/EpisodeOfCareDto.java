package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class EpisodeOfCareDto {
	private PatientDto patient;
	private PeriodDto period;
	private ServiceRequestDto referralRequest;
	
	private Date arvStartDate;
	private Date arvStopDate;
	private Date lossFollowUpDate;
	
	public PatientDto getPatient() {
		return patient;
	}
	
	public PeriodDto getPeriod() {
		return period;
	}
	public void setPeriod(PeriodDto period) {
		this.period = period;
	}
	public ServiceRequestDto getReferralRequest() {
		return referralRequest;
	}
	public void setReferralRequest(ServiceRequestDto referralRequest) {
		this.referralRequest = referralRequest;
	}
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
	
	
}

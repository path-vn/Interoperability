package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class CoMorbidityTreatmentDto {
	private String treatmentId;
	private Date startDate;
	private Date endDate;
	private String placeProvided;
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
	public String getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}
	public String getPlaceProvided() {
		return placeProvided;
	}
	public void setPlaceProvided(String placeProvided) {
		this.placeProvided = placeProvided;
	}
	
	
}

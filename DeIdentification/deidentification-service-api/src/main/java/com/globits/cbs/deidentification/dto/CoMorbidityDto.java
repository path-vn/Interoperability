package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class CoMorbidityDto {
	private Date diagnosisDate;
	private String treatmentId;
	private CoMorbidityTreatmentDto treatment;
	public Date getDiagnosisDate() {
		return diagnosisDate;
	}
	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	public CoMorbidityTreatmentDto getTreatment() {
		return treatment;
	}
	public void setTreatment(CoMorbidityTreatmentDto treatment) {
		this.treatment = treatment;
	}
	public String getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	} 
	
	
}

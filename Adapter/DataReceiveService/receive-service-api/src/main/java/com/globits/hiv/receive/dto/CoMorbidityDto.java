package com.globits.hiv.receive.dto;

import java.util.Date;
import java.util.List;

import com.globits.hiv.receive.valueset.DiagnosisValueSet;

public class CoMorbidityDto {

	private Date diagnosisDate;
	private SystemCodeDto diagnosisResult;
//	private String treatmentId;
	private List<CoMorbidityTreatmentDto> treatment;

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

//	public String getTreatmentId() {
//		return treatmentId;
//	}
//
//	public void setTreatmentId(String treatmentId) {
//		this.treatmentId = treatmentId;
//	}

	public List<CoMorbidityTreatmentDto> getTreatment() {
		return treatment;
	}

	public void setTreatment(List<CoMorbidityTreatmentDto> treatment) {
		this.treatment = treatment;
	}

	public SystemCodeDto getDiagnosisResult() {
		return diagnosisResult;
	}

	public void setDiagnosisResult(SystemCodeDto diagnosisResult) {
		this.diagnosisResult = diagnosisResult;
	}

}

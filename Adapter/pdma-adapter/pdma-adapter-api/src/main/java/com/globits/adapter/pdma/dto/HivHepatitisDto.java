package com.globits.adapter.pdma.dto;

import java.io.Serializable;

import com.globits.adapter.pdma.domain.Hepatitis;

public class HivHepatitisDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diagnosisDate;
	private String treatmentStartDate;
	private String treatmentEndDate;
	private String placeProvided;
	public String getDiagnosisDate() {
		return diagnosisDate;
	}
	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	public String getTreatmentStartDate() {
		return treatmentStartDate;
	}
	public void setTreatmentStartDate(String treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}
	public String getTreatmentEndDate() {
		return treatmentEndDate;
	}
	public void setTreatmentEndDate(String treatmentEndDate) {
		this.treatmentEndDate = treatmentEndDate;
	}
	public String getPlaceProvided() {
		return placeProvided;
	}
	public void setPlaceProvided(String placeProvided) {
		this.placeProvided = placeProvided;
	}
	public HivHepatitisDto() {
		
	}
	public HivHepatitisDto(Hepatitis item) {
		if(item.getTestDate()!=null) {
			this.setDiagnosisDate(item.getTestDate().toString());
		}
		if(item.getTxStartDate()!=null) {
			this.setTreatmentStartDate(item.getTxStartDate().toString());
		}
		if(item.getTxEndDate()!=null) {
			this.setTreatmentEndDate(item.getTxEndDate().toString());
		}
		if(item.getOrganization()!=null) {
			this.setPlaceProvided(item.getOrganization().getName());
		}
	}
}

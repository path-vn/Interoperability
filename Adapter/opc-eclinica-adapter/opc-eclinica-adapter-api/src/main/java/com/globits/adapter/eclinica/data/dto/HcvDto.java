package com.globits.adapter.eclinica.data.dto;

import java.util.Date;

public class HcvDto {// HCV
	private Date diagnosisDate;
	private String place;

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public HcvDto() {

	}

}

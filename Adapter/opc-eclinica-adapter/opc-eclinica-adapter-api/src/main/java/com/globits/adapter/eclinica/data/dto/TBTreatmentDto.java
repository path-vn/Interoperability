package com.globits.adapter.eclinica.data.dto;

import java.util.Date;

public class TBTreatmentDto {//Điều trị lao
	private Date startDate;
	private Date completedDate;
	private String place;
	private String regimen;//phác đồ

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public TBTreatmentDto() {

	}

}

package com.globits.adapter.eclinica.data.dto;

import java.util.Date;

public class PregnancyDto {
	private Date datePregnancyReported;//ngày mang thai được báo
	private String placePregnancyReported;//nơi báo cáo
	private Date childDeliveryDate;//ngày sinh con được báo
	private String childDeliveryPlace;//nơi sinh được báo
	private Double gestationalAge;//tuổi thai

	private String childDateOfBirth;//ngày sinh con
	private Double gestationAtDelivery;//Tuổi thai khi sinh (tuần)
	private Integer encounterId;

	public String getChildDeliveryPlace() {
		return childDeliveryPlace;
	}

	public void setChildDeliveryPlace(String childDeliveryPlace) {
		this.childDeliveryPlace = childDeliveryPlace;
	}

	public String getChildDateOfBirth() {
		return childDateOfBirth;
	}

	public void setChildDateOfBirth(String childDateOfBirth) {
		this.childDateOfBirth = childDateOfBirth;
	}

	public Date getDatePregnancyReported() {
		return datePregnancyReported;
	}

	public void setDatePregnancyReported(Date datePregnancyReported) {
		this.datePregnancyReported = datePregnancyReported;
	}

	public String getPlacePregnancyReported() {
		return placePregnancyReported;
	}

	public void setPlacePregnancyReported(String placePregnancyReported) {
		this.placePregnancyReported = placePregnancyReported;
	}

	public Date getChildDeliveryDate() {
		return childDeliveryDate;
	}

	public void setChildDeliveryDate(Date childDeliveryDate) {
		this.childDeliveryDate = childDeliveryDate;
	}

	public Double getGestationAtDelivery() {
		return gestationAtDelivery;
	}

	public void setGestationAtDelivery(Double gestationAtDelivery) {
		this.gestationAtDelivery = gestationAtDelivery;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Double getGestationalAge() {
		return gestationalAge;
	}

	public void setGestationalAge(Double gestationalAge) {
		this.gestationalAge = gestationalAge;
	}

	public PregnancyDto() {

	}
}

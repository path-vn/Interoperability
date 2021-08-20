package com.globits.hivimportcsadapter.dto;

import java.util.Date;

public class HivDiagnosisDto {
	private Date confirmatoryDate;
	private OrganizationDto confirmatoryLab;
	private String placeOfSpecimenCollection;//Nơi lấy mẫu
	private Date specimenCollectionDate;
	
	private SystemCodeDto riskPopulation;//Đối tượng
	private SystemCodeDto riskBehavior;//Hành vi nguy cơ
	private SystemCodeDto transmissionRoute;//Đường lây
	
	public Date getConfirmatoryDate() {
		return confirmatoryDate;
	}
	public void setConfirmatoryDate(Date confirmatoryDate) {
		this.confirmatoryDate = confirmatoryDate;
	}
	public OrganizationDto getConfirmatoryLab() {
		return confirmatoryLab;
	}
	public void setConfirmatoryLab(OrganizationDto confirmatoryLab) {
		this.confirmatoryLab = confirmatoryLab;
	}
	public String getPlaceOfSpecimenCollection() {
		return placeOfSpecimenCollection;
	}
	public void setPlaceOfSpecimenCollection(String placeOfSpecimenCollection) {
		this.placeOfSpecimenCollection = placeOfSpecimenCollection;
	}
	public Date getSpecimenCollectionDate() {
		return specimenCollectionDate;
	}
	public void setSpecimenCollectionDate(Date specimenCollectionDate) {
		this.specimenCollectionDate = specimenCollectionDate;
	}
	public SystemCodeDto getRiskPopulation() {
		return riskPopulation;
	}
	public void setRiskPopulation(SystemCodeDto riskPopulation) {
		this.riskPopulation = riskPopulation;
	}
	public SystemCodeDto getRiskBehavior() {
		return riskBehavior;
	}
	public void setRiskBehavior(SystemCodeDto riskBehavior) {
		this.riskBehavior = riskBehavior;
	}
	public SystemCodeDto getTransmissionRoute() {
		return transmissionRoute;
	}
	public void setTransmissionRoute(SystemCodeDto transmissionRoute) {
		this.transmissionRoute = transmissionRoute;
	}
}

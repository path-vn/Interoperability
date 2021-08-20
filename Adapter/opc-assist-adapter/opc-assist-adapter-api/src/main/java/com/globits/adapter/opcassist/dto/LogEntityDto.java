package com.globits.adapter.opcassist.dto;

import java.util.Date;


public class LogEntityDto {
	private  Date logDate;
	private  String name;
	private String artID;
	private String nationalID;
	private String healthInsuranceNumber;
	private String passportNumber;
	private String syncorg;
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtID() {
		return artID;
	}
	public void setArtID(String artID) {
		this.artID = artID;
	}
	public String getNationalID() {
		return nationalID;
	}
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	public String getHealthInsuranceNumber() {
		return healthInsuranceNumber;
	}
	public void setHealthInsuranceNumber(String healthInsuranceNumber) {
		this.healthInsuranceNumber = healthInsuranceNumber;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getSyncorg() {
		return syncorg;
	}
	public void setSyncorg(String syncorg) {
		this.syncorg = syncorg;
	}
	public LogEntityDto() {
		
	}
	
}

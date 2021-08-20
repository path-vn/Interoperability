package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class LabTestDto {
	private String name;
	private String stringValue;
	private Date testPerformanceDate;
	private Date specimenCollectionDate;
	private String specimenCollectionPlace;
	private Integer valueNumber;
	private SystemCodeDto testResultOther;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public Date getTestPerformanceDate() {
		return testPerformanceDate;
	}
	public void setTestPerformanceDate(Date testPerformanceDate) {
		this.testPerformanceDate = testPerformanceDate;
	}
	public Date getSpecimenCollectionDate() {
		return specimenCollectionDate;
	}
	public void setSpecimenCollectionDate(Date specimenCollectionDate) {
		this.specimenCollectionDate = specimenCollectionDate;
	}
	public String getSpecimenCollectionPlace() {
		return specimenCollectionPlace;
	}
	public void setSpecimenCollectionPlace(String specimenCollectionPlace) {
		this.specimenCollectionPlace = specimenCollectionPlace;
	}
	public Integer getValueNumber() {
		return valueNumber;
	}
	public void setValueNumber(Integer valueNumber) {
		this.valueNumber = valueNumber;
	}
	public SystemCodeDto getTestResultOther() {
		return testResultOther;
	}
	public void setTestResultOther(SystemCodeDto testResultOther) {
		this.testResultOther = testResultOther;
	}
	
	
}

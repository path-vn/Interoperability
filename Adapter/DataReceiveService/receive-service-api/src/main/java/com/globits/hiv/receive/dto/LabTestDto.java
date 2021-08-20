package com.globits.hiv.receive.dto;

import java.util.Date;

import com.globits.hiv.receive.valueset.LabTestReasonValueSet;
import com.globits.hiv.receive.valueset.LabTestResultOtherValueSet;

public class LabTestDto {

	private SystemCodeDto testReason;
	private Date specimenCollectionDate;
	private OrganizationDto specimenCollectionPlace;
	private Date testPerformanceDate;
	private Integer valueNumber;
	private SystemCodeDto testResultOther;

	private String stringValue;
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
	public OrganizationDto getSpecimenCollectionPlace() {
		return specimenCollectionPlace;
	}
	public void setSpecimenCollectionPlace(OrganizationDto specimenCollectionPlace) {
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
	public SystemCodeDto getTestReason() {
		return testReason;
	}
	public void setTestReason(SystemCodeDto testReason) {
		this.testReason = testReason;
	}
	
}

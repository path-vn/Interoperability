package com.globits.adapter.pdma.dto;

import java.io.Serializable;

import com.globits.adapter.pdma.domain.LabTest;

public class HivViralLoadDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String testReason;
	private String sampleDate;
	private String samplePlace;
	private String resultDate;
	private String org;
	private String resultText;
	private Long resultNumber;
	private String labName;


	public HivViralLoadDto(LabTest item) {
		if (item.getReasonForTesting() != null) {
			this.setTestReason(item.getReasonForTesting().toString());
		}
		if(item.getSampleDate()!=null) {
			this.setSampleDate(item.getSampleDate().toString());
		}
		if (item.getSampleSite() != null) {
			this.setSamplePlace(item.getSampleSite());
		}
		if(item.getResultDate()!=null) {
			this.setResultDate(item.getResultDate().toString());
		}
		this.setOrg(item.getOrganization().getName());
		this.setResultNumber(item.getResultNumber());
		this.setResultText(item.getResultText());
		this.setLabName(item.getLabName());
	}


	public String getSamplePlace() {
		return samplePlace;
	}


	public void setSamplePlace(String samplePlace) {
		this.samplePlace = samplePlace;
	}


	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	
	public String getResultText() {
		return resultText;
	}


	public void setResultText(String resultText) {
		this.resultText = resultText;
	}


	public Long getResultNumber() {
		return resultNumber;
	}


	public void setResultNumber(Long resultNumber) {
		this.resultNumber = resultNumber;
	}


	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getSampleDate() {
		return sampleDate;
	}
	public void setSampleDate(String sampleDate) {
		this.sampleDate = sampleDate;
	}
	public String getTestReason() {
		return testReason;
	}
	public void setTestReason(String testReason) {
		this.testReason = testReason;
	}
	

	
}



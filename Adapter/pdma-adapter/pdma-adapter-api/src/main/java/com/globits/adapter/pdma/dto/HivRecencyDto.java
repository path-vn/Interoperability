package com.globits.adapter.pdma.dto;

import java.io.Serializable;

import com.globits.adapter.pdma.domain.Recency;

public class HivRecencyDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dateOfTestPerformance;
	private String testResult;
	private String testResultOther;
	private String recentInfectionConclusion;
	private String placeSpecimenCollected;
	
	public String getDateOfTestPerformance() {
		return dateOfTestPerformance;
	}
	public void setDateOfTestPerformance(String dateOfTestPerformance) {
		this.dateOfTestPerformance = dateOfTestPerformance;
	}

	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getTestResultOther() {
		return testResultOther;
	}
	public void setTestResultOther(String testResultOther) {
		this.testResultOther = testResultOther;
	}
	public String getRecentInfectionConclusion() {
		return recentInfectionConclusion;
	}
	public void setRecentInfectionConclusion(String recentInfectionConclusion) {
		this.recentInfectionConclusion = recentInfectionConclusion;
	}
	public String getPlaceSpecimenCollected() {
		return placeSpecimenCollected;
	}
	public void setPlaceSpecimenCollected(String placeSpecimenCollected) {
		this.placeSpecimenCollected = placeSpecimenCollected;
	}
	public HivRecencyDto() {
		
	}
	
	public HivRecencyDto(Recency item) {
		if(item.getVlTestDate()!=null) {
			this.setDateOfTestPerformance(item.getVlTestDate().toString());
		}
		if(item.getVlResult()!=null) {
			this.setTestResult(item.getVlResult());
		}
		if( item.getConfirmResult()!= null ) {
			Integer result = item.getConfirmResult().getNumber();
			if(result.equals(3)) {
				this.testResultOther = "undetectable";
			}
			if(result.equals(0)) {
				this.recentInfectionConclusion = "recent";
			}
			if(result.equals(1)) {
				this.recentInfectionConclusion = "long-term";
			}
		}
	}
}

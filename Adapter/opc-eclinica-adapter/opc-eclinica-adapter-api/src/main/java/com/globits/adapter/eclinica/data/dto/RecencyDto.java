package com.globits.adapter.eclinica.data.dto;

import java.util.Date;

public class RecencyDto {
	private Date dateOfTestPerformance;
	private Double result;

	public Date getDateOfTestPerformance() {
		return dateOfTestPerformance;
	}

	public void setDateOfTestPerformance(Date dateOfTestPerformance) {
		this.dateOfTestPerformance = dateOfTestPerformance;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public RecencyDto() {

	}

}

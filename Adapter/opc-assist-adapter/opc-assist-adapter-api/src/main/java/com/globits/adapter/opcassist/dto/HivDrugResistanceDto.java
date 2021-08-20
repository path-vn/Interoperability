package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.LabTest;

public class HivDrugResistanceDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String drugResistanceSampleDate;
	private String drugResistanceResultDate;
	private String drugResistanceOrg;
	private String drugResistanceResult;
	private Long result;
	public String getDrugResistanceSampleDate() {
		return drugResistanceSampleDate;
	}
	public void setDrugResistanceSampleDate(String drugResistanceSampleDate) {
		this.drugResistanceSampleDate = drugResistanceSampleDate;
	}
	public String getDrugResistanceResultDate() {
		return drugResistanceResultDate;
	}
	public void setDrugResistanceResultDate(String drugResistanceResultDate) {
		this.drugResistanceResultDate = drugResistanceResultDate;
	}
	public String getDrugResistanceOrg() {
		return drugResistanceOrg;
	}
	public void setDrugResistanceOrg(String drugResistanceOrg) {
		this.drugResistanceOrg = drugResistanceOrg;
	}
	public String getDrugResistanceResult() {
		return drugResistanceResult;
	}
	public void setDrugResistanceResult(String drugResistanceResult) {
		this.drugResistanceResult = drugResistanceResult;
	}
	public Long getResult() {
		return result;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	public HivDrugResistanceDto() {
		
	}
	public HivDrugResistanceDto(LabTest item) {
		if(item.getSampleDate()!=null) {
			this.setDrugResistanceSampleDate(item.getSampleDate().toString());
		}
		if(item.getResultDate()!=null) {
			this.setDrugResistanceResultDate(item.getResultDate().toString());
		}
		this.setDrugResistanceOrg(item.getOrganization().getName());
		this.setDrugResistanceResult(item.getResultText());
		this.setResult(item.getResultNumber());
	}
}

package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class PatientRegimenDto {

	private String code;
	private String name;
	private Date startDate;
	private Date endDate;
	
	private Integer regimenLine;//1,2,3	
	private SystemCodeDto line;//1,2,3	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRegimenLine() {
		return regimenLine;
	}
	public void setRegimenLine(Integer regimenLine) {
		this.regimenLine = regimenLine;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public SystemCodeDto getLine() {
		return line;
	}
	public void setLine(SystemCodeDto line) {
		this.line = line;
	}
	
}

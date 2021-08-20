package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.Dictionary;
import com.globits.adapter.opcassist.domain.RiskInterview;

public class HivRiskFactorDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String display;
	private String definition;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public HivRiskFactorDto() {
		
	}
	public HivRiskFactorDto(RiskInterview item) {
		if(item.getRisks()!=null &&item.getRisks().size()>0) {
			for (Dictionary risk : item.getRisks()) {
				this.setCode(risk.getCode());
				this.setDisplay(risk.getValueEn());
				this.setDefinition(risk.getValueEn());
			}
		}
	}
	
}

package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.TBTreatment;

public class HivTBTreatmentDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tbDiagnoseDate;
	private String tbTreamentDateStart;
	private String tbTreamentDateEnd;
	private String tbTreamentFacility;
	public String getTbDiagnoseDate() {
		return tbDiagnoseDate;
	}
	public void setTbDiagnoseDate(String tbDiagnoseDate) {
		this.tbDiagnoseDate = tbDiagnoseDate;
	}
	public String getTbTreamentDateStart() {
		return tbTreamentDateStart;
	}
	public void setTbTreamentDateStart(String tbTreamentDateStart) {
		this.tbTreamentDateStart = tbTreamentDateStart;
	}
	public String getTbTreamentDateEnd() {
		return tbTreamentDateEnd;
	}
	public void setTbTreamentDateEnd(String tbTreamentDateEnd) {
		this.tbTreamentDateEnd = tbTreamentDateEnd;
	}
	public String getTbTreamentFacility() {
		return tbTreamentFacility;
	}
	public void setTbTreamentFacility(String tbTreamentFacility) {
		this.tbTreamentFacility = tbTreamentFacility;
	}
	public HivTBTreatmentDto() {
		
	}
	public HivTBTreatmentDto(TBTreatment item) {
		if(item.getDiagnoseDate()!=null) {
			this.setTbDiagnoseDate(item.getDiagnoseDate().toString());
		}
		if(item.getTxStartDate()!=null) {
			this.setTbTreamentDateStart(item.getTxStartDate().toString());
		}
		if(item.getTxEndDate()!=null) {
			this.setTbTreamentDateEnd(item.getTxEndDate().toString());
		}
		if(item.getFacilityName()!=null) {
			this.setTbTreamentFacility(item.getFacilityName());
		}
	}
}

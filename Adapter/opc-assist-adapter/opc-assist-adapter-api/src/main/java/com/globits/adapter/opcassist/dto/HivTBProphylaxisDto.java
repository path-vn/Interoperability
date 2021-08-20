package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.TBProphylaxis;

public class HivTBProphylaxisDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tpTreamentDateStart;
	private String tpTreamentDateEnd;
	private String tpTreamentOrg;
	public String getTpTreamentDateStart() {
		return tpTreamentDateStart;
	}
	public void setTpTreamentDateStart(String tpTreamentDateStart) {
		this.tpTreamentDateStart = tpTreamentDateStart;
	}
	public String getTpTreamentDateEnd() {
		return tpTreamentDateEnd;
	}
	public void setTpTreamentDateEnd(String tpTreamentDateEnd) {
		this.tpTreamentDateEnd = tpTreamentDateEnd;
	}
	public String getTpTreamentOrg() {
		return tpTreamentOrg;
	}
	public void setTpTreamentOrg(String tpTreamentOrg) {
		this.tpTreamentOrg = tpTreamentOrg;
	}
	public HivTBProphylaxisDto() {
		
	}
	public HivTBProphylaxisDto(TBProphylaxis item) {
		if(item.getStartDate()!=null) {
			this.setTpTreamentDateStart(item.getStartDate().toString());
		}
		if(item.getEndDate()!=null) {
			this.setTpTreamentDateEnd(item.getEndDate().toString());
		}
		if(item.getOrganization()!=null) {
			this.setTpTreamentOrg(item.getOrganization().getName());
		}
	}
}

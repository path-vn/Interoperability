package com.globits.hiv.receive.dto;

import java.util.Date;

import com.globits.hiv.receive.valueset.RegimenChangeReasonValueSet;
import com.globits.hiv.receive.valueset.RegimenLineValueSet;
import com.globits.hiv.receive.valueset.RegimenValueSet;

public class PatientRegimenDto {

	private Date dateRegimenStarted;
	private Date dateRegimenStopped;
	private SystemCodeDto name;
	private SystemCodeDto line;
	private SystemCodeDto regimenChangeReason;

	public Date getDateRegimenStarted() {
		return dateRegimenStarted;
	}
	public void setDateRegimenStarted(Date dateRegimenStarted) {
		this.dateRegimenStarted = dateRegimenStarted;
	}
	public Date getDateRegimenStopped() {
		return dateRegimenStopped;
	}
	public void setDateRegimenStopped(Date dateRegimenStopped) {
		this.dateRegimenStopped = dateRegimenStopped;
	}
	public SystemCodeDto getName() {
		return name;
	}
	public void setName(SystemCodeDto name) {
		this.name = name;
	}
	public SystemCodeDto getLine() {
		return line;
	}
	public void setLine(SystemCodeDto line) {
		this.line = line;
	}
	public SystemCodeDto getRegimenChangeReason() {
		return regimenChangeReason;
	}
	public void setRegimenChangeReason(SystemCodeDto regimenChangeReason) {
		this.regimenChangeReason = regimenChangeReason;
	}
	
}

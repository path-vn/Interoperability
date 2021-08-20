package com.globits.hiv.receive.dto;

import java.util.Date;

public class PatientDeathDto {
	private Date dateOfDeath;
	private SystemCodeDto causeOfDeath;
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public SystemCodeDto getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(SystemCodeDto causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	
	
}

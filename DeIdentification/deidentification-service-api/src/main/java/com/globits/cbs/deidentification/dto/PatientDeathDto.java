package com.globits.cbs.deidentification.dto;

import java.util.Date;

public class PatientDeathDto {
	private Date dateOfDeath;
	private String causeOfDeath;
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	
}

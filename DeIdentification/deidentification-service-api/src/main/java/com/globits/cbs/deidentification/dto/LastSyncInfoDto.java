package com.globits.cbs.deidentification.dto;

import java.util.Date;
import java.util.UUID;

public class LastSyncInfoDto {
	private UUID id;
	private Integer lastSyncNumberPatient;
	private Date lastSyncTime;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Integer getLastSyncNumberPatient() {
		return lastSyncNumberPatient;
	}
	public void setLastSyncNumberPatient(Integer lastSyncNumberPatient) {
		this.lastSyncNumberPatient = lastSyncNumberPatient;
	}
	public Date getLastSyncTime() {
		return lastSyncTime;
	}
	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	
	
}

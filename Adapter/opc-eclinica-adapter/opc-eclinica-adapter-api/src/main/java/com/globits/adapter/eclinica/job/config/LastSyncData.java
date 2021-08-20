package com.globits.adapter.eclinica.job.config;

import java.io.Serializable;
import java.util.Date;

public class LastSyncData implements Serializable {
	private Date lastSyncTime;
	private Integer lastNumberSyncPatient;

	public Date getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public Integer getLastNumberSyncPatient() {
		return lastNumberSyncPatient;
	}

	public void setLastNumberSyncPatient(Integer lastNumberSyncPatient) {
		this.lastNumberSyncPatient = lastNumberSyncPatient;
	}
}

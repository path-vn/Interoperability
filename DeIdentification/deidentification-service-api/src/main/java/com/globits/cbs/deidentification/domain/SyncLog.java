package com.globits.cbs.deidentification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_sync_log")
@XmlRootElement
public class SyncLog extends BaseObject {
	private static final long serialVersionUID = 1L;
	@Column(name="patient_id")
	private String patientId;
	@Column(name="sync_status")
	private String syncStatus;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	
}

package com.globits.adapter.eclinica.data.dto;

import java.util.Date;
import java.util.List;

public class SearchObjectDto extends SearchDto {

	private String name;
	private String identifier;
	private String patientUuid;
	private Integer patientId;
	private List<Integer> patientIds;
	private Date lastSynDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPatientUuid() {
		return patientUuid;
	}

	public void setPatientUuid(String patientUuid) {
		this.patientUuid = patientUuid;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public List<Integer> getPatientIds() {
		return patientIds;
	}

	public void setPatientIds(List<Integer> patientIds) {
		this.patientIds = patientIds;
	}

	public Date getLastSynDate() {
		return lastSynDate;
	}

	public void setLastSynDate(Date lastSynDate) {
		this.lastSynDate = lastSynDate;
	}

}

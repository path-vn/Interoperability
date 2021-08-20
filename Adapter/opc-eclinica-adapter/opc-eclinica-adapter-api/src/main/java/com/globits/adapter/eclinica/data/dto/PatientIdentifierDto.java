package com.globits.adapter.eclinica.data.dto;

import java.util.UUID;

public class PatientIdentifierDto {

	private Integer patientIdentifierId;

//	private PatientDto patient;
	private Integer patientId;

	private UUID uuid;

	private String identifier;

	private PatientIdentifierTypeDto identifierType;

	private boolean preferred;

	protected boolean voided;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getPatientIdentifierId() {
		return patientIdentifierId;
	}

	public void setPatientIdentifierId(Integer patientIdentifierId) {
		this.patientIdentifierId = patientIdentifierId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

	public boolean isVoided() {
		return voided;
	}

	public void setVoided(boolean voided) {
		this.voided = voided;
	}

//	public PatientDto getPatient() {
//		return patient;
//	}
//
//	public void setPatient(PatientDto patient) {
//		this.patient = patient;
//	}

	public PatientIdentifierTypeDto getIdentifierType() {
		return identifierType;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public void setIdentifierType(PatientIdentifierTypeDto identifierType) {
		this.identifierType = identifierType;
	}

}

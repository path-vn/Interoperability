package com.globits.adapter.eclinica.data.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_identifier")
public class PatientIdentifier {
	@Id
	@Column(name = "patient_identifier_id")
	private Integer patientIdentifierId;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "identifier")
	private String identifier;
	@ManyToOne
	@JoinColumn(name = "identifier_type")
	private PatientIdentifierType identifierType;
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@Column(name = "preferred")
	private boolean preferred;
	@Column(name = "voided")
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public PatientIdentifierType getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(PatientIdentifierType identifierType) {
		this.identifierType = identifierType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

}

package com.globits.adapter.eclinica.data.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_identifier_type")
public class PatientIdentifierType {
	@Id
	@Column(name = "patient_identifier_type_id")
	private Integer patientIdentifierTypeId;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "format")
	private String format;
	@Column(name = "check_digit")
	private boolean check_digit;

	@Column(name = "required")
	private boolean required;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getPatientIdentifierTypeId() {
		return patientIdentifierTypeId;
	}

	public void setPatientIdentifierTypeId(Integer patientIdentifierTypeId) {
		this.patientIdentifierTypeId = patientIdentifierTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isCheck_digit() {
		return check_digit;
	}

	public void setCheck_digit(boolean check_digit) {
		this.check_digit = check_digit;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

}

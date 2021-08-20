package dto;

import java.util.UUID;

public class PatientIdentifierTypeDto {

	private Integer patientIdentifierTypeId;

	private UUID uuid;

	private String name;

	private String description;

	private String format;

	private boolean check_digit;
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

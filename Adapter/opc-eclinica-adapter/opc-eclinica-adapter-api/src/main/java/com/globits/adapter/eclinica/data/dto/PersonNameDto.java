package com.globits.adapter.eclinica.data.dto;

import java.util.Date;
import java.util.UUID;

public class PersonNameDto {
	private Integer personNameId;
	private UUID uuid;
	private String givenName;
	private String middleName;
	private String familyName;
	private String displayName;
	private boolean preferred;
	private boolean voided;
//	@JsonSerialize(using = ToStringSerializer.class)
//	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private Date dateVoided;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getPersonNameId() {
		return personNameId;
	}

	public void setPersonNameId(Integer personNameId) {
		this.personNameId = personNameId;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public Date getDateVoided() {
		return dateVoided;
	}

	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}

	public PersonNameDto() {
	}

}

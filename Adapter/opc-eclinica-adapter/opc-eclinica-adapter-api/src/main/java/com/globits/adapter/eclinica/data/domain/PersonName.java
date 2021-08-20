package com.globits.adapter.eclinica.data.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsData;
import com.globits.adapter.eclinica.utils.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "Person_Name")
public class PersonName  extends BaseOpenmrsData {
	@Id
	@Column(name = "person_name_id")
	private Integer personNameId;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "given_name")
	private String givenName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "family_name")
	private String familyName;
	@Column(name = "preferred")
	private boolean preferred;
//	@Column(name = "voided")
//	private boolean voided;
//	@Convert(converter = LocalDateTimeAttributeConverter.class)
//	@Column(name = "date_voided")
//	private LocalDateTime dateVoided;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

//	public boolean isVoided() {
//		return voided;
//	}
//
//	public void setVoided(boolean voided) {
//		this.voided = voided;
//	}
//
//	public LocalDateTime getDateVoided() {
//		return dateVoided;
//	}
//
//	public void setDateVoided(LocalDateTime dateVoided) {
//		this.dateVoided = dateVoided;
//	}
}

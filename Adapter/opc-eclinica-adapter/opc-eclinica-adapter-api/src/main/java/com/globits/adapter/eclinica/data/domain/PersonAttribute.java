package com.globits.adapter.eclinica.data.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsData;

@Entity
@Table(name = "person_attribute")
public class PersonAttribute extends BaseOpenmrsData  {
	@Id
	@Column(name = "person_attribute_id")
	private Integer personAttributeId;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "value")
	private String value;
	@ManyToOne
	@JoinColumn(name = "person_attribute_type_id")
	private PersonAttributeType personAttributeType;

//	@Column(name = "voided")
//	protected boolean voided;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getPersonAttributeId() {
		return personAttributeId;
	}

	public void setPersonAttributeId(Integer personAttributeId) {
		this.personAttributeId = personAttributeId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PersonAttributeType getPersonAttributeType() {
		return personAttributeType;
	}

	public void setPersonAttributeType(PersonAttributeType personAttributeType) {
		this.personAttributeType = personAttributeType;
	}

}

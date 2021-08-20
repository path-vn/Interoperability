package com.globits.adapter.eclinica.data.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsMetadata;

@Entity
@Table(name = "person_attribute_type")
public class PersonAttributeType extends BaseOpenmrsMetadata{
	@Id
	@Column(name = "person_attribute_type_id")
	private Integer personAttributeTypeId;
	@Column(name = "uuid")
	private UUID uuid;
//	@Column(name = "name")
//	private String name;
//	@Column(name = "description")
//	private String description;
	@Column(name = "format")
	private String format;
	@Column(name = "foreign_key")
	private Integer foreign_key;

	@Column(name = "searchable")
	private boolean searchable;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getPersonAttributeTypeId() {
		return personAttributeTypeId;
	}

	public void setPersonAttributeTypeId(Integer personAttributeTypeId) {
		this.personAttributeTypeId = personAttributeTypeId;
	}

	public Integer getForeign_key() {
		return foreign_key;
	}

	public void setForeign_key(Integer foreign_key) {
		this.foreign_key = foreign_key;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

}

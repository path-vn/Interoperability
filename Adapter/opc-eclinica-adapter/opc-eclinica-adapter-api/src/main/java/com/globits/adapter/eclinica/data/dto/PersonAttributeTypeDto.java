package com.globits.adapter.eclinica.data.dto;

import java.util.UUID;

public class PersonAttributeTypeDto {
	private Integer personAttributeTypeId;
	private UUID uuid;
	private String name;
	private String description;
	private String format;
	private Integer foreign_key;
	private boolean searchable;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getPersonAttributeTypeId() {
		return personAttributeTypeId;
	}

	public void setPersonAttributeTypeId(Integer personAttributeTypeId) {
		this.personAttributeTypeId = personAttributeTypeId;
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

	public PersonAttributeTypeDto() {
	}

}

package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "concept_class")
public class ConceptClass {
	@Id
	@Column(name = "concept_class_id")
	private Integer conceptClassId;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "retired")
	private boolean retired;
	@Column(name = "date_retired")
	private Date dateRetired;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}


	public Integer getConceptClassId() {
		return conceptClassId;
	}

	public void setConceptClassId(Integer conceptClassId) {
		this.conceptClassId = conceptClassId;
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

	public boolean isRetired() {
		return retired;
	}

	public void setRetired(boolean retired) {
		this.retired = retired;
	}

	public Date getDateRetired() {
		return dateRetired;
	}

	public void setDateRetired(Date dateRetired) {
		this.dateRetired = dateRetired;
	}

}

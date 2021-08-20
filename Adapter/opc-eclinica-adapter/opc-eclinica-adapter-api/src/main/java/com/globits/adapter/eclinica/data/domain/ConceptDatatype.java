package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "concept_datatype")
public class ConceptDatatype {
	@Id
	@Column(name = "concept_datatype_id")
	private Integer conceptDatatypeId;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "name")
	private String name;
	@Column(name = "hl7_abbreviation")
	private String hl7Abbreviation;
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

	public Integer getConceptDatatypeId() {
		return conceptDatatypeId;
	}

	public void setConceptDatatypeId(Integer conceptDatatypeId) {
		this.conceptDatatypeId = conceptDatatypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHl7Abbreviation() {
		return hl7Abbreviation;
	}

	public void setHl7Abbreviation(String hl7Abbreviation) {
		this.hl7Abbreviation = hl7Abbreviation;
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

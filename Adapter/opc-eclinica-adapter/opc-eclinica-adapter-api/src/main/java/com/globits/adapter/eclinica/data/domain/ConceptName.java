package com.globits.adapter.eclinica.data.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "concept_name")
public class ConceptName {
	@Id
	@Column(name = "concept_name_id")
	private Integer conceptNameId;

	@Column(name = "name")
	private String name;
	@Column(name = "locale")
	private String locale;
	@ManyToOne
	@JoinColumn(name = "concept_id")
	private Concept concept;
	private Boolean voided = false;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "concept_name_type")
	private String conceptNameType;
	@Column(name = "locale_preferred")
	private Boolean localePreferred = false;

	public Integer getConceptNameId() {
		return conceptNameId;
	}

	public void setConceptNameId(Integer conceptNameId) {
		this.conceptNameId = conceptNameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public String getConceptNameType() {
		return conceptNameType;
	}

	public void setConceptNameType(String conceptNameType) {
		this.conceptNameType = conceptNameType;
	}

	public Boolean getLocalePreferred() {
		return localePreferred;
	}

	public void setLocalePreferred(Boolean localePreferred) {
		this.localePreferred = localePreferred;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}

package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "concept")
public class Concept {
	@Id
	@Column(name = "concept_id")
	private Integer conceptId;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "short_name")
	private String shortName;
	@Column(name = "description")
	private String description;
	@Column(name = "form_text")
	private String formText;
	@ManyToOne
	@JoinColumn(name = "datatype_id")
	private ConceptDatatype datatype;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ConceptClass conceptClass;
	@Column(name = "retired")
	private boolean retired;
	@Column(name = "date_retired")
	private Date dateRetired;
//	@Column(name = "set")
//	private Boolean set = false;
	@Column(name = "version")
	private String version;

	@OneToMany(mappedBy = "concept", cascade = CascadeType.ALL)
	protected Set<ConceptName> names;
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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

	public Integer getConceptId() {
		return conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFormText() {
		return formText;
	}

	public void setFormText(String formText) {
		this.formText = formText;
	}

	public ConceptDatatype getDatatype() {
		return datatype;
	}

	public void setDatatype(ConceptDatatype datatype) {
		this.datatype = datatype;
	}

	public ConceptClass getConceptClass() {
		return conceptClass;
	}

	public void setConceptClass(ConceptClass conceptClass) {
		this.conceptClass = conceptClass;
	}

//	public Boolean getSet() {
//		return set;
//	}
//
//	public void setSet(Boolean set) {
//		this.set = set;
//	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Set<ConceptName> getNames() {
		return names;
	}

	public void setNames(Set<ConceptName> names) {
		this.names = names;
	}
	
}

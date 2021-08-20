package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsData;

@Entity
@Table(name = "obs")
public class Obs  extends BaseOpenmrsData{
	@Id
	@Column(name = "obs_id")
	private Integer obsId;
	@Column(name = "obs_datetime", nullable = false, length = 19)
	private Date obsDatetime;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;

	@ManyToOne
	@JoinColumn(name = "concept_id")
	private Concept concept;
	@Column(name = "accession_number")
	private String accessionNumber;

//	@ManyToOne
//	@JoinColumn(name = "obs_group_id")
//	private Obs obsGroup;

	@ManyToOne
	@JoinColumn(name = "value_coded")
	private Concept valueCoded;

	@ManyToOne
	@JoinColumn(name = "value_coded_name_id")
	private ConceptName valueCodedName;

//	@ManyToOne
//	@JoinColumn(name = "value_drug")
//	private Drug valueDrug;
	@Column(name = "value_group_id")
	private Integer valueGroupId;
	@Column(name = "value_boolean")
	private Boolean valueBoolean;
	@Column(name = "value_datetime")
	private Date valueDatetime;
	@Column(name = "value_numeric")
	private Double valueNumeric;
	@Column(name = "value_modifier")
	private String valueModifier;
	@Column(name = "value_text")
	private String valueText;
	@Column(name = "value_complex")
	private String valueComplex;
	@Column(name = "comments")
	private String comments;
//
//	@ManyToOne
//	@JoinColumn(name = "previous_version")
//	private Obs previousVersion;
	@Column(name = "uuid")
	private UUID uuid;

//	@Column(name = "voided")
//	private boolean voided;

	public Integer getObsId() {
		return obsId;
	}

	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}

	public Date getObsDatetime() {
		return obsDatetime;
	}

	public void setObsDatetime(Date obsDatetime) {
		this.obsDatetime = obsDatetime;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

//	public Obs getObsGroup() {
//		return obsGroup;
//	}
//
//	public void setObsGroup(Obs obsGroup) {
//		this.obsGroup = obsGroup;
//	}

	public Concept getValueCoded() {
		return valueCoded;
	}

	public void setValueCoded(Concept valueCoded) {
		this.valueCoded = valueCoded;
	}

	public ConceptName getValueCodedName() {
		return valueCodedName;
	}

	public void setValueCodedName(ConceptName valueCodedName) {
		this.valueCodedName = valueCodedName;
	}

	public Integer getValueGroupId() {
		return valueGroupId;
	}

	public void setValueGroupId(Integer valueGroupId) {
		this.valueGroupId = valueGroupId;
	}

	public Boolean getValueBoolean() {
		return valueBoolean;
	}

	public void setValueBoolean(Boolean valueBoolean) {
		this.valueBoolean = valueBoolean;
	}

	public Date getValueDatetime() {
		return valueDatetime;
	}

	public void setValueDatetime(Date valueDatetime) {
		this.valueDatetime = valueDatetime;
	}

	public Double getValueNumeric() {
		return valueNumeric;
	}

	public void setValueNumeric(Double valueNumeric) {
		this.valueNumeric = valueNumeric;
	}

	public String getValueModifier() {
		return valueModifier;
	}

	public void setValueModifier(String valueModifier) {
		this.valueModifier = valueModifier;
	}

	public String getValueText() {
		return valueText;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}

	public String getValueComplex() {
		return valueComplex;
	}

	public void setValueComplex(String valueComplex) {
		this.valueComplex = valueComplex;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

//	public Obs getPreviousVersion() {
//		return previousVersion;
//	}
//
//	public void setPreviousVersion(Obs previousVersion) {
//		this.previousVersion = previousVersion;
//	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

//	public boolean isVoided() {
//		return voided;
//	}
//
//	public void setVoided(boolean voided) {
//		this.voided = voided;
//	}
}

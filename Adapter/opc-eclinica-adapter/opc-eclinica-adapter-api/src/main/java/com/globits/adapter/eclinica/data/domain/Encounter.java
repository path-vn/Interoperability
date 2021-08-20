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
@Table(name = "encounter")
public class Encounter extends BaseOpenmrsData {
	@Id
	@Column(name = "encounter_id")
	private Integer encounterId;
	@Column(name = "encounter_datetime", nullable = false, length = 19)
	private Date encounterDatetime;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
//	@ManyToOne
//	@JoinColumn(name = "form_id")
//	private Form form;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "encounter_type")
	private EncounterType encounterType;
	
	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;
	
	@Column(name = "uuid")
	private UUID uuid;
	
//	@Column(name = "voided")
//	private boolean voided;

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Date getEncounterDatetime() {
		return encounterDatetime;
	}

	public void setEncounterDatetime(Date encounterDatetime) {
		this.encounterDatetime = encounterDatetime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public EncounterType getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(EncounterType encounterType) {
		this.encounterType = encounterType;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

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

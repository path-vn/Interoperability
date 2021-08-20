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
import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsMetadata;

@Entity
@Table(name = "visit")
public class Visit extends BaseOpenmrsData {
	@Id
	@Column(name = "visit_id")
	private Integer visitId;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "visit_type_id")
	private VisitType visitType;
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
//	@ManyToOne
//	@JoinColumn(name = "indication_concept_id")
//	private Concept indication;
	@Column(name = "uuid")
	private UUID uuid;
	@Column(name = "date_started", nullable = false, length = 19)
	private Date startDatetime;

	@Column(name = "date_stopped", length = 19)
	private Date stopDatetime;
//	@Column(name = "voided")
//	private boolean voided;

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getStopDatetime() {
		return stopDatetime;
	}

	public void setStopDatetime(Date stopDatetime) {
		this.stopDatetime = stopDatetime;
	}

//	public boolean isVoided() {
//		return voided;
//	}
//
//	public void setVoided(boolean voided) {
//		this.voided = voided;
//	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}

package com.globits.adapter.opcassist.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.opcassist.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_treatment")
public class Treatment {
	@Transient
	private static final long serialVersionUID = -370779018661555361L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@ManyToOne
	@JoinColumn(name = "case_id", nullable = false)
	private Case theCase;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = false)
	private Organization organization;

	@OneToOne
	@JoinColumn(name = "regimen_id", nullable = true)
	private Regimen regimen;

	@Column(name = "regimen_name", nullable = false)
	private String regimenName;

	@Column(name = "regimen_line", nullable = false)
	private int regimenLine;

	@ManyToOne
	@JoinColumn(name = "disease_id", nullable = false)
	private Dictionary disease;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "end_date", nullable = true)
	private LocalDateTime endDate;

	@Column(name = "reason_for_ending", length = 200, nullable = true)
	private String endingReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Case getTheCase() {
		return theCase;
	}

	public void setTheCase(Case theCase) {
		this.theCase = theCase;
	}

	public Regimen getRegimen() {
		return regimen;
	}

	public void setRegimen(Regimen regimen) {
		this.regimen = regimen;
	}

	public String getRegimenName() {
		return regimenName;
	}

	public void setRegimenName(String regimenName) {
		this.regimenName = regimenName;
	}

	public int getRegimenLine() {
		return regimenLine;
	}

	public void setRegimenLine(int regimenLine) {
		this.regimenLine = regimenLine;
	}

	public Dictionary getDisease() {
		return disease;
	}

	public void setDisease(Dictionary disease) {
		this.disease = disease;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getEndingReason() {
		return endingReason;
	}

	public void setEndingReason(String endingReason) {
		this.endingReason = endingReason;
	}
}

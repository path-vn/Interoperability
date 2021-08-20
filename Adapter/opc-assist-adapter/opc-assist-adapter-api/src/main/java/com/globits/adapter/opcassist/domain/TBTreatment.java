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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.opcassist.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_tb_treatment")
public class TBTreatment {
	@Transient
	private static final long serialVersionUID = 1504328285092916666L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "test_date", nullable = false)
	private LocalDateTime testDate;

	@Column(name = "lab_name", length = 250, nullable = false)
	private String labName;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "diagnose_date", nullable = false)
	private LocalDateTime diagnoseDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "tx_start_date", nullable = true)
	private LocalDateTime txStartDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "tx_end_date", nullable = true)
	private LocalDateTime txEndDate;

	@Column(name = "facility_name", length = 250, nullable = true)
	private String facilityName;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = false)
	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "case_id", nullable = false)
	private Case theCase;

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

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public LocalDateTime getDiagnoseDate() {
		return diagnoseDate;
	}

	public void setDiagnoseDate(LocalDateTime diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
	}

	public LocalDateTime getTxStartDate() {
		return txStartDate;
	}

	public void setTxStartDate(LocalDateTime txStartDate) {
		this.txStartDate = txStartDate;
	}

	public LocalDateTime getTxEndDate() {
		return txEndDate;
	}

	public void setTxEndDate(LocalDateTime txEndDate) {
		this.txEndDate = txEndDate;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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
}

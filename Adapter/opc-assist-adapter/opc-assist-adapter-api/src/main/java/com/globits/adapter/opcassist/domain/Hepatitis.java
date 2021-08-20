package com.globits.adapter.opcassist.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.types.ClinicalTestingType;
import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.opcassist.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_hepatitis")
public class Hepatitis {
	@Transient
	private static final long serialVersionUID = 2855942465280692077L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = false)
	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "case_id", nullable = false)
	private Case theCase;

	@Column(name = "test_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ClinicalTestingType testType;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "test_date", nullable = true)
	private LocalDateTime testDate;

	@Column(name = "test_positive", nullable = false)
	private boolean testPositive;

	@Column(name = "on_treatment", nullable = false)
	private boolean onTreatment;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "tx_start_date", nullable = true)
	private LocalDateTime txStartDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "tx_end_date", nullable = true)
	private LocalDateTime txEndDate;

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

	public ClinicalTestingType getTestType() {
		return testType;
	}

	public void setTestType(ClinicalTestingType testType) {
		this.testType = testType;
	}

	public LocalDateTime getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}

	public boolean isTestPositive() {
		return testPositive;
	}

	public void setTestPositive(boolean testPositive) {
		this.testPositive = testPositive;
	}

	public boolean isOnTreatment() {
		return onTreatment;
	}

	public void setOnTreatment(boolean onTreatment) {
		this.onTreatment = onTreatment;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(uid).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Hepatitis)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		Hepatitis that = (Hepatitis) obj;

		return new EqualsBuilder().append(id, that.id).append(uid, that.uid).isEquals();
	}
}

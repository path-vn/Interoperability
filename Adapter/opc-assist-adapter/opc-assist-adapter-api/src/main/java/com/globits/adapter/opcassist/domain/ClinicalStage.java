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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.opcassist.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_clinical_stage")
public class ClinicalStage {
	@Transient
	private static final long serialVersionUID = 4413187982228579395L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false, updatable = false)
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

	@Column(name = "stage", nullable = false)
	private int stage;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "eval_date", nullable = false)
	private LocalDateTime evalDate;

	@Lob
	@Column(name = "note", nullable = true)
	private String note;

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

	public Case getTheCase() {
		return theCase;
	}

	public void setTheCase(Case theCase) {
		this.theCase = theCase;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public LocalDateTime getEvalDate() {
		return evalDate;
	}

	public void setEvalDate(LocalDateTime evalDate) {
		this.evalDate = evalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

		if (!(obj instanceof ClinicalStage)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		ClinicalStage that = (ClinicalStage) obj;

		return new EqualsBuilder().append(id, that.id).append(uid, that.uid).isEquals();
	}
}

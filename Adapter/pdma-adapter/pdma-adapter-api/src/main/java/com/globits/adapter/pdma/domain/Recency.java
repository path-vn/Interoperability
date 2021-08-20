package com.globits.adapter.pdma.domain;

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

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.RecencyResult;
import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.pdma.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_recency")
public class Recency {
	@Transient
	private static final long serialVersionUID = 6325703046961803386L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "screen_sample_date", nullable = false)
	private LocalDateTime screenSampleDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "screen_test_date", nullable = false)
	private LocalDateTime screenTestDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "screen_result", nullable = false)
	private RecencyResult screenResult;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "vl_test_date", nullable = true)
	private LocalDateTime vlTestDate;

	@Column(name = "vl_result", length = 250, nullable = true)
	private String vlResult;

	@Enumerated(EnumType.STRING)
	@Column(name = "confirm_result", nullable = true)
	private RecencyResult confirmResult;

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

	public LocalDateTime getScreenSampleDate() {
		return screenSampleDate;
	}

	public void setScreenSampleDate(LocalDateTime screenSampleDate) {
		this.screenSampleDate = screenSampleDate;
	}

	public LocalDateTime getScreenTestDate() {
		return screenTestDate;
	}

	public void setScreenTestDate(LocalDateTime screenTestDate) {
		this.screenTestDate = screenTestDate;
	}

	public RecencyResult getScreenResult() {
		return screenResult;
	}

	public void setScreenResult(RecencyResult screenResult) {
		this.screenResult = screenResult;
	}

	public LocalDateTime getVlTestDate() {
		return vlTestDate;
	}

	public void setVlTestDate(LocalDateTime vlTestDate) {
		this.vlTestDate = vlTestDate;
	}

	public String getVlResult() {
		return vlResult;
	}

	public void setVlResult(String vlResult) {
		this.vlResult = vlResult;
	}

	public RecencyResult getConfirmResult() {
		return confirmResult;
	}

	public void setConfirmResult(RecencyResult confirmResult) {
		this.confirmResult = confirmResult;
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

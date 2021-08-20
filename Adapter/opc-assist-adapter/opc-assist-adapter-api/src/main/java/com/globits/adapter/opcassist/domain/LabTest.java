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

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.types.ClinicalTestingType;
import com.globits.adapter.opcassist.types.LabTestFundingSource;
import com.globits.adapter.opcassist.types.LabTestReason;
import com.globits.adapter.opcassist.types.SimpleClinicalTestResult;
import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.opcassist.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_lab_test")
public class LabTest  extends BaseObject {
	@Transient
	private static final long serialVersionUID = 9134116285867959547L;

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

	@Column(name = "test_type", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ClinicalTestingType testType;

	@Column(name = "reason_for_testing", nullable = true)
	@Enumerated(EnumType.STRING)
	private LabTestReason reasonForTesting;

	@Column(name = "funding_source", nullable = true)
	@Enumerated(EnumType.STRING)
	private LabTestFundingSource fundingSource;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "sample_date", nullable = false)
	private LocalDateTime sampleDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "result_date", nullable = true)
	private LocalDateTime resultDate;

	@Column(name = "number_result", nullable = true)
	private Long resultNumber;

	@Column(name = "text_result", length = 1024, nullable = true)
	private String resultText;

	@Deprecated
	@Column(name = "enum_result", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private SimpleClinicalTestResult resultEnum;

	// Tên cơ sở lấy mẫu
	@Column(name = "sample_site", length = 200, nullable = true)
	private String sampleSite;

	@Column(name = "lab_name", length = 200, nullable = true)
	private String labName;

	@Column(name = "need_consultation", nullable = true)
	private Boolean needConsultation;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "consultation_1", nullable = true)
	private LocalDateTime consultation1;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "consultation_2", nullable = true)
	private LocalDateTime consultation2;

	@Column(name = "note", length = 1024, nullable = true)
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

	public LabTestReason getReasonForTesting() {
		return reasonForTesting;
	}

	public void setReasonForTesting(LabTestReason reasonForTesting) {
		this.reasonForTesting = reasonForTesting;
	}

	public LabTestFundingSource getFundingSource() {
		return fundingSource;
	}

	public void setFundingSource(LabTestFundingSource fundingSource) {
		this.fundingSource = fundingSource;
	}

	public LocalDateTime getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(LocalDateTime sampleDate) {
		this.sampleDate = sampleDate;
	}

	public LocalDateTime getResultDate() {
		return resultDate;
	}

	public void setResultDate(LocalDateTime resultDate) {
		this.resultDate = resultDate;
	}

	public ClinicalTestingType getTestType() {
		return testType;
	}

	public void setTestType(ClinicalTestingType testType) {
		this.testType = testType;
	}

	public Long getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(Long resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public SimpleClinicalTestResult getResultEnum() {
		return resultEnum;
	}

	public void setResultEnum(SimpleClinicalTestResult resultEnum) {
		this.resultEnum = resultEnum;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getSampleSite() {
		return sampleSite;
	}

	public void setSampleSite(String sampleSite) {
		this.sampleSite = sampleSite;
	}

	public Boolean getNeedConsultation() {
		return needConsultation;
	}

	public void setNeedConsultation(Boolean needConsultation) {
		this.needConsultation = needConsultation;
	}

	public LocalDateTime getConsultation1() {
		return consultation1;
	}

	public void setConsultation1(LocalDateTime consultation1) {
		this.consultation1 = consultation1;
	}

	public LocalDateTime getConsultation2() {
		return consultation2;
	}

	public void setConsultation2(LocalDateTime consultation2) {
		this.consultation2 = consultation2;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}

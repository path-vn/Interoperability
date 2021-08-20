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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.EnrollmentType;
import com.globits.adapter.pdma.types.PatientStatus;
import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.pdma.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_case_org")
public class CaseOrg {
	@Transient
	private static final long serialVersionUID = -8194285583007564350L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@ManyToOne
	@JoinColumn(name = "case_id", referencedColumnName = "id", nullable = false)
	private Case theCase;

	@ManyToOne
	@JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
	private Organization organization;

	@Column(name = "org_name", length = 512, nullable = true)
	private String organizationName;

	@Column(name = "patient_chart_id", length = 100, nullable = true)
	private String patientChartId;

	@Enumerated(EnumType.STRING)
	@Column(name = "prev_status", nullable = false)
	private PatientStatus prevStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private PatientStatus status;

	/**
	 * If there are multiple relationships between one patient and one facility,
	 * there should be one with the latest relationship set to TRUE
	 */
	@Column(name = "is_latest_rel", nullable = false)
	private boolean latestRelationship;

	@Enumerated(EnumType.STRING)
	@Column(name = "enrollment_type", nullable = false)
	private EnrollmentType enrollmentType;

	@Column(name = "arv_group", length = 255, nullable = true)
	private String arvGroup;

	/**
	 * ARV start date at current OPC
	 */
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "arv_start_date", nullable = true)
	private LocalDateTime arvStartDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "end_date", nullable = true)
	private LocalDateTime endDate;

	@Column(name = "reason_for_ending", length = 200, nullable = true)
	private String endingReason;

	/**
	 * TRUE if the patient is currently managed by this organization
	 */
	@Column(name = "current", nullable = false)
	private Boolean current;

	/**
	 * For referral tracking only. By default this is set to FALSE. Only when a
	 * sending facility mark the referral result as successful by themselves, this
	 * is set to TRUE. The receiving facility in that case will not count this
	 * record.
	 */
	@Column(name = "ref_tracking_only", nullable = false)
	private boolean refTrackingOnly;

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

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPatientChartId() {
		return patientChartId;
	}

	public void setPatientChartId(String patientChartId) {
		this.patientChartId = patientChartId;
	}

	public LocalDateTime getArvStartDate() {
		return arvStartDate;
	}

	public void setArvStartDate(LocalDateTime arvStartDate) {
		this.arvStartDate = arvStartDate;
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

	public PatientStatus getPrevStatus() {
		return prevStatus;
	}

	public void setPrevStatus(PatientStatus prevStatus) {
		this.prevStatus = prevStatus;
	}

	public PatientStatus getStatus() {
		return status;
	}

	public void setStatus(PatientStatus status) {
		this.status = status;
	}

	public boolean isLatestRelationship() {
		return latestRelationship;
	}

	public void setLatestRelationship(boolean latestRelationship) {
		this.latestRelationship = latestRelationship;
	}

	public EnrollmentType getEnrollmentType() {
		return enrollmentType;
	}

	public void setEnrollmentType(EnrollmentType enrollmentType) {
		this.enrollmentType = enrollmentType;
	}

	public String getArvGroup() {
		return arvGroup;
	}

	public void setArvGroup(String arvGroup) {
		this.arvGroup = arvGroup;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public boolean isRefTrackingOnly() {
		return refTrackingOnly;
	}

	public void setRefTrackingOnly(boolean refTrackingOnly) {
		this.refTrackingOnly = refTrackingOnly;
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

		if (!(obj instanceof CaseOrg)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		CaseOrg that = (CaseOrg) obj;

		return new EqualsBuilder().append(id, that.id).append(uid, that.uid).isEquals();
	}
}

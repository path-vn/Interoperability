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

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.types.PregnancyResult;
import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;
import com.globits.adapter.opcassist.utils.UUIDAttributeConverter;

@Entity
@Table(name = "tbl_pregnancy")
public class Pregnancy {
	@Transient
	private static final long serialVersionUID = 5751358222352054012L;
	
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "modify_date", nullable = true)
	private LocalDateTime modifyDate;
	
	@Convert(converter = UUIDAttributeConverter.class)
	@Column(name = "uuid", unique = true, nullable = false, updatable = false, columnDefinition = "char(36)")
	private UUID uid;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = false)
	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "case_id", nullable = false)
	private Case theCase;

	@Column(name = "is_pregnant", nullable = false)
	private boolean pregnant;

	@Column(name = "attended_anc", nullable = false)
	private boolean attendedAnc;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "last_menstrual_period", nullable = true)
	private LocalDateTime lastMenstrualPeriod;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "due_date", nullable = true)
	private LocalDateTime dueDate;

	@Column(name = "preg_result", nullable = false)
	private PregnancyResult pregResult;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "child_dob", nullable = true)
	private LocalDateTime childDob;

	/**
	 * Cân nặng của con lúc sinh
	 */
	@Column(name = "birth_weight", nullable = true)
	private double birthWeight;

	/**
	 * 1 = Yes 2 = No 3 = Don't know
	 */
	@Column(name = "child_prophylaxis", nullable = true)
	private Integer childProphylaxis;

	/**
	 * Diagnosed with HIV?
	 * 
	 * 1 = Yes 2 = No 3 = Don't know
	 */
	@Column(name = "child_hiv_status", nullable = true)
	private int childHIVStatus;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "child_diag_date", nullable = true)
	private LocalDateTime childDiagnosedDate;

	/**
	 * Con đã được đưa vào điều trị ARV chưa?
	 * 
	 */
	@Column(name = "child_on_art", nullable = true)
	private boolean childInitiatedOnART;

	/**
	 * Name of facility providing Tx for child
	 */
	@Column(name = "child_opc", length = 250, nullable = true)
	private String childOpc;

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

	public boolean isPregnant() {
		return pregnant;
	}

	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}

	public LocalDateTime getLastMenstrualPeriod() {
		return lastMenstrualPeriod;
	}

	public void setLastMenstrualPeriod(LocalDateTime lastMenstrualPeriod) {
		this.lastMenstrualPeriod = lastMenstrualPeriod;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getChildDob() {
		return childDob;
	}

	public void setChildDob(LocalDateTime childDob) {
		this.childDob = childDob;
	}

	public double getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(double birthWeight) {
		this.birthWeight = birthWeight;
	}

	public Integer getChildProphylaxis() {
		return childProphylaxis;
	}

	public void setChildProphylaxis(Integer childProphylaxis) {
		this.childProphylaxis = childProphylaxis;
	}

	public boolean isAttendedAnc() {
		return attendedAnc;
	}

	public void setAttendedAnc(boolean attendedAnc) {
		this.attendedAnc = attendedAnc;
	}

	public PregnancyResult getPregResult() {
		return pregResult;
	}

	public void setPregResult(PregnancyResult pregResult) {
		this.pregResult = pregResult;
	}

	public int getChildHIVStatus() {
		return childHIVStatus;
	}

	public void setChildHIVStatus(int childHIVStatus) {
		this.childHIVStatus = childHIVStatus;
	}

	public LocalDateTime getChildDiagnosedDate() {
		return childDiagnosedDate;
	}

	public void setChildDiagnosedDate(LocalDateTime childDiagnosedDate) {
		this.childDiagnosedDate = childDiagnosedDate;
	}

	public boolean isChildInitiatedOnART() {
		return childInitiatedOnART;
	}

	public void setChildInitiatedOnART(boolean childInitiatedOnART) {
		this.childInitiatedOnART = childInitiatedOnART;
	}

	public String getChildOpc() {
		return childOpc;
	}

	public void setChildOpc(String childOpc) {
		this.childOpc = childOpc;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

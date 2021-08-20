package com.globits.hiv.receive.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tbl_hiv_case_report")
public class HivCaseReport {
	@Id
	@Type(type = "uuid-char")
	@Column(name = "id", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private UUID id;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Column(name = "modify_date", nullable = true)
	private Date modifyDate;

	@Column(name = "modified_by", length = 100, nullable = true)
	private String modifiedBy;
	
	@Column(name = "voided",  nullable = true)
	private Boolean voided;
	
	@Column(name = "content",  nullable = true,columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "title",  nullable = true)
	private String title;
	
	@Column(name = "instructions",  nullable = true)
	private String instructions;
	
	@Column(name = "report_date",  nullable = true)
	private Date reportDate;
	
	@Column(name = "facility_id",  nullable = true)
	private String facilityId;
	
	@Column(name = "system_id",  nullable = true)
	private String systemId;
	
	public HivCaseReport() {
		this.id = UUID.randomUUID();
	}
	
	public HivCaseReport(UUID id, Date createDate, String createdBy, Date modifyDate, String modifiedBy, Boolean voided,
			String content, String title, String instructions, Date reportDate, String facilityId, String systemId) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
		this.voided = voided;
		this.content = content;
		this.title = title;
		this.instructions = instructions;
		this.reportDate = reportDate;
		this.facilityId = facilityId;
		this.systemId = systemId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
}

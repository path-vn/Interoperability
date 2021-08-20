package com.globits.adapter.eclinica.data.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.globits.adapter.eclinica.data.domain.auditing.BaseOpenmrsMetadata;

@Entity
@Table(name = "visit_type")
public class VisitType extends BaseOpenmrsMetadata {
	@Id
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	@Column(name = "uuid")
	private UUID uuid;
//	@Column(name = "name")
//	private String name;
//	@Column(name = "description")
//	private String description;
//	@Column(name = "retired")
//	private boolean retired;
//	@Column(name = "date_retired")
//	private Date dateRetired;

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public boolean isRetired() {
//		return retired;
//	}
//
//	public void setRetired(boolean retired) {
//		this.retired = retired;
//	}
//
//	public Date getDateRetired() {
//		return dateRetired;
//	}
//
//	public void setDateRetired(Date dateRetired) {
//		this.dateRetired = dateRetired;
//	}

}

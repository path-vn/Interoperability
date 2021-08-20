package com.globits.adapter.eclinica.data.domain.auditing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public  class BaseOpenmrsData {

	// ***** Properties *****
//	@Column(name = "creator")
//	protected User creator;
	@Column(name = "date_created")
	protected Date dateCreated;
//	@Column(name = "changed_by")
//	protected User changedBy;
//	@Column(name = "date_changed")
//	protected Date dateChanged;
	@Column(name = "voided")
	protected Boolean voided = Boolean.FALSE;
	@Column(name = "date_voided")
	protected Date dateVoided;
//	@Column(name = "voided_by")
//	protected User voidedBy;
	@Column(name = "void_reason")
	protected String voidReason;

//	public User getCreator() {
//		return creator;
//	}
//
//	public void setCreator(User creator) {
//		this.creator = creator;
//	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

//	public User getChangedBy() {
//		return changedBy;
//	}
//
//	public void setChangedBy(User changedBy) {
//		this.changedBy = changedBy;
//	}

//	public Date getDateChanged() {
//		return dateChanged;
//	}
//
//	public void setDateChanged(Date dateChanged) {
//		this.dateChanged = dateChanged;
//	}

	public Boolean getVoided() {
		return voided;
	}

	public void setVoided(Boolean voided) {
		this.voided = voided;
	}

	public Date getDateVoided() {
		return dateVoided;
	}

	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}

//	public User getVoidedBy() {
//		return voidedBy;
//	}
//
//	public void setVoidedBy(User voidedBy) {
//		this.voidedBy = voidedBy;
//	}

	public String getVoidReason() {
		return voidReason;
	}

	public void setVoidReason(String voidReason) {
		this.voidReason = voidReason;
	}

	public BaseOpenmrsData() {
	}

}

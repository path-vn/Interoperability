package com.globits.cbs.deidentification.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_last_sync_info")
@XmlRootElement
public class LastSyncInfo extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="last_sync_time")
	private Date lastSyncTime;
	@Column(name="last_sync_number_patient")
	private Integer lastSyncNumberPatient;
	
	@Column(name="current_page")
	private Integer currentPage;
	@Column(name="from_date")
	private Date fromDate;
	@Column(name="to_date")
	private Date toDate;
	
	@Column(name="total")
	private Integer total;

	public Date getLastSyncTime() {
		return lastSyncTime;
	}
	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	public Integer getLastSyncNumberPatient() {
		return lastSyncNumberPatient;
	}
	public void setLastSyncNumberPatient(Integer lastSyncNumberPatient) {
		this.lastSyncNumberPatient = lastSyncNumberPatient;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public LastSyncInfo() {
		this.setId(UUID.randomUUID());
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}

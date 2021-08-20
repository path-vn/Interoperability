package com.globits.adapter.eclinica.data.dto;

import java.io.Serializable;
import java.util.Date;

public class SerializeObjectDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateGetData;
	private Date timeStartJob;
	private long totalRecord;
	private Boolean status;
	public Date getDateGetData() {
		return dateGetData;
	}
	public void setDateGetData(Date dateGetData) {
		this.dateGetData = dateGetData;
	}
	public Date getTimeStartJob() {
		return timeStartJob;
	}
	public void setTimeStartJob(Date timeStartJob) {
		this.timeStartJob = timeStartJob;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
	
}

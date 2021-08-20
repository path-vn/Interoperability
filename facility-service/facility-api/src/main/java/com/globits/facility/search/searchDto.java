package com.globits.facility.search;

import java.util.Date;
import java.util.UUID;

public class searchDto {
	private UUID id;
	private String checkCode;
	private String text;
	private Date fromDate;
	private Date toDate;
	private Date startDate;
	private Date endDate;
	private Integer pageIndex;
	private Integer pageSize;
	private Integer type;
	private UUID administrativeUnitId;
	private UUID healthOrgId;
	private Integer level;
	private String systemType;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public UUID getAdministrativeUnitId() {
		return administrativeUnitId;
	}
	public void setAdministrativeUnitId(UUID administrativeUnitId) {
		this.administrativeUnitId = administrativeUnitId;
	}
	
	public UUID getHealthOrgId() {
		return healthOrgId;
	}
	public void setHealthOrgId(UUID healthOrgId) {
		this.healthOrgId = healthOrgId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public searchDto() {
		super();
	}

}

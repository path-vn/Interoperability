package com.globits.adapter.opcassist.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.globits.adapter.opcassist.utils.LocalDateTimeAttributeConverter;

@MappedSuperclass
public class BaseObject implements Serializable{
	@Transient
	private static final long serialVersionUID = 4559994432567537044L;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "created_by", length = 100, nullable = false)
	private String createdBy;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "modify_date", nullable = true)
	private LocalDateTime modifyDate;

	@Column(name = "modified_by", length = 100, nullable = true)
	private String modifiedBy;

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

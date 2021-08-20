package com.globits.adapter.opcassist.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer;

public class BaseObjectDto {
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createDate;

	private String createdBy;

	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime modifyDate;

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
	
}

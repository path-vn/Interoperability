package com.globits.cbs.deidentification.dto;

import com.globits.cbs.deidentification.domain.DeIdentificationConfig;
import com.globits.core.dto.BaseObjectDto;

public class DeIdentificationConfigDto extends BaseObjectDto{
	private String name;
	private String code;
	private Boolean isUsed;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public DeIdentificationConfigDto() {

	}
	public DeIdentificationConfigDto(DeIdentificationConfig entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.code = entity.getCode();
			this.isUsed = entity.getIsUsed();
		}
	}
	
}

package com.globits.facility.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.facility.domain.SystemHealthOrganizationCode;

public class SystemHealthOrganizationCodeDto extends BaseObjectDto {
	private HealthOrganizationDto healthOrganization;// Đơn vị Y tế
	private String systemType;// hệ thống
	private String code;
	private String name;
	private Integer level;

	public HealthOrganizationDto getHealthOrganization() {
		return healthOrganization;
	}

	public void setHealthOrganization(HealthOrganizationDto healthOrganization) {
		this.healthOrganization = healthOrganization;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public SystemHealthOrganizationCodeDto() {
		super();
	}

	public SystemHealthOrganizationCodeDto(SystemHealthOrganizationCode entity) {
		super();
		if (entity != null) {
			this.id = entity.getId();
			this.name =entity.getName();
			this.code=entity.getCode();
			this.systemType =entity.getSystemType() != null ? entity.getSystemType().getValue() : null;
			
			if (entity.getHealthOrganization() != null) {
				this.healthOrganization = new HealthOrganizationDto(entity.getHealthOrganization(), true);
			}
		}
	}
}

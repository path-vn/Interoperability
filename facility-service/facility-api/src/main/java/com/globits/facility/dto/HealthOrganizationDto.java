package com.globits.facility.dto;

import java.util.ArrayList;
import java.util.List;

import com.globits.core.dto.BaseObjectDto;
import com.globits.facility.domain.HealthOrganization;

public class HealthOrganizationDto extends BaseObjectDto {
	private String name;
	private String code;
	private Integer level;
	private HealthOrganizationDto parent;
	private List<HealthOrganizationDto> chidren;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public HealthOrganizationDto getParent() {
		return parent;
	}

	public void setParent(HealthOrganizationDto parent) {
		this.parent = parent;
	}

	public List<HealthOrganizationDto> getChidren() {
		return chidren;
	}

	public void setChidren(List<HealthOrganizationDto> chidren) {
		this.chidren = chidren;
	}

	public HealthOrganizationDto() {
		super();
	}

	public HealthOrganizationDto(HealthOrganization entity, boolean isParent, int type) {
		if (entity != null) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.code = entity.getCode();
		}
	}

	public HealthOrganizationDto(HealthOrganization entity, boolean isParent) {
		if (entity != null) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.code = entity.getCode();
			this.level = entity.getLevel();
			if (entity.getParent() != null) {
				if (isParent) {
					this.parent = new HealthOrganizationDto();
					this.parent.setCode(entity.getParent().getCode());
					this.parent.setName(entity.getParent().getName());
				}
			}
			if (entity.getChidren() != null && entity.getChidren().size() > 0) {
				this.chidren = new ArrayList<>();
				for (HealthOrganization healthOrganization : entity.getChidren()) {
					this.chidren.add(new HealthOrganizationDto(healthOrganization, true));
				}
			}
		}
	}

	public HealthOrganizationDto(HealthOrganization entity) {
		this(entity, true);
	}

}

package com.globits.facility.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.facility.domain.SystemAddressCode;

public class SystemAddressCodeDto extends BaseObjectDto {
	private FacilityAdministrativeUnitDto administrativeUnit;// Đơn vị hành chính

	private String systemType;// hệ thống

	private String code;

	private String name;

	private Integer level;

	public FacilityAdministrativeUnitDto getAdministrativeUnit() {
		return administrativeUnit;
	}

	public void setAdministrativeUnit(FacilityAdministrativeUnitDto administrativeUnit) {
		this.administrativeUnit = administrativeUnit;
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

	public SystemAddressCodeDto() {
		super();
	}

	public SystemAddressCodeDto(SystemAddressCode entity) {
		super();
		if (entity != null) {
			this.id = entity.getId();
			this.name =entity.getName();
			this.code=entity.getCode();
			this.systemType =entity.getSystemType() != null ? entity.getSystemType().getValue() : null;
			
			if (entity.getAdministrativeUnit() != null) {
				this.administrativeUnit = new FacilityAdministrativeUnitDto(entity.getAdministrativeUnit(), true);
			}
		}
	}

}

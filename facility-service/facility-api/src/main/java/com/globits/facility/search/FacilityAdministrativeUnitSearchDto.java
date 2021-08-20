package com.globits.facility.search;

import java.util.UUID;

public class FacilityAdministrativeUnitSearchDto extends searchDto {
	private UUID parentId;
	private Boolean isGetAllCity;

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsGetAllCity() {
		return isGetAllCity;
	}

	public void setIsGetAllCity(Boolean isGetAllCity) {
		this.isGetAllCity = isGetAllCity;
	}

	public FacilityAdministrativeUnitSearchDto() {
		super();
	}

}

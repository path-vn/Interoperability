package com.globits.facility.search;

import java.util.UUID;

public class HealthOrganizationSearchDto extends searchDto {
	private UUID parentId;

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}

	public HealthOrganizationSearchDto() {
		super();
	}

}

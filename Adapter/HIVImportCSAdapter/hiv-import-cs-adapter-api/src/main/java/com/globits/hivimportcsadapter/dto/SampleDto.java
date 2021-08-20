package com.globits.hivimportcsadapter.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.hivimportcsadapter.domain.Sample;

public class SampleDto extends BaseObjectDto {

	private String name;

	private String code;

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

	public SampleDto() {
		super();
	}

	public SampleDto(Sample entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.code = entity.getCode();
	}

	public SampleDto(Sample entity, boolean simple) {
		if (simple) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.code = entity.getCode();
		} else {
		}
	}

}

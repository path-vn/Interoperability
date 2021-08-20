package com.globits.cbs.deidentification.dto;

import com.globits.cbs.deidentification.domain.Sample;
import com.globits.core.dto.BaseObjectDto;

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
		this(entity,true);
	}

	public SampleDto(Sample entity, boolean simple) {
		if (simple) {
			this.setId(entity.getId());
			this.setCreateDate(entity.getCreateDate());
			this.setCreatedBy(entity.getCreatedBy());
			this.setModifyDate(entity.getModifyDate());
			this.name = entity.getName();
			this.code = entity.getCode();
		} else {
		}
	}

}

package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.Dictionary;
import com.globits.adapter.opcassist.types.DictionaryType;

public class DictionaryDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private DictionaryType type;

	private String code;

	private String value;

	private String valueEn;

	private String description;

	private Integer order;

	private Boolean active;

	public DictionaryDto() {
		super();
	}

	public DictionaryDto(Dictionary entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.type = entity.getType();
		this.code = entity.getCode();
		this.value = entity.getValue();
		this.valueEn = entity.getValueEn();
		this.description = entity.getDescription();
		this.order = entity.getOrder();
		this.active = entity.getActive();
	}

	public Dictionary toEntity() {
		Dictionary entity = new Dictionary();
		entity.setId(id);
		entity.setType(type);
		entity.setCode(code);
		entity.setValue(value);
		entity.setValueEn(valueEn);
		entity.setDescription(description);
		entity.setOrder(order);
		entity.setActive(active);

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DictionaryType getType() {
		return type;
	}

	public void setType(DictionaryType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueEn() {
		return valueEn;
	}

	public void setValueEn(String valueEn) {
		this.valueEn = valueEn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}

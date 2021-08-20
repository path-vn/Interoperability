package com.globits.adapter.pdma.dto;

import java.io.Serializable;

import com.globits.adapter.pdma.domain.Regimen;

public class RegimenDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String shortName;

	private String name;

	private Integer line;

	private DictionaryDto disease;

	private String description;

	public RegimenDto() {
	}

	public RegimenDto(Regimen entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.shortName = entity.getShortName();
		this.name = entity.getName();
		this.line = entity.getLine();
		this.description = entity.getDescription();

		if (entity.getDisease() != null) {
			this.disease = new DictionaryDto(entity.getDisease());
		}
	}

	public Regimen toEntity() {
		Regimen entity = new Regimen();

		entity.setId(id);
		entity.setShortName(shortName);
		entity.setName(name);
		entity.setLine(line);
		entity.setDescription(description);

		if (disease != null) {
			entity.setDisease(disease.toEntity());
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public DictionaryDto getDisease() {
		return disease;
	}

	public void setDisease(DictionaryDto disease) {
		this.disease = disease;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.opcassist.domain.ClinicalStage;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer2;

public class ClinicalStageDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private CaseDto theCase;

	private OrganizationDto organization;

	private int stage;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime evalDate;

	private String note;

	public ClinicalStageDto() {

	}

	public ClinicalStageDto(ClinicalStage entity) {
		super();

		if (entity == null) {
			return;
		}

		id = entity.getId();
		stage = entity.getStage();
		evalDate = entity.getEvalDate();
		note = entity.getNote();

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
			this.organization.setName(entity.getOrganization().getName());
		}

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());
		}
	}

	public ClinicalStage toEntity() {
		ClinicalStage entity = new ClinicalStage();

		entity.setId(id);
		entity.setStage(stage);
		entity.setEvalDate(evalDate);
		entity.setNote(note);

		if (organization != null) {
			entity.setOrganization(organization.toEntity());
		}

		if (theCase != null) {
			entity.setTheCase(theCase.toEntity());
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CaseDto getTheCase() {
		return theCase;
	}

	public void setTheCase(CaseDto theCase) {
		this.theCase = theCase;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public LocalDateTime getEvalDate() {
		return evalDate;
	}

	public void setEvalDate(LocalDateTime evalDate) {
		this.evalDate = evalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

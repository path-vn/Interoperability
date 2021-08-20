package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.opcassist.domain.TBProphylaxis;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer2;

public class TBProphylaxisDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime startDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime endDate;

	private int result;

	private String note;

	private OrganizationDto organization;

	private CaseDto theCase;

	public TBProphylaxisDto() {

	}

	public TBProphylaxisDto(TBProphylaxis entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		this.result = entity.getResult();
		this.note = entity.getNote();

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

	public TBProphylaxis toEntity() {
		TBProphylaxis entity = new TBProphylaxis();

		entity.setId(id);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		entity.setResult(result);
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public CaseDto getTheCase() {
		return theCase;
	}

	public void setTheCase(CaseDto theCase) {
		this.theCase = theCase;
	}
}

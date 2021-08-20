package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.opcassist.domain.Recency;
import com.globits.adapter.opcassist.types.RecencyResult;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer2;

public class RecencyDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime screenSampleDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime screenTestDate;

	private RecencyResult screenResult;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime vlTestDate;

	private String vlResult;

	private RecencyResult confirmResult;

	private OrganizationDto organization;

	private CaseDto theCase;

	public RecencyDto() {

	}

	public RecencyDto(Recency entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.screenSampleDate = entity.getScreenSampleDate();
		this.screenTestDate = entity.getScreenTestDate();
		this.screenResult = entity.getScreenResult();

		this.vlTestDate = entity.getVlTestDate();
		this.vlResult = entity.getVlResult();

		this.confirmResult = entity.getConfirmResult();

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

	public Recency toEntity() {
		Recency entity = new Recency();

		entity.setId(id);
		entity.setScreenSampleDate(screenSampleDate);
		entity.setScreenTestDate(screenTestDate);
		entity.setScreenResult(screenResult);

		entity.setVlTestDate(vlTestDate);
		entity.setVlResult(vlResult);

		entity.setConfirmResult(confirmResult);

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

	public LocalDateTime getScreenSampleDate() {
		return screenSampleDate;
	}

	public void setScreenSampleDate(LocalDateTime screenSampleDate) {
		this.screenSampleDate = screenSampleDate;
	}

	public LocalDateTime getScreenTestDate() {
		return screenTestDate;
	}

	public void setScreenTestDate(LocalDateTime screenTestDate) {
		this.screenTestDate = screenTestDate;
	}

	public RecencyResult getScreenResult() {
		return screenResult;
	}

	public void setScreenResult(RecencyResult screenResult) {
		this.screenResult = screenResult;
	}

	public LocalDateTime getVlTestDate() {
		return vlTestDate;
	}

	public void setVlTestDate(LocalDateTime vlTestDate) {
		this.vlTestDate = vlTestDate;
	}

	public String getVlResult() {
		return vlResult;
	}

	public void setVlResult(String vlResult) {
		this.vlResult = vlResult;
	}

	public RecencyResult getConfirmResult() {
		return confirmResult;
	}

	public void setConfirmResult(RecencyResult confirmResult) {
		this.confirmResult = confirmResult;
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

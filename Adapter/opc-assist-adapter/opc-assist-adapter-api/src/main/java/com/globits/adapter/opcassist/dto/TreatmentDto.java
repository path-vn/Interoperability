package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.opcassist.domain.Treatment;
import com.globits.adapter.opcassist.utils.CustomLocalDateTimeDeserializer2;

public class TreatmentDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private CaseDto theCase;

	private OrganizationDto organization;

	private RegimenDto regimen;

	private String regimenName;

	private int regimenLine;

	private DictionaryDto disease;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime startDate;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime endDate;

	private String endingReason;

	public TreatmentDto() {
	}

	public TreatmentDto(Treatment entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.regimenName = entity.getRegimenName();
		this.regimenLine = entity.getRegimenLine();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		this.endingReason = entity.getEndingReason();

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());
		}

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
		}

		if (entity.getRegimen() != null) {
			this.regimen = new RegimenDto(entity.getRegimen());
		}

		if (entity.getDisease() != null) {
			this.disease = new DictionaryDto(entity.getDisease());
		}
	}

	public Treatment toEntity() {
		
		Treatment entity = new Treatment();

		entity.setId(id);
		entity.setRegimenName(regimenName);
		entity.setRegimenLine(regimenLine);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		entity.setEndingReason(endingReason);

		if (theCase != null) {
			entity.setTheCase(theCase.toEntity());
		}

		if (organization != null) {
			entity.setOrganization(organization.toEntity());
		}

		if (regimen != null) {
			entity.setRegimen(regimen.toEntity());
		}

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

	public RegimenDto getRegimen() {
		return regimen;
	}

	public void setRegimen(RegimenDto regimen) {
		this.regimen = regimen;
	}

	public String getRegimenName() {
		return regimenName;
	}

	public void setRegimenName(String regimenName) {
		this.regimenName = regimenName;
	}

	public int getRegimenLine() {
		return regimenLine;
	}

	public void setRegimenLine(int regimenLine) {
		this.regimenLine = regimenLine;
	}

	public DictionaryDto getDisease() {
		return disease;
	}

	public void setDisease(DictionaryDto disease) {
		this.disease = disease;
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

	public String getEndingReason() {
		return endingReason;
	}

	public void setEndingReason(String endingReason) {
		this.endingReason = endingReason;
	}
}

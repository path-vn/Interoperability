package com.globits.adapter.pdma.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.pdma.domain.Dictionary;
import com.globits.adapter.pdma.domain.RiskInterview;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer2;

public class RiskInterviewDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private OrganizationDto organization;

	private CaseDto theCase;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime interviewDate;

	private Boolean riskIdentified;

	private String otherRiskGroupText;

	private Set<DictionaryDto> risks = new HashSet<>();

	public RiskInterviewDto() {
	}

	public RiskInterviewDto(RiskInterview entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.interviewDate = entity.getInterviewDate();
		this.riskIdentified = entity.getRiskIdentified();
		this.otherRiskGroupText = entity.getOtherRiskGroupText();

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
			this.organization.setActive(entity.getOrganization().getActive());
			this.organization.setName(entity.getOrganization().getName());
		}

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());
		}

		if (entity.getRisks() != null) {
			entity.getRisks().parallelStream().forEach(d -> {
				risks.add(new DictionaryDto(d));
			});
		}
	}

	public RiskInterview toEntity() {
		RiskInterview entity = new RiskInterview();

		entity.setId(id);
		entity.setInterviewDate(interviewDate);
		entity.setRiskIdentified(riskIdentified);
		entity.setOtherRiskGroupText(otherRiskGroupText);

		if (organization != null) {
			entity.setOrganization(organization.toEntity());
		}

		if (theCase != null) {
			entity.setTheCase(theCase.toEntity());
		}

		if (risks != null) {
			Set<Dictionary> _risks = new HashSet<>();

			risks.parallelStream().filter(dto -> (dto.getId() != null)).forEach(dto -> {
				_risks.add(dto.toEntity());
			});

			entity.getRisks().addAll(_risks);
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDateTime interviewDate) {
		this.interviewDate = interviewDate;
	}

	public Boolean getRiskIdentified() {
		return riskIdentified;
	}

	public void setRiskIdentified(Boolean riskIdentified) {
		this.riskIdentified = riskIdentified;
	}

	public String getOtherRiskGroupText() {
		return otherRiskGroupText;
	}

	public void setOtherRiskGroupText(String otherRiskGroupText) {
		this.otherRiskGroupText = otherRiskGroupText;
	}

	public Set<DictionaryDto> getRisks() {

		if (risks == null) {
			risks = new HashSet<>();
		}

		return risks;
	}

	public void setRisks(Set<DictionaryDto> risks) {
		this.risks = risks;
	}
}

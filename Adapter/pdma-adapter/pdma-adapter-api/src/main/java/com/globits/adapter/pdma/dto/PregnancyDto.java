package com.globits.adapter.pdma.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.globits.adapter.pdma.domain.Pregnancy;
import com.globits.adapter.pdma.types.PregnancyResult;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer;
import com.globits.adapter.pdma.utils.CustomLocalDateTimeDeserializer2;

public class PregnancyDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createDate;
	
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime modifyDate;
	
	private OrganizationDto organization;

	private CaseDto theCase;

	private boolean pregnant;

	private boolean attendedAnc;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime lastMenstrualPeriod;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime dueDate;

	private PregnancyResult pregResult;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime childDob;

	private double birthWeight;

	private Integer childProphylaxis;

	private int childHIVStatus;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	private LocalDateTime childDiagnosedDate;

	private boolean childInitiatedOnART;

	private String childOpc;

	private String note;

	public PregnancyDto() {

	}

	public PregnancyDto(Pregnancy entity) {
		super();

		this.id = entity.getId();
		this.pregnant = entity.isPregnant();
		this.attendedAnc = entity.isAttendedAnc();
		this.lastMenstrualPeriod = entity.getLastMenstrualPeriod();
		this.dueDate = entity.getDueDate();
		this.pregResult = entity.getPregResult();
		this.childDob = entity.getChildDob();
		this.birthWeight = entity.getBirthWeight();
		this.childProphylaxis = entity.getChildProphylaxis();
		this.childHIVStatus = entity.getChildHIVStatus();
		this.childDiagnosedDate = entity.getChildDiagnosedDate();
		this.childInitiatedOnART = entity.isChildInitiatedOnART();
		this.childOpc = entity.getChildOpc();
		this.note = entity.getNote();

		if (entity.getTheCase() != null) {
			this.theCase = new CaseDto();
			this.theCase.setId(entity.getTheCase().getId());
		}

		if (entity.getOrganization() != null) {
			this.organization = new OrganizationDto();
			this.organization.setId(entity.getOrganization().getId());
		}
	}

	public Pregnancy toEntity() {
		Pregnancy entity = new Pregnancy();

		entity.setId(id);
		entity.setPregnant(pregnant);
		entity.setAttendedAnc(attendedAnc);
		entity.setLastMenstrualPeriod(lastMenstrualPeriod);
		entity.setDueDate(dueDate);
		entity.setPregResult(pregResult);
		entity.setChildDob(childDob);
		entity.setBirthWeight(birthWeight);
		entity.setChildProphylaxis(childProphylaxis);
		entity.setChildHIVStatus(childHIVStatus);
		entity.setChildDiagnosedDate(childDiagnosedDate);
		entity.setChildInitiatedOnART(childInitiatedOnART);
		entity.setChildOpc(childOpc);
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

	public boolean isPregnant() {
		return pregnant;
	}

	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}

	public LocalDateTime getLastMenstrualPeriod() {
		return lastMenstrualPeriod;
	}

	public void setLastMenstrualPeriod(LocalDateTime lastMenstrualPeriod) {
		this.lastMenstrualPeriod = lastMenstrualPeriod;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getChildDob() {
		return childDob;
	}

	public void setChildDob(LocalDateTime childDob) {
		this.childDob = childDob;
	}

	public double getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(double birthWeight) {
		this.birthWeight = birthWeight;
	}

	public Integer getChildProphylaxis() {
		return childProphylaxis;
	}

	public void setChildProphylaxis(Integer childProphylaxis) {
		this.childProphylaxis = childProphylaxis;
	}

	public boolean isAttendedAnc() {
		return attendedAnc;
	}

	public void setAttendedAnc(boolean attendedAnc) {
		this.attendedAnc = attendedAnc;
	}

	public PregnancyResult getPregResult() {
		return pregResult;
	}

	public void setPregResult(PregnancyResult pregResult) {
		this.pregResult = pregResult;
	}

	public int getChildHIVStatus() {
		return childHIVStatus;
	}

	public void setChildHIVStatus(int childHIVStatus) {
		this.childHIVStatus = childHIVStatus;
	}

	public LocalDateTime getChildDiagnosedDate() {
		return childDiagnosedDate;
	}

	public void setChildDiagnosedDate(LocalDateTime childDiagnosedDate) {
		this.childDiagnosedDate = childDiagnosedDate;
	}

	public boolean isChildInitiatedOnART() {
		return childInitiatedOnART;
	}

	public void setChildInitiatedOnART(boolean childInitiatedOnART) {
		this.childInitiatedOnART = childInitiatedOnART;
	}

	public String getChildOpc() {
		return childOpc;
	}

	public void setChildOpc(String childOpc) {
		this.childOpc = childOpc;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}

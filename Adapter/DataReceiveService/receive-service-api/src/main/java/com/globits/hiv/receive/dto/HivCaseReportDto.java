package com.globits.hiv.receive.dto;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globits.hiv.receive.domain.HivCaseReport;

public class HivCaseReportDto {
	
	private UUID id;
	
	private Date createDate;

	private String createdBy;

	private Date modifyDate;

	private String modifiedBy;
	
//	private Boolean voided;
	
//	private String content;
	
	private String title;
	
//	private String instructions;
//	
//	private String facilityId;
	
	private Date reportDate;
		
	private String systemId;

	private PatientDto patientDto;
	
	public HivCaseReportDto() {
		
	}
	
	public HivCaseReportDto(HivCaseReport entity) {
		this.id = entity.getId();
		this.createDate = entity.getCreateDate();
		this.createdBy = entity.getCreatedBy();
		this.modifyDate = entity.getModifyDate();
		this.modifiedBy = entity.getModifiedBy();
//		this.voided = entity.getVoided();
//		this.content = entity.getContent();
		this.title = entity.getTitle();
//		this.instructions = entity.getInstructions();
		this.reportDate = entity.getReportDate();
//		this.facilityId = entity.getFacilityId();
		this.systemId = entity.getSystemId();		
//        String jsonInString = objectMapper.writeValueAsString(reportDto);
//        System.out.println(jsonInString);

        if(entity.getContent()!=null && entity.getContent().length()>0) {
        	ObjectMapper objectMapper = new ObjectMapper();
        	try {
				this.patientDto = objectMapper.readValue(entity.getContent(), PatientDto.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
        }
	}
	
	public HivCaseReportDto(UUID id, Date createDate, String createdBy, Date modifyDate, String modifiedBy,
			Boolean voided, String content, String title, String instructions, Date reportDate, String facilityId,
			String systemId, PatientDto patientDto) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
//		this.voided = voided;
//		this.content = content;
		this.title = title;
//		this.instructions = instructions;
		this.reportDate = reportDate;
//		this.facilityId = facilityId;
		this.systemId = systemId;
		this.patientDto = patientDto;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

//	public Boolean getVoided() {
//		return voided;
//	}
//
//	public void setVoided(Boolean voided) {
//		this.voided = voided;
//	}

//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getInstructions() {
//		return instructions;
//	}
//
//	public void setInstructions(String instructions) {
//		this.instructions = instructions;
//	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
//	public String getFacilityId() {
//		return facilityId;
//	}
//
//	public void setFacilityId(String facilityId) {
//		this.facilityId = facilityId;
//	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public PatientDto getPatientDto() {
		return patientDto;
	}

	public void setPatientDto(PatientDto patientDto) {
		this.patientDto = patientDto;
	}
}

package com.globits.hiv.extraction.dto;

import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.r4.model.Resource;

public class PatientInfoDto {
	private HivPatient patient;
	private List<? extends Resource> children;
	private List<HivPregnancyDto> pregnancies;
	private List<CoMorbidityDto> listCoMorbidity;
	private List<HivArvTreatmentDto> arvTreatmentDto;
	
	public HivPatient getPatient() {
		return patient;
	}
	public void setPatient(HivPatient patient) {
		this.patient = patient;
	}
	public List<? extends Resource> getChildren() {
		return children;
	}
	public void setChildren(List<? extends Resource> children) {
		this.children = children;
	}
	public List<HivPregnancyDto> getPregnancies() {
		return pregnancies;
	}
	public void setPregnancies(List<HivPregnancyDto> pregnancies) {
		this.pregnancies = pregnancies;
	}
	public List<CoMorbidityDto> getListCoMorbidity() {
		return listCoMorbidity;
	}
	public void setListCoMorbidity(List<CoMorbidityDto> listCoMorbidity) {
		this.listCoMorbidity = listCoMorbidity;
	}
	public List<HivArvTreatmentDto> getArvTreatmentDto() {
		return arvTreatmentDto;
	}
	public void setArvTreatmentDto(List<HivArvTreatmentDto> arvTreatmentDto) {
		this.arvTreatmentDto = arvTreatmentDto;
	}
	
	
}

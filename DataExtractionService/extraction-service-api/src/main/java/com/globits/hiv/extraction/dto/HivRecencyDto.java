package com.globits.hiv.extraction.dto;

import java.util.List;

import org.hl7.fhir.r4.model.Coding;

public class HivRecencyDto {
	private List<LabTestDto> listTest;
	private Coding recentInfectionConclusion;

	public List<LabTestDto> getListTest() {
		return listTest;
	}
	public void setListTest(List<LabTestDto> listTest) {
		this.listTest = listTest;
	}
	public Coding getRecentInfectionConclusion() {
		return recentInfectionConclusion;
	}
	public void setRecentInfectionConclusion(Coding recentInfectionConclusion) {
		this.recentInfectionConclusion = recentInfectionConclusion;
	}
	
}

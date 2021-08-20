package com.globits.hiv.extraction.dto;

import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivPregnancyObservation;

public class HivPregnancyDto {
	private HivPregnancyObservation pregnancyObservation;
	private HivPregnancyObservation outcomes;
	private List<HivChildDto> listChild;
	public HivPregnancyObservation getPregnancyObservation() {
		return pregnancyObservation;
	}
	public void setPregnancyObservation(HivPregnancyObservation pregnancyObservation) {
		this.pregnancyObservation = pregnancyObservation;
	}
	public HivPregnancyObservation getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(HivPregnancyObservation outcomes) {
		this.outcomes = outcomes;
	}
	public List<HivChildDto> getListChild() {
		return listChild;
	}
	public void setListChild(List<HivChildDto> listChild) {
		this.listChild = listChild;
	}
	
}

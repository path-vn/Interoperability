package com.globits.hiv.extraction.dto;

import org.hl7.fhir.hiv.vietnam.r4.model.HIVSpecimen;
import org.hl7.fhir.r4.model.Observation;

public class LabTestDto {
	private Observation observation;
	private HIVSpecimen specimen;
	public Observation getObservation() {
		return observation;
	}
	public void setObservation(Observation observation) {
		this.observation = observation;
	}
	public HIVSpecimen getSpecimen() {
		return specimen;
	}
	public void setSpecimen(HIVSpecimen specimen) {
		this.specimen = specimen;
	}
	
}

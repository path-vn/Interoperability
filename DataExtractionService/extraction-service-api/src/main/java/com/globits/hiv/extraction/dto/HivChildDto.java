package com.globits.hiv.extraction.dto;

import org.hl7.fhir.hiv.vietnam.r4.model.HivChildPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPregnancyObservation;

public class HivChildDto {
	private HivChildPatient child;
	private HivPregnancyObservation observation;
	public HivChildPatient getChild() {
		return child;
	}
	public void setChild(HivChildPatient child) {
		this.child = child;
	}
	public HivPregnancyObservation getObservation() {
		return observation;
	}
	public void setObservation(HivPregnancyObservation observation) {
		this.observation = observation;
	}
	
	
}

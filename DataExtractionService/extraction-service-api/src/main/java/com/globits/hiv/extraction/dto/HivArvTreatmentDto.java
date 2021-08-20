package com.globits.hiv.extraction.dto;

import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.ARVCarePlan;
import org.hl7.fhir.r4.model.PlanDefinition;

public class HivArvTreatmentDto {
	private ARVCarePlan carePlan;
	private List<PlanDefinition> planDefinition;
	public ARVCarePlan getCarePlan() {
		return carePlan;
	}
	public void setCarePlan(ARVCarePlan carePlan) {
		this.carePlan = carePlan;
	}
	public List<PlanDefinition> getPlanDefinition() {
		return planDefinition;
	}
	public void setPlanDefinition(List<PlanDefinition> planDefinition) {
		this.planDefinition = planDefinition;
	}
	
}

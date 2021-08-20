package org.hl7.fhir.hiv.vietnam.r4.model;

import java.util.List;

import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
@ResourceDef(name="PlanDefinition",profile = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-regimen")
public class HivRegimen extends PlanDefinition {
	private static final long serialVersionUID = 1L;
//	
//    @Child(name = "testCondition")
//    @Extension(url="testCondition", definedLocally=true, isModifier=false)
//    private List<Reference> testConditions = null;
//
//	public List<Reference> getTestConditions() {
//		return testConditions;
//	}
//
//	public void setTestConditions(List<Reference> testConditions) {
//		this.testConditions = testConditions;
//	}
    
    
}

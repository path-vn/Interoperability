package org.hl7.fhir.hiv.vietnam.r4.model;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.DecimalType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
@ResourceDef(name="Observation",profile = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-diagnosis-profile")
public class HivDiagnosisObservation extends Observation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Child(name = "specimenCollectionPlace")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/ext-specimen-collection-place", definedLocally = false, isModifier = false)
	private StringType specimenCollectionPlace;


	@Child(name = "confirmatoryLab")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/confirmatory-lab", definedLocally = false, isModifier = false)
	private Coding confirmatoryLab;
	
	public Coding getConfirmatoryLab() {
		return confirmatoryLab;
	}
	public void setConfirmatoryLab(Coding confirmatoryLab) {
		this.confirmatoryLab = confirmatoryLab;
	}
	
	public StringType getSpecimenCollectionPlace() {
		return specimenCollectionPlace;
	}
	public void setSpecimenCollectionPlace(StringType specimenCollectionPlace) {
		this.specimenCollectionPlace = specimenCollectionPlace;
	}
}

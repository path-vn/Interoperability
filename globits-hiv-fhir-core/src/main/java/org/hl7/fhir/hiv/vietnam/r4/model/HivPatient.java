package org.hl7.fhir.hiv.vietnam.r4.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
@ResourceDef(name="Patient",profile = "http://openhie.org/fhir/openhie.vn.case-reporting.hiv/StructureDefinition/hiv-patient")
public class HivPatient extends Patient {

	private static final long serialVersionUID = 1L;

	@Child(name = "ethnicity")
	@Extension(url="http://openhie.org/fhir/openhie.vn.case-reporting.hiv/StructureDefinition/ext-vn-ethnicity", definedLocally = false, isModifier = false)
	private CodeableConcept ethnicity;
	
	@Child(name = "genderIdentity")
	@Extension(url="http://hl7.org/fhir/StructureDefinition/patient-genderIdentity", definedLocally = false, isModifier = false)
	private CodeableConcept genderIdentity;

	@Child(name = "occupation")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-occupations", definedLocally = false, isModifier = false)
	private Coding occupation;

	
	@Child(name = "population")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-population", definedLocally = false, isModifier = false)
	private Coding population;

	@Child(name = "causeofdeath")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-causeofdeath", definedLocally = false, isModifier = false)
	private Coding causeOfDeath;

	@Child(name = "treatmentStatus")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-treatmentstatus", definedLocally = false, isModifier = false)
	private Coding treatmentStatus;

	@Child(name = "syncorg")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-syncorg", definedLocally = false, isModifier = false)
	private CodeableConcept syncOrg;
	
	@Child(name = "lastUpdated")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-last-updated", definedLocally = false, isModifier = false)
	private Coding lastUpdated;
	
	public CodeableConcept getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(CodeableConcept ethnicity) {
		this.ethnicity = ethnicity;
	}

	public CodeableConcept getGenderIdentity() {
		return genderIdentity;
	}

	public void setGenderIdentity(CodeableConcept genderIdentity) {
		this.genderIdentity = genderIdentity;
	}
	
	public Coding getOccupation() {
		return occupation;
	}

	public void setOccupation(Coding occupation) {
		this.occupation = occupation;
	}

	public Coding getPopulation() {
		return population;
	}

	public void setPopulation(Coding population) {
		this.population = population;
	}

	public Coding getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(Coding causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public Coding getTreatmentStatus() {
		return treatmentStatus;
	}

	public void setTreatmentStatus(Coding treatmentStatus) {
		this.treatmentStatus = treatmentStatus;
	}

	public CodeableConcept getSyncOrg() {
		return syncOrg;
	}

	public void setSyncOrg(CodeableConcept syncOrg) {
		this.syncOrg = syncOrg;
	}
	
	public Coding getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Coding lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Override
	public Narrative getText() {
		return super.getText();
	}
	
	public boolean hivEquals(HivPatient other) {
		return this.equalsShallow(other);
	}

	
}

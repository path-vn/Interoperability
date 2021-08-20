package org.hl7.fhir.hiv.vietnam.r4.model;



import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DecimalType;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
@ResourceDef(name="Patient",profile = "http://openhie.org/fhir/openhie.vn.case-reporting.hiv/StructureDefinition/HIVChildPatient")
public class HivChildPatient extends Patient {
	@Child(name = "birthWeight")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-child#birthWeight", definedLocally = false, isModifier = false)
	private DecimalType birthWeight;
	@Child(name = "birthDefects")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-child#birthDefects", definedLocally = false, isModifier = false)
	private BooleanType birthDefects;
	@Child(name = "hivStatus")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-child#hivStatus", definedLocally = false, isModifier = false)
	private Coding hivStatus;
	public DecimalType getBirthWeight() {
		return birthWeight;
	}
	public void setBirthWeight(DecimalType birthWeight) {
		this.birthWeight = birthWeight;
	}
	public BooleanType getBirthDefects() {
		return birthDefects;
	}
	public void setBirthDefects(BooleanType birthDefects) {
		this.birthDefects = birthDefects;
	}
	public Coding getHivStatus() {
		return hivStatus;
	}
	public void setHivStatus(Coding hivStatus) {
		this.hivStatus = hivStatus;
	}
	
}

package org.hl7.fhir.hiv.vietnam.r4.model;

import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
@ResourceDef(name="Specimen",profile = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-specimen")
public class HIVSpecimen extends Specimen {
	private static final long serialVersionUID = 1L;
	@Child(name = "specimen-collection-place")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/ext-specimen-collection-place", definedLocally = false, isModifier = false)
	private StringType specimenCollectionPlace;

	public StringType getSpecimenCollectionPlace() {
		return specimenCollectionPlace;
	}
	public void setSpecimenCollectionPlace(StringType specimenCollectionPlace) {
		this.specimenCollectionPlace = specimenCollectionPlace;
	}
}

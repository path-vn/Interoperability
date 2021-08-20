package org.hl7.fhir.hiv.vietnam.r4.model;

import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.DecimalType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name="Observation",profile = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-status")
public class HivPregnancyObservation extends Observation{
	/*
	 * Note: In hiv-ig using the Condition but some other map the Pregnancy to Observation
	 */
	@Child(name = "datePregnancyReported")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-status#datePregnancyReported", definedLocally = false, isModifier = false)
	private DateType datePregnancyReported;
	@Child(name = "placePregnancyReported")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-status#placePregnancyReported", definedLocally = false, isModifier = false)
	private StringType placePregnancyReported;
	@Child(name = "deliveryPlace")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-status#deliveryPlace", definedLocally = false, isModifier = false)
	private StringType deliveryPlace;
    
	public DateType getDatePregnancyReported() {
		return datePregnancyReported;
	}

	public void setDatePregnancyReported(DateType datePregnancyReported) {
		this.datePregnancyReported = datePregnancyReported;
	}

	public StringType getPlacePregnancyReported() {
		return placePregnancyReported;
	}

	public void setPlacePregnancyReported(StringType placePregnancyReported) {
		this.placePregnancyReported = placePregnancyReported;
	}

	public StringType getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(StringType deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}




	
}

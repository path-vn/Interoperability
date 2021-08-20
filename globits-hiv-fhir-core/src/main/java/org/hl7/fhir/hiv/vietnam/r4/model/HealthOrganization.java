package org.hl7.fhir.hiv.vietnam.r4.model;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
@ResourceDef(name="Organization",profile = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-health-organization")
public class HealthOrganization extends Organization {
	@Child(name = "parentCode")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-health-observation-parentCode", definedLocally = false, isModifier = false)
	private StringType parentCode;

	public StringType getParentCode() {
		return parentCode;
	}

	public void setParentCode(StringType parentCode) {
		this.parentCode = parentCode;
	}
	
	
	
	
}

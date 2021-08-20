package com.globits.cbs.deidentification.rest;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.codesystems.NameUse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globits.fhir.hiv.service.impl.HAPIFhirService;
@RestController
@RequestMapping("/api/fhir")
public class TestFHIRController {
	public static void main(String[] args) {
		Patient patient = new Patient();
		patient.setId("test_patient");
		patient.setName(new ArrayList<HumanName>());
		HumanName name = new HumanName();
		name.setFamily("Nguyen");
		name.setGiven(new ArrayList<StringType>());
		name.getGiven().add(new StringType("Van Anh"));
		patient.getName().add(name);
		Identifier identifier = new Identifier();
		identifier.setType(new CodeableConcept());
		Coding ppn = new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "PPN", null);
		identifier.getType().addCoding(ppn);
		HAPIFhirService.saveResource(patient);
		//name.getUse().equals(NameUse.OFFICIAL)
	}
}

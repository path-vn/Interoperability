package com.globits.hiv.extraction.service;

import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Resource;

import ca.uhn.fhir.rest.api.MethodOutcome;

public interface QRExtractionService {
	public Bundle getFhirResourceFromQR(QuestionnaireResponse qr);
	public MethodOutcome savePatient(QuestionnaireResponse qr); 
	public Bundle savePatientTransaction(QuestionnaireResponse qr) ;
}

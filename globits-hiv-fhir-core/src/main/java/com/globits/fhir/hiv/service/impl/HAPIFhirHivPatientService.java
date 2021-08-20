package com.globits.fhir.hiv.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.base.composite.BaseIdentifierDt;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.api.SortOrderEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IDelete;
import ca.uhn.fhir.rest.gclient.IDeleteTyped;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.TokenParam;

public class HAPIFhirHivPatientService {
	public static String serverBaseUrl = HivConst.serverFhirUrl;
	static FhirContext ctx = FhirContext.forR4();
	static IParser jsonParser = ctx.newJsonParser();
	public HivPatient deIdentification(HivPatient patient,List<String> blackListFields) {
		HivPatient result = patient;
		for(String name:blackListFields) {
			if(name.equals("genderIdentity")) {
				patient.setName(null);
			}else if(name.equals("address")) {
				patient.setAddress(null);
			}else if(name.equals("name")) {
				patient.setName(null);
			}else if(name.equals("identifier")) {
				patient.setIdentifier(null);
			}
		}
		return patient;
	}
	public static MethodOutcome savePatient (HivPatient patient) {
		return HAPIFhirService.saveResource(patient);
	}
	public static MethodOutcome createPatient (HivPatient patient) {
		return HAPIFhirService.createResource(patient);
	}
	public static Bundle savePatientTransaction (HivPatient patient, List<? extends Resource> children) {
		  // Create a bundle that will be used as a transaction
		  patient.setId(IdType.newRandomUuid());
		  Bundle bundle = new Bundle();
		  bundle.setType(Bundle.BundleType.TRANSACTION);
		  bundle.addEntry()
		     .setFullUrl(patient.getIdElement().getValue())
		     .setResource(patient)
		     .getRequest()
		        .setUrl(Patient.class.getSimpleName())
		        .setMethod(Bundle.HTTPVerb.POST);
		
			for(Resource r : children) {
				if(r.getResourceType().name().equals(Observation.class.getSimpleName())) {
					Observation obs = (Observation)r;
					obs.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					     .setResource(obs)
					     .getRequest()
					      .setUrl(Observation.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}else if(r.getResourceType().name().equals(Condition.class.getSimpleName())) {
					Condition cond = (Condition)r;
					cond.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					     .setResource(cond)
					     .getRequest()
					      .setUrl(Condition.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}else if(r.getResourceType().name().equals(CarePlan.class.getSimpleName())) {
					CarePlan carePlan = (CarePlan)r;
					carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					     .setResource(carePlan)
					     .getRequest()
					      .setUrl(CarePlan.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}
				else if(r.getResourceType().name().equals(PlanDefinition.class.getSimpleName())) {
					CarePlan carePlan = (CarePlan)r;
					carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					     .setResource(carePlan)
					     .getRequest()
					      .setUrl(PlanDefinition.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}
				
			}
		  // Save bundle which contains the Patient and list Resource to HAPI Server
		  FhirContext ctx = FhirContext.forR4();
		  IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		  Bundle resp = client.transaction().withBundle(bundle).execute();
		  return resp;
	}
	
	public static HivPatient getPatientById(String theUrl) {
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      HivPatient response= client.fetchResourceFromUrl(HivPatient.class, theUrl);
	      return response;
	}
	public static MethodOutcome deletePatient(HivPatient patient) {
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      IDelete response= client.delete();
	      IDeleteTyped deleteType = response.resource(patient);
	      
	      MethodOutcome outcome = deleteType.execute();
	      
	      return outcome;
	}
	
	public static MethodOutcome deletePatientById(String patientId) {
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      IDelete response= client.delete();
	      IDeleteTyped deleteType = response.resourceById(Patient.class.getSimpleName(), patientId);
	      
	      MethodOutcome outcome = deleteType.execute();
	      
	      return outcome;
	}
	public static Bundle findPatientDuplicatePatient(String theSystem, String indentifier) {
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      Bundle response = client.search()
	    	         .forResource(HivPatient.class)
	    	         .where(Patient.IDENTIFIER.exactly().systemAndCode(theSystem, indentifier))
	    	         .totalMode(SearchTotalModeEnum.ACCURATE)
	    	         .count(100)
	    	         .returnBundle(Bundle.class)
	    	         .execute();
	      return response;
	}
	
	public static Bundle getPatientEverythingById(String theUrl) {
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      Bundle response= client.fetchResourceFromUrl(Bundle.class, theUrl);
	      return response;
	}
	
	public static Bundle getPatientBundle() {
	      FhirContext ctx = FhirContext.forR4();
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      // Build a search and execute it
	      Bundle response = client
	    		  .search()
	    		  .forResource(HivPatient.class)
	    		  .returnBundle(Bundle.class)
	    		  .execute();
	      System.out.println("Number of Responses: " + response.getTotal());
	      return response;
	  }
	
	public static Bundle getPatientBundle(int pageIndex, int pageSize) {
		int pagesoffset= (pageIndex-1)*pageSize;
	      FhirContext ctx = FhirContext.forR4();
	      //serverBaseUrl="http://hapi.fhir.org/baseR4";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      // Build a search and execute it
	      Bundle response = client
	    		  .search()
	    		  .forResource(HivPatient.class)
	    		  .totalMode(SearchTotalModeEnum.ACCURATE)
	    		  .count(pageSize)
	    		  .offset(pagesoffset)
	    		  .returnBundle(Bundle.class)
	    		  .execute();
	      return response;
	  }
	
	public static Bundle getPatientByLastUpdate(int pageIndex, int pageSize,Date fromDate, Date toDate) {
		int pagesoffset= (pageIndex-1)*pageSize;
	      FhirContext ctx = FhirContext.forR4();
	      DateRangeParam dateRange = new DateRangeParam(fromDate, toDate);
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      ctx.getRestfulClientFactory().setConnectionRequestTimeout(200000);
	      Bundle response = client
	    		  .search()
	    		  .forResource(HivPatient.class)
	    		  .lastUpdated(dateRange)
	    		  .totalMode(SearchTotalModeEnum.ACCURATE)
	    		  .count(pageSize)
	    		  .offset(pagesoffset)
	    		  .returnBundle(Bundle.class)
	    		  .execute();
	      return response;
	  }

	public static List<HivPatient> getListPatient() {
		Bundle bundle = getPatientBundle();
		List<HivPatient> list  = (List<HivPatient>)HAPIFhirService.bundleToList(bundle);
		return list;
	  }
	public static Bundle searchPatient(SortSpec theSort ) {
	      FhirContext ctx = FhirContext.forR4();
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      // Build a search and execute it
	      Bundle response = client.search()
	         .forResource(HivPatient.class)
	         //.where(HivPatient.NAME.contains().value("Bùi"))
	         //.and(new TokenClientParam("occupation").exactly().code("Kinh"))
	         //.and(HivPatient.BIRTHDATE.before().day("2014-01-01"))
	         .totalMode(SearchTotalModeEnum.ACCURATE)
	         .count(2)
	         .sort(theSort)
	         .offset(7)
	         .returnBundle(Bundle.class)
	         .execute();
	      
	      
	      // How many resources did we find?
	      System.out.println("Responses: " + response.getTotal());
	      for(int i=0;i<response.getEntry().size();i++) {
	    	  Patient p = (Patient)response.getEntry().get(i).getResource();
	    	  System.out.println(p.getMeta().getLastUpdated()+":"+p.getNameFirstRep().getText()+":"+p.getBirthDate()+":"+p.getGender().getDisplay());
	      }
	      // Print the ID of the first one
	      System.out.println("First response ID: " + response.getEntry().get(0).getResource().getId());
	      return response;
	}
	
	public static void main(String[] args) {
		serverBaseUrl = "http://fhircs.globits.net:8082/fhir";
		HivPatient p  = getPatientById("Patient/3");
		Date date  = p.getMeta().getLastUpdated();
		System.out.println(date);
	System.out.println(PlanDefinition.class.getSimpleName());
	//Bundle bundle1 = getPatientEverythingById("Patient/1402/$everything");
	//serverBaseUrl ="http://fhir.globits.net:8082/fhir";
	//serverBaseUrl="http://fhir.globits.net:7070/hapi/fhir";
			//public static String serverFhirUrl = "http://fhir.globits.net:8082/fhir";
			//public static String serverFhirUrl = "http://fhir.globits.net:7070/hapi/fhir";			
	SortSpec theSort = new SortSpec("_lastUpdated",SortOrderEnum.DESC);//lastUpdate
	//theSort = new SortSpec("birthdate",SortOrderEnum.DESC);//birthdate
	//theSort = new SortSpec("gender",SortOrderEnum.ASC);//gender
	//searchPatient(theSort);
	SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");  
	Date fromDate;
	try {
		fromDate = df.parse("01/05/2021");
		Date toDate = df.parse("15/05/2021");
		int pageIndex =1;
		int pageSize =30;
		Date start = LocalDateTime.now().toDate();
		Bundle bundle = getPatientByLastUpdate(pageIndex, pageSize, fromDate, toDate);
		Date end = LocalDateTime.now().toDate();
		System.out.println("Time:"+(end.getTime()-start.getTime())/1000);
		//Bundle bundle = getPatientBundle(3,3);
//		Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
//		//Page<BundleEntryComponent> result = new PageImpl<BundleEntryComponent>(bundle.getEntry(), pageable, bundle.getTotal());
//		System.out.println(bundle.getTotal()+":"+bundle.getEntry().size());
//		Page<BundleEntryComponent> result = new PageImpl<BundleEntryComponent>(bundle.getEntry(), pageable, bundle.getTotal());
//		System.out.println(result.getTotalElements()+":"+result.getNumberOfElements()+":"+result.getTotalPages());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
//		HivPatient patient = getPatientById("Patient/639");
//		System.out.println(patient.getIdentifier().get(1).getValue());
//		Bundle bundle = findPatientDuplicatePatient(HivConst.ArvIdentifierNamingSystem,"2");
//		 System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));		
//		Bundle bundle = getPatientBundle();
		//getListPatient();
//		Bundle response = getPatientBundle(2,10);
//		System.out.println(response.getTotal()+":"+response.getEntry().size());
//		HivPatient patient = new HivPatient();
//		patient.setEthnicity(new CodeableConcept());
//		patient.getEthnicity().addCoding(new Coding("http", "Test", "For test only"));
//		  patient.addName()
//		     .setFamily("Jameson")
//		     .addGiven("J")
//		     .addGiven("Jonah");
//		  Address address = patient.addAddress();
//		  address.setCity("Hà nội");
//		  address.addLine("So nha 67 ngach 23 ngo 120 Yen Lang");
//		  address.setPostalCode("10000");
//		  address.setCityElement(new StringType("HN"));
//		  // Create an observation object
//		  Observation observation = new Observation();
//		  observation.setStatus(Observation.ObservationStatus.FINAL);
//		  observation
//		     .getCode()
//		        .addCoding()
//		           .setSystem("http://loinc.org")
//		           .setCode("789-8")
//		           .setDisplay("Erythrocytes [#/volume] in Blood by Automated count");
//		  observation.setValue(
//		     new Quantity()
//		        .setValue(4.12)
//		        .setUnit("10 trillion/L")
//		        .setSystem("http://unitsofmeasure.org")
//		        .setCode("10*12/L"));
//		  List<Resource> obs= new ArrayList<Resource>();
//		  obs.add(observation);
//		  Condition cond = new Condition();
//		  cond.setOnset(DateTimeType.now());
//		  obs.add(cond);
//
//		  CarePlan carePlan = new CarePlan();
//		  carePlan.setIdBase("Test-CarePlan");
//		  obs.add(carePlan);
//
//		  Bundle resp= savePatientTransaction(patient,obs);
//		  //String theUrl ="Patient/312/$everything";
//		  //Bundle resp= getPatientEverythingById(theUrl);
//		  System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
		//MethodOutcome outcome = savePatient(patient);
		//patient = getPatientById(outcome.getId().toString());
		//System.out.println(patient.getEthnicity().getCoding().get(0).getCode());
	}
}

package com.globits.fhir.hiv.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.hiv.vietnam.r4.model.HIVSpecimen;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivRegimen;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.ActivityDefinition;
import org.hl7.fhir.r4.model.ActivityDefinition.ActivityDefinitionKind;
import org.hl7.fhir.r4.model.ActivityDefinition.ActivityDefinitionParticipantComponent;
import org.hl7.fhir.r4.model.ActivityDefinition.ActivityParticipantType;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Dosage.DosageDoseAndRateComponent;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.Medication.MedicationIngredientComponent;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.PlanDefinition.PlanDefinitionActionComponent;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.Ratio;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.RequestGroup;
import org.hl7.fhir.r4.model.RequestGroup.RequestGroupActionComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.SimpleQuantity;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Timing;
import org.hl7.fhir.r4.model.Timing.TimingRepeatComponent;
import org.hl7.fhir.r4.model.Timing.UnitsOfTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;

public class HAPIFhirService {
	static String serverBaseUrl = "http://fhircs.globits.net:8082/fhir";
	public static IBaseResource parseResource (String body) {
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		IBaseResource resource = (Bundle)parser.parseResource(body);	
		return resource;
	}
	public static String encodeResourceToString(IBaseResource theResource ) {
		// Create a FHIR context
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		return parser.encodeResourceToString(theResource);
	}
	
	public static Bundle iBaseToBundle (IBaseResource iBaseResource) {
		Bundle bundle = new Bundle();
		bundle.setType(Bundle.BundleType.COLLECTION);
		bundle.addEntry().setFullUrl(iBaseResource.getIdElement().getValue()).setResource((Resource)iBaseResource);
		return bundle;
	}
	
	public static List<? extends IBaseResource> bundleToList(Bundle theBundle) {
		FhirContext ctx = FhirContext.forR4();
		 
		List<IBaseResource> list = new ArrayList<IBaseResource>();
		list.addAll(BundleUtil.toListOfResources(ctx, theBundle));
		 return list;
	}
	
	public static Bundle listToBundle(List<? extends IBaseResource> list) {
		Bundle bundle = new Bundle();

		bundle.setType(Bundle.BundleType.COLLECTION);
		
		for(int i=0;i<list.size();i++) {
			IBaseResource obj = list.get(i);
			bundle.addEntry().setFullUrl(obj.getIdElement().getValue()).setResource((Resource)obj);
		}

		return bundle;
	}
	
	 public static void updateBundle( Bundle bundle ) {
	      FhirContext ctx = FhirContext.forR4();
//	      serverBaseUrl = "http://localhost:8082/fhir";
	      
	      serverBaseUrl  = "http://fhircs.globits.net:8082/fhir";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      // Build a search and execute it
	     List<IBaseResource> list = new ArrayList<IBaseResource>();
	 	 list.addAll( BundleUtil.toListOfResources(ctx, bundle));
	 	 List<IBaseResource> response = client.transaction().withResources(list).execute();
	 	 	Integer pageIndex = 1;
	 	 	Integer pageSize = 10;
			Pageable pageable = PageRequest.of(pageIndex, pageSize);
			Page<IBaseResource> result = new PageImpl<IBaseResource>(response, pageable, response.size());
			System.out.println("----");
//	 	response.get(0).
	      //System.out.println("Responses: " + response.size());
	  }
	public static QuestionnaireResponse getQuestionnaireResponseById(String resourceId) {
	      FhirContext ctx = FhirContext.forR4();
	      serverBaseUrl = "https://lforms-fhir.nlm.nih.gov/baseR4/";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      String theUrl =  serverBaseUrl+"QuestionnaireResponse/"+resourceId;
	      QuestionnaireResponse response = client.fetchResourceFromUrl(QuestionnaireResponse.class, theUrl);
	      return response;
	  }
	public static Bundle getBundle() {
	      FhirContext ctx = FhirContext.forR4();
	      serverBaseUrl = "http://localhost:8082/fhir";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      List<IBaseResource> questionnaireResponse = new ArrayList<>();
	      // Build a search and execute it
	      Bundle response = client
	    		  .search()
	    		  .forResource(QuestionnaireResponse.class)
	    		  //.where(Patient.NAME.matches().value("smith"))
	    		  //.where(QuestionnaireResponse.)
	    		  .returnBundle(Bundle.class)
	    		  .execute();
	      // How many resources did we find?
	      System.out.println("Number of Responses: " + response.getTotal());
	      
	      questionnaireResponse.addAll(BundleUtil.toListOfResources(ctx, response));
	      System.out.println("Search result: " + ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(response));
	      return response;
	  }
	
	public static Bundle convertToBundle(QuestionnaireResponse qr) {
		Bundle result = new Bundle();
		CarePlan care = new CarePlan();
//		care.getAddresses()
		List<IBaseResource> resources = new ArrayList<IBaseResource>(); 
		List<QuestionnaireResponseItemComponent> list = qr.getItem();
		 Calendar calendar = Calendar.getInstance();
		for (QuestionnaireResponseItemComponent item : list) {
			if(item.getLinkId()!=null && item.getLinkId().equals("patient-information")) { 
				Patient patient = new Patient();//Get or create new Patient (for testing create new patient)
				if(patient.getIdentifier()==null) {
					patient.setIdentifier(new ArrayList<Identifier>());
				}
				if(patient.getAddress()==null) {
					patient.setAddress(new ArrayList<Address>());
				}
				List<QuestionnaireResponseItemComponent> listChild =item.getItem();
				for (QuestionnaireResponseItemComponent child : listChild) {
					if(child.getLinkId()!=null && child.getLinkId().equals("identification")) {
						
						List<Identifier> listiDentifier = new ArrayList<Identifier>();
						if( child.getAnswer()!= null && child.getAnswer().size() >0 && child.getAnswer().get(0).getItem() != null && child.getAnswer().get(0).getItem().size() >0) {
							for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child.getAnswer().get(0).getItem()) {
								Identifier identifier = new Identifier();

								if(questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent.getLinkId().equals("passport_nr")) {
									identifier.setUse(Identifier.IdentifierUse.OFFICIAL);
									if(questionnaireResponseItemComponent.getAnswer() != null && questionnaireResponseItemComponent.getAnswer().size() >0 ) {
										identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
									}
				
								}else if(questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent.getLinkId().equals("national_id_nr")) {
									identifier.setUse(Identifier.IdentifierUse.USUAL);
									if(questionnaireResponseItemComponent.getAnswer() != null && questionnaireResponseItemComponent.getAnswer().size() >0 ) {
										identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
									}
				
								}else if(questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent.getLinkId().equals("health_insurance_nr")) {
									identifier.setUse(Identifier.IdentifierUse.SECONDARY);
									if(questionnaireResponseItemComponent.getAnswer() != null && questionnaireResponseItemComponent.getAnswer().size() >0 ) {
										identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
									}
				
								}
								identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
								listiDentifier.add(identifier);
							}
						}
						
						patient.setIdentifier(listiDentifier);
					}else if(child.getLinkId()!=null && child.getLinkId().equals("name")) {
						HumanName humanName = new HumanName();
						humanName.setText(child.getAnswer().get(0).getValueStringType().asStringValue());
						patient.getName().add(humanName);
						System.out.println(child.getAnswer().get(0).getValueStringType());
					}else if(child.getLinkId()!=null && child.getLinkId().equals("ethnicity")) {
						
						System.out.println("ethnicity:"+child.getId());
					}else if(child.getLinkId()!=null && child.getLinkId().equals("gender")) {
//						patient.getGender();
//						AdministrativeGender.fromCode(child.getAnswer().get(0).getValueCoding().getCode());
						patient.setGender(AdministrativeGender.fromCode(child.getAnswer().get(0).getValueCoding().getCode()));
						System.out.println(child.getAnswer().get(0).getValueCoding().getCode());
					}else if(child.getLinkId()!=null && child.getLinkId().equals("birth_year")) {
						 if(child.getAnswer() != null && child.getAnswer().size() >0 && child.getAnswer().get(0).getValueIntegerType().getValue() > 1990) {
							 try {
								Date date = new SimpleDateFormat("yyyy").parse(child.getAnswer().get(0).getValueIntegerType().getValue()+"");
								patient.setBirthDate(date);
							} catch (FHIRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 };
                         
//						if(child.getAnswer() != null ) {
//							System.out.println(child.getAnswer().get(0).getValueIntegerType().asStringValue());
//						}
						 
						 
					}else if(child.getLinkId()!=null && child.getLinkId().equals("residence")) {
						List<Address> listAddress = new ArrayList<Address>();
						if( child.getAnswer()!= null && child.getAnswer().size() >0 && child.getAnswer().get(0).getItem() != null && child.getAnswer().get(0).getItem().size() >0) {
							for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child.getAnswer().get(0).getItem()) {
								Address address = new Address();
								if(questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent.getLinkId().equals("current")) {
									address.setUse(Address.AddressUse.fromCode("temp"));
									if(questionnaireResponseItemComponent.getAnswer() != null && questionnaireResponseItemComponent.getAnswer().size() >0 ) {
										address.setText(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
									}
				
								}else if(questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent.getLinkId().equals("permanent")) {
									address.setUse(Address.AddressUse.fromCode("home"));
									if(questionnaireResponseItemComponent.getAnswer() != null && questionnaireResponseItemComponent.getAnswer().size() >0 ) {
										address.setText(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
									}
								}
								
								listAddress.add(address);
							}
						}
					}
				}
				result.addEntry()
				   .setFullUrl(patient.getIdElement().getValue())
				   .setResource(patient);
			}
			if(item.getLinkId()!=null && item.getLinkId().equals("/54126-8/21112-8")) {
				if(item.getAnswer() != null && item.getAnswer().size() >0) {
					Date date = item.getAnswer().get(0).getValueDateType().getValue();
					Patient patient = new Patient();
					result.addEntry()
					   .setFullUrl(qr.getIdElement().getValue())
					   .setResource(qr);
				}
				
			}
			if(item.getLinkId()!=null && item.getLinkId().equals("risk_factors")) {
				Condition condition = new Condition();
				List<QuestionnaireResponseItemComponent> listChild =item.getItem();
				for (QuestionnaireResponseItemComponent child : listChild) {
					List<CodeableConcept> category = new ArrayList<CodeableConcept>();
					if(child.getLinkId()!=null && child.getLinkId().equals("risk_population")) {
						
						CodeableConcept codeableConcept = new CodeableConcept();
						codeableConcept.setText("risk_population");
						if(codeableConcept.getCoding()==null) {
							codeableConcept.setCoding(new ArrayList<Coding>());
						}
						Coding coding = new Coding();
						if(child.getAnswer() != null && child.getAnswer().size() >0 ) {
							coding.setCode(child.getAnswer().get(0).getValueCoding().getCode());
							coding.setDisplay(child.getAnswer().get(0).getValueCoding().getDisplay());
						}
						codeableConcept.getCoding().add(coding);
						category.add(codeableConcept);
					}else if(child.getLinkId()!=null && child.getLinkId().equals("risk_behavior")) {
						
						CodeableConcept codeableConcept = new CodeableConcept();
						codeableConcept.setText("Risk Behavior");
						if(codeableConcept.getCoding()==null) {
							codeableConcept.setCoding(new ArrayList<Coding>());
						}
						Coding coding = new Coding();
						if(child.getAnswer() != null && child.getAnswer().size() >0 ) {
							coding.setCode(child.getAnswer().get(0).getValueCoding().getCode());
							coding.setDisplay(child.getAnswer().get(0).getValueCoding().getDisplay());
						}
						codeableConcept.getCoding().add(coding);
						category.add(codeableConcept);
					}else if(child.getLinkId()!=null && child.getLinkId().equals("transmssion_route")) {
						
						CodeableConcept codeableConcept = new CodeableConcept();
						codeableConcept.setText("Transmission Route");
						if(codeableConcept.getCoding()==null) {
							codeableConcept.setCoding(new ArrayList<Coding>());
						}
						Coding coding = new Coding();
						if(child.getAnswer() != null && child.getAnswer().size() >0 ) {
							coding.setCode(child.getAnswer().get(0).getValueCoding().getCode());
							coding.setDisplay(child.getAnswer().get(0).getValueCoding().getDisplay());
						}
						codeableConcept.getCoding().add(coding);
						category.add(codeableConcept);
					}
				}
			}
			
		}
		result.setType(Bundle.BundleType.COLLECTION);

		
		return result;
	}

	 public static MethodOutcome saveResource(Resource resource) {
		 //serverBaseUrl = "http://hapi.fhir.org/baseR4";
		 //serverBaseUrl = "http://fhir.globits.net:8082/fhir";
	      FhirContext ctx = FhirContext.forR4();
	      
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);

	      // Use the client to store a new resource instance
	      MethodOutcome outcome = client
	         .update()
	         .resource(resource)
	         .execute();

	      // Print the ID of the newly created resource
	      System.out.println(outcome.getId());
	      return outcome;
	  }	
	 
	 public static MethodOutcome createResource(Resource resource) {
		 //serverBaseUrl = "http://hapi.fhir.org/baseR4";
		 //serverBaseUrl = "http://fhir.globits.net:8082/fhir";
	      FhirContext ctx = FhirContext.forR4();
	      
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);

	      // Use the client to store a new resource instance
	      MethodOutcome outcome = client
	         .create()
	         .resource(resource)
	         .execute();

	      // Print the ID of the newly created resource
	      System.out.println(outcome.getId());
	      return outcome;
	  }	
	 
	private static void createTestHivPatient() {
		FhirContext ctx = FhirContext.forR4();
		// Create a patient
		HivPatient patient = new HivPatient();
		patient.setId("Patient/A1333");
		patient.addIdentifier().setSystem("urn:mrns").setValue("253345");

		// Set the reference, and manually add the contained resource
		patient.getManagingOrganization().setReference("#localOrganization");
		patient.setName(new ArrayList<HumanName>());
		HumanName name = new HumanName();
		name.setFamily("Nguyen");
		name.setGiven(new ArrayList<StringType>());
		name.getGiven().add(new StringType("Van Anh-1"));
		patient.getName().add(name);
		
		Identifier passport = new Identifier();
		passport.setType(new CodeableConcept());
		Coding ppn = new Coding(HivConst.PassportIdentifierNamingSystem, "NSPassportID", "Naming System - Passport identifiers");
		passport.getType().addCoding(ppn);
		passport.setSystem(HivConst.PassportIdentifierNamingSystem);
		
		passport.setValue("A1029323829");
		patient.addIdentifier(passport);
		
		Identifier insuranceNumber = new Identifier();
		insuranceNumber.setType(new CodeableConcept());
		Coding insuranceNumberCode = new Coding("https://openhie.org/sid/ns-insurance-id", "NSInsuranceID", "Naming System - Insurance identifiers");
		insuranceNumber.getType().addCoding(insuranceNumberCode);
		insuranceNumber.setSystem("http://openhie.org/fhir/NamingSystem/ns-insurance-id");
		insuranceNumber.setValue("INS-123456");
		
		patient.addIdentifier(insuranceNumber);

		Identifier arvNumber = new Identifier();
		arvNumber.setType(new CodeableConcept());
		Coding arvNumberCode = new Coding("http://openhie.org/fhir/NamingSystem/ns-art-id", "NSARTID", "ARV treatment identifiers");
		arvNumber.getType().addCoding(arvNumberCode);
		arvNumber.setSystem("http://openhie.org/fhir/NamingSystem/ns-art-id");
		patient.addIdentifier(arvNumber);
		saveResource(patient);
		String encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
		System.out.println(encoded);
	}
	public static Observation getObsevationResource(String theUrl) {
	      FhirContext ctx = FhirContext.forR4();
	      //String serverBaseUrl = "http://hapi.fhir.org/baseR4";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      Observation response= client.fetchResourceFromUrl(Observation.class, theUrl);
	      return response;
	  }
	
	public static IResource getResource(String theUrl) {
	      FhirContext ctx = FhirContext.forR4();
	      //String localServerBaseUrl = "http://hapi.fhir.org/baseR4";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      IResource response= client.fetchResourceFromUrl(IResource.class, theUrl);
	      
//	      IGenericClient client1 = ctx.newRestfulGenericClient(serverBaseUrl);
//	      response= client1.fetchResourceFromUrl(PlanDefinition.class, theUrl);
//	      //String encoded = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(response);
//		  //System.out.println(encoded);		  
//	      MethodOutcome outcome = client1.create().resource(response).execute();
//	      System.out.println(outcome.getId());
	      return response;
	  }
	
	private static void createTestRegimen() {
		HivRegimen regimen = new HivRegimen();
		regimen.setName("AZT + 3TC + NVP");
		List<PlanDefinitionActionComponent>  actions= new ArrayList<PlanDefinitionActionComponent>();
		PlanDefinitionActionComponent action = new PlanDefinitionActionComponent();
		action.setTiming(DateTimeType.now());
//		MedicationRequest request = new MedicationRequest();
//		Dosage dosage = new Dosage();
//		List<DosageDoseAndRateComponent> doseAndRates=new ArrayList<Dosage.DosageDoseAndRateComponent>();
//		DosageDoseAndRateComponent ddrc = new DosageDoseAndRateComponent();
//		ddrc.setType(new CodeableConcept().addCoding(new Coding("http://terminology.hl7.org/CodeSystem/dose-rate-type","ordered","Ordered")));
//		doseAndRates.add(ddrc);
//		dosage.setDoseAndRate(doseAndRates);
//		dosage.setText("1 tablet oral 2 time daily");
//		SimpleQuantity quatity = new SimpleQuantity();
//		quatity.setCode("1");
//		quatity.setValue(1.0);
//		quatity.setUnit("{tbl}");
//		
//		ddrc.setDose(quatity);
//		request.addDosageInstruction(dosage);
//		request.setId("test");
//		regimen.addContained(request);
//		MethodOutcome outcome = saveResource(request);
//		List<Reference> list = new ArrayList<Reference>();
//		request.setId(outcome.getId());
//		
//		list.add(new Reference(request));
//		regimen.setTestConditions(list);
//		regimen.addContained(request);
//		action.setId("#metoprololTartrate25Prescription");
//		actions.add(action);		
//		regimen.setAction(actions);		
//		saveResource(regimen);
		ActivityDefinition ad =  new ActivityDefinition();
		ad.setId("referralToCardiologyConsult");
		ad.setDescription("Consider other consult modalities which might be available (e.g., e-consult or other rapidly iterative consult method. A simple consult is included here as the overarching clinical intent.");
		ad.setKind(ActivityDefinitionKind.SERVICEREQUEST);
		ActivityDefinitionParticipantComponent participant = new ActivityDefinitionParticipantComponent();
		participant.setType(ActivityParticipantType.PRACTITIONER);
		ad.addParticipant(participant);
		regimen.addContained(ad);
		
		ad =  new ActivityDefinition();
		ad.setId("metoprololTartrate25Prescription");
		ad.setDescription("Consider other consult modalities which might be available (e.g., e-consult or other rapidly iterative consult method. A simple consult is included here as the overarching clinical intent.");
		ad.setKind(ActivityDefinitionKind.MEDICATIONREQUEST);
		Reference ref = new Reference();
		ref.setReference("#metoprololTartrate25Medication");
		ad.setProduct(ref);
		participant.setType(ActivityParticipantType.PRACTITIONER);
		ad.addParticipant(participant);
		Dosage dosage = new Dosage();
		dosage.setText("1 tablet oral 2 time daily");
		
		/*Set Time Period for dosage*/
		
		Timing timing =new Timing();
		TimingRepeatComponent repeat = new TimingRepeatComponent();
		repeat.setFrequency(2);
		repeat.setPeriod(1);
		repeat.setPeriodUnit(UnitsOfTime.D);
		timing.setRepeat(repeat);
		dosage.setTiming(timing);
		/*Set route for using*/
		dosage.setRoute(new CodeableConcept());
		dosage.getRoute().addCoding(new Coding(null,"26643006", "Oral route (qualifier value)"));
		/*Set Dosage and rate */
		
		List<DosageDoseAndRateComponent> doseAndRates=new ArrayList<Dosage.DosageDoseAndRateComponent>();
		DosageDoseAndRateComponent ddrc = new DosageDoseAndRateComponent();
		ddrc.setType(new CodeableConcept());
		ddrc.getType().addCoding(new Coding("http://terminology.hl7.org/CodeSystem/dose-rate-type","ordered","Ordered"));
		SimpleQuantity quatity = new SimpleQuantity();
		quatity.setCode("1");
		quatity.setValue(1.0);
		quatity.setUnit("{tbl}");
		ddrc.setDose(quatity);	
		dosage.addDoseAndRate(ddrc);
		ad.addDosage(dosage);
		regimen.addContained(ad);
		
		Medication medication = new Medication();
		medication.setId("metoprololTartrate25Medication");
		medication.setCode(new CodeableConcept());
		Coding coding = new Coding("http://www.nlm.nih.gov/research/umls/rxnorm", "866426", null);
		medication.getCode().setText("Metoprolol Tartrate 25 MG");
		medication.getCode().addCoding(coding);
		
		medication.setForm(new CodeableConcept());
		medication.getForm().addCoding(new Coding("http://snomed.info/sct", "385055001", "Tablet dose form"));
		medication.getForm().setText("Tablet dose form");
		MedicationIngredientComponent ingredient = new MedicationIngredientComponent();
		ref = new Reference();
		ref.setReference("#metoprololTartrate25Substance");
		ingredient.setItem(ref);
		ingredient.setStrength(new Ratio());
		Quantity numerator =new Quantity();
		numerator.setValue(25);
		numerator.setUnit("mg");
		ingredient.getStrength().setDenominator(numerator);
		medication.addIngredient(ingredient);
		regimen.addContained(medication);
		
		IParser jsonR4 = FhirContext.forR4().newJsonParser().setPrettyPrint(true);
	    String json = jsonR4.encodeResourceToString(regimen);
	    System.out.println(json);		
	    
	    saveResource(regimen);
	}
	
	public static void createLabTestObservation() {
		Observation obs = new Observation();
		obs.setSpecimen(new Reference());
		HIVSpecimen specimen = new HIVSpecimen();
		specimen.setSpecimenCollectionPlace(new StringType("Kiem tra xem tinh hinh the nao"));
		obs.getSpecimen().setResource(specimen);
		IParser jsonR4 = FhirContext.forR4().newJsonParser().setPrettyPrint(true);
	    String json = jsonR4.encodeResourceToString(obs);

	    System.out.println(json);	
	    Observation ob = (Observation)jsonR4.parseResource(json);
	    
	    MethodOutcome outcome = saveResource(obs);
	    ob = (Observation)getObsevationResource(outcome.getId().toString());
	    
	    Specimen specimen1 = (Specimen)ob.getSpecimen().getResource();
	    String jsonHIVSpecimen = jsonR4.encodeResourceToString(specimen1);
	    specimen = jsonR4.parseResource(HIVSpecimen.class, jsonHIVSpecimen);
	    System.out.println("Test:"+specimen.getSpecimenCollectionPlace());
	}
	public static void main(String[] args) {
//		createLabTestObservation();
		//createRegimen();
		createTestHivPatient();
//		getResource("http://fhir.globits.net:8082/fhir/PlanDefinition/66");
//		HivRegimen regimen = new HivRegimen();
//		regimen.setName("AZT + 3TC + NVP");
//
//		List<PlanDefinitionActionComponent>  actions= new ArrayList<PlanDefinition.PlanDefinitionActionComponent>();
//		PlanDefinitionActionComponent action = new PlanDefinitionActionComponent();
//		action.setTiming(DateTimeType.now());
//		MedicationRequest request = new MedicationRequest();
//		action.setId("#metoprololTartrate25Prescription");
//		actions.add(action);
//		
//		regimen.setAction(actions);
//
//		Medication metoprololTartrate50Prescription = new Medication();
//		metoprololTartrate50Prescription.setId("#metoprololTartrate50Prescription");
		//metoprololTartrate50Prescription.setDescription("Test");

		//ActivityDefinitionKind kind=ActivityDefinitionKind.SERVICEREQUEST;
		//metoprololTartrate50Prescription.setKind(kind);
//		regimen.setId("detemirMedication");
//		regimen.addContained(metoprololTartrate50Prescription);

//		IParser jsonR4 = FhirContext.forR4().newJsonParser().setPrettyPrint(true);
//	    String json = jsonR4.encodeResourceToString(regimen);	    
//	    System.out.println(json);
	    
//	    json = jsonR4.encodeResourceToString(metoprololTartrate50Prescription);
//	    System.out.println(json);
//		QuestionnaireResponse qr = getQuestionnaireResponseById("5770415");
//		convertToBundle(qr);
//		HAPIFhirService.updateBundle(convertToBundle(qr));
//		FhirContext ctx = FhirContext.forR4();
//		IParser parser = ctx.newJsonParser();
//		// We'll populate this list
//		List<IBaseResource> patients = new ArrayList<>();
//
//		// We'll do a search for all Patients and extract the first page
//		Bundle bundle = new Bundle();
//		QuestionnaireResponse obj = new QuestionnaireResponse();
//		obj.setAuthored(new Date());
//
//		bundle.setType(Bundle.BundleType.COLLECTION);
//
//
//		bundle.addEntry()
//		   .setFullUrl(obj.getIdElement().getValue())
//		   .setResource(obj);
//		bundle.addEntry()
//		   .setFullUrl(obj.getIdElement().getValue())
//		   .setResource(obj);
//		String json = parser.encodeResourceToString(bundle);
//		System.out.println(json);
//		 List<QuestionnaireResponse>  list = (List<QuestionnaireResponse>)bundleToList(bundle);
//		 
//		 Bundle bundle1 =listToBundle(list);
//		 String json1= parser.encodeResourceToString(bundle);
//		 System.out.println(json1);
		//getBundlePatient();
		

		
	}
	
	
	
}

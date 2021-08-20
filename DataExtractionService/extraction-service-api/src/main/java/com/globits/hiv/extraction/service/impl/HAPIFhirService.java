package com.globits.hiv.extraction.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.Specimen.SpecimenCollectionComponent;
import org.hl7.fhir.r4.model.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.HivConst;
import com.globits.hiv.extraction.dto.CoMorbidityDto;
import com.globits.hiv.extraction.utils.QRConvertUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;

public class HAPIFhirService {
	static String serverBaseUrl = HivConst.serverFhirUrl;
	static FhirContext ctx = FhirContext.forR4();
	static IParser jsonParser = ctx.newJsonParser();
	public static IBaseResource parseResource(String body) {
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		IBaseResource resource = (Bundle) parser.parseResource(body);
		return resource;
	}

	//
	public static MethodOutcome savePatient(Patient pat) {
		FhirContext ctx = FhirContext.forR4();

		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(pat).execute();

		// Print the ID of the newly created resource
		System.out.println(outcome.getId());
		return outcome;
	}

	// Observation
	public static MethodOutcome saveObservation(Observation pat) {
		FhirContext ctx = FhirContext.forR4();

		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(pat).execute();

		// Print the ID of the newly created resource
		System.out.println(outcome.getId());
		return outcome;
	}

	///////
	public static Bundle getPatientBundle(int pageIndex, int pageSize,String text) {
		int pagesoffset= (pageIndex-1)*pageSize;
	      FhirContext ctx = FhirContext.forR4();
	      //serverBaseUrl="http://hapi.fhir.org/baseR4";
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      // Build a search and execute it
	      Bundle response = client
	    		  .search()
	    		  .forResource(HivPatient.class)
	    		  .where(Patient.NAME.matchesExactly().value(text))
	    		  .totalMode(SearchTotalModeEnum.ACCURATE)
	    		  .count(pageSize)
	    		  .offset(pagesoffset)
	    		  .returnBundle(Bundle.class)
	    		  .execute();
	      return response;
	  }
	///////
	public static MethodOutcome saveCondition(Condition cond) {
		FhirContext ctx = FhirContext.forR4();

		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(cond).execute();

		// Print the ID of the newly created resource
		System.out.println(outcome.getId());
		return outcome;
	}

	/////
	public static MethodOutcome saveCarePlan(CarePlan carePlan) {
		FhirContext ctx = FhirContext.forR4();

		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(carePlan).execute();

		// Print the ID of the newly created resource
		System.out.println(outcome.getId());
		return outcome;
	}

	/////
	public static MethodOutcome saveSpecimen(Specimen cond) {
		FhirContext ctx = FhirContext.forR4();

		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(cond).execute();

		// Print the ID of the newly created resource
		System.out.println(outcome.getId());
		return outcome;
	}

	////
	public static MethodOutcome saveOrganization(Organization cond) {
		FhirContext ctx = FhirContext.forR4();

		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(cond).execute();

		// Print the ID of the newly created resource
		System.out.println(outcome.getId());
		return outcome;
	}

	public static String encodeResourceToString(IBaseResource theResource) {
		// Create a FHIR context
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		return parser.encodeResourceToString(theResource);
	}

	public static Bundle iBaseToBundle(IBaseResource iBaseResource) {
		Bundle bundle = new Bundle();
		bundle.setType(Bundle.BundleType.COLLECTION);
		bundle.addEntry().setFullUrl(iBaseResource.getIdElement().getValue()).setResource((Resource) iBaseResource);
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

		for (int i = 0; i < list.size(); i++) {
			IBaseResource obj = list.get(i);
			bundle.addEntry().setFullUrl(obj.getIdElement().getValue()).setResource((Resource) obj);
		}

		return bundle;
	}

	/////
	public static Bundle savePatientTransaction (HivPatient patient, List<? extends Resource> children,List<CoMorbidityDto>listCoMorbidity) {
		HivPatient hivPatient = null;
		Bundle bundle = new Bundle();
		if(patient != null) {
			if(patient.getIdentifier() != null && patient.getIdentifier().size()>0) {
				Patient hl7Patient = null;
				for (Identifier item : patient.getIdentifier()) {
					if(item.getSystem()!=null) {
					Bundle bundleTest = QRConvertUtil.findPatientDuplicatePatient(item.getSystem(),
							item.getValue());
					hl7Patient = (Patient) bundleTest.getEntryFirstRep().getResource();
					if(hl7Patient != null) {
						String json = jsonParser.encodeResourceToString(hl7Patient);
						hivPatient = jsonParser.parseResource(HivPatient.class, json);
					}
					}
					if(hivPatient != null) {
						hivPatient.setName(patient.getName());
						hivPatient.setAddress(patient.getAddress());
						hivPatient.setIdentifier(patient.getIdentifier());
						hivPatient.setBirthDate(patient.getBirthDate());
						break;
					}
				}
			}
		}
		if(hivPatient != null) {
//			IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
//			MethodOutcome outcome = client.update().resource(hivPatient).execute();
			
			System.out.print(Patient.class.getSimpleName());
			  bundle.setType(Bundle.BundleType.TRANSACTION);
			  bundle.addEntry()
			     .setFullUrl(hivPatient.getIdElement().getValue())
			     .setResource(hivPatient)
			     .getRequest()
			        .setUrl(Patient.class.getSimpleName()+"/"+hivPatient.getIdElement().getIdPart())
			        .setMethod(Bundle.HTTPVerb.PUT);
			  
			
		}else {
		  // Create a bundle that will be used as a transaction	
		  patient.setId(IdType.newRandomUuid());		  
		  bundle.setType(Bundle.BundleType.TRANSACTION);
		  bundle.addEntry()
		     .setFullUrl(patient.getIdElement().getValue())
		     .setResource(patient)
		     .getRequest()
		        .setUrl(Patient.class.getSimpleName())
		        .setMethod(Bundle.HTTPVerb.POST);

		
		
		if(children != null && children.size()>0) {
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
				
				
			}
		}
		
		if(listCoMorbidity != null && listCoMorbidity.size()>0) {
			for (CoMorbidityDto coMorbidity : listCoMorbidity) {
				CarePlan carePlan= null;
				Specimen specimen= null;
				if(coMorbidity.getCarePlan()!= null) {
					 carePlan = coMorbidity.getCarePlan();
					 carePlan.setId(IdType.newRandomUuid());
					carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					  .setFullUrl(carePlan.getIdElement().getValue())
					     .setResource(carePlan)
					     .getRequest()
					      .setUrl(CarePlan.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}else if(coMorbidity.getSpecimen()!=null) {
					specimen = coMorbidity.getSpecimen();
					specimen.setId(IdType.newRandomUuid());
					specimen.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					  .setFullUrl(specimen.getIdElement().getValue())
					     .setResource(specimen)
					     .getRequest()
					      .setUrl(Specimen.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}
				
				if(carePlan!= null&&coMorbidity.getObservation() != null) {
					Observation obs = coMorbidity.getObservation();
					obs.addBasedOn(new Reference("CarePlan"+"/"+carePlan.getIdElement().getValue()));
					obs.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					     .setResource(obs)
					     .getRequest()
					      .setUrl(Observation.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}
				if(specimen!= null&&coMorbidity.getObservation() != null) {
					Observation obs = coMorbidity.getObservation();
					Reference ref = new Reference();	
					ref.setReference("Specimen"+"/"+specimen.getIdElement().getValue());
					obs.setSpecimen(ref);
					obs.setSubject(new Reference(patient.getIdElement().getValue()));
					  bundle.addEntry()
					     .setResource(obs)
					     .getRequest()
					      .setUrl(Observation.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
				}
				
				
			}
		}
		}

		  // Save bundle which contains the Patient and list Resource to HAPI Server
		  FhirContext ctx = FhirContext.forR4();
		  IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		  Bundle resp = client.transaction().withBundle(bundle).execute();
		  return resp;
	}
	
	
	/////
	public static Bundle saveTestPatientTransaction(HivPatient patient, List<? extends Resource> children,
			List<? extends Resource> comorbidities) {
		// Create a bundle that will be used as a transaction
		patient.setId(IdType.newRandomUuid());
		Bundle bundle = new Bundle();
		bundle.setType(Bundle.BundleType.TRANSACTION);
		bundle.addEntry().setFullUrl(patient.getIdElement().getValue()).setResource(patient).getRequest()
				.setUrl(Patient.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
		if (children != null && children.size() > 0) {
			for (Resource r : children) {
				if (r.getResourceType().name().equals(Observation.class.getSimpleName())) {
					Observation obs = (Observation) r;
					obs.setSubject(new Reference(patient.getIdElement().getValue()));
					bundle.addEntry().setResource(obs).getRequest().setUrl(Observation.class.getSimpleName())
							.setMethod(Bundle.HTTPVerb.POST);
				} else if (r.getResourceType().name().equals(Condition.class.getSimpleName())) {
					Condition cond = (Condition) r;
					cond.setSubject(new Reference(patient.getIdElement().getValue()));
					bundle.addEntry().setResource(cond).getRequest().setUrl(Condition.class.getSimpleName())
							.setMethod(Bundle.HTTPVerb.POST);
				} else if (r.getResourceType().name().equals(CarePlan.class.getSimpleName())) {
					CarePlan carePlan = (CarePlan) r;
					carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
					bundle.addEntry().setResource(carePlan).getRequest().setUrl(CarePlan.class.getSimpleName())
							.setMethod(Bundle.HTTPVerb.POST);
				} else if (r.getResourceType().name().equals(PlanDefinition.class.getSimpleName())) {
					CarePlan carePlan = (CarePlan) r;
					carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
					bundle.addEntry().setResource(carePlan).getRequest().setUrl(PlanDefinition.class.getSimpleName())
							.setMethod(Bundle.HTTPVerb.POST);
				}

			}
		}
		
		///////
		if (comorbidities != null && comorbidities.size() > 0) {
			List<Observation> observationList = new ArrayList<Observation>();
			List<CarePlan> carePlanList = new ArrayList<CarePlan>();
			String specimenId = null;
			for (Resource r : comorbidities) {
				if (r.getResourceType().name().equals(Observation.class.getSimpleName())) {
					Observation obs = (Observation) r;
					observationList.add(obs);
				} else if (r.getResourceType().name().equals(CarePlan.class.getSimpleName())) {
					CarePlan carePlan = (CarePlan) r;
					carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
					bundle.addEntry().setResource(carePlan).getRequest().setUrl(CarePlan.class.getSimpleName())
							.setMethod(Bundle.HTTPVerb.POST);
					carePlanList.add(carePlan);
				}else if(r.getResourceType().name().equals(Specimen.class.getSimpleName())) {
					Specimen specimen = (Specimen) r;
					specimen.setSubject(new Reference(patient.getIdElement().getValue()));
					bundle.addEntry().setResource(specimen).getRequest().setUrl(Specimen.class.getSimpleName())
							.setMethod(Bundle.HTTPVerb.POST);
					specimenId = specimen.getIdElement().getValue();
				}

			}
			////
			if (observationList != null && observationList.size() > 0) {
				for (Observation observation : observationList) {
					if (observation.getCode() != null && observation.getCode().getCoding() != null
							&& observation.getCode().getCoding().size() > 0) {
						
						if(observation.getCode().getCoding().get(0).getSystem().equals(HivConst.VsDiagnosisObs)) {
							observation.setSubject(new Reference(patient.getIdElement().getValue()));
							observation.getSpecimen().setReference(specimenId);
							bundle.addEntry().setResource(observation).getRequest().setUrl(Observation.class.getSimpleName())
									.setMethod(Bundle.HTTPVerb.POST);
						}
						
						
						if (carePlanList != null && carePlanList.size() > 0) {
							for (CarePlan carePlan : carePlanList) {
								if (carePlan.getActivity() != null && carePlan.getActivity().size() > 0
										&& carePlan.getActivity().get(0).getDetail() != null) {
									if (carePlan.getActivity().get(0).getDetail().getCode() != null
											&& carePlan.getActivity().get(0).getDetail().getCode().getCoding() != null
											&& carePlan.getActivity().get(0).getDetail().getCode().getCoding()
													.size() > 0) {
										if (observation.getCode().getCoding().get(0).getSystem()
												.equals(carePlan.getActivity().get(0).getDetail().getCode().getCoding()
														.get(0).getSystem())) {
											observation.setSubject(new Reference(patient.getIdElement().getValue()));
											observation.addBasedOn(new Reference(carePlan.getIdElement().getValue()));
											bundle.addEntry().setResource(observation).getRequest().setUrl(Observation.class.getSimpleName())
													.setMethod(Bundle.HTTPVerb.POST);

										}

									}
								}
							}
						}
					}

				}
			}
		}
		// Save bundle which contains the Patient and list Resource to HAPI Server
		FhirContext ctx = FhirContext.forR4();
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		Bundle resp = client.transaction().withBundle(bundle).execute();
		return resp;
	}

	public static void updateBundle(Bundle bundle) {
		FhirContext ctx = FhirContext.forR4();
//	      serverBaseUrl = "http://localhost:8082/fhir";

		//serverBaseUrl = "http://fhir.globits.net:8082/fhir";
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		// Build a search and execute it
		List<IBaseResource> list = new ArrayList<IBaseResource>();
		list.addAll(BundleUtil.toListOfResources(ctx, bundle));
		List<IBaseResource> response = client.transaction().withResources(list).execute();
		Integer pageIndex = 1;
		Integer pageSize = 10;
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<IBaseResource> result = new PageImpl<IBaseResource>(response, pageable, response.size());
		System.out.println("----");
//	 	response.get(0).
		// System.out.println("Responses: " + response.size());
	}

	public static QuestionnaireResponse getQuestionnaireResponseById(String resourceId) {
		FhirContext ctx = FhirContext.forR4();
		serverBaseUrl = "https://lforms-fhir.nlm.nih.gov/baseR4/";
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		String theUrl = serverBaseUrl + "QuestionnaireResponse/" + resourceId;
		QuestionnaireResponse response = client.fetchResourceFromUrl(QuestionnaireResponse.class, theUrl);
		return response;
	}

	public static Bundle getBundle() {
		FhirContext ctx = FhirContext.forR4();
		//serverBaseUrl = "http://localhost:8082/fhir";
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		List<IBaseResource> questionnaireResponse = new ArrayList<>();
		// Build a search and execute it
		Bundle response = client.search().forResource(QuestionnaireResponse.class)
				// .where(Patient.NAME.matches().value("smith"))
				// .where(QuestionnaireResponse.)
				.returnBundle(Bundle.class).execute();
		// How many resources did we find?
		System.out.println("Number of Responses: " + response.getTotal());

		questionnaireResponse.addAll(BundleUtil.toListOfResources(ctx, response));
		System.out
				.println("Search result: " + ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(response));
		return response;
	}

	public static Bundle convertToBundle(QuestionnaireResponse qr) {
		Bundle result = new Bundle();
		CarePlan care = new CarePlan();
//		care.getAddresses()
		List<IBaseResource> resources = new ArrayList<IBaseResource>();
		List<QuestionnaireResponseItemComponent> list = qr.getItem();
		Calendar calendar = Calendar.getInstance();
		HivPatient patient = new HivPatient();
		Observation hivDiagnosis = new Observation();
		List<Observation> hivRecencyTest = new ArrayList<Observation>();
		List<Condition> riskFactors = new ArrayList<Condition>();
		Observation cd4BeforeART = new Observation();
		Observation cd4DuringART = new Observation();
		Observation vl4DuringART = new Observation();
		Observation drugResistanceTest = new Observation();
		CarePlan tbt = new CarePlan();
		CarePlan tbTreatment = new CarePlan();
		Observation hbv = new Observation();
		Observation hcv = new Observation();
		Specimen specimenHivDiagnosis = new Specimen();
		Specimen specimenRapidTest = new Specimen();
//		Specimen specimenVLTest = new Specimen();
		for (QuestionnaireResponseItemComponent item : list) {

			if (item.getLinkId() != null && item.getLinkId().equals("patient-information")) {
				// Get or create new Patient (for testing create new patient)
				if (patient.getIdentifier() == null) {
					patient.setIdentifier(new ArrayList<Identifier>());
				}
				if (patient.getAddress() == null) {
					patient.setAddress(new ArrayList<Address>());
				}
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				for (QuestionnaireResponseItemComponent child : listChild) {
					if (child.getLinkId() != null && child.getLinkId().equals("identification")) {

						List<Identifier> listiDentifier = new ArrayList<Identifier>();
						if (child.getItem() != null && child.getItem().size() > 0) {
							for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
									.getItem()) {
								Identifier identifier = new Identifier();

								if (questionnaireResponseItemComponent.getLinkId() != null
										&& questionnaireResponseItemComponent.getLinkId().equals("passport_nr")) {
									identifier.setUse(Identifier.IdentifierUse.OFFICIAL);
									if (questionnaireResponseItemComponent.getAnswer() != null
											&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
										identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0)
												.getValueStringType().asStringValue());
									}

								} else if (questionnaireResponseItemComponent.getLinkId() != null
										&& questionnaireResponseItemComponent.getLinkId().equals("national_id_nr")) {
									identifier.setUse(Identifier.IdentifierUse.USUAL);
									if (questionnaireResponseItemComponent.getAnswer() != null
											&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
										identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0)
												.getValueStringType().asStringValue());
									}

								} else if (questionnaireResponseItemComponent.getLinkId() != null
										&& questionnaireResponseItemComponent.getLinkId()
												.equals("health_insurance_nr")) {
									identifier.setUse(Identifier.IdentifierUse.SECONDARY);
									if (questionnaireResponseItemComponent.getAnswer() != null
											&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
										identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0)
												.getValueStringType().asStringValue());
									}

								}
								identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0)
										.getValueStringType().asStringValue());
								listiDentifier.add(identifier);
							}
						}
						patient.setIdentifier(listiDentifier);
					} else if (child.getLinkId() != null && child.getLinkId().equals("name")) {
						HumanName humanName = new HumanName();
						if (child.getAnswer() != null & child.getAnswer().size() > 0) {
							humanName.setText(child.getAnswer().get(0).getValueStringType().asStringValue());
							patient.getName().add(humanName);
							System.out.println(child.getAnswer().get(0).getValueStringType());
						}

					} else if (child.getLinkId() != null && child.getLinkId().equals("ethnicity")) {
						/// chưa có Extension
						System.out.println("ethnicity:" + child.getId());
					} else if (child.getLinkId() != null && child.getLinkId().equals("gender")) {
						if (child.getAnswer().size() > 0) {
							if (child.getAnswer().get(0).getValueCoding().getCode().equals("male")
									|| child.getAnswer().get(0).getValueCoding().getCode().equals("male")) {
								patient.setGender(AdministrativeGender
										.fromCode(child.getAnswer().get(0).getValueCoding().getCode()));
							} else {
								patient.setGender(AdministrativeGender.fromCode("unknown"));
							}
							System.out.println(child.getAnswer().get(0).getValueCoding().getCode());
						}

					} else if (child.getLinkId() != null && child.getLinkId().equals("birth_year")) {
						if (child.getAnswer() != null && child.getAnswer().size() > 0
								&& child.getAnswer().get(0).getValueIntegerType().getValue() > 1900) {
							try {
								Date date = new SimpleDateFormat("yyyy")
										.parse(child.getAnswer().get(0).getValueIntegerType().getValue() + "");
								patient.setBirthDate(date);
							} catch (FHIRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						;

					} else if (child.getLinkId() != null && child.getLinkId().equals("residence")) {
						List<Address> listAddress = new ArrayList<Address>();
						if (child.getItem() != null && child.getItem().size() > 0) {
							for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
									.getItem()) {
								Address address = new Address();
								if (questionnaireResponseItemComponent.getLinkId() != null
										&& questionnaireResponseItemComponent.getLinkId().equals("current")) {
									address.setUse(Address.AddressUse.fromCode("temp"));
									if (questionnaireResponseItemComponent.getAnswer() != null
											&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
										address.setText(questionnaireResponseItemComponent.getAnswer().get(0)
												.getValueStringType().asStringValue());
									}

								} else if (questionnaireResponseItemComponent.getLinkId() != null
										&& questionnaireResponseItemComponent.getLinkId().equals("permanent")) {
									address.setUse(Address.AddressUse.fromCode("home"));
									if (questionnaireResponseItemComponent.getAnswer() != null
											&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
										address.setText(questionnaireResponseItemComponent.getAnswer().get(0)
												.getValueStringType().asStringValue());
									}
								}

								listAddress.add(address);
								patient.setAddress(listAddress);
							}
						}
					}

				}

			}

			//// risk_factors
			if (item.getLinkId() != null && item.getLinkId().equals("risk_factors")) {

				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				for (QuestionnaireResponseItemComponent child : listChild) {
					CodeableConcept codeableConcept = new CodeableConcept();
					Condition condition = new Condition();
					if (child.getLinkId() != null && child.getLinkId().equals("risk_population")) {

						codeableConcept.setText("risk_population");
						if (codeableConcept.getCoding() == null) {
							codeableConcept.setCoding(new ArrayList<Coding>());
						}
						Coding coding = new Coding();
						if (child.getAnswer() != null && child.getAnswer().size() > 0) {
							codeableConcept.addCoding(
									new Coding("http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-population",
											child.getAnswer().get(0).getValueCoding().getCode(),
											child.getAnswer().get(0).getValueCoding().getDisplay()));
						}

					} else if (child.getLinkId() != null && child.getLinkId().equals("risk_behavior")) {

						codeableConcept.setText("Risk Behavior");
						if (codeableConcept.getCoding() == null) {
							codeableConcept.setCoding(new ArrayList<Coding>());
						}

						if (child.getAnswer() != null && child.getAnswer().size() > 0) {
							codeableConcept.addCoding(new Coding(
									"http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-risk-behavior",
									child.getAnswer().get(0).getValueCoding().getCode(),
									child.getAnswer().get(0).getValueCoding().getDisplay()));
						}
					} else if (child.getLinkId() != null && child.getLinkId().equals("transmssion_route")) {
						codeableConcept.setText("Transmission Route");
						if (codeableConcept.getCoding() == null) {
							codeableConcept.setCoding(new ArrayList<Coding>());
						}
						if (child.getAnswer() != null && child.getAnswer().size() > 0) {
							codeableConcept.addCoding(new Coding(
									"http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-transmission-route",
									child.getAnswer().get(0).getValueCoding().getCode(),
									child.getAnswer().get(0).getValueCoding().getDisplay()));
						}
					}
					riskFactors.add(condition);
				}
			}
			/// hiv-diagnosis
			if (item.getLinkId() != null && item.getLinkId().equals("hiv-diagnosis")) {

				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {
						if (child.getLinkId() != null && child.getLinkId().equals("date-of-confirmation")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								try {
									Date date = new SimpleDateFormat("yyyy")
											.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
									hivDiagnosis.setIssued(date);
								} catch (FHIRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							;
						} else if (child.getLinkId() != null && child.getLinkId().equals("confirming-lab")) {

							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								List<Reference> listRefOrg = new ArrayList<Reference>();
								Reference refOrg = new Reference();
								Organization org = new Organization();
								org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
								MethodOutcome outcome = saveOrganization(org);
								refOrg.setType(Organization.class.getName());
								refOrg.setDisplay(org.getName());
								listRefOrg.add(refOrg);
								hivDiagnosis.setPerformer(listRefOrg);
							}
						} else if (child.getLinkId() != null
								&& child.getLinkId().equals("date-of-specimen-collection")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								Reference specimenRef = new Reference();
								SpecimenCollectionComponent specimenCollection = new SpecimenCollectionComponent();
								DateTimeType dateType = new DateTimeType(
										child.getAnswer().get(0).getValueDateType().getValue());
								specimenCollection.setCollected(dateType);
								specimenHivDiagnosis.setCollection(specimenCollection);
								specimenRef.setType(Specimen.class.getName());
								specimenRef.setReference(specimenHivDiagnosis.getId());
								specimenRef.setDisplay(dateType.getValueAsString());
								hivDiagnosis.setSpecimen(specimenRef);
							}
							;
						} else if (child.getLinkId() != null
								&& child.getLinkId().equals("place-of-specimen-collection")) {

						}
					}

				}
			}
			// hiv-recency-test
			if (item.getLinkId() != null && item.getLinkId().equals("hiv-recency-test")) {
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {
						Date date = null;
						String testResult = "";
						if (child.getLinkId() != null && child.getLinkId().equals("rapid-test")) {
							Observation rapidTest = new Observation();
							rapidTest.setIssued(date);

							CodeableConcept rapidTestCode = new CodeableConcept();
							Coding coding = new Coding();
							List<Coding> listCoding = new ArrayList<Coding>();
							coding.setSystem(
									"http://openhie.org/fhir/hiv-casereporting/ValueSet/hiv-rapid-test-results");
							coding.setDisplay("HIV Rapid Test Results");
							listCoding.add(coding);
							rapidTest.setCode(rapidTestCode);
							rapidTestCode.setCoding(listCoding);
							if (child.getItem() != null && child.getItem().size() > 0) {
								for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
										.getItem()) {
									if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("type")) {

									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("test-result")) {
										if (questionnaireResponseItemComponent.getAnswer() != null
												&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
											rapidTest.setValue(new StringType(questionnaireResponseItemComponent
													.getAnswer().get(0).getValueStringType().asStringValue()));
										}
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("date-of-test")) {
										if (questionnaireResponseItemComponent.getAnswer() != null
												&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
											rapidTest.setIssued(questionnaireResponseItemComponent.getAnswer().get(0)
													.getValueDateType().getValue());
										}
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId()
													.equals("date-of-specimen-collection")) {
										Reference specimenRef = new Reference();
										SpecimenCollectionComponent specimenCollection = new SpecimenCollectionComponent();
										DateTimeType dateType = new DateTimeType(questionnaireResponseItemComponent
												.getAnswer().get(0).getValueDateType().getValue());
										specimenCollection.setCollected(dateType);
										specimenRapidTest.setCollection(specimenCollection);
										specimenRef.setType(Specimen.class.getName());
										specimenRef.setReference(specimenRapidTest.getId());
										specimenRef.setDisplay(testResult);
										rapidTest.setSpecimen(specimenRef);
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId()
													.equals("place-of-specimen-collection")) {

									}
								}
							}
							hivRecencyTest.add(rapidTest);
						} else if (child.getLinkId() != null && child.getLinkId().equals("vl-test")) {
							Observation vlTest = new Observation();
							CodeableConcept vlTestCode = new CodeableConcept();
							Coding coding = new Coding();
							List<Coding> listCoding = new ArrayList<Coding>();
							coding.setSystem("http://openhie.org/fhir/hiv-casereporting/ValueSet/hiv-vl-test-results");
							coding.setDisplay("HIV VL Test Results");
							listCoding.add(coding);
							vlTestCode.setCoding(listCoding);
							vlTest.setCode(vlTestCode);
							if (child.getItem() != null && child.getItem().size() > 0) {
								for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
										.getItem()) {
									if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("type")) {

									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("test-result")) {
										if (questionnaireResponseItemComponent.getAnswer() != null
												&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
											vlTest.setValue(new StringType(questionnaireResponseItemComponent
													.getAnswer().get(0).getValueStringType().asStringValue()));
										}
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("date-of-test")) {
										if (questionnaireResponseItemComponent.getAnswer() != null
												&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
											vlTest.setIssued(child.getAnswer().get(0).getValueDateType().getValue());
										}
									}
								}
							}
							hivRecencyTest.add(vlTest);
						}
					}
				}
			}
			////// cd4BeforeART
			if (item.getLinkId() != null && item.getLinkId().equals("cd4-before-art")) {
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {

						if (child.getLinkId() != null && child.getLinkId().equals("date-of-test-performance")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								try {
									Date date = new SimpleDateFormat("yyyy")
											.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
									cd4BeforeART.setIssued(date);
								} catch (FHIRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							;
						} else if (child.getLinkId() != null && child.getLinkId().equals("confirming-lab")) {

							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								List<Reference> listRefOrg = new ArrayList<Reference>();
								Reference refOrg = new Reference();
								Organization org = new Organization();
								org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
								MethodOutcome outcome = saveOrganization(org);
								refOrg.setType(Organization.class.getName());
								refOrg.setDisplay(org.getName());
								listRefOrg.add(refOrg);
								cd4BeforeART.setPerformer(listRefOrg);
							}
						} else if (child.getLinkId() != null
								&& child.getLinkId().equals("place-of-specimen-collection")) {

						} else if (child.getLinkId() != null && child.getLinkId().equals("test-result")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								cd4BeforeART.setValue(
										new StringType(child.getAnswer().get(0).getValueStringType().asStringValue()));
							}
						}
					}
				}
			}
			/// cd4DuringART
			if (item.getLinkId() != null && item.getLinkId().equals("cd4-during-art")) {
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {

						if (child.getLinkId() != null && child.getLinkId().equals("date-of-test-performance")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								try {
									Date date = new SimpleDateFormat("yyyy")
											.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
									cd4DuringART.setIssued(date);
								} catch (FHIRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							;
						} else if (child.getLinkId() != null && child.getLinkId().equals("confirming-lab")) {

							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								List<Reference> listRefOrg = new ArrayList<Reference>();
								Reference refOrg = new Reference();
								Organization org = new Organization();
								org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
								MethodOutcome outcome = saveOrganization(org);
								refOrg.setType(Organization.class.getName());
								refOrg.setDisplay(org.getName());
								listRefOrg.add(refOrg);
								cd4DuringART.setPerformer(listRefOrg);
							}
						} else if (child.getLinkId() != null
								&& child.getLinkId().equals("place-of-specimen-collection")) {

						} else if (child.getLinkId() != null && child.getLinkId().equals("test-result")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								cd4DuringART.setValue(
										new StringType(child.getAnswer().get(0).getValueStringType().asStringValue()));
							}
						}
					}
				}
			}
			//// vl4DuringART
			if (item.getLinkId() != null && item.getLinkId().equals("vl4-during-art")) {
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {

						if (child.getLinkId() != null && child.getLinkId().equals("date-of-test-performance")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								try {
									Date date = new SimpleDateFormat("yyyy")
											.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
									vl4DuringART.setIssued(date);
								} catch (FHIRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							;
						} else if (child.getLinkId() != null && child.getLinkId().equals("confirming-lab")) {

							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								List<Reference> listRefOrg = new ArrayList<Reference>();
								Reference refOrg = new Reference();
								Organization org = new Organization();
								org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
								MethodOutcome outcome = saveOrganization(org);
								refOrg.setType(Organization.class.getName());
								refOrg.setDisplay(org.getName());
								listRefOrg.add(refOrg);
								vl4DuringART.setPerformer(listRefOrg);
							}
						} else if (child.getLinkId() != null
								&& child.getLinkId().equals("place-of-specimen-collection")) {

						} else if (child.getLinkId() != null && child.getLinkId().equals("test-result")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								vl4DuringART.setValue(
										new StringType(child.getAnswer().get(0).getValueStringType().asStringValue()));
							}
						}
					}
				}
			}
			/// drugResistanceTest
			if (item.getLinkId() != null && item.getLinkId().equals("vl4-during-art")) {
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {

						if (child.getLinkId() != null && child.getLinkId().equals("date-of-test-performance")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								try {
									Date date = new SimpleDateFormat("yyyy")
											.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
									drugResistanceTest.setIssued(date);
								} catch (FHIRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							;
						} else if (child.getLinkId() != null && child.getLinkId().equals("confirming-lab")) {

							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								List<Reference> listRefOrg = new ArrayList<Reference>();
								Reference refOrg = new Reference();
								Organization org = new Organization();
								org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
								MethodOutcome outcome = saveOrganization(org);
								refOrg.setType(Organization.class.getName());
								refOrg.setDisplay(org.getName());
								listRefOrg.add(refOrg);
								drugResistanceTest.setPerformer(listRefOrg);
							}
						} else if (child.getLinkId() != null
								&& child.getLinkId().equals("place-of-specimen-collection")) {

						} else if (child.getLinkId() != null && child.getLinkId().equals("test-result")) {
							if (child.getAnswer() != null && child.getAnswer().size() > 0) {
								drugResistanceTest.setValue(
										new StringType(child.getAnswer().get(0).getValueStringType().asStringValue()));
							}
						}
					}
				}
			}
			/// comorbidities
			if (item.getLinkId() != null && item.getLinkId().equals("comorbidities")) {
				List<QuestionnaireResponseItemComponent> listChild = item.getItem();
				if (listChild != null && listChild.size() > 0) {
					for (QuestionnaireResponseItemComponent child : listChild) {

						if (child.getLinkId() != null && child.getLinkId().equals("tuberculosis")) {
							if (child.getItem() != null && child.getItem().size() > 0) {
								for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
										.getItem()) {
									if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("tbt")) {
										if (questionnaireResponseItemComponent.getItem() != null
												&& questionnaireResponseItemComponent.getItem().size() > 0) {
											Period period = new Period();
											for (QuestionnaireResponseItemComponent tbtItem : questionnaireResponseItemComponent
													.getItem()) {
												if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("date-started")) {
													if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
														period.setStart(tbtItem.getAnswer().get(0).getValueDateType()
																.getValue());
													}
												} else if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("date-completed")) {
													if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
														period.setEnd(tbtItem.getAnswer().get(0).getValueDateType()
																.getValue());
													}
												} else if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("place-provided")) {

												}
											}
											tbt.setPeriod(period);
										}
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId()
													.equals("tb-diagnosis-date")) {

									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("tb-treatment")) {
										Period period = new Period();
										for (QuestionnaireResponseItemComponent tbtItem : questionnaireResponseItemComponent
												.getItem()) {
											if (tbtItem.getLinkId() != null
													&& tbtItem.getLinkId().equals("date-started")) {
												if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
													period.setStart(
															tbtItem.getAnswer().get(0).getValueDateType().getValue());
												}
											} else if (tbtItem.getLinkId() != null
													&& tbtItem.getLinkId().equals("date-completed")) {
												if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
													period.setEnd(
															tbtItem.getAnswer().get(0).getValueDateType().getValue());
												}
											} else if (tbtItem.getLinkId() != null
													&& tbtItem.getLinkId().equals("place-provided")) {

											}
										}
										tbTreatment.setPeriod(period);
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId()
													.equals("tb-diagnosis-date")) {

									}
								}
							}
							////////// ????????????????????
							else if (child.getLinkId() != null && child.getLinkId().equals("hbv_hcv")) {
								for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
										.getItem()) {
									if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("hbv")) {
										if (questionnaireResponseItemComponent.getItem() != null
												&& questionnaireResponseItemComponent.getItem().size() > 0) {
											Period period = new Period();
											CarePlan carePlan = new CarePlan();
											for (QuestionnaireResponseItemComponent tbtItem : questionnaireResponseItemComponent
													.getItem()) {
												if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("treatment-start-date")) {
													if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
														period.setStart(tbtItem.getAnswer().get(0).getValueDateType()
																.getValue());
													}
												} else if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("treatment-end-date")) {
													if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
														period.setEnd(tbtItem.getAnswer().get(0).getValueDateType()
																.getValue());
													}
												} else if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("diagnosis-date")) {
													if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
														hbv.setIssued(tbtItem.getAnswer().get(0).getValueDateType()
																.getValue());
													}
												} else if (tbtItem.getLinkId() != null
														&& tbtItem.getLinkId().equals("place-provided")) {

												}
											}
											carePlan.setPeriod(period);
										}
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId()
													.equals("tb-diagnosis-date")) {

									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId().equals("tb-treatment")) {
										Period period = new Period();
										for (QuestionnaireResponseItemComponent tbtItem : questionnaireResponseItemComponent
												.getItem()) {
											if (tbtItem.getLinkId() != null
													&& tbtItem.getLinkId().equals("date-started")) {
												if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
													period.setStart(
															tbtItem.getAnswer().get(0).getValueDateType().getValue());
												}
											} else if (tbtItem.getLinkId() != null
													&& tbtItem.getLinkId().equals("date-completed")) {
												if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
													period.setEnd(
															tbtItem.getAnswer().get(0).getValueDateType().getValue());
												}
											} else if (tbtItem.getLinkId() != null
													&& tbtItem.getLinkId().equals("place-provided")) {

											}
										}
										tbTreatment.setPeriod(period);
									} else if (questionnaireResponseItemComponent.getLinkId() != null
											&& questionnaireResponseItemComponent.getLinkId()
													.equals("tb-diagnosis-date")) {

									}
								}
							}
						}
					}
				}
			}
		}

		MethodOutcome outcomePatien = savePatient(patient);

		Reference ref = new Reference();
		ref.setType(Patient.class.getName());
		ref.setId(outcomePatien.getId().getResourceType() + "/" + outcomePatien.getId().getIdPart());
		ref.setDisplay(patient.getName().get(0).getText());
		ref.setReference(outcomePatien.getId().getResourceType() + "/" + outcomePatien.getId().getIdPart());
		for (Condition condition : riskFactors) {
			condition.setSubject(ref);
			MethodOutcome outcomeCondition = saveCondition(condition);
		}
		//
		CodeableConcept hivDiagnosisCode = new CodeableConcept();
		Coding hivDiagnosisCoding = new Coding();
		List<Coding> listHivDiagnosisCoding = new ArrayList<Coding>();
		hivDiagnosisCoding
				.setSystem("http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-diagnosis-observation");
		if (hivDiagnosis.getPerformer() != null && hivDiagnosis.getPerformer().size() > 0) {
			hivDiagnosisCoding.setDisplay(hivDiagnosis.getPerformer().get(0).getDisplay());
		}
		/////
		specimenHivDiagnosis.setSubject(ref);
		MethodOutcome outcomeSpecimen = saveSpecimen(specimenHivDiagnosis);
		listHivDiagnosisCoding.add(hivDiagnosisCoding);
		hivDiagnosisCode.setCoding(listHivDiagnosisCoding);
		hivDiagnosis.setCode(hivDiagnosisCode);
		hivDiagnosis.setSubject(ref);
		hivDiagnosis.getSpecimen().setId(outcomeSpecimen.getId().toString());
		hivDiagnosis.getSpecimen()
				.setReference(outcomePatien.getId().getResourceType() + "/" + outcomePatien.getId().getIdPart());
//		if(hivDiagnosis!=null) {
//			MethodOutcome outcomeHIVDiagnosis = saveObservation(hivDiagnosis);
//		}

		////

		if (hivRecencyTest != null && hivRecencyTest.size() > 0) {
			for (Observation observation : hivRecencyTest) {
				if (observation.getCode() != null && observation.getCode().getCoding() != null
						&& observation.getCode().getCoding().size() > 0) {
					if (observation.getCode().getCoding().get(0).getSystem()
							.equals("http://openhie.org/fhir/hiv-casereporting/ValueSet/hiv-rapid-test-results")) {
						specimenRapidTest.setSubject(ref);
						MethodOutcome outcomeSpecimenRapidTest = saveSpecimen(specimenRapidTest);
						observation.setSubject(ref);
						observation.getSpecimen().setId(outcomeSpecimenRapidTest.getId().toString());
						observation.getSpecimen().setReference(
								outcomePatien.getId().getResourceType() + "/" + outcomePatien.getId().getIdPart());
						MethodOutcome outcomeHIVRecencyTest = saveObservation(observation);
					} else if (observation.getCode().getCoding().get(0).getSystem()
							.equals("http://openhie.org/fhir/hiv-casereporting/ValueSet/hiv-vl-test-results")) {
						observation.setSubject(ref);
						MethodOutcome outcomeHIVRecencyTest = saveObservation(observation);
					}
				}

			}
		}
		//
		CodeableConcept cd4BeforeARTCode = new CodeableConcept();
		Coding cd4BeforeARTCoding = new Coding();
		List<Coding> listcd4BeforeARTCoding = new ArrayList<Coding>();
		cd4BeforeARTCoding.setSystem(
				"http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-cd4-before-art-observation");
		cd4BeforeARTCoding.setDisplay("CD4 Before ART");
		listcd4BeforeARTCoding.add(cd4BeforeARTCoding);
		cd4BeforeARTCode.setCoding(listcd4BeforeARTCoding);
		cd4BeforeART.setCode(cd4BeforeARTCode);
		cd4BeforeART.setSubject(ref);
		MethodOutcome outcomeCd4BeforeART = saveObservation(cd4BeforeART);
		////
		CodeableConcept cd4DuringARTCode = new CodeableConcept();
		Coding cd4DuringARTCoding = new Coding();
		List<Coding> listCd4DuringARTCoding = new ArrayList<Coding>();
		cd4DuringARTCoding.setSystem(
				"http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-cd4-during-art-observation");
		cd4DuringARTCoding.setDisplay("CD4 During ART");
		listCd4DuringARTCoding.add(cd4DuringARTCoding);
		cd4DuringARTCode.setCoding(listCd4DuringARTCoding);
		cd4DuringART.setCode(cd4DuringARTCode);
		cd4DuringART.setSubject(ref);
		MethodOutcome outcomeCd4DuringART = saveObservation(cd4DuringART);
		///
		CodeableConcept vl4DuringARTCode = new CodeableConcept();
		Coding vl4DuringARTCoding = new Coding();
		List<Coding> listvl4DuringARTCoding = new ArrayList<Coding>();
		vl4DuringARTCoding.setSystem(
				"http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-vl4-during-art-observation");
		vl4DuringARTCoding.setDisplay("VL4 During ART");
		listvl4DuringARTCoding.add(vl4DuringARTCoding);
		vl4DuringARTCode.setCoding(listvl4DuringARTCoding);
		vl4DuringART.setCode(vl4DuringARTCode);
		vl4DuringART.setSubject(ref);
		MethodOutcome outcomeVl4DuringART = saveObservation(vl4DuringART);
		//////
		CodeableConcept drugResistanceTestCode = new CodeableConcept();
		Coding drugResistanceTestCoding = new Coding();
		List<Coding> listDrugResistanceTestCoding = new ArrayList<Coding>();
		drugResistanceTestCoding.setSystem(
				"http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-during-resistance-test-observation");
		drugResistanceTestCoding.setDisplay("During Resistance Test");
		listDrugResistanceTestCoding.add(drugResistanceTestCoding);
		drugResistanceTestCode.setCoding(listDrugResistanceTestCoding);
		drugResistanceTest.setCode(drugResistanceTestCode);
		drugResistanceTest.setSubject(ref);
		MethodOutcome outcomeDrugResistanceTest = saveObservation(drugResistanceTest);
		////
		tbt.setSubject(ref);
		MethodOutcome outcomeTBT = saveCarePlan(tbt);
		tbTreatment.setSubject(ref);
		MethodOutcome outcomeTbTreatment = saveCarePlan(tbTreatment);
		result.setType(Bundle.BundleType.COLLECTION);
		return result;
	}

	public static Bundle findPatientDuplicatePatient(String theSystem, String indentifier) {
		FhirContext ctx = FhirContext.forR4();
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		Bundle response = client.search().forResource(HivPatient.class)
				.where(Patient.IDENTIFIER.exactly().identifier(indentifier))
				.totalMode(SearchTotalModeEnum.ACCURATE).count(100).returnBundle(Bundle.class).execute();
		return response;
	}
	public static Bundle getPatientEverythingById(String theUrl) {
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		Bundle response = client.fetchResourceFromUrl(Bundle.class, theUrl);
		return response;
	}
	public static HivPatient getPatientById(String theUrl) {
	      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	      HivPatient response= client.fetchResourceFromUrl(HivPatient.class, theUrl);
	      return response;
	}
	public static void main(String[] args) {
//		QuestionnaireResponse qr = getQuestionnaireResponseById("5770738");
//		convertToBundle(qr);
		String theUrl = HivConst.serverFhirUrl + "/Patient/" + "8"  ;
		HivPatient bundle = getPatientById(theUrl);
//		Patient hl7Patient = null;
//		if (bundle != null && bundle.getEntry() != null && bundle.getEntry().size() > 0) {
//			hl7Patient = (Patient) bundle.getEntryFirstRep().getResource();
//		}
//		HivPatient response = null;
//		if (hl7Patient != null) {
//			String json = jsonParser.encodeResourceToString(hl7Patient);
//
//			response = jsonParser.parseResource(HivPatient.class, json);
//		}
		MethodOutcome MethodOutcome = HAPIFhirHivPatientService.deletePatient(bundle);
//		Bundle bundle = findPatientDuplicatePatient("","594957177");
//		IParser parser = ctx.newJsonParser();
//		String json = parser.encodeResourceToString(bundle);
//		 System.out.println(json);
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
		// getBundlePatient();

	}

}

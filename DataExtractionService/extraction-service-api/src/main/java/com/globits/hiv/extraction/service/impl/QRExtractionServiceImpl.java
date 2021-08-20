package com.globits.hiv.extraction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivChildPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPregnancyObservation;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Patient.PatientLinkComponent;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Specimen;

import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.HivConst;
import com.globits.hiv.extraction.dto.CoMorbidityDto;
import com.globits.hiv.extraction.dto.HivArvTreatmentDto;
import com.globits.hiv.extraction.dto.HivChildDto;
import com.globits.hiv.extraction.dto.HivPregnancyDto;
import com.globits.hiv.extraction.dto.LabTestDto;
import com.globits.hiv.extraction.dto.PatientInfoDto;
import com.globits.hiv.extraction.service.QRExtractionService;
import com.globits.hiv.extraction.utils.QRConvertUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class QRExtractionServiceImpl implements QRExtractionService {
	static String serverBaseUrl = HivConst.serverFhirUrl;
	static FhirContext ctx = FhirContext.forR4();
	static IParser jsonParser = ctx.newJsonParser();

	@Override
	public Bundle getFhirResourceFromQR(QuestionnaireResponse qr) {
		return null;
	}

	@Override
	public MethodOutcome savePatient(QuestionnaireResponse qr) {
		HivPatient patient = new HivPatient();

		return HAPIFhirHivPatientService.savePatient(patient);
	}

	@Override
	public Bundle savePatientTransaction(QuestionnaireResponse qr) {
		PatientInfoDto dto = QRConvertUtil.convertToBundle(qr);
		return QRExtractionServiceImpl.savePatientTransaction(dto.getPatient(), dto.getChildren(),
				dto.getListCoMorbidity(), dto.getPregnancies(),dto.getArvTreatmentDto());

	}

	public static Bundle savePatientTransaction(HivPatient patient, List<? extends Resource> children,
			List<CoMorbidityDto> listCoMorbidity, List<HivPregnancyDto> hivPregnancyDto,List<HivArvTreatmentDto> hivArvTreatmentDto ) {
		HivPatient hivPatient = null;
		Bundle bundle = new Bundle();
		if (patient != null) {
			if (patient.getIdentifier() != null && patient.getIdentifier().size() > 0) {
				Patient hl7Patient = null;
				for (Identifier item : patient.getIdentifier()) {
					if (item.getValue() != null && !item.getValue().trim().equals("")) {
						Bundle bundleTest = QRConvertUtil.findPatientDuplicatePatient(item.getSystem(),
								item.getValue());
						hl7Patient = (Patient) bundleTest.getEntryFirstRep().getResource();
					}

					if (hl7Patient != null) {
						String json = jsonParser.encodeResourceToString(hl7Patient);
						hivPatient = jsonParser.parseResource(HivPatient.class, json);
					}
					if (hivPatient != null) {
						// chua bi?t uu tin ngu?n n�o
						if (hivPatient != null) {
							hivPatient.setName(patient.getName());
							hivPatient.setAddress(patient.getAddress());
							hivPatient.setIdentifier(patient.getIdentifier());
							hivPatient.setBirthDate(patient.getBirthDate());
							// hivPatient.setLastUpdated(patient.getLastUpdated());
							hivPatient.setSyncOrg(patient.getSyncOrg());
							patient = hivPatient;
							break;
						}
					}
				}
			}
			if (hivPatient != null) {
				// IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
				// MethodOutcome outcome = client.update().resource(hivPatient).execute();

				// System.out.print(Patient.class.getSimpleName());
				bundle.setType(Bundle.BundleType.TRANSACTION);
				bundle.addEntry().setFullUrl(patient.getIdElement().getValue()).setResource(patient).getRequest()
						.setUrl(Patient.class.getSimpleName() + "/" + patient.getIdElement().getIdPart())
						.setMethod(Bundle.HTTPVerb.PUT);

			} else {
				// Create a bundle that will be used as a transaction
				patient.setId(IdType.newRandomUuid());
				bundle.setType(Bundle.BundleType.TRANSACTION);
				bundle.addEntry().setFullUrl(patient.getIdElement().getValue()).setResource(patient).getRequest()
						.setUrl(Patient.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
			}
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
						// ARVCarePlan carePlan = (ARVCarePlan)r;
						CarePlan carePlan = (CarePlan) r;
						carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setResource(carePlan).getRequest().setUrl(CarePlan.class.getSimpleName())
								.setMethod(Bundle.HTTPVerb.POST);
					} else if (r.getResourceType().name().equals(PlanDefinition.class.getSimpleName())) {
						CarePlan carePlan = (CarePlan) r;
						carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setResource(carePlan).getRequest()
								.setUrl(PlanDefinition.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
					}

				}
			}
			
			if(hivArvTreatmentDto!= null && hivArvTreatmentDto.size()>0) {
				for (HivArvTreatmentDto hivArvTreatment : hivArvTreatmentDto) {
					List<Reference> refs = new ArrayList<Reference>();
					if(hivArvTreatment.getPlanDefinition() != null && hivArvTreatment.getPlanDefinition().size()>0) {
						
						for (PlanDefinition plan : hivArvTreatment.getPlanDefinition()) {
							PlanDefinition planDefinition = plan;
//							plan.setSubject(new Reference(patient.getIdElement().getValue()));
							planDefinition.setId(IdType.newRandomUuid());
							bundle.addEntry().setFullUrl(planDefinition.getIdElement().getValue()).setResource(planDefinition).getRequest()
									.setUrl(PlanDefinition.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
							refs.add(new Reference(planDefinition.getIdElement().getValue()));
						}
					}
					if(hivArvTreatment.getCarePlan() != null) {
						CarePlan treatment = hivArvTreatment.getCarePlan();
						if(refs != null && refs.size()>0) {
							treatment.setSupportingInfo(refs);
							treatment.setSubject(new Reference(patient.getIdElement().getValue()));
							bundle.addEntry().setResource(treatment).getRequest()
									.setUrl(PlanDefinition.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
						}
					}
				}
			}

			if (listCoMorbidity != null && listCoMorbidity.size() > 0) {
				for (CoMorbidityDto coMorbidity : listCoMorbidity) {
					CarePlan carePlan = null;
					Specimen specimen = null;
					if (coMorbidity.getCarePlan() != null) {
						carePlan = coMorbidity.getCarePlan();
						carePlan.setId(IdType.newRandomUuid());
						carePlan.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setFullUrl(carePlan.getIdElement().getValue()).setResource(carePlan)
								.getRequest().setUrl(CarePlan.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
					} else if (coMorbidity.getSpecimen() != null) {
						specimen = coMorbidity.getSpecimen();
						specimen.setId(IdType.newRandomUuid());
						specimen.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setFullUrl(specimen.getIdElement().getValue()).setResource(specimen)
								.getRequest().setUrl(Specimen.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
					}
					if (carePlan == null && specimen == null && coMorbidity.getObservation() != null) {
						Observation obs = coMorbidity.getObservation();
						obs.setId(IdType.newRandomUuid());
						obs.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setResource(obs).getRequest().setUrl(Observation.class.getSimpleName())
								.setMethod(Bundle.HTTPVerb.POST);
					}
					if (carePlan != null && coMorbidity.getObservation() != null) {
						Observation obs = coMorbidity.getObservation();
						obs.setId(IdType.newRandomUuid());
						obs.addBasedOn(new Reference("CarePlan" + "/" + carePlan.getIdElement().getValue()));
						obs.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setResource(obs).getRequest().setUrl(Observation.class.getSimpleName())
								.setMethod(Bundle.HTTPVerb.POST);
					}
					if (specimen != null && coMorbidity.getObservation() != null) {
						Observation obs = coMorbidity.getObservation();
						Reference ref = new Reference();
						ref.setReference("Specimen" + "/" + specimen.getIdElement().getValue());
						obs.setSpecimen(ref);
						obs.setId(IdType.newRandomUuid());
						obs.setSubject(new Reference(patient.getIdElement().getValue()));
						bundle.addEntry().setResource(obs).getRequest().setUrl(Observation.class.getSimpleName())
								.setMethod(Bundle.HTTPVerb.POST);
					}

					if (coMorbidity.getRecencyDto() != null) {
						List<Reference> listRef = new ArrayList<Reference>();
						if (coMorbidity.getRecencyDto().getListTest() != null
								&& coMorbidity.getRecencyDto().getListTest().size() > 0) {

							for (LabTestDto labTest : coMorbidity.getRecencyDto().getListTest()) {
								Specimen specimenTest = null;
								
								if (labTest.getSpecimen() != null) {
									specimenTest = labTest.getSpecimen();
									specimenTest.setId(IdType.newRandomUuid());
									specimenTest.setSubject(new Reference(patient.getIdElement().getValue()));
									bundle.addEntry().setFullUrl(specimenTest.getIdElement().getValue())
											.setResource(specimenTest).getRequest()
											.setUrl(Specimen.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
								}

								if (specimenTest != null) {
									if (labTest.getObservation() != null) {
										Observation observation = labTest.getObservation();
										Reference ref = new Reference();
										observation.setId(IdType.newRandomUuid());
										ref.setReference("Specimen" + "/" + specimenTest.getIdElement().getValue());
										observation.setSpecimen(ref);
										observation.setSubject(new Reference(patient.getIdElement().getValue()));
										bundle.addEntry().setFullUrl(observation.getIdElement().getValue()).setResource(observation).getRequest()
												.setUrl(Observation.class.getSimpleName())
												.setMethod(Bundle.HTTPVerb.POST);
										///
										listRef.add(new Reference(observation.getIdElement().getValue()));
									}
								} else {
									if (labTest.getObservation() != null) {
										Observation observation = labTest.getObservation();
										observation.setId(IdType.newRandomUuid());
										observation.setSubject(new Reference(patient.getIdElement().getValue()));
										
										bundle.addEntry().setFullUrl(observation.getIdElement().getValue()).setResource(observation).getRequest()
												.setUrl(Observation.class.getSimpleName())
												.setMethod(Bundle.HTTPVerb.POST);
										///
										listRef.add(new Reference(observation.getIdElement().getValue()));
									}
								}
								// Reference ref = new Reference();
								// 		// ref.setReference("Observation" + "/" + observation.getIdElement().getValue());
								// listRef.add(ref);
							}

						}
						if (listRef != null && listRef.size() > 0) {
							Observation observation = new Observation();
							CodeableConcept code = new CodeableConcept();
							Coding coding = new Coding();
							coding.setSystem(HivConst.VsHivRecency);
							coding.setDisplay("HIV recency test");
							code.addCoding(coding);
							observation.setDerivedFrom(listRef);
							observation.setCode(code);

							if (coMorbidity.getRecencyDto().getRecentInfectionConclusion() != null) {
								CodeableConcept codeableConcept = new CodeableConcept();
								codeableConcept.addCoding(coMorbidity.getRecencyDto().getRecentInfectionConclusion());
								observation.setValue(codeableConcept);
							}
							observation.setSubject(new Reference(patient.getIdElement().getValue()));
							bundle.addEntry().setFullUrl(observation.getIdElement().getValue()).setResource(observation).getRequest()
									.setUrl(Observation.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
						}
					}

				}
			}
			if (hivPregnancyDto != null && hivPregnancyDto.size() > 0) {
				for (HivPregnancyDto hivPregnancy : hivPregnancyDto) {
					HivPregnancyObservation hivPregnancyObservation = null;
					if (hivPregnancy.getPregnancyObservation() != null) {
						hivPregnancyObservation = hivPregnancy.getPregnancyObservation();

					}
					if (hivPregnancyObservation != null) {
						

							if (hivPregnancy.getListChild() != null && hivPregnancy.getListChild().size() > 0) {
								List<Reference> refs = new ArrayList<Reference>();
								for (HivChildDto hivChildDto : hivPregnancy.getListChild()) {
									if (hivChildDto.getChild() != null) {
										//
										HivChildPatient hivChildPatient = hivChildDto.getChild();
//										List<Identifier> indenfiers = new ArrayList<Identifier>();
//												Identifier arv = new Identifier();
//												arv.setType(new CodeableConcept());
//												Coding arvCode = new Coding(HivConst.ArvIdentifierNamingSystem, "ArvID",
//														"Naming System - ArvID identifiers");
//												arv.getType().addCoding(arvCode);
//												arv.setSystem(HivConst.ArvIdentifierNamingSystem);
//									
//												arv.setValue();
//												indenfiers.add(arv);
//											}
										PatientLinkComponent patientLinkComponent = new PatientLinkComponent();
										patientLinkComponent.setOther(new Reference(patient.getIdElement().getValue()));
										hivChildPatient.addLink(patientLinkComponent);
										hivChildPatient.setId(IdType.newRandomUuid());
										bundle.addEntry().setFullUrl(hivChildPatient.getIdElement().getValue()).setResource(hivChildPatient).getRequest()
												.setUrl(Patient.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
										hivPregnancyObservation.addHasMember(new Reference(hivChildPatient.getIdElement().getValue()));
										if(hivChildDto.getObservation() != null) {
											HivPregnancyObservation obsHiv =hivChildDto.getObservation();
											obsHiv.setSubject(new Reference(hivChildPatient.getIdElement().getValue()));
											obsHiv.setId(IdType.newRandomUuid());
											bundle.addEntry().setFullUrl(obsHiv.getIdElement().getValue()).setResource(obsHiv).getRequest()
													.setUrl(Observation.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);
											refs.add(new Reference(obsHiv.getIdElement().getValue()));
										}
									}
								}
								if(refs != null && refs.size()>0) {
									hivPregnancyObservation.setHasMember(refs);
								}
							}
							
							////
							
							hivPregnancyObservation.setSubject(new Reference(patient.getIdElement().getValue()));
							hivPregnancyObservation.setId(IdType.newRandomUuid());
							bundle.addEntry().setFullUrl(hivPregnancyObservation.getIdElement().getValue()).setResource(hivPregnancyObservation).getRequest()
									.setUrl(Observation.class.getSimpleName()).setMethod(Bundle.HTTPVerb.POST);

					}
				}
			}

		}
		// Save bundle which contains the Patient and list Resource to HAPI Server
		FhirContext ctx = FhirContext.forR4();
		
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		Bundle resp = client.transaction().withBundle(bundle).execute();
//		 IParser parser = ctx.newJsonParser();
//		 String body = parser.encodeResourceToString(bundle);
//		 String str = parser.encodeResourceToString(resp);
//		 System.out.println(body);
//		 System.out.println(str);
		

		return resp;
	}
}
package com.globits.hiv.extraction.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.hiv.vietnam.r4.model.ARVCarePlan;
import org.hl7.fhir.hiv.vietnam.r4.model.HIVSpecimen;
import org.hl7.fhir.hiv.vietnam.r4.model.HivChildPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPregnancyObservation;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CarePlan.CarePlanActivityComponent;
import org.hl7.fhir.r4.model.CarePlan.CarePlanActivityDetailComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DecimalType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Specimen.SpecimenCollectionComponent;
import org.hl7.fhir.r4.model.StringType;

import com.globits.fhir.hiv.utils.HivConst;
import com.globits.hiv.extraction.dto.CoMorbidityDto;
import com.globits.hiv.extraction.dto.HivArvTreatmentDto;
import com.globits.hiv.extraction.dto.HivChildDto;
import com.globits.hiv.extraction.dto.HivPregnancyDto;
import com.globits.hiv.extraction.dto.HivRecencyDto;
import com.globits.hiv.extraction.dto.LabTestDto;
import com.globits.hiv.extraction.dto.PatientInfoDto;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class QRConvertUtil {
	static FhirContext ctx = FhirContext.forR4();
	static IParser jsonParser = ctx.newJsonParser();
	public static String serverFhirUrl = HivConst.serverFhirUrl;
	public static Bundle getOrganizationBundle(String text) {
		FhirContext ctx = FhirContext.forR4();
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		// serverBaseUrl="http://hapi.fhir.org/baseR4";
		IGenericClient client = ctx.newRestfulGenericClient(serverFhirUrl);
		// Build a search and execute it
		Bundle response = client.search().forResource(Organization.class)
				.where(Organization.NAME.matchesExactly().value(text)).returnBundle(Bundle.class).execute();
		return response;
	}

	public static Bundle getPlanDefinitionBundle(String text) {
		FhirContext ctx = FhirContext.forR4();
		// serverBaseUrl="http://hapi.fhir.org/baseR4";
		IGenericClient client = ctx.newRestfulGenericClient(serverFhirUrl);
		// Build a search and execute it
		Bundle response = client.search().forResource(PlanDefinition.class)
				.where(PlanDefinition.NAME.matchesExactly().value(text)).returnBundle(Bundle.class).execute();
		return response;
	}

	public static MethodOutcome saveOrganization(Organization cond) {

		IGenericClient client = ctx.newRestfulGenericClient(serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(cond).execute();

		// Print the ID of the newly created resource
		return outcome;
	}

	public static MethodOutcome saveCarePlan(CarePlan cond) {

		IGenericClient client = ctx.newRestfulGenericClient(serverFhirUrl);

		// Use the client to store a new resource instance
		MethodOutcome outcome = client.create().resource(cond).execute();

		// Print the ID of the newly created resource

		return outcome;
	}

	public static Bundle findPatientDuplicatePatient(String theSystem, String indentifier) {

		IGenericClient client = ctx.newRestfulGenericClient(serverFhirUrl);
		Bundle response = client.search().forResource(HivPatient.class)
				.where(Patient.IDENTIFIER.exactly().systemAndCode(theSystem, indentifier))
				.totalMode(SearchTotalModeEnum.ACCURATE).count(100).returnBundle(Bundle.class).execute();
		return response;
	}

	private static CoMorbidityDto getLabTest(QuestionnaireResponseItemComponent item, CoMorbidityDto labTest,
			String system, String display) {
		if (labTest == null) {
			labTest = new CoMorbidityDto();
		}
		Observation observation = new Observation();
		CodeableConcept cd4BeforeARTCode = new CodeableConcept();
		Coding coding = new Coding();
		List<Coding> listCoding = new ArrayList<Coding>();
		coding.setSystem(system);
		coding.setDisplay(display);
		listCoding.add(coding);
		cd4BeforeARTCode.setCoding(listCoding);

		HIVSpecimen specimenCd4BeforeART = new HIVSpecimen();
		List<QuestionnaireResponseItemComponent> listChild = item.getItem();
		if (listChild != null && listChild.size() > 0) {
			for (QuestionnaireResponseItemComponent child : listChild) {
				if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdLastestDateTestPerformed)) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						try {
							Date date = new SimpleDateFormat("yyyy-MM-dd")
									.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
							observation.setIssued(date);
						} catch (FHIRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					;
				} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdConfirmingLab)) {

					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						List<Reference> listRefOrg = new ArrayList<Reference>();
						Reference refOrg = new Reference();
						Organization org = null;
						if (child.getAnswer().get(0).getValueStringType() != null
								&& child.getAnswer().get(0).getValueStringType().asStringValue() != null) {
							Bundle organizationBundle = QRConvertUtil.getOrganizationBundle(
									child.getAnswer().get(0).getValueStringType().asStringValue());
							if (organizationBundle != null && organizationBundle.getEntry() != null
									&& organizationBundle.getEntry().size() > 0) {
								org = (Organization) organizationBundle.getEntryFirstRep().getResource();
							}
							MethodOutcome outcome = null;
							if (org == null) {
								org = new Organization();
								org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
								outcome = saveOrganization(org);
							}

							if (outcome != null) {
								refOrg.setReference("Organization" + "/" + outcome.getId().getIdPart());
							} else {
								if (org.getIdElement() != null) {
									refOrg.setReference("Organization" + "/" + org.getIdElement().getIdPart());
								}
							}

							refOrg.setDisplay(child.getAnswer().get(0).getValueStringType().asStringValue());

							refOrg.setType(Organization.class.getName());

							listRefOrg.add(refOrg);
							observation.setPerformer(listRefOrg);
						}
					}
				} else if (child.getLinkId() != null
						&& child.getLinkId().equals(HivConst.QRLinkIdLastestPlaceSpecimen)) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						specimenCd4BeforeART.setSpecimenCollectionPlace(child.getAnswer().get(0).getValueStringType());
					}

				} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdTestResult)) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						// observation.setValue(
						// new
						// StringType(child.getAnswer().get(0).getValueStringType().asStringValue()));
						if (child.getAnswer().get(0).getValueIntegerType() != null) {
							observation.setValue(
									new IntegerType(child.getAnswer().get(0).getValueIntegerType().getValue()));
						}

					}
				} else if (child.getLinkId() != null
						&& child.getLinkId().equals(HivConst.QRLinkIdLastestDateSpecimen)) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						SpecimenCollectionComponent specimenCollection = new SpecimenCollectionComponent();
						DateTimeType dateType = new DateTimeType(
								child.getAnswer().get(0).getValueDateType().getValue());
						specimenCollection.setCollected(dateType);
						specimenCd4BeforeART.setCollection(specimenCollection);
					}
				} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdTestReason)) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {

						Coding testReason = new Coding();
						List<Coding> listTestReason = new ArrayList<Coding>();
						testReason.setSystem(system);
						testReason.setDisplay(child.getAnswer().get(0).getValueCoding().getDisplay());
						listTestReason.add(testReason);
						cd4BeforeARTCode.setCoding(listTestReason);
					}
				} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdTestResultOther)) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						CodeableConcept testValue = new CodeableConcept();
						Coding resultOther = new Coding();
						List<Coding> listTest = new ArrayList<Coding>();
						resultOther.setSystem(system);
						resultOther.setDisplay(child.getAnswer().get(0).getValueCoding().getDisplay());
						resultOther.setCode(child.getAnswer().get(0).getValueCoding().getCode());
						listTest.add(resultOther);
						testValue.setCoding(listTest);
						observation.setValue(testValue);
					}
				}

			}
		}
		observation.setCode(cd4BeforeARTCode);
		///
		labTest.setObservation(observation);
		labTest.setSpecimen(specimenCd4BeforeART);
		return labTest;

	}

	private static List<Identifier> getListIdentifier(QuestionnaireResponseItemComponent child, HivPatient patient,
			String arvId, String caseId, String indexCaseNationalId12, String indexCaseNationalId9, String indexCaseUid) {
		List<Identifier> indenfiers = new ArrayList<Identifier>();
		if (!arvId.equals("")) {
			Identifier arv = new Identifier();
			arv.setType(new CodeableConcept());
			Coding arvCode = new Coding(HivConst.ArvIdentifierNamingSystem, "ArvID",
					"Naming System - ArvID identifiers");
			arv.getType().addCoding(arvCode);
			arv.setSystem(HivConst.ArvIdentifierNamingSystem);

			arv.setValue(arvId);
			indenfiers.add(arv);
		}
		if (!indexCaseNationalId12.equals("")) {
			Identifier nationalId12 = new Identifier();
			nationalId12.setType(new CodeableConcept());
			Coding nationalId12Code = new Coding(HivConst.IndexTestingIdentifierNamingSystem, "IndexCaseNationalId12",
					"Link to index national id 12 digits");
			nationalId12.getType().addCoding(nationalId12Code);
			nationalId12.setSystem(HivConst.IndexCaseNationalId12IdentifierNamingSystem);

			nationalId12.setValue(indexCaseNationalId12);
			indenfiers.add(nationalId12);
		}
		if (!indexCaseNationalId9.equals("")) {
			Identifier nationalId9 = new Identifier();
			nationalId9.setType(new CodeableConcept());
			Coding nationalId9Code = new Coding(HivConst.IndexTestingIdentifierNamingSystem, "IndexCaseNationalId9",
					"Link to index national id 9 digits");
			nationalId9.getType().addCoding(nationalId9Code);
			nationalId9.setSystem(HivConst.IndexCaseNationalId9IdentifierNamingSystem);
			nationalId9.setValue(indexCaseNationalId9);
			indenfiers.add(nationalId9);
		}
		if (!indexCaseUid.equals("")) {
			Identifier indexCase = new Identifier();
			indexCase.setType(new CodeableConcept());
			Coding indexCaseCode = new Coding(HivConst.IndexTestingIdentifierNamingSystem, "indexCaseUid",
					"Link to index hivinfo case id");
			indexCase.getType().addCoding(indexCaseCode);
			indexCase.setSystem(HivConst.IndexCaseUidIdentifierNamingSystem);
			indexCase.setValue(indexCaseUid);
			indenfiers.add(indexCase);
		}
		if (!caseId.equals("")) {
			Identifier caseUid = new Identifier();
			caseUid.setType(new CodeableConcept());
			Coding arvCode = new Coding(HivConst.CaseIdentifierNamingSystem, "Case UID",
					"Naming System - CaseUid identifiers");
			caseUid.getType().addCoding(arvCode);
			caseUid.setSystem(HivConst.CaseIdentifierNamingSystem);

			caseUid.setValue(arvId);
			indenfiers.add(caseUid);
		}
		if (child.getItem() != null && child.getItem().size() > 0) {
			for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child.getItem()) {
				Identifier identifier = new Identifier();

				if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdPassportNumber)) {

					// identifier.setUse(Identifier.IdentifierUse.OFFICIAL);
					identifier.setType(new CodeableConcept());
					Coding ppn = new Coding(HivConst.PassportIdentifierNamingSystem, "NSPassportID",
							"Naming System - Passport identifiers");
					identifier.getType().addCoding(ppn);
					identifier.setSystem(HivConst.PassportIdentifierNamingSystem);
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						// identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()

						// .asStringValue());
						identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()
								.asStringValue());
					}

				} else if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdNationalId9)) {
					// identifier.setUse(Identifier.IdentifierUse.USUAL);
					identifier.setType(new CodeableConcept());
					Coding arvNumberCode = new Coding("https://openhie.org/sid/nationalid9", "NSNationalID9",
							"Naming System - National9 identifiers");
					identifier.getType().addCoding(arvNumberCode);
					identifier.setSystem(HivConst.National9IdentifierNamingSystem);
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()
								.asStringValue());
					}

				} else if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdNationalId12)) {
					// identifier.setUse(Identifier.IdentifierUse.USUAL);
					identifier.setType(new CodeableConcept());
					Coding arvNumberCode = new Coding(HivConst.National12IdentifierNamingSystem, "NSNationalID12",
							"Naming System - National12 identifiers");
					identifier.getType().addCoding(arvNumberCode);
					identifier.setSystem(HivConst.National12IdentifierNamingSystem);
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()
								.asStringValue());
					}

				} else if (questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent
						.getLinkId().equals(HivConst.QRLinkIdHealthInsuranceNumber)) {

					identifier.setType(new CodeableConcept());
					Coding insuranceNumberCode = new Coding(HivConst.InsuranceIdentifierNamingSystem, "NSInsuranceID",
							"Naming System - Insurance identifiers");
					identifier.getType().addCoding(insuranceNumberCode);
					identifier.setSystem(HivConst.InsuranceIdentifierNamingSystem);
					// identifier.setUse(Identifier.IdentifierUse.SECONDARY);
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()
								.asStringValue());

					}
				} else if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdDriverLicense)) {

					identifier.setType(new CodeableConcept());
					Coding insuranceNumberCode = new Coding(HivConst.DriverLicenseIdentifierNamingSystem,
							"NSDRIVERLICENSE", "driver license");
					identifier.getType().addCoding(insuranceNumberCode);
					identifier.setSystem(HivConst.DriverLicenseIdentifierNamingSystem);
					// identifier.setUse(Identifier.IdentifierUse.SECONDARY);
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						identifier.setValue(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()
								.asStringValue());
					}

				}
				indenfiers.add(identifier);
				// if (questionnaireResponseItemComponent.getAnswer() != null
				// && questionnaireResponseItemComponent.getAnswer().size() > 0) {
				// identifier.setValue(
				// questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
				// indenfiers.add(identifier);
				//
				// }

			}

		}
		return indenfiers;
	}

	private static List<Address> getListAddress(QuestionnaireResponseItemComponent child) {
		List<Address> listAddress = new ArrayList<Address>();
		if (child.getItem() != null && child.getItem().size() > 0) {
			for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child.getItem()) {
				Address address = new Address();
				if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdCurrentResidence)) {
					address.setUse(Address.AddressUse.fromCode("temp"));
					if (questionnaireResponseItemComponent.getItem() != null
							&& questionnaireResponseItemComponent.getItem().size() > 0) {
						Extension adminAddress = new Extension("Temporary");
						// address.setText(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType().asStringValue());
						for (QuestionnaireResponseItemComponent currentItem : questionnaireResponseItemComponent
								.getItem()) {
							if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdAddress)) {
								List<StringType> listCurrentLine = new ArrayList<StringType>();
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									// StringType currentLine = new
									// StringType(currentItem.getAnswer().get(0).getValueStringType())
									listCurrentLine.add(currentItem.getAnswer().get(0).getValueStringType());
								}

								address.setLine(listCurrentLine);
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdCommune)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									Extension commune = new Extension("commune",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(commune);
								}
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdDistrict)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setDistrict(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension district = new Extension("district",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(district);
								}
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdProvince)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setState(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension province = new Extension("province",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(province);
								}
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdCountry)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setCountry(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension country = new Extension("country",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(country);
								}
							}
							// else if(currentItem.getLinkId() != null
							// && currentItem.getLinkId().equals(HivConst.QRLinkIdCurrentResidenceText)) {
							// if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
							// address.setText(
							// currentItem.getAnswer().get(0).getValueStringType().asStringValue());
							// }
							// }
						}
						address.addExtension(adminAddress);
					}

				} else if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdPermanentResidence)) {
					address.setUse(Address.AddressUse.fromCode("home"));
					if (questionnaireResponseItemComponent.getItem() != null
							&& questionnaireResponseItemComponent.getItem().size() > 0) {
						Extension adminAddress = new Extension("Home");
						// address.setText(questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType()
						// .asStringValue());
						for (QuestionnaireResponseItemComponent currentItem : questionnaireResponseItemComponent
								.getItem()) {
							if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdAddress)) {
								List<StringType> listCurrentLine = new ArrayList<StringType>();
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									// StringType currentLine = new
									// StringType(currentItem.getAnswer().get(0).getValueStringType())
									listCurrentLine.add(currentItem.getAnswer().get(0).getValueStringType());
								}

								address.setLine(listCurrentLine);
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdCommune)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setCity(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension commune = new Extension("commune",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(commune);
								}
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdDistrict)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setDistrict(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension district = new Extension("district",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(district);
								}
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdProvince)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setState(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension province = new Extension("province",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(province);
								}
							} else if (currentItem.getLinkId() != null
									&& currentItem.getLinkId().equals(HivConst.QRLinkIdCountry)) {
								if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
									address.setCountry(currentItem.getAnswer().get(0).getValueCoding().getDisplay());
									Extension country = new Extension("country",
											new Coding("vietnam_administrative",
													currentItem.getAnswer().get(0).getValueCoding().getCode(),
													currentItem.getAnswer().get(0).getValueCoding().getDisplay()));
									adminAddress.addExtension(country);
								}
							}
							// else if (currentItem.getLinkId() != null
							// && currentItem.getLinkId().equals(HivConst.QRLinkIdPermanentResidenceText)) {
							// if (currentItem.getAnswer() != null && currentItem.getAnswer().size() > 0) {
							// address.setText(
							// currentItem.getAnswer().get(0).getValueStringType().asStringValue());
							// }
							// }
						}
						address.addExtension(adminAddress);

					}
				}

				listAddress.add(address);
			}
		}
		return listAddress;
	}

	private static List<Resource> getListRiskFactor(List<QuestionnaireResponseItemComponent> listChild,
			List<Resource> riskFactors) {

		Condition condition = new Condition();
		CodeableConcept code = new CodeableConcept();
		code.addCoding(new Coding(HivConst.CsHivRiskFactor, HivConst.QRTextRiskFactor, HivConst.QRTextRiskFactor));
		condition.setCode(code);

		for (QuestionnaireResponseItemComponent child : listChild) {
			CodeableConcept codeableConcept = new CodeableConcept();
			if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdPopulationGroup)) {

				codeableConcept.setText(HivConst.QRTextPopulationGroup);
				if (codeableConcept.getCoding() == null) {
					codeableConcept.setCoding(new ArrayList<Coding>());
				}
				// Coding coding = new Coding();
				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					codeableConcept.addCoding(
							new Coding(HivConst.CsHivPopulation, child.getAnswer().get(0).getValueCoding().getCode(),
									child.getAnswer().get(0).getValueCoding().getDisplay()));
				}
			} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdRiskBehavior)) {
				codeableConcept.setText(HivConst.VsHivRiskBehavior);
				if (codeableConcept.getCoding() == null) {
					codeableConcept.setCoding(new ArrayList<Coding>());
				}
				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					codeableConcept.addCoding(
							new Coding(HivConst.CsHivRiskBehavior, child.getAnswer().get(0).getValueCoding().getCode(),
									child.getAnswer().get(0).getValueCoding().getDisplay()));
				}
			} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdTransmissionRoute)) {
				codeableConcept.setText(HivConst.QRTextTransmissionRoute);
				if (codeableConcept.getCoding() == null) {
					codeableConcept.setCoding(new ArrayList<Coding>());
				}
				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					codeableConcept.addCoding(new Coding(HivConst.CsHivTransmissionRoute,
							child.getAnswer().get(0).getValueCoding().getCode(),
							child.getAnswer().get(0).getValueCoding().getDisplay()));
				}
			}
			condition.addCategory(codeableConcept);
		}

		riskFactors.add(condition);

		return riskFactors;
	}

	private static CoMorbidityDto getHivDiagnosis(List<QuestionnaireResponseItemComponent> listChild,
			Observation hivDiagnosis, HIVSpecimen specimenHivDiagnosis, CoMorbidityDto hivDiagnosisDto) {
		for (QuestionnaireResponseItemComponent child : listChild) {
			CodeableConcept hivDiagnosisCode = new CodeableConcept();
			Coding coding = new Coding();
			List<Coding> listCoding = new ArrayList<Coding>();
			coding.setSystem(HivConst.VsDiagnosisObs);
			coding.setDisplay("HIV Diagnosis");
			listCoding.add(coding);
			hivDiagnosisCode.setCoding(listCoding);
			hivDiagnosis.setCode(hivDiagnosisCode);
			if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdDateOfConfirmation)) {
				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					hivDiagnosis.setIssued(child.getAnswer().get(0).getValueDateType().getValue());
//					try {
//						Date date = new SimpleDateFormat("dd/MM/yyyy")
//								.parse(child.getAnswer().get(0).getValueDateType().getValueAsString());
//						hivDiagnosis.setIssued(date);
//					} catch (FHIRException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
				;
			} else if (child.getLinkId() != null && child.getLinkId().equals(HivConst.QRLinkIdConfirmingLab)) {

				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					if (child.getAnswer() != null && child.getAnswer().size() > 0) {
						List<Reference> listRefOrg = new ArrayList<Reference>();
						Reference refOrg = new Reference();
						Organization org = null;
						Bundle organizationBundle = QRConvertUtil
								.getOrganizationBundle(child.getAnswer().get(0).getValueStringType().asStringValue());
						if (organizationBundle != null && organizationBundle.getEntry() != null
								&& organizationBundle.getEntry().size() > 0) {
							org = (Organization) organizationBundle.getEntryFirstRep().getResource();
						}
						MethodOutcome outcome = null;
						if (org == null) {
							org = new Organization();
							org.setName(child.getAnswer().get(0).getValueStringType().asStringValue());
							outcome = saveOrganization(org);
						}
						if (outcome != null) {
							refOrg.setReference("Organization" + "/" + outcome.getId().getIdPart());
						} else {
							if (org.getIdElement() != null) {
								refOrg.setReference("Organization" + "/" + org.getIdElement().getIdPart());
							}
						}

						refOrg.setDisplay(child.getAnswer().get(0).getValueStringType().asStringValue());
						refOrg.setType(Organization.class.getName());

						listRefOrg.add(refOrg);
						hivDiagnosis.setPerformer(listRefOrg);

					}
				}
			} else if (child.getLinkId() != null
					&& child.getLinkId().equals(HivConst.QRLinkIdDateOfSpecimenCollection)) {
				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					SpecimenCollectionComponent specimenCollection = new SpecimenCollectionComponent();
					DateTimeType dateType = new DateTimeType(child.getAnswer().get(0).getValueDateType().getValue());
					specimenCollection.setCollected(dateType);
					specimenHivDiagnosis.setCollection(specimenCollection);
				}
				;
			} else if (child.getLinkId() != null
					&& child.getLinkId().equals(HivConst.QRLinkIdPlaceOfSpecimenCollection)) {

				if (child.getAnswer() != null && child.getAnswer().size() > 0) {
					specimenHivDiagnosis.setSpecimenCollectionPlace(child.getAnswer().get(0).getValueStringType());
				}
				;
			}
		}
		hivDiagnosisDto.setSpecimen(specimenHivDiagnosis);
		hivDiagnosisDto.setObservation(hivDiagnosis);
		return hivDiagnosisDto;

	}

	private static LabTestDto getHivRecencyRapidTest(QuestionnaireResponseItemComponent child, LabTestDto labTest,
			HIVSpecimen specimenRapidTest) {
		Observation rapidTest = new Observation();
		Date date = null;
		String testResult = "";
		rapidTest.setIssued(date);
		CodeableConcept rapidTestCode = new CodeableConcept();
		Coding coding = new Coding();
		List<Coding> listCoding = new ArrayList<Coding>();
		coding.setSystem(HivConst.VsHivRapidTest);
		coding.setDisplay("HIV Rapid Test Results");
		listCoding.add(coding);
		rapidTest.setCode(rapidTestCode);
		rapidTestCode.setCoding(listCoding);
		if (child.getItem() != null && child.getItem().size() > 0) {
			for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child.getItem()) {
				if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals("type")) {

				} else if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdTestResult)) {
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						Coding code =  new Coding();
						code.setCode(questionnaireResponseItemComponent.getAnswer().get(0)
								.getValueStringType().asStringValue());
						code.setDisplay(questionnaireResponseItemComponent.getAnswer().get(0)
								.getValueStringType().asStringValue());
						//
						CodeableConcept codeableConcept = new CodeableConcept();
						codeableConcept.addCoding(code);
						rapidTest.setValue(codeableConcept);
//						rapidTest.setValue(new IntegerType(questionnaireResponseItemComponent.getAnswer().get(0)
//								.getValueStringType().asStringValue()));
					}
				} else if (questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent
						.getLinkId().equals(HivConst.QRLinkIdDateOfTestPerformance)) {
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						rapidTest.setIssued(
								questionnaireResponseItemComponent.getAnswer().get(0).getValueDateType().getValue());
					}
				} else if (questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent
						.getLinkId().equals(HivConst.QRLinkIdDateOfSpecimenCollection)) {
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						SpecimenCollectionComponent specimenCollection = new SpecimenCollectionComponent();
						DateTimeType dateType = new DateTimeType(
								questionnaireResponseItemComponent.getAnswer().get(0).getValueDateType().getValue());
						specimenCollection.setCollected(dateType);
						specimenRapidTest.setCollection(specimenCollection);
					}
				} else if (questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent
						.getLinkId().equals(HivConst.QRLinkIdPlaceOfSpecimenCollection)) {
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						specimenRapidTest.setSpecimenCollectionPlace(
								questionnaireResponseItemComponent.getAnswer().get(0).getValueStringType());
					}
				}
			}
		}
		labTest.setSpecimen(specimenRapidTest);
		labTest.setObservation(rapidTest);
		return labTest;
	}

	private static Observation getHivRecencyVlTest(QuestionnaireResponseItemComponent child, Observation vlTest) {
		CodeableConcept vlTestCode = new CodeableConcept();
		Coding coding = new Coding();
		List<Coding> listCoding = new ArrayList<Coding>();
		coding.setSystem(HivConst.VsHivVLTest);
		coding.setDisplay("HIV VL Test Results");
		listCoding.add(coding);
		vlTestCode.setCoding(listCoding);
		vlTest.setCode(vlTestCode);
		if (child.getItem() != null && child.getItem().size() > 0) {
			for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child.getItem()) {
				if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals("type")) {

				} else if (questionnaireResponseItemComponent.getLinkId() != null
						&& questionnaireResponseItemComponent.getLinkId().equals(HivConst.QRLinkIdVlRecencyTestResult)) {
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						vlTest.setValue(new IntegerType(questionnaireResponseItemComponent.getAnswer().get(0)
								.getValueStringType().asStringValue()));
					}
				} else if (questionnaireResponseItemComponent.getLinkId() != null && questionnaireResponseItemComponent
						.getLinkId().equals(HivConst.QRLinkIdDateOfTestPerformance)) {
					if (questionnaireResponseItemComponent.getAnswer() != null
							&& questionnaireResponseItemComponent.getAnswer().size() > 0) {
						vlTest.setIssued(
								questionnaireResponseItemComponent.getAnswer().get(0).getValueDateType().getValue());
					}
				}
			}
		}
		return vlTest;
	}

	private static CoMorbidityDto getComorbidities(
			QuestionnaireResponseItemComponent questionnaireResponseItemComponent, Observation comorbidity,
			String system) {
		if (questionnaireResponseItemComponent.getItem() != null
				&& questionnaireResponseItemComponent.getItem().size() > 0) {
			Period period = new Period();
			CarePlan carePlan = new CarePlan();
			if (comorbidity == null) {
				comorbidity = new Observation();
			}
			for (QuestionnaireResponseItemComponent tbtItem : questionnaireResponseItemComponent.getItem()) {
				if (tbtItem.getLinkId() != null && (tbtItem.getLinkId().equals(HivConst.QRLinkIdDateOfTreatmentStart)
						|| tbtItem.getLinkId().equals(HivConst.QRLinkIdDateStarted))) {
					if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
						period.setStart(tbtItem.getAnswer().get(0).getValueDateType().getValue());
					}
				} else if (tbtItem.getLinkId() != null
						&& (tbtItem.getLinkId().equals(HivConst.QRLinkIdDateOfTreatmentStop)
								|| tbtItem.getLinkId().equals(HivConst.QRLinkIdDateCompleted))) {
					if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
						period.setEnd(tbtItem.getAnswer().get(0).getValueDateType().getValue());
					}
				} else if (tbtItem.getLinkId() != null && tbtItem.getLinkId().equals(HivConst.QRLinkIdDiagnosisDate)) {
					if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
						comorbidity.setIssued(tbtItem.getAnswer().get(0).getValueDateType().getValue());
					}
				} else if (tbtItem.getLinkId() != null && tbtItem.getLinkId().equals(HivConst.QRLinkIdPlaceProvided)) {
					if (tbtItem.getAnswer() != null && tbtItem.getAnswer().size() > 0) {
						Reference refOrg = new Reference();
						Organization org = null;
						Bundle organizationBundle = QRConvertUtil
								.getOrganizationBundle(tbtItem.getAnswer().get(0).getValueStringType().asStringValue());
						if (organizationBundle != null && organizationBundle.getEntry() != null
								&& organizationBundle.getEntry().size() > 0) {
							org = (Organization) organizationBundle.getEntryFirstRep().getResource();
						}
						if (org == null) {
							org = new Organization();
							org.setName(tbtItem.getAnswer().get(0).getValueStringType().asStringValue());
							MethodOutcome outcome = saveOrganization(org);
						}
						if (org != null) {
							refOrg.setReference("Organization" + "/" + org.getIdElement().getIdPart());
							refOrg.setType(Organization.class.getName());
							refOrg.setDisplay(org.getName());
						}
						carePlan.setAuthor(refOrg);

					}
				}
			}
			carePlan.setPeriod(period);
			CarePlanActivityComponent carePlanActivityComponent = new CarePlanActivityComponent();
			CarePlanActivityDetailComponent carePlanActivityDetailComponent = new CarePlanActivityDetailComponent();
			CodeableConcept codeableConcept = new CodeableConcept();
			Coding coding = new Coding();
			List<Coding> listCoding = new ArrayList<Coding>();
			coding.setSystem(system);
			listCoding.add(coding);
			codeableConcept.setCoding(listCoding);
			carePlanActivityDetailComponent.setCode(codeableConcept);
			carePlanActivityComponent.setDetail(carePlanActivityDetailComponent);
			carePlan.addActivity(carePlanActivityComponent);
			comorbidity.setCode(codeableConcept);
			CoMorbidityDto coMorbidityDto = new CoMorbidityDto();
			coMorbidityDto.setCarePlan(carePlan);
			coMorbidityDto.setObservation(comorbidity);
			return coMorbidityDto;
		}
		return null;

	}

	public static PatientInfoDto convertToBundle(QuestionnaireResponse qr) {
		PatientInfoDto result = new PatientInfoDto();
		new CarePlan();
		new ArrayList<IBaseResource>();
		List<QuestionnaireResponseItemComponent> listData = qr.getItem();
		Calendar.getInstance();
		HivPatient patient = new HivPatient();
		List<Resource> children = new ArrayList<Resource>();
		List<Resource> comorbidities = new ArrayList<Resource>();
		List<CoMorbidityDto> listCoMorbidity = new ArrayList<CoMorbidityDto>();
		List<Condition> riskFactors = new ArrayList<Condition>();
		List<HivPregnancyDto> pregnancies = new ArrayList<HivPregnancyDto>();
		List<HivArvTreatmentDto> listArvTreatmentDto = new ArrayList<HivArvTreatmentDto>();
		// List<Condition> riskFactors = new ArrayList<Condition>();
		Observation hivDiagnosis = null;
		HIVSpecimen specimenHivDiagnosis = null;
		// List<Observation> hivRecencyTest = new ArrayList<Observation>();
		HIVSpecimen specimenRapidTest = new HIVSpecimen();
		Observation hbv = null;

		Observation hcv = null;
		String fullName = null;
		if (listData != null && listData.size() > 0) {
			for (QuestionnaireResponseItemComponent questions : listData) {
				if (questions.getLinkId() != null
						&& questions.getLinkId().equals(HivConst.QRLinkIdOrganizationUnitName)) {
					if (questions.getAnswer() != null && questions.getAnswer().size() > 0) {
						patient.setSyncOrg(new CodeableConcept());
						List<Coding> listSyncOrg = new ArrayList<Coding>();
						Coding coding = new Coding();
						coding.setSystem(HivConst.VsOrganizationUnitName);
						coding.setCode(questions.getAnswer().get(0).getValueCoding().getCode());
						coding.setDisplay(questions.getAnswer().get(0).getValueCoding().getDisplay());
						listSyncOrg.add(coding);
						patient.getSyncOrg().setCoding(listSyncOrg);
						// patient.setLastUpdated(coding);
					}
				} else if (questions.getLinkId() != null && questions.getLinkId().equals(HivConst.QRLinkIdReportDate)) {

				} else if (questions.getLinkId() != null
						&& questions.getLinkId().equals(HivConst.QRLinkIdOrganizationUnitName)) {

				} else if (questions.getLinkId() != null && questions.getLinkId().equals(HivConst.QRLinkIdQuestions)) {
					List<QuestionnaireResponseItemComponent> list = questions.getItem();
					for (QuestionnaireResponseItemComponent item : list) {
						CoMorbidityDto cd4Test = null;
						CoMorbidityDto cd4DuringART = null;
						CoMorbidityDto vl4DuringART = null;
						CoMorbidityDto drugResistanceTest = null;
						String arvId = "";
						String caseId = "";
						String indexCaseNationalId12 = "";
						String indexCaseNationalId9 = "";
						String indexCaseUid = "";
						if (item.getLinkId() != null
								&& item.getLinkId().equals(HivConst.QRLinkIdPatientIdentification)) {
							List<QuestionnaireResponseItemComponent> listPatientIdentification = item.getItem();

							if (listPatientIdentification != null && listPatientIdentification.size() > 0) {
								for (QuestionnaireResponseItemComponent patientItem : listPatientIdentification) {
									if (patientItem.getLinkId() != null
											&& patientItem.getLinkId().equals(HivConst.QRLinkIdPatientArvNumber)) {
										if (patientItem.getAnswer() != null && patientItem.getAnswer().size() > 0) {
											arvId = patientItem.getAnswer().get(0).getValueStringType().asStringValue();
										}

									} else if (patientItem.getLinkId() != null
											&& patientItem.getLinkId().equals(HivConst.QRLinkIdCaseUid)) {
										if (patientItem.getAnswer() != null && patientItem.getAnswer().size() > 0) {
											caseId = patientItem.getAnswer().get(0).getValueStringType()
													.asStringValue();
										}
									} else if (patientItem.getLinkId() != null
											&& patientItem.getLinkId().equals(HivConst.QRLinkIdIndexTesting)) {
										List<QuestionnaireResponseItemComponent> listChild = patientItem.getItem();
										if (patientItem.getAnswer() != null && patientItem.getAnswer().size() > 0) {
											for (QuestionnaireResponseItemComponent child : listChild) {
												if (child.getLinkId() != null && child.getLinkId()
														.equals(HivConst.QRLinkIdIndexCaseNationalId12)) {
													if (child.getAnswer() != null && child.getAnswer().size() > 0) {
														indexCaseNationalId12 = child.getAnswer().get(0)
																.getValueStringType().asStringValue();
													}
												}
												if (child.getLinkId() != null && child.getLinkId()
														.equals(HivConst.QRLinkIdIndexCaseNationalId9)) {
													if (child.getAnswer() != null && child.getAnswer().size() > 0) {
														indexCaseNationalId9 = child.getAnswer().get(0)
																.getValueStringType().asStringValue();
													}
												}
												if (child.getLinkId() != null
														&& child.getLinkId().equals(HivConst.QRLinkIdIndexCaseUID)) {
													if (child.getAnswer() != null && child.getAnswer().size() > 0) {
														indexCaseUid = child.getAnswer().get(0).getValueStringType()
																.asStringValue();
													}
												}
											}
										}
									}
									// Personal information
									else if (patientItem.getLinkId() != null
											&& patientItem.getLinkId().equals(HivConst.QRLinkIdPersonalInformation)) {

										List<QuestionnaireResponseItemComponent> listChild = patientItem.getItem();
										for (QuestionnaireResponseItemComponent child : listChild) {
											if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdIdentification)) {

												List<Identifier> identifiers = getListIdentifier(child, patient, arvId,
														caseId, indexCaseNationalId12, indexCaseNationalId9,
														indexCaseUid);
												patient.setIdentifier(identifiers);
											} else if (child.getLinkId() != null && child.getLinkId()
													.equals(HivConst.QRLinkIdOrganizationUnitName)) {
												if (child.getAnswer() != null && child.getAnswer().size() > 0) {

													patient.setSyncOrg(new CodeableConcept());
													List<Coding> listSyncOrg = new ArrayList<Coding>();
													Coding coding = new Coding();
													coding.setSystem(HivConst.VsGoveningUnitName);
													coding.setCode(child.getAnswer().get(0).getValueCoding().getCode());
													coding.setDisplay(
															child.getAnswer().get(0).getValueCoding().getDisplay());
													listSyncOrg.add(coding);
													patient.getSyncOrg().setCoding(listSyncOrg);
													// patient.setLastUpdated(coding);
												}
											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdPatientFullName)) {
												if (child.getAnswer() != null && child.getAnswer().size() > 0) {
													fullName = child.getAnswer().get(0).getValueStringType()
															.asStringValue();
												}
											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdPatientName)) {
												HumanName humanName = new HumanName();
												if (child.getItem() != null && child.getItem().size() > 0) {
													for (QuestionnaireResponseItemComponent nameItem : child
															.getItem()) {
														if (nameItem.getLinkId() != null && nameItem.getLinkId()
																.equals(HivConst.QRLinkIdPatientFamilyName)) {
															if (nameItem.getAnswer() != null
																	&& nameItem.getAnswer().size() > 0) {
																humanName.setFamily(nameItem.getAnswer().get(0)
																		.getValueStringType().asStringValue());
															}

														} else if (nameItem.getLinkId() != null && nameItem.getLinkId()
																.equals(HivConst.QRLinkIdPatientGivenName)) {
															if (nameItem.getAnswer() != null
																	&& nameItem.getAnswer().size() > 0) {
																List<StringType> listGivenName = new ArrayList<StringType>();
																listGivenName.add(nameItem.getAnswer().get(0)
																		.getValueStringType());
																humanName.setGiven(listGivenName);
															}
														} else if (nameItem.getLinkId() != null && nameItem.getLinkId()
																.equals(HivConst.QRLinkIdPatientFullName)) {
															if (nameItem.getAnswer() != null
																	&& nameItem.getAnswer().size() > 0) {
																humanName.setText(nameItem.getAnswer().get(0)
																		.getValueStringType().asStringValue());
															}
														}
													}
												}
												if (humanName.getText() == null) {
													humanName.setText(fullName);
												}
												patient.getName().add(humanName);
												// System.out.println(child.getAnswer().get(0).getValueStringType());
											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdEthnicity)) {
												///
												if (child.getAnswerFirstRep() != null) {
													patient.setEthnicity(new CodeableConcept());
													patient.getEthnicity()
															.addCoding(child.getAnswerFirstRep().getValueCoding());
												}
											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdOccupation)) {

												if (child.getAnswerFirstRep() != null) {
													patient.setOccupation(child.getAnswerFirstRep().getValueCoding());
												}
											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdGender)) {
												if (child.getAnswer() != null && child.getAnswer().size() > 0) {

													patient.setGenderIdentity(new CodeableConcept());
													List<Coding> listGender = new ArrayList<Coding>();
													Coding coding = new Coding();
													coding.setSystem(HivConst.VsPatientGenderIdentity);
													coding.setCode(child.getAnswer().get(0).getValueCoding().getCode());
													coding.setDisplay(
															child.getAnswer().get(0).getValueCoding().getDisplay());
													listGender.add(coding);
													patient.getGenderIdentity().setCoding(listGender);
													patient.setGender(AdministrativeGender.fromCode(
															child.getAnswer().get(0).getValueCoding().getCode()));

												}

											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdYearOfBirth)) {
												if (child.getAnswer() != null && child.getAnswer().size() > 0) {
													patient.setBirthDate(
															child.getAnswer().get(0).getValueDateType().getValue());
												}
											} else if (child.getLinkId() != null
													&& child.getLinkId().equals(HivConst.QRLinkIdResidence)) {
												List<Address> listAddress = QRConvertUtil.getListAddress(child);
												patient.setAddress(listAddress);
											}
										}
									}
								}
							}
						}
						//// risk_factor
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdRiskFactor)) {
							List<QuestionnaireResponseItemComponent> listChild = item.getItem();
							if (listChild != null && listChild.size() > 0) {
								List<Resource> risks = QRConvertUtil.getListRiskFactor(listChild, children);
							}

						}
						/// hiv-diagnosis
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdHivDiagnosis)) {
							List<QuestionnaireResponseItemComponent> listChild = item.getItem();
							if (hivDiagnosis == null) {
								hivDiagnosis = new Observation();
							}
							CoMorbidityDto hivDiagnosisDto = new CoMorbidityDto();
							if (listChild != null && listChild.size() > 0) {
								if (specimenHivDiagnosis == null) {
									specimenHivDiagnosis = new HIVSpecimen();
								}
								hivDiagnosisDto = QRConvertUtil.getHivDiagnosis(listChild, hivDiagnosis,
										specimenHivDiagnosis, hivDiagnosisDto);
								listCoMorbidity.add(hivDiagnosisDto);
								// children.add(hivDiagnosis);
								// children.add(specimenHivDiagnosis);
							}
						}
						// hiv-recency-test
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdHivRecencyTest)) {
							List<QuestionnaireResponseItemComponent> listChild = item.getItem();
							HivRecencyDto recenryDto = new HivRecencyDto();
							CoMorbidityDto coMorbidityDto = new CoMorbidityDto();
							List<LabTestDto> listLabTest = new ArrayList<LabTestDto>();
							if (listChild != null && listChild.size() > 0) {
								for (QuestionnaireResponseItemComponent child : listChild) {
									if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinnkIdRecencyRapidTest)) {

										LabTestDto labTest = new LabTestDto();
										labTest = QRConvertUtil.getHivRecencyRapidTest(child, labTest,
												specimenRapidTest);
										listLabTest.add(labTest);

										// children.add(rapidTest);

									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdRecencyVlTest)) {
										Observation vlTest = new Observation();
										LabTestDto labTest = new LabTestDto();
										vlTest = QRConvertUtil.getHivRecencyVlTest(child, vlTest);
										labTest.setObservation(vlTest);
										listLabTest.add(labTest);
										// listCoMorbidity.add(coMorbidityDto);
										// children.add(vlTest);
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdRecentInfectionConclusion)) {

										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											Coding valueCode = child.getAnswer().get(0).getValueCoding();
											if (valueCode != null) {
												recenryDto.setRecentInfectionConclusion(valueCode);
											}
										}
									}

								}
							}
							recenryDto.setListTest(listLabTest);
							coMorbidityDto.setRecencyDto(recenryDto);
							listCoMorbidity.add(coMorbidityDto);
						}
						////// cd4ART
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdCd4Test)) {
							cd4Test = QRConvertUtil.getLabTest(item, cd4Test, HivConst.HivCd4TestObservation,
									item.getText());

						}

						// /// cd4DuringART
						// else if (item.getLinkId() != null &&
						// item.getLinkId().equals(HivConst.QRLinkIdCd4DuringArt)) {
						// cd4DuringART = QRConvertUtil.getLabTest(item, cd4DuringART,
						// HivConst.HivCd4DuringArtObservation, item.getText());
						// }
						//// vl4DuringART
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdVlTest)) {
							vl4DuringART = QRConvertUtil.getLabTest(item, vl4DuringART,
									HivConst.HivVl4DuringArtObservation, item.getText());
						}
						/// drugResistanceTest
						else if (item.getLinkId() != null
								&& item.getLinkId().equals(HivConst.QRLinkIdDrugResistanceTest)) {
							drugResistanceTest = QRConvertUtil.getLabTest(item, drugResistanceTest,
									HivConst.HivDuringResistanceTestObservation, item.getText());
						}
						/// comorbidities
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdComorbidities)) {
							List<QuestionnaireResponseItemComponent> listChild = item.getItem();
							if (listChild != null && listChild.size() > 0) {
								for (QuestionnaireResponseItemComponent child : listChild) {

									if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdTuberculosis)) {
										if (child.getItem() != null && child.getItem().size() > 0) {
											Observation tbDiagnosis = null;
											for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
													.getItem()) {

												if (questionnaireResponseItemComponent.getLinkId() != null
														&& questionnaireResponseItemComponent.getLinkId()
																.equals(HivConst.QRLinkIdTpt)) {
													CoMorbidityDto dto = QRConvertUtil.getComorbidities(
															questionnaireResponseItemComponent, null, HivConst.HivTpt);
													if (dto != null && dto.getCarePlan() != null) {
														children.add(dto.getCarePlan());
													}
													/////
												} else if (questionnaireResponseItemComponent.getLinkId() != null
														&& questionnaireResponseItemComponent.getLinkId()
																.equals(HivConst.QRLinkIdTbDiagnosisDate)) {
													if (tbDiagnosis == null) {
														tbDiagnosis = new Observation();
													}

													if (questionnaireResponseItemComponent.getAnswer() != null
															&& questionnaireResponseItemComponent.getAnswer()
																	.size() > 0) {
														tbDiagnosis.setIssued(questionnaireResponseItemComponent
																.getAnswer().get(0).getValueDateType().getValue());
													}

												} else if (questionnaireResponseItemComponent.getLinkId() != null
														&& questionnaireResponseItemComponent.getLinkId()
																.equals(HivConst.QRLinkIdTbTreatment)) {
													if (tbDiagnosis == null) {
														tbDiagnosis = new Observation();
													}
													CoMorbidityDto dto = QRConvertUtil.getComorbidities(
															questionnaireResponseItemComponent, tbDiagnosis,
															HivConst.HivTb);
													if (dto != null) {
														listCoMorbidity.add(dto);
													}

												}
											}
										}
										////////// ????????????????????

									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdHbvHcv)) {
										for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
												.getItem()) {
											if (questionnaireResponseItemComponent.getLinkId() != null
													&& questionnaireResponseItemComponent.getLinkId()
															.equals(HivConst.QRLinkIdHbv)) {
												CoMorbidityDto dto = QRConvertUtil.getComorbidities(
														questionnaireResponseItemComponent, hbv, HivConst.HivHbv);
												if (dto != null) {
													listCoMorbidity.add(dto);
												}
											} else if (questionnaireResponseItemComponent.getLinkId() != null
													&& questionnaireResponseItemComponent.getLinkId()
															.equals(HivConst.QRLinkIdHcv)) {

												CoMorbidityDto dto = QRConvertUtil.getComorbidities(
														questionnaireResponseItemComponent, hcv, HivConst.HivHcv);
												if (dto != null) {
													listCoMorbidity.add(dto);
												}

											}
										}
									}
								}
							}
						} //
							// ARV Treatment
						else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdArvTreatment)) {
							List<QuestionnaireResponseItemComponent> listChild = item.getItem();
							if (listChild != null && listChild.size() > 0) {
								HivArvTreatmentDto treatmentDto = new HivArvTreatmentDto();
								List<PlanDefinition> listPlanDefinition = new ArrayList<PlanDefinition>();

								Period period = new Period();
								ARVCarePlan carePlan = new ARVCarePlan();
//								carePlan.getA
								CarePlanActivityComponent carePlanActivityComponent = new CarePlanActivityComponent();
								CarePlanActivityDetailComponent detail = new CarePlanActivityDetailComponent();

								CodeableConcept codeableConceptPlanDetail = new CodeableConcept();
								Coding codePlanDetail = new Coding();
								List<Coding> listCodePlanDetail = new ArrayList<Coding>();
								codePlanDetail.setSystem(HivConst.ARVTreatment);
								codePlanDetail.setDisplay("ARV treatment");
								listCodePlanDetail.add(codePlanDetail);
								codeableConceptPlanDetail.setCoding(listCodePlanDetail);
								detail.setCode(codeableConceptPlanDetail);
								carePlanActivityComponent.setDetail(detail);
								carePlan.addActivity(carePlanActivityComponent);
								// CarePlan carePlan = new CarePlan();
								for (QuestionnaireResponseItemComponent child : listChild) {

									if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdInitiationDate)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											period.setStart(child.getAnswer().get(0).getValueDateType().getValue());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdArvTreatmentStopDate)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											period.setEnd(child.getAnswer().get(0).getValueDateType().getValue());
										}
									}

									else if (child.getLinkId() != null && child.getLinkId()
											.equals(HivConst.QRLinkIdArvTreatmentInitiationFacility)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											Reference refOrg = new Reference();
											Organization org = null;
											Bundle organizationBundle = QRConvertUtil.getOrganizationBundle(
													child.getAnswer().get(0).getValueStringType().asStringValue());
											if (organizationBundle != null && organizationBundle.getEntry() != null
													&& organizationBundle.getEntry().size() > 0) {
												org = (Organization) organizationBundle.getEntryFirstRep()
														.getResource();
											}
											if (org == null) {
												org = new Organization();
												org.setName(
														child.getAnswer().get(0).getValueStringType().asStringValue());
												MethodOutcome outcome = saveOrganization(org);
											}
											if (org != null) {
												refOrg.setReference(
														"Organization" + "/" + org.getIdElement().getIdPart());
												refOrg.setType(Organization.class.getName());
												refOrg.setDisplay(org.getName());
											}
											carePlan.setAuthor(refOrg);

										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdDateOfLossToFollowUp)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											carePlan.setDateOfLFTU(child.getAnswer().get(0).getValueDateType());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdDateOfTransferredOut)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											carePlan.setDateOfTransferOut(child.getAnswer().get(0).getValueDateType());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdPlaceOfTransferredOut)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											Reference refOrg = new Reference();
											Organization org = null;
											Bundle organizationBundle = QRConvertUtil.getOrganizationBundle(
													child.getAnswer().get(0).getValueStringType().asStringValue());
											if (organizationBundle != null && organizationBundle.getEntry() != null
													&& organizationBundle.getEntry().size() > 0) {
												org = (Organization) organizationBundle.getEntryFirstRep()
														.getResource();
											}
											if (org == null) {
												org = new Organization();
												org.setName(
														child.getAnswer().get(0).getValueStringType().asStringValue());
												MethodOutcome outcome = saveOrganization(org);
											}
											if (org != null) {
												refOrg.setReference(
														"Organization" + "/" + org.getIdElement().getIdPart());
												refOrg.setType(Organization.class.getName());
												refOrg.setDisplay(org.getName());
											}
											carePlan.setPlaceOfTransferOut(refOrg);
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdRegimen)) {
										if (child.getItem() != null && child.getItem().size() > 0) {
											PlanDefinition planDefinition = new PlanDefinition();
											CodeableConcept codeableConceptPlan = new CodeableConcept();
											Coding codePlan = new Coding();
											List<Coding> listCodePlan = new ArrayList<Coding>();
											codePlan.setSystem(HivConst.CsHivRegimen);
											codePlan.setDisplay("ARV treatment regimen");
											listCodePlan.add(codePlan);
											codeableConceptPlan.setCoding(listCodePlan);
											planDefinition.setType(codeableConceptPlan);
											Period planPeriod = new Period();
											for (QuestionnaireResponseItemComponent questionnaireResponseItemComponent : child
													.getItem()) {

												if (questionnaireResponseItemComponent.getLinkId()
														.equals(HivConst.QRLinkIdArvRegimenDateStarted)) {
													if (questionnaireResponseItemComponent.getAnswer() != null
															&& questionnaireResponseItemComponent.getAnswer()
																	.size() > 0) {
														planPeriod.setStart(questionnaireResponseItemComponent
																.getAnswer().get(0).getValueDateType().getValue());
													}
												} else if (questionnaireResponseItemComponent.getLinkId()
														.equals(HivConst.QRLinkIdArvRegimenDateStopped)) {
													if (questionnaireResponseItemComponent.getAnswer() != null
															&& questionnaireResponseItemComponent.getAnswer()
																	.size() > 0) {
														planPeriod.setEnd(questionnaireResponseItemComponent.getAnswer()
																.get(0).getValueDateType().getValue());
													}
												} else if (questionnaireResponseItemComponent.getLinkId()
														.equals(HivConst.QRLinkIdArvRegimenName)) {
													if (questionnaireResponseItemComponent.getAnswer() != null
															&& questionnaireResponseItemComponent.getAnswer()
																	.size() > 0) {
														planDefinition.setName(questionnaireResponseItemComponent
																.getAnswer().get(0).getValueCoding().getDisplay());
													}
												} else if (questionnaireResponseItemComponent.getLinkId()
														.equals(HivConst.QRLinkIdArvRegimenLine)) {
													if (questionnaireResponseItemComponent.getAnswer() != null
															&& questionnaireResponseItemComponent.getAnswer()
																	.size() > 0) {
														CodeableConcept codeableConceptLine = new CodeableConcept();
														codeableConceptLine.addCoding(questionnaireResponseItemComponent
																.getAnswer().get(0).getValueCoding());
														planDefinition.addTopic(codeableConceptLine);
													}
												} else if (questionnaireResponseItemComponent.getLinkId()
														.equals(HivConst.QRLinkIdArvRegimenChangeReason)) {
													if (questionnaireResponseItemComponent.getAnswer() != null
															&& questionnaireResponseItemComponent.getAnswer()

																	.size() > 0) {
														planDefinition.setPurpose(questionnaireResponseItemComponent
																.getAnswer().get(0).getValueCoding().getDisplay());
													}
												}

											}
											planDefinition.setEffectivePeriod(planPeriod);
											listPlanDefinition.add(planDefinition);
										}

									}

									carePlan.setPeriod(period);
									treatmentDto.setPlanDefinition(listPlanDefinition);
								}
								treatmentDto.setCarePlan(carePlan);
								listArvTreatmentDto.add(treatmentDto);
//								children.add(carePlan);
							}
							//////

						} else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdDeath)) {
							List<QuestionnaireResponseItemComponent> listChild = item.getItem();
							if (listChild != null && listChild.size() > 0) {
								for (QuestionnaireResponseItemComponent child : listChild) {

									if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdCauseOfDeath)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											patient.setCauseOfDeath(child.getAnswer().get(0).getValueCoding());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdDateOfDeath)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											patient.setDeceased(child.getAnswer().get(0).getValueDateTimeType());
										}
									}
								}
							}
						} else if (item.getLinkId() != null && item.getLinkId().equals(HivConst.QRLinkIdPregnancies)) {

							HivPregnancyDto hivPregnancyDto = new HivPregnancyDto();
							HivPregnancyObservation hivPregnancyObservation = new HivPregnancyObservation();
							List<HivChildDto> listChildDto = new ArrayList<HivChildDto>();

							//
							CodeableConcept codeableConceptPregnancy = new CodeableConcept();
							Coding codePregnancy = new Coding();
							List<Coding> listCodePregnancy = new ArrayList<Coding>();
							codePregnancy.setSystem(HivConst.VsHivPregnancy);

							//
							List<QuestionnaireResponseItemComponent> listItem = item.getItem();
							if (listItem != null && listItem.size() > 0) {
								for (QuestionnaireResponseItemComponent child : listItem) {
									HivChildDto hivChildDto = new HivChildDto();
									if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdDatePregnancyReported)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											hivPregnancyObservation.setDatePregnancyReported(
													child.getAnswer().get(0).getValueDateType());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdDeliveryPlace)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											hivPregnancyObservation.setDeliveryPlace(
													child.getAnswer().get(0).getValueStringType());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdPlaceReported)) {
										if (child.getAnswer() != null && child.getAnswer().size() > 0) {
											hivPregnancyObservation.setPlacePregnancyReported(
													child.getAnswer().get(0).getValueStringType());
										}
									} else if (child.getLinkId() != null
											&& child.getLinkId().equals(HivConst.QRLinkIdPregnancyOutcomes)) {
										List<QuestionnaireResponseItemComponent> listOutcomes = child.getItem();
										if (listOutcomes != null && listOutcomes.size() > 0) {
//											hivPregnancyObservation.setCode(codeableConceptPregnancy);
											for (QuestionnaireResponseItemComponent outcome : listOutcomes) {
												// HivPregnancyObservation obs = new HivPregnancyObservation();
												if (outcome.getLinkId() != null && outcome.getLinkId()
														.equals(HivConst.QRLinkIdPregnancyOutcomeCode)) {
													if (outcome.getAnswer() != null && outcome.getAnswer().size() > 0) {
														codePregnancy.setCode(
																outcome.getAnswer().get(0).getValueCoding().getCode());
														codePregnancy.setDisplay(outcome.getAnswer().get(0)
																.getValueCoding().getDisplay());
													}
												} else if (outcome.getLinkId() != null
														&& outcome.getLinkId().equals(HivConst.QRLinkIdDeliveryDate)) {
													if (outcome.getAnswer() != null && outcome.getAnswer().size() > 0) {
														hivPregnancyObservation.setEffective(
																outcome.getAnswer().get(0).getValueDateTimeType());
													}
												} else if (outcome.getLinkId() != null && outcome.getLinkId()
														.equals(HivConst.QRLinkIdGestationalAgeAtDelivery)) {
													if (outcome.getAnswer() != null && outcome.getAnswer().size() > 0) {
														hivPregnancyObservation.setValue(
																outcome.getAnswer().get(0).getValueIntegerType());
													}
												} else if (outcome.getLinkId() != null && outcome.getLinkId()
														.equals(HivConst.QRLinkIdPregnancyOutcomesChild)) {
													HivChildPatient hivChildPatient = new HivChildPatient();
//													HivChildDto childDto = new HivChildDto();
//													hivChildDto.setChild(hivChildPatient);
													List<HivPregnancyObservation> listObservation = new ArrayList<HivPregnancyObservation>();
													List<QuestionnaireResponseItemComponent> listOutcomesChild = outcome
															.getItem();
													if (listOutcomesChild != null && listOutcomesChild.size() > 0) {
														HivChildPatient hivChild = new HivChildPatient();
														HivPregnancyObservation obsChild = new HivPregnancyObservation();
														CodeableConcept childCode = new CodeableConcept();
														Coding coding = new Coding();
														List<Coding> listCoding = new ArrayList<Coding>();
														coding.setSystem(HivConst.PregnancyChild);
														coding.setDisplay(HivConst.PregnancyChild);
														listCoding.add(coding);
														childCode.setCoding(listCoding);
														obsChild.setCode(childCode);

														for (QuestionnaireResponseItemComponent outcomesChild : listOutcomesChild) {

															if (outcomesChild.getLinkId() != null
																	&& outcomesChild.getLinkId().equals(
																			HivConst.QRLinkIdPregnancyBirthWeight)) {
																if (outcomesChild.getAnswer() != null
																		&& outcomesChild.getAnswer().size() > 0) {
																	DecimalType type = new DecimalType(outcomesChild
																			.getAnswer().get(0).getValueStringType()
																			.asStringValue());
																	if (type != null) {
																		hivChild.setBirthWeight(type);
																	}
																}
															} else if (outcomesChild.getLinkId() != null
																	&& outcomesChild.getLinkId().equals(
																			HivConst.QRLinkIdPregnancyBirthDefects)) {
																if (outcomesChild.getAnswer() != null
																		&& outcomesChild.getAnswer().size() > 0) {
																	BooleanType booleanType = outcomesChild.getAnswer()
																			.get(0).getValueBooleanType();
																	hivChild.setBirthDefects(booleanType);
																}
															} else if (outcomesChild.getLinkId() != null
																	&& outcomesChild.getLinkId().equals(
																			HivConst.QRLinkIdPregnancyHivStatus)) {
																if (outcomesChild.getAnswer() != null
																		&& outcomesChild.getAnswer().size() > 0) {
																	hivChild.setHivStatus(outcomesChild.getAnswer()
																			.get(0).getValueCoding());
																}
															} else if (outcomesChild.getLinkId() != null
																	&& outcomesChild.getLinkId().equals(
																			HivConst.QRLinkIdHivDiagnosisDate)) {
																if (outcomesChild.getAnswer() != null
																		&& outcomesChild.getAnswer().size() > 0) {

																	
																	DateTimeType dateTimeType = new DateTimeType(outcomesChild.getAnswer().get(0)
																			.getValueDateType().getValue());
																	obsChild.setValue(dateTimeType);
																}
															} else if (outcomesChild.getLinkId() != null
																	&& outcomesChild.getLinkId().equals(
																			HivConst.QRLinkIdChildArvStartDate)) {
																if (outcomesChild.getAnswer() != null
																		&& outcomesChild.getAnswer().size() > 0) {
																	DateTimeType dateTimeType = new DateTimeType(outcomesChild.getAnswer().get(0)
																			.getValueDateType().getValue());
																	obsChild.setEffective(dateTimeType);

																}
															}

														}
														hivChildDto.setChild(hivChild);
														hivChildDto.setObservation(obsChild);
														listChildDto.add(hivChildDto);
														hivPregnancyDto.setListChild(listChildDto);
													}
												}
											}
										}
										hivPregnancyDto.setPregnancyObservation(hivPregnancyObservation);

									}
								}
							}
							listCodePregnancy.add(codePregnancy);
							codeableConceptPregnancy.setCoding(listCodePregnancy);
							hivPregnancyObservation.setCode(codeableConceptPregnancy);
							hivPregnancyObservation.setCode(codeableConceptPregnancy);
							pregnancies.add(hivPregnancyDto);
						}
						if (cd4Test != null) {
							listCoMorbidity.add(cd4Test);
						}
						// if (cd4DuringART != null) {
						// listCoMorbidity.add(cd4DuringART);
						// }
						if (vl4DuringART != null) {
							listCoMorbidity.add(vl4DuringART);
						}
						if (drugResistanceTest != null) {
							listCoMorbidity.add(drugResistanceTest);
						}
					}
				}
			}
		}
		result.setChildren(children);
		result.setPatient(patient);
		// System.out.print(patient.getId());
		// result.setComorbidities(comorbidities);
		result.setPregnancies(pregnancies);
		result.setListCoMorbidity(listCoMorbidity);
		result.setArvTreatmentDto(listArvTreatmentDto);
		if (pregnancies != null && pregnancies.size() > 0) {
			result.setPregnancies(pregnancies);
		}

		return result;

	}
}

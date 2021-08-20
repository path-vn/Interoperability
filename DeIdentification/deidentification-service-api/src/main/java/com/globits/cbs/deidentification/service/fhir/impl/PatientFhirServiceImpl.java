package com.globits.cbs.deidentification.service.fhir.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.ARVCarePlan;
import org.hl7.fhir.hiv.vietnam.r4.model.HIVSpecimen;
import org.hl7.fhir.hiv.vietnam.r4.model.HivChildPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivDiagnosisObservation;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.hiv.vietnam.r4.model.HivPregnancyObservation;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.PlanDefinition;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.globits.cbs.deidentification.dto.AddressDto;
import com.globits.cbs.deidentification.dto.ArvTreatmentDto;
import com.globits.cbs.deidentification.dto.ChildDto;
import com.globits.cbs.deidentification.dto.CoMorbidityDto;
import com.globits.cbs.deidentification.dto.CoMorbidityTreatmentDto;
import com.globits.cbs.deidentification.dto.EthnicityDto;
import com.globits.cbs.deidentification.dto.HivDiagnosisDto;
import com.globits.cbs.deidentification.dto.IndexTestingDto;
import com.globits.cbs.deidentification.dto.LabTestDto;
import com.globits.cbs.deidentification.dto.OccupationDto;
import com.globits.cbs.deidentification.dto.OrganizationDto;
import com.globits.cbs.deidentification.dto.PatientDeathDto;
import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.dto.PatientRegimenDto;
import com.globits.cbs.deidentification.dto.PregnancyDto;
import com.globits.cbs.deidentification.dto.SystemCodeDto;
import com.globits.cbs.deidentification.functiondto.SearchDto;
import com.globits.cbs.deidentification.service.PatientService;
import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.api.SortOrderEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IDelete;
import ca.uhn.fhir.rest.gclient.IDeleteTyped;
import ca.uhn.fhir.util.BundleUtil;

@Service
public class PatientFhirServiceImpl implements PatientService {
	public static String serverBaseUrl = "";
	static FhirContext ctx = FhirContext.forR4();
	static IParser jsonParser = ctx.newJsonParser();

	public static Bundle getPatientEverythingById(String theUrl) {
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		Bundle response = client.fetchResourceFromUrl(Bundle.class, theUrl);
		return response;
	}

	public static HIVSpecimen getSpecimenById(String theUrl) {
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		HIVSpecimen response = client.fetchResourceFromUrl(HIVSpecimen.class, theUrl);
		return response;
	}

	public static PlanDefinition getPlanDefinitionById(String theUrl) {
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		PlanDefinition response = client.fetchResourceFromUrl(PlanDefinition.class, theUrl);
		return response;
	}

	@Override
	public MethodOutcome deleteById(String id) {
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		IDelete response = client.delete();
		IDeleteTyped deleteType = response.resourceById(Patient.class.getSimpleName(), id);
		MethodOutcome outcome = deleteType.execute();
		return outcome;
	}

	private static PatientDto getPatientInformation(PatientDto dto, HivPatient response, String patientId) {
		dto.setBirthDate(response.getBirthDate());
		dto.setUrlID(patientId);
		dto.setId(patientId);
//		if (response.getLastUpdated() != null) {
//			SystemCodeDto systemCodeDto = new SystemCodeDto();
//			systemCodeDto.setCode(response.getLastUpdated().getCode());
//			systemCodeDto.setDisplay(response.getLastUpdated().getDisplay());
//			dto.setLastUpdated(systemCodeDto);
//		}
		if (response.getSyncOrg() != null && response.getSyncOrg().getCoding() != null
				&& response.getSyncOrg().getCoding().size() > 0) {

			List<SystemCodeDto> listData = new ArrayList<SystemCodeDto>();
			SystemCodeDto systemCodeDto = new SystemCodeDto();
			systemCodeDto.setCode(response.getSyncOrg().getCoding().get(0).getCode());
			systemCodeDto.setDisplay(response.getSyncOrg().getCoding().get(0).getDisplay());
			dto.setLastUpdated(systemCodeDto);
			for (Coding coding : response.getSyncOrg().getCoding()) {
				SystemCodeDto systemCode = new SystemCodeDto();
				systemCode.setCode(coding.getCode());
				systemCode.setDisplay(coding.getDisplay());
				listData.add(systemCode);
			}
			dto.setSyncOrg(listData);
		}
		if (response.getGenderIdentity() != null && response.getGenderIdentity().getCoding() != null
				&& response.getGenderIdentity().getCoding().size() > 0) {
			SystemCodeDto systemCodeDto = new SystemCodeDto();
			systemCodeDto.setCode(response.getGenderIdentity().getCoding().get(0).getCode());
			systemCodeDto.setDisplay(response.getGenderIdentity().getCoding().get(0).getDisplay());
			dto.setGender(systemCodeDto);
		}
		if (response.getEthnicity() != null && response.getEthnicity().getCoding() != null
				&& response.getEthnicity().getCoding().size() > 0) {
			EthnicityDto systemCodeDto = new EthnicityDto();
			systemCodeDto.setCode(response.getEthnicity().getCoding().get(0).getCode());
			systemCodeDto.setName(response.getEthnicity().getCoding().get(0).getDisplay());
			dto.setEthnicity(systemCodeDto);
		}
		if (response.getOccupation() != null) {
			OccupationDto systemCodeDto = new OccupationDto();
			systemCodeDto.setCode(response.getOccupation().getCode());
			systemCodeDto.setDisplay(response.getOccupation().getDisplay());
			dto.setOccupation(systemCodeDto);
		}
		if (response.getIdentifier() != null && response.getIdentifier().size() > 0) {
			IndexTestingDto indexTestingDto = new IndexTestingDto();
			for (Identifier identifier : response.getIdentifier()) {
				if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.PassportIdentifierNamingSystem)) {
					dto.setPassportNumber(identifier.getValue());
				} else if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.InsuranceIdentifierNamingSystem)) {
					dto.setHealthInsuranceNumber(identifier.getValue());
				} else if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.ArvIdentifierNamingSystem)) {
					dto.setArtID(identifier.getValue());
				} else if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.National9IdentifierNamingSystem)) {
					dto.setNationalID(identifier.getValue());
				}else if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.IndexCaseNationalId12IdentifierNamingSystem)) {
					indexTestingDto.setIndexCaseNationalId12(identifier.getValue());
				}else if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.IndexCaseNationalId9IdentifierNamingSystem)) {
					indexTestingDto.setIndexCaseNationalId9(identifier.getValue());
				}else if (identifier.getSystem() != null
						&& identifier.getSystem().equals(HivConst.IndexCaseUidIdentifierNamingSystem)) {
					indexTestingDto.setIndexCaseUid(identifier.getValue());
				}
			}
			dto.setIndexTesting(indexTestingDto);
		}
		/*
		 * Convert name information
		 */
		if (response.getName() != null && response.getName().size() > 0) {
			dto.setName(response.getName().get(0).getText());
			if (response.getName().get(0).getGiven() != null && response.getName().get(0).getGiven().size() > 0) {
				dto.setFirstName(response.getName().get(0).getFamily());
			}

			dto.setLastName(response.getName().get(0).getGiven().get(0).getValueAsString());
		}

		if (dto.getName() == null && dto.getFirstName() != null && dto.getLastName() != null) {
			dto.setName(dto.getFirstName() + " " + dto.getLastName());
		}

		/*
		 * Convert Address Information
		 */

		if (response.getAddress() != null && response.getAddress().size() > 0) {

			for (Address add : response.getAddress()) {
				if (add.getExtension() != null && add.getExtension().size() > 0) {
					for (Extension ex : add.getExtension()) {
						if (ex.getUrl().equals("Temporary")) {
							AddressDto address = new AddressDto();
							if (add.getExtension() != null && add.getExtension().size() > 0) {
								for (Extension extension : ex.getExtension()) {
									if (extension.getUrl().equals("commune")) {
										Coding code = (Coding) extension.getValue();
										address.setCommuneName(code.getDisplay());
										address.setCommuneCode(code.getCode());
									} else if (extension.getUrl().equals("district")) {
										Coding code = (Coding) extension.getValue();
										address.setDistrictCode(code.getCode());
										address.setDistrictName(code.getDisplay());
									} else if (extension.getUrl().equals("province")) {
										Coding code = (Coding) extension.getValue();
										address.setCityCode(code.getCode());
										;
										address.setCityName(code.getDisplay());
									}
								}
							}
							if (address.getCityName() != null && address.getDistrictName() != null
									&& address.getCommuneName() != null) {
								address.setText(address.getCommuneName() + ", " + address.getDistrictName() + ", "
										+ address.getCityName());
							}
							if (address.getText() == null && address.getCityName() != null
									&& address.getDistrictName() != null) {
								address.setText(address.getDistrictName() + ", " + address.getCityName());
							}
							if (address.getText() == null && address.getCityName() != null) {
								address.setText(address.getCityName());
							}
							dto.setCurrentAddress(address);

						} else if (ex.getUrl().equals("Home")) {
							AddressDto address = new AddressDto();
							if (add.getExtension() != null && add.getExtension().size() > 0) {
								for (Extension extension : ex.getExtension()) {
									if (extension.getUrl().equals("commune")) {
										Coding code = (Coding) extension.getValue();
										address.setCommuneName(code.getDisplay());
										address.setCommuneCode(code.getCode());
									} else if (extension.getUrl().equals("district")) {
										Coding code = (Coding) extension.getValue();
										address.setDistrictCode(code.getCode());
										address.setDistrictName(code.getDisplay());
									} else if (extension.getUrl().equals("province")) {
										Coding code = (Coding) extension.getValue();
										address.setCityCode(code.getCode());
										;
										address.setCityName(code.getDisplay());
									}
								}
							}
							if (address.getCityName() != null && address.getDistrictName() != null
									&& address.getCommuneName() != null) {
								address.setText(address.getCommuneName() + ", " + address.getDistrictName() + ", "
										+ address.getCityName());
							}
							if (address.getText() == null && address.getCityName() != null
									&& address.getDistrictName() != null) {
								address.setText(address.getDistrictName() + ", " + address.getCityName());
							}
							if (address.getText() == null && address.getCityName() != null) {
								address.setText(address.getCityName());
							}

							dto.setRegisteredAddress(address);
						}
					}
				}

//				if (Address.AddressUse.HOME.equals(add.getUse())) {
//					address.setCityName(add.getState());
//					address.setCountryName(add.getCountry());
//					address.setDistrictName(add.getDistrict());
//					address.setCommuneName(add.getCity());
//					if (add.getLine() != null && add.getLine().size() > 0) {
//						address.setLine(add.getLine().get(0).asStringValue());
//					}
//					address.setText(add.getText());
//					dto.setRegisteredAddress(address);
//				} else if (Address.AddressUse.TEMP.equals(add.getUse())) {
//					address.setCityName(add.getState());
//					address.setCountryName(add.getCountry());
//					address.setDistrictName(add.getDistrict());
//					address.setCommuneName(add.getCity());
//					if (add.getLine() != null && add.getLine().size() > 0) {
//						address.setLine(add.getLine().get(0).asStringValue());
//					}
//					address.setText(add.getText());
//					if (address.getText() == null && address.getCommuneName() != null
//							&& address.getDistrictName() != null && address.getCityName() != null) {
//						address.setText(address.getCommuneName()+", "+address.getDistrictName() +", "+address.getCityName());
//					}
//					if (address.getText() == null 
//							&& address.getDistrictName() != null && address.getCityName() != null) {
//						address.setText(address.getDistrictName() +", "+address.getCityName());
//					}
//					if (address.getText() == null &&  address.getCityName() != null) {
//						address.setText(address.getCityName());
//					}
//						dto.setCurrentAddress(address);
//				}
			}
		}
		return dto;
	}

	private static LabTestDto getLabTest(LabTestDto labTestObservation, HivDiagnosisObservation hivObservation) {
		HIVSpecimen hivSpecimen = null;

		if (hivObservation != null && hivObservation.getSpecimen() != null
				&& hivObservation.getSpecimen().getReference() != null) {
			String url = HivConst.serverFhirUrl + "/" + hivObservation.getSpecimen().getReference();
			hivSpecimen = PatientFhirServiceImpl.getSpecimenById(hivObservation.getSpecimen().getReference());
		}

		if (hivObservation.getPerformer() != null && hivObservation.getPerformer().size() > 0) {
			labTestObservation.setName(hivObservation.getPerformer().get(0).getDisplay());
		}
		labTestObservation.setTestPerformanceDate(hivObservation.getIssued());
		if (hivSpecimen != null && hivSpecimen.getCollection() != null
				&& hivSpecimen.getCollection().getCollectedDateTimeType() != null) {
			labTestObservation
					.setSpecimenCollectionDate(hivSpecimen.getCollection().getCollectedDateTimeType().getValue());
		}
		if (hivSpecimen != null && hivSpecimen.getCollection() != null
				&& hivSpecimen.getSpecimenCollectionPlace() != null) {
			labTestObservation.setSpecimenCollectionPlace(hivSpecimen.getSpecimenCollectionPlace().asStringValue());
		}
		if (hivObservation.hasValueIntegerType()) {
			labTestObservation.setStringValue(hivObservation.getValueIntegerType().getValueAsString());
			labTestObservation.setValueNumber(hivObservation.getValueIntegerType().getValue());
		}
		if (hivObservation.hasValueCodeableConcept() && hivObservation.getValueCodeableConcept().getCoding() != null
				&& hivObservation.getValueCodeableConcept().getCoding().size() > 0) {
			SystemCodeDto systemCodeDto = new SystemCodeDto();
			systemCodeDto.setCode(hivObservation.getValueCodeableConcept().getCoding().get(0).getCode());
			systemCodeDto.setDisplay(hivObservation.getValueCodeableConcept().getCoding().get(0).getDisplay());
			labTestObservation.setTestResultOther(systemCodeDto);
		}
		return labTestObservation;
	}

	@Override
	public PatientDto getPatient(String patientId) {
//			HivPatient hivPatient =  getPatientById(patientId);
//			System.out.println(hivPatient.getId());
		String theUrl = HivConst.serverFhirUrl + "/Patient/" + patientId + "/$everything?_count=1000";
		Bundle bundle = PatientFhirServiceImpl.getPatientEverythingById(theUrl);
		Patient hl7Patient = null;
		if (bundle != null && bundle.getEntry() != null && bundle.getEntry().size() > 0) {
			hl7Patient = (Patient) bundle.getEntryFirstRep().getResource();
		}
//			IParser parser = ctx.newJsonParser();
		HivPatient response = null;
		if (hl7Patient != null) {
			String json = jsonParser.encodeResourceToString(hl7Patient);

			response = jsonParser.parseResource(HivPatient.class, json);
		}
		// HivPatient response= client.fetchResourceFromUrl(HivPatient.class,
		// patientId);
		if (response != null) {
			PatientDto dto = new PatientDto();
			dto.setId(patientId);
			/* DungHQ add 31/05/2021 */
			dto.setLastUpdateDate(hl7Patient.getMeta().getLastUpdated());
			/* End DungHQ add 31/05/2021 */
			dto = PatientFhirServiceImpl.getPatientInformation(dto, response, patientId);
			/*
			 * Convert treatment Status
			 */

			if (response.getTreatmentStatus() != null) {
				SystemCodeDto systemCodeDto = new SystemCodeDto();
				systemCodeDto.setCode(response.getTreatmentStatus().getCode());
				systemCodeDto.setDisplay(response.getTreatmentStatus().getDisplay());
				dto.setTreatmentStatus(systemCodeDto);
			}
			/*
			 * Convert Cause Of Death
			 */
			PatientDeathDto patientDeathDto = new PatientDeathDto();
			if (response.getCauseOfDeath() != null) {
				patientDeathDto.setCauseOfDeath(response.getCauseOfDeath().getDisplay());

			}
			/*
			 * Convert Cause Of Death
			 */

			if (response.getDeceasedDateTimeType() != null) {
				patientDeathDto.setDateOfDeath(response.getDeceasedDateTimeType().getValue());
			}
			/*
			 * Convert Text
			 */

			/*
			 * Convert HIV Diagnosis Infomation
			 */

			HivDiagnosisDto diagnosis = new HivDiagnosisDto();
			LabTestDto cd4BeforeArt = new LabTestDto();
			List<LabTestDto> cd4List = new ArrayList<LabTestDto>();
			List<SystemCodeDto> riskPopulations = new ArrayList<SystemCodeDto>();
			List<SystemCodeDto> riskBehaviors = new ArrayList<SystemCodeDto>();
			List<SystemCodeDto> transmissionRoutes = new ArrayList<SystemCodeDto>();
			List<LabTestDto> viralLoadList = new ArrayList<LabTestDto>();
			LabTestDto rapidRecencyTest = new LabTestDto();
			LabTestDto viralLoadRecencyTest = new LabTestDto();
			List<CoMorbidityDto> listOfHbv = new ArrayList<CoMorbidityDto>();
			List<CoMorbidityDto> listOfHcv = new ArrayList<CoMorbidityDto>();
			List<CoMorbidityDto> listOfTb = new ArrayList<CoMorbidityDto>();
			List<CoMorbidityTreatmentDto> listOfTpt = new ArrayList<CoMorbidityTreatmentDto>();
			List<ArvTreatmentDto> listOfArvTreatment = new ArrayList<ArvTreatmentDto>();
			List<PatientRegimenDto> regimens = new ArrayList<PatientRegimenDto>();
			List<CoMorbidityTreatmentDto> listCoMorbidityTreatment = new ArrayList<CoMorbidityTreatmentDto>();
			List<LabTestDto> drugResistanceList = new ArrayList<LabTestDto>();
			List<PregnancyDto> listPregnancy = new ArrayList<PregnancyDto>();
			List<ChildDto> childs = new ArrayList<ChildDto>();
			List<HivChildPatient> childPatient = new ArrayList<HivChildPatient>();
			List<HivPregnancyObservation> hivPregnancy = new ArrayList<HivPregnancyObservation>();
//		List<LabTestDto> cd4List = new ArrayList<LabTestDto>();
			if (bundle != null && bundle.getEntry() != null && bundle.getEntry().size() > 0) {
				for (BundleEntryComponent item : bundle.getEntry()) {
//		    		diagnosis =PatientFhirServiceImpl.getHivDiagnosisDto(item, diagnosis);
					CoMorbidityDto hbv = null;
					CoMorbidityDto tb = null;
					CoMorbidityDto hcv = null;
					CoMorbidityTreatmentDto tpt = null;
					ArvTreatmentDto arvTreatmentDto = null;
//					Date dateTb = null;
					if (item.getResource().fhirType().equals("Observation")) {
						Observation observation = new Observation();
						observation = (Observation) item.getResource();
						if (observation.getCode() != null && observation.getCode().getCoding() != null
								&& observation.getCode().getCoding().size() > 0) {
							String jsonObs = jsonParser.encodeResourceToString(observation);
							HivDiagnosisObservation hivObservation = jsonParser
									.parseResource(HivDiagnosisObservation.class, jsonObs);
//							HivDiagnosisObservation hivObservation = (HivDiagnosisObservation)item.getResource();
							if (observation.getCode().getCoding().get(0).getSystem().equals(HivConst.VsDiagnosisObs)) {
								HIVSpecimen hivSpecimen = null;

								if (hivObservation != null && hivObservation.getSpecimen() != null
										&& hivObservation.getSpecimen().getReference() != null) {
									String url = HivConst.serverFhirUrl + "/"
											+ hivObservation.getSpecimen().getReference();
									hivSpecimen = PatientFhirServiceImpl
											.getSpecimenById(hivObservation.getSpecimen().getReference());
								}
								diagnosis.setConfirmatoryDate(hivObservation.getIssued());
								OrganizationDto organizationDto = new OrganizationDto();
								if (hivObservation.getPerformer() != null && hivObservation.getPerformer().size() > 0) {
									// Organization org =
									// (Organization)observation.getPerformer().get(0).getResource();
									organizationDto.setCode(hivObservation.getPerformer().get(0).getId());
									organizationDto.setName(hivObservation.getPerformer().get(0).getDisplay());
								}
								diagnosis.setConfirmatoryLab(organizationDto);
								if (hivSpecimen != null && hivSpecimen.getCollection() != null
										&& hivSpecimen.getCollection().getCollectedDateTimeType() != null) {
									diagnosis.setSpecimenCollectionDate(
											hivSpecimen.getCollection().getCollectedDateTimeType().getValue());
								}
								if (hivSpecimen != null && hivSpecimen.getCollection() != null
										&& hivSpecimen.getSpecimenCollectionPlace() != null) {
									diagnosis.setPlaceOfSpecimenCollection(
											hivSpecimen.getSpecimenCollectionPlace().asStringValue());
								}
//								if (hivObservation.getSpecimenCollectionPlace() != null) {
//									diagnosis.setPlaceOfSpecimenCollection(
//											hivObservation.getSpecimenCollectionPlace().getValueAsString());
//								}
//
//								try {
//									if (hivObservation != null && hivObservation.getSpecimen() != null
//											&& hivObservation.getSpecimen().getDisplay() != null) {
//										Date date = new SimpleDateFormat("yyyy")
//												.parse(hivObservation.getSpecimen().getDisplay());
//										diagnosis.setSpecimenCollectionDate(date);
//									}
//								} catch (FHIRException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								} catch (ParseException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}

							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.HivCd4TestObservation)) {
								LabTestDto labTestObservation = new LabTestDto();
								labTestObservation = PatientFhirServiceImpl.getLabTest(labTestObservation,
										hivObservation);
								if (labTestObservation != null) {
									cd4List.add(labTestObservation);
								}
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.HivVl4DuringArtObservation)) {
								LabTestDto labTestObservation = new LabTestDto();
								labTestObservation = PatientFhirServiceImpl.getLabTest(labTestObservation,
										hivObservation);
								if (labTestObservation != null) {
									viralLoadList.add(labTestObservation);
								}
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.HivDuringResistanceTestObservation)) {
								LabTestDto labTestObservation = new LabTestDto();
								labTestObservation = PatientFhirServiceImpl.getLabTest(labTestObservation,
										hivObservation);
								if (labTestObservation != null) {
									drugResistanceList.add(labTestObservation);
								}
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.VsHivRapidTest)) {
								rapidRecencyTest = PatientFhirServiceImpl.getLabTest(rapidRecencyTest, hivObservation);
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.VsHivVLTest)) {
								viralLoadRecencyTest = PatientFhirServiceImpl.getLabTest(viralLoadRecencyTest,
										hivObservation);
							} else if (hivObservation.getCode().getCoding().get(0).getSystem().equals(HivConst.HivTb)) {
								if (tb == null) {
									tb = new CoMorbidityDto();
								}
								tb.setDiagnosisDate(hivObservation.getIssued());
								if (hivObservation.getSubject() != null
										&& hivObservation.getSubject().getReference() != null) {
									if (hivObservation.getBasedOn() != null && hivObservation.getBasedOn().size() > 0) {
										tb.setTreatmentId(hivObservation.getBasedOn().get(0).getReference());
									}
								}
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.HivHbv)) {
								if (hbv == null) {
									hbv = new CoMorbidityDto();
								}
								hbv.setDiagnosisDate(hivObservation.getIssued());
								if (hivObservation.getSubject() != null
										&& hivObservation.getSubject().getReference() != null) {
									if (hivObservation.getBasedOn() != null && hivObservation.getBasedOn().size() > 0) {
										hbv.setTreatmentId(hivObservation.getBasedOn().get(0).getReference());
									}
								}
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.HivHcv)) {
								if (hcv == null) {
									hcv = new CoMorbidityDto();
								}
								hcv.setDiagnosisDate(hivObservation.getIssued());
								if (hivObservation.getBasedOn() != null && hivObservation.getBasedOn().size() > 0) {
									hcv.setTreatmentId(hivObservation.getBasedOn().get(0).getReference());
								}
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.VsHivPregnancy)) {
								String json = jsonParser.encodeResourceToString(hivObservation);

								HivPregnancyObservation hivPregnancyObservation = jsonParser
										.parseResource(HivPregnancyObservation.class, json);
								PregnancyDto pregnancyDto = new PregnancyDto();
								if (hivPregnancyObservation.getDatePregnancyReported() != null) {
									pregnancyDto.setDateReported(
											hivPregnancyObservation.getDatePregnancyReported().getValue());
								}
								if (hivPregnancyObservation.getPlacePregnancyReported() != null) {
									OrganizationDto org = new OrganizationDto();
									org.setName(hivPregnancyObservation.getPlacePregnancyReported().asStringValue());
									pregnancyDto.setPlaceReported(org);
								}
								if (hivPregnancyObservation.getDeliveryPlace() != null) {
									OrganizationDto org = new OrganizationDto();
									org.setName(hivPregnancyObservation.getDeliveryPlace().asStringValue());
									pregnancyDto.setDeliveryPlace(org);
								}
								if (hivPregnancyObservation.getCode() != null
										&& hivPregnancyObservation.getCode().getCoding() != null
										&& hivPregnancyObservation.getCode().getCoding().size() > 0) {
									SystemCodeDto code = new SystemCodeDto();
									code.setCode(hivPregnancyObservation.getCode().getCoding().get(0).getCode());
									code.setDisplay(hivPregnancyObservation.getCode().getCoding().get(0).getDisplay());
									pregnancyDto.setOutcomeCode(code);
								}
								if (hivPregnancyObservation.getEffectiveDateTimeType() != null
										&& hivPregnancyObservation.getEffectiveDateTimeType().getValue() != null) {
									Date date = hivPregnancyObservation.getEffectiveDateTimeType().getValue();
									List<Date> listDate = new ArrayList<Date>();
									listDate.add(date);
									pregnancyDto.setDeliveryDate(listDate);
								}
								if (hivPregnancyObservation.getValueIntegerType() != null
										&& hivPregnancyObservation.getValueIntegerType().getValue() != null) {
									Integer number = hivPregnancyObservation.getValueIntegerType().getValue();
									List<Integer> listNumber = new ArrayList<Integer>();
									listNumber.add(number);
									pregnancyDto.setGestationalAgeAtDelivery(listNumber);;
								}
								if (hivPregnancyObservation.getHasMember() != null
										&& hivPregnancyObservation.getHasMember().size() > 0) {
									for (Reference ref : hivPregnancyObservation.getHasMember()) {
										ChildDto childDto = new ChildDto();
										childDto.setObsId(ref.getReference());
										childs.add(childDto);
									}

									pregnancyDto.setChilds(childs);
								}
								listPregnancy.add(pregnancyDto);
							} else if (hivObservation.getCode().getCoding().get(0).getSystem()
									.equals(HivConst.PregnancyChild)) {
								String json = jsonParser.encodeResourceToString(hivObservation);

								HivPregnancyObservation pregnancyChild = jsonParser
										.parseResource(HivPregnancyObservation.class, json);
								hivPregnancy.add(pregnancyChild);
							}

						}
					} else if (item.getResource().fhirType().equals(Condition.class.getSimpleName())) {
						Condition condition = (Condition) item.getResource();
						if (condition.getCategory() != null && condition.getCategory().size() > 0) {
							for (CodeableConcept child : condition.getCategory()) {
								if (child.getCoding() != null && child.getCoding().size() > 0) {
									if (child.getCoding().get(0).getSystem().equals(HivConst.CsHivRiskBehavior)) {
										diagnosis.setRiskBehavior(new SystemCodeDto());
										diagnosis.getRiskBehavior().setCode(child.getCoding().get(0).getCode());
										diagnosis.getRiskBehavior().setDisplay(child.getCoding().get(0).getDisplay());
									}
									if (child.getCoding().get(0).getSystem().equals(HivConst.CsHivPopulation)) {
										diagnosis.setRiskPopulation(new SystemCodeDto());
										diagnosis.getRiskPopulation().setCode(child.getCoding().get(0).getCode());
										diagnosis.getRiskPopulation().setDisplay(child.getCoding().get(0).getDisplay());
									}
									if (child.getCoding().get(0).getSystem().equals(HivConst.CsHivTransmissionRoute)) {
										diagnosis.setTransmissionRoute(new SystemCodeDto());
										diagnosis.getTransmissionRoute().setCode(child.getCoding().get(0).getCode());
										diagnosis.getTransmissionRoute()
												.setDisplay(child.getCoding().get(0).getDisplay());
									}
								}
							}
						}

					} else if (item.getResource().fhirType().equals(CarePlan.class.getSimpleName())) {
						CarePlan carePlan = new CarePlan();
						carePlan = (CarePlan) item.getResource();
						CoMorbidityTreatmentDto coMorbidityTreatmentDto = new CoMorbidityTreatmentDto();
						if (carePlan != null && carePlan.getActivity() != null && carePlan.getActivity().size() > 0
								&& carePlan.getActivity().get(0).getDetail() != null) {
							if (carePlan.getActivity().get(0).getDetail().getCode() != null
									&& carePlan.getActivity().get(0).getDetail().getCode().getCoding() != null
									&& carePlan.getActivity().get(0).getDetail().getCode().getCoding().size() > 0) {
								if (carePlan.getActivity().get(0).getDetail().getCode().getCoding().get(0).getSystem()
										.equals(HivConst.HivHbv)) {
									coMorbidityTreatmentDto.setStartDate(carePlan.getPeriod().getStart());
									coMorbidityTreatmentDto.setEndDate(carePlan.getPeriod().getEnd());
									coMorbidityTreatmentDto
											.setTreatmentId("CarePlan" + "/" + carePlan.getIdElement().getIdPart());
									if (carePlan.getAuthor() != null) {
										coMorbidityTreatmentDto.setPlaceProvided(carePlan.getAuthor().getDisplay());
									}
//								hbv.setTreatment(coMorbidityTreatmentDto);
									listCoMorbidityTreatment.add(coMorbidityTreatmentDto);

								} else if (carePlan.getActivity().get(0).getDetail().getCode().getCoding().get(0)
										.getSystem().equals(HivConst.HivTpt)) {
									if (tpt == null) {
										tpt = new CoMorbidityTreatmentDto();
									}
									tpt.setStartDate(carePlan.getPeriod().getStart());
									tpt.setEndDate(carePlan.getPeriod().getEnd());
									tpt.setTreatmentId("CarePlan" + "/" + carePlan.getIdElement().getIdPart());
									if (carePlan.getAuthor() != null) {
										tpt.setPlaceProvided(carePlan.getAuthor().getDisplay());
									}

								} else if (carePlan.getActivity().get(0).getDetail().getCode().getCoding().get(0)
										.getSystem().equals(HivConst.HivHcv)) {
									coMorbidityTreatmentDto.setStartDate(carePlan.getPeriod().getStart());
									coMorbidityTreatmentDto.setEndDate(carePlan.getPeriod().getEnd());
									coMorbidityTreatmentDto
											.setTreatmentId("CarePlan" + "/" + carePlan.getIdElement().getIdPart());
									if (carePlan.getAuthor() != null) {
										coMorbidityTreatmentDto.setPlaceProvided(carePlan.getAuthor().getDisplay());
									}
//									hcv.setTreatment(coMorbidityTreatmentDto);
									listCoMorbidityTreatment.add(coMorbidityTreatmentDto);
								} else if (carePlan.getActivity().get(0).getDetail().getCode().getCoding().get(0)
										.getSystem().equals(HivConst.HivTb)) {

									coMorbidityTreatmentDto.setStartDate(carePlan.getPeriod().getStart());
									coMorbidityTreatmentDto.setEndDate(carePlan.getPeriod().getEnd());
									coMorbidityTreatmentDto
											.setTreatmentId("CarePlan" + "/" + carePlan.getIdElement().getIdPart());
									if (carePlan.getAuthor() != null) {
										coMorbidityTreatmentDto.setPlaceProvided(carePlan.getAuthor().getDisplay());
									}
//									hcv.setTreatment(coMorbidityTreatmentDto);
									listCoMorbidityTreatment.add(coMorbidityTreatmentDto);
								} else if (carePlan.getActivity().get(0).getDetail().getCode().getCoding().get(0)
										.getSystem().equals(HivConst.ARVTreatment)) {
									String json = jsonParser.encodeResourceToString(carePlan);
									ARVCarePlan aRVCarePlan = jsonParser.parseResource(ARVCarePlan.class, json);
									if (arvTreatmentDto == null) {
										arvTreatmentDto = new ArvTreatmentDto();
									}
									if (carePlan.getPeriod() != null) {
										arvTreatmentDto.setArvStartDate(carePlan.getPeriod().getStart());
										arvTreatmentDto.setArvStopDate(carePlan.getPeriod().getEnd());
									}
									OrganizationDto organizationDto = new OrganizationDto();
									if (carePlan.getAuthor() != null) {
										organizationDto.setName(carePlan.getAuthor().getDisplay());
										arvTreatmentDto.setOrg(organizationDto);
									}
									if (aRVCarePlan.getPlaceOfTransferOut() != null) {
										organizationDto.setName(aRVCarePlan.getPlaceOfTransferOut().getDisplay());
										arvTreatmentDto.setPlaceOfTransferOut(organizationDto);
									}
									if (carePlan.getSupportingInfo() != null
											&& carePlan.getSupportingInfo().size() > 0) {
										for (Reference ref : carePlan.getSupportingInfo()) {
											if (ref.getReference() != null) {
												PlanDefinition plan = PatientFhirServiceImpl
														.getPlanDefinitionById(ref.getReference());
												if (plan != null) {
													PatientRegimenDto patientRegimenDto = new PatientRegimenDto();
													if (plan.getName() != null) {
														patientRegimenDto.setName(plan.getName());
													}
													if (plan.getEffectivePeriod() != null) {
														if (plan.getEffectivePeriod().getEnd() != null) {
															patientRegimenDto
																	.setEndDate(plan.getEffectivePeriod().getEnd());
														}
														if (plan.getEffectivePeriod().getStart() != null) {
															patientRegimenDto
																	.setStartDate(plan.getEffectivePeriod().getStart());
														}
													}
													if (plan.getTopic() != null && plan.getTopic().size() > 0
															&& plan.getTopic().get(0).getCoding() != null && plan.getTopic().get(0).getCoding().size()>0) {
														SystemCodeDto code = new SystemCodeDto();
														code.setCode(plan.getTopic().get(0).getCoding().get(0).getCode());
														code.setDisplay(plan.getTopic().get(0).getCoding().get(0).getDisplay());
														patientRegimenDto.setLine(code);
													}
													regimens.add(patientRegimenDto);
												}
											}
										}
									}

									listOfArvTreatment.add(arvTreatmentDto);
								}
							}
						}
					} else if (item.getResource().fhirType().equals(Patient.class.getSimpleName())) {
						Patient patientChild = (Patient) item.getResource();
						if (patientChild.getMeta().getProfile() != null
								&& patientChild.getMeta().getProfile().size() > 0 && patientChild.getMeta().getProfile()
										.get(0).getValueAsString().equals(HivConst.HIVChildPatient)) {
							String json = jsonParser.encodeResourceToString(patientChild);
							HivChildPatient hivChildPatient = jsonParser.parseResource(HivChildPatient.class, json);
							childPatient.add(hivChildPatient);
						}
					}
					if (hbv != null) {
						listOfHbv.add(hbv);
					}
					if (hcv != null) {
						listOfHcv.add(hcv);
					}
					if (tb != null) {
						listOfTb.add(tb);
					}
					if (tpt != null) {
						listOfTpt.add(tpt);
					}
					///////
				}
			}
///

//		listCoMorbidityTreatment
			if (listCoMorbidityTreatment != null && listCoMorbidityTreatment.size() > 0) {
				for (CoMorbidityTreatmentDto coMorbidityTreatmentDto : listCoMorbidityTreatment) {
					if (listOfHbv != null && listOfHbv.size() > 0) {
						for (CoMorbidityDto hbv : listOfHbv) {
							if (hbv.getTreatmentId() != null && coMorbidityTreatmentDto.getTreatmentId() != null
									&& hbv.getTreatmentId().equals(coMorbidityTreatmentDto.getTreatmentId())) {
								hbv.setTreatment(coMorbidityTreatmentDto);
							}
						}
					}
					if (listOfHcv != null && listOfHcv.size() > 0) {
						for (CoMorbidityDto hcv : listOfHcv) {
							if (hcv.getTreatmentId() != null && coMorbidityTreatmentDto.getTreatmentId() != null
									&& hcv.getTreatmentId().equals(coMorbidityTreatmentDto.getTreatmentId())) {
								hcv.setTreatment(coMorbidityTreatmentDto);
							}
						}
					}
					if (listOfTb != null && listOfTb.size() > 0) {
						for (CoMorbidityDto tb : listOfTb) {
							if (tb.getTreatmentId() != null && coMorbidityTreatmentDto.getTreatmentId() != null
									&& tb.getTreatmentId().equals(coMorbidityTreatmentDto.getTreatmentId())) {
								tb.setTreatment(coMorbidityTreatmentDto);
							}
						}
					}
				}
			}
			////
			if (listPregnancy != null && listPregnancy.size() > 0) {
				for (PregnancyDto pregnancy : listPregnancy) {
					if (pregnancy.getChilds() != null && pregnancy.getChilds().size() > 0) {
						for (ChildDto child : pregnancy.getChilds()) {
							if (hivPregnancy != null && hivPregnancy.size() > 0) {
								for (HivPregnancyObservation observation : hivPregnancy) {
									String strId = "Observation/" + observation.getIdElement().getIdPart();
									if (child.getObsId().equals(strId)) {
										if (observation.getEffectiveDateTimeType() != null
												&& observation.getEffectiveDateTimeType().getValue() != null) {
											child.setChildArvStartDate(
													observation.getEffectiveDateTimeType().getValue());
										}
										if (observation.getValueDateTimeType() != null
												&& observation.getValueDateTimeType().getValue() != null) {
											child.setHivDiagnosisDate(
													observation.getValueDateTimeType().getValue());
										}
										String strChild = observation.getSubject().getReference();
										child.setChildId(strChild);
									}
									;

								}
							}
							if (childPatient != null && childPatient.size() > 0) {
								for (HivChildPatient patient : childPatient) {
									String strChildId = "Patient/" + patient.getIdElement().getIdPart();
									if (child.getChildId().equals(strChildId)) {

										if (patient.getBirthDefects() != null
												&& patient.getBirthDefects().getValue() != null) {
											child.setBirthDefects(patient.getBirthDefects().getValue());
										}
										if (patient.getBirthWeight() != null
												&& patient.getBirthWeight().getValue() != null) {
											child.setBirthWeight(patient.getBirthWeight().getValue());
										}
										if (patient.getHivStatus() != null) {
											SystemCodeDto hivStatus = new SystemCodeDto();
											hivStatus.setCode(patient.getHivStatus().getCode());
											hivStatus.setDisplay(patient.getHivStatus().getDisplay());
											child.setHivStatus(hivStatus);
										}

									}

								}
							}

						}
					}
				}
				dto.setListPregnancy(listPregnancy);
			}
			//
			dto.setDeath(patientDeathDto);
			dto.setCd4BeforeART(cd4BeforeArt);
			dto.setDiagnosis(diagnosis);
			dto.setRiskPopulations(riskPopulations);
			dto.setRiskBehaviors(riskBehaviors);
			dto.setTransmissionRoutes(transmissionRoutes);
			dto.setViralLoadList(viralLoadList);
			dto.setRapidRecencyTest(rapidRecencyTest);
			dto.setViralLoadRecencyTest(viralLoadRecencyTest);
			dto.setCd4List(cd4List);
			dto.setRegimens(regimens);
			dto.setListOfArvTreatment(listOfArvTreatment);
			dto.setListOfHbv(listOfHbv);
			dto.setListOfHcv(listOfHcv);
			dto.setDrugResistanceList(drugResistanceList);
			dto.setListOfTb(listOfTb);
			dto.setListOfTpt(listOfTpt);
			return dto;
		}
		return null;
	}

//	@Override
//	public Bundle savePatient(PatientDto dto) {
//		HivPatient patient = new HivPatient();
//		patient.setIdentifier(new ArrayList<Identifier>());
//		if (dto.getArtID() != null) {
//			Identifier arvId = new Identifier();
//			arvId.setSystem(HivConst.ArvIdentifierNamingSystem);
//			arvId.setId(dto.getArtID());
//			arvId.setValue(dto.getArtID());
//			patient.getIdentifier().add(arvId);
//		}
//
//		if (dto.getPassportNumber() != null) {
//			Identifier passportId = new Identifier();
//			passportId.setSystem(HivConst.PassportIdentifierNamingSystem);
//			passportId.setId(dto.getPassportNumber());
//			passportId.setValue(dto.getPassportNumber());
//			patient.getIdentifier().add(passportId);
//		}
//
//		if (dto.getNationalID() != null) {
//			Identifier nationalId = new Identifier();
//			nationalId.setSystem(HivConst.NationalIdentifierNamingSystem);
//			nationalId.setId(dto.getNationalID());
//			nationalId.setValue(dto.getNationalID());
//			patient.getIdentifier().add(nationalId);
//		}
//		patient.setName(new ArrayList<HumanName>());
//		HumanName name = new HumanName();
//		name.setFamily(dto.getLastName());
//		name.setGiven(new ArrayList<StringType>());
//		name.getGiven().add(new StringType(dto.getFirstName()));
//		patient.getName().add(name);
//		if (dto.getName() != null) {
//			name.setText(dto.getName());
//		}
//		if (dto.getName() == null && dto.getFirstName() != null && dto.getLastName() != null) {
//			name.setText(dto.getFirstName() + " " + dto.getLastName());
//		}
//
//		patient.setBirthDate(dto.getBirthDate());
//		patient.setGenderIdentity(new CodeableConcept());
//		patient.getGenderIdentity().addCoding(
//				new Coding(HivConst.VsPatientGenderIdentity, dto.getGender().getCode(), dto.getGender().getDisplay()));
//		if (dto.getEthnicity() != null) {
//			patient.setEthnicity(new CodeableConcept());
//			patient.getEthnicity().addCoding(
//					new Coding(HivConst.VsEthnicity, dto.getEthnicity().getCode(), dto.getEthnicity().getName()));
//		}
//		if (dto.getDeath() != null) {
//			Coding coding = new Coding();
////			coding.setCode(dto.getCauseOfDeath().getCode());
//			coding.setSystem(HivConst.CsCauseOfDeath);
//			coding.setDisplay(dto.getDeath().getCauseOfDeath());
//			patient.setCauseOfDeath(coding);
//		}
//		if (dto.getTreatmentStatus() != null) {
//			Coding coding = new Coding();
//			coding.setCode(dto.getTreatmentStatus().getCode());
//			coding.setSystem(HivConst.CsTreatmentStatus);
//			coding.setDisplay(dto.getTreatmentStatus().getDisplay());
//			patient.setTreatmentStatus(coding);
//		}
//		if (dto.getDeath() != null) {
//			patient.setDeceased(new DateTimeType(dto.getDeath().getDateOfDeath()));
//		}
//		List<Resource> children = new ArrayList<Resource>();
//		if (dto.getDiagnosis() != null) {
//
//			if (dto.getDiagnosis().getConfirmatoryLab() != null) {
//
//				HivDiagnosisObservation diagnosis = new HivDiagnosisObservation();
//				diagnosis.setCode(new CodeableConcept());
//				Coding coding = diagnosis.getCode().addCoding();
//				coding.setSystem(HivConst.VsDiagnosisObs);
//
//				diagnosis.setPerformer(new ArrayList<Reference>());
//				Reference refPerformer = new Reference();
//
//				refPerformer.setDisplay(dto.getDiagnosis().getConfirmatoryLab().getName());
//				refPerformer.setId(dto.getDiagnosis().getConfirmatoryLab().getCode());
//				diagnosis.getPerformer().add(refPerformer);
//
//				diagnosis.setIssued(dto.getDiagnosis().getConfirmatoryDate());
//				diagnosis.setSpecimenCollectionPlace(new StringType(dto.getDiagnosis().getPlaceOfSpecimenCollection()));
//				children.add(diagnosis);
//
//			}
//			if (dto.getDiagnosis().getRiskBehavior() != null) {
//				Condition condRisk = new Condition();
//				condRisk.setCode(new CodeableConcept());
//				Coding riskCoding = condRisk.getCode().addCoding();
//				riskCoding.setSystem(HivConst.VsHivRiskBehavior);
//				riskCoding.setCode(dto.getDiagnosis().getRiskBehavior().getCode());
//				riskCoding.setDisplay(dto.getDiagnosis().getRiskBehavior().getDisplay());
//				condRisk.setOnset(DateTimeType.now());
//				children.add(condRisk);
//			}
//
//			if (dto.getDiagnosis().getRiskPopulation() != null) {
//				Condition condCsHivPopulation = new Condition();
//				condCsHivPopulation.setCode(new CodeableConcept());
//				Coding riskCoding = condCsHivPopulation.getCode().addCoding();
//				riskCoding.setSystem(HivConst.CsHivPopulation);
//
//				riskCoding.setCode(dto.getDiagnosis().getRiskPopulation().getCode());
//				riskCoding.setDisplay(dto.getDiagnosis().getRiskPopulation().getDisplay());
//				condCsHivPopulation.setOnset(DateTimeType.now());
//				children.add(condCsHivPopulation);
//			}
//
//		}
//		return PatientFhirServiceImpl.savePatientTransaction(patient, children);
//
//	}

	public static HivPatient getPatientById(String theUrl) {
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		HivPatient response = client.fetchResourceFromUrl(HivPatient.class, theUrl);
		return response;
	}

	public static Bundle savePatientTransaction(HivPatient patient, List<? extends Resource> children) {
		HivPatient hivPatient = null;
		Bundle bundle = new Bundle();
		if (patient != null) {
			if (patient.getIdentifier() != null && patient.getIdentifier().size() > 0) {
				Patient hl7Patient = null;
				for (Identifier item : patient.getIdentifier()) {
					if (item.getValue() != null && !item.getValue().trim().equals("")) {
						Bundle bundleTest = HAPIFhirHivPatientService.findPatientDuplicatePatient(item.getSystem(),
								item.getValue());
						hl7Patient = (Patient) bundleTest.getEntryFirstRep().getResource();
					}

					if (hl7Patient != null) {
						String json = jsonParser.encodeResourceToString(hl7Patient);
						hivPatient = jsonParser.parseResource(HivPatient.class, json);
					}
					if (hivPatient != null) {
						hivPatient.setName(patient.getName());
						hivPatient.setAddress(patient.getAddress());
						hivPatient.setIdentifier(patient.getIdentifier());
						hivPatient.setBirthDate(patient.getBirthDate());
						hivPatient.setLastUpdated(patient.getLastUpdated());
						hivPatient.setSyncOrg(patient.getSyncOrg());
						patient = hivPatient;
						break;
					}
				}
			}
		}
		if (hivPatient != null) {
			System.out.print(Patient.class.getSimpleName());
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

		// Save bundle which contains the Patient and list Resource to HAPI Server
		FhirContext ctx = FhirContext.forR4();
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		Bundle resp = client.transaction().withBundle(bundle).execute();
		return resp;
	}

	@Override
	public Page<PatientDto> getAllPatient(SearchDto searchDto) {
//		RestTemplate restTemplate = new RestTemplate();
//		String catUrl = serverBaseUrl + "/Patient";
//		JSONObject patientObjects = restTemplate.getForObject(catUrl, JSONObject.class);

		IParser parser = ctx.newJsonParser();
//		Bundle bundle = (Bundle)parser.parseResource(patientObjects.toJSONString());
		Bundle bundle = getPatientBundle(searchDto.getPageIndex(), searchDto.getPageSize(), searchDto.getText());
		List<HivPatient> list = new ArrayList<HivPatient>();
		list = (List<HivPatient>) PatientFhirServiceImpl.bundleToList(bundle);
		List<PatientDto> listData = new ArrayList<PatientDto>();
		if (list != null && list.size() > 0) {
			for (HivPatient response : list) {
				PatientDto dto = new PatientDto();

//				if (response.getLastUpdated() != null) {
//					SystemCodeDto systemCodeDto = new SystemCodeDto();
//					systemCodeDto.setCode(response.getLastUpdated().getCode());
//					systemCodeDto.setDisplay(response.getLastUpdated().getDisplay());
//					dto.setLastUpdated(systemCodeDto);
//				}
				if (response.getSyncOrg() != null && response.getSyncOrg().getCoding() != null
						&& response.getSyncOrg().getCoding().size() > 0) {

					List<SystemCodeDto> listCoding = new ArrayList<SystemCodeDto>();
					SystemCodeDto systemCodeDto = new SystemCodeDto();
					systemCodeDto.setCode(response.getSyncOrg().getCoding().get(0).getCode());
					systemCodeDto.setDisplay(response.getSyncOrg().getCoding().get(0).getDisplay());
					dto.setLastUpdated(systemCodeDto);
					for (Coding coding : response.getSyncOrg().getCoding()) {
						SystemCodeDto systemCode = new SystemCodeDto();
						systemCode.setCode(coding.getCode());
						systemCode.setDisplay(coding.getDisplay());
						listCoding.add(systemCode);
					}
					dto.setSyncOrg(listCoding);
				}

				dto.setBirthDate(response.getBirthDate());
				dto.setId(response.getIdElement().getIdPart());
				if (response.getName() != null && response.getName().size() > 0) {
					dto.setName(response.getName().get(0).getText());
					if (response.getName().get(0).getGiven() != null
							&& response.getName().get(0).getGiven().size() > 0) {
						dto.setFirstName(response.getName().get(0).getGiven().get(0).asStringValue());
					}
					dto.setLastName(response.getName().get(0).getFamily());
					if (dto.getName() == null && dto.getFirstName() != null && dto.getLastName() != null) {
						dto.setName(dto.getLastName() + " " + dto.getFirstName());
					}
				}

				if (response.getAddress() != null && response.getAddress().size() > 0) {

					for (Address add : response.getAddress()) {
						if (add.getExtension() != null && add.getExtension().size() > 0) {
							for (Extension ex : add.getExtension()) {
								if (ex.getUrl().equals("Temporary")) {
									AddressDto address = new AddressDto();
									if (add.getExtension() != null && add.getExtension().size() > 0) {
										for (Extension extension : ex.getExtension()) {
											if (extension.getUrl().equals("commune")) {
												Coding code = (Coding) extension.getValue();
												address.setCommuneName(code.getDisplay());
												address.setCommuneCode(code.getCode());
											} else if (extension.getUrl().equals("district")) {
												Coding code = (Coding) extension.getValue();
												address.setDistrictCode(code.getCode());
												address.setDistrictName(code.getDisplay());
											} else if (extension.getUrl().equals("province")) {
												Coding code = (Coding) extension.getValue();
												address.setCityCode(code.getCode());
												;
												address.setCityName(code.getDisplay());
											}
										}
									}
									if (address.getCityName() != null && address.getDistrictName() != null
											&& address.getCommuneName() != null) {
										address.setText(address.getCommuneName() + ", " + address.getDistrictName()
												+ ", " + address.getCityName());
									}
									if (address.getText() == null && address.getCityName() != null
											&& address.getDistrictName() != null) {
										address.setText(address.getDistrictName() + ", " + address.getCityName());
									}
									if (address.getText() == null && address.getCityName() != null) {
										address.setText(address.getCityName());
									}
									dto.setCurrentAddress(address);

								} else if (ex.getUrl().equals("Home")) {
									AddressDto address = new AddressDto();
									if (add.getExtension() != null && add.getExtension().size() > 0) {
										for (Extension extension : ex.getExtension()) {
											if (extension.getUrl().equals("commune")) {
												Coding code = (Coding) extension.getValue();
												address.setCommuneName(code.getDisplay());
												address.setCommuneCode(code.getCode());
											} else if (extension.getUrl().equals("district")) {
												Coding code = (Coding) extension.getValue();
												address.setDistrictCode(code.getCode());
												address.setDistrictName(code.getDisplay());
											} else if (extension.getUrl().equals("province")) {
												Coding code = (Coding) extension.getValue();
												address.setCityCode(code.getCode());
												;
												address.setCityName(code.getDisplay());
											}
										}
									}
									if (address.getCityName() != null && address.getDistrictName() != null
											&& address.getCommuneName() != null) {
										address.setText(address.getCommuneName() + ", " + address.getDistrictName()
												+ ", " + address.getCityName());
									}
									if (address.getText() == null && address.getCityName() != null
											&& address.getDistrictName() != null) {
										address.setText(address.getDistrictName() + ", " + address.getCityName());
									}
									if (address.getText() == null && address.getCityName() != null) {
										address.setText(address.getCityName());
									}

									dto.setRegisteredAddress(address);
								}
							}
						}

					}
				}
				if (response.getIdentifier() != null && response.getIdentifier().size() > 0) {
					for (Identifier identifier : response.getIdentifier()) {

						if (identifier.getSystem() != null
								&& identifier.getSystem().equals(HivConst.PassportIdentifierNamingSystem)) {
							dto.setPassportNumber(identifier.getValue());
						} else if (identifier.getSystem() != null
								&& identifier.getSystem().equals(HivConst.InsuranceIdentifierNamingSystem)) {
							dto.setHealthInsuranceNumber(identifier.getValue());
						} else if (identifier.getSystem() != null
								&& identifier.getSystem().equals(HivConst.ArvIdentifierNamingSystem)) {
							dto.setArtID(identifier.getValue());
						} else if (identifier.getSystem() != null
								&& identifier.getSystem().equals(HivConst.National9IdentifierNamingSystem)) {
							dto.setNationalID(identifier.getValue());
						}
					}
				}
				if (response.getGenderIdentity() != null && response.getGenderIdentity().getCoding() != null
						&& response.getGenderIdentity().getCoding().size() > 0) {
					SystemCodeDto systemCode = new SystemCodeDto();
					systemCode.setCode(response.getGenderIdentity().getCodingFirstRep().getCode());
					systemCode.setDisplay(response.getGenderIdentity().getCodingFirstRep().getDisplay());
					dto.setGender(systemCode);
				}

				listData.add(dto);
			}
		}
		int pageIndex = searchDto.getPageIndex() > 0 ? searchDto.getPageIndex() - 1 : searchDto.getPageIndex();
		Pageable pageable = PageRequest.of(pageIndex, searchDto.getPageSize());
		Page<PatientDto> result = new PageImpl<PatientDto>(listData, pageable, bundle.getTotal());
//		List<HivPatient> listData = new ArrayList<HivPatient>();
//		List<HivPatient> listData = (List<HivPatient>) Arrays.asList(catObjects);
		return result;
	}

	public static Bundle getPatientBundle(int pageIndex, int pageSize, String text) {
		int pagesoffset = (pageIndex - 1) * pageSize;
		FhirContext ctx = FhirContext.forR4();
		// serverBaseUrl="http://hapi.fhir.org/baseR4";
		ctx.getRestfulClientFactory().setSocketTimeout(HivConst.fhirSocketTimeout);
		IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
		// Build a search and execute it
		SortSpec sortSpec = new SortSpec("_lastUpdated", SortOrderEnum.DESC);
		Bundle response = client.search().forResource(HivPatient.class).where(Patient.NAME.contains().value(text))
				.withProfile(HivConst.HivPatientProfile).sort(sortSpec).totalMode(SearchTotalModeEnum.ACCURATE)
				.count(pageSize).offset(pagesoffset).returnBundle(Bundle.class).execute();
		return response;
	}

	public static List<? extends IBaseResource> bundleToList(Bundle theBundle) {
		FhirContext ctx = FhirContext.forR4();

		List<IBaseResource> list = new ArrayList<IBaseResource>();
		list.addAll(BundleUtil.toListOfResources(ctx, theBundle));
		return list;
	}
//	public static void testCreate() {
//		PatientFhirServiceImpl service = new PatientFhirServiceImpl();
//		PatientDto dto = new PatientDto();
//		dto.setFirstName("Nguyen");
//		dto.setLastName("Van Chung");
//		dto.setBirthDate(DateTimeType.now().getValue());
//		dto.setNationalID("N0000000001");
//		dto.setPassportNumber("P0000000001");
//		dto.setArtID("A0000000001");
//		dto.setEthnicity(new EthnicityDto());
//		dto.getEthnicity().setCode("KINH");
//		dto.getEthnicity().setName("Kinh");
//		dto.setCurrentAddress(new AddressDto());
//		dto.getCurrentAddress().setCityCode("HN");
//		dto.getCurrentAddress().setCityName("Hà nội");
//		dto.getCurrentAddress().setLine("Số 64 ngõ 122 Thái Thịnh");
//		dto.setRegisteredAddress(new AddressDto());
//		dto.getRegisteredAddress().setCityCode("HN");
//		dto.getRegisteredAddress().setCityName("Hà nội");
//		dto.setGender(new SystemCodeDto());
//		dto.getGender().setCode("male");
//		dto.getGender().setDisplay("Nam");
//		dto.setDiagnosis(new HivDiagnosisDto());
//		dto.getDiagnosis().setConfirmatoryDate(new Date());
//		dto.getDiagnosis().setConfirmatoryLab(new OrganizationDto());
//		dto.getDiagnosis().getConfirmatoryLab().setCode("Test");
//		dto.getDiagnosis().getConfirmatoryLab().setName("Test");
//
//		dto.getDiagnosis().setRiskBehavior(new SystemCodeDto());
//		dto.getDiagnosis().getRiskBehavior().setCode("02.2");
//		dto.getDiagnosis().getRiskBehavior().setDisplay("Tình dục khác giới");
//
//		dto.getDiagnosis().setRiskPopulation(new SystemCodeDto());
//		dto.getDiagnosis().getRiskPopulation().setCode("01");
//		dto.getDiagnosis().getRiskPopulation().setDisplay("Vợ/chồng nhiễm HIV");
//
//		dto.getDiagnosis().setConfirmatoryLab(new OrganizationDto());
//		dto.getDiagnosis().getConfirmatoryLab().setCode("001");
//		dto.getDiagnosis().getConfirmatoryLab().setName("Trung tâm phòng, chống HIV/AIDS Tiền Giang");
//		dto.getDiagnosis().setPlaceOfSpecimenCollection("PAC Tiền giang");
//		dto.getDiagnosis().setConfirmatoryDate(DateTimeType.now().getValue());
//
//		Bundle bundle = service.savePatient(dto);
//		IParser parser = ctx.newJsonParser();
//		parser.setPrettyPrint(true);
//		String json = parser.encodeResourceToString(bundle);
//		System.out.println(json);
//
//	}

	public static void testGet(String id) {
		PatientFhirServiceImpl service = new PatientFhirServiceImpl();
		PatientDto patient = service.getPatient(id);
		System.out.println(patient.getFirstName() + ":" + patient.getArtID() + ":" + patient.getNationalID() + ":"
				+ patient.getPassportNumber());
		System.out.println(patient.getDiagnosis().getConfirmatoryDate() + ":"
				+ patient.getDiagnosis().getConfirmatoryLab().getCode());

		System.out.println(patient.getDiagnosis().getRiskBehavior().getCode() + ":"
				+ patient.getDiagnosis().getRiskPopulation().getCode());

		System.out.println(patient.getDiagnosis().getConfirmatoryDate() + ":"
				+ patient.getDiagnosis().getPlaceOfSpecimenCollection());
	}

	public static void main(String[] args) {
//		System.out.println(Condition.class.getSimpleName());
//		testCreate();
//		testGet("http://fhir.globits.net:8082/fhir/Patient/2/_history/1");
		// PatientFhirServiceImpl service = new PatientFhirServiceImpl();
		// HivConst.serverFhirUrl="http://fhir.globits.net:8082/fhir";
		// service.deleteById("178480");
//		Page<PatientDto> page =service.getAllPatient(1, 10);
//		System.out.println(page.getContent().size());
//		service.deleteById("15");
	}

//	@Override
//	public Bundle getPatientEverythingById(String theUrl) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Bundle getPatientByLastUpdate(int pageIndex, int pageSize, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}

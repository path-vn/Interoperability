package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseStatus;

import com.globits.fhir.hiv.utils.HivConst;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import dto.ArvTreatmentDto;
import dto.ChildDto;
import dto.CoMorbidityTreatmentDto;
import dto.HbvDto;
import dto.HcvDto;
import dto.LabTestDto;
import dto.PatientDto;
import dto.PatientRegimenDto;
import dto.PregnancyDto;
import dto.TuberculosisDto;
import dto.WHOStateDto;

public class CsApiService {
	public QuestionnaireResponseItemComponent createEmptyItemCompenent(String linkId, String text) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);
		return itemComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueString(
			String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueStringType().setValue(valueString);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueBoolean(Boolean value) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueBooleanType().setValue(value);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueBoolean(String linkId, String text,
			Boolean value) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueBoolean(
				value);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueInteger(Integer value) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueIntegerType().setValue(value);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDate(String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		// answerVLTestDateOfTestPerformance.getValueStringType().setValue(data.getRapidVLTestDate());
		Date dateType = null;
		try {
			dateType = new SimpleDateFormat("yyyy-MM-dd").parse(valueString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itemAnswerComponent.getValueDateType().setValue(dateType);

		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}
	
	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDateTime(String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		// answerVLTestDateOfTestPerformance.getValueStringType().setValue(data.getRapidVLTestDate());
		Date dateType = null;
		try {
			dateType = new SimpleDateFormat("yyyy-MM-dd").parse(valueString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itemAnswerComponent.getValueDateTimeType().setValue(dateType);

		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueCoding(String valueCode,
			String valueDisplay) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueCoding().setCode(valueCode);
		itemAnswerComponent.getValueCoding().setDisplay(valueDisplay);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueString(String linkId, String text,
			String valueString) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueString(
				valueString);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueInteger(String linkId, String text,
			Integer value) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueInteger(
				value);
		itemComponent.setAnswer(listItemAnswerComponent);

		return itemComponent;
	}

	public QuestionnaireResponseItemComponent createQRItemCompenentValueDate(String linkId, String text, Date date) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String valueString = dateFormat.format(date);
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueDate(
				valueString);
		itemComponent.setAnswer(listItemAnswerComponent);
		return itemComponent;
	}
	public QuestionnaireResponseItemComponent createQRItemCompenentValueDateTime(String linkId, String text, Date date) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String valueString = dateFormat.format(date);
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueDateTime(
				valueString);
		itemComponent.setAnswer(listItemAnswerComponent);
		return itemComponent;
	}
	public QuestionnaireResponseItemComponent createQRItemCompenentValueCoding(String linkId, String text,
			String valueCode, String valueDisplay) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueCoding(
				valueCode, valueDisplay);
		itemComponent.setAnswer(listItemAnswerComponent);
		return itemComponent;
	}

	public QuestionnaireResponseItemComponent generatePatientIdentifying(PatientDto data) {
		// patient_identification
		QuestionnaireResponseItemComponent patientIdentification = new QuestionnaireResponseItemComponent();
		patientIdentification.setLinkId(HivConst.QRLinkIdPatientIdentification);
		patientIdentification.setText("Part I: Identifying Information");

		// arv number
		if (data != null && data.getArvNumber() != null) {
			QuestionnaireResponseItemComponent arvIdNr = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientArvNumber, "ARV Patient number", data.getArvNumber());
			patientIdentification.addItem(arvIdNr);
		}
		// caseid
		if (data.getCaseId() != null) {
			QuestionnaireResponseItemComponent casrUid = createQRItemCompenentValueString(HivConst.QRLinkIdCaseUid,
					"Case UID", data.getCaseId().toString());
			patientIdentification.addItem(casrUid);
		}
		//
		
		if(data.getIndexTesting()!= null) {
			QuestionnaireResponseItemComponent indexTesting = createEmptyItemCompenent(
					HivConst.QRLinkIdIndexTesting, HivConst.QRTextIndexTesting);
			if(data.getIndexTesting().getIndexCaseNationalId12() != null) {
				QuestionnaireResponseItemComponent indexCaseNationalId12 = createQRItemCompenentValueString(
						HivConst.QRLinkIdIndexCaseNationalId12, HivConst.QRTextIndexCaseNationalId12, data.getIndexTesting().getIndexCaseNationalId12());
				indexTesting.addItem(indexCaseNationalId12);
			}
			if(data.getIndexTesting().getIndexCaseNationalId9() != null) {
				QuestionnaireResponseItemComponent indexCaseNationalId9 = createQRItemCompenentValueString(
						HivConst.QRLinkIdIndexCaseNationalId9, HivConst.QRLinkIdIndexCaseNationalId9, data.getIndexTesting().getIndexCaseNationalId9());
				indexTesting.addItem(indexCaseNationalId9);
			}
			if(data.getIndexTesting().getIndexCaseUid() != null) {
				QuestionnaireResponseItemComponent indexCaseUid = createQRItemCompenentValueString(
						HivConst.QRLinkIdIndexCaseUID, HivConst.QRTextIndexCaseUID, data.getIndexTesting().getIndexCaseUid());
				indexTesting.addItem(indexCaseUid);
			}
		}
		//
		QuestionnaireResponseItemComponent patientInformation = createEmptyItemCompenent(
				HivConst.QRLinkIdPersonalInformation, "Patient Information");

		QuestionnaireResponseItemComponent name = createEmptyItemCompenent(HivConst.QRLinkIdPatientName, "Name");
		if (data.getName() != null) {
			String fullNamePart = data.getName();

			// FullName
			QuestionnaireResponseItemComponent fullName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientFullName, "Full name", fullNamePart);
			name.addItem(fullName);
			patientInformation.addItem(name);

		}

		if (data.getFirstName() != null) {
			String familyNamePart = data.getFirstName();

			// familyName
			QuestionnaireResponseItemComponent familyName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientFamilyName, "Family name", familyNamePart);
			name.addItem(familyName);
		}

		if (data.getLastName() != null) {
			String givenNamePart = data.getLastName();
			// GivenName
			QuestionnaireResponseItemComponent givenName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientGivenName, "Given name", givenNamePart);
			name.addItem(givenName);
		}
		if (data.getEthnicity() != null) {
			QuestionnaireResponseItemComponent ethnicity = createQRItemCompenentValueCoding(HivConst.QRLinkIdEthnicity,
					"Ethnicity", data.getEthnicity().getCode(), data.getEthnicity().getDisplay());
			patientInformation.addItem(ethnicity);
		}
		if (data.getGender() != null) {

			QuestionnaireResponseItemComponent gender = createQRItemCompenentValueCoding(HivConst.QRLinkIdGender,
					"Gender", data.getGender().getCode(), data.getGender().getDisplay());
			patientInformation.addItem(gender);
		}

		if (data.getOccupation() != null) {

			QuestionnaireResponseItemComponent occupation = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdOccupation, HivConst.QRTextOccupation, data.getOccupation().getCode(),
					data.getOccupation().getDisplay());
			patientInformation.addItem(occupation);
		}

		// date of birth
		if (data.getBirthDate() != null) {
			QuestionnaireResponseItemComponent birthDate = createQRItemCompenentValueDate(HivConst.QRLinkIdYearOfBirth,
					"Date of Birth", data.getBirthDate());

			patientInformation.addItem(birthDate);
		}
		QuestionnaireResponseItemComponent identification = createEmptyItemCompenent(HivConst.QRLinkIdIdentification,
				"Identification");

		if (data.getPassportNumber() != null) {
			// Passport
			QuestionnaireResponseItemComponent passport_nr = createQRItemCompenentValueString(
					HivConst.QRLinkIdPassportNumber, "Passport Number", data.getPassportNumber());
			identification.addItem(passport_nr);
		}
		if (data.getNationalID9() != null) {
			// NationalId
			QuestionnaireResponseItemComponent nationalIdRr = createQRItemCompenentValueString(
					HivConst.QRLinkIdNationalId9, "National ID 9 digits", data.getNationalID9());
			identification.addItem(nationalIdRr);
		}
		if (data.getNationalID12() != null) {
			// NationalId
			QuestionnaireResponseItemComponent nationalIdRr = createQRItemCompenentValueString(
					HivConst.QRLinkIdNationalId12, "National ID 12 digits", data.getNationalID12());
			identification.addItem(nationalIdRr);
		}
		patientInformation.addItem(identification);
		//// Residence
		QuestionnaireResponseItemComponent residence = new QuestionnaireResponseItemComponent();
		residence.setLinkId(HivConst.QRLinkIdResidence);
		residence.setText("Residence");
		// Current Residence
		QuestionnaireResponseItemComponent current = new QuestionnaireResponseItemComponent();
		current.setLinkId(HivConst.QRLinkIdCurrentResidence);
		current.setText("Current Residence");

		if (data.getCurrentAddress() != null) {
			if (data.getCurrentAddress().getLine() != null) {
				// currentStressAddress
				QuestionnaireResponseItemComponent currentStressAddress = createQRItemCompenentValueString(
						HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getCurrentAddress().getLine());
				current.addItem(currentStressAddress);
			}
			if (data.getCurrentAddress() != null && data.getCurrentAddress().getCommuneCode() != null
					&& data.getCurrentAddress().getCommuneName() != null) {
				// currentCommune
				QuestionnaireResponseItemComponent currentCommune = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getCurrentAddress().getCommuneCode(),
						data.getCurrentAddress().getCommuneName());
				current.addItem(currentCommune);
			}
			if (data.getCurrentAddress() != null && data.getCurrentAddress().getDistrictCode() != null
					&& data.getCurrentAddress().getDistrictName() != null) {
				// currentDistrict
				QuestionnaireResponseItemComponent currentDistrict = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getCurrentAddress().getDistrictCode(),
						data.getCurrentAddress().getDistrictName());
				current.addItem(currentDistrict);
			}
			if (data.getCurrentAddress() != null && data.getCurrentAddress().getProvinceCode() != null
					&& data.getCurrentAddress().getProvinceName() != null) {
				// currentProvince
				QuestionnaireResponseItemComponent currentProvince = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getCurrentAddress().getProvinceCode(),
						data.getCurrentAddress().getProvinceName());
				current.addItem(currentProvince);
			}
			if (data.getCurrentAddress() != null && data.getCurrentAddress().getCountryCode() != null
					&& data.getCurrentAddress().getCountryName() != null) {
				// currentCountry
				QuestionnaireResponseItemComponent currentCountry = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getCurrentAddress().getCountryCode(),
						data.getCurrentAddress().getCountryName());
				current.addItem(currentCountry);
			}

			residence.addItem(current);
		}

		QuestionnaireResponseItemComponent permanent = new QuestionnaireResponseItemComponent();
		permanent.setLinkId(HivConst.QRLinkIdPermanentResidence);
		permanent.setText("Permanent residence");
		if (data.getRegisteredAddress() != null) {
			if (data.getRegisteredAddress() != null && data.getRegisteredAddress().getLine() != null) {
				// permanentStressAddress
				QuestionnaireResponseItemComponent permanentStressAddress = createQRItemCompenentValueString(
						HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getRegisteredAddress().getLine());
				permanent.addItem(permanentStressAddress);
			}
			if (data.getRegisteredAddress() != null && data.getRegisteredAddress().getCommuneCode() != null
					&& data.getRegisteredAddress().getCommuneName() != null) {
				// permanentCommune
				QuestionnaireResponseItemComponent permanentCommune = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getRegisteredAddress().getCommuneCode(),
						data.getRegisteredAddress().getCommuneName());
				permanent.addItem(permanentCommune);
			}
			if (data.getRegisteredAddress() != null && data.getRegisteredAddress().getDistrictCode() != null
					&& data.getRegisteredAddress().getDistrictName() != null) {
				// PermanentDistrict
				QuestionnaireResponseItemComponent permanentDistrict = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict,
						data.getRegisteredAddress().getDistrictCode(), data.getRegisteredAddress().getDistrictName());
				permanent.addItem(permanentDistrict);
			}
			if (data.getRegisteredAddress() != null && data.getRegisteredAddress().getProvinceCode() != null
					&& data.getRegisteredAddress().getProvinceName() != null) {
				// PermanentProvince
				QuestionnaireResponseItemComponent permanentProvince = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdProvince, HivConst.QRTextProvince,
						data.getRegisteredAddress().getProvinceCode(), data.getRegisteredAddress().getProvinceName());
				permanent.addItem(permanentProvince);
			}
			if (data.getRegisteredAddress() != null && data.getRegisteredAddress().getCountryCode() != null
					&& data.getRegisteredAddress().getCountryName() != null) {
				// PermanentCountry
				QuestionnaireResponseItemComponent permanentCountry = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getRegisteredAddress().getCountryCode(),
						data.getRegisteredAddress().getCountryName());
				permanent.addItem(permanentCountry);
			}

			residence.addItem(permanent);
		}
		patientInformation.addItem(residence);

		// death
		if (data.getDeath() != null) {

			QuestionnaireResponseItemComponent death = createEmptyItemCompenent(HivConst.QRLinkIdDeath,
					"Patient death");
			if (data.getDeath().getDateOfDeath() != null) {
				QuestionnaireResponseItemComponent dateOfDeath = new QuestionnaireResponseItemComponent();
				dateOfDeath.setLinkId(HivConst.QRLinkIdDateOfDeath);
				dateOfDeath.setText("Date of death");
				List<QuestionnaireResponseItemAnswerComponent> listAnswer = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
				QuestionnaireResponseItemAnswerComponent answer = new QuestionnaireResponseItemAnswerComponent();
				answer.getValueDateTimeType().setValue(data.getDeath().getDateOfDeath());
				listAnswer.add(answer);
				dateOfDeath.setAnswer(listAnswer);
				death.addItem(dateOfDeath);
			}

			if (data.getDeath().getCauseOfDeath() != null) {
				QuestionnaireResponseItemComponent causeOfDeath = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCauseOfDeath, "Cause of death", data.getDeath().getCauseOfDeath().getCode(),
						data.getDeath().getCauseOfDeath().getDisplay());
				death.addItem(causeOfDeath);
			}
			patientInformation.addItem(death);
			// listItem.add(death);
		}
		patientIdentification.addItem(patientInformation);
		return patientIdentification;

	}

	public QuestionnaireResponseItemComponent generateRiskFactors(PatientDto data) {
		QuestionnaireResponseItemComponent riskFactors = createEmptyItemCompenent(HivConst.QRLinkIdRiskFactor,
				"Risk Factors");

		if (data.getDiagnosis() != null) {
			if (data.getDiagnosis().getRiskPopulation() != null) {

				QuestionnaireResponseItemComponent risk = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdPopulationGroup, "Risk Population",
						data.getDiagnosis().getRiskPopulation().getCode(),
						data.getDiagnosis().getRiskPopulation().getDisplay());
				riskFactors.addItem(risk);
			}
			if (data.getDiagnosis().getRiskBehavior() != null) {

				QuestionnaireResponseItemComponent risk = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdRiskBehavior, HivConst.QRTextRiskBehavior,
						data.getDiagnosis().getRiskBehavior().getCode(),
						data.getDiagnosis().getRiskBehavior().getDisplay());
				riskFactors.addItem(risk);
			}
			if (data.getDiagnosis().getTransmissionRoute() != null) {

				QuestionnaireResponseItemComponent risk = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdTransmissionRoute, HivConst.QRTextTransmissionRoute,
						data.getDiagnosis().getTransmissionRoute().getCode(),
						data.getDiagnosis().getTransmissionRoute().getDisplay());
				riskFactors.addItem(risk);
			}

		}
		return riskFactors;
	}

	public QuestionnaireResponseItemComponent generateHivDiagnosis(PatientDto data) {
		QuestionnaireResponseItemComponent hivDiagnosis = createEmptyItemCompenent(HivConst.QRLinkIdHivDiagnosis,
				"HIV Diagnosis");
		if (data.getDiagnosis() != null) {
			// Date Of Confirmation
			if (data.getDiagnosis().getConfirmatoryDate() != null) {
				QuestionnaireResponseItemComponent dateOfConfirmation = createQRItemCompenentValueDate(
						HivConst.QRLinkIdDateOfConfirmation, "Date of Confirmation",
						data.getDiagnosis().getConfirmatoryDate());

				hivDiagnosis.addItem(dateOfConfirmation);
			}
			// Confirming Lab
			if (data.getDiagnosis().getConfirmatoryLab() != null) {
				QuestionnaireResponseItemComponent confirmingLab = createQRItemCompenentValueString(
						HivConst.QRLinkIdConfirmingLab, "Confirming Lab",
						data.getDiagnosis().getConfirmatoryLab().getName());
				hivDiagnosis.addItem(confirmingLab);

			}
			if (data.getDiagnosis().getPlaceOfSpecimenCollection() != null) {
				QuestionnaireResponseItemComponent placeOfSpecimenCollection = createQRItemCompenentValueString(
						HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of specimen collection",
						data.getDiagnosis().getPlaceOfSpecimenCollection());
				hivDiagnosis.addItem(placeOfSpecimenCollection);
			}
			if (data.getDiagnosis().getSpecimenCollectionDate() != null) {
				QuestionnaireResponseItemComponent specimenCollectionDate = createQRItemCompenentValueDate(
						HivConst.QRLinkIdDateOfSpecimenCollection, "Date of specimen collection",
						data.getDiagnosis().getSpecimenCollectionDate());
				hivDiagnosis.addItem(specimenCollectionDate);
			}
		}
		return hivDiagnosis;
	}

	public QuestionnaireResponseItemComponent generateHivRecencyTest(PatientDto data) {
		QuestionnaireResponseItemComponent hivRecencyTest = createEmptyItemCompenent(HivConst.QRLinkIdHivRecencyTest,
				"HIV Recency Test");
		QuestionnaireResponseItemComponent vlRecency = null;
		if (data.getHivRecencyTest().getVlTest() != null) {

			QuestionnaireResponseItemComponent vlTest = createEmptyItemCompenent(HivConst.QRLinkIdRecencyVlTest,
					"Rapid VL test");
			if (data.getHivRecencyTest().getVlTest().getTestPerformanceDate() != null) {
				QuestionnaireResponseItemComponent vlTestDateOfTestPerformance = createQRItemCompenentValueDate(
						HivConst.QRLinkIdDateOfTestPerformance, "Date of rapid test performance",
						data.getHivRecencyTest().getVlTest().getTestPerformanceDate());
				vlTest.addItem(vlTestDateOfTestPerformance);
			}
//			if (data.getHivRecencyTest().getVlTest().getValueNumber() != null) {
//				QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueInteger(
//						HivConst.QRLinkIdVlRecencyTestResult, "VL recency test result",
//						data.getHivRecencyTest().getVlTest().getValueNumber());
//
//				vlTest.addItem(vlRecencyTestResult);
//			}
			if (data.getHivRecencyTest().getVlTest().getTestResultOther() != null) {
				QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdVlRecencyTestResultOther, "Other of viral load test (undetectable)",data.getHivRecencyTest().getVlTest().getTestResultOther().getCode(),
						data.getHivRecencyTest().getVlTest().getTestResultOther().getDisplay());

				vlTest.addItem(vlRecencyTestResult);
			}
			hivRecencyTest.addItem(vlTest);
		}
		if (data.getHivRecencyTest().getRapidTest() != null) {

			QuestionnaireResponseItemComponent rapidTest = createEmptyItemCompenent(HivConst.QRLinnkIdRecencyRapidTest,
					"Rapid VL test");
			if (data.getHivRecencyTest().getRapidTest().getSpecimenCollectionDate() != null) {
				QuestionnaireResponseItemComponent specimenCollectionDate = createQRItemCompenentValueDate(
						HivConst.QRLinkIdDateOfSpecimenCollection, "Date of specimen collection",
						data.getHivRecencyTest().getRapidTest().getSpecimenCollectionDate());
				rapidTest.addItem(specimenCollectionDate);
			}
			if (data.getHivRecencyTest().getVlTest().getSpecimenCollectionPlace() != null) {
				QuestionnaireResponseItemComponent specimenCollectionPlace = createQRItemCompenentValueString(
						HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of specimen collection",
						data.getHivRecencyTest().getVlTest().getSpecimenCollectionPlace().getName());

				rapidTest.addItem(specimenCollectionPlace);
			}
			if (data.getHivRecencyTest().getRapidTest().getTestPerformanceDate() != null) {
				QuestionnaireResponseItemComponent vlTestDateOfTestPerformance = createQRItemCompenentValueDate(
						HivConst.QRLinkIdDateOfTestPerformance, "Date of rapid test performance",
						data.getHivRecencyTest().getVlTest().getTestPerformanceDate());
				rapidTest.addItem(vlTestDateOfTestPerformance);
			}
//			if (data.getHivRecencyTest().getVlTest().getValueNumber() != null) {
//				QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
//						HivConst.QRLinkIdVlRecencyTestResult, "VL recency test result",
//						data.getHivRecencyTest().getVlTest().getValueNumber() + "");
//
//				rapidTest.addItem(vlRecencyTestResult);
//			}
			if (data.getHivRecencyTest().getRapidTest().getTestResultOther() != null) {
				QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
						HivConst.QRLinkIdTestResult, "Test Result ",
						data.getHivRecencyTest().getRapidTest().getTestResultOther().getDisplay());

				rapidTest.addItem(vlRecencyTestResult);
			}
			hivRecencyTest.addItem(rapidTest);
		}

		if (data.getHivRecencyTest().getRecentInfectionConclusion() != null) {

			vlRecency = createQRItemCompenentValueCoding(HivConst.QRLinkIdRecencyRecentInfectionConclusion,
					"Recent infection conclusion", data.getHivRecencyTest().getRecentInfectionConclusion().getCode(),
					data.getHivRecencyTest().getRecentInfectionConclusion().getDisplay());

		}

		if (vlRecency != null) {
			// hivRecencyTest = vlRecency;
			hivRecencyTest.addItem(vlRecency);
		}

		return hivRecencyTest;

	}

	public QuestionnaireResponseItemComponent generateLastestCd4(LabTestDto item) {
		QuestionnaireResponseItemComponent cd4Lastest = createEmptyItemCompenent(HivConst.QRLinkIdCd4Test,
				"CD4 test results");

		// reason test
		if (item.getTestReason() != null) {
			QuestionnaireResponseItemComponent testReason = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdLastestTestReason, "Reason for present CD4 test", item.getTestReason().getCode(),
					item.getTestReason().getDisplay());
			cd4Lastest.addItem(testReason);
		}
		// sample date
		if (item.getSpecimenCollectionDate() != null) {
			QuestionnaireResponseItemComponent sampleDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for present CD4 test",
					item.getSpecimenCollectionDate());
			cd4Lastest.addItem(sampleDate);
		}

		// sample place
		if (item.getSpecimenCollectionPlace() != null) {
			QuestionnaireResponseItemComponent samplePlace = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for present CD4 test",
					item.getSpecimenCollectionPlace().getName());
			cd4Lastest.addItem(samplePlace);
		}

		// test date
		if (item.getTestPerformanceDate() != null) {
			QuestionnaireResponseItemComponent sampleDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of present CD4 test",
					item.getTestPerformanceDate());
			cd4Lastest.addItem(sampleDate);
		}

		// test result
		if (item.getTestResultOther() != null) {
			QuestionnaireResponseItemComponent testResult = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestResultOther, "Result of present CD4 test", item.getTestResultOther().getCode(),
					item.getTestResultOther().getDisplay());
			cd4Lastest.addItem(testResult);
		}

		if (item.getValueNumber() != null) {
			QuestionnaireResponseItemComponent testResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "Result of present CD4 test", item.getValueNumber());
			cd4Lastest.addItem(testResult);
		}

		return cd4Lastest;
	}

	public QuestionnaireResponseItemComponent generateVlDuringArt(LabTestDto item) {
		QuestionnaireResponseItemComponent vl4DuringART = createEmptyItemCompenent(HivConst.QRLinkIdVlTest,
				"VL test during ART");
		if (item.getTestReason() != null) {

			QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestReason, "Reason for VL test", item.getTestReason().getCode(),
					item.getTestReason().getDisplay());
			vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);

		}
		if (item.getSpecimenCollectionDate() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for VL test during ART",
					item.getSpecimenCollectionDate());
			vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);
		}
		if (item.getSpecimenCollectionPlace() != null) {
			QuestionnaireResponseItemComponent samplePlace = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for VL test during ART",
					item.getSpecimenCollectionPlace().getName());
			vl4DuringART.addItem(samplePlace);
		}

		if (item.getTestPerformanceDate() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of VL test during ART performance",
					item.getTestPerformanceDate());
			vl4DuringART.addItem(vl4DuringARTDateOfTestPerformance);
		}
		if (item.getValueNumber() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTTestResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "VL test during ART - result", item.getValueNumber());

			vl4DuringART.addItem(vl4DuringARTTestResult);
		}
		if (item.getTestResultOther() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTTestResult = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestResultOther, "Result of other outcome", item.getTestResultOther().getCode(),
					item.getTestResultOther().getDisplay());
			vl4DuringART.addItem(vl4DuringARTTestResult);
		}
		return vl4DuringART;
	}

	public QuestionnaireResponseItemComponent generateDrugResistance(LabTestDto item) {
		QuestionnaireResponseItemComponent drugResistanceTest = createEmptyItemCompenent(
				HivConst.QRLinkIdDrugResistanceTest, "Drug Resistance test");
		if (item.getSpecimenCollectionDate() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for Drug Resistance test",
					item.getSpecimenCollectionDate());

			drugResistanceTest.addItem(drugResistanceTestDateOfSpecimenCollection);
		}
		if (item.getTestPerformanceDate() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of Drug Resistance test",
					item.getTestPerformanceDate());
			drugResistanceTest.addItem(drugResistanceTestDateOfTestPerformance);
		}
		if (item.getSpecimenCollectionPlace() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for Drug Resistance test",
					item.getSpecimenCollectionPlace().getName());

			drugResistanceTest.addItem(drugResistanceTestPlaceOfSpecimenCollection);
		}

		if (item.getTestResultOther() != null) {
			QuestionnaireResponseItemComponent testResult = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestResultOther, "Drug resistance test result",
					item.getTestResultOther().getCode(), item.getTestResultOther().getDisplay());
			drugResistanceTest.addItem(testResult);
		}
		return drugResistanceTest;
	}

	public QuestionnaireResponseItemComponent generateArvTreatment(ArvTreatmentDto item) {
		QuestionnaireResponseItemComponent arvTreatment = createEmptyItemCompenent(HivConst.QRLinkIdArvTreatment,
				"ARV Treatment");

		if (item.getInitiationDate() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStart = createQRItemCompenentValueDate(
					HivConst.QRLinkIdInitiationDate, HivConst.QRTextInitiationDate, item.getInitiationDate());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStart);
		}
		if (item.getInitiationFacility() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdArvTreatmentInitiationFacility, HivConst.QRTextArvTreatmentInitiationFacility,
					item.getInitiationFacility().getName());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}

		if (item.getEnrollmentDate() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStart = createQRItemCompenentValueDate(
					HivConst.QRLinkIdEnrollmentDate, HivConst.QRTextEnrollmentDate, item.getEnrollmentDate());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStart);
		}
		if (item.getEnrollmentFacility() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdArvTreatmentEnrollmentFacility, HivConst.QRTextArvTreatmentEnrollmentFacility,
					item.getEnrollmentFacility().getName());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}
		if (item.getEnrollmentType() != null) {
			QuestionnaireResponseItemComponent enrollmentType = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdEnrollmentType, HivConst.QRTextEnrollmentType, item.getEnrollmentType().getCode(),
					item.getEnrollmentType().getDisplay());

			arvTreatment.addItem(enrollmentType);
		}
		if (item.getFacilityTransferIn() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdFacilityTransferIn, HivConst.QRTextFacilityTransferIn,
					item.getFacilityTransferIn().getName());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}
		if (item.getTreatmentStopDate() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStop = createQRItemCompenentValueDate(
					HivConst.QRLinkIdArvTreatmentStopDate, HivConst.QRTextArvTreatmentStopDate,
					item.getTreatmentStopDate());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStop);
		}

		///
		if (item.getDateNextAppointment() != null) {
			QuestionnaireResponseItemComponent dateNextAppointment = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateNextAppointment, HivConst.QRTextDateNextAppointment,
					item.getDateNextAppointment());

			arvTreatment.addItem(dateNextAppointment);
		}
		if (item.getDateLastExamination() != null) {
			QuestionnaireResponseItemComponent dateLastExamination = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateLastExamination, HivConst.QRTextDateLastExamination,
					item.getDateLastExamination());

			arvTreatment.addItem(dateLastExamination);
		}
		if (item.getTreatmentStopReason() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfLossToFollowUp = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdArvTreatmentStopReason, HivConst.QRTextArvTreatmentStopReason,
					item.getTreatmentStopReason().getCode(), item.getTreatmentStopReason().getDisplay());

			arvTreatment.addItem(arvTreatmentDateOfLossToFollowUp);
		}

		if (item.getPlaceTransferOut() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceTransferredOut = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfTransferredOut, "Place ARV treatment transferred out",
					item.getPlaceTransferOut().getName());
			arvTreatment.addItem(arvTreatmentPlaceTransferredOut);
		}
		///
		QuestionnaireResponseItemComponent regimen = createEmptyItemCompenent(HivConst.QRLinkIdRegimen,
				"ARV treatment regimen");

		if (item.getArvRegimens() != null && item.getArvRegimens().size() > 0) {
			for (PatientRegimenDto data : item.getArvRegimens()) {
				if (data.getDateRegimenStarted() != null) {
					QuestionnaireResponseItemComponent dateRegimenStarted = createQRItemCompenentValueDate(
							HivConst.QRLinkIdArvRegimenDateStarted, HivConst.QRTextArvRegimenDateStarted,
							data.getDateRegimenStarted());
					regimen.addItem(dateRegimenStarted);
				}
				if (data.getDateRegimenStopped() != null) {
					QuestionnaireResponseItemComponent dateRegimenStopped = createQRItemCompenentValueDate(
							HivConst.QRLinkIdArvRegimenDateStopped, HivConst.QRTextArvRegimenDateStopped,
							data.getDateRegimenStopped());
					regimen.addItem(dateRegimenStopped);
				}
				if (data.getName() != null) {
					QuestionnaireResponseItemComponent name = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdArvRegimenName, HivConst.QRTextArvRegimenName, data.getName().getCode(),
							data.getName().getDisplay());
					regimen.addItem(name);
				}
				if (data.getLine() != null) {
					QuestionnaireResponseItemComponent line = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdArvRegimenLine, HivConst.QRTextArvRegimenLine, data.getLine().getCode(),
							data.getLine().getDisplay());
					regimen.addItem(line);
				}
				if (data.getRegimenChangeReason() != null) {
					QuestionnaireResponseItemComponent reason = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdArvRegimenChangeReason, HivConst.QRTextArvRegimenChangeReason,
							data.getRegimenChangeReason().getCode(), data.getRegimenChangeReason().getDisplay());
					regimen.addItem(reason);
				}
			}
		}
		if (item.getWhoStage() != null && item.getWhoStage().size() > 0) {

			for (WHOStateDto element : item.getWhoStage()) {
				QuestionnaireResponseItemComponent whoStage = new QuestionnaireResponseItemComponent();
				whoStage.setLinkId(HivConst.QRLinkIdWhoStage);
				whoStage.setText(HivConst.QRTextWhoStage);
				if (element.getWhoClinicalStage() != null) {

					QuestionnaireResponseItemComponent stage = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdWhoClinicalStage, HivConst.QRTextWhoClinicalStage,
							element.getWhoClinicalStage().getCode(), element.getWhoClinicalStage().getDisplay());

					// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					// String dateFormat = formatter.format(element.getEvalDate());

					whoStage.addItem(stage);

				}
				if (element.getDateWhoStage() != null) {
					QuestionnaireResponseItemComponent dateStage = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateWhoStage, HivConst.QRTextDateWhoStage, element.getDateWhoStage());

					whoStage.addItem(dateStage);
				}
				arvTreatment.addItem(whoStage);

			}
		}
		arvTreatment.addItem(regimen);
		return arvTreatment;
	}

	public QuestionnaireResponseItemComponent generateComorbidities(PatientDto data) {
		QuestionnaireResponseItemComponent comorbidities = createEmptyItemCompenent(HivConst.QRLinkIdComorbidities,
				"Comorbidities");
		// Tuberculosis

		// Tuberculosis.TPT

		if (data.getTuberculosis() != null && data.getTuberculosis().size() > 0) {
			QuestionnaireResponseItemComponent tuberculosis = createEmptyItemCompenent(HivConst.QRLinkIdTuberculosis,
					HivConst.QRTextTuberculosis);
			for (TuberculosisDto item : data.getTuberculosis()) {

				if (item.getDiagnosisDate() != null) {
					QuestionnaireResponseItemComponent tbDiagnosisDate = createQRItemCompenentValueDate(
							HivConst.QRLinkIdTbDiagnosisDate, HivConst.QRTextTbDiagnosisDate, item.getDiagnosisDate());
					tuberculosis.addItem(tbDiagnosisDate);
				}

				if (item.getTreatment() != null && item.getTreatment().size() > 0) {
					for (CoMorbidityTreatmentDto treatment : item.getTreatment()) {
						QuestionnaireResponseItemComponent tbt = generateTbTreatment(treatment);
						tuberculosis.addItem(tbt);

					}
				}

				if (item.getPrevent() != null && item.getPrevent().size() > 0) {
					for (CoMorbidityTreatmentDto prevent : item.getPrevent()) {
						QuestionnaireResponseItemComponent tpt = generateTPT(prevent);
						tuberculosis.addItem(tpt);
					}
				}

			}
			comorbidities.addItem(tuberculosis);
		}

		// HBV-HCV
		QuestionnaireResponseItemComponent hbvHcv = createEmptyItemCompenent(HivConst.QRLinkIdHbvHcv, "HBV and HCV");
		// HBV
		if (data.getListOfHbv() != null && data.getListOfHbv().size() > 0) {
			for (HbvDto item : data.getListOfHbv()) {
				QuestionnaireResponseItemComponent hbvTreatment = generateHbvTreatment(item);
				hbvHcv.addItem(hbvTreatment);
			}
		}
		// HCV
		if (data.getListOfHcv() != null && data.getListOfHcv().size() > 0) {
			for (HcvDto item : data.getListOfHcv()) {
				QuestionnaireResponseItemComponent hcvTreatment = generateHcvTreatment(item);
				hbvHcv.addItem(hcvTreatment);

			}
		}
		comorbidities.addItem(hbvHcv);
		return comorbidities;
	}

	public QuestionnaireResponseItemComponent generateTPT(CoMorbidityTreatmentDto item) {
		QuestionnaireResponseItemComponent tpt = createEmptyItemCompenent(HivConst.QRLinkIdTpt, "TPT");
		if (item.getStartDate() != null) {
			QuestionnaireResponseItemComponent tptDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TPT started", item.getStartDate());
			tpt.addItem(tptDateStarted);
		}
		if (item.getEndDate() != null) {
			QuestionnaireResponseItemComponent tptDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TPT completed", item.getEndDate());
			tpt.addItem(tptDateCompleted);
		}
		if (item.getPlaceProvided() != null) {
			QuestionnaireResponseItemComponent tptPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TPT provided", item.getPlaceProvided().getName());

			tpt.addItem(tptPlaceProvided);
		}
		if (item.getIsComplete() != null) {
			QuestionnaireResponseItemComponent isCompletes = createQRItemCompenentValueBoolean(
					HivConst.QRLinkIdTptCompleted, HivConst.QRTextTptCompleted, item.getIsComplete());
			tpt.addItem(isCompletes);
		}
		return tpt;
	}

	public QuestionnaireResponseItemComponent generateTbTreatment(CoMorbidityTreatmentDto item) {
		QuestionnaireResponseItemComponent tbTreatment = createEmptyItemCompenent(HivConst.QRLinkIdTbTreatment,
				"TB Treatment");

		if (item.getStartDate() != null) {
			QuestionnaireResponseItemComponent tbTreatmentDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TB Treatment started", item.getStartDate());

			tbTreatment.addItem(tbTreatmentDateStarted);
		}

		if (item.getEndDate() != null) {
			QuestionnaireResponseItemComponent tbTreatmentDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TB Treatment completed", item.getEndDate());
			tbTreatment.addItem(tbTreatmentDateCompleted);
		}

		if (item.getPlaceProvided() != null) {
			QuestionnaireResponseItemComponent tbTreamentplaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TB Treatment provided", item.getPlaceProvided().getName());
			tbTreatment.addItem(tbTreamentplaceProvided);
		}
		return tbTreatment;
	}

	public QuestionnaireResponseItemComponent generateHbvTreatment(HbvDto item) {
		QuestionnaireResponseItemComponent hbvTreatment = createEmptyItemCompenent(HivConst.QRLinkIdHbv, "HBV");
		if (item.getDiagnosisDate() != null) {
			QuestionnaireResponseItemComponent hbvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HBV diagnosis", item.getDiagnosisDate());
			hbvTreatment.addItem(hbvDiagnosisDate);
		}

		if (item.getTreatment() != null && item.getTreatment().size() > 0 && item.getTreatment().get(0) != null) {
			QuestionnaireResponseItemComponent hbvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of HBV treatment start",
					item.getTreatment().get(0).getStartDate());
			hbvTreatment.addItem(hbvStartDate);

			QuestionnaireResponseItemComponent hbvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of HBV treatment end",
					item.getTreatment().get(0).getEndDate());
			hbvTreatment.addItem(hbvEndDate);

			if (item.getTreatment().get(0).getPlaceProvided() != null) {
				QuestionnaireResponseItemComponent hbvPlaceProvided = createQRItemCompenentValueString(
						HivConst.QRLinkIdPlaceProvided, "Place HBV treatment provided",
						item.getTreatment().get(0).getPlaceProvided().getName());
				hbvTreatment.addItem(hbvPlaceProvided);
			}
		}

		return hbvTreatment;
	}

	public QuestionnaireResponseItemComponent generateHcvTreatment(HcvDto item) {
		QuestionnaireResponseItemComponent hcvTreatment = createEmptyItemCompenent(HivConst.QRLinkIdHcv, "HCV");
		if (item.getDiagnosisDate() != null) {
			QuestionnaireResponseItemComponent hbvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HCV diagnosis", item.getDiagnosisDate());
			hcvTreatment.addItem(hbvDiagnosisDate);
		}

		if (item.getTreatment() != null && item.getTreatment().size() > 0 && item.getTreatment().get(0) != null) {
			QuestionnaireResponseItemComponent hbvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of HCV treatment start",
					item.getTreatment().get(0).getStartDate());
			hcvTreatment.addItem(hbvStartDate);

			QuestionnaireResponseItemComponent hbvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of HCV treatment end",
					item.getTreatment().get(0).getEndDate());
			hcvTreatment.addItem(hbvEndDate);

			if (item.getTreatment().get(0).getPlaceProvided() != null) {
				QuestionnaireResponseItemComponent hbvPlaceProvided = createQRItemCompenentValueString(
						HivConst.QRLinkIdPlaceProvided, "Place HCV treatment provided",
						item.getTreatment().get(0).getPlaceProvided().getName());
				hcvTreatment.addItem(hbvPlaceProvided);
			}
		}

		return hcvTreatment;
	}

	public QuestionnaireResponseItemComponent generatePregnancy(PregnancyDto item) {
		QuestionnaireResponseItemComponent pregnancy = createEmptyItemCompenent(HivConst.QRLinkIdPregnancies,
				"Pregnancies");
		if (item.getDateReported() != null) {
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDatePregnancyReported, "Date pregnancy reported", item.getDateReported());
			pregnancy.addItem(datePregnancyReported);
		}
		if (item.getDeliveryPlace() != null) {
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueString(
					HivConst.QRLinkIdDeliveryPlace, "Delivery place", item.getDeliveryPlace().getName());
			pregnancy.addItem(datePregnancyReported);
		}
		if (item.getPlaceReported() != null) {
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceReported, "Place pregnancy reported", item.getPlaceReported().getName());
			pregnancy.addItem(datePregnancyReported);
		}

		QuestionnaireResponseItemComponent outcome = new QuestionnaireResponseItemComponent();
		outcome.setLinkId(HivConst.QRLinkIdPregnancyOutcomes);
		outcome.setText(HivConst.QRTextPregnancyOutcomes);

		if (item.getOutcomeCode() != null) {
			QuestionnaireResponseItemComponent outcomeCode = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdPregnancyOutcomeCode, "Pregnancy outcome code", item.getOutcomeCode().getCode(),
					item.getOutcomeCode().getDisplay());
			outcome.addItem(outcomeCode);
		}
		if (item.getDeliveryDate() != null && item.getDeliveryDate().size()>0) {
			QuestionnaireResponseItemComponent deliveryDate = createQRItemCompenentValueDateTime(
					HivConst.QRLinkIdDeliveryDate, "Delivery Date", item.getDeliveryDate().get(0));
			outcome.addItem(deliveryDate);
		}
		if (item.getGestationalAgeAtDelivery() != null && item.getGestationalAgeAtDelivery().size()>0) {
			QuestionnaireResponseItemComponent gestationalAgeAtDelivery = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdGestationalAgeAtDelivery, "Gestational age at delivery (weeks)", item.getGestationalAgeAtDelivery().get(0));
			outcome.addItem(gestationalAgeAtDelivery);
		}

		if (item.getChilds() != null && item.getChilds().size() > 0) {
			QuestionnaireResponseItemComponent child = new QuestionnaireResponseItemComponent();
			child.setLinkId(HivConst.QRLinkIdPregnancyOutcomesChild);
			child.setText(HivConst.QRTextPregnancyOutcomesChild);
			for (ChildDto dto : item.getChilds()) {

				if (dto.getBirthWeight() != null) {
					QuestionnaireResponseItemComponent childBirthWeight = createQRItemCompenentValueString(
							HivConst.QRLinkIdPregnancyBirthWeight, "Weight at birth (kg)", dto.getBirthWeight() + "");
					child.addItem(childBirthWeight);
				}

				if (dto.getHivStatus() != null) {
					QuestionnaireResponseItemComponent birthDefects = createQRItemCompenentValueBoolean(
							HivConst.QRLinkIdPregnancyBirthDefects, HivConst.QRTextPregnancyBirthDefects, true);
					child.addItem(birthDefects);

					QuestionnaireResponseItemComponent hivStatus = createQRItemCompenentValueCoding(
							HivConst.QRLinkIdPregnancyHivStatus, HivConst.QRTextPregnancyHivStatus,
							dto.getHivStatus().getCode(), dto.getHivStatus().getDisplay());
					child.addItem(hivStatus);
					
					QuestionnaireResponseItemComponent diagnosisDate = createQRItemCompenentValueDate(
							HivConst.QRLinkIdHivDiagnosisDate, HivConst.QRLinkIdChildHivDiagnosisDate,
							dto.getHivDiagnosisDate());
					child.addItem(diagnosisDate);
					
					QuestionnaireResponseItemComponent arvStartDate = createQRItemCompenentValueDate(
							HivConst.QRLinkIdChildArvStartDate, HivConst.QRTextChildArvStartDate,
							dto.getChildArvStartDate());
					child.addItem(arvStartDate);
					// }
				} else {
					QuestionnaireResponseItemComponent birthDefects = createQRItemCompenentValueBoolean(
							HivConst.QRLinkIdPregnancyBirthDefects, HivConst.QRTextPregnancyBirthDefects, false);
					child.addItem(birthDefects);
				}

			}
			outcome.addItem(child);
		}
		pregnancy.addItem(outcome);
		return pregnancy;
	}

	public Bundle convertListObjectToBundle(List<PatientDto> listPatientDto) {
		Bundle bundle = new Bundle();
		for (PatientDto data : listPatientDto) {
			// Gson gson = new Gson();
			// Type type = new TypeToken<PatientDto>() {
			// }.getType();
			// PatientDto data = gson.fromJson(caseReport.getContent(), type);
			FhirContext ctx = FhirContext.forR4();
			IParser parser = ctx.newJsonParser();

			QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
			// Meta
			Meta meta = new Meta();
			CanonicalType canonicalType = new CanonicalType();
			canonicalType.setValue("http://hl7.org/fhir/uv/sdc/StructureDefinition/sdc-questionnaireresponse|2.7");
			List<CanonicalType> listCanonicalType = new ArrayList<CanonicalType>();
			listCanonicalType.add(canonicalType);
			// Meta--Profile
			meta.setProfile(listCanonicalType);
			List<Coding> listCoding = new ArrayList<Coding>();
			Coding coding = new Coding();
			coding.setCode("lformsVersion: 28.1.1");
			listCoding.add(coding);
			// Meta--Tag
			meta.setTag(listCoding);
			questionnaireResponse.setMeta(meta);
			// Status
			questionnaireResponse.setStatus(QuestionnaireResponseStatus.COMPLETED);
			// Authored
			questionnaireResponse.setAuthored(new Date());

			List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();

			// Title
			QuestionnaireResponseItemComponent title = new QuestionnaireResponseItemComponent();
			title.setLinkId(HivConst.QRLinkIdTitle);
			title.setText("HIV Case Report");
			listItem.add(title);

			// instructions
			QuestionnaireResponseItemComponent instructions = new QuestionnaireResponseItemComponent();
			instructions.setLinkId(HivConst.QRLinkIdInstructions);
			instructions.setText(
					"Reporting instructions: Monthly - applicable to each patient during the reporting period");
			listItem.add(instructions);

			QuestionnaireResponseItemComponent orgUnitName = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdOrganizationUnitName, "Facility ID", "6", "CS API");
			listItem.add(orgUnitName);

			// Date of Report
			QuestionnaireResponseItemComponent reportDate = new QuestionnaireResponseItemComponent();
			reportDate.setLinkId(HivConst.QRLinkIdReportDate);
			reportDate.setText("Date of Report");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerReportDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerReportDate = new QuestionnaireResponseItemAnswerComponent();
			answerReportDate.getValueDateType().setValue(new Date());
			listAnswerReportDate.add(answerReportDate);
			reportDate.setAnswer(listAnswerReportDate);
			listItem.add(reportDate);
			// Questions
			QuestionnaireResponseItemComponent questions = new QuestionnaireResponseItemComponent();
			questions.setLinkId(HivConst.QRLinkIdQuestions);
			questions.setText("Monthly report");

			// // Patient Identification
			// QuestionnaireResponseItemComponent patientIdentification = new
			// QuestionnaireResponseItemComponent();
			// patientIdentification.setLinkId(HivConst.QRLinkIdPatientIdentification);
			// patientIdentification.setText("Patient Identification");

			// Patient Information
			if(data != null) {
				QuestionnaireResponseItemComponent patientIdentification = generatePatientIdentifying(data);
				questions.addItem(patientIdentification);
			}
			// Risk factors
			QuestionnaireResponseItemComponent riskFactors = generateRiskFactors(data);
			questions.addItem(riskFactors);
			// HIV Diagnosis
			QuestionnaireResponseItemComponent hivDiagnosis = generateHivDiagnosis(data);
			questions.addItem(hivDiagnosis);
			// Recency Test
			if (data.getHivRecencyTest() != null) {
				QuestionnaireResponseItemComponent hivRecencyTest = generateHivRecencyTest(data);
				questions.addItem(hivRecencyTest);
			}

			// CD4
			if (data.getCd4List() != null && data.getCd4List().size() > 0) {
				for (LabTestDto item : data.getCd4List()) {
					QuestionnaireResponseItemComponent lastestCd4 = generateLastestCd4(item);
					questions.addItem(lastestCd4);
				}

			}

			// VL test During ART
			if (data.getViralLoadList() != null && data.getViralLoadList().size() > 0) {
				for (LabTestDto item : data.getViralLoadList()) {
					QuestionnaireResponseItemComponent vl = generateVlDuringArt(item);
					questions.addItem(vl);
				}
			}
			// Drug Resistance Test
			if (data.getDrugResistanceList() != null && data.getDrugResistanceList().size() > 0) {
				for (LabTestDto item : data.getDrugResistanceList()) {
					QuestionnaireResponseItemComponent drugResistanceTest = generateDrugResistance(item);
					questions.addItem(drugResistanceTest);
				}
			}
			// ARV Treatment
			if (data.getListOfArvTreatment() != null && data.getListOfArvTreatment().size() > 0) {
				for (ArvTreatmentDto item : data.getListOfArvTreatment()) {
					QuestionnaireResponseItemComponent arvTreatment = generateArvTreatment(item);
					questions.addItem(arvTreatment);
				}
			}
			// Comorbidities
			QuestionnaireResponseItemComponent comorbidities = generateComorbidities(data);
			questions.addItem(comorbidities);
			// Pregnancy
			if (data.getPregnancies() != null && data.getPregnancies().size() > 0) {
				for (PregnancyDto item : data.getPregnancies()) {
					QuestionnaireResponseItemComponent pregnancy = generatePregnancy(item);
					questions.addItem(pregnancy);
				}
			}
			listItem.add(questions);
			questionnaireResponse.setItem(listItem);

			bundle.setType(Bundle.BundleType.COLLECTION);

			bundle.addEntry().setFullUrl(questionnaireResponse.getIdElement().getValue())
					.setResource(questionnaireResponse);

			String body = parser.encodeResourceToString(bundle);
			System.out.println("-----------------------------------------");
		}
		return bundle;

	}
}

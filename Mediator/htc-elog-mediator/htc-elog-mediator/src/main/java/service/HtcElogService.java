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
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseStatus;

import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import dto.HivArvTreatmentDto;
import dto.HivCd4Dto;
import dto.HivDrugResistanceDto;
import dto.HivHepatitisDto;
import dto.HivPregnancyDto;
import dto.HivTBProphylaxisDto;
import dto.HivTBTreatmentDto;
import dto.HivViralLoadDto;
import dto.HtcElogDto;
import dto.OpcAssistDto;

public class HtcElogService {
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

//	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDate(String valueString) {
//		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
//		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
//		// answerVLTestDateOfTestPerformance.getValueStringType().setValue(data.getRapidVLTestDate());
//		DateType dateType = new DateType(valueString);
//		
//		itemAnswerComponent.setValue(dateType);
//
//		listItemAnswerComponent.add(itemAnswerComponent);
//		return listItemAnswerComponent;
//	}
	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDate(String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		// answerVLTestDateOfTestPerformance.getValueStringType().setValue(data.getRapidVLTestDate());
		Date dateType = null;
		try {
			dateType = new SimpleDateFormat("dd/MM/yyyy").parse(valueString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itemAnswerComponent.getValueDateType().setValue(dateType);

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

	public QuestionnaireResponseItemComponent createQRItemCompenentValueDate(String linkId, String text,
			String valueString) {
		QuestionnaireResponseItemComponent itemComponent = new QuestionnaireResponseItemComponent();
		itemComponent.setLinkId(linkId);
		itemComponent.setText(text);
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = createQRListItemAnswerComponentValueDate(
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

	public QuestionnaireResponseItemComponent generatePatientInfomation(HtcElogDto data) {
		
		// patient_identification
		QuestionnaireResponseItemComponent patientIdentification = new QuestionnaireResponseItemComponent();
		patientIdentification.setLinkId(HivConst.QRLinkIdPatientIdentification);
		patientIdentification.setText("Part I: Identifying Information");
		
		if (data.getArtID() != null) {
			// artID
			QuestionnaireResponseItemComponent arvIdNr = createQRItemCompenentValueString(HivConst.QRLinkIdPatientArvNumber,
					"Patient ARV identification number", data.getArtID().toString());
			patientIdentification.addItem(arvIdNr);
		}
		
		QuestionnaireResponseItemComponent patientInformation = createEmptyItemCompenent(
				HivConst.QRLinkIdPersonalInformation, HivConst.QRTextPersonalInformation);

		if (data.getName() != null) {
			String fullNamePart = data.getName();
			String[] nameParts = fullNamePart.split(" ", 2);
			String familyNamePart = nameParts[0];
			String givenNamePart = nameParts[1];
			QuestionnaireResponseItemComponent name = createEmptyItemCompenent(HivConst.QRLinkIdPatientName, "Name");
			// familyName
			QuestionnaireResponseItemComponent familyName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientFamilyName, "Family name", familyNamePart);
			name.addItem(familyName);
			// GivenName
			QuestionnaireResponseItemComponent givenName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientGivenName, "Given name", givenNamePart);
			name.addItem(givenName);

			// FullName
			QuestionnaireResponseItemComponent fullName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientFullName, "Full name", fullNamePart);
			name.addItem(fullName);
			patientInformation.addItem(name);
		}

		if (data.getEthnicity() != null) {
			QuestionnaireResponseItemComponent ethnicity = createQRItemCompenentValueCoding(HivConst.QRLinkIdEthnicity,
					"Ethnicity", data.getEthnicity().getCode(), data.getEthnicity().getName());

			patientInformation.addItem(ethnicity);
		}
		if (data.getGender() != null) {
			QuestionnaireResponseItemComponent gender = createQRItemCompenentValueCoding(HivConst.QRLinkIdGender,
					"Gender", data.getGender().getCode(), data.getGender().getDisplay());
			patientInformation.addItem(gender);
		}
		if (data.getBirthDate() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String date = df.format(data.getBirthDate());
			QuestionnaireResponseItemComponent birthYear = createQRItemCompenentValueDate(
					HivConst.QRLinkIdYearOfBirth, "Year of Birth", date);
			patientInformation.addItem(birthYear);
		}
		QuestionnaireResponseItemComponent idCccd = createEmptyItemCompenent(HivConst.QRLinkIdIdentification,
				"Identification");

		if (data.getPassportNumber() != null) {
			// Passport
			QuestionnaireResponseItemComponent passport_nr = createQRItemCompenentValueString(
					HivConst.QRLinkIdPassportNumber, "Passport Number", data.getPassportNumber());
			idCccd.addItem(passport_nr);
		}
		if (data.getNationalID() != null) {
			// NationalId
			QuestionnaireResponseItemComponent nationalIdRr = createQRItemCompenentValueString(
					HivConst.QRLinkIdNationalId9, "National Id Number", data.getNationalID());
			idCccd.addItem(nationalIdRr);
		}
		
		patientInformation.addItem(idCccd);

		QuestionnaireResponseItemComponent residence = new QuestionnaireResponseItemComponent();
		residence.setLinkId(HivConst.QRLinkIdResidence);
		residence.setText("Residence");
		/////

		if (data.getCurrentAddress() != null) {
			QuestionnaireResponseItemComponent current = new QuestionnaireResponseItemComponent();
			current.setLinkId(HivConst.QRLinkIdCurrentResidence);
			current.setText("Current Residence");
			// currentStressAddress
			if (data.getCurrentAddress().getLine() != null) {

				// currentStressAddress
				QuestionnaireResponseItemComponent currentStressAddress = createQRItemCompenentValueString(
						HivConst.QRLinkIdAddress, HivConst.QRTextAddress, data.getCurrentAddress().getLine());
				current.addItem(currentStressAddress);
			}
			// currentCommune
			if (data.getCurrentAddress().getCommuneName() != null) {
				// currentCommune
				QuestionnaireResponseItemComponent currentCommune = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getCurrentAddress().getCommuneCode(),
						data.getCurrentAddress().getCommuneName());
				current.addItem(currentCommune);
			}
			// currentDistrict
			if (data.getCurrentAddress().getDistrictName() != null) {
				// currentDistrict
				QuestionnaireResponseItemComponent currentDistrict = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getCurrentAddress().getDistrictCode(),
						data.getCurrentAddress().getDistrictName());
				current.addItem(currentDistrict);
			}
			// currentProvince
			if (data.getCurrentAddress().getCityName() != null) {
				// currentProvince
				QuestionnaireResponseItemComponent currentProvince = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getCurrentAddress().getCityCode(),
						data.getCurrentAddress().getCityName());
				current.addItem(currentProvince);
			}
			// currentCountry
			if (data.getCurrentAddress().getCountryName() != null) {
				// currentCountry
				QuestionnaireResponseItemComponent currentCountry = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getCurrentAddress().getCountryCode(),
						data.getCurrentAddress().getCountryName());
				current.addItem(currentCountry);
			}
			// currentText
//			if (data.getCurrentAddress().getText() != null) {
//				QuestionnaireResponseItemComponent currentText = createQRItemCompenentValueString(
//						HivConst.QRLinkIdCurrentResidenceText, "Current Text", data.getCurrentAddress().getText());
//				current.addItem(currentText);
//			}

			residence.addItem(current);
		}
		if (data.getCurrentAddress() != null) {
			QuestionnaireResponseItemComponent permanent = new QuestionnaireResponseItemComponent();
			permanent.setLinkId(HivConst.QRLinkIdPermanentResidence);
			permanent.setText("Permanent residence");
			// permanentStressAddress
			if (data.getRegisteredAddress().getLine() != null) {
				// permanentStressAddress
				QuestionnaireResponseItemComponent permanentStressAddress = createQRItemCompenentValueString(
						HivConst.QRLinkIdAddress, HivConst.QRTextAddress,
						data.getRegisteredAddress().getLine());
				permanent.addItem(permanentStressAddress);
			}
			// permanentCommune
			if (data.getRegisteredAddress().getCommuneName() != null) {
				// permanentCommune
				QuestionnaireResponseItemComponent permanentCommune = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCommune, HivConst.QRTextCommune, data.getRegisteredAddress().getCommuneCode(),
						data.getRegisteredAddress().getCommuneName());
				permanent.addItem(permanentCommune);
			}
			// PermanentDistrict
			if (data.getRegisteredAddress().getDistrictName() != null) {
				// PermanentDistrict
				QuestionnaireResponseItemComponent permanentDistrict = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict, data.getRegisteredAddress().getDistrictCode(),
						data.getRegisteredAddress().getDistrictName());
				permanent.addItem(permanentDistrict);
			}
			// PermanentProvince
			if (data.getRegisteredAddress().getCityName() != null) {
				// PermanentProvince
				QuestionnaireResponseItemComponent permanentProvince = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdProvince, HivConst.QRTextProvince, data.getRegisteredAddress().getCityCode(),
						data.getRegisteredAddress().getCityName());
				permanent.addItem(permanentProvince);
			}
			// PermanentCountry
			if (data.getRegisteredAddress().getCountryName() != null) {
				// PermanentCountry
				QuestionnaireResponseItemComponent permanentCountry = createQRItemCompenentValueCoding(
						HivConst.QRLinkIdCountry, HivConst.QRTextCountry, data.getRegisteredAddress().getCountryCode(),
						data.getRegisteredAddress().getCountryName());
				permanent.addItem(permanentCountry);
			}
			// PermanentText
//			if (data.getRegisteredAddress().getText() != null) {
//				QuestionnaireResponseItemComponent permanentText = createQRItemCompenentValueString(
//						HivConst.QRLinkIdPermanentResidenceText, "Permanent Text",
//						data.getRegisteredAddress().getText());
//				permanent.addItem(permanentText);
//			}

			residence.addItem(permanent);
		}
		patientInformation.addItem(residence);
		patientIdentification.addItem(patientInformation);
		return patientIdentification;

	}

	public QuestionnaireResponseItemComponent generateRiskFactors(HtcElogDto data) {
		QuestionnaireResponseItemComponent riskFactors = createEmptyItemCompenent(HivConst.QRLinkIdRiskFactor,
				"Risk Factors");
		////
		if (data.getRiskPopulations() != null && data.getRiskPopulations().size() > 0) {
//			QuestionnaireResponseItemComponent riskPopulation = new QuestionnaireResponseItemComponent();
//			riskPopulation.setLinkId(HivConst.QRLinkIdRiskPopulation);
//			riskPopulation.setText("Risk Population");
//			List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskPopulation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
//
//			for (SystemCodeDto item : data.getRiskPopulations()) {
//				QuestionnaireResponseItemAnswerComponent answerRiskPopulation = new QuestionnaireResponseItemAnswerComponent();
//				answerRiskPopulation.getValueCoding().setCode(item.getCode());
//				answerRiskPopulation.getValueCoding().setDisplay(item.getDisplay());
//				listAnswerRiskPopulation.add(answerRiskPopulation);
//			}
//			riskPopulation.setAnswer(listAnswerRiskPopulation);
			QuestionnaireResponseItemComponent riskPopulation = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdPopulationGroup, "Risk Population", data.getRiskPopulations().get(0).getCode(), data.getRiskPopulations().get(0).getDisplay());
			riskFactors.addItem(riskPopulation);

		}
		///
		if (data.getRiskBehaviors() != null && data.getRiskBehaviors().size() > 0) {
			
			QuestionnaireResponseItemComponent riskRiskBehavior = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdRiskBehavior, "Risk Behavior", data.getRiskBehaviors().get(0).getCode(), data.getRiskBehaviors().get(0).getDisplay());
			riskFactors.addItem(riskRiskBehavior);
			
//			QuestionnaireResponseItemComponent riskRiskBehavior = new QuestionnaireResponseItemComponent();
//			riskRiskBehavior.setLinkId(HivConst.QRLinkIdRiskBehavior);
//			riskRiskBehavior.setText("Risk Behavior");
//			List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskPopulation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
//
//			for (SystemCodeDto item : data.getRiskPopulations()) {
//				QuestionnaireResponseItemAnswerComponent answerRiskPopulation = new QuestionnaireResponseItemAnswerComponent();
//				answerRiskPopulation.getValueCoding().setCode(item.getCode());
//				answerRiskPopulation.getValueCoding().setDisplay(item.getDisplay());
//				listAnswerRiskPopulation.add(answerRiskPopulation);
//			}
//			riskRiskBehavior.setAnswer(listAnswerRiskPopulation);
//			riskFactors.addItem(riskRiskBehavior);

		}
		///
		if (data.getTransmissionRoutes() != null && data.getTransmissionRoutes().size() > 0) {
			
			QuestionnaireResponseItemComponent riskTransmissionRoutes = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTransmssionRoute, "Transmssion Route", data.getTransmissionRoutes().get(0).getCode(), data.getTransmissionRoutes().get(0).getDisplay());
			riskFactors.addItem(riskTransmissionRoutes);
			
//			QuestionnaireResponseItemComponent riskTransmissionRoutes = new QuestionnaireResponseItemComponent();
//			riskTransmissionRoutes.setLinkId(HivConst.QRLinkIdTransmssionRoute);
//			riskTransmissionRoutes.setText("Transmssion Route");
//			List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskPopulation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
//
//			for (SystemCodeDto item : data.getRiskPopulations()) {
//				QuestionnaireResponseItemAnswerComponent answerRiskPopulation = new QuestionnaireResponseItemAnswerComponent();
//				answerRiskPopulation.getValueCoding().setCode(item.getCode());
//				answerRiskPopulation.getValueCoding().setDisplay(item.getDisplay());
//				listAnswerRiskPopulation.add(answerRiskPopulation);
//			}
//			riskTransmissionRoutes.setAnswer(listAnswerRiskPopulation);
//			riskFactors.addItem(riskTransmissionRoutes);

		}
		return riskFactors;
	}

	public QuestionnaireResponseItemComponent generateHivDiagnosis(HtcElogDto data) {
		QuestionnaireResponseItemComponent hivDiagnosis = createEmptyItemCompenent(HivConst.QRLinkIdHivDiagnosis,
				"HIV Diagnosis");

		// Date Of Confirmation
		if (data.getDiagnosis() != null&&data.getDiagnosis().getConfirmatoryDate() != null) {
			DateFormat df = new SimpleDateFormat();
			String date = df.format(data.getDiagnosis().getConfirmatoryDate());
			QuestionnaireResponseItemComponent dateOfConfirmation = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfConfirmation, "Date of Confirmation",
					date);

			hivDiagnosis.addItem(dateOfConfirmation);
		}
		// Confirming Lab
		if (data.getDiagnosis() != null&&data.getDiagnosis().getConfirmatoryLab() != null&&data.getDiagnosis().getConfirmatoryLab().getName() != null) {
			QuestionnaireResponseItemComponent confirmingLab = createQRItemCompenentValueString(
					HivConst.QRLinkIdConfirmingLab, "Confirming Lab", data.getDiagnosis().getConfirmatoryLab().getName());
			hivDiagnosis.addItem(confirmingLab);

		}
		// Date Of Specimen Collection
		if (data.getDiagnosis() != null&&data.getDiagnosis().getSpecimenCollectionDate() != null) {
			DateFormat df = new SimpleDateFormat();
			String date = df.format(data.getDiagnosis().getSpecimenCollectionDate());
			QuestionnaireResponseItemComponent confirmingLab = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfSpecimenCollection, "Date Of Specimen Collection", date);
			hivDiagnosis.addItem(confirmingLab);

		}
		// Place Of Specimen Collection
		if (data.getDiagnosis() != null&&data.getDiagnosis().getPlaceOfSpecimenCollection() != null) {
			QuestionnaireResponseItemComponent confirmingLab = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place Of Specimen Collection", data.getDiagnosis().getPlaceOfSpecimenCollection());
			hivDiagnosis.addItem(confirmingLab);
		}
		return hivDiagnosis;
	}

	public QuestionnaireResponseItemComponent generateHivRecencyTest(HtcElogDto data) {
		QuestionnaireResponseItemComponent hivRecencyTest = createEmptyItemCompenent(HivConst.QRLinkIdHivRecencyTest,
				"HIV Recency Test");
		QuestionnaireResponseItemComponent vlRecency = null;
			if(data.getHivRecencyTest().getVlTest() != null) {

				QuestionnaireResponseItemComponent vlTest = createEmptyItemCompenent(HivConst.QRLinkIdRecencyVlTest,
						"Rapid VL test");
				if (data.getHivRecencyTest().getVlTest().getTestPerformanceDate() != null) {
					QuestionnaireResponseItemComponent vlTestDateOfTestPerformance = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateOfTestPerformance, "Date of rapid test performance",
							data.getHivRecencyTest().getVlTest().getTestPerformanceDate().toString());
					vlTest.addItem(vlTestDateOfTestPerformance);
				}
				if (data.getHivRecencyTest().getVlTest().getValueNumber() != null) {
					QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
							HivConst.QRLinkIdVlRecencyTestResult, "VL recency test result",
							data.getHivRecencyTest().getVlTest().getValueNumber()+"");
	
					vlTest.addItem(vlRecencyTestResult);
				}
				if (data.getHivRecencyTest().getVlTest().getTestResultOther() != null) {
					QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
							HivConst.QRLinkIdVlRecencyTestResultOther, "Other of viral load test (undetectable)",
							data.getHivRecencyTest().getVlTest().getTestResultOther().getDisplay());
	
					vlTest.addItem(vlRecencyTestResult);
				}
			hivRecencyTest.addItem(vlTest);
			}
			if(data.getHivRecencyTest().getRapidTest() != null) {

				QuestionnaireResponseItemComponent rapidTest = createEmptyItemCompenent(HivConst.QRLinnkIdRecencyRapidTest,
						"Rapid VL test");
				if (data.getHivRecencyTest().getRapidTest().getSpecimenCollectionDate() != null) {
					QuestionnaireResponseItemComponent specimenCollectionDate = createQRItemCompenentValueDate(
							HivConst.QRLinkIdDateOfSpecimenCollection, "Date of specimen collection",
							data.getHivRecencyTest().getRapidTest().getSpecimenCollectionDate().toString());
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
							data.getHivRecencyTest().getVlTest().getTestPerformanceDate().toString());
					rapidTest.addItem(vlTestDateOfTestPerformance);
				}
				if (data.getHivRecencyTest().getVlTest().getValueNumber() != null) {
					QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
							HivConst.QRLinkIdVlRecencyTestResult, "VL recency test result",
							data.getHivRecencyTest().getVlTest().getValueNumber()+"");
	
					rapidTest.addItem(vlRecencyTestResult);
				}
				if (data.getHivRecencyTest().getVlTest().getTestResultOther() != null) {
					QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
							HivConst.QRLinkIdVlRecencyTestResultOther, "Other of viral load test (undetectable)",
							data.getHivRecencyTest().getVlTest().getTestResultOther().getDisplay());
	
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

	public QuestionnaireResponseItemComponent generateCd4BeforeArt(HivCd4Dto item) {
		QuestionnaireResponseItemComponent cd4BeforeART = new QuestionnaireResponseItemComponent();
		cd4BeforeART.setLinkId(HivConst.QRLinkIdCd4Test);
		cd4BeforeART.setText("CD4 test before ART");
		if (item.getCd4SampleDate() != null) {
			QuestionnaireResponseItemComponent cd4BeforeARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for CD4 test before ART",
					item.getCd4SampleDate());

			cd4BeforeART.addItem(cd4BeforeARTDateOfSpecimenCollection);
		}
		if (item.getCd4ResultDate() != null) {
			QuestionnaireResponseItemComponent cd4BeforeARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTestPerformance, "Date of CD4 test before ART performance",
					item.getCd4ResultDate());
			cd4BeforeART.addItem(cd4BeforeARTDateOfTestPerformance);
		}
		if (item.getCd4Org() != null) {
			QuestionnaireResponseItemComponent cd4BeforeARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for CD4 test before ART",
					item.getCd4Org());
			cd4BeforeART.addItem(cd4BeforeARTPlaceOfSpecimenCollection);
		}
		if (item.getCd4Result() != null) {
			QuestionnaireResponseItemComponent cd4BeforeARTTestResult = createQRItemCompenentValueString(
					HivConst.QRLinkIdTestResult, "CD4 test before ART - result", item.getCd4Result());
			cd4BeforeART.addItem(cd4BeforeARTTestResult);
		}
		return cd4BeforeART;
	}

	public QuestionnaireResponseItemComponent generateCd4DuringArt(HivCd4Dto item) {
		QuestionnaireResponseItemComponent cd4DuringART = new QuestionnaireResponseItemComponent();
		cd4DuringART.setLinkId(HivConst.QRLinkIdCd4Test);
		cd4DuringART.setText("CD4 test during ART");
		if (item.getCd4SampleDate() != null) {
			QuestionnaireResponseItemComponent cd4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for CD4 test during ART",
					item.getCd4SampleDate());
			cd4DuringART.addItem(cd4DuringARTDateOfSpecimenCollection);
		}
		if (item.getCd4ResultDate() != null) {
			QuestionnaireResponseItemComponent cd4DuringARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTestPerformance, "Date of CD4 test during ART performance",
					item.getCd4ResultDate());
			cd4DuringART.addItem(cd4DuringARTDateOfTestPerformance);
		}
		if (item.getCd4Org() != null) {
			QuestionnaireResponseItemComponent cd4DuringARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for CD4 test during ART",
					item.getCd4Org());
			cd4DuringART.addItem(cd4DuringARTPlaceOfSpecimenCollection);
		}
		if (item.getCd4Result() != null) {
			QuestionnaireResponseItemComponent cd4DuringARTTestResult = createQRItemCompenentValueString(
					HivConst.QRLinkIdTestResult, "CD4 test during ART - result", item.getCd4Result());

			cd4DuringART.addItem(cd4DuringARTTestResult);
		}
		return cd4DuringART;
	}

	public QuestionnaireResponseItemComponent generateVlDuringArt(HivViralLoadDto item) {
		QuestionnaireResponseItemComponent vl4DuringART = new QuestionnaireResponseItemComponent();
		vl4DuringART.setLinkId(HivConst.QRLinkIdVl4DuringArt);
		vl4DuringART.setText("VL test during ART");
		if (item.getVlSampleDate() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for VL test during ART",
					item.getVlSampleDate());
			vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);
		}
		if (item.getVlResultDate() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTestPerformance, "Date of VL test during ART performance",
					item.getVlResultDate());
			vl4DuringART.addItem(vl4DuringARTDateOfTestPerformance);
		}
		if (item.getVlOrg() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for VL test during ART",
					item.getVlOrg());
			vl4DuringART.addItem(vl4DuringARTPlaceOfSpecimenCollection);
		}
		if (item.getVlResult() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTTestResult = createQRItemCompenentValueString(
					HivConst.QRLinkIdTestResult, "VL test during ART - result", item.getVlResult().toString());

			vl4DuringART.addItem(vl4DuringARTTestResult);
		}
		return vl4DuringART;
	}

	public QuestionnaireResponseItemComponent generateDrugResistance(HivDrugResistanceDto item) {
		QuestionnaireResponseItemComponent drugResistanceTest = new QuestionnaireResponseItemComponent();
		drugResistanceTest.setLinkId(HivConst.QRLinkIdDrugResistanceTest);
		drugResistanceTest.setText("Drug Resistance test");
		if (item.getDrugResistanceSampleDate() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfSpecimenCollection, "Date of Specimen Collection for Drug Resistance test",
					item.getDrugResistanceSampleDate());

			drugResistanceTest.addItem(drugResistanceTestDateOfSpecimenCollection);
		}
		if (item.getDrugResistanceResultDate() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTestPerformance, "Date of Drug Resistance test",
					item.getDrugResistanceResultDate());
			drugResistanceTest.addItem(drugResistanceTestDateOfTestPerformance);
		}
		if (item.getDrugResistanceOrg() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfSpecimenCollection, "Place of Specimen Collection for Drug Resistance test",
					item.getDrugResistanceOrg());

			drugResistanceTest.addItem(drugResistanceTestPlaceOfSpecimenCollection);
		}
		if (item.getDrugResistanceResult() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestTestResult = createQRItemCompenentValueString(
					HivConst.QRLinkIdTestResult, "Drug Resistance test result", item.getDrugResistanceResult());

			drugResistanceTest.addItem(drugResistanceTestTestResult);
		}
		return drugResistanceTest;
	}

	public QuestionnaireResponseItemComponent generateArvTreatment(HivArvTreatmentDto item) {
		QuestionnaireResponseItemComponent arvTreatment = new QuestionnaireResponseItemComponent();
		arvTreatment.setLinkId(HivConst.QRLinkIdArvTreatment);
		arvTreatment.setText("ARV Treatment");
		if (item.getArvTreatmentDateStart() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStart = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of treatment start", item.getArvTreatmentDateStart());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStart);
		}
		if (item.getArvTreatmentDateEnd() != null) {
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStop = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of treatment stop", item.getArvTreatmentDateEnd());

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStop);
		}
		if (item.getArvTreatmentPlaceInitiation() != null) {
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceOfInitiation, "Place of ARV treatment initiation",
					item.getArvTreatmentPlaceInitiation());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}
		QuestionnaireResponseItemComponent regimen = new QuestionnaireResponseItemComponent();
		regimen.setLinkId(HivConst.QRLinkIdRegimen);
		regimen.setText("ARV treatment regimen");
		if (item.getArvRegimenLine() == 1) {
			QuestionnaireResponseItemComponent regimen1stLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate1stLineStarted, "Date 1st ARV regimen started",
					item.getArvTreatmentDateStart());

			regimen.addItem(regimen1stLine);
		}
		if (item.getArvRegimenLine() == 2) {
			QuestionnaireResponseItemComponent regimen2ndLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate2ndLineStarted, "Date 2nd ARV regimen started",
					item.getArvTreatmentDateStart());

			regimen.addItem(regimen2ndLine);
		}
		if (item.getArvRegimenLine() == 3) {
			QuestionnaireResponseItemComponent regimen3rdLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate3rdLineStarted, "Date 3rd ARV regimen started",
					item.getArvTreatmentDateStart());

			regimen.addItem(regimen3rdLine);
		}
		arvTreatment.addItem(regimen);
		return arvTreatment;
	}

	public QuestionnaireResponseItemComponent generateComorbidities(OpcAssistDto data) {
		QuestionnaireResponseItemComponent comorbidities = createEmptyItemCompenent(HivConst.QRLinkIdComorbidities,
				"Comorbidities");
//		Tuberculosis
		QuestionnaireResponseItemComponent tuberculosis = createEmptyItemCompenent(HivConst.QRLinkIdTuberculosis,
				"Tuberculosis");
//		Tuberculosis.TPT

		if (data.getListTBProphylaxis() != null && data.getListTBProphylaxis().size() > 0) {
			for (HivTBProphylaxisDto item : data.getListTBProphylaxis()) {
				QuestionnaireResponseItemComponent tpt = generateTPT(item);
				tuberculosis.addItem(tpt);
			}
		}

		// Comorbidities.Tuberculosis.TB Treatment
		if (data.getListTBTreatment() != null) {
			if (data.getListTBTreatment().get(data.getListTBTreatment().size() - 1) != null) {
				QuestionnaireResponseItemComponent tbDiagnosisDate = createQRItemCompenentValueDate(
						HivConst.QRLinkIdTbDiagnosisDate, "TB Diagnosis Date",
						data.getListTBTreatment().get(data.getListTBTreatment().size() - 1).getTbDiagnoseDate());
				tuberculosis.addItem(tbDiagnosisDate);
			}
		}
		if (data.getListTBTreatment() != null && data.getListTBTreatment().size() > 0) {

			for (HivTBTreatmentDto item : data.getListTBTreatment()) {
				QuestionnaireResponseItemComponent tbTreatment = generateTbTreatment(item);
				tuberculosis.addItem(tbTreatment);
			}
		}
		comorbidities.addItem(tuberculosis);

//		HBV-HCV
		QuestionnaireResponseItemComponent hbvHcv = createEmptyItemCompenent(HivConst.QRLinkIdHbvHcv, "HBV and HCV");
		// HBV
		if (data.getListHbv() != null && data.getListHbv().size() > 0) {
			for (HivHepatitisDto item : data.getListHbv()) {
				QuestionnaireResponseItemComponent hbvTreatment = generateHbvTreatment(item);
				hbvHcv.addItem(hbvTreatment);
			}
		}
		// HCV
		if (data.getListHcv() != null && data.getListHcv().size() > 0) {
			for (HivHepatitisDto item : data.getListHcv()) {
				QuestionnaireResponseItemComponent hcvTreatment = generateHcvTreatment(item);
				hbvHcv.addItem(hcvTreatment);

			}
		}
		comorbidities.addItem(hbvHcv);
		return comorbidities;
	}

	public QuestionnaireResponseItemComponent generateTPT(HivTBProphylaxisDto item) {
		QuestionnaireResponseItemComponent tpt = new QuestionnaireResponseItemComponent();
		tpt.setLinkId(HivConst.QRLinkIdTpt);
		tpt.setText("TPT");
		if (item.getTpTreamentDateStart() != null) {
			QuestionnaireResponseItemComponent tptDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TPT started", item.getTpTreamentDateStart());
			tpt.addItem(tptDateStarted);
		}
		if (item.getTpTreamentDateEnd() != null) {
			QuestionnaireResponseItemComponent tptDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TPT completed", item.getTpTreamentDateEnd());
			tpt.addItem(tptDateCompleted);
		}
		if (item.getTpTreamentOrg() != null) {
			QuestionnaireResponseItemComponent tptPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TPT provided", item.getTpTreamentOrg());

			tpt.addItem(tptPlaceProvided);
		}
		return tpt;
	}

	public QuestionnaireResponseItemComponent generateTbTreatment(HivTBTreatmentDto item) {
		QuestionnaireResponseItemComponent tbTreatment = new QuestionnaireResponseItemComponent();
		tbTreatment.setLinkId(HivConst.QRLinkIdTbTreatment);
		tbTreatment.setText("TB Treatment");
		if (item.getTbDiagnoseDate() != null) {
			QuestionnaireResponseItemComponent tbDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdTbDiagnosisDate, "TB Diagnosis Date", item.getTbDiagnoseDate());
			tbTreatment.addItem(tbDiagnosisDate);
		}
		if (item.getTbTreamentDateStart() != null) {
			QuestionnaireResponseItemComponent tbTreatmentDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TB Treatment started", item.getTbTreamentDateStart());

			tbTreatment.addItem(tbTreatmentDateStarted);
		}
		if (item.getTbTreamentDateEnd() != null) {
			QuestionnaireResponseItemComponent tbTreatmentDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TB Treatment completed", item.getTbTreamentDateEnd());
			tbTreatment.addItem(tbTreatmentDateCompleted);
		}
		if (item.getTbTreamentFacility() != null) {
			QuestionnaireResponseItemComponent tbTreamentplaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TB Treatment provided", item.getTbTreamentFacility());
			tbTreatment.addItem(tbTreamentplaceProvided);
		}
		return tbTreatment;
	}

	public QuestionnaireResponseItemComponent generateHbvTreatment(HivHepatitisDto item) {
		QuestionnaireResponseItemComponent hbvTreatment = new QuestionnaireResponseItemComponent();
		hbvTreatment.setLinkId(HivConst.QRLinkIdHbv);
		hbvTreatment.setText("HBV");
		if (item.getDiagnosisDate() != null) {
			QuestionnaireResponseItemComponent hbvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HBV diagnosis", item.getDiagnosisDate());
			hbvTreatment.addItem(hbvDiagnosisDate);
		}
		if (item.getTreatmentStartDate() != null) {
			QuestionnaireResponseItemComponent hbvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of HBV treatment start", item.getTreatmentStartDate());
			hbvTreatment.addItem(hbvStartDate);
		}
		if (item.getTreatmentEndDate() != null) {
			QuestionnaireResponseItemComponent hbvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of HBV treatment end", item.getTreatmentEndDate());

			hbvTreatment.addItem(hbvEndDate);
		}
		if (item.getPlaceProvided() != null) {
			QuestionnaireResponseItemComponent hbvPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place HBV treatment provided", item.getPlaceProvided());
			hbvTreatment.addItem(hbvPlaceProvided);
		}
		return hbvTreatment;
	}

	public QuestionnaireResponseItemComponent generateHcvTreatment(HivHepatitisDto item) {
		QuestionnaireResponseItemComponent hcvTreatment = new QuestionnaireResponseItemComponent();
		hcvTreatment.setLinkId(HivConst.QRLinkIdHcv);
		hcvTreatment.setText("HCV");
		if (item.getDiagnosisDate() != null) {
			QuestionnaireResponseItemComponent hcvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HCV diagnosis", item.getDiagnosisDate());
			hcvTreatment.addItem(hcvDiagnosisDate);
		}
		if (item.getTreatmentStartDate() != null) {
			QuestionnaireResponseItemComponent hcvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStart, "Date of HCV treatment start", item.getTreatmentStartDate());
			hcvTreatment.addItem(hcvStartDate);
		}
		if (item.getTreatmentEndDate() != null) {
			QuestionnaireResponseItemComponent hcvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of HCV treatment end", item.getTreatmentEndDate());
			hcvTreatment.addItem(hcvEndDate);
		}
		if (item.getPlaceProvided() != null) {
			QuestionnaireResponseItemComponent hcvPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place HCV treatment provided", item.getPlaceProvided());
			hcvTreatment.addItem(hcvPlaceProvided);
		}
		return hcvTreatment;
	}

	public QuestionnaireResponseItemComponent generatePregnancy(HivPregnancyDto item) {
		QuestionnaireResponseItemComponent pregnancy = createEmptyItemCompenent(HivConst.QRLinkIdPregnancies,
				"Pregnancies");
		if (item.getCreateDate() != null) {
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDatePregnancyReported, "Date pregnancy reported", item.getCreateDate());
			pregnancy.addItem(datePregnancyReported);
		}
		if (item.getChildDeliveryPlace() != null) {
			QuestionnaireResponseItemComponent childDeliveryPlace = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Reported child delivery place", item.getChildDeliveryPlace());
			pregnancy.addItem(childDeliveryPlace);
		}
		if (item.getChildDateOfBirth() != null) {
			QuestionnaireResponseItemComponent childDateOfBirth = createQRItemCompenentValueString(
					HivConst.QRLinkIdChildDateOfBirth, "Date of child birth", item.getChildDateOfBirth());
			pregnancy.addItem(childDateOfBirth);
		}
		// Hiv Status
		QuestionnaireResponseItemComponent childHivStatus = createQRItemCompenentValueString(HivConst.QRLinkIdHivStatus,
				"HIV status", item.getChildHivStatus() + "");
		pregnancy.addItem(childHivStatus);

		// Birth Weight
		QuestionnaireResponseItemComponent childBirthWeight = createQRItemCompenentValueString(
				HivConst.QRLinkIdChildBirthWeight, "Weight at birth (kg)", item.getBirthWeight() + "");
		pregnancy.addItem(childBirthWeight);
		return pregnancy;
	}

	public Bundle convertListHtcElogObjectToBundle(List<HtcElogDto> listHtcElogDto) {
		Bundle bundle = new Bundle();
		for (HtcElogDto data : listHtcElogDto) {
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
			// Date of Report
			QuestionnaireResponseItemComponent reportDate = new QuestionnaireResponseItemComponent();
			reportDate.setLinkId("reportDate");
			reportDate.setText("Date of Report");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerReportDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerReportDate = new QuestionnaireResponseItemAnswerComponent();
			answerReportDate.getValueDateType().setValue(new Date());
			listAnswerReportDate.add(answerReportDate);
			reportDate.setAnswer(listAnswerReportDate);
			listItem.add(reportDate);
			//
			QuestionnaireResponseItemComponent synOrg = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdOrganizationUnitName ,HivConst.QRTextOrganizationUnitName,
					HivConst.synOrg.HtcElog.getValue().toString(), HivConst.synOrg.HtcElog.name());
			listItem.add(synOrg);

			// Questions
			QuestionnaireResponseItemComponent questions = new QuestionnaireResponseItemComponent();
			questions.setLinkId(HivConst.QRLinkIdQuestions);
			questions.setText("Monthly report");
			// Patient Information
			QuestionnaireResponseItemComponent patientIdentification = generatePatientInfomation(data);
			questions.addItem(patientIdentification);
			// Risk factors
			QuestionnaireResponseItemComponent riskFactors = generateRiskFactors(data);
			questions.addItem(riskFactors);
			// HIV Diagnosis
			QuestionnaireResponseItemComponent hivDiagnosis = generateHivDiagnosis(data);
			questions.addItem(hivDiagnosis);
			listItem.add(questions);
			questionnaireResponse.setItem(listItem);
			
			if (data.getHivRecencyTest() != null) {
				QuestionnaireResponseItemComponent hivRecencyTest = generateHivRecencyTest(data);
				questions.addItem(hivRecencyTest);
			}

			bundle.setType(Bundle.BundleType.COLLECTION);

			bundle.addEntry().setFullUrl(questionnaireResponse.getIdElement().getValue())
					.setResource(questionnaireResponse);

//			String body = parser.encodeResourceToString(bundle);
//			System.out.println(body);
		}
		return bundle;

	}
}

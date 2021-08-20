package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.globits.fhir.hiv.utils.HivConst;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import dto.HbvDto;
import dto.HcvDto;
import dto.ObjectDto;
import dto.PatientDto;
import dto.PregnancyDto;
import dto.RegimenPatientDto;
import dto.TBTreatmentDto;
import dto.TPTDto;

public class OpcEclinicaService {
	@Autowired
	DeathService deathService = new DeathService();
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

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueDate(String valueString) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		// answerVLTestDateOfTestPerformance.getValueStringType().setValue(data.getRapidVLTestDate());
		Date dateType = null;
		try {
			dateType = new SimpleDateFormat("yyyy-MM-dd").parse(valueString);
		} catch (ParseException e) {
			try {
				dateType = new SimpleDateFormat("yyyy").parse(valueString);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
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

	public List<QuestionnaireResponseItemAnswerComponent> createQRListItemAnswerComponentValueInteger(Integer value) {
		List<QuestionnaireResponseItemAnswerComponent> listItemAnswerComponent = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent itemAnswerComponent = new QuestionnaireResponseItemAnswerComponent();
		itemAnswerComponent.getValueIntegerType().setValue(value);
		listItemAnswerComponent.add(itemAnswerComponent);
		return listItemAnswerComponent;
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
	public QuestionnaireResponseItemComponent createQRItemCompenentValueDateTime(String linkId, String text,
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

	public QuestionnaireResponseItemComponent generatePatientInfomation(PatientDto data) {

		
		QuestionnaireResponseItemComponent patientInformation = createEmptyItemCompenent(
				HivConst.QRLinkIdPersonalInformation, HivConst.QRTextPersonalInformation);
//		QuestionnaireResponseItemComponent synOrg = createQRItemCompenentValueCoding(
//				HivConst.QRLinkIdSynchronizedOrganization, "Synchronized Organization",
//				HivConst.synOrg.OpcEclinica.getValue().toString(), HivConst.synOrg.OpcEclinica.name());
//		patientInformation.addItem(synOrg);
		QuestionnaireResponseItemComponent name = createEmptyItemCompenent(HivConst.QRLinkIdPatientName, "Name");
		if (data.getDisplayName() != null) {
			String fullNamePart = data.getDisplayName();
			String familyNamePart = "";
			String givenNamePart ="";
			String[] nameParts = fullNamePart.split(" ", 2);
			if(nameParts!=null && nameParts.length>0) {
				familyNamePart = nameParts[0];
				if(nameParts.length>1)
				givenNamePart = nameParts[1];
			}
				
			// familyName
			if (!familyNamePart.equals("")) {
				QuestionnaireResponseItemComponent familyName = createQRItemCompenentValueString(
						HivConst.QRLinkIdPatientFamilyName, "Family name", familyNamePart);
				name.addItem(familyName);
			}
			if (!givenNamePart.equals("")) {
				QuestionnaireResponseItemComponent givenName = createQRItemCompenentValueString(
						HivConst.QRLinkIdPatientGivenName, "Given name", givenNamePart);
				name.addItem(givenName);
			}
//		if (data.getMiddleName() != null) {
//			QuestionnaireResponseItemComponent familyName = createQRItemCompenentValueString(
//					HivConst.QRLinkIdPatientFamilyName, "Family name", data.getMiddleName());
//			name.addItem(familyName);
//		}else {
//			QuestionnaireResponseItemComponent familyName = createQRItemCompenentValueString(
//					HivConst.QRLinkIdPatientFamilyName, "Family name", familyNamePart);
//			name.addItem(familyName);
//		}
			// GivenName
//			if (data.getGivenName() != null) {
//			QuestionnaireResponseItemComponent givenName = createQRItemCompenentValueString(
//					HivConst.QRLinkIdPatientGivenName, "Given name", data.getGivenName());
//			name.addItem(givenName);
//			}else {
//				QuestionnaireResponseItemComponent givenName = createQRItemCompenentValueString(
//						HivConst.QRLinkIdPatientGivenName, "Given name", givenNamePart);
//				name.addItem(givenName);
//			}
			// FullName
			if (data.getDisplayName() != null) {
			QuestionnaireResponseItemComponent fullName = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientFullName, "Full name", data.getDisplayName());
			name.addItem(fullName);
			patientInformation.addItem(name);	
		}
		}
		
		if (data.getEthnic() != null) {
			QuestionnaireResponseItemComponent ethnicity = createQRItemCompenentValueCoding(HivConst.QRLinkIdEthnicity,
					"Ethnicity", data.getEthnic().toLowerCase(), data.getEthnic());
			patientInformation.addItem(ethnicity);
		}
		if (data.getGender() != null) {
			String genderText = "";
			
			if(data.getGender()!=null&&data.getGender().toLowerCase().equals("m")) {
				genderText = "Male";
			} else if (data.getGender()!=null&&data.getGender().toLowerCase().equals("f")) {
				genderText = "Female";
			} else if (data.getGender()!=null&&data.getGender().toLowerCase().equals("u")) {
				genderText = "Other";
			} else  {
				genderText = "Does not wish to disclose";
			}
			QuestionnaireResponseItemComponent gender = createQRItemCompenentValueCoding(HivConst.QRLinkIdGender,
					"Gender", genderText.toLowerCase(), genderText);
			patientInformation.addItem(gender);
		}
		if (data.getBirthdate() != null) {
			LocalDate localDate = data.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year  = localDate.getYear();
			String yearStr="";
			yearStr=String.valueOf(year);
			QuestionnaireResponseItemComponent birthYear = createQRItemCompenentValueDate(
					HivConst.QRLinkIdYearOfBirth, "Year of Birth",yearStr );
			patientInformation.addItem(birthYear);
		}
		
		
		if (data.getPatientCareer() != null) {
			QuestionnaireResponseItemComponent patientCareer = createQRItemCompenentValueCoding(HivConst.QRLinkIdOccupation,
					"Occupation", data.getPatientCareer().toLowerCase(), data.getPatientCareer());

			patientInformation.addItem(patientCareer);
		}
		QuestionnaireResponseItemComponent idCccd = createEmptyItemCompenent(HivConst.QRLinkIdIdentification,
				"Identification");

//		if (data.getPassportNumber() != null) {
//			// Passport
//			QuestionnaireResponseItemComponent passport_nr = createQRItemCompenentValueString(
//					HivConst.QRLinkIdPassportNumber, "Passport Number", data.getPassportNumber());
//			idCccd.addItem(passport_nr);
//		}
		if (data.getBplCardNo() != null) {
			// NationalId
//			if()
			QuestionnaireResponseItemComponent nationalIdRr = createQRItemCompenentValueString(
					HivConst.QRLinkIdNationalId9, "National Id 9 Number", data.getBplCardNo());
			idCccd.addItem(nationalIdRr);
		}
		if (data.getMedicalInsurance() != null) {
			// Health insurance code
			QuestionnaireResponseItemComponent insurance = createQRItemCompenentValueString(
					HivConst.QRLinkIdHealthInsuranceNumber, "Health insurance code", data.getMedicalInsurance());
			idCccd.addItem(insurance);
		}
//		if (data.getIdentifier() != null) {
//			// artID
//			QuestionnaireResponseItemComponent arvIdNr = createQRItemCompenentValueString(HivConst.QRLinkIdARVNumber,
//					"Patient ARV identification number", data.getIdentifier());
//			idCccd.addItem(arvIdNr);
//		}
		patientInformation.addItem(idCccd);

		QuestionnaireResponseItemComponent residence = new QuestionnaireResponseItemComponent();
		residence.setLinkId(HivConst.QRLinkIdResidence);
		residence.setText("Residence");
		QuestionnaireResponseItemComponent current = new QuestionnaireResponseItemComponent();
		current.setLinkId(HivConst.QRLinkIdCurrentResidence);
		current.setText("Current Residence");
		// currentStressAddress
		QuestionnaireResponseItemComponent currentStressAddress = createQRItemCompenentValueString(
				HivConst.QRLinkIdAddress, "Current Stress Address",
				data.getCurrentStressAddress());
		current.addItem(currentStressAddress);
		// currentCommune
		QuestionnaireResponseItemComponent currentCommune = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdCommune, HivConst.QRTextCommune, "",data.getCurrentCommune());
		current.addItem(currentCommune);
		// currentDistrict
		QuestionnaireResponseItemComponent currentDistrict = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict,"", data.getCurrentDistrict());
		current.addItem(currentDistrict);
		// currentProvince
		QuestionnaireResponseItemComponent currentProvince = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdProvince, HivConst.QRTextProvince, "",data.getCurrentProvince());
		current.addItem(currentProvince);
		// currentCountry
		QuestionnaireResponseItemComponent currentCountry = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdCountry, HivConst.QRTextCountry,"", data.getCurrentCountry());
		current.addItem(currentCountry);
		// currentText
		String currentTextT="";
		if(data.getCurrentStressAddress()!=null) {
			currentTextT=data.getCurrentStressAddress();
		}
		if(data.getCurrentCommune()!=null) {
			if(currentTextT!=null&& currentTextT.length()>0) {
				currentTextT=currentTextT+ ", " ;
			}
			currentTextT=currentTextT+data.getCurrentCommune();
		}
		if(data.getCurrentDistrict()!=null) {
			if(currentTextT!=null&& currentTextT.length()>0) {
				currentTextT=currentTextT+ ", " ;
			}
			currentTextT=currentTextT+data.getCurrentDistrict();
		}
		if(data.getCurrentProvince()!=null) {
			if(currentTextT!=null&& currentTextT.length()>0) {
				currentTextT=currentTextT+ ", " ;
			}
			currentTextT=currentTextT+data.getCurrentProvince();
		}
		if(data.getCurrentCountry()!=null) {
			if(currentTextT!=null&& currentTextT.length()>0) {
				currentTextT=currentTextT+ ", " ;
			}
			currentTextT=currentTextT+data.getCurrentCountry();
		}
//		QuestionnaireResponseItemComponent currentText = createQRItemCompenentValueString(
//				HivConst.QRLinkIdCurrentResidenceText, "Current Text",currentTextT);
//		current.addItem(currentText);

		residence.addItem(current);

		QuestionnaireResponseItemComponent permanent = new QuestionnaireResponseItemComponent();
		permanent.setLinkId(HivConst.QRLinkIdPermanentResidence);
		permanent.setText("Permanent residence");
		// permanentStressAddress
		QuestionnaireResponseItemComponent permanentStressAddress = createQRItemCompenentValueString(
				HivConst.QRLinkIdAddress, "Permanent Stress Address",
				data.getPermanentStressAddress());
		permanent.addItem(permanentStressAddress);
		// permanentCommune
		QuestionnaireResponseItemComponent permanentCommune = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdCommune, HivConst.QRTextCommune,"", data.getPermanentCommune());
		permanent.addItem(permanentCommune);
		// PermanentDistrict
		QuestionnaireResponseItemComponent permanentDistrict = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdDistrict, HivConst.QRTextDistrict,"" ,data.getPermanentDistrict());
		permanent.addItem(permanentDistrict);
		// PermanentProvince
		QuestionnaireResponseItemComponent permanentProvince = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdCommune, HivConst.QRTextCommune,"" ,data.getPermanentProvince());
		permanent.addItem(permanentProvince);
		// PermanentCountry
		QuestionnaireResponseItemComponent permanentCountry = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdCountry, HivConst.QRTextCountry,"", data.getPermanentCountry());
		permanent.addItem(permanentCountry);
		// PermanentText
		String PermanentTextT="";
		if(data.getPermanentStressAddress()!=null) {
			PermanentTextT=data.getPermanentStressAddress();
		}
		if(data.getPermanentCommune()!=null) {
			if(PermanentTextT!=null&& PermanentTextT.length()>0) {
				PermanentTextT=PermanentTextT+ ", " ;
			}
			PermanentTextT=PermanentTextT+data.getPermanentCommune();
		}
		if(data.getPermanentDistrict()!=null) {
			if(PermanentTextT!=null&& PermanentTextT.length()>0) {
				PermanentTextT=PermanentTextT+ ", " ;
			}
			PermanentTextT=PermanentTextT+data.getPermanentDistrict();
		}
		if(data.getPermanentProvince()!=null) {
			if(PermanentTextT!=null&& PermanentTextT.length()>0) {
				PermanentTextT=PermanentTextT+ ", " ;
			}
			PermanentTextT=PermanentTextT+data.getPermanentProvince();
		}
		if(data.getPermanentCountry()!=null) {
			if(PermanentTextT!=null&& PermanentTextT.length()>0) {
				PermanentTextT=PermanentTextT+ ", " ;
			}
			PermanentTextT=PermanentTextT+data.getPermanentCountry();
		}
//		QuestionnaireResponseItemComponent permanentText = createQRItemCompenentValueString(
//				HivConst.QRLinkIdPermanentResidenceText, "Permanent Text",PermanentTextT);
//		permanent.addItem(permanentText);

		residence.addItem(permanent);
		patientInformation.addItem(residence);
		return patientInformation;

	}
	
	public QuestionnaireResponseItemComponent generateRiskFactors(PatientDto data) {
		QuestionnaireResponseItemComponent riskFactors = createEmptyItemCompenent(HivConst.QRLinkIdRiskFactor,
				"Risk Factors");

//		if (data.getListRiskPopulation() != null && data.getListRiskPopulation().size() > 0) {
//			QuestionnaireResponseItemComponent riskPopulation = new QuestionnaireResponseItemComponent();
//			riskPopulation.setLinkId(HivConst.QRLinkIdRiskPopulation);
//			riskPopulation.setText("Risk Population");
//			List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskPopulation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
//
//			for (HivRiskFactorDto item : data.getListRiskPopulation()) {
//				QuestionnaireResponseItemAnswerComponent answerRiskPopulation = new QuestionnaireResponseItemAnswerComponent();
//				answerRiskPopulation.getValueCoding().setCode(item.getCode());
//				answerRiskPopulation.getValueCoding().setDisplay(item.getDisplay());
//				listAnswerRiskPopulation.add(answerRiskPopulation);
//			}
//			riskPopulation.setAnswer(listAnswerRiskPopulation);
//			riskFactors.addItem(riskPopulation);
//
//		}
		if(data.getTransmission()!=null) {
			QuestionnaireResponseItemComponent transmission_route = new QuestionnaireResponseItemComponent();
			transmission_route.setLinkId(HivConst.QRLinkIdTransmissionRoute);
			transmission_route.setText("Transmission Route");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTranmission = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTranmission = new QuestionnaireResponseItemAnswerComponent();
			answerTranmission.getValueCoding().setCode(data.getTransmission().trim());
			answerTranmission.getValueCoding().setDisplay(data.getTransmission());
			listAnswerTranmission.add(answerTranmission);
			transmission_route.setAnswer(listAnswerTranmission);
			riskFactors.addItem(transmission_route);
		}
		return riskFactors;
	}

	public QuestionnaireResponseItemComponent generateHivDiagnosis(PatientDto data) {
		QuestionnaireResponseItemComponent hivDiagnosis = createEmptyItemCompenent(HivConst.QRLinkIdHivDiagnosis,
				"HIV Diagnosis");

		// Date Of Confirmation
		if (data.getHivInfectionDate() != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = dateFormat.format(data.getHivInfectionDate());
			QuestionnaireResponseItemComponent dateOfConfirmation = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfConfirmation, "Date of Confirmation",
					strDate);

			hivDiagnosis.addItem(dateOfConfirmation);
		}
		// Confirming Lab
		if (data.getHivAssertionLocation() != null) {
			QuestionnaireResponseItemComponent confirmingLab = createQRItemCompenentValueString(
					HivConst.QRLinkIdConfirmingLab, "Confirming Lab", data.getHivAssertionLocation());
			hivDiagnosis.addItem(confirmingLab);

		}
		return hivDiagnosis;
	}

	public QuestionnaireResponseItemComponent generateHivRecencyTest(PatientDto data) {
		QuestionnaireResponseItemComponent hivRecencyTest = new QuestionnaireResponseItemComponent();
		hivRecencyTest.setLinkId(HivConst.QRLinkIdHivRecencyTest);
		hivRecencyTest.setText("HIV Recency Test");
		// Rapid VL test
//		if (data.getListRecency() != null && data.getListRecency().size() > 0) {
//			for (HivRecencyDto item : data.getListRecency()) {
//				QuestionnaireResponseItemComponent vlTest = new QuestionnaireResponseItemComponent();
//				vlTest.setLinkId(HivConst.QRLinkIdRecencyVlTest);
//				vlTest.setText("Rapid VL test");
//				if (item.getDateOfTestPerformance() != null) {
//					QuestionnaireResponseItemComponent vlTestDateOfTestPerformance = createQRItemCompenentValueDate(
//							HivConst.QRLinkIdDateOfTestPerformance, "Date of rapid test performance",
//							item.getDateOfTestPerformance());
//					vlTest.addItem(vlTestDateOfTestPerformance);
//				}
//				if (item.getTestResult() != null) {
//					QuestionnaireResponseItemComponent vlRecencyTestResult = createQRItemCompenentValueString(
//							HivConst.QRLinkIdVlRecencyTestResult, "VL recency test result", item.getTestResult());
//
//					vlTest.addItem(vlRecencyTestResult);
//				}
//				hivRecencyTest.addItem(vlTest);
//			}
//
//		}
		return hivRecencyTest;
	}

	public QuestionnaireResponseItemComponent generateCd4BeforeArt(ObjectDto item) {
		QuestionnaireResponseItemComponent cd4BeforeART = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cd4BeforeART.setLinkId(HivConst.QRLinkIdCd4Test);
		cd4BeforeART.setText("CD4 test ART");

		
		/////
		QuestionnaireResponseItemComponent testReason = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdLastestTestReason, "Reason for present CD4 test", "before_art",
				"Before ART");
		cd4BeforeART.addItem(testReason);
		//
		if (item.getSampleDate() != null) {
			
			String strDate = dateFormat.format(item.getSampleDate());
			QuestionnaireResponseItemComponent cd4BeforeARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for CD4 test before ART",
					strDate);

			cd4BeforeART.addItem(cd4BeforeARTDateOfSpecimenCollection);
		}
		if (item.getResultDate() != null) {
			String strDate = dateFormat.format(item.getResultDate());
			QuestionnaireResponseItemComponent cd4BeforeARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of CD4 test before ART performance",
					strDate);
			cd4BeforeART.addItem(cd4BeforeARTDateOfTestPerformance);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent cd4BeforeARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for CD4 test before ART",
					item.getPlace());
			cd4BeforeART.addItem(cd4BeforeARTPlaceOfSpecimenCollection);
		}
		if (item.getResult() != null) {
			QuestionnaireResponseItemComponent cd4BeforeARTTestResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "CD4 test before ART - result", item.getResult().intValue());
			cd4BeforeART.addItem(cd4BeforeARTTestResult);
		}
		return cd4BeforeART;
	}

	public QuestionnaireResponseItemComponent generateCd4DuringArt(ObjectDto item) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		QuestionnaireResponseItemComponent cd4DuringART = new QuestionnaireResponseItemComponent();
		cd4DuringART.setLinkId(HivConst.QRLinkIdCd4Test);
		cd4DuringART.setText("CD4 test ART");
		
			/////
		QuestionnaireResponseItemComponent testReason = createQRItemCompenentValueCoding(
				HivConst.QRLinkIdLastestTestReason, "Reason for present CD4 test", "routine_test",
				"Routine test");
		cd4DuringART.addItem(testReason);

			//
		
		if (item.getSampleDate() != null) {
			String strDate = dateFormat.format(item.getSampleDate());
			QuestionnaireResponseItemComponent cd4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for CD4 test during ART",
					strDate);
			cd4DuringART.addItem(cd4DuringARTDateOfSpecimenCollection);
		}
		if (item.getResultDate() != null) {
			String strDate = dateFormat.format(item.getResultDate());
			QuestionnaireResponseItemComponent cd4DuringARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of CD4 test during ART performance",
					strDate);
			cd4DuringART.addItem(cd4DuringARTDateOfTestPerformance);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent cd4DuringARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for CD4 test during ART",
					item.getPlace());
			cd4DuringART.addItem(cd4DuringARTPlaceOfSpecimenCollection);
		}
		if (item.getResult() != null) {
			QuestionnaireResponseItemComponent cd4DuringARTTestResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "CD4 test during ART - result", item.getResult().intValue());

			cd4DuringART.addItem(cd4DuringARTTestResult);
		}
		return cd4DuringART;
	}

	public QuestionnaireResponseItemComponent generateVlDuringArt(ObjectDto item) {
		QuestionnaireResponseItemComponent vl4DuringART = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vl4DuringART.setLinkId(HivConst.QRLinkIdVl4DuringArt);
		vl4DuringART.setText("VL test during ART");
		if (item.getSampleDate() != null) {
			String strDate = dateFormat.format(item.getSampleDate());
			QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for VL test during ART",
					strDate);
			vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);
		}
		if (item.getResultDate() != null) {
			String strDate = dateFormat.format(item.getResultDate());
			QuestionnaireResponseItemComponent vl4DuringARTDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of VL test during ART performance",
					strDate);
			vl4DuringART.addItem(vl4DuringARTDateOfTestPerformance);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for VL test during ART",
					item.getPlace());
			vl4DuringART.addItem(vl4DuringARTPlaceOfSpecimenCollection);
		}
		if (item.getResult() != null) {
			QuestionnaireResponseItemComponent vl4DuringARTTestResult = createQRItemCompenentValueInteger(
					HivConst.QRLinkIdTestResult, "VL test during ART - result", item.getResult().intValue());
			vl4DuringART.addItem(vl4DuringARTTestResult);
		}
		return vl4DuringART;
	}

	public QuestionnaireResponseItemComponent generateDrugResistance(ObjectDto item) {
		QuestionnaireResponseItemComponent drugResistanceTest = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		drugResistanceTest.setLinkId(HivConst.QRLinkIdDrugResistanceTest);
		drugResistanceTest.setText("Drug Resistance test");
		if (item.getSampleDate() != null) {
			String strDate = dateFormat.format(item.getSampleDate());
			QuestionnaireResponseItemComponent drugResistanceTestDateOfSpecimenCollection = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateSpecimen, "Date of Specimen Collection for Drug Resistance test",
					strDate);

			drugResistanceTest.addItem(drugResistanceTestDateOfSpecimenCollection);
		}
		if (item.getResultDate() != null) {
			String strDate = dateFormat.format(item.getResultDate());
			QuestionnaireResponseItemComponent drugResistanceTestDateOfTestPerformance = createQRItemCompenentValueDate(
					HivConst.QRLinkIdLastestDateTestPerformed, "Date of Drug Resistance test",strDate);
			drugResistanceTest.addItem(drugResistanceTestDateOfTestPerformance);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestPlaceOfSpecimenCollection = createQRItemCompenentValueString(
					HivConst.QRLinkIdLastestPlaceSpecimen, "Place of Specimen Collection for Drug Resistance test",
					item.getPlace());

			drugResistanceTest.addItem(drugResistanceTestPlaceOfSpecimenCollection);
		}
		if (item.getValueText() != null) {
			QuestionnaireResponseItemComponent drugResistanceTestTestResult = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdTestResultOther, "Drug Resistance test result", item.getValueText(),item.getValueText());

			drugResistanceTest.addItem(drugResistanceTestTestResult);
		}
		return drugResistanceTest;
	}

	public QuestionnaireResponseItemComponent generateArvTreatment(RegimenPatientDto item) {
		QuestionnaireResponseItemComponent arvTreatment = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		arvTreatment.setLinkId(HivConst.QRLinkIdArvTreatment);
		arvTreatment.setText("ARV Treatment");
		
		if (item.getStartDate() != null) {
			String strDate = dateFormat.format(item.getStartDate());
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStart = createQRItemCompenentValueDate(
					HivConst.QRLinkIdInitiationDate, HivConst.QRTextInitiationDate, strDate);

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStart);
		}
		if (item.getEndDate() != null) {
			
			String strDate = dateFormat.format(item.getEndDate());
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStop = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateOfTreatmentStop, "Date of treatment stop", strDate);

			arvTreatment.addItem(arvTreatmentDateOfTreatmentStop);
		}
		if (item.getPlace() != null) {			
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = createQRItemCompenentValueString(
					HivConst.QRLinkIdArvTreatmentInitiationFacility, HivConst.QRTextArvTreatmentInitiationFacility,
					item.getPlace());
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		}
		QuestionnaireResponseItemComponent regimen = new QuestionnaireResponseItemComponent();
		regimen.setLinkId(HivConst.QRLinkIdRegimen);
		regimen.setText("ARV treatment regimen");
		String strDate = dateFormat.format(item.getStartDate());
		if (item.getArvRegimenLine() == 1) {
			QuestionnaireResponseItemComponent regimen1stLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate1stLineStarted, "Date 1st ARV regimen started",
					strDate);

			regimen.addItem(regimen1stLine);
		}
		if (item.getArvRegimenLine() == 2) {
			QuestionnaireResponseItemComponent regimen2ndLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate2ndLineStarted, "Date 2nd ARV regimen started",
					strDate);

			regimen.addItem(regimen2ndLine);
		}
		if (item.getArvRegimenLine() == 3) {
			QuestionnaireResponseItemComponent regimen3rdLine = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDate3rdLineStarted, "Date 3rd ARV regimen started",
					strDate);

			regimen.addItem(regimen3rdLine);
		}
		arvTreatment.addItem(regimen);
		return arvTreatment;
	}

	public QuestionnaireResponseItemComponent generateComorbidities(PatientDto data) {
		QuestionnaireResponseItemComponent comorbidities = createEmptyItemCompenent(HivConst.QRLinkIdComorbidities,
				"Comorbidities");
//		Tuberculosis
		QuestionnaireResponseItemComponent tuberculosis = createEmptyItemCompenent(HivConst.QRLinkIdTuberculosis,
				"Tuberculosis");
//		Tuberculosis.TPT
		if (data.getListTPT() != null && data.getListTPT().size() > 0) {
			for (TPTDto item : data.getListTPT()) {
				QuestionnaireResponseItemComponent tpt = generateTPT(item);
				tuberculosis.addItem(tpt);
			}
		}

		// Comorbidities.Tuberculosis.TB Treatment
		if (data.getListTBTreatment() != null && data.getListTBTreatment().size() > 0) {
			for (TBTreatmentDto item : data.getListTBTreatment()) {
				QuestionnaireResponseItemComponent tbTreatment = generateTbTreatment(item);
				tuberculosis.addItem(tbTreatment);
			}
		}
		comorbidities.addItem(tuberculosis);

//		HBV-HCV
		QuestionnaireResponseItemComponent hbvHcv = createEmptyItemCompenent(HivConst.QRLinkIdHbvHcv, "HBV and HCV");
		// HBV
		if (data.getListHBV() != null && data.getListHBV().size() > 0) {
			for (HbvDto item : data.getListHBV()) {
				QuestionnaireResponseItemComponent hbvTreatment = generateHbvTreatment(item);
				hbvHcv.addItem(hbvTreatment);
			}
		}
		// HCV
		if (data.getListHCV() != null && data.getListHCV().size() > 0) {
			for (HcvDto item : data.getListHCV()) {
				QuestionnaireResponseItemComponent hcvTreatment = generateHcvTreatment(item);
				hbvHcv.addItem(hcvTreatment);

			}
		}
		comorbidities.addItem(hbvHcv);
		return comorbidities;
	}

	public QuestionnaireResponseItemComponent generateTPT(TPTDto item) {
		QuestionnaireResponseItemComponent tpt = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tpt.setLinkId(HivConst.QRLinkIdTpt);
		tpt.setText("TPT");
		if (item.getStartDate() != null) {
			String strDate = dateFormat.format(item.getStartDate());
			QuestionnaireResponseItemComponent tptDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TPT started", strDate);
			tpt.addItem(tptDateStarted);
		}
		if (item.getCompletedDate() != null) {
			String strDate = dateFormat.format(item.getCompletedDate());
			QuestionnaireResponseItemComponent tptDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TPT completed", strDate);
			tpt.addItem(tptDateCompleted);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent tptPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TPT provided", item.getPlace());

			tpt.addItem(tptPlaceProvided);
		}
		return tpt;
	}

	public QuestionnaireResponseItemComponent generateTbTreatment(TBTreatmentDto item) {
		QuestionnaireResponseItemComponent tbTreatment = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tbTreatment.setLinkId(HivConst.QRLinkIdTbTreatment);
		tbTreatment.setText("TB Treatment");
//		if (item.getTbDiagnoseDate() != null) {
//			QuestionnaireResponseItemComponent tbDiagnosisDate = createQRItemCompenentValueDate(
//					HivConst.QRLinkIdTbDiagnosisDate, "TB Diagnosis Date", item.getTbDiagnoseDate());
//			tbTreatment.addItem(tbDiagnosisDate);
//		}
		if (item.getStartDate() != null) {
			String strDate = dateFormat.format(item.getStartDate());
			QuestionnaireResponseItemComponent tbTreatmentDateStarted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date TB Treatment started", strDate);

			tbTreatment.addItem(tbTreatmentDateStarted);
		}
		if (item.getCompletedDate() != null) {
			String strDate = dateFormat.format(item.getCompletedDate());
			QuestionnaireResponseItemComponent tbTreatmentDateCompleted = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date TB Treatment completed", strDate);
			tbTreatment.addItem(tbTreatmentDateCompleted);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent tbTreamentplaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place TB Treatment provided", item.getPlace());
			tbTreatment.addItem(tbTreamentplaceProvided);
		}
		return tbTreatment;
	}

	public QuestionnaireResponseItemComponent generateHbvTreatment(HbvDto item) {
		QuestionnaireResponseItemComponent hbvTreatment = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hbvTreatment.setLinkId(HivConst.QRLinkIdHbv);
		hbvTreatment.setText("HBV");
		if (item.getDiagnosisDate() != null) {
			String strDate = dateFormat.format(item.getDiagnosisDate());
			QuestionnaireResponseItemComponent hbvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HBV diagnosis",strDate);
			hbvTreatment.addItem(hbvDiagnosisDate);
		}
		if (item.getTreatmentStartDate() != null) {
			String strDate = dateFormat.format(item.getTreatmentStartDate());
			QuestionnaireResponseItemComponent hbvStartDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateStarted, "Date of HBV treatment start", strDate);
			hbvTreatment.addItem(hbvStartDate);
		}
		if (item.getTreatmentEndDate() != null) {
			String strDate = dateFormat.format(item.getTreatmentEndDate());
			QuestionnaireResponseItemComponent hbvEndDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDateCompleted, "Date of HBV treatment end", strDate);

			hbvTreatment.addItem(hbvEndDate);
		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent hbvPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place HBV treatment provided", item.getPlace());
			hbvTreatment.addItem(hbvPlaceProvided);
		}
		return hbvTreatment;
	}

	public QuestionnaireResponseItemComponent generateHcvTreatment(HcvDto item) {
		QuestionnaireResponseItemComponent hcvTreatment = new QuestionnaireResponseItemComponent();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hcvTreatment.setLinkId(HivConst.QRLinkIdHcv);
		hcvTreatment.setText("HCV");
		if (item.getDiagnosisDate() != null) {
			
			String strDate = dateFormat.format(item.getDiagnosisDate());
			QuestionnaireResponseItemComponent hcvDiagnosisDate = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDiagnosisDate, "Date of HCV diagnosis", strDate);
			hcvTreatment.addItem(hcvDiagnosisDate);
		}
//		if (item.getTreatmentStartDate() != null) {
//			QuestionnaireResponseItemComponent hcvStartDate = createQRItemCompenentValueDate(
//					HivConst.QRLinkIdDateStarted, "Date of HCV treatment start", item.getTreatmentStartDate());
//			hcvTreatment.addItem(hcvStartDate);
//		}
//		if (item.getTreatmentEndDate() != null) {
//			QuestionnaireResponseItemComponent hcvEndDate = createQRItemCompenentValueDate(
//					HivConst.QRLinkIdDateCompleted, "Date of HCV treatment end", item.getTreatmentEndDate());
//			hcvTreatment.addItem(hcvEndDate);
//		}
		if (item.getPlace() != null) {
			QuestionnaireResponseItemComponent hcvPlaceProvided = createQRItemCompenentValueString(
					HivConst.QRLinkIdPlaceProvided, "Place HCV treatment provided", item.getPlace());
			hcvTreatment.addItem(hcvPlaceProvided);
		}
		return hcvTreatment;
	}

	public QuestionnaireResponseItemComponent generatePregnancy(PregnancyDto item) {
		QuestionnaireResponseItemComponent pregnancy = createEmptyItemCompenent(HivConst.QRLinkIdPregnancies,
				"Pregnancies");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (item.getDatePregnancyReported() != null) {
			
			String strDate = dateFormat.format(item.getDatePregnancyReported());
			QuestionnaireResponseItemComponent datePregnancyReported = createQRItemCompenentValueDate(
					HivConst.QRLinkIdDatePregnancyReported, "Date pregnancy reported", strDate);
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
//		QuestionnaireResponseItemComponent childHivStatus = createQRItemCompenentValueString(HivConst.QRLinkIdHivStatus,
//				"HIV status", item.getChildHivStatus() + "");
//		pregnancy.addItem(childHivStatus);

		// Birth Weight
//		QuestionnaireResponseItemComponent childBirthWeight = createQRItemCompenentValueString(
//				HivConst.QRLinkIdChildBirthWeight, "Weight at birth (kg)", item.getBirthWeight() + "");
//		pregnancy.addItem(childBirthWeight);
		return pregnancy;
	}

	public Bundle convertListOpcObjectToBundle(List<PatientDto> list) {
		Bundle bundle = new Bundle();
		for (PatientDto data : list) {
			if(data==null) {
				continue;
			}
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
			//organization_unit_name
			QuestionnaireResponseItemComponent synOrg = createQRItemCompenentValueCoding(
					HivConst.QRLinkIdOrganizationUnitName, "Organization Unit Name",
					HivConst.synOrg.OpcEclinica.getValue().toString(), HivConst.synOrg.OpcEclinica.name());
			listItem.add(synOrg);
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
			// questions
			QuestionnaireResponseItemComponent questions = new QuestionnaireResponseItemComponent();
			questions.setLinkId(HivConst.QRLinkIdQuestions);
			questions.setText("questions");
			// Patient Identification
			QuestionnaireResponseItemComponent patientIdentification = new QuestionnaireResponseItemComponent();
			patientIdentification.setLinkId(HivConst.QRLinkIdPatientIdentification);
			patientIdentification.setText("Patient Identification");
			//Patient Arv Number
			if (data.getIdentifier() != null) {
			QuestionnaireResponseItemComponent patientArvNumber = createQRItemCompenentValueString(
					HivConst.QRLinkIdPatientArvNumber, "Patient Arv Number",
					data.getIdentifier());
			patientIdentification.addItem(patientArvNumber);
			}
			//case uid
//			QuestionnaireResponseItemComponent caseUid = createQRItemCompenentValueString(
//					HivConst.QRLinkIdCaseUid, "Case Uid",
//					data.getIdentifier());
//			patientIdentification.addItem(patientArvNumber);
			
			// Patient Information
			QuestionnaireResponseItemComponent patientInformation = generatePatientInfomation(data);
			
//			questions.addItem();
			patientIdentification.addItem(patientInformation);
			questions.addItem(patientIdentification);
			// Risk factors
			QuestionnaireResponseItemComponent riskFactors = generateRiskFactors(data);
			questions.addItem(riskFactors);
			// HIV Diagnosis
			QuestionnaireResponseItemComponent hivDiagnosis = generateHivDiagnosis(data);
			questions.addItem(hivDiagnosis);
			// Recency Test
			QuestionnaireResponseItemComponent hivRecencyTest = generateHivRecencyTest(data);
			questions.addItem(hivRecencyTest);
			// CD4 Before ART
			if (data.getListCD4BeforeARV() != null && data.getListCD4BeforeARV().size() > 0) {
				for (ObjectDto item : data.getListCD4BeforeARV()) {
					QuestionnaireResponseItemComponent cd4BeforeART = generateCd4BeforeArt(item);
					questions.addItem(cd4BeforeART);
				}
			}
			// CD4 During ART
			if (data.getListCD4DuringARV() != null && data.getListCD4DuringARV().size() > 0) {
				for (ObjectDto item : data.getListCD4DuringARV()) {
					QuestionnaireResponseItemComponent cd4DuringART = generateCd4DuringArt(item);
					questions.addItem(cd4DuringART);
				}
			}
			// VL test During ART
			if (data.getListVlResultDuringARV() != null && data.getListVlResultDuringARV().size() > 0) {
				for (ObjectDto item : data.getListVlResultDuringARV()) {
					QuestionnaireResponseItemComponent vl4DuringART = generateVlDuringArt(item);
					questions.addItem(vl4DuringART);
				}
			}
			// Drug Resistance Test
			if (data.getListDrugResistance() != null && data.getListDrugResistance().size() > 0) {
				for (ObjectDto item : data.getListDrugResistance()) {
					QuestionnaireResponseItemComponent drugResistanceTest = generateDrugResistance(item);
					questions.addItem(drugResistanceTest);
				}
			}
			// ARV Treatment
			if (data.getListRegimenHistory() != null && data.getListRegimenHistory().size() > 0) {
				for (RegimenPatientDto item : data.getListRegimenHistory()) {
					QuestionnaireResponseItemComponent arvTreatment = generateArvTreatment(item);
					questions.addItem(arvTreatment);
				}
			}
			// Comorbidities
			QuestionnaireResponseItemComponent comorbidities = generateComorbidities(data);
			questions.addItem(comorbidities);
			// Pregnancy
			if (data.getListPregnancy() != null && data.getListPregnancy().size() > 0) {
				for (PregnancyDto item : data.getListPregnancy()) {
					QuestionnaireResponseItemComponent pregnancy = generatePregnancy(item);
					questions.addItem(pregnancy);
				}
			}
			// death
			QuestionnaireResponseItemComponent death = deathService.convertDataToDeath(data);
			if (death != null && death.getItem() != null && death.getItem().size() > 0) {
				questions.addItem(death);
			}
			listItem.add(questions);
			questionnaireResponse.setItem(listItem);
			bundle.setType(Bundle.BundleType.COLLECTION);

			bundle.addEntry().setFullUrl(questionnaireResponse.getIdElement().getValue())
					.setResource(questionnaireResponse);

//			String body = parser.encodeResourceToString(bundle);
//			System.out.println(body);
		}
		return bundle;

	}
}

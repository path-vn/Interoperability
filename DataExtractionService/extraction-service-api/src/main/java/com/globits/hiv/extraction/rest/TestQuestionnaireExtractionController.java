package com.globits.hiv.extraction.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.hiv.vietnam.r4.model.HivPatient;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemAnswerComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseItemComponent;
import org.hl7.fhir.r4.model.QuestionnaireResponse.QuestionnaireResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.HivConst;
import com.globits.hiv.extraction.dto.PatientInfoDto;
import com.globits.hiv.extraction.service.impl.QRExtractionServiceImpl;
import com.globits.hiv.extraction.utils.QRConvertUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@RestController
@RequestMapping("/public/extraction")
public class TestQuestionnaireExtractionController {
	@RequestMapping(path ="/single",method = RequestMethod.POST)
	public void postSingleQR() {
		testSendQRFull();
		
	}
	public static void testSendQRFull() {
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		Bundle bundle = new Bundle();
		QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
		//Meta
		Meta meta = new Meta();
		CanonicalType canonicalType = new CanonicalType();
		canonicalType.setValue("http://hl7.org/fhir/uv/sdc/StructureDefinition/sdc-questionnaireresponse|2.7");
		List<CanonicalType> listCanonicalType = new ArrayList<CanonicalType>();
		listCanonicalType.add(canonicalType);
		//Meta--Profile
		meta.setProfile(listCanonicalType);
		List<Coding> listCoding = new ArrayList<Coding>();
		Coding coding = new Coding();
		coding.setCode("lformsVersion: 28.1.1");
		listCoding.add(coding);
		//Meta--Tag
		meta.setTag(listCoding);
		questionnaireResponse.setMeta(meta);
		//Status
		questionnaireResponse.setStatus(QuestionnaireResponseStatus.COMPLETED);
		//Authored
		questionnaireResponse.setAuthored(new Date());
		List<QuestionnaireResponseItemComponent> listItem = new ArrayList<QuestionnaireResponseItemComponent>();
		//Governing unit name
		QuestionnaireResponseItemComponent unitName = new QuestionnaireResponseItemComponent();
		unitName.setLinkId(HivConst.QRLinkIdUnitName);
		unitName.setText("Governing unit name");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerUnitName = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerUnitName = new QuestionnaireResponseItemAnswerComponent();
		answerUnitName.getValueStringType().setValue("Việt Nam");
		listAnswerUnitName.add(answerUnitName);
		unitName.setAnswer(listAnswerUnitName);
		listItem.add(unitName);
		//Date of Report
		QuestionnaireResponseItemComponent reportDate = new QuestionnaireResponseItemComponent();
		reportDate.setLinkId("reportDate");
		reportDate.setText("Date of Report");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerReportDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerReportDate = new QuestionnaireResponseItemAnswerComponent();
		answerReportDate.getValueDateType().setValue(new Date());
		listAnswerReportDate.add(answerReportDate);
		reportDate.setAnswer(listAnswerReportDate);
		listItem.add(reportDate);
		QuestionnaireResponseItemComponent patientInformation = new QuestionnaireResponseItemComponent();
		patientInformation.setLinkId(HivConst.QRLinkIdPersonalInformation);
		patientInformation.setText("Patient Information");
		//Resource Name
		QuestionnaireResponseItemComponent governingUnitName = new QuestionnaireResponseItemComponent();
		governingUnitName.setLinkId(HivConst.QRLinkIdUnitName);
		governingUnitName.setText("Governing unit name");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerGoverningUnitName = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerGoverningUnitName = new QuestionnaireResponseItemAnswerComponent();
		answerGoverningUnitName.getValueCoding().setCode("0");
		answerGoverningUnitName.getValueCoding().setDisplay("Sample test");
		listAnswerGoverningUnitName.add(answerGoverningUnitName);
		governingUnitName.setAnswer(listAnswerGoverningUnitName);
		patientInformation.addItem(governingUnitName);
		
		
		String fullNamePart = "Nguyễn Văn Mèo";
		String[] nameParts = fullNamePart.split(" ", 2);
		String familyNamePart = nameParts[0];
		String givenNamePart = nameParts[1];
		
		QuestionnaireResponseItemComponent name = new QuestionnaireResponseItemComponent();
		name.setLinkId(HivConst.QRLinkIdPatientName);
		name.setText("Name");
		//familyName
		QuestionnaireResponseItemComponent familyName = new QuestionnaireResponseItemComponent();
		familyName.setLinkId(HivConst.QRLinkIdPatientFamilyName);
		familyName.setText("Family name");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerFamilyName = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerFamilyName = new QuestionnaireResponseItemAnswerComponent();
		answerFamilyName.getValueStringType().setValue(familyNamePart);
		listAnswerFamilyName.add(answerFamilyName);
		familyName.setAnswer(listAnswerFamilyName);
		//givenName
		QuestionnaireResponseItemComponent givenName = new QuestionnaireResponseItemComponent();
		givenName.setLinkId(HivConst.QRLinkIdPatientGivenName);
		givenName.setText("Given name");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerGivenName = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerGivenName = new QuestionnaireResponseItemAnswerComponent();
		answerGivenName.getValueStringType().setValue(givenNamePart);
		listAnswerGivenName.add(answerGivenName);
		givenName.setAnswer(listAnswerGivenName);
		
		//fullName
		QuestionnaireResponseItemComponent fullName = new QuestionnaireResponseItemComponent();
		fullName.setLinkId(HivConst.QRLinkIdPatientFullName);
		fullName.setText("Full name");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerFullName = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerFullName = new QuestionnaireResponseItemAnswerComponent();
		answerFullName.getValueStringType().setValue(fullNamePart);
		listAnswerFullName.add(answerFullName);
		fullName.setAnswer(listAnswerFullName);
		
		name.addItem(familyName);
		name.addItem(givenName);
		name.addItem(fullName);
		patientInformation.addItem(name);
		
		QuestionnaireResponseItemComponent ethnicity = new QuestionnaireResponseItemComponent();
		ethnicity.setLinkId(HivConst.QRLinkIdEthnicity);
		ethnicity.setText("Ethnicity");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerEthnicity = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerEthnicity = new QuestionnaireResponseItemAnswerComponent();
		answerEthnicity.getValueCoding().setCode("1");
		listAnswerEthnicity.add(answerEthnicity);
		ethnicity.setAnswer(listAnswerEthnicity);
		patientInformation.addItem(ethnicity);
		
		QuestionnaireResponseItemComponent gender = new QuestionnaireResponseItemComponent();
		gender.setLinkId(HivConst.QRLinkIdGender);
		gender.setText("Gender");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerGender = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerGender = new QuestionnaireResponseItemAnswerComponent();
		answerGender.getValueCoding().setCode("male");
		answerGender.getValueCoding().setDisplay("Male");
		listAnswerGender.add(answerGender);
		gender.setAnswer(listAnswerGender);
		patientInformation.addItem(gender);
		
		
		QuestionnaireResponseItemComponent birthYear = new QuestionnaireResponseItemComponent();
		birthYear.setLinkId(HivConst.QRLinkIdYearOfBirth);
		birthYear.setText("Year of Birth");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerBirthYear = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerBirthYear = new QuestionnaireResponseItemAnswerComponent();
		answerBirthYear.getValueStringType().setValue("1992");
		//answerBirthYear.getValueIntegerType().setValue(1998);
		listAnswerBirthYear.add(answerBirthYear);
		birthYear.setAnswer(listAnswerBirthYear);
		patientInformation.addItem(birthYear);
		
		
		QuestionnaireResponseItemComponent idCccd = new QuestionnaireResponseItemComponent();
		idCccd.setLinkId(HivConst.QRLinkIdIdentification);
		idCccd.setText("Identification");
		QuestionnaireResponseItemComponent passport_nr = new QuestionnaireResponseItemComponent();
		passport_nr.setLinkId(HivConst.QRLinkIdPassportNumber);
		passport_nr.setText("Passport Number");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPassportNr = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPassportNr = new QuestionnaireResponseItemAnswerComponent();
		answerPassportNr.getValueStringType().setValue("123456789a6Lan4");
		listAnswerPassportNr.add(answerPassportNr);
		passport_nr.setAnswer(listAnswerPassportNr);
		
		QuestionnaireResponseItemComponent nationalIdRr = new QuestionnaireResponseItemComponent();
		nationalIdRr.setLinkId(HivConst.QRLinkIdNationalId12);
		nationalIdRr.setText("National Id Number");
		List<QuestionnaireResponseItemAnswerComponent> listAnswernationalIdRr = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answernationalIdRr = new QuestionnaireResponseItemAnswerComponent();
		answernationalIdRr.getValueStringType().setValue("898989898a6Lan4");
		listAnswernationalIdRr.add(answernationalIdRr);
		nationalIdRr.setAnswer(listAnswernationalIdRr);
		//artID
		QuestionnaireResponseItemComponent arvIdNr = new QuestionnaireResponseItemComponent();
//		arvIdNr.setLinkId(HivConst.QRLinkIdARVNumber);
		arvIdNr.setText("Patient ARV identification number");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerArvIdNr = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerArvIdNr = new QuestionnaireResponseItemAnswerComponent();
		answerArvIdNr.getValueStringType().setValue("10200a6Lan4");
		listAnswerArvIdNr.add(answerArvIdNr);
		arvIdNr.setAnswer(listAnswerArvIdNr);
		
		
		idCccd.addItem(passport_nr);
		idCccd.addItem(nationalIdRr);
		idCccd.addItem(arvIdNr);
		patientInformation.addItem(idCccd);
		
		QuestionnaireResponseItemComponent residence = new QuestionnaireResponseItemComponent();
		residence.setLinkId(HivConst.QRLinkIdResidence);
		residence.setText("Residence");
		QuestionnaireResponseItemComponent current = new QuestionnaireResponseItemComponent();
		current.setLinkId(HivConst.QRLinkIdCurrentResidence);
		current.setText("Current Residence");
		//currentStressAddress
		QuestionnaireResponseItemComponent currentStressAddress = new QuestionnaireResponseItemComponent();
		currentStressAddress.setLinkId(HivConst.QRLinkIdAddress);
		currentStressAddress.setText("Current Stress Address");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerCurrentStressAddress = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerCurrentStressAddress = new QuestionnaireResponseItemAnswerComponent();
		answerCurrentStressAddress.getValueStringType().setValue("so 44s, ngo 117 Thai Ha");
		listAnswerCurrentStressAddress.add(answerCurrentStressAddress);
		currentStressAddress.setAnswer(listAnswerCurrentStressAddress);
		//currentCommune
		QuestionnaireResponseItemComponent currentCommune = new QuestionnaireResponseItemComponent();
		currentCommune.setLinkId(HivConst.QRLinkIdCommune);
		currentCommune.setText("Current Commune");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerCurrentCommune = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerCurrentCommune = new QuestionnaireResponseItemAnswerComponent();
		answerCurrentCommune.getValueStringType().setValue("Dong Da");
		listAnswerCurrentCommune.add(answerCurrentCommune);
		currentCommune.setAnswer(listAnswerCurrentCommune);
		//currentDistrict
		QuestionnaireResponseItemComponent currentDistrict = new QuestionnaireResponseItemComponent();
		currentDistrict.setLinkId(HivConst.QRLinkIdDistrict);
		currentDistrict.setText("Current District");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerCurrentDistrict = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerCurrentDistrict = new QuestionnaireResponseItemAnswerComponent();
		answerCurrentDistrict.getValueStringType().setValue("Dong Da");
		listAnswerCurrentDistrict.add(answerCurrentDistrict);
		currentDistrict.setAnswer(listAnswerCurrentDistrict);
		//currentProvince
		QuestionnaireResponseItemComponent currentProvince = new QuestionnaireResponseItemComponent();
		currentProvince.setLinkId(HivConst.QRLinkIdProvince);
		currentProvince.setText("Current Province");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerCurrentProvince = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerCurrentProvince = new QuestionnaireResponseItemAnswerComponent();
		answerCurrentProvince.getValueStringType().setValue("Ha Noi");
		listAnswerCurrentProvince.add(answerCurrentProvince);
		currentProvince.setAnswer(listAnswerCurrentProvince);
		//currentCountry
		QuestionnaireResponseItemComponent currentCountry = new QuestionnaireResponseItemComponent();
		currentCountry.setLinkId(HivConst.QRLinkIdCountry);
		currentCountry.setText("Current Country");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerCurrentCountry = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerCurrentCountry = new QuestionnaireResponseItemAnswerComponent();
		answerCurrentCountry.getValueStringType().setValue("Viet Nam");
		listAnswerCurrentCountry.add(answerCurrentCountry);
		currentCountry.setAnswer(listAnswerCurrentCountry);
		//currentText
		QuestionnaireResponseItemComponent currentText = new QuestionnaireResponseItemComponent();
//		currentText.setLinkId(HivConst.QRLinkIdCurrentResidenceText);
		currentText.setText("Current Text");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerCurrentText = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerCurrentText = new QuestionnaireResponseItemAnswerComponent();
		answerCurrentText.getValueStringType().setValue("So 44, ngo 117 Thai Ha, Quan Dong Da, Ha Noi, Viet Nam");
		listAnswerCurrentText.add(answerCurrentText);
		currentText.setAnswer(listAnswerCurrentText);
		
		current.addItem(currentStressAddress);
		current.addItem(currentCommune);
		current.addItem(currentDistrict);
		current.addItem(currentProvince);
		current.addItem(currentCountry);
		current.addItem(currentText);
		residence.addItem(current);
		
		QuestionnaireResponseItemComponent permanent = new QuestionnaireResponseItemComponent();
		permanent.setLinkId(HivConst.QRLinkIdPermanentResidence);
		permanent.setText("Permanent residence");
		//permanentStressAddress
		QuestionnaireResponseItemComponent permanentStressAddress = new QuestionnaireResponseItemComponent();
		permanentStressAddress.setLinkId(HivConst.QRLinkIdAddress);
		permanentStressAddress.setText("Permanent Stress Address");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPermanentStressAddress = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPermanentStressAddress = new QuestionnaireResponseItemAnswerComponent();
		answerPermanentStressAddress.getValueStringType().setValue("So 44, ngo 117 Thai Ha");
		listAnswerPermanentStressAddress.add(answerPermanentStressAddress);
		permanentStressAddress.setAnswer(listAnswerPermanentStressAddress);
		//permanentCommune
		QuestionnaireResponseItemComponent permanentCommune = new QuestionnaireResponseItemComponent();
		permanentCommune.setLinkId(HivConst.QRLinkIdCommune);
		permanentCommune.setText("Permanent Commune");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPermanentCommune = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPermanentCommune = new QuestionnaireResponseItemAnswerComponent();
		answerPermanentCommune.getValueStringType().setValue("Dong Da");
		listAnswerPermanentCommune.add(answerPermanentCommune);
		permanentCommune.setAnswer(listAnswerPermanentCommune);
		//PermanentDistrict
		QuestionnaireResponseItemComponent permanentDistrict = new QuestionnaireResponseItemComponent();
		permanentDistrict.setLinkId(HivConst.QRLinkIdDistrict);
		permanentDistrict.setText("Permanent District");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPermanentDistrict = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPermanentDistrict = new QuestionnaireResponseItemAnswerComponent();
		answerPermanentDistrict.getValueStringType().setValue("Dong Da");
		listAnswerPermanentDistrict.add(answerPermanentDistrict);
		permanentDistrict.setAnswer(listAnswerPermanentDistrict);
		//PermanentProvince
		QuestionnaireResponseItemComponent permanentProvince = new QuestionnaireResponseItemComponent();
		permanentProvince.setLinkId(HivConst.QRLinkIdProvince);
		permanentProvince.setText("Permanent Province");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPermanentProvince = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPermanentProvince = new QuestionnaireResponseItemAnswerComponent();
		answerPermanentProvince.getValueStringType().setValue("Ha Noi");
		listAnswerPermanentProvince.add(answerPermanentProvince);
		permanentProvince.setAnswer(listAnswerPermanentProvince);
		//PermanentCountry
		QuestionnaireResponseItemComponent permanentCountry = new QuestionnaireResponseItemComponent();
		permanentCountry.setLinkId(HivConst.QRLinkIdCountry);
		permanentCountry.setText("Permanent Country");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPermanentCountry = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPermanentCountry = new QuestionnaireResponseItemAnswerComponent();
		answerPermanentCountry.getValueStringType().setValue("Viet Nam");
		listAnswerPermanentCountry.add(answerPermanentCountry);
		permanentCountry.setAnswer(listAnswerPermanentCountry);
		//PermanentText
		QuestionnaireResponseItemComponent permanentText = new QuestionnaireResponseItemComponent();
//		permanentText.setLinkId(HivConst.QRLinkIdPermanentResidenceText);
		permanentText.setText("Permanent Text");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerPermanentText = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerPermanentText = new QuestionnaireResponseItemAnswerComponent();
		answerPermanentText.getValueStringType().setValue("So 44, ngo 117 Thai Ha, Quan Dong Da, Ha Noi, Viet Nam");
		listAnswerPermanentText.add(answerPermanentText);
		permanentText.setAnswer(listAnswerPermanentText);
		permanent.addItem(permanentStressAddress);
		permanent.addItem(permanentCommune);
		permanent.addItem(permanentDistrict);
		permanent.addItem(permanentProvince);
		permanent.addItem(permanentCountry);
		permanent.addItem(permanentText);
		
		residence.addItem(permanent);
		patientInformation.addItem(residence);
		
		//Risk factors
		QuestionnaireResponseItemComponent riskFactors = new QuestionnaireResponseItemComponent();
		riskFactors.setLinkId(HivConst.QRLinkIdRiskFactor);
		riskFactors.setText("Risk Factors");
		//riskPolpulation
		QuestionnaireResponseItemComponent riskPopulation = new QuestionnaireResponseItemComponent();
		riskPopulation.setLinkId(HivConst.QRLinkIdRiskPopulation);
		riskPopulation.setText("Risk Population");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskPopulation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerRiskPopulation = new QuestionnaireResponseItemAnswerComponent();
		answerRiskPopulation.getValueCoding().setCode("9");
		answerRiskPopulation.getValueCoding().setDisplay("MSM");
		listAnswerRiskPopulation.add(answerRiskPopulation);
		riskPopulation.setAnswer(listAnswerRiskPopulation);
		//riskBehavior
		QuestionnaireResponseItemComponent riskBehavior = new QuestionnaireResponseItemComponent();
		riskBehavior.setLinkId(HivConst.QRLinkIdRiskBehavior);
		riskBehavior.setText("Risk Behavior");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerRiskBehavior = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerRiskBehavior = new QuestionnaireResponseItemAnswerComponent();
		answerRiskBehavior.getValueCoding().setCode("3");
		answerRiskBehavior.getValueCoding().setDisplay("MSM");
		listAnswerRiskBehavior.add(answerRiskBehavior);
		riskBehavior.setAnswer(listAnswerRiskBehavior);
		//transmissionRoute
		QuestionnaireResponseItemComponent transmissionRoute = new QuestionnaireResponseItemComponent();
		riskBehavior.setLinkId(HivConst.QRLinkIdTransmssionRoute);
		riskBehavior.setText("Transmssion Route");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerTransmissionRoute = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerTransmissionRoute = new QuestionnaireResponseItemAnswerComponent();
		answerTransmissionRoute.getValueCoding().setCode("2");
		answerTransmissionRoute.getValueCoding().setDisplay("Sexual Relationship");
		listAnswerTransmissionRoute.add(answerTransmissionRoute);
		transmissionRoute.setAnswer(listAnswerTransmissionRoute);
		
		riskFactors.addItem(riskPopulation);
		riskFactors.addItem(riskBehavior);
		riskFactors.addItem(transmissionRoute);
		
		//HIV Diagnosis
		QuestionnaireResponseItemComponent hivDiagnosis = new QuestionnaireResponseItemComponent();
		hivDiagnosis.setLinkId(HivConst.QRLinkIdHivDiagnosis);
		hivDiagnosis.setText("HIV Diagnosis");
		
		QuestionnaireResponseItemComponent dateOfConfirmation = new QuestionnaireResponseItemComponent();
		dateOfConfirmation.setLinkId(HivConst.QRLinkIdDateOfConfirmation);
		dateOfConfirmation.setText("Date of Confirmation");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerDateOfConfirmation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerDateOfConfirmation = new QuestionnaireResponseItemAnswerComponent();
		//answerDateOfConfirmation.getValueStringType().setValue("14-04-2021");
		Date hivDiagnosisDateOfConfirmation = null;
		try {
			hivDiagnosisDateOfConfirmation = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		answerDateOfConfirmation.getValueDateType().setValue(hivDiagnosisDateOfConfirmation);
		
		listAnswerDateOfConfirmation.add(answerDateOfConfirmation);
		dateOfConfirmation.setAnswer(listAnswerDateOfConfirmation);
		hivDiagnosis.addItem(dateOfConfirmation);
		
		QuestionnaireResponseItemComponent confirmingLab = new QuestionnaireResponseItemComponent();
		confirmingLab.setLinkId(HivConst.QRLinkIdConfirmingLab);
		confirmingLab.setText("Confirming Lab");
		List<QuestionnaireResponseItemAnswerComponent> listAnswerConfirmingLab = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
		QuestionnaireResponseItemAnswerComponent answerConfirmingLab = new QuestionnaireResponseItemAnswerComponent();
		answerConfirmingLab.getValueStringType().setValue("BV Bach Mai");
		listAnswerConfirmingLab.add(answerConfirmingLab);
		confirmingLab.setAnswer(listAnswerConfirmingLab);
		hivDiagnosis.addItem(confirmingLab);
		
		//Recency Test
		QuestionnaireResponseItemComponent hivRecencyTest = new QuestionnaireResponseItemComponent();
		hivRecencyTest.setLinkId(HivConst.QRLinkIdHivRecencyTest);
		hivRecencyTest.setText("HIV Recency Test");
		
		QuestionnaireResponseItemComponent vlTest = new QuestionnaireResponseItemComponent();
		vlTest.setLinkId(HivConst.QRLinkIdRecencyVlTest);
		vlTest.setText("Rapid VL test");
		
			QuestionnaireResponseItemComponent vlTestDateOfTestPerformance = new QuestionnaireResponseItemComponent();
			vlTestDateOfTestPerformance.setLinkId(HivConst.QRLinkIdDateOfTestPerformance);
			vlTestDateOfTestPerformance.setText("Date of rapid test performance");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerVLTestDateOfTestPerformance = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerVLTestDateOfTestPerformance = new QuestionnaireResponseItemAnswerComponent();
			//answerVLTestDateOfTestPerformance.getValueStringType().setValue(data.getRapidVLTestDate());
			Date rapidVLTestDate = null;
			try {
				rapidVLTestDate = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerVLTestDateOfTestPerformance.getValueDateType().setValue(rapidVLTestDate);
			
			listAnswerVLTestDateOfTestPerformance.add(answerVLTestDateOfTestPerformance);
			vlTestDateOfTestPerformance.setAnswer(listAnswerVLTestDateOfTestPerformance);
			vlTest.addItem(vlTestDateOfTestPerformance);
		
		
			QuestionnaireResponseItemComponent vlRecencyTestResult = new QuestionnaireResponseItemComponent();
			vlRecencyTestResult.setLinkId(HivConst.QRLinkIdVlRecencyTestResult);
			vlRecencyTestResult.setText("VL recency test result");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerVLRecencyTestResult = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerVLRecencyTestResult = new QuestionnaireResponseItemAnswerComponent();
			//answerVLRecencyTestResult.getValueStringType().setValue(data.getRapidVLTestResult().toString());
			Date rapidVLTestResult = null;
			try {
				rapidVLTestResult = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerVLRecencyTestResult.getValueDateType().setValue(rapidVLTestResult);
			
			listAnswerVLRecencyTestResult.add(answerVLRecencyTestResult);
			vlRecencyTestResult.setAnswer(listAnswerVLRecencyTestResult);
			vlTest.addItem(vlRecencyTestResult);
		
		hivRecencyTest.addItem(vlTest);
		
		//CD4 Before ART
		QuestionnaireResponseItemComponent cd4BeforeART = new QuestionnaireResponseItemComponent();
		cd4BeforeART.setLinkId(HivConst.QRLinkIdCd4Test);
		cd4BeforeART.setText("CD4 test before ART");
			
			QuestionnaireResponseItemComponent cd4BeforeARTDateOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			cd4BeforeARTDateOfSpecimenCollection.setLinkId(HivConst.QRLinkIdDateOfSpecimenCollection);
			cd4BeforeARTDateOfSpecimenCollection.setText("Date of Specimen Collection for CD4 test before ART");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4BeforeARTDateOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4BeforeARTDateOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			//answerCd4BeforeARTDateOfSpecimenCollection.getValueStringType().setValue(data.getCd4SampleDateBeforeARV());
			Date cd4SampleDateBeforeARV = null;
			try {
				cd4SampleDateBeforeARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerCd4BeforeARTDateOfSpecimenCollection.getValueDateType().setValue(cd4SampleDateBeforeARV);
			
			listAnswerCd4BeforeARTDateOfSpecimenCollection.add(answerCd4BeforeARTDateOfSpecimenCollection);
			cd4BeforeARTDateOfSpecimenCollection.setAnswer(listAnswerCd4BeforeARTDateOfSpecimenCollection);
			cd4BeforeART.addItem(cd4BeforeARTDateOfSpecimenCollection);
			QuestionnaireResponseItemComponent cd4BeforeARTDateOfTestPerformance = new QuestionnaireResponseItemComponent();
			cd4BeforeARTDateOfTestPerformance.setLinkId(HivConst.QRLinkIdDateOfTestPerformance);
			cd4BeforeARTDateOfTestPerformance.setText("Date of CD4 test before ART performance");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4BeforeARTDateOfTestPerformance = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4BeforeARTDateOfTestPerformance = new QuestionnaireResponseItemAnswerComponent();
			//answerCd4BeforeARTDateOfTestPerformance.getValueStringType().setValue(data.getCd4ResultDateBeforeARV());
			Date cd4ResultDateBeforeARV = null;
			try {
				cd4ResultDateBeforeARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerCd4BeforeARTDateOfTestPerformance.getValueDateType().setValue(cd4ResultDateBeforeARV);
			
			listAnswerCd4BeforeARTDateOfTestPerformance.add(answerCd4BeforeARTDateOfTestPerformance);
			cd4BeforeARTDateOfTestPerformance.setAnswer(listAnswerCd4BeforeARTDateOfTestPerformance);
			cd4BeforeART.addItem(cd4BeforeARTDateOfTestPerformance);
		
			QuestionnaireResponseItemComponent cd4BeforeARTPlaceOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			cd4BeforeARTPlaceOfSpecimenCollection.setLinkId(HivConst.QRLinkIdPlaceOfSpecimenCollection);
			cd4BeforeARTPlaceOfSpecimenCollection.setText("Place of Specimen Collection for CD4 test before ART");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4BeforeARTPlaceOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4BeforeARTPlaceOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			answerCd4BeforeARTPlaceOfSpecimenCollection.getValueStringType().setValue("BV Bach Mai");
			listAnswerCd4BeforeARTPlaceOfSpecimenCollection.add(answerCd4BeforeARTPlaceOfSpecimenCollection);
			cd4BeforeARTPlaceOfSpecimenCollection.setAnswer(listAnswerCd4BeforeARTPlaceOfSpecimenCollection);
			cd4BeforeART.addItem(cd4BeforeARTPlaceOfSpecimenCollection);
		
			QuestionnaireResponseItemComponent cd4BeforeARTTestResult = new QuestionnaireResponseItemComponent();
			cd4BeforeARTTestResult.setLinkId(HivConst.QRLinkIdTestResult);
			cd4BeforeARTTestResult.setText("CD4 test before ART - result");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4BeforeARTTestResult = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4BeforeARTTestResult = new QuestionnaireResponseItemAnswerComponent();
			answerCd4BeforeARTTestResult.getValueStringType().setValue("696969");
			listAnswerCd4BeforeARTTestResult.add(answerCd4BeforeARTTestResult);
			cd4BeforeARTTestResult.setAnswer(listAnswerCd4BeforeARTTestResult);
			cd4BeforeART.addItem(cd4BeforeARTTestResult);
		
		//CD4 During ART
		QuestionnaireResponseItemComponent cd4DuringART = new QuestionnaireResponseItemComponent();
		cd4DuringART.setLinkId(HivConst.QRLinkIdCd4Test);
		cd4DuringART.setText("CD4 test during ART");
			QuestionnaireResponseItemComponent cd4DuringARTDateOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			cd4DuringARTDateOfSpecimenCollection.setLinkId(HivConst.QRLinkIdDateOfSpecimenCollection);
			cd4DuringARTDateOfSpecimenCollection.setText("Date of Specimen Collection for CD4 test during ART");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4DuringARTDateOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4DuringARTDateOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			//answerCd4DuringARTDateOfSpecimenCollection.getValueStringType().setValue(data.getCd4SampleDateDuringARV());
			Date cd4SampleDateDuringARV = null;
			try {
				cd4SampleDateDuringARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerCd4DuringARTDateOfSpecimenCollection.getValueDateType().setValue(cd4SampleDateDuringARV);
			
			listAnswerCd4DuringARTDateOfSpecimenCollection.add(answerCd4DuringARTDateOfSpecimenCollection);
			cd4DuringARTDateOfSpecimenCollection.setAnswer(listAnswerCd4DuringARTDateOfSpecimenCollection);
			cd4DuringART.addItem(cd4DuringARTDateOfSpecimenCollection);
		
			QuestionnaireResponseItemComponent cd4DuringARTDateOfTestPerformance = new QuestionnaireResponseItemComponent();
			cd4DuringARTDateOfTestPerformance.setLinkId(HivConst.QRLinkIdDateOfTestPerformance);
			cd4DuringARTDateOfTestPerformance.setText("Date of CD4 test during ART performance");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4DuringARTDateOfTestPerformance = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4DuringARTDateOfTestPerformance = new QuestionnaireResponseItemAnswerComponent();
			//answerCd4DuringARTDateOfTestPerformance.getValueStringType().setValue(data.getCd4ResultDateDuringARV());
			
			Date cd4ResultDateDuringARV = null;
			try {
				cd4ResultDateDuringARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerCd4DuringARTDateOfTestPerformance.getValueDateType().setValue(cd4ResultDateDuringARV);
			
			listAnswerCd4DuringARTDateOfTestPerformance.add(answerCd4DuringARTDateOfTestPerformance);
			cd4DuringARTDateOfTestPerformance.setAnswer(listAnswerCd4DuringARTDateOfTestPerformance);
			cd4DuringART.addItem(cd4DuringARTDateOfTestPerformance);
		
			QuestionnaireResponseItemComponent cd4DuringARTPlaceOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			cd4DuringARTPlaceOfSpecimenCollection.setLinkId(HivConst.QRLinkIdPlaceOfSpecimenCollection);
			cd4DuringARTPlaceOfSpecimenCollection.setText("Place of Specimen Collection for CD4 test during ART");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4DuringARTPlaceOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4DuringARTPlaceOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			answerCd4DuringARTPlaceOfSpecimenCollection.getValueStringType().setValue("BV Bach Mai");
			listAnswerCd4DuringARTPlaceOfSpecimenCollection.add(answerCd4DuringARTPlaceOfSpecimenCollection);
			cd4DuringARTPlaceOfSpecimenCollection.setAnswer(listAnswerCd4DuringARTPlaceOfSpecimenCollection);
			cd4DuringART.addItem(cd4DuringARTPlaceOfSpecimenCollection);
		
			QuestionnaireResponseItemComponent cd4DuringARTTestResult = new QuestionnaireResponseItemComponent();
			cd4DuringARTTestResult.setLinkId(HivConst.QRLinkIdTestResult);
			cd4DuringARTTestResult.setText("CD4 test during ART - result");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerCd4DuringARTTestResult = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerCd4DuringARTTestResult = new QuestionnaireResponseItemAnswerComponent();
			answerCd4DuringARTTestResult.getValueStringType().setValue("1234");
			listAnswerCd4DuringARTTestResult.add(answerCd4DuringARTTestResult);
			cd4DuringARTTestResult.setAnswer(listAnswerCd4DuringARTTestResult);
			cd4DuringART.addItem(cd4DuringARTTestResult);
		
		//VL test During ART
		QuestionnaireResponseItemComponent vl4DuringART = new QuestionnaireResponseItemComponent();
		vl4DuringART.setLinkId(HivConst.QRLinkIdVl4DuringArt);
		vl4DuringART.setText("VL test during ART");
			QuestionnaireResponseItemComponent vl4DuringARTDateOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			vl4DuringARTDateOfSpecimenCollection.setLinkId(HivConst.QRLinkIdDateOfSpecimenCollection);
			vl4DuringARTDateOfSpecimenCollection.setText("Date of Specimen Collection for VL test during ART");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerVl4DuringARTDateOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerVl4DuringARTDateOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			//answerVl4DuringARTDateOfSpecimenCollection.getValueStringType().setValue(data.getVlSampleDateDuringARV());
			Date vlTestDateDuringARV = null;
			try {
				vlTestDateDuringARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerVl4DuringARTDateOfSpecimenCollection.getValueDateType().setValue(vlTestDateDuringARV);
			
			
			listAnswerVl4DuringARTDateOfSpecimenCollection.add(answerVl4DuringARTDateOfSpecimenCollection);
			vl4DuringARTDateOfSpecimenCollection.setAnswer(listAnswerVl4DuringARTDateOfSpecimenCollection);
			vl4DuringART.addItem(vl4DuringARTDateOfSpecimenCollection);
		
			QuestionnaireResponseItemComponent vl4DuringARTDateOfTestPerformance = new QuestionnaireResponseItemComponent();
			vl4DuringARTDateOfTestPerformance.setLinkId(HivConst.QRLinkIdDateOfTestPerformance);
			vl4DuringARTDateOfTestPerformance.setText("Date of VL test during ART performance");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerVl4DuringARTDateOfTestPerformance = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerVl4DuringARTDateOfTestPerformance = new QuestionnaireResponseItemAnswerComponent();
			//answerVl4DuringARTDateOfTestPerformance.getValueStringType().setValue(data.getVlResultDateDuringARV());
			Date vlResultTestDateDuringARV = null;
			try {
				vlResultTestDateDuringARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerVl4DuringARTDateOfTestPerformance.getValueDateType().setValue(vlResultTestDateDuringARV);
			
			listAnswerVl4DuringARTDateOfTestPerformance.add(answerVl4DuringARTDateOfTestPerformance);
			vl4DuringARTDateOfTestPerformance.setAnswer(listAnswerVl4DuringARTDateOfTestPerformance);
			vl4DuringART.addItem(vl4DuringARTDateOfTestPerformance);
		
			QuestionnaireResponseItemComponent vl4DuringARTPlaceOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			vl4DuringARTPlaceOfSpecimenCollection.setLinkId(HivConst.QRLinkIdPlaceOfSpecimenCollection);
			vl4DuringARTPlaceOfSpecimenCollection.setText("Place of Specimen Collection for VL test during ART");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerVl4DuringARTPlaceOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerVl4DuringARTPlaceOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			answerVl4DuringARTPlaceOfSpecimenCollection.getValueStringType().setValue("BV Bach Mai");
			listAnswerVl4DuringARTPlaceOfSpecimenCollection.add(answerVl4DuringARTPlaceOfSpecimenCollection);
			vl4DuringARTPlaceOfSpecimenCollection.setAnswer(listAnswerVl4DuringARTPlaceOfSpecimenCollection);
			vl4DuringART.addItem(vl4DuringARTPlaceOfSpecimenCollection);
		
			QuestionnaireResponseItemComponent vl4DuringARTTestResult = new QuestionnaireResponseItemComponent();
			vl4DuringARTTestResult.setLinkId(HivConst.QRLinkIdTestResult);
			vl4DuringARTTestResult.setText("VL test during ART - result");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerVl4DuringARTTestResult = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerVl4DuringARTTestResult = new QuestionnaireResponseItemAnswerComponent();
			answerVl4DuringARTTestResult.getValueStringType().setValue("34353");
			listAnswerVl4DuringARTTestResult.add(answerVl4DuringARTTestResult);
			vl4DuringARTTestResult.setAnswer(listAnswerVl4DuringARTTestResult);
			vl4DuringART.addItem(vl4DuringARTTestResult);
		
		
		//Drug Resistance test
		QuestionnaireResponseItemComponent drugResistanceTest = new QuestionnaireResponseItemComponent();
		drugResistanceTest.setLinkId(HivConst.QRLinkIdDrugResistanceTest);
		drugResistanceTest.setText("Drug Resistance test");
			QuestionnaireResponseItemComponent drugResistanceTestDateOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			drugResistanceTestDateOfSpecimenCollection.setLinkId(HivConst.QRLinkIdDateOfSpecimenCollection);
			drugResistanceTestDateOfSpecimenCollection.setText("Date of Specimen Collection for Drug Resistance test");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerDrugResistanceTestDateOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerDrugResistanceTestDateOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			//answerDrugResistanceTestDateOfSpecimenCollection.getValueStringType().setValue(data.getVlSampleDateDuringARV());
			Date vlSampleDateDuringARV = null;
			try {
				vlSampleDateDuringARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerDrugResistanceTestDateOfSpecimenCollection.getValueDateType().setValue(vlSampleDateDuringARV);
			
			listAnswerDrugResistanceTestDateOfSpecimenCollection.add(answerDrugResistanceTestDateOfSpecimenCollection);
			drugResistanceTestDateOfSpecimenCollection.setAnswer(listAnswerDrugResistanceTestDateOfSpecimenCollection);
			drugResistanceTest.addItem(drugResistanceTestDateOfSpecimenCollection);
	
			QuestionnaireResponseItemComponent drugResistanceTestDateOfTestPerformance = new QuestionnaireResponseItemComponent();
			drugResistanceTestDateOfTestPerformance.setLinkId(HivConst.QRLinkIdDateOfTestPerformance);
			drugResistanceTestDateOfTestPerformance.setText("Date of Drug Resistance test");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerDrugResistanceTestDateOfTestPerformance = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerDrugResistanceTestDateOfTestPerformance = new QuestionnaireResponseItemAnswerComponent();
			//answerDrugResistanceTestDateOfTestPerformance.getValueStringType().setValue(data.getVlResultDateDuringARV());
			Date vlResultDateDuringARV = null;
			try {
				vlResultDateDuringARV = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerDrugResistanceTestDateOfTestPerformance.getValueDateType().setValue(vlResultDateDuringARV);
			
			listAnswerDrugResistanceTestDateOfTestPerformance.add(answerDrugResistanceTestDateOfTestPerformance);
			drugResistanceTestDateOfTestPerformance.setAnswer(listAnswerDrugResistanceTestDateOfTestPerformance);
			drugResistanceTest.addItem(drugResistanceTestDateOfTestPerformance);
		
			QuestionnaireResponseItemComponent drugResistanceTestPlaceOfSpecimenCollection = new QuestionnaireResponseItemComponent();
			drugResistanceTestPlaceOfSpecimenCollection.setLinkId(HivConst.QRLinkIdPlaceOfSpecimenCollection);
			drugResistanceTestPlaceOfSpecimenCollection.setText("Place of Specimen Collection for Drug Resistance test");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerDrugResistanceTestPlaceOfSpecimenCollection = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerDrugResistanceTestPlaceOfSpecimenCollection = new QuestionnaireResponseItemAnswerComponent();
			answerDrugResistanceTestPlaceOfSpecimenCollection.getValueStringType().setValue("BV Bach Mai");
			listAnswerDrugResistanceTestPlaceOfSpecimenCollection.add(answerDrugResistanceTestPlaceOfSpecimenCollection);
			drugResistanceTestPlaceOfSpecimenCollection.setAnswer(listAnswerDrugResistanceTestPlaceOfSpecimenCollection);
			drugResistanceTest.addItem(drugResistanceTestPlaceOfSpecimenCollection);
		
			QuestionnaireResponseItemComponent drugResistanceTestTestResult = new QuestionnaireResponseItemComponent();
			drugResistanceTestTestResult.setLinkId(HivConst.QRLinkIdTestResult);
			drugResistanceTestTestResult.setText("Drug Resistance test result");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerDrugResistanceTestTestResult = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerDrugResistanceTestTestResult = new QuestionnaireResponseItemAnswerComponent();
			answerDrugResistanceTestTestResult.getValueStringType().setValue("Duong tinh");
			listAnswerDrugResistanceTestTestResult.add(answerDrugResistanceTestTestResult);
			drugResistanceTestTestResult.setAnswer(listAnswerDrugResistanceTestTestResult);
			drugResistanceTest.addItem(drugResistanceTestTestResult);
		
		//ARV Treatment
		QuestionnaireResponseItemComponent arvTreatment = new QuestionnaireResponseItemComponent();
		arvTreatment.setLinkId(HivConst.QRLinkIdArvTreatment);
		arvTreatment.setText("ARV Treatment");
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStart = new QuestionnaireResponseItemComponent();
			arvTreatmentDateOfTreatmentStart.setLinkId(HivConst.QRLinkIdDateOfTreatmentStart);
			arvTreatmentDateOfTreatmentStart.setText("Date of treatment start");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerARVTreatmentDateOfTreatmentStart = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerARVTreatmentDateOfTreatmentStart = new QuestionnaireResponseItemAnswerComponent();
			
			Date arvTreatmentDateStart = null;
			try {
				arvTreatmentDateStart = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerARVTreatmentDateOfTreatmentStart.getValueDateType().setValue(arvTreatmentDateStart);
			
			listAnswerARVTreatmentDateOfTreatmentStart.add(answerARVTreatmentDateOfTreatmentStart);
			arvTreatmentDateOfTreatmentStart.setAnswer(listAnswerARVTreatmentDateOfTreatmentStart);
			arvTreatment.addItem(arvTreatmentDateOfTreatmentStart);
		
			QuestionnaireResponseItemComponent arvTreatmentDateOfTreatmentStop = new QuestionnaireResponseItemComponent();
			arvTreatmentDateOfTreatmentStop.setLinkId(HivConst.QRLinkIdDateOfTreatmentStop);
			arvTreatmentDateOfTreatmentStop.setText("Date of treatment stop");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerARVTreatmentDateOfTreatmentStop = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerARVTreatmentDateOfTreatmentStop = new QuestionnaireResponseItemAnswerComponent();
			
			Date arvTreatmentDateEnd = null;
			try {
				arvTreatmentDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerARVTreatmentDateOfTreatmentStop.getValueDateType().setValue(arvTreatmentDateEnd);
			
			listAnswerARVTreatmentDateOfTreatmentStop.add(answerARVTreatmentDateOfTreatmentStop);
			arvTreatmentDateOfTreatmentStop.setAnswer(listAnswerARVTreatmentDateOfTreatmentStop);
			arvTreatment.addItem(arvTreatmentDateOfTreatmentStop);
		
			QuestionnaireResponseItemComponent arvTreatmentPlaceOfInitiation = new QuestionnaireResponseItemComponent();
			arvTreatmentPlaceOfInitiation.setLinkId(HivConst.QRLinkIdPlaceOfInitiation);
			arvTreatmentPlaceOfInitiation.setText("Place of ARV treatment initiation");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerArvTreatmentPlaceInitiation = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerArvTreatmentPlaceInitiation = new QuestionnaireResponseItemAnswerComponent();
			answerArvTreatmentPlaceInitiation.getValueStringType().setValue("BV Bach Mai");
			listAnswerArvTreatmentPlaceInitiation.add(answerArvTreatmentPlaceInitiation);
			arvTreatmentPlaceOfInitiation.setAnswer(listAnswerArvTreatmentPlaceInitiation);
			arvTreatment.addItem(arvTreatmentPlaceOfInitiation);
		
		
		//Comorbidities
		QuestionnaireResponseItemComponent comorbidities = new QuestionnaireResponseItemComponent();
		comorbidities.setLinkId(HivConst.QRLinkIdComorbidities);
		comorbidities.setText("Comorbidities");
		//Comorbidities.Tuberculosis
		QuestionnaireResponseItemComponent tuberculosis = new QuestionnaireResponseItemComponent();
		tuberculosis.setLinkId(HivConst.QRLinkIdTuberculosis);
		tuberculosis.setText("Tuberculosis");
		//Comorbidities.Tuberculosis.TPT
		QuestionnaireResponseItemComponent tpt = new QuestionnaireResponseItemComponent();
		tpt.setLinkId(HivConst.QRLinkIdTpt);
		tpt.setText("TPT");
			QuestionnaireResponseItemComponent tptDateStarted = new QuestionnaireResponseItemComponent();
			tptDateStarted.setLinkId(HivConst.QRLinkIdDateStarted);
			tptDateStarted.setText("Date TPT started");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTPTDateStarted = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTPTDateStarted = new QuestionnaireResponseItemAnswerComponent();
			Date tptTreamentDateStart = null;
			try {
				tptTreamentDateStart = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerTPTDateStarted.getValueDateType().setValue(tptTreamentDateStart);
			
			listAnswerTPTDateStarted.add(answerTPTDateStarted);
			tptDateStarted.setAnswer(listAnswerTPTDateStarted);
			tpt.addItem(tptDateStarted);
		
			QuestionnaireResponseItemComponent tptDateCompleted = new QuestionnaireResponseItemComponent();
			tptDateCompleted.setLinkId(HivConst.QRLinkIdDateCompleted);
			tptDateCompleted.setText("Date TPT completed");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTPTDateCompleted = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTPTDateCompleted = new QuestionnaireResponseItemAnswerComponent();
			Date tptTreamentDateCompleted = null;
			try {
				tptTreamentDateCompleted = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerTPTDateCompleted.getValueDateType().setValue(tptTreamentDateCompleted);
			
			listAnswerTPTDateCompleted.add(answerTPTDateCompleted);
			tptDateCompleted.setAnswer(listAnswerTPTDateCompleted);
			tpt.addItem(tptDateCompleted);
		
			QuestionnaireResponseItemComponent tptPlaceProvided = new QuestionnaireResponseItemComponent();
			tptPlaceProvided.setLinkId(HivConst.QRLinkIdPlaceProvided);
			tptPlaceProvided.setText("Place TPT provided");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTPTPlaceProvided = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTPTPlaceProvided = new QuestionnaireResponseItemAnswerComponent();
			answerTPTPlaceProvided.getValueStringType().setValue("BV Thai Nguyen");
			listAnswerTPTPlaceProvided.add(answerTPTPlaceProvided);
			tptPlaceProvided.setAnswer(listAnswerTPTPlaceProvided);
			tpt.addItem(tptPlaceProvided);
		
		tuberculosis.addItem(tpt);
		//Comorbidities.Tuberculosis.TB Diagnosis Date
			QuestionnaireResponseItemComponent tbDiagnosisDate = new QuestionnaireResponseItemComponent();
			tbDiagnosisDate.setLinkId(HivConst.QRLinkIdTbDiagnosisDate);
			tbDiagnosisDate.setText("TB Diagnosis Date");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTBDiagnosisDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTBDiagnosisDate = new QuestionnaireResponseItemAnswerComponent();
			Date tbDiagnoseDate = null;
			try {
				tbDiagnoseDate = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerTBDiagnosisDate.getValueDateType().setValue(tbDiagnoseDate);
			
			listAnswerTBDiagnosisDate.add(answerTBDiagnosisDate);
			tbDiagnosisDate.setAnswer(listAnswerTBDiagnosisDate);
			tuberculosis.addItem(tbDiagnosisDate);
		
		//Comorbidities.Tuberculosis.TB Treatment
		QuestionnaireResponseItemComponent tbTreatment = new QuestionnaireResponseItemComponent();
		tbTreatment.setLinkId(HivConst.QRLinkIdTbTreatment);
		tbTreatment.setText("TB Treatment");
			QuestionnaireResponseItemComponent tbTreatmentDateStarted = new QuestionnaireResponseItemComponent();
			tbTreatmentDateStarted.setLinkId("date-started");
			tbTreatmentDateStarted.setText("Date TB Treatment started");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTBTreatmentDateStarted = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTBTreatmentDateStarted = new QuestionnaireResponseItemAnswerComponent();
			Date tbTreamentDateStart = null;
			try {
				tbTreamentDateStart = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerTBTreatmentDateStarted.getValueDateType().setValue(tbTreamentDateStart);
			
			listAnswerTBTreatmentDateStarted.add(answerTBTreatmentDateStarted);
			tbTreatmentDateStarted.setAnswer(listAnswerTBTreatmentDateStarted);
			tbTreatment.addItem(tbTreatmentDateStarted);
		
			QuestionnaireResponseItemComponent tbTreatmentDateCompleted = new QuestionnaireResponseItemComponent();
			tbTreatmentDateCompleted.setLinkId(HivConst.QRLinkIdDateCompleted);
			tbTreatmentDateCompleted.setText("Date TB Treatment completed");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerTBTreatmentDateCompleted = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerTBTreatmentDateCompleted = new QuestionnaireResponseItemAnswerComponent();
			Date tbTreamentDateEnd = null;
			try {
				tbTreamentDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerTBTreatmentDateCompleted.getValueDateType().setValue(tbTreamentDateEnd);
			
			listAnswerTBTreatmentDateCompleted.add(answerTBTreatmentDateCompleted);
			tbTreatmentDateCompleted.setAnswer(listAnswerTBTreatmentDateCompleted);
			tbTreatment.addItem(tbTreatmentDateCompleted);
		
			QuestionnaireResponseItemComponent tbTreamentplaceProvided = new QuestionnaireResponseItemComponent();
			tbTreamentplaceProvided.setLinkId(HivConst.QRLinkIdPlaceProvided);
			tbTreamentplaceProvided.setText("Place TB Treatment provided");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerPlaceProvided = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerPlaceProvided = new QuestionnaireResponseItemAnswerComponent();
			answerPlaceProvided.getValueStringType().setValue("BV 108");
			listAnswerPlaceProvided.add(answerPlaceProvided);
			tbTreamentplaceProvided.setAnswer(listAnswerPlaceProvided);
			tbTreatment.addItem(tbTreamentplaceProvided);
			comorbidities.addItem(tuberculosis);
			//Comorbidities.Hbv-hcv
			QuestionnaireResponseItemComponent hbcHcv = new QuestionnaireResponseItemComponent();
			hbcHcv.setLinkId(HivConst.QRLinkIdHbvHcv);
			hbcHcv.setText("HBV and HCV");
			//HBV
			QuestionnaireResponseItemComponent hbv = new QuestionnaireResponseItemComponent();
			hbv.setLinkId(HivConst.QRLinkIdHbv);
			hbv.setText("HBV");
			//HBV DiagnosisDate
			QuestionnaireResponseItemComponent hbvDiagnosisDate = new QuestionnaireResponseItemComponent();
			hbvDiagnosisDate.setLinkId(HivConst.QRLinkIdDiagnosisDate);
			hbvDiagnosisDate.setText("HBV Diagnosis Date");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerHBVDiagnosisDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerHBVDiagnosisDate = new QuestionnaireResponseItemAnswerComponent();
			Date hbvDiagnoseDate = null;
			try {
				hbvDiagnoseDate = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerHBVDiagnosisDate.getValueDateType().setValue(hbvDiagnoseDate);
			
			listAnswerHBVDiagnosisDate.add(answerHBVDiagnosisDate);
			hbvDiagnosisDate.setAnswer(listAnswerHBVDiagnosisDate);
			hbv.addItem(hbvDiagnosisDate);
			
			//HBC Date Started
			QuestionnaireResponseItemComponent hbvDateStarted = new QuestionnaireResponseItemComponent();
			hbvDateStarted.setLinkId(HivConst.QRLinkIdDateOfTreatmentStart);
			hbvDateStarted.setText("Date HBV started");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerHBVDateStarted = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerHBVDateStarted = new QuestionnaireResponseItemAnswerComponent();
			Date hbvTreamentDateStart = null;
			try {
				hbvTreamentDateStart = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerHBVDateStarted.getValueDateType().setValue(hbvTreamentDateStart);
			
			listAnswerHBVDateStarted.add(answerHBVDateStarted);
			hbvDateStarted.setAnswer(listAnswerHBVDateStarted);
			hbv.addItem(hbvDateStarted);
			//HBV Date Completed
			QuestionnaireResponseItemComponent hbvDateCompleted = new QuestionnaireResponseItemComponent();
			hbvDateCompleted.setLinkId(HivConst.QRLinkIdDateOfTreatmentStop);
			hbvDateCompleted.setText("Date HBV completed");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerHBVDateCompleted = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerHBVDateCompleted = new QuestionnaireResponseItemAnswerComponent();
			Date hbvTreamentDateCompleted = null;
			try {
				hbvTreamentDateCompleted = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerHBVDateCompleted.getValueDateType().setValue(hbvTreamentDateCompleted);
			
			listAnswerHBVDateCompleted.add(answerHBVDateCompleted);
			hbvDateCompleted.setAnswer(listAnswerHBVDateCompleted);
			hbv.addItem(hbvDateCompleted);
			//HBV placeProvider
			QuestionnaireResponseItemComponent hbvPlaceProvided = new QuestionnaireResponseItemComponent();
			hbvPlaceProvided.setLinkId(HivConst.QRLinkIdPlaceProvided);
			hbvPlaceProvided.setText("Place HBV provided");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerHBVPlaceProvided = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerHBVPlaceProvided = new QuestionnaireResponseItemAnswerComponent();
			answerHBVPlaceProvided.getValueStringType().setValue("BV Thai Nguyen");
			listAnswerHBVPlaceProvided.add(answerHBVPlaceProvided);
			hbvPlaceProvided.setAnswer(listAnswerHBVPlaceProvided);
			hbv.addItem(hbvPlaceProvided);
		
			hbcHcv.addItem(hbv);
			//HCV
			QuestionnaireResponseItemComponent hcv = new QuestionnaireResponseItemComponent();
			hcv.setLinkId(HivConst.QRLinkIdHcv);
			hcv.setText("HCV");
			//HCV DiagnosisDate
			QuestionnaireResponseItemComponent hcvDiagnosisDate = new QuestionnaireResponseItemComponent();
			hcvDiagnosisDate.setLinkId(HivConst.QRLinkIdDiagnosisDate);
			hcvDiagnosisDate.setText("HCV Diagnosis Date");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerHCVDiagnosisDate = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerHCVDiagnosisDate = new QuestionnaireResponseItemAnswerComponent();
			Date hcvDiagnoseDate = null;
			try {
				hcvDiagnoseDate = new SimpleDateFormat("yyyy-MM-dd").parse("14-04-2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			answerHCVDiagnosisDate.getValueDateType().setValue(hcvDiagnoseDate);
			
			listAnswerHCVDiagnosisDate.add(answerHCVDiagnosisDate);
			hcvDiagnosisDate.setAnswer(listAnswerHCVDiagnosisDate);
			hcv.addItem(hcvDiagnosisDate);
			//HCV placeProvider
			QuestionnaireResponseItemComponent hcvPlaceProvided = new QuestionnaireResponseItemComponent();
			hcvPlaceProvided.setLinkId(HivConst.QRLinkIdPlaceProvided);
			hcvPlaceProvided.setText("Place HBV provided");
			List<QuestionnaireResponseItemAnswerComponent> listAnswerHCVPlaceProvided = new ArrayList<QuestionnaireResponseItemAnswerComponent>();
			QuestionnaireResponseItemAnswerComponent answerHCVPlaceProvided = new QuestionnaireResponseItemAnswerComponent();
			answerHCVPlaceProvided.getValueStringType().setValue("BV Thai Nguyen");
			listAnswerHCVPlaceProvided.add(answerHCVPlaceProvided);
			hcvPlaceProvided.setAnswer(listAnswerHCVPlaceProvided);
			hcv.addItem(hcvPlaceProvided);
			hbcHcv.addItem(hcv);
			comorbidities.addItem(hbcHcv);
			
			
		listItem.add(patientInformation);
		listItem.add(riskFactors);
		listItem.add(hivDiagnosis);
		listItem.add(hivRecencyTest);
		listItem.add(cd4BeforeART);
		listItem.add(cd4DuringART);
		listItem.add(vl4DuringART);
		listItem.add(vl4DuringART);
		listItem.add(drugResistanceTest);
		listItem.add(drugResistanceTest);
		listItem.add(arvTreatment);
		listItem.add(comorbidities);
		questionnaireResponse.setItem(listItem);
		
//		bundle.setType(Bundle.BundleType.COLLECTION);
//
//		bundle.addEntry()
//		   .setFullUrl(questionnaireResponse.getIdElement().getValue())
//		   .setResource(questionnaireResponse);
		
		
		
		// PatientInfoDto patientInfoDto = QRConvertUtil.convertToBundle(questionnaireResponse);
		// bundle = QRExtractionServiceImpl.savePatientTransaction(patientInfoDto.getPatient(), patientInfoDto.getChildren(),patientInfoDto.getListCoMorbidity());
		// parser.setPrettyPrint(true);
		// String body = parser.encodeResourceToString(bundle);
		// System.out.println(body);
		// PatientInfoDto patientInfoDto = QRConvertUtil.convertToBundle(questionnaireResponse);
		// bundle = QRExtractionServiceImpl.savePatientTransaction(patientInfoDto.getPatient(), patientInfoDto.getChildren(),patientInfoDto.getListCoMorbidity(),patientInfoDto.getPregnancies());
		// parser.setPrettyPrint(true);
		// String body = parser.encodeResourceToString(bundle);
		// System.out.println(body);
	}
	
public static void testSendQR() {
		QuestionnaireResponse qr = new QuestionnaireResponse();
		qr.setSubject(new Reference());
		HivPatient patient = new HivPatient();
		HumanName name = patient.addName();
		name.addGiven("Van Tuan");
		name.setFamily("Nguyen");
		qr.getSubject().setResource(patient);
		QuestionnaireResponseItemComponent item = qr.addItem();
		item.setLinkId("patient-information");

		QuestionnaireResponseItemComponent itemName = item.addItem();
		itemName.setLinkId("name");
		QuestionnaireResponseItemAnswerComponent nameAnswer = itemName.addAnswer();
		nameAnswer.setValue(new StringType("Nguyen Van Dung"));

		QuestionnaireResponseItemComponent child = item.addItem();
		child.setLinkId("unique_arv_number");
		QuestionnaireResponseItemAnswerComponent arvIdAnswer = child.addAnswer();
		arvIdAnswer.setValue(new StringType("123455666"));

		child = item.addItem();
		child.setLinkId(HivConst.QRLinkIdIdentification);
		QuestionnaireResponseItemAnswerComponent answer = child.addAnswer();
		answer.setValue(new StringType("P0121091201"));
		
		QuestionnaireResponseItemComponent riskFactor = qr.addItem();
		riskFactor.setLinkId(HivConst.QRLinkIdRiskFactor);
		QuestionnaireResponseItemComponent riskPopulation= riskFactor.addItem();
		riskPopulation.setLinkId(HivConst.QRLinkIdRiskPopulation);
//		QuestionnaireResponseItemAnswerComponent answer = riskPopulation.addAnswer();
		answer = riskPopulation.addAnswer();
		QuestionnaireResponseItemComponent riskPopulationAnswer=answer.addItem();
		answer.setValue(new Coding("http://openhie.org/fhir/CodeSystem/cs-patient-gender", "male", "Male"));
		
		
		FhirContext ctx = FhirContext.forR4();
		IParser parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);
//		String body = parser.encodeResourceToString(qr);
//		System.out.println(body);

		PatientInfoDto patientInfoDto = QRConvertUtil.convertToBundle(qr);
		Bundle bundle =HAPIFhirHivPatientService.savePatientTransaction(patientInfoDto.getPatient(), patientInfoDto.getChildren());
//		String body = parser.encodeResourceToString(bundle);
		String body = parser.encodeResourceToString(qr);






		System.out.println(body);

//		PatientInfoDto patientInfoDto = QRConvertUtil.convertToBundle(qr);
//		Bundle bundle =HAPIFhirHivPatientService.savePatientTransaction(patientInfoDto.getPatient(), patientInfoDto.getChildren());
//		String bodyBundle = parser.encodeResourceToString(bundle);
//		System.out.println(bodyBundle);

	}
///////
	public static Bundle getPatientBundle(int pageIndex, int pageSize,String text) {
		int pagesoffset= (pageIndex-1)*pageSize;
	      FhirContext ctx = FhirContext.forR4();
	      //serverBaseUrl="http://hapi.fhir.org/baseR4";
	      IGenericClient client = ctx.newRestfulGenericClient(HivConst.serverFhirUrl);
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
	public static void main(String[] args) {
		//testSendQR();
		testSendQRFull();
//		Bundle response = getPatientBundle(1, 10,"Nguyen");
//		System.out.print(response.getTotal());
	}
	public static void test() {
		HivPatient patient = new HivPatient();
		patient.setEthnicity(new CodeableConcept());
		patient.getEthnicity().addCoding(new Coding("http", "Test", "For test only"));
		  patient.addName()
		     .setFamily("Jameson")
		     .addGiven("J")
		     .addGiven("Jonah");
		  Address address = patient.addAddress();
		  address.setCity("Hà nội");
		  address.addLine("So nha 67 ngach 23 ngo 120 Yen Lang");
		  address.setPostalCode("10000");
		  address.setCityElement(new StringType("HN"));
		  // Create an observation object
		  Observation observation = new Observation();
		  observation.setStatus(Observation.ObservationStatus.FINAL);
		  observation
		     .getCode()
		        .addCoding()
		           .setSystem("http://loinc.org")
		           .setCode("789-8")
		           .setDisplay("Erythrocytes [#/volume] in Blood by Automated count");
		  observation.setValue(
		     new Quantity()
		        .setValue(4.12)
		        .setUnit("10 trillion/L")
		        .setSystem("http://unitsofmeasure.org")
		        .setCode("10*12/L"));
		  List<Resource> obs= new ArrayList<Resource>();
		  obs.add(observation);
		  Condition cond = new Condition();
		  cond.setOnset(DateTimeType.now());
		  obs.add(cond);

		  CarePlan carePlan = new CarePlan();
		  carePlan.setIdBase("Test-CarePlan");
		  obs.add(carePlan);

		  Bundle resp= HAPIFhirHivPatientService.savePatientTransaction(patient,obs);
	}

}

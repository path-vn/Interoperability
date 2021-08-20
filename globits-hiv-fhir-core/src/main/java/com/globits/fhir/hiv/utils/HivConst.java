package com.globits.fhir.hiv.utils;

public class HivConst {
//	public static String serverFhirUrl = "http://fhircs.globits.net:8082/fhir";
	public static int fhirSocketTimeout = 30000;
	public static Boolean IsUsingEncodeData=true;
	public static String serverFhirUrl = "http://fhir.globits.net:7070/hapi/fhir";
	public static String HIVChildPatient = "http://openhie.org/fhir/openhie.vn.case-reporting.hiv/StructureDefinition/HIVChildPatient";
	public static String HivPatientProfile = "http://openhie.org/fhir/openhie.vn.case-reporting.hiv/StructureDefinition/hiv-patient";
	
	public static String IndexCaseNationalId12IdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/index-case-national-id-12";
	public static String IndexCaseNationalId9IdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/index-case-national-id-9";
	public static String IndexCaseUidIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/index-case-uid";
	public static String IndexTestingIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/index-testing";
	
	public static String PassportIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-passport-id";
	public static String InsuranceIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-insurance-id";
	public static String ArvIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-art-id";
	public static String CaseIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-case-id";
	public static String DriverLicenseIdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-driver-license";
	public static String National9IdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-national-id-9";
	public static String National12IdentifierNamingSystem = "http://openhie.org/fhir/NamingSystem/ns-national-id-12";
	public static String VsPatientGenderIdentity = "http://openhie.org/fhir/hiv-casereporting/ValueSet/vs-patient-gender-identity";
	public static String VsGoveningUnitName = "http://openhie.org/fhir/hiv-casereporting/ValueSet/vs-govening-unit-name";
	public static String VsEthnicity = "http://openhie.org/fhir/openhie.vn.case-reporting.hiv/StructureDefinition/ext-vn-ethnicity";
	public static String VsDiagnosisObs = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-diagnosis-observation";
	public static String VsHivRiskFactor = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/vs-hiv-risk-factor";
	public static String CsHivRiskFactor = "http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-risk-factor";

	public static String VsHivRiskBehavior = "http://openhie.org/fhir/hiv-casereporting/ValueSet/vs-hiv-risk-behavior";
	public static String CsHivRiskBehavior = "http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-risk-behavior";
	public static String CsHivPopulation = "http://openhie.org/fhir/CodeSystem/cs-hiv-population";
	public static String CsHivTransmissionRoute = "http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-transmission-route";
	public static String VsHivRapidTest = "http://openhie.org/fhir/hiv-casereporting/ValueSet/hiv-rapid-test-results";
	public static String VsHivVLTest = "http://openhie.org/fhir/hiv-casereporting/ValueSet/hiv-vl-test-results";
	public static String CsCauseOfDeath = "http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-causeofdeath";
	public static String CsTreatmentStatus = "http://openhie.org/fhir/hiv-casereporting/CodeSystem/cs-hiv-treatmentstatus";
	public static String HivCd4TestObservation = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-cd4-test-observation";
	public static String HivCd4DuringArtObservation = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-cd4-during-art-observation";
	public static String HivVl4DuringArtObservation = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-vl4-during-art-observation";
	public static String HivDuringResistanceTestObservation = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-during-resistance-test-observation";
	public static String DrugResistanceTest = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/drug-resistance-test";
	public static String HivHcv = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-hcv";
	public static String HivHbv = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-hbv";
	public static String CsHiv1stLineStarted = "http://openhie.org/fhir/CodeSystem/cs-1st-line-started";
	public static String CsHiv2ndLineStarted = "http://openhie.org/fhir/CodeSystem/cs-2st-line-started";
	public static String CsHiv3stLineStarted = "http://openhie.org/fhir/CodeSystem/cs-3st-line-started";
	public static String ARVTreatment = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/arv-treatment";
	public static String HivTpt = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-tpt";
	public static String HivTb = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-tb";
	public static String VsOrganizationUnitName = "http://openhie.org/fhir/hiv-casereporting/ValueSet/vs-organization_unit_name";
	public static String PregnancyChild = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy_child";
	public static String VsHivRecency = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-recency-test";
	public static String VsHivPregnancy = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy";
	public static String VsHivOutcome = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/pregnancy-outcome";
	public static String CsHivRegimen = "http://openhie.org/fhir/CodeSystem/regimen";
	
	
	public static String QRLinkIdTitle = "title";
	public static String QRTextTitle = "HIV case report";
	
	public static String QRLinkIdInstructions = "instructions";
	public static String QRTextInstructions = "Reporting instructions: Monthly - applicable to each patient during the reporting period";

	public static String QRLinkIdReportDate = "report_date";
	public static String QRTextReportDate = "Date of report";

	public static String QRLinkIdQuestions = "questions";
	public static String QRTextQuestions = "Monthly report";

	public static String QRLinkIdPatientIdentification = "patient_identification";
	public static String QRTextPatientIdentification = "Part I: Identifying information";

	public static String QRLinkIdPatientArvNumber = "patient_arv_nr";
	public static String QRTextPatientArvNumber = "ARV patient number";

	public static String QRLinkIdCaseUid = "case_uid";
	public static String QRTextCaseUid = "Case UID";

	public static String QRLinkIdIndexTesting = "index_testing";
	public static String QRTextIndexTesting = "Index testing";

	public static String QRLinkIdIndexCaseNationalId12 = "index_case_national_id12";
	public static String QRTextIndexCaseNationalId12 = "Link to index national id 12 digits";

	public static String QRLinkIdIndexCaseNationalId9 = "index_case_national_id9";
	public static String QRTextIndexCaseNationalId9 = "Link to index national id 9 digits";

	public static String QRLinkIdIndexCaseUID = "index_case_uid";
	public static String QRTextIndexCaseUID = "Link to index hivinfo case id";

	public static String QRLinkIdPersonalInformation = "personal_information";
	public static String QRTextPersonalInformation = "Personal information";

	public static String QRLinkIdPatientFullName = "fullname";
	public static String QRTextPatientFullName = "Full name";

	public static String QRLinkIdEthnicity = "ethnicity";
	public static String QRTextEthnicity = "Vietnam ethnicity";

	public static String QRLinkIdGender = "gender";
	public static String QRTextGender = "Gender";

	public static String QRLinkIdYearOfBirth = "birth_date";
	public static String QRTextYearOfBirth = "Date of birth";

	public static String QRLinkIdIdentification = "identification";
	public static String QRTextIdentification = "Identification";

	public static String QRLinkIdNationalId12 = "national_id12";
	public static String QRTextNationalId12 = "National ID 12 digits";
	
	public static String QRLinkIdNationalId9 = "national_id9";
	public static String QRTextNationalId9 = "National ID 9 digits";

	public static String QRLinkIdHealthInsuranceNumber = "insurance_nr";
	public static String QRTextHealthInsuranceNumber = "Health insurance number";

	public static String QRLinkIdInsuranceExpDate = "insurance_exp_date";
	public static String QRTextInsuranceExpDate = "insurance_exp_date";

	public static String QRLinkIdPassportNumber = "passport_nr";
	public static String QRTextPassportNumber = "Passport number";

	public static String QRLinkIdDriverLicense = "driver_license";
	public static String QRTextDriverLicense = "Driver license";

	public static String QRLinkIdResidence = "residence";
	public static String QRTextResidence = "Residence";
	
	public static String QRLinkIdCurrentResidence = "current_residence";
	public static String QRTextCurrentResidence = "Current residence";
	
	public static String QRLinkIdPermanentResidence = "permanent_residence";
	public static String QRTextPermanentResidence = "Permanent residence";

	public static String QRLinkIdCountry = "country";
	public static String QRTextCountry = "Country";

	public static String QRLinkIdProvince = "province";
	public static String QRTextProvince = "Province";

	public static String QRLinkIdDistrict = "district";
	public static String QRTextDistrict = "District";

	public static String QRLinkIdCommune = "commune";
	public static String QRTextCommune = "Commune";

	public static String QRLinkIdAddress = "address";
	public static String QRTextAddress = "Street address";

	public static String QRLinkIdOccupation = "occupation";
	public static String QRTextOccupation = "Occupation";

	public static String QRLinkIdRiskFactor = "risk_factors";
	public static String QRTextRiskFactor = "Risk factors";

	public static String QRLinkIdPopulationGroup = "population_group";
	public static String QRTextPopulationGroup = "Population group";

	public static String QRLinkIdRiskBehavior = "risk_behavior";
	public static String QRTextRiskBehavior = "Risk behavior";

	public static String QRLinkIdTransmissionRoute = "transmission_route";
	public static String QRTextTransmissionRoute = "Transmission route";
	
	public static String QRLinkIdOrganizationUnitName = "organization_unit_name";
	public static String QRTextOrganizationUnitName = "Synchronized Organization";

	public static String QRLinkIdUnitName = "unit_name";
	public static String QRLinkIdPatientName = "name";
	public static String QRLinkIdPatientFamilyName = "family-name";
	public static String QRLinkIdPatientGivenName = "given-name";

	public static String QRTextCd4TestResult = "Result of CD4 test";
	public static String QRTextCd4TestDateTestPerformed = "Date of CD4 test";
	public static String QRTextVlTestDateTestPerformed = "Date of viral load test";
	public static String QRTextVlTestResult = "Result of viral load test";

	public static String QRLinkIdTestResultOther = "test_result_other";
	public static String QRTextVlTestResultOther = "Result of other outcome";

	public static String QRTextDrugResistanceTestDateSpecimenCollected = "Date of specimen collection";

	public static String QRTextDrugResistanceTestResult = "Drug resistance test result";
	public static String QRTextDrugResistanceTestDateTestPerformed = "Date test results performed";

	public static String QRLinkIdHivRecencyTest = "hiv_recency_test";
	public static String QRTextHivRecencyTest = "HIV recency test";

	public static String QRLinnkIdRecencyRapidTest = "rapid_test";
	public static String QRTextRecencyRapidTest = "Rapid test for recent infection";

	public static String QRTextLastestDateTestPerformed = "Date test result performed";
	public static String QRTextRapidTestResult = "Rapid test result";
	public static String QRTextHivRecencyTestVlTestDateTestPerformed = "Date of VL test performance";

	public static String QRTextHivRecencyTestResult = "VL test result (copies/mL)";
	public static String QRTextHivRecencyTestResultOther = "Other of viral load test (undetectable)";

	public static String QRLinkIdRecentInfectionConclusion = "recent_infection_conclusion";
	public static String QRTextRecentInfectionConclusion = "Recent infection conclusion";

	public static String QRLinkIdInitiationDate = "initiation_date";
	public static String QRTextInitiationDate = "Date of ARV treatment initiation";

	public static String QRLinkIdArvTreatmentStopDate = "treatment_stop_date";
	public static String QRTextArvTreatmentStopDate = "Date of treatment stop at facility";
	
	public static String QRLinkIdEnrollmentDate = "enrollment_date";
	public static String QRTextEnrollmentDate = "Date of ARV Treatment registration";
	
	public static String QRLinkIdEnrollmentType = "enrollment_type";
	public static String QRTextEnrollmentType = "Reason for ARV treatment registration";

	public static String QRLinkIdArvTreatmentEnrollmentFacility = "enrollment_facility";
	public static String QRTextArvTreatmentEnrollmentFacility = "Name of ARV treatment facility";

	public static String QRLinkIdArvTreatmentInitiationFacility = "initiation_facility";
	public static String QRTextArvTreatmentInitiationFacility = "Name of ARV treatment initiation facility";
	
	public static String QRLinkIdFacilityTransferIn = "facility_transfer_in";
	public static String QRTextFacilityTransferIn = "Transferred-in facility (previous facility)";
	
	public static String QRLinkIdDateNextAppointment = "date_next_appointment";
	public static String QRTextDateNextAppointment = "Date of next appointment";
	
	public static String QRLinkIdDateLastExamination = "date_last_examination";
	public static String QRTextDateLastExamination = "Date of last examination";

	public static String QRTextTPT = "TPT";
	public static String QRTextDateTPTStarted = "Date TPT started";
	public static String QRTextDateTPTEnded = "Date TPT ended";

	public static String QRLinkIdLocation = "location";
	public static String QRLinkIdPlaceTPTProvided = "Place TPT provided";

	public static String QRLinkIdTptCompleted = "tpt_completed";
	public static String QRTextTptCompleted = "TPT completed";

	public static String QRTextTbTreatment = "TPT completed";

	public static String QRLinkIdTbDiagnosisDate = "tb_diagnosis_date";
	public static String QRTextTbDiagnosisDate = "TB diagnosis date";
	public static String QRLinkIdArvTreatmentStopReason = "treatment_stop_reason";
	public static String QRTextArvTreatmentStopReason = "Reason to stop treatment at facility";
	public static String QRTextTbTreatmentDateStarted = "TB treatment date started";
	public static String QRTextTbTreatmentDateEnded = "TB treatment date ended";
	public static String QRLinkIdPlaceTbTreatmentProvided = "Place TB treatment provided";

	public static String QRTextHbv = "HBV";
	public static String QRTextHbvDiagnosisDate = "Date of diagnosis test";
	public static String QRTextHbvDiagnosisResult = "Result of diagnosis test";
	public static String QRTextHbvDateOfTreatmentStart = "Date of HBV treatment start";
	public static String QRTextHbvDateOfTreatmentStop = "Date of HBV treatment stopped";

	public static String QRTextHcv = "HCV";
	public static String QRTextHcvDiagnosisDate = "Date of HCV diagnosis test";
	public static String QRTextHcvDiagnosisResult = "Result of HCV dianosis test";
	public static String QRTextHcvDateOfTreatmentStart = "Date of HCV treatment start";
	public static String QRTextHcvDateOfTreatmentStop = "Date of HCV treatment stopped";

	public static String QRLinkIdDiagnosisResult = "diagnosis_result";

	public static String QRLinkIdPregnancyOutcomes = "outcomes";
	public static String QRTextPregnancyOutcomes = "Pregnancy outcome";
	public static String QRTextPregnancyOutcomeCode = "Pregnancy outcome code";

	public static String QRLinkIdPregnancyOutcomesChild = "child";
	public static String QRTextPregnancyOutcomesChild = "Births";
	

	public static String QRLinkIdPregnancyBirthWeight = "birth_weight";
	public static String QRTextPregnancyBirthWeight= "Child weight at birth";
	
	public static String QRLinkIdPregnancyBirthDefects= "birth_defects";
	public static String QRTextPregnancyBirthDefects= "Birth defects";
	
	public static String QRLinkIdPregnancyHivStatus= "hiv_status";
	public static String QRTextPregnancyHivStatus= "HIV status";
	
//	public static String QRLinkIdPregnancyHivDiagnosisDate= "hiv_diagnosis_date";
//	public static String QRTextPregnancyHivDiagnosisDate= "	Child HIV diagnosis date";
	
	public static String QRLinkIdHivDiagnosisDate = "hiv_diagnosis_date";
	public static String QRLinkIdChildHivDiagnosisDate = "Child HIV diagnosis date";
	
	public static String QRLinkIdChildArvStartDate = "child_arv_start_date";
	public static String QRTextChildArvStartDate = "Child ARV start date";

	public static String QRLinkIdWhoStage = "who_stage";
	public static String QRTextWhoStage = "WHO Stage";

	public static String QRLinkIdWhoClinicalStage = "who_clinical_stage";
	public static String QRTextWhoClinicalStage = "WHO clinical stage";

//	public static String QRLinkIdDateWhoStage = "date_who_stage";
//	public static String QRTextDateWhoStage = "Date of WHO clinical stage";

	public static String QRLinkIdArvRegimen = "arv_regimen";
	public static String QRTextArvRegimen = "ARV treatment regimen";

	public static String QRLinkIdArvRegimenDateStarted = "date_regimen_started";
	public static String QRTextArvRegimenDateStarted = "Date regimen started";

	public static String QRLinkIdArvRegimenDateStopped = "date_regimen_stopped";
	public static String QRTextArvRegimenDateStopped = "Date regimen stopped";

	public static String QRLinkIdArvRegimenName = "regimen_name";
	public static String QRTextArvRegimenName = "Regimen name";
	
	public static String QRLinkIdArvRegimenLine = "regimen_line";
	public static String QRTextArvRegimenLine = "Regimen line";
	
	public static String QRLinkIdArvRegimenChangeReason = "regimen_change_reason";
	public static String QRTextArvRegimenChangeReason = "Reason to change regimen";
	
public static String QRLinkIdDateWhoStage = "date_who_stage";
	public static String QRTextDateWhoStage = "Date of WHO clinical stage";	//public static String QRLinkIdCurrentResidenceStressAddress = "current_stress_address";
	//public static String QRLinkIdCurrentResidenceCommune = "current_commune";
	//public static String QRLinkIdCurrentResidenceDistrict = "current_district";
	//public static String QRLinkIdCurrentResidenceProvince = "current_province";
	//public static String QRLinkIdCurrentResidenceCountry = "current_country";
	//public static String QRLinkIdPermanentResidence = "permanent";
	//public static String QRLinkIdPermanentResidenceStressAddress = "permanent_stress_address";
	//public static String QRLinkIdPermanentResidenceCommune = "permanent_commune";
	//public static String QRLinkIdPermanentResidenceDistrict = "permanent_district";
	//public static String QRLinkIdPermanentResidenceProvince = "permanent_province";
	//public static String QRLinkIdPermanentResidenceCountry = "permanent_country";

	public static String QRLinkIdRiskPopulation = "risk_population";
	public static String QRLinkIdTransmssionRoute = "transmssion_route";
	public static String QRLinkIdHivDiagnosis = "hiv-diagnosis";
	public static String QRLinkIdDateOfConfirmation = "date-of-confirmation";
	public static String QRLinkIdConfirmingLab = "confirming-lab";
	public static String QRLinkIdRecencyVlTest = "vl-Test";
	public static String QRLinkIdRecencyRecentInfectionConclusion = "recent_infection_conclusion";
	public static String QRLinkIdDateOfTestPerformance = "date_of_test_performance";
	public static String QRLinkIdVlRecencyTestResult = "test_result";
	public static String QRLinkIdVlRecencyTestResultOther = "test_result_other";
	// public static String QRLinkIdCd4BeforeArt = "cd4-before-art";
	public static String QRLinkIdDateOfSpecimenCollection = "date-of-specimen-collection";
	public static String QRLinkIdPlaceOfSpecimenCollection = "place-of-specimen-collection";
	public static String QRLinkIdTestResult = "test_result";
	// public static String QRLinkIdCd4DuringArt = "cd4-during-art";
	public static String QRLinkIdVl4DuringArt = "vl4-during-art";
	public static String QRLinkIdDrugResistanceTest = "drug_resistance_test";
	public static String QRLinkIdArvTreatment = "arv_treatment";
	public static String QRLinkIdDateOfTreatmentStart = "treatment_start_date";
	public static String QRLinkIdDateOfTreatmentStop = "treatment_stop_date";
	public static String QRLinkIdPlaceOfInitiation = "place-of-initiation";

	public static String QRLinkIdCd4Test = "cd4_test";
	public static String QRTextCd4Test = "CD4 test results";
	public static String QRLinkIdVlTest = "vl_test";
	public static String QRLinkIdLastestTestReason = "test_reason";
	public static String QRLinkIdLastestDateSpecimen = "date_specimen_collected";
	public static String QRTextLastestDateSpecimen = "Date of specimen collection for CD4 test";
	public static String QRLinkIdLastestPlaceSpecimen = "place_specimen_collected";
	public static String QRLinkIdLastestDateTestPerformed = "date_test_performed";
	public static String QRLinkIdLastestTestResult = "test_result";
	public static String QRLinkIdHistory = "history";
	public static String QRLinkIdTestReason = "test_reason";
	public static String QRTextVlDateSpecimenCollected = "Date of Specimen Collection for viral load test";	
//	public static String QRLinkIdTestResultOther = "test_result_other";
	public static String QRLinkIdDateOfLossToFollowUp = "date-of-loss-to-follow-up";
	public static String QRLinkIdDateOfTransferredOut = "date-of-transferred-out";
	public static String QRLinkIdPlaceOfTransferredOut = "place-of-transferred-out";
	
	public static String QRLinkIdComorbidities = "comorbidities";
	public static String QRLinkIdTuberculosis = "tuberculosis";
	public static String QRLinkIdTpt = "tpt";
	public static String QRLinkIdDateStarted = "date_started";
	public static String QRLinkIdDateEnded = "date_ended";
	public static String QRLinkIdDateCompleted = "date-completed";
	public static String QRLinkIdPlaceProvided = "place-provided";
	public static String QRLinkIdTbTreatment = "tb_treatment";
	public static String QRLinkIdHbvHcv = "hbv_hcv";
	public static String QRLinkIdHbv = "hbv";
	public static String QRLinkIdHcv = "hcv";
	public static String QRLinkIdDiagnosisDate = "diagnosis_date";
	public static String QRLinkIdPregnancies = "pregnancy";
	public static String QRLinkIdDateReported = "date_reported";
	public static String QRLinkIdDeliveryPlace = "delivery_place";
	public static String QRLinkIdPlaceReported = "place_reported";
	public static String QRLinkIdPregnancyOutcomeCode = "outcome_code";
	public static String QRLinkIdDeliveryDate = "delivery_date";
	public static String QRLinkIdGestationalAgeAtDelivery = "gestational_age_at_delivery";
	
	public static String QRLinkIdDatePregnancyReported = "date-pregnancy-reported";
	public static String QRLinkIdChildDeliveryPlace ="child-delivery-place";
	public static String QRLinkIdChildDateOfBirth = "child-date-of-birth";
	public static String QRLinkIdChildBirthWeight = "birthWeight";
	public static String QRLinkIdHivStatus = "hivStatus";
	public static String QRLinkIdChildHIVDiagnosisDate = "child-hiv-diagnosis-date";
	public static String QRLinkIdDeath = "death";
	public static String QRLinkIdDateOfDeath = "date";
	public static String QRLinkIdCauseOfDeath = "cause";
	
	public static String QRLinkIdRegimen = "regimen";
	public static String QRLinkIdDate1stLineStarted = "date1stLineStarted";
	public static String QRLinkIdDate2ndLineStarted = "date2ndLineStarted";
	public static String QRLinkIdDate3rdLineStarted = "date3rdLineStarted";

	public static final String QRTextCd4DuringArt = "CD4 test during ART";
	public static final String QRTextCd4BeforeArt = "CD4 test before ART";
	public static final String QRTextVl4DuringArt = "Viral Load test during ART";
	public static final String QRTextVlTest = "Viral load test (First time and follow-up)";
	public static final String QRTextDrugResistanceTest = "Drug resistance test";
	public static final String QRTextArvTreatment = "ARV Treatment";
	public static final String QRTextComorbidities = "Comorbidities";
	public static final String QRTextTuberculosis = "Tuberculosis";
	public static final String QRTextHbvHcv = "HBV and HCV";
	
	public static final String QRCodeSystemCd4BeforeArt = "http://example.org/CodeSystem/casereporting-hiv-vn-casereport-answeroptions#pre-art";
	public static final String QRCodeSystemCd4DuringArt = "http://example.org/CodeSystem/casereporting-hiv-vn-casereport-answeroptions#routine";
	public static final String QRCodeSystemCd4Other = "http://example.org/CodeSystem/casereporting-hiv-vn-casereport-answeroptions#other";
	public static final String VN_COUNTRY_CODE = "1";
	public static final String VN_COUNTRY_DISPLAY = "Việt Nam";
	
	public enum synOrg{
		HivInfo(1),
		OpcAssist(2),
		OpcEclinica(3),
		HtcElog(4),
		Pdma(5),
		;
		private Integer value;
		private synOrg(int value) {
		    this.value = value;
		}
		public Integer getValue() {
			return value;
		}
	}
	
	public enum RegimenLine{
		Regimen1stLineStarted(1),
		Regimen2ndLineStarted(2),
		Regimen3rdLineStarted(3),
		;
		private Integer value;
		private RegimenLine(int value) {
		    this.value = value;
		}
	
		public Integer getValue() {
			return value;
		}
	}
	public enum  PatientGenderIdentity{
		TransgenderFemale("transgender-to-female"),
		TransgenderMale("transgender-to-male"),
		NonBinary("non-binary"),
		Male("male"),
		Female("female"),
		Other("other"),
		NotDisclosed("not-disclosed"),
		;
		private String value;
		private PatientGenderIdentity(String value) {
		    this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}
	
	
	
}

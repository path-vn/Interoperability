package com.globits.hiv.receive.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.globits.hiv.receive.dto.AddressDto;
import com.globits.hiv.receive.dto.ArvTreatmentDto;
import com.globits.hiv.receive.dto.CoMorbidityDto;
import com.globits.hiv.receive.dto.CoMorbidityTreatmentDto;
import com.globits.hiv.receive.dto.EthnicityDto;
import com.globits.hiv.receive.dto.HbvDto;
import com.globits.hiv.receive.dto.HivCaseReportDto;
import com.globits.hiv.receive.dto.HivDiagnosisDto;
import com.globits.hiv.receive.dto.HivRecencyTestDto;
import com.globits.hiv.receive.dto.LabTestDto;
import com.globits.hiv.receive.dto.OccupationDto;
import com.globits.hiv.receive.dto.OrganizationDto;
import com.globits.hiv.receive.dto.PatientDto;
import com.globits.hiv.receive.dto.PatientRegimenDto;
import com.globits.hiv.receive.dto.SystemCodeDto;
import com.globits.hiv.receive.dto.TuberculosisDto;
import com.globits.hiv.receive.valueset.EthnicityValueSet;
import com.globits.hiv.receive.valueset.GenderValueSet;
import com.globits.hiv.receive.valueset.LabTestReasonValueSet;
import com.globits.hiv.receive.valueset.RecentInfectionConclusionValueSet;
import com.globits.hiv.receive.valueset.RegimenLineValueSet;
import com.globits.hiv.receive.valueset.RegimenValueSet;
import com.globits.hiv.receive.valueset.RiskBehaviorValueSet;
import com.globits.hiv.receive.valueset.RiskPopulationValueSet;
import com.globits.hiv.receive.valueset.TransmissionRouteValueSet;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class Test {
    public static void main(String[] args) throws ParseException {

//         RestTemplate rest = new RestTemplate();
//         // Create Patient
//         HivCaseReportDto reportDto = new HivCaseReportDto();
//         reportDto.setTitle("Test");
//         PatientDto patientDto = new PatientDto();
//         // Sync Org
//         SystemCodeDto facility = generateSystemCode("000", "Test", "");

//         patientDto.setFacility(facility);
//         // Url Id
//         patientDto.setCaseId("9c4e3134-0056-4443-ab2d-ccbc12d9b9af");
//         // name
//         patientDto.setName("Le Trong Nghia 2872021");
//         patientDto.setFirstName("Le Trong");
//         patientDto.setLastName("Nghia");
//         // birthDate
//         Date birthDate = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-1975");
//         patientDto.setBirthDate(birthDate);
//         // Ethnicity
//         patientDto.setEthnicity(EthnicityValueSet.KINH);
//         // artID
//         patientDto.setArvNumber("ARV-0123456789");
//         // nationalID
//         patientDto.setNationalID9("039871254");
//         // healthInsuranceNumber
//         patientDto.setInsuranceNumber("02M85U74");
//         // passportNumber
//         patientDto.setPassportNumber("952037p512");
//         // current Address
//         AddressDto currentAddress = new AddressDto();
//         currentAddress.setLine("44 117 Thai Ha");
//         currentAddress.setCommuneCode("1010917");
//         currentAddress.setCommuneName("Trung Liet");
//         currentAddress.setDistrictCode("10109");
//         currentAddress.setDistrictName("Dong đa");
//         currentAddress.setCityCode("101");
//         currentAddress.setCityName("Ha Noi");
//         currentAddress.setCountryCode("1");
//         currentAddress.setCountryName("Viet Nam");
//         currentAddress.setTypeCode("01");
//         currentAddress.setText("44 117 Thai Ha, Trung Liet, Dong Đa, Ha Noi, Viet Nam");
//         patientDto.setCurrentAddress(currentAddress);
//         // registeredAddress
//         AddressDto registeredAddress = new AddressDto();
//         registeredAddress.setLine("44 117 Thai Ha");
//         registeredAddress.setCommuneCode("1010917");
//         registeredAddress.setCommuneName("Trung Liet");
//         registeredAddress.setDistrictCode("10109");
//         registeredAddress.setDistrictName("Dong đa");
//         registeredAddress.setCityCode("101");
//         registeredAddress.setCityName("Ha Noi");
//         registeredAddress.setCountryCode("1");
//         registeredAddress.setCountryName("Viet Nam");
//         registeredAddress.setTypeCode("02");
//         registeredAddress.setText("44 117 Thai Ha, Trung Liet, Dong Đa, Ha Noi, Viet Nam");
//         patientDto.setRegisteredAddress(registeredAddress);
//         // gender
//         patientDto.setGender(GenderValueSet.MALE);
//         // occupation
//         OccupationDto occupation = new OccupationDto();
//         occupation.setCode("gov_empl");
//         occupation.setDisplay("Government employee");
//         patientDto.setOccupation(occupation);

//         // cd4List
//         patientDto.setCd4List(mockupListCd4());
//         // viralLoad
//         patientDto.setViralLoadList(mockupListVl());
//         // HivDiagnosis
//         patientDto.setDiagnosis(mockupHivDiagnosis());
//         // recency
//         patientDto.setHivRecencyTest(mockupRecencyTest());
//         // arv treatment
//         patientDto.setListOfArvTreatment(mockupArvTreatment());

//         reportDto.setPatientDto(patientDto);
        
//         RestTemplate restTemplate = new RestTemplate();
// 		HttpHeaders headers = new HttpHeaders();
// 		String access_token = "ec978f5f-f828-4963-b796-f360b0a8d3d4";
// 		headers.add("Authorization", "Bearer" + " " + access_token.trim());
// 		headers.add("Accept", "application/json");
// 		headers.add("Content-Type", "application/json");
// 		HttpEntity<HivCaseReportDto> entity = new HttpEntity<HivCaseReportDto>(reportDto, headers);
	
// //		ResponseEntity<HivCaseReportDto> response = restTemplate.exchange("http://localhost:3555/receive/api/case_report/", HttpMethod.POST, entity, HivCaseReportDto.class);
// 		Gson gson = new Gson();
// 	    String jsonInString = gson.toJson(reportDto);
// 	    System.out.println(jsonInString);
// 		restTemplate.postForObject("http://localhost:3555/receive/public/case_report", entity, String.class);
        
//        rest.postForObject("http://localhost:3555/receive/public/case_report", reportDto, String.class);
    }

    // public static LabTestDto gererateLabTest(LabTestReasonValueSet testReason, String specimenCollectionDate,
    //         OrganizationDto specimenCollectionPlace, String testPerformanceDate, Integer valueNumber,
    //         SystemCodeDto testResultOther) {
    //     LabTestDto labTest = new LabTestDto();
    //     // labTest.setName(name);
    //     Date labTestDate = null;
    //     try {
    //         labTestDate = (new SimpleDateFormat("dd-MM-yyyy")).parse(specimenCollectionDate);
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     labTest.setTestReason(testReason);
    //     labTest.setTestPerformanceDate(labTestDate);
    //     labTest.setSpecimenCollectionDate(labTestDate);
    //     labTest.setSpecimenCollectionPlace(specimenCollectionPlace);
    //     labTest.setValueNumber(valueNumber);
    //     return labTest;
    // }

    // public static List<LabTestDto> mockupListCd4() {
    //     List<LabTestDto> listCd4 = new ArrayList<>();
    //     OrganizationDto specimenCollectionPlace = generateOrg("12", "TTYT Dong Da");
    //     LabTestDto cd40 = gererateLabTest(LabTestReasonValueSet.BEFORE_ART, "20-08-2002", specimenCollectionPlace, "",
    //             250, null);
    //     listCd4.add(cd40);
    //     LabTestDto cd41 = gererateLabTest(LabTestReasonValueSet.ROUTINE_TEST, "20-11-2002", specimenCollectionPlace, "",
    //             160, null);
    //     listCd4.add(cd41);
    //     LabTestDto cd42 = gererateLabTest(LabTestReasonValueSet.ROUTINE_TEST, "20-01-2003", specimenCollectionPlace, "",
    //             148, null);
    //     listCd4.add(cd42);
    //     LabTestDto cd43 = gererateLabTest(LabTestReasonValueSet.ROUTINE_TEST, "20-03-2003", specimenCollectionPlace, "",
    //             278, null);
    //     listCd4.add(cd43);
    //     return listCd4;
    // }

    // public static List<LabTestDto> mockupListVl() {
    //     List<LabTestDto> listViralLoad = new ArrayList<>();
    //     OrganizationDto specimenCollectionPlace = generateOrg("12", "TTYT Dong Da");
    //     LabTestDto vl1 = gererateLabTest(LabTestReasonValueSet.ROUTINE_6, "20-08-2002", specimenCollectionPlace, "",
    //             380, null);
    //     listViralLoad.add(vl1);
    //     LabTestDto vl2 = gererateLabTest(LabTestReasonValueSet.ROUTINE_6, "20-2-2003", specimenCollectionPlace, "", 390,
    //             null);
    //     listViralLoad.add(vl2);
    //     LabTestDto vl3 = gererateLabTest(LabTestReasonValueSet.ROUTINE_6, "20-8-2003", specimenCollectionPlace, "", 489,
    //             null);

    //     listViralLoad.add(vl3);
    //     return listViralLoad;
    // }

    // public static HivDiagnosisDto mockupHivDiagnosis() {
    //     HivDiagnosisDto hivDiagnosis = new HivDiagnosisDto();
    //     Date confirmatoryDate = null;
    //     try {
    //         confirmatoryDate = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-2002");
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     hivDiagnosis.setConfirmatoryDate(confirmatoryDate);
    //     OrganizationDto confirmatoryLab = new OrganizationDto();
    //     confirmatoryLab.setCode("12");
    //     confirmatoryLab.setName("TTYT Dong Da");
    //     hivDiagnosis.setConfirmatoryLab(confirmatoryLab);
    //     hivDiagnosis.setPlaceOfSpecimenCollection("TTYT Dong Da");
    //     Date specimenCollectionDate = null;
    //     try {
    //         specimenCollectionDate = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-2002");
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     hivDiagnosis.setSpecimenCollectionDate(specimenCollectionDate);
    //     hivDiagnosis.setRiskPopulation(RiskPopulationValueSet.MSM);
    //     hivDiagnosis.setRiskBehavior(RiskBehaviorValueSet.MSM);
    //     hivDiagnosis.setTransmissionRoute(TransmissionRouteValueSet.SEXUAL_TRANSMISSION);
    //     return hivDiagnosis;
    // }

    // public static HivRecencyTestDto mockupRecencyTest() {
    //     HivRecencyTestDto rencency = new HivRecencyTestDto();
    //     OrganizationDto specimenCollectionPlace = generateOrg("20", "TTYT Thai Nguyen");
    //     LabTestDto rapidTest = gererateLabTest(null, "20-08-2002", specimenCollectionPlace, "", 380, null);
    //     rencency.setRapidTest(rapidTest);
    //     rencency.setRecentInfectionConclusion(RecentInfectionConclusionValueSet.RECENT);
    //     return rencency;
    // }

    // public static SystemCodeDto generateSystemCode(String code, String display, String definition) {
    //     SystemCodeDto systemCode = new SystemCodeDto();
    //     systemCode.setCode(code);
    //     systemCode.setDisplay(display);
    //     systemCode.setDefinition(definition);
    //     return systemCode;
    // }

    // public static OrganizationDto generateOrg(String code, String name) {

    //     OrganizationDto org = new OrganizationDto();
    //     org.setCode(code);
    //     org.setName(name);
    //     return org;

    // }

    // public static List<TuberculosisDto> mockupListTB() {

    //     List<TuberculosisDto> list = new ArrayList<>();
    //     TuberculosisDto dto = new TuberculosisDto();
    //     Date date = null;
    //     try {
    //         date = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-2003");
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     dto.setDiagnosisDate(date);
    //     List<CoMorbidityTreatmentDto> treatments = new ArrayList<>();
    //     CoMorbidityTreatmentDto treatment = new CoMorbidityTreatmentDto();
    //     Date start = null;
    //     try {
    //         start = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-2003");
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     treatment.setStartDate(start);
    //     Date end = null;
    //     try {
    //         end = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-2004");
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     treatment.setEndDate(end);
    //     OrganizationDto placeProvided = generateOrg("12", "TTYT Dong Da");
    //     treatment.setPlaceProvided(placeProvided);

    //     list.add(dto);
    //     return list;
    // }

    // public static List<ArvTreatmentDto> mockupArvTreatment() {
    //     List<ArvTreatmentDto> arvTreatments = new ArrayList<>();
    //     ArvTreatmentDto item = new ArvTreatmentDto();
    //     item.setArvRegimens(mockupRegimen());
    //     arvTreatments.add(item);
    //     return arvTreatments;
    // }

    // public static List<PatientRegimenDto> mockupRegimen() {
    //     List<PatientRegimenDto> regimens = new ArrayList<>();
    //     PatientRegimenDto item = new PatientRegimenDto();
    //     item.setName(RegimenValueSet.REG001);
    //     item.setLine(RegimenLineValueSet.LVL1);
    //     Date dateRegimenStarted = null;
    //     try {
    //         dateRegimenStarted = (new SimpleDateFormat("dd-MM-yyyy")).parse("20-08-2002");
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //     }
    //     item.setDateRegimenStarted(dateRegimenStarted);
    //     regimens.add(item);
    //     return regimens;
    // }
}

package com.globits.hiv.receive.dto;

import java.util.Date;
import java.util.List;

import com.globits.hiv.receive.valueset.EthnicityValueSet;
import com.globits.hiv.receive.valueset.GenderValueSet;

public class PatientDto {
	public static final String dateFormat="yyyyDDD'T'HHmmss.SSSZ";
/*
 * For HIV Patient in Vietnam
 */
	private String caseId;
	private SystemCodeDto facility;
	private String hivInfoCaseId;
	private String name;
	private String lastName;
	private String firstName;
	private Date birthDate;
	private SystemCodeDto ethnicity;
	private String arvNumber;
	private String nationalID12;
	private String nationalID9;
	private String insuranceNumber;
	private Date insuranceExpDate;
	private String passportNumber;
	private String driverLicense;
	private AddressDto currentAddress;
	private AddressDto registeredAddress;
	private OccupationDto occupation;
	private List<LabTestDto> cd4List;//CD4 test during ART (first time and follow-up)
	private List<LabTestDto> viralLoadList;//Routine viral load test during ART (first time and follow-up)
	
	private List<LabTestDto> drugResistanceList;
	private SystemCodeDto gender;
	private HivDiagnosisDto diagnosis;

	private HivRecencyTestDto hivRecencyTest;

	private PatientDeathDto death;
	private String text;//ghi chus
	//index testing
	private IndexTestingDto indexTesting;

	/// comorbidities
	private List<HbvDto> listOfHbv;
	private List<HcvDto> listOfHcv;
	private List<TuberculosisDto> tuberculosis;
	
	////
	private List<ArvTreatmentDto> listOfArvTreatment;

	private List<PregnancyDto> pregnancies;
	
	
	public HivDiagnosisDto getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(HivDiagnosisDto diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public SystemCodeDto getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(SystemCodeDto ethnicity) {
		this.ethnicity = ethnicity;
	}
	
	public String getNationalID12() {
		return nationalID12;
	}
	public void setNationalID12(String nationalID12) {
		this.nationalID12 = nationalID12;
	}
	public String getNationalID9() {
		return nationalID9;
	}
	public void setNationalID9(String nationalID9) {
		this.nationalID9 = nationalID9;
	}
	
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public Date getInsuranceExpDate() {
		return insuranceExpDate;
	}
	public void setInsuranceExpDate(Date insuranceExpDate) {
		this.insuranceExpDate = insuranceExpDate;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public AddressDto getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(AddressDto currentAddress) {
		this.currentAddress = currentAddress;
	}
	public AddressDto getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(AddressDto registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public OccupationDto getOccupation() {
		return occupation;
	}
	public void setOccupation(OccupationDto occupation) {
		this.occupation = occupation;
	}
	public List<LabTestDto> getCd4List() {
		return cd4List;
	}
	public void setCd4List(List<LabTestDto> cd4List) {
		this.cd4List = cd4List;
	}
	
	public List<LabTestDto> getViralLoadList() {
		return viralLoadList;
	}
	public void setViralLoadList(List<LabTestDto> viralLoadList) {
		this.viralLoadList = viralLoadList;
	}
	
	public HivRecencyTestDto getHivRecencyTest() {
		return hivRecencyTest;
	}
	public void setHivRecencyTest(HivRecencyTestDto hivRecencyTest) {
		this.hivRecencyTest = hivRecencyTest;
	}
	public String getArvNumber() {
		return arvNumber;
	}
	public void setArvNumber(String arvNumber) {
		this.arvNumber = arvNumber;
	}
	
	public String getHivInfoCaseId() {
		return hivInfoCaseId;
	}
	public void setHivInfoCaseId(String hivInfoCaseId) {
		this.hivInfoCaseId = hivInfoCaseId;
	}
	public SystemCodeDto getGender() {
		return gender;
	}
	public void setGender(SystemCodeDto gender) {
		this.gender = gender;
	}
	
	public PatientDeathDto getDeath() {
		return death;
	}
	public void setDeath(PatientDeathDto death) {
		this.death = death;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	public List<HbvDto> getListOfHbv() {
		return listOfHbv;
	}
	public void setListOfHbv(List<HbvDto> listOfHbv) {
		this.listOfHbv = listOfHbv;
	}
	
	public List<HcvDto> getListOfHcv() {
		return listOfHcv;
	}
	public void setListOfHcv(List<HcvDto> listOfHcv) {
		this.listOfHcv = listOfHcv;
	}
	public List<ArvTreatmentDto> getListOfArvTreatment() {
		return listOfArvTreatment;
	}
	public void setListOfArvTreatment(List<ArvTreatmentDto> listOfArvTreatment) {
		this.listOfArvTreatment = listOfArvTreatment;
	}
	public List<LabTestDto> getDrugResistanceList() {
		return drugResistanceList;
	}
	public void setDrugResistanceList(List<LabTestDto> drugResistanceList) {
		this.drugResistanceList = drugResistanceList;
	}
	public SystemCodeDto getFacility() {
		return facility;
	}
	public void setFacility(SystemCodeDto facility) {
		this.facility = facility;
	}
	public String getDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	public IndexTestingDto getIndexTesting() {
		return indexTesting;
	}
	public void setIndexTesting(IndexTestingDto indexTesting) {
		this.indexTesting = indexTesting;
	}
	public List<TuberculosisDto> getTuberculosis() {
		return tuberculosis;
	}
	public void setTuberculosis(List<TuberculosisDto> tuberculosis) {
		this.tuberculosis = tuberculosis;
	}
	public List<PregnancyDto> getPregnancies() {
		return pregnancies;
	}
	public void setPregnancies(List<PregnancyDto> pregnancies) {
		this.pregnancies = pregnancies;
	}
	
	
}

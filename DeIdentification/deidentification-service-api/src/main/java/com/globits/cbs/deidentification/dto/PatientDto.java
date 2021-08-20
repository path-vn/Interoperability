package com.globits.cbs.deidentification.dto;

import java.util.Date;
import java.util.List;

public class PatientDto {
	public static final String dateFormat="yyyyDDD'T'HHmmss.SSSZ";
/*
 * For HIV Patient in Vietnam
 */
	private String id;
	private List<SystemCodeDto> syncOrg;
	private SystemCodeDto lastUpdated;
	private String urlID;
	private String name;
	private String lastName;
	private String firstName;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+7")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern = Constant.DateFormat)
	private Date birthDate;
	private EthnicityDto ethnicity;
	private String artID;
	private String nationalID;
	private String healthInsuranceNumber;
	private String passportNumber;
	private AddressDto currentAddress;
	private AddressDto registeredAddress;
	private OccupationDto occupation;
	private IndexTestingDto indexTesting;
	private List<SystemCodeDto> riskPopulations;
	private List<SystemCodeDto> riskBehaviors;
	private List<SystemCodeDto> transmissionRoutes;
	private List<PatientRegimenDto> regimens;
	private List<LabTestDto> cd4List;//CD4 test during ART (first time and follow-up)
	private List<LabTestDto> viralLoadList;//Routine viral load test during ART (first time and follow-up)
	//private List<PatientTransferDto> transferInfo;
	private List<LabTestDto> drugResistanceList;
	private SystemCodeDto gender;
	private HivDiagnosisDto diagnosis;
	
	private LabTestDto rapidRecencyTest;
	
	private LabTestDto viralLoadRecencyTest;
	
	private LabTestDto cd4BeforeART;
	private SystemCodeDto treatmentStatus;
	private PatientDeathDto death;
	private String text;//ghi chus
	/// comorbidities
	private List<CoMorbidityDto> listOfHbv;
	private List<CoMorbidityDto> listOfHcv;
	private List<CoMorbidityDto> listOfTb;
	private List<CoMorbidityTreatmentDto> listOfTpt;
	
	private List<PregnancyDto> listPregnancy;
	
	////
	private List<ArvTreatmentDto> listOfArvTreatment;
	
	private Date lastUpdateDate;
	
	
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
	public EthnicityDto getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(EthnicityDto ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getNationalID() {
		return nationalID;
	}
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}
	public String getHealthInsuranceNumber() {
		return healthInsuranceNumber;
	}
	public void setHealthInsuranceNumber(String healthInsuranceNumber) {
		this.healthInsuranceNumber = healthInsuranceNumber;
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
	public List<SystemCodeDto> getRiskPopulations() {
		return riskPopulations;
	}
	public void setRiskPopulations(List<SystemCodeDto> riskPopulations) {
		this.riskPopulations = riskPopulations;
	}
	public List<SystemCodeDto> getRiskBehaviors() {
		return riskBehaviors;
	}
	public void setRiskBehaviors(List<SystemCodeDto> riskBehaviors) {
		this.riskBehaviors = riskBehaviors;
	}
	public List<SystemCodeDto> getTransmissionRoutes() {
		return transmissionRoutes;
	}
	public void setTransmissionRoutes(List<SystemCodeDto> transmissionRoutes) {
		this.transmissionRoutes = transmissionRoutes;
	}

	public List<PatientRegimenDto> getRegimens() {
		return regimens;
	}
	public void setRegimens(List<PatientRegimenDto> regimens) {
		this.regimens = regimens;
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
	public LabTestDto getRapidRecencyTest() {
		return rapidRecencyTest;
	}
	public void setRapidRecencyTest(LabTestDto rapidRecencyTest) {
		this.rapidRecencyTest = rapidRecencyTest;
	}
	public LabTestDto getViralLoadRecencyTest() {
		return viralLoadRecencyTest;
	}
	public void setViralLoadRecencyTest(LabTestDto viralLoadRecencyTest) {
		this.viralLoadRecencyTest = viralLoadRecencyTest;
	}
	public String getArtID() {
		return artID;
	}
	public void setArtID(String artID) {
		this.artID = artID;
	}
	public String getUrlID() {
		return urlID;
	}
	public void setUrlID(String urlID) {
		this.urlID = urlID;
	}
	public SystemCodeDto getGender() {
		return gender;
	}
	public void setGender(SystemCodeDto gender) {
		this.gender = gender;
	}
	public LabTestDto getCd4BeforeART() {
		return cd4BeforeART;
	}
	public void setCd4BeforeART(LabTestDto cd4BeforeART) {
		this.cd4BeforeART = cd4BeforeART;
	}
	
	public SystemCodeDto getTreatmentStatus() {
		return treatmentStatus;
	}
	public void setTreatmentStatus(SystemCodeDto treatmentStatus) {
		this.treatmentStatus = treatmentStatus;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public List<PatientTransferDto> getTransferInfo() {
//		return transferInfo;
//	}
//	public void setTransferInfo(List<PatientTransferDto> transferInfo) {
//		this.transferInfo = transferInfo;
//	}
	public List<CoMorbidityDto> getListOfHbv() {
		return listOfHbv;
	}
	public void setListOfHbv(List<CoMorbidityDto> listOfHbv) {
		this.listOfHbv = listOfHbv;
	}
	public List<CoMorbidityDto> getListOfHcv() {
		return listOfHcv;
	}
	public void setListOfHcv(List<CoMorbidityDto> listOfHcv) {
		this.listOfHcv = listOfHcv;
	}
	public List<CoMorbidityDto> getListOfTb() {
		return listOfTb;
	}
	public void setListOfTb(List<CoMorbidityDto> listOfTb) {
		this.listOfTb = listOfTb;
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
	public List<CoMorbidityTreatmentDto> getListOfTpt() {
		return listOfTpt;
	}
	public void setListOfTpt(List<CoMorbidityTreatmentDto> listOfTpt) {
		this.listOfTpt = listOfTpt;
	}
	public List<SystemCodeDto> getSyncOrg() {
		return syncOrg;
	}
	public void setSyncOrg(List<SystemCodeDto> syncOrg) {
		this.syncOrg = syncOrg;
	}
	public SystemCodeDto getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(SystemCodeDto lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public List<PregnancyDto> getListPregnancy() {
		return listPregnancy;
	}
	public void setListPregnancy(List<PregnancyDto> listPregnancy) {
		this.listPregnancy = listPregnancy;
	}
	public IndexTestingDto getIndexTesting() {
		return indexTesting;
	}
	public void setIndexTesting(IndexTestingDto indexTesting) {
		this.indexTesting = indexTesting;
	}
	
	
}

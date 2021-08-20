package com.globits.hivimportcsadapter.dto;

import java.util.Date;
import java.util.List;
public class HtcElogDto {
	private String name;
	private String lastName;
	private String firstName;
	private Date birthDate;
	private EthnicityDto ethnicity;
	private String artID;
	private String nationalID;
	private String healthInsuranceNumber;
	private String passportNumber;
	private AddressDto currentAddress;
	private AddressDto registeredAddress;
	private SystemCodeDto occupationName;
	private List<SystemCodeDto> riskPopulations;
	private List<SystemCodeDto> riskBehaviors;
	private List<SystemCodeDto> transmissionRoutes;
	private SystemCodeDto gender;
	private HivDiagnosisDto diagnosis;
	private HivRecencyTestDto hivRecencyTest;
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
	public String getArtID() {
		return artID;
	}
	public void setArtID(String artID) {
		this.artID = artID;
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
	
	public SystemCodeDto getOccupationName() {
		return occupationName;
	}
	public void setOccupationName(SystemCodeDto occupationName) {
		this.occupationName = occupationName;
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
	public SystemCodeDto getGender() {
		return gender;
	}
	public void setGender(SystemCodeDto gender) {
		this.gender = gender;
	}
	public HivDiagnosisDto getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(HivDiagnosisDto diagnosis) {
		this.diagnosis = diagnosis;
	}
	public HivRecencyTestDto getHivRecencyTest() {
		return hivRecencyTest;
	}
	public void setHivRecencyTest(HivRecencyTestDto hivRecencyTest) {
		this.hivRecencyTest = hivRecencyTest;
	}
	
	
}

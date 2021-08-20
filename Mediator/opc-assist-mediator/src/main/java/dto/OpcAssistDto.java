package dto;

import java.time.LocalDateTime;
import java.util.List;

public class OpcAssistDto {
	private Long caseId;
	private Long personId;
	private LocalDateTime lastSynDate;
	private String fullname;
	private String ethnic;
	private Integer gender;
	private String dob;
	private String passportNumber;
	private String nationalIdNumber;
	private String permanentStressAddress;
	private String permanentCommune;
	private String permanentCommuneCode;
	private String permanentDistrict;
	private String permanentDistrictCode;
	private String permanentProvince;
	private String permanentProvinceCode;
	private String permanentCountry;
	private String permanentCountryCode;
	private String currentStressAddress;
	private String currentCommune;
	private String currentCommuneCode;
	private String currentDistrict;
	private String currentDistrictCode;
	private String currentProvince;
	private String currentProvinceCode;
	private String currentCountry;
	private String currentCountryCode;
	private String occupation;
	private String hivConfirmationDate;
	private String confirmLab;
	
	private String dateOfLossToFollowUp;
	private String dateOfTransferredOut;
	private String placeTransferredOut;
	private String dateOfDeath;
	private String causeOfDeath;
	
	// private List<HivRecencyDto> listRecency;
	// private List<HivRiskFactorDto> listRiskPopulation;
	private HivRecencyDto recency;
	private HivRiskFactorDto riskPopulation;
	// private List<HivCd4Dto> listCd4DuringArt;
	// private List<HivCd4Dto> listHivCd4BeforeArt;
	private List<HivCd4Dto> listHivCd4;
	private List<HivViralLoadDto> listHivViralLoad;
	private List<HivDrugResistanceDto> listDrugResistance;
	private List<HivArvTreatmentDto> listArvTreatment;
	
	private List<HivTBTreatmentDto> listTBTreatment;
	private List<HivTBProphylaxisDto> listTBProphylaxis;
	private List<HivHepatitisDto> listHbv;
	private List<HivHepatitisDto> listHcv;
	private List<HivPregnancyDto> listPregnancy;
	
	
	public Long getCaseId() {
		return caseId;
	}
	public List<HivViralLoadDto> getListHivViralLoad() {
		return listHivViralLoad;
	}
	public void setListHivViralLoad(List<HivViralLoadDto> listHivViralLoad) {
		this.listHivViralLoad = listHivViralLoad;
	}
	public List<HivCd4Dto> getListHivCd4() {
		return listHivCd4;
	}
	public void setListHivCd4(List<HivCd4Dto> listHivCd4) {
		this.listHivCd4 = listHivCd4;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public LocalDateTime getLastSynDate() {
		return lastSynDate;
	}

	public void setLastSynDate(LocalDateTime lastSynDate) {
		this.lastSynDate = lastSynDate;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	public String getPermanentStressAddress() {
		return permanentStressAddress;
	}

	public void setPermanentStressAddress(String permanentStressAddress) {
		this.permanentStressAddress = permanentStressAddress;
	}

	public String getPermanentCommune() {
		return permanentCommune;
	}

	public void setPermanentCommune(String permanentCommune) {
		this.permanentCommune = permanentCommune;
	}

	public String getPermanentDistrict() {
		return permanentDistrict;
	}

	public void setPermanentDistrict(String permanentDistrict) {
		this.permanentDistrict = permanentDistrict;
	}

	public String getPermanentProvince() {
		return permanentProvince;
	}

	public void setPermanentProvince(String permanentProvince) {
		this.permanentProvince = permanentProvince;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public String getCurrentStressAddress() {
		return currentStressAddress;
	}

	public void setCurrentStressAddress(String currentStressAddress) {
		this.currentStressAddress = currentStressAddress;
	}

	public String getCurrentCommune() {
		return currentCommune;
	}

	public void setCurrentCommune(String currentCommune) {
		this.currentCommune = currentCommune;
	}

	public String getCurrentDistrict() {
		return currentDistrict;
	}

	public void setCurrentDistrict(String currentDistrict) {
		this.currentDistrict = currentDistrict;
	}

	public String getCurrentProvince() {
		return currentProvince;
	}

	public void setCurrentProvince(String currentProvince) {
		this.currentProvince = currentProvince;
	}

	public String getCurrentCountry() {
		return currentCountry;
	}

	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getHivConfirmationDate() {
		return hivConfirmationDate;
	}

	public void setHivConfirmationDate(String hivConfirmationDate) {
		this.hivConfirmationDate = hivConfirmationDate;
	}

	public String getConfirmLab() {
		return confirmLab;
	}

	public void setConfirmLab(String confirmLab) {
		this.confirmLab = confirmLab;
	}

	// public List<HivRiskFactorDto> getListRiskPopulation() {
	// 	return listRiskPopulation;
	// }

	// public void setListRiskPopulation(List<HivRiskFactorDto> listRiskPopulation) {
	// 	this.listRiskPopulation = listRiskPopulation;
	// }

	// public List<HivCd4Dto> getListCd4DuringArt() {
	// 	return listCd4DuringArt;
	// }

	// public void setListCd4DuringArt(List<HivCd4Dto> listCd4DuringArt) {
	// 	this.listCd4DuringArt = listCd4DuringArt;
	// }

	// public List<HivCd4Dto> getListHivCd4BeforeArt() {
	// 	return listHivCd4BeforeArt;
	// }

	// public void setListHivCd4BeforeArt(List<HivCd4Dto> listHivCd4BeforeArt) {
	// 	this.listHivCd4BeforeArt = listHivCd4BeforeArt;
	// }

	// public List<HivViralLoadDto> getListHivViralLoadDuringArt() {
	// 	return listHivViralLoadDuringArt;
	// }

	// public void setListHivViralLoadDuringArt(List<HivViralLoadDto> listHivViralLoadDuringArt) {
	// 	this.listHivViralLoadDuringArt = listHivViralLoadDuringArt;
	// }

		

	public List<HivDrugResistanceDto> getListDrugResistance() {
		return listDrugResistance;
	}

	public HivRiskFactorDto getRiskPopulation() {
		return riskPopulation;
	}
	public void setRiskPopulation(HivRiskFactorDto riskPopulation) {
		this.riskPopulation = riskPopulation;
	}
	public void setListDrugResistance(List<HivDrugResistanceDto> listDrugResistance) {
		this.listDrugResistance = listDrugResistance;
	}

	public List<HivArvTreatmentDto> getListArvTreatment() {
		return listArvTreatment;
	}

	public void setListArvTreatment(List<HivArvTreatmentDto> listArvTreatment) {
		this.listArvTreatment = listArvTreatment;
	}

	public List<HivTBTreatmentDto> getListTBTreatment() {
		return listTBTreatment;
	}

	public void setListTBTreatment(List<HivTBTreatmentDto> listTBTreatment) {
		this.listTBTreatment = listTBTreatment;
	}

	public List<HivTBProphylaxisDto> getListTBProphylaxis() {
		return listTBProphylaxis;
	}

	public void setListTBProphylaxis(List<HivTBProphylaxisDto> listTBProphylaxis) {
		this.listTBProphylaxis = listTBProphylaxis;
	}

	public List<HivHepatitisDto> getListHbv() {
		return listHbv;
	}

	public void setListHbv(List<HivHepatitisDto> listHbv) {
		this.listHbv = listHbv;
	}

	public List<HivHepatitisDto> getListHcv() {
		return listHcv;
	}

	public void setListHcv(List<HivHepatitisDto> listHcv) {
		this.listHcv = listHcv;
	}

	public List<HivPregnancyDto> getListPregnancy() {
		return listPregnancy;
	}

	public void setListPregnancy(List<HivPregnancyDto> listPregnancy) {
		this.listPregnancy = listPregnancy;
	}

	// public List<HivRecencyDto> getListRecency() {
	// 	return listRecency;
	// }

	// public void setListRecency(List<HivRecencyDto> listRecency) {
	// 	this.listRecency = listRecency;
	// }
	public String getDateOfLossToFollowUp() {
		return dateOfLossToFollowUp;
	}
	public void setDateOfLossToFollowUp(String dateOfLossToFollowUp) {
		this.dateOfLossToFollowUp = dateOfLossToFollowUp;
	}
	public String getDateOfTransferredOut() {
		return dateOfTransferredOut;
	}
	public void setDateOfTransferredOut(String dateOfTransferredOut) {
		this.dateOfTransferredOut = dateOfTransferredOut;
	}
	public String getPlaceTransferredOut() {
		return placeTransferredOut;
	}
	public void setPlaceTransferredOut(String placeTransferredOut) {
		this.placeTransferredOut = placeTransferredOut;
	}
	public String getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	public String getPermanentCommuneCode() {
		return permanentCommuneCode;
	}
	public void setPermanentCommuneCode(String permanentCommuneCode) {
		this.permanentCommuneCode = permanentCommuneCode;
	}
	public String getPermanentDistrictCode() {
		return permanentDistrictCode;
	}
	public void setPermanentDistrictCode(String permanentDistrictCode) {
		this.permanentDistrictCode = permanentDistrictCode;
	}
	public String getPermanentProvinceCode() {
		return permanentProvinceCode;
	}
	public void setPermanentProvinceCode(String permanentProvinceCode) {
		this.permanentProvinceCode = permanentProvinceCode;
	}
	public String getPermanentCountryCode() {
		return permanentCountryCode;
	}
	public void setPermanentCountryCode(String permanentCountryCode) {
		this.permanentCountryCode = permanentCountryCode;
	}
	public String getCurrentCommuneCode() {
		return currentCommuneCode;
	}
	public void setCurrentCommuneCode(String currentCommuneCode) {
		this.currentCommuneCode = currentCommuneCode;
	}
	public String getCurrentDistrictCode() {
		return currentDistrictCode;
	}
	public void setCurrentDistrictCode(String currentDistrictCode) {
		this.currentDistrictCode = currentDistrictCode;
	}
	public String getCurrentProvinceCode() {
		return currentProvinceCode;
	}
	public void setCurrentProvinceCode(String currentProvinceCode) {
		this.currentProvinceCode = currentProvinceCode;
	}
	public String getCurrentCountryCode() {
		return currentCountryCode;
	}
	public void setCurrentCountryCode(String currentCountryCode) {
		this.currentCountryCode = currentCountryCode;
	}
	public HivRecencyDto getRecency() {
		return recency;
	}
	public void setRecency(HivRecencyDto recency) {
		this.recency = recency;
	}
	
	
}

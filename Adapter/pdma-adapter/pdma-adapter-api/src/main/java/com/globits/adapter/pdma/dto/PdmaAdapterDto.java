package com.globits.adapter.pdma.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.globits.adapter.pdma.domain.HTSCase;
import com.globits.adapter.pdma.domain.HTSCaseRiskGroup;
import com.globits.adapter.pdma.domain.PNSCaseContact;

public class PdmaAdapterDto {
	private Long caseId;
	private Long personId;
	private LocalDateTime lastSynDate;
	private String fullname;
	private String ethnic;
	private Integer gender;
	private String dob;
	private String passportNumber;
	private String nationalIdNumber;
	private String healthNumber;
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


	private String dateOfDeath;
	private String causeOfDeath;

//	private List<HivRecencyDto> listRecency;
//	private List<HivRiskFactorDto> listRiskPopulation;
	private HivRecencyDto recency;
	private HivRiskFactorDto riskPopulation;
	
	public PdmaAdapterDto() {
		super();
	}
	public PdmaAdapterDto(PNSCaseContact caseEntity) {
		if(caseEntity != null) {
			if (caseEntity.getPerson().getId() != null) {
				this.personId = caseEntity.getPerson().getId();
			}
			if (caseEntity.getModifyDate() != null) {
				this.lastSynDate = caseEntity.getModifyDate();
			}
			if (caseEntity.getPerson().getFullname() != null) {
				this.fullname = caseEntity.getPerson().getFullname();
			}
			if (caseEntity.getPerson().getEthnic() != null) {
				this.ethnic = caseEntity.getPerson().getEthnic().getValue();
			}
			if (caseEntity.getPerson().getGender() != null) {
				this.gender = caseEntity.getPerson().getGender().getNumber();
			}
			if (caseEntity.getPerson().getDob() != null) {
				this.dob = caseEntity.getPerson().getDob().toString();
			}
			if (caseEntity.getPerson().getPassportNumber() != null) {
				this.passportNumber = caseEntity.getPerson().getPassportNumber();
			}
			if (caseEntity.getPerson().getNidNumber() != null) {
				this.nationalIdNumber = caseEntity.getPerson().getNidNumber();
			}
			if(caseEntity.getProvince() != null) {
				this.currentProvince = caseEntity.getProvince().getName();
				this.currentProvinceCode = caseEntity.getProvince().getCodeGso();
			}
			if(caseEntity.getDistrict() != null ) {
				this.currentDistrict = caseEntity.getDistrict().getName();
				this.currentDistrictCode = caseEntity.getDistrict().getCodeGso();
			}
			if(caseEntity.getAddressDetail() != null) {
				this.currentStressAddress = caseEntity.getAddressDetail();
			}
			if(caseEntity.getC8().getNumber() == 1) {
				if(caseEntity.getPnsCase() != null && caseEntity.getPnsCase().getC2()!= null) {
					this.confirmLab = caseEntity.getPnsCase().getC2().getName();
					this.hivConfirmationDate = caseEntity.getPnsCase().getC10().toString();
				}
				
			}else if(caseEntity.getC8().getNumber() == 2) {
				this.confirmLab = caseEntity.getC8LabtestOrg();
				this.hivConfirmationDate = caseEntity.getC8LabtestDate().toString();
			}
		}
	}
	public PdmaAdapterDto(HTSCase caseEntity) {
		if(caseEntity != null) {
			if (caseEntity.getModifyDate() != null) {
				this.lastSynDate = caseEntity.getModifyDate();
			}
			if (caseEntity.getC23FullName() != null) {
				this.fullname = caseEntity.getC23FullName();
			}
			if(caseEntity.getC23IdNumber()!= null) {
				this.nationalIdNumber = caseEntity.getC23IdNumber();
			}
			if(caseEntity.getC23HealthNumber()!= null) {
				this.healthNumber = caseEntity.getC23HealthNumber();
			}
			if (caseEntity.getC23Ethnic() != null) {
				this.ethnic = caseEntity.getC23Ethnic().getValue();
			}
			if (caseEntity.getC7() != null) {
				this.gender = caseEntity.getC7().getNumber();
			}
			if (caseEntity.getC8() != null) {
				this.dob = caseEntity.getC8().toString();
			}
			if(caseEntity.getC23Profession()!= null) {
				this.occupation = caseEntity.getC23Profession().getValue();
			}
			if(caseEntity.getC23ResidentAddressProvince()!= null) {
				this.permanentProvince = caseEntity.getC23ResidentAddressProvince().getName();
				this.permanentProvinceCode = caseEntity.getC23ResidentAddressProvince().getCodeGso();
			}
			if(caseEntity.getC23ResidentAddressDistrict()!= null) {
				this.permanentDistrict = caseEntity.getC23ResidentAddressDistrict().getName();
				this.permanentDistrictCode = caseEntity.getC23ResidentAddressDistrict().getCodeGso();
			}
			if(caseEntity.getC23ResidentAddressCommune()!= null) {
				this.permanentCommune = caseEntity.getC23ResidentAddressCommune().getName();
				this.permanentCommuneCode = caseEntity.getC23ResidentAddressCommune().getCodeGso();
			}
			if(caseEntity.getC23ResidentAddressDetail()!= null) {
				this.permanentStressAddress = caseEntity.getC23ResidentAddressDetail();
			}
			//
			if(caseEntity.getC23CurrentAddressProvince()!= null) {
				this.currentProvince= caseEntity.getC23CurrentAddressProvince().getName();
				this.currentProvinceCode = caseEntity.getC23CurrentAddressProvince().getCodeGso();
			}
			if(caseEntity.getC23CurrentAddressDistrict()!= null) {
				this.currentDistrict = caseEntity.getC23CurrentAddressDistrict().getName();
				this.currentDistrictCode = caseEntity.getC23CurrentAddressDistrict().getCodeGso();
			}
			if(caseEntity.getC23CurrentAddressCommune()!= null) {
				this.currentCommune = caseEntity.getC23CurrentAddressCommune().getName();
				this.currentCommuneCode = caseEntity.getC23CurrentAddressCommune().getCodeGso();
			}
			if(caseEntity.getC23CurrentAddressDetail()!= null) {
				this.currentStressAddress = caseEntity.getC23CurrentAddressDetail();
			}
			//
			if(caseEntity.getC19() != null) {
				this.confirmLab = caseEntity.getC19();
			}
			if(caseEntity.getC19Date() != null) {
				this.hivConfirmationDate = caseEntity.getC19Date().toString();
			}
			if(caseEntity.getC9() != null && caseEntity.getC9().size()>0) {
				List<HTSCaseRiskGroup> listC12 = new ArrayList<HTSCaseRiskGroup>(caseEntity.getC9());
				this.riskPopulation.setCode(listC12.get(0).getVal().getNumber()+"");
				this.riskPopulation.setDisplay(listC12.get(0).getVal().getDescription());
				this.riskPopulation.setDefinition(listC12.get(0).getVal().getDescription());
			}
		}
	}
	
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public LocalDateTime getLastSynDate() {
		return lastSynDate;
	}
	public void setLastSynDate(LocalDateTime lastSynDate) {
		this.lastSynDate = lastSynDate;
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
	public String getPermanentCommuneCode() {
		return permanentCommuneCode;
	}
	public void setPermanentCommuneCode(String permanentCommuneCode) {
		this.permanentCommuneCode = permanentCommuneCode;
	}
	public String getPermanentDistrict() {
		return permanentDistrict;
	}
	public void setPermanentDistrict(String permanentDistrict) {
		this.permanentDistrict = permanentDistrict;
	}
	public String getPermanentDistrictCode() {
		return permanentDistrictCode;
	}
	public void setPermanentDistrictCode(String permanentDistrictCode) {
		this.permanentDistrictCode = permanentDistrictCode;
	}
	public String getPermanentProvince() {
		return permanentProvince;
	}
	public void setPermanentProvince(String permanentProvince) {
		this.permanentProvince = permanentProvince;
	}
	public String getPermanentProvinceCode() {
		return permanentProvinceCode;
	}
	public void setPermanentProvinceCode(String permanentProvinceCode) {
		this.permanentProvinceCode = permanentProvinceCode;
	}
	public String getPermanentCountry() {
		return permanentCountry;
	}
	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}
	public String getPermanentCountryCode() {
		return permanentCountryCode;
	}
	public void setPermanentCountryCode(String permanentCountryCode) {
		this.permanentCountryCode = permanentCountryCode;
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
	public String getCurrentCommuneCode() {
		return currentCommuneCode;
	}
	public void setCurrentCommuneCode(String currentCommuneCode) {
		this.currentCommuneCode = currentCommuneCode;
	}
	public String getCurrentDistrict() {
		return currentDistrict;
	}
	public void setCurrentDistrict(String currentDistrict) {
		this.currentDistrict = currentDistrict;
	}
	public String getCurrentDistrictCode() {
		return currentDistrictCode;
	}
	public void setCurrentDistrictCode(String currentDistrictCode) {
		this.currentDistrictCode = currentDistrictCode;
	}
	public String getCurrentProvince() {
		return currentProvince;
	}
	public void setCurrentProvince(String currentProvince) {
		this.currentProvince = currentProvince;
	}
	public String getCurrentProvinceCode() {
		return currentProvinceCode;
	}
	public void setCurrentProvinceCode(String currentProvinceCode) {
		this.currentProvinceCode = currentProvinceCode;
	}
	public String getCurrentCountry() {
		return currentCountry;
	}
	public void setCurrentCountry(String currentCountry) {
		this.currentCountry = currentCountry;
	}
	public String getCurrentCountryCode() {
		return currentCountryCode;
	}
	public void setCurrentCountryCode(String currentCountryCode) {
		this.currentCountryCode = currentCountryCode;
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
	public HivRecencyDto getRecency() {
		return recency;
	}
	public void setRecency(HivRecencyDto recency) {
		this.recency = recency;
	}
	public HivRiskFactorDto getRiskPopulation() {
		return riskPopulation;
	}
	public void setRiskPopulation(HivRiskFactorDto riskPopulation) {
		this.riskPopulation = riskPopulation;
	}
	public String getHealthNumber() {
		return healthNumber;
	}
	public void setHealthNumber(String healthNumber) {
		this.healthNumber = healthNumber;
	}
	
}

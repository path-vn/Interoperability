package com.globits.adapter.pdma.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.globits.adapter.pdma.domain.Case;
import com.globits.adapter.pdma.domain.CaseOrg;
import com.globits.adapter.pdma.domain.ClinicalStage;
import com.globits.adapter.pdma.domain.HTSCase;
import com.globits.adapter.pdma.domain.HTSCaseRiskGroup;
import com.globits.adapter.pdma.domain.Hepatitis;
import com.globits.adapter.pdma.domain.LabTest;
import com.globits.adapter.pdma.domain.Location;
import com.globits.adapter.pdma.domain.PNSCase;
import com.globits.adapter.pdma.domain.PNSCaseContact;
import com.globits.adapter.pdma.domain.Pregnancy;
import com.globits.adapter.pdma.domain.Recency;
import com.globits.adapter.pdma.domain.RiskInterview;
import com.globits.adapter.pdma.domain.TBProphylaxis;
import com.globits.adapter.pdma.domain.TBTreatment;
import com.globits.adapter.pdma.domain.Treatment;
import com.globits.adapter.pdma.types.ClinicalTestingType;
import com.globits.adapter.pdma.types.PNSc8;
import com.globits.adapter.pdma.types.PatientStatus;

public class PdmaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8163283450390685768L;
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
	private String placeSpecimenCollected;

	private String dateOfLossToFollowUp;
	private String dateOfTransferredOut;
	private String placeTransferredOut;

	private String dateOfDeath;
	private String causeOfDeath;

//	private List<HivRecencyDto> listRecency;
//	private List<HivRiskFactorDto> listRiskPopulation;
	private HivRecencyDto recency;
	private HivRiskFactorDto riskPopulation;
	private List<HivCd4Dto> listHivCd4;
	private List<HivViralLoadDto> listHivViralLoad;
	private List<HivDrugResistanceDto> listDrugResistance;
	private List<HivArvTreatmentDto> listArvTreatment;

	private List<HivTBTreatmentDto> listTBTreatment;
	private List<HivTBProphylaxisDto> listTBProphylaxis;
	private List<HivHepatitisDto> listHbv;
	private List<HivHepatitisDto> listHcv;
	private List<HivPregnancyDto> listPregnancy;

	public PdmaDto() {

	}
	public PdmaDto(PNSCaseContact caseEntity) {
		if(caseEntity != null) {
			this.caseId = caseEntity.getId();
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
			if(caseEntity.getC8() != null && caseEntity.getC8().equals(PNSc8.answer1) ) {
				if(caseEntity.getPnsCase() != null && caseEntity.getPnsCase().getC2()!= null) {
					this.confirmLab = caseEntity.getPnsCase().getC2().getName();
					this.hivConfirmationDate = caseEntity.getPnsCase().getC10().toString();
				}
				
			}else if(caseEntity.getC8() != null && caseEntity.getC8().equals(PNSc8.answer2)) {
				this.confirmLab = caseEntity.getC8LabtestOrg();
				this.hivConfirmationDate = caseEntity.getC8LabtestDate().toString();
			}
		}
	}
	public PdmaDto(HTSCase caseEntity) {
		if(caseEntity != null) {
			this.caseId = caseEntity.getId();
			HivRecencyDto recencyDto = new HivRecencyDto();
			if (caseEntity.getModifyDate() != null) {
				this.lastSynDate = caseEntity.getModifyDate();
			}
			if(caseEntity.getC2()!= null) {
				this.placeSpecimenCollected = caseEntity.getC2().getName();
				recencyDto.setPlaceSpecimenCollected(caseEntity.getC2().getName());
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
				if(listC12.get(0)!= null && listC12.get(0).getVal() != null) {
				this.riskPopulation = new HivRiskFactorDto();
				this.riskPopulation.setCode(listC12.get(0).getVal().getNumber()+"");
				this.riskPopulation.setDisplay(listC12.get(0).getVal().getDescription());
				this.riskPopulation.setDefinition(listC12.get(0).getVal().getDescription());
				}
			}
			if(caseEntity.getC17() != null) {
				recencyDto.setTestResultOther(caseEntity.getC17().getDescription());
			}
			this.recency = recencyDto;
		}
	}
	public PdmaDto(Case caseEntity) {
		if (caseEntity != null) {
			this.caseId = caseEntity.getId();
			if (caseEntity.getCaseOrgs() != null) {
				for (CaseOrg caseOrg : caseEntity.getCaseOrgs()) {
					if (caseOrg.isLatestRelationship() && !caseOrg.isRefTrackingOnly()
							&& caseOrg.getStatus() == PatientStatus.DEAD) {
						if (caseOrg.getEndDate() != null) {
							this.dateOfDeath = caseOrg.getEndDate().toString();
						}
						if (caseOrg.getEndingReason() != null) {
							this.causeOfDeath = caseOrg.getEndingReason();
						}
					}

					else if (caseOrg.isLatestRelationship() && !caseOrg.isRefTrackingOnly()
							&& caseOrg.getStatus() == PatientStatus.TRANSFERRED_OUT) {
						if (caseOrg.getStartDate() != null) {
							this.dateOfTransferredOut = caseOrg.getStartDate().toString();
						}
						if (caseOrg.getOrganizationName() != null) {
							this.placeTransferredOut = caseOrg.getOrganizationName();
						}
					} else if (caseOrg.isLatestRelationship() && !caseOrg.isRefTrackingOnly()
							&& caseOrg.getStatus() == PatientStatus.LTFU && caseOrg.getStartDate() != null) {
						this.dateOfLossToFollowUp = caseOrg.getStartDate().toString();
					}
				}
			}
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
			if (caseEntity.getPerson().getLocations() != null && caseEntity.getPerson().getLocations().size() > 0) {
				for (Location location : caseEntity.getPerson().getLocations()) {
					if (location.getAddressType().toString() == "CURRENT_ADDRESS") {
						if (location.getStreetAddress() != null) {
							this.currentStressAddress = location.getStreetAddress();
						}
						if (location.getCommune() != null) {
							this.currentCommune = location.getCommune().getName();
							this.currentCommuneCode = location.getCommune().getCodeGso();
						}
						if (location.getDistrict() != null) {
							this.currentDistrict = location.getDistrict().getName();
							this.currentDistrict = location.getDistrict().getCodeGso();
						}
						if (location.getProvince() != null) {
							this.currentProvince = location.getProvince().getName();
							this.currentProvinceCode = location.getProvince().getCodeGso();
						}
						if (location.getCountry() != null) {
							this.currentCountry = location.getCountry().getName();
							this.currentCountryCode = location.getCountry().getCodeGso();
						}
					} else if (location.getAddressType().toString() == "RESIDENT_ADDRESS") {
						if (location.getStreetAddress() != null) {
							this.permanentStressAddress = location.getStreetAddress();
						}
						if (location.getCommune() != null) {
							this.permanentCommune = location.getCommune().getName();
							this.permanentCommuneCode = location.getCommune().getCodeGso();
						}
						if (location.getDistrict() != null) {
							this.permanentDistrict = location.getDistrict().getName();
							this.permanentDistrictCode = location.getDistrict().getCodeGso();
						}
						if (location.getProvince() != null) {
							this.permanentProvince = location.getProvince().getName();
							this.permanentProvinceCode = location.getProvince().getCodeGso();
						}
						if (location.getCountry() != null) {
							this.permanentCountry = location.getCountry().getName();
							this.permanentCountryCode = location.getCountry().getCodeGso();
						}
					}
				}
			}
			if (caseEntity.getConfirmLab() != null) {
				this.confirmLab = caseEntity.getConfirmLab().getName();
			}
			if (caseEntity.getHivConfirmDate() != null) {
				this.hivConfirmationDate = caseEntity.getHivConfirmDate().toString();
			}

			if (caseEntity.getRiskInterviews() != null && caseEntity.getRiskInterviews().size() > 0) {
				for (RiskInterview item : caseEntity.getRiskInterviews()) {
					HivRiskFactorDto riskFactor = new HivRiskFactorDto(item);
					this.riskPopulation = riskFactor;
				}
			}

			if (caseEntity.getRecencies() != null && caseEntity.getRecencies().size() > 0) {
				for (Recency item : caseEntity.getRecencies()) {
					HivRecencyDto dto = new HivRecencyDto(item);
					this.recency = dto;
				}
			}
			if (caseEntity.getLabTests() != null && caseEntity.getLabTests().size() > 0) {
				// Set<LabTest> labTest = caseEntity.getLabTests();
				for (LabTest item : caseEntity.getLabTests()) {
					if (item.getTestType() != null) {
						if (item.getTestType().getNumber() == ClinicalTestingType.CD4.getNumber()) {
							HivCd4Dto cd4 = new HivCd4Dto(item);
							if (this.listHivCd4 == null) {
								this.listHivCd4 = new ArrayList<HivCd4Dto>();
							}
							this.listHivCd4.add(cd4);

						} else if (item.getTestType().getNumber() == ClinicalTestingType.VIRAL_LOAD.getNumber()) {
							HivViralLoadDto vl = new HivViralLoadDto(item);
							if (this.listHivViralLoad == null) {
								this.listHivViralLoad = new ArrayList<HivViralLoadDto>();
							}

							this.listHivViralLoad.add(vl);

						} else if (item.getTestType().getNumber() == ClinicalTestingType.HIV_CONFIRMATION.getNumber()) {
							// HivConfirm - co the se can de lay thong tin chi tiet xet nghiem

						} else if (item.getTestType().getNumber() == ClinicalTestingType.ARV_DR.getNumber()) {
							// Khang thuoc
							HivDrugResistanceDto dr = new HivDrugResistanceDto(item);
							if (this.listDrugResistance == null)
								this.listDrugResistance = new ArrayList<HivDrugResistanceDto>();
							this.listDrugResistance.add(dr);
						}
					}
				}
			}
			// ARV Treatment
			if (caseEntity.getTreatments() != null && caseEntity.getTreatments().size() > 0) {
				ArrayList<Treatment> listData = new ArrayList<Treatment>(caseEntity.getTreatments());
				Long treatmentId = listData.get(0).getId();
				for (Treatment item : caseEntity.getTreatments()) {
					HivArvTreatmentDto arvTm = null;
					if (caseEntity.getCaseOrgs() != null && caseEntity.getCaseOrgs().size() > 0) {
						for (CaseOrg caseOrg : caseEntity.getCaseOrgs()) {
							if (!caseOrg.isRefTrackingOnly() && caseOrg.getOrganization() != null
									&& caseOrg.getOrganization().getId() != null && item.getOrganization() != null
									&& item.getOrganization().getId() != null
									&& item.getOrganization().getId().equals(caseOrg.getOrganization().getId())) {
								if (item.getStartDate() != null && caseOrg.getStartDate() != null
										&& item.getStartDate().equals(caseOrg.getStartDate())) {
									arvTm = new HivArvTreatmentDto(item);
									
									 if (!caseOrg.isRefTrackingOnly()
											&& caseOrg.getStatus() == PatientStatus.TRANSFERRED_OUT) {
										if (caseOrg.getStartDate() != null) {
											arvTm.setDateOfTransferredOut(caseOrg.getStartDate().toString());
										}
										if (caseOrg.getOrganizationName() != null) {
											arvTm.setPlaceTransferredOut(caseOrg.getOrganizationName());
											
										}
										arvTm.setTreatmentStopReason("Transfer out");
									} else if (caseOrg.isLatestRelationship() && !caseOrg.isRefTrackingOnly()
											&& caseOrg.getStatus() == PatientStatus.LTFU && caseOrg.getStartDate() != null) {
										arvTm.setTreatmentStopReason("Transfer out");
										arvTm.setDateOfLossToFollowUp(caseOrg.getStartDate().toString());
									}
								}
							}
						}
					}
					if(arvTm!= null) {
						if(item.getId().equals(treatmentId)) {
							List<ClinicalStageDto> listStage = new ArrayList<ClinicalStageDto>();
							if(caseEntity.getWhoStages()!= null && caseEntity.getWhoStages().size()>0) {
								for (ClinicalStage clinicalStage : caseEntity.getWhoStages()) {
									ClinicalStageDto stage = new ClinicalStageDto();
									stage.setStage(clinicalStage.getStage());
									stage.setEvalDate(clinicalStage.getEvalDate());
									listStage.add(stage);
								}
							}
							arvTm.setWhoStage(listStage);
						}
						if(this.listArvTreatment == null ) {
							this.listArvTreatment = new ArrayList<HivArvTreatmentDto>();
						}
						this.listArvTreatment.add(arvTm);
					}
				}
			}

			// Tuberculosis
			if (caseEntity.getTbtxs() != null && caseEntity.getTbtxs().size() > 0) {
				for (TBTreatment item : caseEntity.getTbtxs()) {
					HivTBTreatmentDto tbt = new HivTBTreatmentDto(item);
					if (this.listTBTreatment == null)
						this.listTBTreatment = new ArrayList<HivTBTreatmentDto>();
					this.listTBTreatment.add(tbt);
				}
			}
			// tbTreatment
			if (caseEntity.getTbpros() != null && caseEntity.getTbpros().size() > 0) {
				for (TBProphylaxis item : caseEntity.getTbpros()) {
					HivTBProphylaxisDto tbp = new HivTBProphylaxisDto(item);
					if (this.listTBProphylaxis == null)
						this.listTBProphylaxis = new ArrayList<HivTBProphylaxisDto>();
					this.listTBProphylaxis.add(tbp);
				}
			}
			if (caseEntity.getHepatitises() != null && caseEntity.getHepatitises().size() > 0) {
				for (Hepatitis item : caseEntity.getHepatitises()) {
					// HBV
					if (item.getTestType().getNumber() == ClinicalTestingType.HEP_B.getNumber()) {
						HivHepatitisDto hbv = new HivHepatitisDto(item);
						if (this.listHbv == null)
							this.listHbv = new ArrayList<HivHepatitisDto>();
						this.listHbv.add(hbv);
					}
					// HBV
					else if (item.getTestType().getNumber() == ClinicalTestingType.HEP_C.getNumber()) {
						HivHepatitisDto hcv = new HivHepatitisDto(item);
						if (this.listHcv == null)
							this.listHcv = new ArrayList<HivHepatitisDto>();
						this.listHcv.add(hcv);
					}
				}
			}

			if (caseEntity.getPregnancies() != null && caseEntity.getPregnancies().size() > 0) {
				for (Pregnancy item : caseEntity.getPregnancies()) {
					HivPregnancyDto pregnancy = new HivPregnancyDto(item);
					if (this.listPregnancy == null)
						this.listPregnancy = new ArrayList<HivPregnancyDto>();
					this.listPregnancy.add(pregnancy);
				}
			}
		}
	}

	public PdmaDto(PNSCase caseEntity,Integer value ) {
		if(caseEntity != null) {
//			if()
		}
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

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public void setListPregnancy(List<HivPregnancyDto> listPregnancy) {
		this.listPregnancy = listPregnancy;
	}

	public List<HivCd4Dto> getListHivCd4() {
		return listHivCd4;
	}

	public void setListHivCd4(List<HivCd4Dto> listHivCd4) {
		this.listHivCd4 = listHivCd4;
	}

	public List<HivViralLoadDto> getListHivViralLoad() {
		return listHivViralLoad;
	}

	public void setListHivViralLoad(List<HivViralLoadDto> listHivViralLoad) {
		this.listHivViralLoad = listHivViralLoad;
	}

	public HivRiskFactorDto getRiskPopulation() {
		return riskPopulation;
	}

	public void setRiskPopulation(HivRiskFactorDto riskPopulation) {
		this.riskPopulation = riskPopulation;
	}

	public List<HivDrugResistanceDto> getListDrugResistance() {
		return listDrugResistance;
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

	public List<HivPregnancyDto> getListPregnancy() {
		return listPregnancy;
	}

	public HivRecencyDto getRecency() {
		return recency;
	}

	public void setRecency(HivRecencyDto recency) {
		this.recency = recency;
	}

	public LocalDateTime getLastSynDate() {
		return lastSynDate;
	}

	public void setLastSynDate(LocalDateTime lastSynDate) {
		this.lastSynDate = lastSynDate;
	}

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
	public String getHealthNumber() {
		return healthNumber;
	}
	public void setHealthNumber(String healthNumber) {
		this.healthNumber = healthNumber;
	}
	public String getPlaceSpecimenCollected() {
		return placeSpecimenCollected;
	}
	public void setPlaceSpecimenCollected(String placeSpecimenCollected) {
		this.placeSpecimenCollected = placeSpecimenCollected;
	}

}

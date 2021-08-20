package com.globits.adapter.eclinica.data.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.globits.adapter.eclinica.data.domain.Person;
import com.globits.adapter.eclinica.data.domain.PersonAddress;
import com.globits.adapter.eclinica.data.domain.PersonAttribute;
import com.globits.adapter.eclinica.data.domain.PersonName;

public class PersonDto {
	protected Integer personId;
	protected UUID uuid;
	protected String displayName;
	private String givenName;
	private String middleName;
	private String familyName;
	protected String gender;
//	@JsonSerialize(using = ToStringSerializer.class)
//	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	protected Date birthdate;
	protected boolean dead;
//	@JsonSerialize(using = ToStringSerializer.class)
//	@JsonDeserialize(using = CustomLocalDateTimeDeserializer2.class)
	protected Date deathDate;
	protected String causeOfDeath;
	protected Set<PersonNameDto> names;
	protected Set<PersonAddressDto> addresses;
	protected String currentAddress;// địa chỉ hiện tại
	private String currentStressAddress;
	private String currentCommune;
	private String currentCommuneCode;
	private String currentDistrict;
	private String currentDistrictCode;
	private String currentProvince;
	private String currentProvinceCode;
	private String currentCountry;
	private String currentCountryCode;
	protected String permanentResidence;// địa chỉ thường trú
	private String permanentStressAddress;
	private String permanentCommune;
	private String permanentCommuneCode;
	private String permanentDistrict;
	private String permanentDistrictCode;
	private String permanentProvince;
	private String permanentProvinceCode;
	private String permanentCountry;
	private String permanentCountryCode;
	private String postalCode;
	protected String bplCardNo;// cmnd
	protected String ethnic;// dân tộc
	protected String birthplace;// nơi sinh
	protected String patientCareer;// nghề nghiệp
	protected Date hivInfectionDate;// ngày khẳng định HIV
	protected String hivAssertionLocation;// nơi khẳng định HIV
	protected String medicalCode;// mã bệnh án
	protected String transmission;// nhóm nguy cơ cao
	protected String object;// đối tượng (BHYT,khác, miễn , thu phí)
	protected String medicalInsurance;// số BHYT
	protected String patientPhone;// SĐT
	protected Set<PersonAttributeDto> attributes;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public Set<PersonNameDto> getNames() {
		return names;
	}

	public void setNames(Set<PersonNameDto> names) {
		this.names = names;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Set<PersonAddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<PersonAddressDto> addresses) {
		this.addresses = addresses;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getPermanentResidence() {
		return permanentResidence;
	}

	public void setPermanentResidence(String permanentResidence) {
		this.permanentResidence = permanentResidence;
	}

	public String getBplCardNo() {
		return bplCardNo;
	}

	public void setBplCardNo(String bplCardNo) {
		this.bplCardNo = bplCardNo;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getPatientCareer() {
		return patientCareer;
	}

	public void setPatientCareer(String patientCareer) {
		this.patientCareer = patientCareer;
	}

	public Date getHivInfectionDate() {
		return hivInfectionDate;
	}

	public void setHivInfectionDate(Date hivInfectionDate) {
		this.hivInfectionDate = hivInfectionDate;
	}

	public String getHivAssertionLocation() {
		return hivAssertionLocation;
	}

	public void setHivAssertionLocation(String hivAssertionLocation) {
		this.hivAssertionLocation = hivAssertionLocation;
	}

	public String getMedicalCode() {
		return medicalCode;
	}

	public void setMedicalCode(String medicalCode) {
		this.medicalCode = medicalCode;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public Set<PersonAttributeDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<PersonAttributeDto> attributes) {
		this.attributes = attributes;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public PersonDto() {
	}

	public PersonDto(Person p) {
		this.personId = p.getPersonId();
		this.uuid = p.getUuid();
		this.gender = p.getGender();
		this.birthdate = p.getBirthdate();
//		this.dead = p.isDead();
//		this.deathDate = p.getDeathDate();
//		this.causeOfDeath = p.getCauseOfDeath();

		if (p.getNames() != null && p.getNames().size() > 0) {
			this.names = new HashSet<PersonNameDto>();

			for (PersonName ex : p.getNames()) {
				if (ex.getVoided()!=null&&ex.getVoided()) {
					continue;
				}
				PersonNameDto exDto = new PersonNameDto();
				exDto.setPersonNameId(ex.getPersonNameId());
				exDto.setUuid(ex.getUuid());
				exDto.setGivenName(ex.getGivenName());
				exDto.setMiddleName(ex.getMiddleName());
				exDto.setFamilyName(ex.getFamilyName());
				exDto.setPreferred(ex.isPreferred());
				exDto.setVoided(ex.getVoided());
				String displayName = "";
				if (ex.getGivenName() != null && ex.getGivenName().length() > 0) {
					displayName = displayName + ex.getGivenName().trim();
				}
				if (displayName != null && displayName.length() > 0) {
					displayName = displayName + " ";
				}
				if (ex.getMiddleName() != null && ex.getMiddleName().length() > 0) {
					displayName = displayName + ex.getMiddleName().trim();
				}
				if (displayName != null && displayName.length() > 0) {
					displayName = displayName + " ";
				}
				if (ex.getFamilyName() != null && ex.getFamilyName().length() > 0) {
					displayName = displayName + ex.getFamilyName().trim();
				}
				exDto.setDisplayName(displayName);
				if (ex.isPreferred() ) {// trường hợp tên hiện thời dùng
					this.displayName = displayName;
					this.givenName=ex.getGivenName();
					this.familyName=ex.getFamilyName();
					this.middleName=ex.getMiddleName();
				}
				this.names.add(exDto);
			}
		}
		try {
			if (p.getAddresses() != null && p.getAddresses().size() > 0) {
				this.addresses = new HashSet<PersonAddressDto>();
				for (PersonAddress pa : p.getAddresses()) {
					if (pa.getVoided()!=null&& pa.getVoided()) {
						continue;
					}
//					PersonAddressDto paDto = new PersonAddressDto();
//					paDto.setPersonAddressId(pa.getPersonAddressId());
//					paDto.setAddress1(pa.getAddress1());
//					paDto.setAddress4(pa.getAddress4());
//					paDto.setCountyDistrict(pa.getCountyDistrict());
//					paDto.setStateProvince(pa.getStateProvince());
//					paDto.setCountry(pa.getCountry());
//					paDto.setPostalCode(pa.getPostalCode());
//					paDto.setPreferred(pa.isPreferred());
////					paDto.setPerson(new PersonDto());
//					paDto.setPersonId(p.getPersonId());
					if (pa.isPreferred()) {
						// địa chỉ hiện tại
						String currentAddress = "";
						if (pa.getAddress1() != null) {
							currentAddress = pa.getAddress1();
							currentStressAddress= pa.getAddress1();
						}

						if (pa.getAddress4() != null) {
							if (currentAddress != null && currentAddress.length() > 0) {
								currentAddress = currentAddress + " - ";
							}
							currentAddress = currentAddress + pa.getAddress4();
							currentCommune=pa.getAddress4();
						}

						if (pa.getCountyDistrict() != null) {
							if (currentAddress != null && currentAddress.length() > 0) {
								currentAddress = currentAddress + " - ";
							}
							currentAddress = currentAddress + pa.getCountyDistrict();
							currentDistrict=pa.getCountyDistrict();
						}

						if (pa.getStateProvince() != null) {
							if (currentAddress != null && currentAddress.length() > 0) {
								currentAddress = currentAddress + " - ";
							}
							currentAddress = currentAddress + pa.getStateProvince();
							currentProvince=pa.getStateProvince();
						}
						if (pa.getCountry() != null) {
							if (currentAddress != null && currentAddress.length() > 0) {
								currentAddress = currentAddress + " - ";
							}
							currentAddress = currentAddress + pa.getCountry();
							currentCountry= pa.getCountry();
						}
						
						if(pa.getPostalCode()!= null) {
							this.postalCode = pa.getPostalCode();
						}
						this.currentAddress = currentAddress;
					} else {
						// địa chỉ thường trú
						String permanentResidence = "";
						if (pa.getAddress1() != null) {
							permanentResidence = pa.getAddress1();
							permanentStressAddress=pa.getAddress1();
						}
						if (pa.getAddress4() != null) {
							if (permanentResidence != null && permanentResidence.length() > 0) {
								permanentResidence = permanentResidence + " - ";
							}
							permanentResidence = permanentResidence + pa.getAddress4();
							permanentCommune=pa.getAddress4();
						}

						if (pa.getCountyDistrict() != null) {
							if (permanentResidence != null && permanentResidence.length() > 0) {
								permanentResidence = permanentResidence + " - ";
							}
							permanentResidence = permanentResidence + pa.getCountyDistrict();
							permanentDistrict=pa.getCountyDistrict();
						}

						if (pa.getStateProvince() != null) {
							if (permanentResidence != null && permanentResidence.length() > 0) {
								permanentResidence = permanentResidence + " - ";
							}
							permanentResidence = permanentResidence + pa.getStateProvince();
							permanentProvince=pa.getStateProvince();;
						}
						if (pa.getCountry() != null) {
							if (permanentResidence != null && permanentResidence.length() > 0) {
								permanentResidence = permanentResidence + " - ";
							}
							permanentResidence = permanentResidence + pa.getCountry();
							permanentCountry= pa.getCountry();
						}
						if(pa.getPostalCode()!= null) {
							this.postalCode = pa.getPostalCode();
						}
						this.permanentResidence = permanentResidence;

					}
//					this.addresses.add(paDto);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (p.getAttributes() != null && p.getAttributes().size() > 0) {
			this.attributes = new HashSet<PersonAttributeDto>();
			for (PersonAttribute pa : p.getAttributes()) {
				if (pa.getVoided()!=null&& pa.getVoided()) {
					continue;
				}
//				PersonAttributeDto paDto = new PersonAttributeDto();
//				paDto.setPersonAttributeId(pa.getPersonAttributeId());
//				paDto.setUuid(pa.getUuid());
//				paDto.setValue(pa.getValue());
//				paDto.setPersonId(p.getPersonId());
//				if (pa.getPersonAttributeType() != null) {
//					paDto.setPersonAttributeType(new PersonAttributeTypeDto());
//					paDto.getPersonAttributeType()
//							.setPersonAttributeTypeId(pa.getPersonAttributeType().getPersonAttributeTypeId());
//					paDto.getPersonAttributeType().setName(pa.getPersonAttributeType().getName());
//					paDto.getPersonAttributeType().setUuid(pa.getPersonAttributeType().getUuid());
//				}
//				this.attributes.add(paDto);
				if (pa.getPersonAttributeType() != null
						&& pa.getPersonAttributeType().getPersonAttributeTypeId() != null) {
					switch (pa.getPersonAttributeType().getPersonAttributeTypeId()) {
					case 1:
						this.ethnic = pa.getValue();
						break;
					case 2:
						this.birthplace = pa.getValue();
						break;
					case 12:
						this.patientPhone = pa.getValue();
						break;
					case 13:
						this.patientCareer = pa.getValue();
						break;
					case 14:
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							this.hivInfectionDate = sdf.parse(pa.getValue());
						} catch (Exception e) {
							// TODO: handle exception
						}

						break;
					case 19:
						this.medicalInsurance = pa.getValue();
						break;
					case 28:
						this.hivAssertionLocation = pa.getValue();
						break;
					case 39:
						this.bplCardNo = pa.getValue();
						break;
					case 52:
						this.medicalCode = pa.getValue();
						break;
					case 55:
						this.transmission = pa.getValue();
						break;
					case 62:
						this.object = pa.getValue();
						break;

					default:
						break;
					}
				}
			}
		}
	}
}

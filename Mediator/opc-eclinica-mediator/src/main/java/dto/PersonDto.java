package dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

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
	private String currentDistrict;
	private String currentProvince;
	private String currentCountry;
	protected String permanentResidence;// địa chỉ thường trú
	private String permanentStressAddress;
	private String permanentCommune;
	private String permanentDistrict;
	private String permanentProvince;
	private String permanentCountry;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

}

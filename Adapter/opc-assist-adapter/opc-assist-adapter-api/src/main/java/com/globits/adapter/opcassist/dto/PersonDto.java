package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.globits.adapter.opcassist.domain.Person;
import com.globits.adapter.opcassist.types.Gender;

public class PersonDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nidNumber;

	private LocalDateTime nidIssuedDate;

	private String nidIssuedBy;

	private String noNidReason;

	private String passportNumber;

	private LocalDateTime passportIssuedDate;

	private String passportIssuedBy;

	private LocalDateTime passportExpiryDate;

	private DictionaryDto ethnic;
	
	private DictionaryDto religion;

	private DictionaryDto nationality;

	private DictionaryDto education;

	private DictionaryDto wealthStatus;

	private String fullname;

	private LocalDateTime dob;

	private Gender gender;

	private Set<LocationDto> locations = new LinkedHashSet<>();

//	private LocationDto placeOfBirth;
//
//	private LocationDto currentAddress;
//
//	private LocationDto residentAddress;

	public PersonDto() {
	}

	public PersonDto(Person entity) {

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.nidNumber = entity.getNidNumber();
		this.nidIssuedDate = entity.getNidIssuedDate();
		this.nidIssuedBy = entity.getNidIssuedBy();
		this.noNidReason = entity.getNoNidReason();
		this.passportNumber = entity.getPassportNumber();
		this.passportIssuedDate = entity.getPassportIssuedDate();
		this.passportIssuedBy = entity.getPassportIssuedBy();
		this.passportExpiryDate = entity.getPassportExpiryDate();

		this.fullname = entity.getFullname();
		this.dob = entity.getDob();
		this.gender = entity.getGender();
		if (entity.getEthnic() != null) {
			this.ethnic = new DictionaryDto(entity.getEthnic());
		}
		if (entity.getLocations() != null) {
			entity.getLocations().forEach(l -> {
				locations.add(new LocationDto(l));
			});
		}
	}

	public Person toEntity() {
		Person entity = new Person();

		entity.setId(id);
		entity.setNidNumber(nidNumber);
		entity.setNidIssuedDate(nidIssuedDate);
		entity.setNidIssuedBy(nidIssuedBy);
		entity.setNoNidReason(noNidReason);
		entity.setPassportNumber(passportNumber);
		entity.setPassportIssuedDate(passportIssuedDate);
		entity.setPassportIssuedBy(passportIssuedBy);
		entity.setPassportExpiryDate(passportExpiryDate);

		entity.setFullname(fullname);
		entity.setDob(dob);
		entity.setGender(gender);

		if (ethnic != null) {
			entity.setEthnic(ethnic.toEntity());
		}
		if (religion != null) {
			entity.setReligion(religion.toEntity());
		}

		if (nationality != null) {
			entity.setNationality(nationality.toEntity());
		}

		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNidNumber() {
		return nidNumber;
	}

	public void setNidNumber(String nidNumber) {
		this.nidNumber = nidNumber;
	}

	public LocalDateTime getNidIssuedDate() {
		return nidIssuedDate;
	}

	public void setNidIssuedDate(LocalDateTime nidIssuedDate) {
		this.nidIssuedDate = nidIssuedDate;
	}

	public String getNidIssuedBy() {
		return nidIssuedBy;
	}

	public void setNidIssuedBy(String nidIssuedBy) {
		this.nidIssuedBy = nidIssuedBy;
	}

	public String getNoNidReason() {
		return noNidReason;
	}

	public void setNoNidReason(String noNidReason) {
		this.noNidReason = noNidReason;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public LocalDateTime getPassportIssuedDate() {
		return passportIssuedDate;
	}

	public void setPassportIssuedDate(LocalDateTime passportIssuedDate) {
		this.passportIssuedDate = passportIssuedDate;
	}

	public String getPassportIssuedBy() {
		return passportIssuedBy;
	}

	public void setPassportIssuedBy(String passportIssuedBy) {
		this.passportIssuedBy = passportIssuedBy;
	}

	public LocalDateTime getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(LocalDateTime passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public DictionaryDto getEthnic() {
		return ethnic;
	}

	public void setEthnic(DictionaryDto ethnic) {
		this.ethnic = ethnic;
	}
	public DictionaryDto getReligion() {
		return religion;
	}

	public void setReligion(DictionaryDto religion) {
		this.religion = religion;
	}

	public DictionaryDto getNationality() {
		return nationality;
	}

	public void setNationality(DictionaryDto nationality) {
		this.nationality = nationality;
	}
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Set<LocationDto> getLocations() {
		if (locations == null) {
			locations = new LinkedHashSet<>();
		}

		return locations;
	}

	public void setLocations(Set<LocationDto> locations) {
		this.locations = locations;
	}
}

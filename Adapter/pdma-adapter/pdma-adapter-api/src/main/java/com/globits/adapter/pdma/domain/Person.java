package com.globits.adapter.pdma.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.pdma.types.Gender;
import com.globits.adapter.pdma.utils.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "tbl_person")
@XmlRootElement
public class Person {
	@Transient
	private static final long serialVersionUID = 6667595228228996480L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

//	@Convert(converter = CryptoConverter.class)
	@Column(name = "national_id_number", length = 20, nullable = true)
	private String nidNumber;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "national_id_issue_date", nullable = true)
	private LocalDateTime nidIssuedDate;

	@Column(name = "national_id_issue_by", length = 100, nullable = true)
	private String nidIssuedBy;

	@Column(name = "no_nid_reason", length = 200, nullable = true)
	private String noNidReason;

//	@Convert(converter = CryptoConverter.class)
	@Column(name = "passport_number", length = 20, nullable = true)
	private String passportNumber;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "passport_issue_date", nullable = true)
	private LocalDateTime passportIssuedDate;

	@Column(name = "passport_issue_by", length = 100, nullable = true)
	private String passportIssuedBy;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "passport_expiry_date", nullable = true)
	private LocalDateTime passportExpiryDate;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ethnic_id", nullable = true)
	private Dictionary ethnic;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "religion_id", nullable = true)
	private Dictionary religion;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "nationality_id", nullable = true)
	private Dictionary nationality;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "education_id", nullable = true)
	private Dictionary education;

	@Deprecated
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "wealth_status_id", nullable = true)
	private Dictionary wealthStatus;

	@Deprecated
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "monthly_income_id", nullable = true)
	private Dictionary monthlyIncome;

	@Deprecated
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "professional_id", nullable = true)
	private Dictionary professional;

//	@Convert(converter = CryptoConverter.class)
	@Column(name = "fullname", length = 255, nullable = false)
	private String fullname;

	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "dob", nullable = true)
	private LocalDateTime dob;

	@Column(name = "gender", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	/**
	 * For place of birth, current address and resident address
	 */
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Location> locations = new LinkedHashSet<>();

	@Column(name = "current_address_id", nullable = true)
	private Long currentAddressId;

	@Column(name = "resident_address_id", nullable = true)
	private Long residentAddressId;

	public Long getCurrentAddressId() {
		return currentAddressId;
	}

	public void setCurrentAddressId(Long currentAddressId) {
		this.currentAddressId = currentAddressId;
	}

	public Long getResidentAddressId() {
		return residentAddressId;
	}

	public void setResidentAddressId(Long residentAddressId) {
		this.residentAddressId = residentAddressId;
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

	public Dictionary getEthnic() {
		return ethnic;
	}

	public void setEthnic(Dictionary ethnic) {
		this.ethnic = ethnic;
	}
	public Dictionary getReligion() {
		return religion;
	}

	public void setReligion(Dictionary religion) {
		this.religion = religion;
	}

	public Dictionary getNationality() {
		return nationality;
	}

	public void setNationality(Dictionary nationality) {
		this.nationality = nationality;
	}

	public Dictionary getEducation() {
		return education;
	}

	public void setEducation(Dictionary education) {
		this.education = education;
	}

	public Dictionary getWealthStatus() {
		return wealthStatus;
	}

	public void setWealthStatus(Dictionary wealthStatus) {
		this.wealthStatus = wealthStatus;
	}

	public Dictionary getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Dictionary monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Dictionary getProfessional() {
		return professional;
	}

	public void setProfessional(Dictionary professional) {
		this.professional = professional;
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
	public Set<Location> getLocations() {
		if (locations == null) {
			locations = new LinkedHashSet<>();
		}

		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
}

package com.globits.adapter.opcassist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.globits.adapter.opcassist.types.AddressType;

@Entity
@Table(name = "tbl_location")
public class Location {
	@Transient
	private static final long serialVersionUID = -7308060391101926703L;

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "address_type", nullable = true)
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

//	@Convert(converter = CryptoConverter.class)
	@Column(name = "street_address", length = 255, nullable = true)
	private String streetAddress;

	@Column(name = "longitude", nullable = true)
	private Double longitude;

	@Column(name = "latitude", nullable = true)
	private Double latitude;

	@ManyToOne
	@JoinColumn(name = "commune_id", nullable = true)
	private AdminUnit commune;

	@ManyToOne
	@JoinColumn(name = "district_id", nullable = true)
	private AdminUnit district;

	@ManyToOne
	@JoinColumn(name = "province_id", nullable = true)
	private AdminUnit province;

	@ManyToOne
	@JoinColumn(name = "country_id", nullable = true)
	private AdminUnit country;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = true)
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public AdminUnit getCommune() {
		return commune;
	}

	public void setCommune(AdminUnit commune) {
		this.commune = commune;
	}

	public AdminUnit getDistrict() {
		return district;
	}

	public void setDistrict(AdminUnit district) {
		this.district = district;
	}

	public AdminUnit getProvince() {
		return province;
	}

	public void setProvince(AdminUnit province) {
		this.province = province;
	}

	public AdminUnit getCountry() {
		return country;
	}

	public void setCountry(AdminUnit country) {
		this.country = country;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(addressType).append(country).append(province)
				.append(district).append(commune).append(streetAddress).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Location)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		Location that = (Location) obj;

		return new EqualsBuilder().append(id, that.id).append(addressType, that.addressType)
				.append(country, that.country).append(province, that.province).append(district, that.district)
				.append(commune, that.commune).append(streetAddress, that.streetAddress).isEquals();
	}
}

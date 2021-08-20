package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.Location;
import com.globits.adapter.opcassist.domain.Person;
import com.globits.adapter.opcassist.types.AddressType;

public class LocationDto  implements Serializable{
	private Long id;

	private AddressType addressType;

	private String streetAddress;

	private Double longitude;

	private Double latitude;
	
	private AdminUnitDto commune;

	private AdminUnitDto district;

	private AdminUnitDto province;

	private AdminUnitDto country;

	private PersonDto person;

	public LocationDto() {
	}

	public LocationDto(Location entity) {
		super();

		if (entity == null) {
			return;
		}

		this.id = entity.getId();
		this.addressType = entity.getAddressType();
		this.streetAddress = entity.getStreetAddress();
		this.longitude = entity.getLongitude();
		this.latitude = entity.getLatitude();

		if (entity.getCommune() != null) {
			this.commune = new AdminUnitDto(entity.getCommune());
		}

		if (entity.getDistrict() != null) {
			this.district = new AdminUnitDto(entity.getDistrict());
		}

		if (entity.getProvince() != null) {
			this.province = new AdminUnitDto(entity.getProvince());
		}

		if (entity.getCountry() != null) {
			this.country = new AdminUnitDto(entity.getCountry());
		}

		if (entity.getPerson() != null) {
			this.person = new PersonDto();
			this.person.setId(entity.getPerson().getId());
		}
	}

	public Location toEntity() {
		Location entity = new Location();

		entity.setId(id);
		entity.setAddressType(addressType);
		entity.setStreetAddress(streetAddress);
		entity.setLongitude(longitude);
		entity.setLatitude(latitude);

		if (commune != null) {
			entity.setCommune(commune.toEntity());
		}

		if (district != null) {
			entity.setDistrict(district.toEntity());
		}

		if (province != null) {
			entity.setProvince(province.toEntity());
		}

		if (country != null) {
			entity.setCountry(country.toEntity());
		}

		if (person != null) {
			Person _person = new Person();
			_person.setId(person.getId());
			entity.setPerson(_person);
		}

		return entity;
	}

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
	public AdminUnitDto getCommune() {
		return commune;
	}

	public void setCommune(AdminUnitDto commune) {
		this.commune = commune;
	}

	public AdminUnitDto getDistrict() {
		return district;
	}

	public void setDistrict(AdminUnitDto district) {
		this.district = district;
	}

	public AdminUnitDto getProvince() {
		return province;
	}

	public void setProvince(AdminUnitDto province) {
		this.province = province;
	}

	public AdminUnitDto getCountry() {
		return country;
	}

	public void setCountry(AdminUnitDto country) {
		this.country = country;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}
}

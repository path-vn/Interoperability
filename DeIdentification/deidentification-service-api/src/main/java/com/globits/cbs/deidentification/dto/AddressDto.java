package com.globits.cbs.deidentification.dto;

public class AddressDto {
	private String text;//Text representation of the address
	private String line;
	private String cityName;
	private String cityCode;
	private String districtName;
	private String districtCode;
	private String communeCode;
	private String communeName;
	private String countryCode;
	private String countryName;
	private String typeCode;//Type code of Address
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}


	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getCommuneCode() {
		return communeCode;
	}
	public void setCommuneCode(String communeCode) {
		this.communeCode = communeCode;
	}
	public String getCommuneName() {
		return communeName;
	}
	public void setCommuneName(String communeName) {
		this.communeName = communeName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	
}

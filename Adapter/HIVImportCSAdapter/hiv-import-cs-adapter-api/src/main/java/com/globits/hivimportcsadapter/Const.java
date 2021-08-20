package com.globits.hivimportcsadapter;

public class Const {

	public enum ConceptDataType{
		coded(0),//Concept
		booleans(1),//True-False
		datetimes(2),//Date
		numeric(3),//numeric
		text(4)//text
		;
		private Integer value;
		private ConceptDataType(int value) {
		    this.value = value;
		}
	
		public Integer getValue() {
			return value;
		}
	}
	
	public enum GenderType {
		unclear("U"), female("F"), male("M");
		
		private String value;
		
		private GenderType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}

	}
	
	public static String getGenderTypeEnumFromString(String gender) {
		switch(gender){
		case "Nam":
			return GenderType.male.getValue();
		case "Nữ":
			return GenderType.female.getValue();
		default:
			return null;
		}
	}

	public static String getGenderTypeFromString(String value) {
		switch(value){
		case "F":
			return "Nữ";
		case "M":
			return "Nam";
		default:
			return null;
		}
	}
	public enum  PatientGenderIdentity{
		TransgenderFemale("transgender-to-female"),
		TransgenderMale("transgender-to-male"),
		NonBinary("non-binary"),
		Male("male"),
		Female("female"),
		Other("other"),
		NotDisclosed("not-disclosed"),
		;
		private String value;
		private PatientGenderIdentity(String value) {
		    this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}
}

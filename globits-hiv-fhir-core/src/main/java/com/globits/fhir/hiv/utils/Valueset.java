package com.globits.fhir.hiv.utils;

public class Valueset {
	private String code;
	private String display;
	private String definition;
	private String system;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public Valueset() {
		super();
	}
	public Valueset(String code, String display, String definition) {
		super();
		this.code = code;
		this.display = display;
		this.definition = definition;
	}
	public Valueset(String code, String display, String definition, String system) {
		super();
		this.code = code;
		this.display = display;
		this.definition = definition;
		this.system = system;
	}
	
}

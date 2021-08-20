package com.globits.hiv.receive.dto;

public class SystemCodeDto {
	private String code;
	private String display;
	private String definition;

	public SystemCodeDto() {
		
	}

	public SystemCodeDto(String code, String display, String definition) {
		this.code = code;
		this.display = display;
		this.definition = definition;
	}

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

}

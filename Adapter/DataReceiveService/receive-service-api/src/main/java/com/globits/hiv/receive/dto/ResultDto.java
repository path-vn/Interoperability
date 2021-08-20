package com.globits.hiv.receive.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDto<T> {
	private String code;
	private String mesage;
	private List<T> responeContent = new ArrayList<T>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	public List<T> getResponeContent() {
		return responeContent;
	}
	public void setResponeContent(List<T> responeContent) {
		this.responeContent = responeContent;
	}
	
}

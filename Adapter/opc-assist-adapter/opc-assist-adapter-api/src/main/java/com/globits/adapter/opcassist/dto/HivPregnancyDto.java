package com.globits.adapter.opcassist.dto;

import java.io.Serializable;

import com.globits.adapter.opcassist.domain.Pregnancy;

public class HivPregnancyDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createDate;
	private String childDeliveryPlace;
	private String childDateOfBirth;
	private double birthWeight;
	private String pregResult;
	private String childDiagnosedDate;
	private int childHivStatus;

	public HivPregnancyDto() {
		
	}

	public HivPregnancyDto(Pregnancy item) {
		if(item.getCreateDate()!=null) {
			this.setCreateDate(item.getCreateDate().toString());
		}
		if(item.getOrganization()!=null) {
			this.setChildDeliveryPlace(item.getOrganization().getName());
		}
		if(item.getChildDob()!=null) {
			this.setChildDateOfBirth(item.getChildDob().toString());
		}
		if(item.getPregResult()!=null){
			this.setPregResult(item.getPregResult().toString());
		}
		if(item.getChildDiagnosedDate()!=null){
			this.setChildDiagnosedDate(item.getChildDiagnosedDate().toString());
		}
		this.setBirthWeight(item.getBirthWeight());
		this.setChildHivStatus(item.getChildHIVStatus());
		
	}
	
	public String getChildDeliveryPlace() {
		return childDeliveryPlace;
	}
	public void setChildDeliveryPlace(String childDeliveryPlace) {
		this.childDeliveryPlace = childDeliveryPlace;
	}
	public String getChildDateOfBirth() {
		return childDateOfBirth;
	}
	public void setChildDateOfBirth(String childDateOfBirth) {
		this.childDateOfBirth = childDateOfBirth;
	}
	
	public double getBirthWeight() {
		return birthWeight;
	}
	public void setBirthWeight(double birthWeight) {
		this.birthWeight = birthWeight;
	}
	
	public int getChildHivStatus() {
		return childHivStatus;
	}
	public void setChildHivStatus(int childHivStatus) {
		this.childHivStatus = childHivStatus;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getPregResult() {
		return pregResult;
	}
	public void setPregResult(String pregResult) {
		this.pregResult = pregResult;
	}
	public String getChildDiagnosedDate() {
		return childDiagnosedDate;
	}
	public void setChildDiagnosedDate(String childDiagnosedDate) {
		this.childDiagnosedDate = childDiagnosedDate;
	}
	
}

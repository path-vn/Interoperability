package com.globits.hiv.receive.dto;

import java.util.List;

import com.globits.hiv.receive.valueset.AdapterObjectType;



public class AdapterObjectDto {
	private HivCaseReportDto singleData;
	private List<HivCaseReportDto> listData;
	private AdapterObjectType objectType;
	//private int objectType =0;//Mac dinh =0 nghia la danh sach
	public HivCaseReportDto getSingleData() {
		return singleData;
	}
	public void setSingleData(HivCaseReportDto singleData) {
		this.singleData = singleData;
	}
	public List<HivCaseReportDto> getListData() {
		return listData;
	}
	public void setListData(List<HivCaseReportDto> listData) {
		this.listData = listData;
	}
	public AdapterObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(AdapterObjectType objectType) {
		this.objectType = objectType;
	}
}

package com.globits.adapter.opcassist.dto;

import java.io.Serializable;
import java.util.List;

import com.globits.adapter.opcassist.types.AdapterObjectType;

public class AdapterObjectDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OpcAssistDto singleData;
	private List<OpcAssistDto> listData;
	private AdapterObjectType objectType;
//	private int objectType =0;//Mac dinh =0 nghia la danh sach
	public OpcAssistDto getSingleData() {
		return singleData;
	}
	public void setSingleData(OpcAssistDto singleData) {
		this.singleData = singleData;
	}
	public List<OpcAssistDto> getListData() {
		return listData;
	}
	public void setListData(List<OpcAssistDto> listData) {
		this.listData = listData;
	}
	public AdapterObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(AdapterObjectType objectType) {
		this.objectType = objectType;
	}
}

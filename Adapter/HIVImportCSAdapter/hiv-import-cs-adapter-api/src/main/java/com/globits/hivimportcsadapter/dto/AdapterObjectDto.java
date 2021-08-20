package com.globits.hivimportcsadapter.dto;

import java.util.List;


public class AdapterObjectDto {
	private HtcElogDto singleData;
	private List<HtcElogDto> listData;
	//private int objectType =0;//Mac dinh =0 nghia la danh sach
	public HtcElogDto getSingleData() {
		return singleData;
	}
	public void setSingleData(HtcElogDto singleData) {
		this.singleData = singleData;
	}
	public List<HtcElogDto> getListData() {
		return listData;
	}
	public void setListData(List<HtcElogDto> listData) {
		this.listData = listData;
	}
}

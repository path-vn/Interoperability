package dto;

import java.util.List;

import types.AdapterObjectType;

public class AdapterObjectDto {
	private PdmaDto singleData;
	private List<PdmaDto> listData;
	private AdapterObjectType objectType;
	//private int objectType =0;//Mac dinh =0 nghia la danh sach
	public PdmaDto getSingleData() {
		return singleData;
	}
	public void setSingleData(PdmaDto singleData) {
		this.singleData = singleData;
	}
	public List<PdmaDto> getListData() {
		return listData;
	}
	public void setListData(List<PdmaDto> listData) {
		this.listData = listData;
	}
	public AdapterObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(AdapterObjectType objectType) {
		this.objectType = objectType;
	}
}

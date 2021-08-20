package dto;

import java.util.List;

import types.AdapterObjectType;

public class AdapterObjectDto {
	private PatientDto singleData;
	private List<PatientDto> listData;
	private AdapterObjectType objectType;

//	private int objectType =0;//Mac dinh =0 nghia la danh sach
	public PatientDto getSingleData() {
		return singleData;
	}

	public void setSingleData(PatientDto singleData) {
		this.singleData = singleData;
	}

	public List<PatientDto> getListData() {
		return listData;
	}

	public void setListData(List<PatientDto> listData) {
		this.listData = listData;
	}

	public AdapterObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(AdapterObjectType objectType) {
		this.objectType = objectType;
	}

}

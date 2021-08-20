package dto;

import java.util.Date;
import java.util.List;

public class TuberculosisDto {
    public Date NgayChanDoan; //TB Diagnosis Date
    public List<HivDPLaoDto> listTPT;
    public List<HivDTLaoDto> listTbTreatment;
	public Date getNgayChanDoan() {
		return NgayChanDoan;
	}
	public void setNgayChanDoan(Date ngayChanDoan) {
		NgayChanDoan = ngayChanDoan;
	}
	public List<HivDPLaoDto> getListTPT() {
		return listTPT;
	}
	public void setListTPT(List<HivDPLaoDto> listTPT) {
		this.listTPT = listTPT;
	}
	public List<HivDTLaoDto> getListTbTreatment() {
		return listTbTreatment;
	}
	public void setListTbTreatment(List<HivDTLaoDto> listTbTreatment) {
		this.listTbTreatment = listTbTreatment;
	}

}

package dto;

import java.util.Date;

public class HivDTLaoDto {

    public Date NgayChanDoan; //Diagnosis Date
	private Date NgayBatDau; // Date TB Treatment started	
	private Date NgayKetThuc; // Date TB Treatment completed	
	private String CoSoDieuTri; // Place TB Treatment provided	
	
	public Date getNgayChanDoan() {
		return NgayChanDoan;
	}
	public void setNgayChanDoan(Date ngayChanDoan) {
		NgayChanDoan = ngayChanDoan;
	}
	public Date getNgayBatDau() {
		return NgayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return NgayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}
	public String getCoSoDieuTri() {
		return CoSoDieuTri;
	}
	public void setCoSoDieuTri(String coSoDieuTri) {
		CoSoDieuTri = coSoDieuTri;
	}

}

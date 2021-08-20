package dto;

import java.util.Date;

public class HivDPLaoDto {

	private Date NgayBatDau; // Date TPT started
	private Date NgayKetThuc; // Date TPT completed
	private String CoSoDieuTri; // Place TPT provided
	private String KetQua; // TPT completed

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

	public String getKetQua() {
		return KetQua;
	}

	public void setKetQua(String ketQua) {
		KetQua = ketQua;
	}

}

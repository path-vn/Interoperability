package dto;

import java.util.Date;

public class HbvHcvDto {
	private Date NgayXetNghiem; // diagnosisDate
	private Date NgayBatDau; // treatmentStartDate
	private Date NgayKetThuc; // treatmentEndDate
	private String KetQua; // diagnosis result

	public Date getNgayXetNghiem() {
		return NgayXetNghiem;
	}

	public void setNgayXetNghiem(Date ngayXetNghiem) {
		NgayXetNghiem = ngayXetNghiem;
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

	public String getKetQua() {
		return KetQua;
	}

	public void setKetQua(String ketQua) {
		KetQua = ketQua;
	}

}

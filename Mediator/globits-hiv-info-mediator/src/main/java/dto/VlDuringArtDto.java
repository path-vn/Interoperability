package dto;

import java.util.Date;

public class VlDuringArtDto {
	private Date Ngay; // Date of viral load test
	private Date NgayLayMau; // dateOfSpecimenCollection
	private String KetQua; // testResult

	public Date getNgayLayMau() {
		return NgayLayMau;
	}

	public void setNgayLayMau(Date ngayLayMau) {
		NgayLayMau = ngayLayMau;
	}

	public String getKetQua() {
		return KetQua;
	}

	public void setKetQua(String ketQua) {
		KetQua = ketQua;
	}

	public Date getNgay() {
		return Ngay;
	}

	public void setNgay(Date ngay) {
		Ngay = ngay;
	}

}

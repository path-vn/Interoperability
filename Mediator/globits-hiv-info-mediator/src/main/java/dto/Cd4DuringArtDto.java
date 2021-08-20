package dto;

import java.util.Date;

public class Cd4DuringArtDto {

	private Date Ngay; // Date of CD4 test
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

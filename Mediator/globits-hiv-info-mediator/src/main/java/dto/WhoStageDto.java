package dto;

import java.util.Date;

public class WhoStageDto {

	private String MaSo;
	private String TenGiaiDoanLamSang;
	private Date NgayBatDau;
	private Date NgayKetThuc;
	private Long IDGiaiDoanLamSang;

	public String getMaSo() {
		return MaSo;
	}

	public void setMaSo(String maSo) {
		MaSo = maSo;
	}

	public String getTenGiaiDoanLamSang() {
		return TenGiaiDoanLamSang;
	}

	public void setTenGiaiDoanLamSang(String tenGiaiDoanLamSang) {
		TenGiaiDoanLamSang = tenGiaiDoanLamSang;
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

	public Long getIDGiaiDoanLamSang() {
		return IDGiaiDoanLamSang;
	}

	public void setIDGiaiDoanLamSang(Long iDGiaiDoanLamSang) {
		IDGiaiDoanLamSang = iDGiaiDoanLamSang;
	}

}

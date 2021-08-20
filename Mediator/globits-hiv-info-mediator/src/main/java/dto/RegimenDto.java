package dto;

import java.util.Date;

public class RegimenDto {
	
	private Date NgayBatDau; 
	private Date NgayKetThuc;
	private String TenPhacDo;
	private Integer IDPhacDo;
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
	public String getTenPhacDo() {
		return TenPhacDo;
	}
	public void setTenPhacDo(String tenPhacDo) {
		TenPhacDo = tenPhacDo;
	}
	public Integer getIDPhacDo() {
		return IDPhacDo;
	}
	public void setIDPhacDo(Integer iDPhacDo) {
		IDPhacDo = iDPhacDo;
	}

}

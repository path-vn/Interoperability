package dto;

import java.util.Date;
import java.util.List;

public class ArvTreatmentDto {

	private Date NgayBatDau; // dateOfTreatmentStart
	private Date NgayKetThuc; // dateOfTreatmentStop
	private String TenCoSoDieuTri; // placeOfInitiation
	private String TenLoaiDangKy; //
	private String TenTinhTrangDieuTri; //
	private List<RegimenDto> ListArvRegimen;

	private List<WhoStageDto> ListWhoStage;

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

	public String getTenCoSoDieuTri() {
		return TenCoSoDieuTri;
	}

	public void setTenCoSoDieuTri(String tenCoSoDieuTri) {
		TenCoSoDieuTri = tenCoSoDieuTri;
	}

	public String getTenLoaiDangKy() {
		return TenLoaiDangKy;
	}

	public void setTenLoaiDangKy(String tenLoaiDangKy) {
		TenLoaiDangKy = tenLoaiDangKy;
	}

	public String getTenTinhTrangDieuTri() {
		return TenTinhTrangDieuTri;
	}

	public void setTenTinhTrangDieuTri(String tenTinhTrangDieuTri) {
		TenTinhTrangDieuTri = tenTinhTrangDieuTri;
	}

	public List<RegimenDto> getListArvRegimen() {
		return ListArvRegimen;
	}

	public void setListArvRegimen(List<RegimenDto> listArvRegimen) {
		ListArvRegimen = listArvRegimen;
	}

	public List<WhoStageDto> getListWhoStage() {
		return ListWhoStage;
	}

	public void setListWhoStage(List<WhoStageDto> listWhoStage) {
		ListWhoStage = listWhoStage;
	}

}

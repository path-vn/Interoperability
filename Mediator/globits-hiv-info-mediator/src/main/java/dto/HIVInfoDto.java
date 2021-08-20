package dto;

import java.util.Date;
import java.util.List;

import com.globits.fhir.hiv.utils.Valueset;

public class HIVInfoDto {
	private String MaSo;
	private String HoTen;
	private String SoCMND;
	private String QuocGia;
	private String MaBHYT;
    private Date ngayHetHanBHYT;
	private String DuongPhoHK;
	private String ToHK;
	private String SoNhaHK;
	private Valueset XaHK;
	private Valueset HuyenHK;
	private Valueset TinhHK;
	private String DuongPhoTT;
	private String ToTT;
	private String SoNhaTT;
	private Valueset XaTT;
	private Valueset HuyenTT;
	private Valueset TinhTT;
	private Byte IDDanToc;
	private String DanToc;
	private Byte IDGioiTinh;
	private String GioiTinh;
	private String NamSinh;
	private Byte IdNgheNghiep;
	private String NgheNghiep;
	private Byte IdDoiTuong;
	private String DoiTuong;
	private Byte IdNguyCo;
	private String NguyCo;
	private Byte IdDuongLay;
	private String DuongLay;
	
	private HivRecencyTest hivRecencyTest;
	private List<Cd4BeforeArtDto> listHivCd4BeforeArt;
	private List<Cd4DuringArtDto> listCd4DuringArt;
	private List<HivDiagnosisDto> listHivDiagnosis;
	private List<VlDuringArtDto> listVlDuringArt;
	private List<DrugResistanceTestDto> listDrugResistanceTest;
	private List<ArvTreatmentDto> listArvTreatment;
	private List<RegimenDto> listRegimen;
	private List<HbvHcvDto> listHbv;
	private List<HbvHcvDto> listHcv;
	private TuberculosisDto tuberculosis;
	private List<PregnanciesDto> listPregnancies;
    private Date dateOfDeath;
	private String causeOfDeath;
    
	public String getQuocGia() {
		return QuocGia;
	}
	public void setQuocGia(String quocGia) {
		QuocGia = quocGia;
	}
	public String getMaSo() {
		return MaSo;
	}
	public void setMaSo(String maSo) {
		MaSo = maSo;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getSoCMND() {
		return SoCMND;
	}
	public void setSoCMND(String soCMND) {
		SoCMND = soCMND;
	}
	public String getDuongPhoHK() {
		return DuongPhoHK;
	}
	public void setDuongPhoHK(String duongPhoHK) {
		DuongPhoHK = duongPhoHK;
	}
	public String getToHK() {
		return ToHK;
	}
	public void setToHK(String toHK) {
		ToHK = toHK;
	}
	public String getSoNhaHK() {
		return SoNhaHK;
	}
	public void setSoNhaHK(String soNhaHK) {
		SoNhaHK = soNhaHK;
	}
	public String getDuongPhoTT() {
		return DuongPhoTT;
	}
	public void setDuongPhoTT(String duongPhoTT) {
		DuongPhoTT = duongPhoTT;
	}
	public String getToTT() {
		return ToTT;
	}
	public void setToTT(String toTT) {
		ToTT = toTT;
	}
	public String getSoNhaTT() {
		return SoNhaTT;
	}
	public void setSoNhaTT(String soNhaTT) {
		SoNhaTT = soNhaTT;
	}
	public String getDanToc() {
		return DanToc;
	}
	public void setDanToc(String danToc) {
		DanToc = danToc;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getNamSinh() {
		return NamSinh;
	}
	public void setNamSinh(String namSinh) {
		NamSinh = namSinh;
	}
	public Byte getIdNgheNghiep() {
		return IdNgheNghiep;
	}
	public void setIdNgheNghiep(Byte idNgheNghiep) {
		IdNgheNghiep = idNgheNghiep;
	}
	public String getNgheNghiep() {
		return NgheNghiep;
	}
	public void setNgheNghiep(String ngheNghiep) {
		NgheNghiep = ngheNghiep;
	}
	public Byte getIdDoiTuong() {
		return IdDoiTuong;
	}
	public void setIdDoiTuong(Byte idDoiTuong) {
		IdDoiTuong = idDoiTuong;
	}
	public String getDoiTuong() {
		return DoiTuong;
	}
	public void setDoiTuong(String doiTuong) {
		DoiTuong = doiTuong;
	}
	
	public Byte getIdNguyCo() {
		return IdNguyCo;
	}
	public void setIdNguyCo(Byte idNguyCo) {
		IdNguyCo = idNguyCo;
	}
	public String getNguyCo() {
		return NguyCo;
	}
	public void setNguyCo(String nguyCo) {
		NguyCo = nguyCo;
	}
	public Byte getIdDuongLay() {
		return IdDuongLay;
	}
	public void setIdDuongLay(Byte idDuongLay) {
		IdDuongLay = idDuongLay;
	}
	public String getDuongLay() {
		return DuongLay;
	}
	public void setDuongLay(String duongLay) {
		DuongLay = duongLay;
	}
	public Byte getIDGioiTinh() {
		return IDGioiTinh;
	}
	public void setIDGioiTinh(Byte iDGioiTinh) {
		IDGioiTinh = iDGioiTinh;
	}
	public Byte getIDDanToc() {
		return IDDanToc;
	}
	public void setIDDanToc(Byte iDDanToc) {
		IDDanToc = iDDanToc;
	}
	public List<Cd4BeforeArtDto> getListHivCd4BeforeArt() {
		return listHivCd4BeforeArt;
	}
	public void setListHivCd4BeforeArt(List<Cd4BeforeArtDto> listHivCd4BeforeArt) {
		this.listHivCd4BeforeArt = listHivCd4BeforeArt;
	}
	public List<HivDiagnosisDto> getListHivDiagnosis() {
		return listHivDiagnosis;
	}
	public void setListHivDiagnosis(List<HivDiagnosisDto> listHivDiagnosis) {
		this.listHivDiagnosis = listHivDiagnosis;
	}
	public List<Cd4DuringArtDto> getListCd4DuringArt() {
		return listCd4DuringArt;
	}
	public void setListCd4DuringArt(List<Cd4DuringArtDto> listCd4DuringArt) {
		this.listCd4DuringArt = listCd4DuringArt;
	}
	public List<VlDuringArtDto> getListVlDuringArt() {
		return listVlDuringArt;
	}
	public void setListVlDuringArt(List<VlDuringArtDto> listVlDuringArt) {
		this.listVlDuringArt = listVlDuringArt;
	}
	public List<DrugResistanceTestDto> getListDrugResistanceTest() {
		return listDrugResistanceTest;
	}
	public void setListDrugResistanceTest(List<DrugResistanceTestDto> listDrugResistanceTest) {
		this.listDrugResistanceTest = listDrugResistanceTest;
	}
	public List<ArvTreatmentDto> getListArvTreatment() {
		return listArvTreatment;
	}
	public void setListArvTreatment(List<ArvTreatmentDto> listArvTreatment) {
		this.listArvTreatment = listArvTreatment;
	}
	public List<RegimenDto> getListRegimen() {
		return listRegimen;
	}
	public void setListRegimen(List<RegimenDto> listRegimen) {
		this.listRegimen = listRegimen;
	}
	public List<HbvHcvDto> getListHbv() {
		return listHbv;
	}
	public void setListHbv(List<HbvHcvDto> listHbv) {
		this.listHbv = listHbv;
	}
	public List<HbvHcvDto> getListHcv() {
		return listHcv;
	}
	public void setListHcv(List<HbvHcvDto> listHcv) {
		this.listHcv = listHcv;
	}
	public List<PregnanciesDto> getListPregnancies() {
		return listPregnancies;
	}
	public void setListPregnancies(List<PregnanciesDto> listPregnancies) {
		this.listPregnancies = listPregnancies;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	public String getMaBHYT() {
		return MaBHYT;
	}
	public void setMaBHYT(String maBHYT) {
		MaBHYT = maBHYT;
	}
	public Date getNgayHetHanBHYT() {
		return ngayHetHanBHYT;
	}
	public void setNgayHetHanBHYT(Date ngayHetHanBHYT) {
		this.ngayHetHanBHYT = ngayHetHanBHYT;
	}
	public Valueset getXaHK() {
		return XaHK;
	}
	public void setXaHK(Valueset xaHK) {
		XaHK = xaHK;
	}
	public Valueset getHuyenHK() {
		return HuyenHK;
	}
	public void setHuyenHK(Valueset huyenHK) {
		HuyenHK = huyenHK;
	}
	public Valueset getTinhHK() {
		return TinhHK;
	}
	public void setTinhHK(Valueset tinhHK) {
		TinhHK = tinhHK;
	}
	public Valueset getXaTT() {
		return XaTT;
	}
	public void setXaTT(Valueset xaTT) {
		XaTT = xaTT;
	}
	public Valueset getHuyenTT() {
		return HuyenTT;
	}
	public void setHuyenTT(Valueset huyenTT) {
		HuyenTT = huyenTT;
	}
	public Valueset getTinhTT() {
		return TinhTT;
	}
	public void setTinhTT(Valueset tinhTT) {
		TinhTT = tinhTT;
	}
	public HivRecencyTest getHivRecencyTest() {
		return hivRecencyTest;
	}
	public void setHivRecencyTest(HivRecencyTest hivRecencyTest) {
		this.hivRecencyTest = hivRecencyTest;
	}
	public TuberculosisDto getTuberculosis() {
		return tuberculosis;
	}
	public void setTuberculosis(TuberculosisDto tuberculosis) {
		this.tuberculosis = tuberculosis;
	}
	
}

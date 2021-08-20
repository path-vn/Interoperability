using GlobitsHivInfoAdapter.Entities;
using GlobitsHivInfoAdapter;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class HivInfoDto
    {
        public String MaSo { get; set; }
        public String HoTen { get; set; }
        public Byte IDDanToc { get; set; }
        public String DanToc { get; set; }
        public String QuocGia = "Việt Nam";
        public Byte IDGioiTinh { get; set; }
        public String GioiTinh { get; set; }
        public Nullable<short> NamSinh { get; set; }
        public String SoCMND { get; set; }
        public String MaBHYT { get; set; }
        public Byte IdNgheNghiep { get; set; }
        public String NgheNghiep { get; set; }
        public Byte IdDoiTuong { get; set; }
        public String DoiTuong { get; set; }
        public Byte IdNguyCo { get; set; }
        public String NguyCo { get; set; }
        public Byte IdDuongLay { get; set; }
        public String DuongLay { get; set; }
        public ValuesetDto XaHK { get; set; }
        public ValuesetDto HuyenHK { get; set; }
        public ValuesetDto TinhHK { get; set; }
        public String DuongPhoHK { get; set; }
        public String ToHK { get; set; }
        public String SoNhaHK { get; set; }
        public ValuesetDto XaTT { get; set; }
        public ValuesetDto HuyenTT { get; set; }
        public ValuesetDto TinhTT { get; set; }
        public String DuongPhoTT { get; set; }
        public String ToTT { get; set; }
        public String SoNhaTT { get; set; }
        public List<HIV_CD4_Gets_Result> listCd4DuringArt;
        public List<HivDiagnosisDto> listHivDiagnosis;
        public List<HIV_VL> listVlDuringArt;
        public List<HIV_KhangThuoc> listDrugResistanceTest;
        public List<ArvTreatmentDto> listArvTreatment;
        public List<HIV_PhacDo> listRegimen;
        public List<HIV_VGB_VGC> listHbv;
        public List<HIV_VGB_VGC> listHcv;
        public HivRecencyTestDto hivRecencyTest;
        public TuberculosisDto tuberculosis { get; set; }
        public List<HIV_MangThai> listPregnancies;
        public Nullable<DateTime> dateOfDeath { get; set; }
        public String causeOfDeath { get; set; }

    }
}

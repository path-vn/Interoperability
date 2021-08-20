using GlobitsHivInfoAdapter;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    [Serializable]
    class HivInfoEntity
    {
        public String MaSo { get; set; }
        public String HoTen { get; set; }
        public Byte IDDanToc { get; set; }
        public String DanToc { get; set; }
        public Byte IDGioiTinh { get; set; }
        public String GioiTinh { get; set; }
        public Nullable<short> NamSinh { get; set; }
        public String SoCMND { get; set; }
        public String NgheNghiep { get; set; }
        public Byte IdDoiTuong { get; set; }
        public String DoiTuong { get; set; }
        public Byte IdNguyCo { get; set; }
        public String NguyCo { get; set; }
        public Byte IdDuongLay { get; set; }
        public String DuongLay { get; set; }
        public String XaHK { get; set; }
        public String HuyenHK { get; set; }
        public String TinhHK { get; set; }
        public String DuongPhoHK { get; set; }
        public String ToHK { get; set; }
        public String SoNhaHK { get; set; }
        public String XaTT { get; set; }
        public String HuyenTT { get; set; }
        public String TinhTT { get; set; }
        public String DuongPhoTT { get; set; }
        public String ToTT { get; set; }
        public String SoNhaTT { get; set; }
        public Nullable<System.DateTime> NgayXetNghiem { get; set; }
        public String TenNoiXN { get; set; }
        public String TenNoiLayMau { get; set; }
        public List<HIV_CD4_Gets_Result> listHivCd4BeforeArt { get; set; }
    }
}

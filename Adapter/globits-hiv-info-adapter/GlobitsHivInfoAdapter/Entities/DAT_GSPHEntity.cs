using GlobitsHivInfoAdapter;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    [Serializable]
    class DAT_GSPHEntity
    {
        public string MaSo { get; set; }
        public string HoTen { get; set; }
        public Nullable<byte> IDDanToc { get; set; }
        public string SoCMND { get; set; }
        public Nullable<byte> IDGioiTinh { get; set; }
        public Nullable<short> NamSinh { get; set; }
        public Nullable<int> IDXaHK { get; set; }
        public Nullable<int> IDHuyenHK { get; set; }
        public Nullable<short> IDTinhHK { get; set; }
        public string DuongPhoHK { get; set; }
        public string ToHK { get; set; }
        public string SoNhaHK { get; set; }
        public Nullable<int> IDXaTT { get; set; }
        public Nullable<int> IDHuyenTT { get; set; }
        public Nullable<short> IDTinhTT { get; set; }
        public string DuongPhoTT { get; set; }
        public string ToTT { get; set; }
        public string SoNhaTT { get; set; }
        public Nullable<byte> IDNgheNghiep { get; set; }
        public Nullable<byte> IDDoiTuong { get; set; }
        public Nullable<byte> IDDuongLay { get; set; }
        public Nullable<byte> IDNguyCo { get; set; }
        public Nullable<byte> IDTinhTrang { get; set; }
        public string GhiChu { get; set; }
        public int MaNoiDangKy { get; set; }
        public Nullable<bool> DaGui { get; set; }
        public string MaEclinica { get; set; }

        public virtual ADM_NoiDangKy ADM_NoiDangKy { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChuyenDoiGSPH> DAT_ChuyenDoiGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_GuiDuLieuGSPH> DAT_GuiDuLieuGSPH { get; set; }
        public virtual DAT_MauGSPH_AIDS DAT_MauGSPH_AIDS { get; set; }
        public virtual DIC_DanToc DIC_DanToc { get; set; }
        public virtual DIC_DuongLay DIC_DuongLay { get; set; }
        public virtual DIC_GioiTinh DIC_GioiTinh { get; set; }
        public virtual DIC_NgheNghiep DIC_NgheNghiep { get; set; }
        public virtual DIC_NguyCoLayNhiem DIC_NguyCoLayNhiem { get; set; }
        public virtual DIC_QuanHuyen DIC_QuanHuyen { get; set; }
        public virtual DIC_QuanHuyen DIC_QuanHuyen1 { get; set; }
        public virtual DIC_TinhThanh DIC_TinhThanh { get; set; }
        public virtual DIC_TinhThanh DIC_TinhThanh1 { get; set; }
        public virtual DIC_TinhTrangHienTai DIC_TinhTrangHienTai { get; set; }
        public virtual DIC_XaPhuong DIC_XaPhuong { get; set; }
        public virtual DIC_XaPhuong DIC_XaPhuong1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH_TheoDoi> DAT_MauGSPH_TheoDoi { get; set; }
        public virtual DAT_MauGSPH_TuVong DAT_MauGSPH_TuVong { get; set; }
        public virtual DAT_NhapLieuGSPH DAT_NhapLieuGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_PhienNhanDuLieu> DAT_PhienNhanDuLieu { get; set; }
    }
}

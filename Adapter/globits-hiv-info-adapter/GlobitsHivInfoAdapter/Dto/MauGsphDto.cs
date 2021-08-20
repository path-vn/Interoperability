using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class MauGsphDto
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
        public Nullable<System.DateTime> NgayXetNghiem { get; set; }
        public Nullable<short> IDNoiLayMau { get; set; }
        public Nullable<short> IDNoiXetNghiem { get; set; }
        public Nullable<System.DateTime> NgayCDAIDS { get; set; }
        public Nullable<short> IDNoiCD { get; set; }
        public Nullable<byte> IDTrangThaiDieuTri { get; set; }
        public Nullable<System.DateTime> NgayTuVong { get; set; }
        public Nullable<byte> IDLoaiBenh { get; set; }
        public Nullable<int> DaRaSoat { get; set; }
        public Nullable<short> IDNoiDieuTri { get; set; }
        public Nullable<System.DateTime> NgayDieuTri { get; set; }
        public bool isNgoaiTinh { get; set; }

    }
}

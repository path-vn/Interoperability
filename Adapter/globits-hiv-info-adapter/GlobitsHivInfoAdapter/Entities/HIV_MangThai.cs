using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    public class HIV_MangThai
    {
        public string MaSo { get; set; }
        public Nullable<System.DateTime> KyKinhCuoi { get; set; }
        public Nullable<System.DateTime> NgayDuKienSinh { get; set; }
        public string DieuTriDuPhongMeCon { get; set; }
        public string KetQuaMangThai { get; set; }
        public Nullable<System.DateTime> NgaySinh { get; set; }
        public string DieuTriDuPhongCon { get; set; }
        public string ChanDoanHIVCon { get; set; }
        public Nullable<System.DateTime> NgayChanDoan { get; set; }
        public string TinhTrangDieuTriCon { get; set; }
        public Nullable<long> IDCoSoDieuTriCon { get; set; }
        public Nullable<System.Guid> UUID { get; set; }
        public Nullable<System.Guid> UUIDBenhNhan { get; set; }
        public string NguoiNhap { get; set; }
        public Nullable<System.DateTime> NgayNhap { get; set; }
        public Nullable<System.DateTime> NgaySua { get; set; }
        public string NguoiSua { get; set; }
        public string TenCoSo { get; set; }
        public string Xoa { get; set; }
        public string Sua { get; set; }
        public string TenCoSoDieuTriCon { get; set; }
    }
}

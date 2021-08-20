using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    public class HIV_CoSoDieuTri
    {
        public Nullable<long> IDCoSoDieuTri { get; set; }
        public Nullable<long> IDLoaiDangKy { get; set; }
        public String MaSo { get; set; }
        public Nullable<System.DateTime> NgayBatDau { get; set; }
        public Nullable<System.DateTime> NgayKetThuc { get; set; }
        public String TenCoSoDieuTri { get; set; }
        public String Xoa { get; set; }
        public String Sua { get; set; }
        public Nullable<System.Guid> UUID { get; set; }
        public Nullable<System.Guid> UUIDBenhNhan { get; set; }
        public Nullable<System.DateTime> NgayNhap { get; set; }
        public Nullable<System.DateTime> NgaySua { get; set; }
        public String NguoiNhap { get; set; }
        public String NguoiSua { get; set; }
        public Nullable<long> IDTinhTrangDieuTri { get; set; }
    }
}

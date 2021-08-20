using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    public class HIV_VGB_VGC
    {
        public string MaSo { get; set; }
        public Nullable<System.DateTime> NgayXetNghiem { get; set; }
        public string KetQua { get; set; }
        public string DaDieuTri { get; set; }
        public Nullable<System.DateTime> NgayBatDau { get; set; }
        public Nullable<System.DateTime> NgayKetThuc { get; set; }
        public Nullable<System.Guid> UUID { get; set; }
        public Nullable<System.Guid> UUIDBenhNhan { get; set; }
        public Nullable<System.DateTime> NgayNhap { get; set; }
        public Nullable<System.DateTime> NgaySua { get; set; }
        public string NguoiNhap { get; set; }
        public string NguoiSua { get; set; }
        public string Xoa { get; set; }
        public string Sua { get; set; }
    }
}

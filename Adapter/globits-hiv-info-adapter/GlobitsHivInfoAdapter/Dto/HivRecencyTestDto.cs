using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class HivRecencyTestDto
    {
        public string MaSo { get; set; }
        public Nullable<System.DateTime> Ngay { get; set; }
        public Nullable<System.DateTime> NgayXetNghiemSangLoc { get; set; }
        public string KetQuaSangLoc { get; set; }
        public Nullable<System.DateTime> NgayXetNghiemTaiLuong { get; set; }
        public string KetQuaTaiLuong { get; set; }
        public string KetQua { get; set; }
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

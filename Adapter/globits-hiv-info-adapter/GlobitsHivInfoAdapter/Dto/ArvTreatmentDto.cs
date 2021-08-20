using GlobitsHivInfoAdapter.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class ArvTreatmentDto
    {
        public Nullable<System.DateTime> NgayBatDau { get; set; }
        public Nullable<System.DateTime> NgayKetThuc { get; set; }
        public String TenCoSoDieuTri { get; set; }
        public String TenLoaiDangKy { get; set; }
        public String TenTinhTrangDieuTri { get; set; }

        public List<HIV_PhacDo> ListArvRegimen;

        public List<HIV_GiaiDoanLamSang> ListWhoStage;

    }
}

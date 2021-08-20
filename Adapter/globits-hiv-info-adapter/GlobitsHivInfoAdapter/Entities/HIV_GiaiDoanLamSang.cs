using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    public class HIV_GiaiDoanLamSang
    {
        public string MaSo { get; set; }
        public String TenGiaiDoanLamSang { get; set; }
        public Nullable<System.DateTime> NgayBatDau { get; set; }
        public Nullable<System.DateTime> NgayKetThuc { get; set; }
        public Nullable<long> IDGiaiDoanLamSang { get; set; }
        public Nullable<System.Guid> UUID { get; set; }
        public Nullable<System.Guid> UUIDBenhNhan { get; set; }
    }
}

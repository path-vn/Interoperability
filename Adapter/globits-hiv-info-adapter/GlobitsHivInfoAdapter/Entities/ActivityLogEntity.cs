using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Entities
{
    [Serializable]
    class ActivityLogEntity
    {
        public string Id { get; set; }
        public Nullable<DateTime> create_date { get; set; }
        public string create_by { get; set; }
        public string modified_by { get; set; }
        public Nullable<DateTime> modify_date { get; set; }
        public string uuid_key { get; set; }
        public string content_log { get; set; }
        public Nullable<DateTime> log_date { get; set; }
        public string log_type { get; set; }
        public string module_log { get; set; }
        public string user_name { get; set; }
    }
}

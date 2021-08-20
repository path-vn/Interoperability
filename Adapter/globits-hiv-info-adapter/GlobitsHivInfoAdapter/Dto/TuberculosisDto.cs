using GlobitsHivInfoAdapter.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class TuberculosisDto
    {
        public Nullable<DateTime> NgayChanDoan { get; set; } //TB Diagnosis Date
        public List<HIV_DPLao> listTPT;
        public List<HIV_DTLao> listTbTreatment;
    }
}

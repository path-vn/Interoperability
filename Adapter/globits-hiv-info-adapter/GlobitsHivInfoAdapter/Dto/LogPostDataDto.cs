using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class LogPostDataDto
    {
        public string index { get; set; }
        public string startDate { get; set; }
        public string endDate { get; set; }
        public List<string> datas = new List<string>();
        public string content { get; set; }
        public string status { get; set; }
    }
}

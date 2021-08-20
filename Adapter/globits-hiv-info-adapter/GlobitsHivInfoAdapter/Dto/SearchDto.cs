using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class SearchDto
    {
        public DateTime? dateStart = null;
        public int pageIndex = 1;
        public int pageSize = 10;
        public bool isRunJob = false;
        public String text { get; set; }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Dto
{
    public class SerializeObjectDto
    {
        public DateTime dateGetData { get; set; }
        public DateTime timeStartJob { get; set; }
        public int totalRecord { get; set; }
        public bool status = false;
    }
}

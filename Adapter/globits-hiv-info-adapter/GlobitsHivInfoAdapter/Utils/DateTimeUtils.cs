using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Utils
{
    public class DateTimeUtils
    {
        private string format = "dd/MM/yyyy HH:mm:ss Z";
        internal string convertToString(DateTime now)
        {
            return now.ToString(format);
        }
    }
}

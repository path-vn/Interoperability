using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Utils
{
    public class CheckUtils
    {
        public static bool checkNotNull(object obj)
        {
            if (obj != null && !obj.Equals("{}") && !String.IsNullOrWhiteSpace(obj.ToString()))
            {
                return true;
            }
            return false;
        }
    }
}

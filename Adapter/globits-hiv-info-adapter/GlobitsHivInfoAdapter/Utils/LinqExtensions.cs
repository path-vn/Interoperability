using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Utils
{
    public static class LinqExtensions
    {
        public static IEnumerable<TResult> TakeIfNotNull<TResult>(this IEnumerable<TResult> source, int? count)
        {
            return (!count.HasValue && count > 0) ? source : source.Take(count.Value);
        }
    }
}

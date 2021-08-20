using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Utils
{
    public class Constants
    {
        public static string openhimURL = (ConfigurationManager.AppSettings["openhimURL"] != null) ? ConfigurationManager.AppSettings["openhimURL"].ToString() : "https://openhim.globits.net:5000";
        public static string folderNameSerializeObject = (ConfigurationManager.AppSettings["folderNameSerializeObject"] != null) ? ConfigurationManager.AppSettings["folderNameSerializeObject"].ToString() : "config";
        public static string fileNameSerializeObject = (ConfigurationManager.AppSettings["fileNameSerializeObject"] != null) ? ConfigurationManager.AppSettings["fileNameSerializeObject"].ToString() : "SerializeObject";
        public static string folderNameLogPostData = (ConfigurationManager.AppSettings["folderNameLogPostData"] != null) ? ConfigurationManager.AppSettings["folderNameLogPostData"].ToString() : "logs";
        public static string fileNameLogPostData = (ConfigurationManager.AppSettings["fileNameLogPostData"] != null) ? ConfigurationManager.AppSettings["fileNameLogPostData"].ToString() : "LogPostData";
        public static bool writeLogPostData = (ConfigurationManager.AppSettings["writeLogPostData"] != null) ? bool.Parse(ConfigurationManager.AppSettings["writeLogPostData"].ToString()) : false;
        public static int pageSizePostData = (ConfigurationManager.AppSettings["pageSizePostDataToMediator"] != null) ? Int32.Parse(ConfigurationManager.AppSettings["pageSizePostDataToMediator"].ToString()) : 100;
        public static bool IsUsingEncodeData = (ConfigurationManager.AppSettings["IsUsingEncodeData"] != null) ? Boolean.Parse(ConfigurationManager.AppSettings["IsUsingEncodeData"].ToString()) : false;

        public static DateTime? startDatePostDataToMediator = (ConfigurationManager.AppSettings["startDatePostDataToMediator"] != null) ? (DateTime?)DateTime.Parse(ConfigurationManager.AppSettings["startDatePostDataToMediator"].ToString()) : null;
        public static int pageSizeGetDataInJob = (ConfigurationManager.AppSettings["pageSizeGetDataInJob"] != null) ? Int32.Parse(ConfigurationManager.AppSettings["pageSizeGetDataInJob"].ToString()) : 0;
        public static int pageIndexGetDataInJob = (ConfigurationManager.AppSettings["pageIndexGetDataInJob"] != null) ? Int32.Parse(ConfigurationManager.AppSettings["pageIndexGetDataInJob"].ToString()) : 1;
        public static bool autoStartJob = (ConfigurationManager.AppSettings["autoStartJob"] != null) ? bool.Parse(ConfigurationManager.AppSettings["autoStartJob"].ToString()) : false;
        public static int hourStartJob = (ConfigurationManager.AppSettings["hourStartJob"] != null) ? Int32.Parse(ConfigurationManager.AppSettings["hourStartJob"].ToString()) : 00;
        public static int minuteStartJob = (ConfigurationManager.AppSettings["minuteStartJob"] != null) ? Int32.Parse(ConfigurationManager.AppSettings["minuteStartJob"].ToString()) : 00;

    }
}

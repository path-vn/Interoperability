using GlobitsHivInfoAdapter.Utils;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GlobitsHivInfoAdapter
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Scheduler sc = new Scheduler();
            bool autoStartJob = bool.Parse(ConfigurationManager.AppSettings["autoStartJob"].ToString());
            if (autoStartJob)
            {
                Task<bool> checkJob = sc.checkScheduleStart();
                if (!checkJob.Result)
                {
                    var job = sc.StartAsync();
                }
            }

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new FormMain());
        }
    }
}

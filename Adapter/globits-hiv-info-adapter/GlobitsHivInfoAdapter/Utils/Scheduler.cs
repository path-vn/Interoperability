using GlobitsHivInfoAdapter.service;
using Quartz;
using Quartz.Impl;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.Utils
{
    public class Scheduler
    {
        IScheduler scheduler;
        IJobDetail job;
        ITrigger trigger;
        public async Task StartAsync()
        {
            if (Constants.autoStartJob)
            {
                scheduler = await StdSchedulerFactory.GetDefaultScheduler();
                var unused = scheduler.Start();
                job = JobBuilder.Create<Job>().Build();

                trigger = TriggerBuilder.Create()
                    .WithDailyTimeIntervalSchedule
                      (s =>
                         s.WithIntervalInHours(24)
                        .OnEveryDay()
                        .StartingDailyAt(TimeOfDay.HourAndMinuteOfDay(Constants.hourStartJob, Constants.minuteStartJob))
                      )

                    .Build();
                var unused1 = scheduler.ScheduleJob(job, trigger);
            }
        }

        public async Task<bool> checkScheduleStart()
        {
            scheduler = await StdSchedulerFactory.GetDefaultScheduler();
            return scheduler.IsStarted;
        }

        public void Stop()
        {
            if (scheduler.IsStarted)
            {
                scheduler.Shutdown();
            }
        }
    }
}

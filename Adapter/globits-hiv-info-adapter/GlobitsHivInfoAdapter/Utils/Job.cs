using GlobitsHivInfoAdapter.Dto;
using GlobitsHivInfoAdapter.service;
using Quartz;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GlobitsHivInfoAdapter.Utils
{
    class Job : IJob
    {
        HivInfoService hivInfoService = new HivInfoService();
        SyncData syncData = new SyncData();
        FileManager fileManager = new FileManager();

        Task IJob.Execute(IJobExecutionContext context)
        {
            SerializeObjectDto file = fileManager.DeSerializeObject<SerializeObjectDto>(Constants.folderNameSerializeObject, Constants.fileNameSerializeObject);
            if (file != null)
            {
                if (file.status && file.timeStartJob != null)
                {
                    Constants.startDatePostDataToMediator = file.timeStartJob;
                }
                else
                {
                    Constants.startDatePostDataToMediator = file.dateGetData;
                }
            }

            var dateNow = DateTime.Now;
            SerializeObjectDto serializeObject = new SerializeObjectDto();
            serializeObject.dateGetData = Constants.startDatePostDataToMediator.GetValueOrDefault();
            serializeObject.timeStartJob = dateNow;
            List<MauGsphDto> listData = hivInfoService.LoadDataWithParams(Constants.startDatePostDataToMediator, Constants.pageIndexGetDataInJob, Constants.pageSizeGetDataInJob);
            if (listData != null && listData.Count() > 0)
            {
                serializeObject.status = syncData.postDataToMediator(listData, false);
                serializeObject.totalRecord = listData.Count();
            }
            else
            {
                serializeObject.totalRecord = 0;
            }
            fileManager.SerializeObject(serializeObject, Constants.folderNameSerializeObject, Constants.fileNameSerializeObject);
            return null;
        }
    }
}

using GlobitsHivInfoAdapter.Dto;
using GlobitsHivInfoAdapter.service;
using Newtonsoft.Json;
using RestSharp;
using RestSharp.Authenticators;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Configuration;
using System.Data.Entity.Core.Objects;
using System.IO;
using System.Linq;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GlobitsHivInfoAdapter.Utils
{
    public class SyncData
    {
        FileManager fileManager = new FileManager();
        HivInfoService hivInfoService = new HivInfoService();
        DateTimeUtils dateTimeUtils = new DateTimeUtils();

        String messageLog = "";
        bool result = true;

        public bool postDataToMediator<T>(List<T> listData, bool showMessage)
        {
            if (listData != null && listData.Count() > 0)
            {
                object obj = listData.FirstOrDefault();
                var objType = ObjectContext.GetObjectType(obj.GetType());

                var dateNow = DateTime.Now;
                messageLog = "";
                int pageNumber = 1;
                int RESULTS_PER_PAGE = Constants.pageSizePostData;
                int totalPage = listData.Count() / RESULTS_PER_PAGE;
                if ((listData.Count() % RESULTS_PER_PAGE) > 0)
                {
                    totalPage++;
                }
                List<HivInfoDto> listNew = null;
                DateTime startDate = DateTime.Now;
                List<LogPostDataDto> listLog = new List<LogPostDataDto>();
                for (int i = pageNumber; i <= totalPage; i++)
                {
                    int to = pageNumber * RESULTS_PER_PAGE;
                    int from = to - RESULTS_PER_PAGE;
                    if (to > listData.Count())
                    {
                        to = listData.Count();
                    }
                    listNew = new List<HivInfoDto>();
                    if (objType == typeof(HivInfoDto))
                    {
                        listNew = (List<HivInfoDto>)(Object)listData.Skip(from).TakeIfNotNull(RESULTS_PER_PAGE).ToList();
                    }
                    else if (objType == typeof(MauGsphDto))
                    {
                        listNew = hivInfoService.LoadDataDetails((List<MauGsphDto>)(Object)listData.Skip(from).TakeIfNotNull(RESULTS_PER_PAGE).ToList());
                    }
                    else
                    {
                        break;
                    }
                    string index = pageNumber + "." + " /from: " + from + " /to: " + to;

                    LogPostDataDto log = this.post(listNew);
                    log.index = index;
                    log.datas = listNew.Select(x => x.MaSo).ToList();
                    listLog.Add(log);

                    messageLog += index + Environment.NewLine;
                    messageLog += log.content;
                    pageNumber++;
                    Thread.Sleep(5000);
                }
                DateTime endDate = DateTime.Now;
                if (showMessage)
                {
                    this.ShowMessage(messageLog, startDate, endDate);
                }

                if (Constants.writeLogPostData)
                {
                    string fileName = Constants.fileNameLogPostData + "_" + dateNow.ToString("yyyy-MM-dd_HH-mm-ss");
                    fileManager.SerializeObject(listLog, Constants.folderNameLogPostData, fileName);
                }
            }
            return result;
        }

        private LogPostDataDto post(List<HivInfoDto> listData)
        {
            LogPostDataDto log = new LogPostDataDto();
            log.startDate = dateTimeUtils.convertToString(DateTime.Now);
            string fileName = "certificate.pem";
            string path = Path.Combine(Environment.CurrentDirectory, @"globits\", fileName);

            ServicePointManager.ServerCertificateValidationCallback = new System.Net.Security.RemoteCertificateValidationCallback(AcceptAllCertifications);
            X509Certificate2 certificate = new X509Certificate2(path);

            // Handle any certificate errors on the certificate from the server.
            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12;
            // You must change the URL to point to your Web server.
            String username = "GlobitsClient";
            String password = "123456";
            var client = new RestClient(Constants.openhimURL);
            client.Authenticator = new HttpBasicAuthenticator(username, password);
            var request = new RestRequest("/hivinfomediator/1");
            String encoded = System.Convert.ToBase64String(System.Text.Encoding.GetEncoding("ISO-8859-1").GetBytes(username + ":" + password));
            request.AddHeader("Authorization", "Basic " + encoded);
            if (Constants.IsUsingEncodeData)
            {
                //thêm đoạn convert DateTimeZoneHandling để có dữ liệu time zone khi bắn lên mediator -> gjson convert date tránh lỗi
                //com.google.gson.JsonSyntaxException: 2008-12-03T00:00:00
                //java.text.ParseException: Failed to parse date ["2008-12-03T00:00:00"]: No time zone indicator
                String body = JsonConvert.SerializeObject(listData, new JsonSerializerSettings
                {
                    DateTimeZoneHandling = DateTimeZoneHandling.Utc
                });
                //
                request.AddHeader("Content-Type", "text/plain;charset=UTF-8");
                body = System.Convert.ToBase64String(System.Text.Encoding.GetEncoding(System.Text.Encoding.UTF8.CodePage).GetBytes(body));
                request.AddJsonBody(body);
            }
            else
            {
                request.AddHeader("Content-Type", "application/json;charset=UTF-8");
                request.AddJsonBody(listData);
            }
            var response = client.Post(request);
            if (!response.IsSuccessful || response.StatusCode != HttpStatusCode.OK)
            {
                this.result = false;
            }

            log.endDate = dateTimeUtils.convertToString(DateTime.Now);
            log.content = response.Content;
            log.status = response.StatusCode.ToString();
            return log;
        }

        private void ShowMessage(string messageLog, DateTime startDate, DateTime endDate)
        {
            // Get reference to the dialog type.
            var dialogTypeName = "System.Windows.Forms.PropertyGridInternal.GridErrorDlg";
            var dialogType = typeof(Form).Assembly.GetType(dialogTypeName);

            // Create dialog instance.
            var dialog = (Form)Activator.CreateInstance(dialogType, new PropertyGrid());

            // Populate relevant properties on the dialog instance.
            dialog.Text = "Thông Báo";
            string messageContent = "Hoàn thành" + Environment.NewLine;
            messageContent += "Bắt đầu: " + startDate + Environment.NewLine;
            messageContent += "Kết thúc: " + endDate;
            dialogType.GetProperty("Details").SetValue(dialog, messageLog, null);
            dialogType.GetProperty("Message").SetValue(dialog, messageContent, null);
            // Display dialog.
            var result = dialog.ShowDialog();
        }
        public static bool AcceptAllCertifications(object sender, System.Security.Cryptography.X509Certificates.X509Certificate certification, System.Security.Cryptography.X509Certificates.X509Chain chain, System.Net.Security.SslPolicyErrors sslPolicyErrors)
        {
            return true;
        }
    }
}
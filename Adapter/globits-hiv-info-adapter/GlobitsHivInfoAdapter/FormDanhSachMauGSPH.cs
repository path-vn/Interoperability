using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Core.EntityClient;
using System.Data.Entity.Validation;
using System.Data.SqlClient;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using GlobitsHivInfoAdapter;
using GlobitsHivInfoAdapter.Dto;
using GlobitsHivInfoAdapter.Entities;
using GlobitsHivInfoAdapter.service;
using GlobitsHivInfoAdapter.Utils;
using IdentityServer4.Models;
using Newtonsoft.Json;
using RestSharp;
using RestSharp.Authenticators;

namespace GlobitsHivInfoAdapter
{
    public partial class FormDanhSachMauGSPH : Form
    {
        private HivInfoService hivInfoService = new HivInfoService();
        static List<HivInfoDto> listHivInfoEntity = new List<HivInfoDto>();
        SyncData syncData = new SyncData();
        private int pageIndex = 1;
        private int pageSize = 10;
        private int totalPage = 0;
        public FormDanhSachMauGSPH()
        {
            InitializeComponent();
        }

        private void FormDanhSachMauGSPH_Load(object sender, EventArgs e)
        {
            cboPageSize.SelectedIndex = cboPageSize.Items.IndexOf(pageSize.ToString());
            this.updateTotalPage();
        }

        private void updateTotalPage()
        {
            var searchDto = new SearchDto() { pageIndex = this.pageIndex, pageSize = this.pageSize };
            totalPage = 0;
            totalPage = hivInfoService.countRecord(searchDto);
            this.updateBtnPage();
            this.txtCurrentIndex.Text = pageIndex.ToString();
            this.txtIndexRecord.Text = totalPage.ToString();
            this.loadData();
        }

        private void loadData()
        {
            var searchDto = new SearchDto() { pageIndex = this.pageIndex, pageSize = this.pageSize };
            listHivInfoEntity = hivInfoService.LoadData(searchDto);
            this.dataGridViewDSMauGSPH.DataSource = listHivInfoEntity;
            this.dataGridViewDSMauGSPH.Refresh();
        }

        public static bool AcceptAllCertifications(object sender, System.Security.Cryptography.X509Certificates.X509Certificate certification, System.Security.Cryptography.X509Certificates.X509Chain chain, System.Net.Security.SslPolicyErrors sslPolicyErrors)
        {
            return true;
        }

        void postHTTPS()
        {
            if (listHivInfoEntity != null && listHivInfoEntity.Count() > 0)
            {
                syncData.postDataToMediator(listHivInfoEntity, true);
            }
        }

        private void cboPageSize_SelectedIndexChanged(object sender, EventArgs e)
        {
            ComboBox comboBox = (ComboBox)sender;
            int index = int.Parse(cboPageSize.SelectedItem.ToString());
            if (index > 0)
            {
                pageIndex = 1;
                pageSize = index;
                updateTotalPage();
            }

        }

        private void btnPrePage_Click(object sender, EventArgs e)
        {
            if (pageIndex > 1)
            {
                pageIndex--;
                this.updateTotalPage();
            }
            this.updateBtnPage();
        }

        private void btnNextPage_Click(object sender, EventArgs e)
        {
            if (pageIndex < totalPage)
            {
                pageIndex++;
                this.updateTotalPage();
            }
            this.updateBtnPage();
        }

        private void updateBtnPage()
        {
            if (pageIndex == 1)
            {
                this.btnPrePage.Enabled = false;
            }
            else
            {
                this.btnPrePage.Enabled = true;
            }
            if (pageIndex == totalPage)
            {
                this.btnNextPage.Enabled = false;
            }
            else
            {
                this.btnNextPage.Enabled = true;
            }
        }

        private void btnPostData_Click(object sender, EventArgs e)
        {
            using (WaitForm form = new WaitForm(postHTTPS))
            {
                form.ShowDialog(this);
            }
        }

        private void txtCurrentIndex_Leave(object sender, EventArgs e)
        {
            currentIndex_Change();
        }

        private void currentIndex_Change()
        {
            int currentIndex = (!String.IsNullOrEmpty(this.txtCurrentIndex.Text) ? Convert.ToInt32(this.txtCurrentIndex.Text) : 0);
            if (currentIndex >= 1 && currentIndex <= totalPage)
            {
                this.pageIndex = currentIndex;
                updateBtnPage();
            }
            else
            {
                MessageBox.Show("Số trang không hợp lý.", "Thông báo");
                this.pageIndex = 1;
                txtCurrentIndex.Text = this.pageIndex.ToString();
                updateBtnPage();
            }
            loadData();
        }

        private void txtCurrentIndex_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
                currentIndex_Change();
        }

        private void btnLoadFile_Click(object sender, EventArgs e)
        {
            //SerializeObjectDto file = fileManager.DeSerializeObject<SerializeObjectDto>(fileNameSerializeObject);
            //if (file != null)
            //{
            //    if (file.dateGetData != null)
            //    {

            //    }
            //}
        }
    }
}

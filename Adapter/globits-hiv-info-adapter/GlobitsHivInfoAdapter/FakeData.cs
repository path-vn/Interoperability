using GlobitsHivInfoAdapter.Dto;
using GlobitsHivInfoAdapter.Entities;
using GlobitsHivInfoAdapter.service;
using GlobitsHivInfoAdapter.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Entity;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GlobitsHivInfoAdapter
{
    public partial class FakeData : Form
    {
        private hivcdcEntities hivcdcEntities = new hivcdcEntities();
        private HivInfoService hivInfoService = new HivInfoService();
        static List<DAT_MauGSPH> listHivInfoEntity = new List<DAT_MauGSPH>();
        static List<DAT_MauGSPH_NgoaiTinh> listHivInfoEntity_NgoaiTinh = new List<DAT_MauGSPH_NgoaiTinh>();
        private HIVInfoSecurityUtil hivInfoSecurity = new HIVInfoSecurityUtil();
        private HivBaoHiemService hivBaoHiemService = new HivBaoHiemService();
        private int totalPage = 0;
        Random random = new Random();

        List<string> ho = new List<string> { "Nguyễn", "Phạm", "Trần", "Đào", "Phùng", "Trần", "Lê", "Phan", "Vũ", "Võ", "Đặng", "Bùi", "Đỗ", "Hồ", "Ngô", "Lý", "Dương" };
        List<string> tenDemNam = new List<string> { "Văn", "Đức", "Anh", "Tuấn", "Quang" };
        List<string> tenDemNu = new List<string> { "Thị", "Vân", "Anh", "Quỳnh", "Huyền", "Kiều", "Khánh", "Kim", "Lan", "Minh", "Ngọc", "Nhã", "Như", "Phương", "Thanh" };
        List<string> tenNam = new List<string> { "Văn", "Đức", "Anh", "Tuấn", "Quang", "Dũng", "Nam", "Toàn", "Quân", "Tuấn", "Thông", "Trung" };
        List<string> tenNu = new List<string> { "Thị", "Vân", "Anh", "Quỳnh", "Huyền", "Mai", "Hương", " Ngân", "Quyên", "Quỳnh", "Thủy", "Trang", "Vân", "Vi", "Thảo", "Hồng", "Kiết Trinh", "Anh", "Diễm", "Dung", "Giang", "Hạnh", "Hoa" };
        List<string> maBHYT = new List<string> { "HT9", "TC9", "CN9", "CB9" };

        public FakeData()
        {
            InitializeComponent();
        }

        private void FakeData_Load(object sender, EventArgs e)
        {

        }

        private void btnIdNumber_Click(object sender, EventArgs e)
        {

        }

        private void btnFakeData_Click(object sender, EventArgs e)
        {
            DialogResult dr = MessageBox.Show("Bạn có chắc chắc muốn thay đổi dữ liệu trong database thành dữ liệu giả không? Nhớ backup database trước khi thực hiện.", "Làm giả dữ liệu"
                , MessageBoxButtons.YesNo, MessageBoxIcon.Information);

            if (dr == DialogResult.Yes)
            {
                using (var context = new hivcdcEntities())
                {
                    int total = 0;
                    total = context.DAT_MauGSPH.Count() + context.DAT_MauGSPH_NgoaiTinh.Count();
                    lblProcess.Text = "0/" + total;
                    var searchDto = new SearchDto() { dateStart = null, pageIndex = 0, pageSize = 0 };
                    progressBar1.Minimum = 0; //Đặt giá trị nhỏ nhất cho ProgressBar
                    progressBar1.Maximum = total; //Đặt giá trị lớn nhất cho ProgressBar
                    progressBar1.Step = 1;
                    int progressBarValue = 0;
                    progressBar1.Value = progressBarValue;
                    //listHivInfoEntity = hivInfoService.LoadListDAT_MauGSPH(searchDto);
                    listHivInfoEntity = context.DAT_MauGSPH.ToList();
                    listHivInfoEntity_NgoaiTinh = context.DAT_MauGSPH_NgoaiTinh.ToList();
                    foreach (var item in listHivInfoEntity)
                    {
                        if (item.MaSo != null)
                        {
                            var maso = item.MaSo;
                            item.HoTen = hivInfoSecurity.Encrypt(fakeHoTen(item.IDGioiTinh), true);
                            item.SoCMND = hivInfoSecurity.Encrypt(fakeSoCMND(), true);
                            item.ToHK = item.ToTT = hivInfoSecurity.Encrypt("Tổ " + random.Next(1, 100), true);
                            item.SoNhaHK = item.SoNhaTT = hivInfoSecurity.Encrypt("Số " + random.Next(1, 1000), true);
                            item.DuongPhoHK = item.DuongPhoTT = hivInfoSecurity.Encrypt("", true);

                            String radomBHYT = fakeSoBHYT();
                            context.Database.ExecuteSqlCommand(" UPDATE HIV_BaoHiem " +
                                " SET [MaThe] = {0} " +
                                " WHERE [MaSo] = {1}", radomBHYT, maso);

                            progressBar1.Value = progressBarValue;
                            progressBar1.PerformStep();
                            progressBar1.Refresh();
                            lblProcess.Text = progressBar1.Value.ToString() + "/" + total;
                            progressBarValue++;
                        }
                    }
                    foreach (var item in listHivInfoEntity_NgoaiTinh)
                    {
                        if (item.MaSo != null)
                        {
                            var maso = item.MaSo;
                            var hoTen = hivInfoSecurity.Encrypt(fakeHoTen(item.IDGioiTinh), true);
                            var cmndFake = hivInfoSecurity.Encrypt(fakeSoCMND(), true);
                            var toFake = hivInfoSecurity.Encrypt("Tổ " + random.Next(1, 100), true);
                            var soNhaFake = hivInfoSecurity.Encrypt("Số " + random.Next(1, 1000), true);

                            context.Database.ExecuteSqlCommand(" UPDATE DAT_MauGSPH_NgoaiTinh " +
                                " SET [SoCMND] = {0}, [ToHK] = {1}, [ToTT] = {2}, [SoNhaHK] = {3}, [SoNhaTT] = {4}, [HoTen] = {5} " +
                                " WHERE [MaSo] = {6} AND [SoCMND] = {7} ",
                                cmndFake, toFake, toFake, soNhaFake, soNhaFake, hoTen, maso, item.SoCMND);

                            String radomBHYT = fakeSoBHYT();
                            context.Database.ExecuteSqlCommand(" UPDATE HIV_BaoHiem " +
                                " SET [MaThe] = {0} " +
                                " WHERE [MaSo] = {1}", radomBHYT, maso);

                            progressBar1.Value = progressBarValue;
                            progressBar1.PerformStep();
                            progressBar1.Refresh();
                            lblProcess.Text = progressBar1.Value.ToString() + "/" + total;
                            progressBarValue++;
                        }
                    }
                    context.SaveChanges();
                    MessageBox.Show("Thành công.", "Thông báo");
                    progressBar1 = new ProgressBar();

                }
            }
            else if (dr == DialogResult.Cancel)
            {
                //
            }
        }

        private string fakeSoCMND()
        {
            string idNumber = random.Next(10, 19) + random.Next(1, 99999).ToString("D5") + random.Next(1, 99999).ToString("D5");
            return idNumber;
        }

        private string fakeSoBHYT()
        {
            string ma = maBHYT[random.Next(0, maBHYT.Count - 1)];
            string idNumber = ma + random.Next(1, 999999).ToString("D6") + random.Next(1, 999999).ToString("D6");
            return idNumber;
        }

        private string fakeHoTen(byte? iDGioiTinh)
        {
            string hoTen = "";
            string ho = "";
            string tenDem = "";
            string ten = "";
            DIC_GioiTinh gioitinh = getGioiTinh(iDGioiTinh);
            if (gioitinh != null && gioitinh.TenGioiTinh.ToLower().Equals("nam"))
            {
                ho = randomHo(1);
                tenDem = randomTenDem(1);
                ten = randomTen(1);
            }
            else if (gioitinh != null && gioitinh.TenGioiTinh.ToLower().Equals("nữ"))
            {
                ho = randomHo(2);
                tenDem = randomTenDem(2);
                ten = randomTen(2);
            }
            else
            {
                int type = random.Next(0, 1);
                ho = randomHo(type);
                tenDem = randomTenDem(type);
                ten = randomTen(type);
            }

            hoTen = ho + " " + tenDem + " " + ten;
            return hoTen;
        }

        private string randomTen(int type)
        {
            if (type == 1)
            {
                return tenNam[random.Next(0, tenNam.Count - 1)];
            }
            else
            {
                return tenNu[random.Next(0, tenNu.Count - 1)];
            }
        }

        private string randomTenDem(int type)
        {
            if (type == 1)
            {
                return tenDemNam[random.Next(0, tenDemNam.Count - 1)];
            }
            else
            {
                return tenDemNu[random.Next(0, tenDemNu.Count - 1)];
            }
        }

        private string randomHo(int type)
        {
            if (type == 1)
            {
                return ho[random.Next(0, ho.Count - 1)];
            }
            else
            {
                return ho[random.Next(0, ho.Count - 1)];
            }
        }

        private DIC_GioiTinh getGioiTinh(byte? iDGioiTinh)
        {
            DIC_GioiTinh gioitinh = null;
            if (iDGioiTinh != null)
            {
                gioitinh = iDGioiTinh != null ? (from o in hivcdcEntities.DIC_GioiTinh where o.IDGioiTinh == iDGioiTinh select o).FirstOrDefault() : null;
            }
            return gioitinh;
        }
    }
}

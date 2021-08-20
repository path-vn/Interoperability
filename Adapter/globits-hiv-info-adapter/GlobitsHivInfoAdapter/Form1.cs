using GlobitsHivInfoAdapter.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GlobitsHivInfoAdapter
{
    public partial class FormMain : Form
    {
        public FormMain()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        }

        private void danhSáchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormDanhSachMauGSPH formDanhSachMauGSPH = new FormDanhSachMauGSPH();
            formDanhSachMauGSPH.MdiParent = this;
            formDanhSachMauGSPH.Show();
        }

        private void FakeData_Click(object sender, EventArgs e)
        {
            FakeData form = new FakeData();
            form.MdiParent = this;
            form.Show();
        }
    }
}

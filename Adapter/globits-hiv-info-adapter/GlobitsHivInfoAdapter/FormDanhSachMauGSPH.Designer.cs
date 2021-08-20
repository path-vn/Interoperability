
namespace GlobitsHivInfoAdapter
{
    partial class FormDanhSachMauGSPH
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridViewDSMauGSPH = new System.Windows.Forms.DataGridView();
            this.btnPostData = new System.Windows.Forms.Button();
            this.panel1 = new System.Windows.Forms.Panel();
            this.panel3 = new System.Windows.Forms.Panel();
            this.panel2 = new System.Windows.Forms.Panel();
            this.panel5 = new System.Windows.Forms.Panel();
            this.panel4 = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.btnPrePage = new System.Windows.Forms.Button();
            this.lblPageSize = new System.Windows.Forms.Label();
            this.txtCurrentIndex = new System.Windows.Forms.TextBox();
            this.cboPageSize = new System.Windows.Forms.ComboBox();
            this.txtIndexRecord = new System.Windows.Forms.TextBox();
            this.btnNextPage = new System.Windows.Forms.Button();
            this.pageSetupDialog1 = new System.Windows.Forms.PageSetupDialog();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewDSMauGSPH)).BeginInit();
            this.panel1.SuspendLayout();
            this.panel3.SuspendLayout();
            this.panel2.SuspendLayout();
            this.panel5.SuspendLayout();
            this.panel4.SuspendLayout();
            this.SuspendLayout();
            // 
            // dataGridViewDSMauGSPH
            // 
            this.dataGridViewDSMauGSPH.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewDSMauGSPH.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridViewDSMauGSPH.Location = new System.Drawing.Point(0, 0);
            this.dataGridViewDSMauGSPH.Margin = new System.Windows.Forms.Padding(2);
            this.dataGridViewDSMauGSPH.Name = "dataGridViewDSMauGSPH";
            this.dataGridViewDSMauGSPH.RowHeadersWidth = 51;
            this.dataGridViewDSMauGSPH.RowTemplate.Height = 24;
            this.dataGridViewDSMauGSPH.Size = new System.Drawing.Size(1170, 523);
            this.dataGridViewDSMauGSPH.TabIndex = 0;
            // 
            // btnPostData
            // 
            this.btnPostData.Location = new System.Drawing.Point(20, 11);
            this.btnPostData.Margin = new System.Windows.Forms.Padding(2);
            this.btnPostData.Name = "btnPostData";
            this.btnPostData.Size = new System.Drawing.Size(148, 43);
            this.btnPostData.TabIndex = 1;
            this.btnPostData.Text = "POST";
            this.btnPostData.UseVisualStyleBackColor = true;
            this.btnPostData.Click += new System.EventHandler(this.btnPostData_Click);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.panel3);
            this.panel1.Controls.Add(this.panel2);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1170, 593);
            this.panel1.TabIndex = 2;
            // 
            // panel3
            // 
            this.panel3.Controls.Add(this.dataGridViewDSMauGSPH);
            this.panel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel3.Location = new System.Drawing.Point(0, 70);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(1170, 523);
            this.panel3.TabIndex = 3;
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.panel5);
            this.panel2.Controls.Add(this.panel4);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel2.Location = new System.Drawing.Point(0, 0);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(1170, 70);
            this.panel2.TabIndex = 2;
            // 
            // panel5
            // 
            this.panel5.Controls.Add(this.btnPostData);
            this.panel5.Dock = System.Windows.Forms.DockStyle.Left;
            this.panel5.Location = new System.Drawing.Point(0, 0);
            this.panel5.Name = "panel5";
            this.panel5.Size = new System.Drawing.Size(191, 70);
            this.panel5.TabIndex = 9;
            // 
            // panel4
            // 
            this.panel4.Controls.Add(this.label1);
            this.panel4.Controls.Add(this.btnPrePage);
            this.panel4.Controls.Add(this.lblPageSize);
            this.panel4.Controls.Add(this.txtCurrentIndex);
            this.panel4.Controls.Add(this.cboPageSize);
            this.panel4.Controls.Add(this.txtIndexRecord);
            this.panel4.Controls.Add(this.btnNextPage);
            this.panel4.Dock = System.Windows.Forms.DockStyle.Right;
            this.panel4.Location = new System.Drawing.Point(567, 0);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(603, 70);
            this.panel4.TabIndex = 8;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(407, 26);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(12, 13);
            this.label1.TabIndex = 8;
            this.label1.Text = "/";
            // 
            // btnPrePage
            // 
            this.btnPrePage.Location = new System.Drawing.Point(252, 21);
            this.btnPrePage.Name = "btnPrePage";
            this.btnPrePage.Size = new System.Drawing.Size(75, 23);
            this.btnPrePage.TabIndex = 5;
            this.btnPrePage.Text = "Trước";
            this.btnPrePage.UseVisualStyleBackColor = true;
            this.btnPrePage.Click += new System.EventHandler(this.btnPrePage_Click);
            // 
            // lblPageSize
            // 
            this.lblPageSize.AutoSize = true;
            this.lblPageSize.Location = new System.Drawing.Point(59, 25);
            this.lblPageSize.Name = "lblPageSize";
            this.lblPageSize.Size = new System.Drawing.Size(109, 13);
            this.lblPageSize.TabIndex = 3;
            this.lblPageSize.Text = "Số bản ghi trên trang:";
            // 
            // txtCurrentIndex
            // 
            this.txtCurrentIndex.Location = new System.Drawing.Point(349, 23);
            this.txtCurrentIndex.Name = "txtCurrentIndex";
            this.txtCurrentIndex.Size = new System.Drawing.Size(52, 20);
            this.txtCurrentIndex.TabIndex = 7;
            this.txtCurrentIndex.KeyUp += new System.Windows.Forms.KeyEventHandler(this.txtCurrentIndex_KeyUp);
            this.txtCurrentIndex.Leave += new System.EventHandler(this.txtCurrentIndex_Leave);
            // 
            // cboPageSize
            // 
            this.cboPageSize.FormattingEnabled = true;
            this.cboPageSize.Items.AddRange(new object[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "10",
            "20",
            "25",
            "50",
            "100",
            "1000",
            "5000"});
            this.cboPageSize.Location = new System.Drawing.Point(174, 22);
            this.cboPageSize.Name = "cboPageSize";
            this.cboPageSize.Size = new System.Drawing.Size(72, 21);
            this.cboPageSize.TabIndex = 2;
            this.cboPageSize.SelectedIndexChanged += new System.EventHandler(this.cboPageSize_SelectedIndexChanged);
            // 
            // txtIndexRecord
            // 
            this.txtIndexRecord.Location = new System.Drawing.Point(425, 23);
            this.txtIndexRecord.Name = "txtIndexRecord";
            this.txtIndexRecord.ReadOnly = true;
            this.txtIndexRecord.Size = new System.Drawing.Size(52, 20);
            this.txtIndexRecord.TabIndex = 4;
            // 
            // btnNextPage
            // 
            this.btnNextPage.Location = new System.Drawing.Point(509, 21);
            this.btnNextPage.Name = "btnNextPage";
            this.btnNextPage.Size = new System.Drawing.Size(75, 23);
            this.btnNextPage.TabIndex = 6;
            this.btnNextPage.Text = "Sau";
            this.btnNextPage.UseVisualStyleBackColor = true;
            this.btnNextPage.Click += new System.EventHandler(this.btnNextPage_Click);
            // 
            // FormDanhSachMauGSPH
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1170, 593);
            this.Controls.Add(this.panel1);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "FormDanhSachMauGSPH";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Danh sách mẫu GSPH";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.FormDanhSachMauGSPH_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewDSMauGSPH)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel3.ResumeLayout(false);
            this.panel2.ResumeLayout(false);
            this.panel5.ResumeLayout(false);
            this.panel4.ResumeLayout(false);
            this.panel4.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewDSMauGSPH;
        private System.Windows.Forms.Button btnPostData;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Label lblPageSize;
        private System.Windows.Forms.ComboBox cboPageSize;
        private System.Windows.Forms.PageSetupDialog pageSetupDialog1;
        private System.Windows.Forms.TextBox txtIndexRecord;
        private System.Windows.Forms.Button btnNextPage;
        private System.Windows.Forms.Button btnPrePage;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.TextBox txtCurrentIndex;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel5;
    }
}
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace GlobitsHivInfoAdapter
{
    using System;
    using System.Collections.Generic;
    
    public partial class DAT_ChuyenDoiDiaGioi
    {
        public int IDChuyenDoi { get; set; }
        public System.DateTime NgayChuyenDoi { get; set; }
        public Nullable<short> IDTinhCu { get; set; }
        public Nullable<int> IDHuyenCu { get; set; }
        public Nullable<int> IDXaCu { get; set; }
        public Nullable<short> IDTinhMoi { get; set; }
        public Nullable<int> IDHuyenMoi { get; set; }
        public Nullable<int> IDXaMoi { get; set; }
        public short IDNguoiChuyen { get; set; }
    
        public virtual ADM_NguoiDung ADM_NguoiDung { get; set; }
        public virtual DIC_QuanHuyen DIC_QuanHuyen { get; set; }
        public virtual DIC_QuanHuyen DIC_QuanHuyen1 { get; set; }
        public virtual DIC_TinhThanh DIC_TinhThanh { get; set; }
        public virtual DIC_TinhThanh DIC_TinhThanh1 { get; set; }
        public virtual DIC_XaPhuong DIC_XaPhuong { get; set; }
        public virtual DIC_XaPhuong DIC_XaPhuong1 { get; set; }
    }
}

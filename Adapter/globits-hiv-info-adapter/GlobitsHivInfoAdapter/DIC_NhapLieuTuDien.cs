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
    
    public partial class DIC_NhapLieuTuDien
    {
        public string TenBang { get; set; }
        public string MaBanGhi { get; set; }
        public Nullable<System.DateTime> NgayNhap { get; set; }
        public Nullable<System.DateTime> NgaySua { get; set; }
        public Nullable<System.DateTime> NgayXoa { get; set; }
        public Nullable<short> IDNguoiNhap { get; set; }
        public Nullable<short> IDNguoiSua { get; set; }
        public Nullable<short> IDNguoiXoa { get; set; }
    
        public virtual ADM_NguoiDung ADM_NguoiDung { get; set; }
        public virtual ADM_NguoiDung ADM_NguoiDung1 { get; set; }
        public virtual ADM_NguoiDung ADM_NguoiDung2 { get; set; }
    }
}

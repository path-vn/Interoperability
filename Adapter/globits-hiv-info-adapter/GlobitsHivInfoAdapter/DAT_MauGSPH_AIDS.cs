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
    
    public partial class DAT_MauGSPH_AIDS
    {
        public string MaSo { get; set; }
        public Nullable<System.DateTime> NgayCDAIDS { get; set; }
        public Nullable<short> IDNoiXN { get; set; }
        public Nullable<byte> IDTrangThaiDieuTri { get; set; }
        public int MaNoiDangKy { get; set; }
    
        public virtual DAT_MauGSPH DAT_MauGSPH { get; set; }
        public virtual DIC_TrangThaiDieuTri DIC_TrangThaiDieuTri { get; set; }
        public virtual DAT_MauGSPH_AIDS_DieuTri DAT_MauGSPH_AIDS_DieuTri { get; set; }
    }
}

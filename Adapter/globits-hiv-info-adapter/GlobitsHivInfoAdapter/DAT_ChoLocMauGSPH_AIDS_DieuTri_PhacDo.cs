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
    
    public partial class DAT_ChoLocMauGSPH_AIDS_DieuTri_PhacDo
    {
        public long IDDieuTriPhacDo { get; set; }
        public string MaSo { get; set; }
        public byte IDPhacDo { get; set; }
        public System.DateTime NgayBatDau { get; set; }
        public Nullable<System.DateTime> NgayKetThuc { get; set; }
        public Nullable<byte> IDLyDo { get; set; }
        public Nullable<byte> BacPhacDo { get; set; }
    
        public virtual DAT_ChoLocMauGSPH_AIDS_DieuTri DAT_ChoLocMauGSPH_AIDS_DieuTri { get; set; }
        public virtual DIC_LyDoThayThePhacDo DIC_LyDoThayThePhacDo { get; set; }
        public virtual DIC_PhacDo DIC_PhacDo { get; set; }
    }
}
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
    
    public partial class DIC_CoSoDieuTri
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public DIC_CoSoDieuTri()
        {
            this.DAT_ChoLocMauGSPH_AIDS_DieuTri = new HashSet<DAT_ChoLocMauGSPH_AIDS_DieuTri>();
            this.DAT_MauGSPH_AIDS_DieuTri = new HashSet<DAT_MauGSPH_AIDS_DieuTri>();
        }
    
        public short IDCoSo { get; set; }
        public string TenCoSo { get; set; }
        public Nullable<short> IDTinh { get; set; }
        public bool SuDung { get; set; }
        public bool DaXoa { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH_AIDS_DieuTri> DAT_ChoLocMauGSPH_AIDS_DieuTri { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH_AIDS_DieuTri> DAT_MauGSPH_AIDS_DieuTri { get; set; }
        public virtual DIC_TinhThanh DIC_TinhThanh { get; set; }
    }
}
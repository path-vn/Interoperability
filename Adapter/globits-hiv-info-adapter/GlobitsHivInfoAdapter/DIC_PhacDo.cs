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
    
    public partial class DIC_PhacDo
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public DIC_PhacDo()
        {
            this.DAT_ChoLocMauGSPH_AIDS_DieuTri_PhacDo = new HashSet<DAT_ChoLocMauGSPH_AIDS_DieuTri_PhacDo>();
            this.DAT_MauGSPH_AIDS_DieuTri_PhacDo = new HashSet<DAT_MauGSPH_AIDS_DieuTri_PhacDo>();
        }
    
        public byte IDPhacDo { get; set; }
        public string TenPhacDo { get; set; }
        public bool SuDung { get; set; }
        public bool DaXoa { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH_AIDS_DieuTri_PhacDo> DAT_ChoLocMauGSPH_AIDS_DieuTri_PhacDo { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH_AIDS_DieuTri_PhacDo> DAT_MauGSPH_AIDS_DieuTri_PhacDo { get; set; }
    }
}
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
    
    public partial class DIC_QuanHuyen
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public DIC_QuanHuyen()
        {
            this.DAT_ChoLocMauGSPH = new HashSet<DAT_ChoLocMauGSPH>();
            this.DAT_ChoLocMauGSPH1 = new HashSet<DAT_ChoLocMauGSPH>();
            this.DAT_ChuyenDoiDiaGioi = new HashSet<DAT_ChuyenDoiDiaGioi>();
            this.DAT_ChuyenDoiDiaGioi1 = new HashSet<DAT_ChuyenDoiDiaGioi>();
            this.DAT_MauGSPH = new HashSet<DAT_MauGSPH>();
            this.DAT_MauGSPH1 = new HashSet<DAT_MauGSPH>();
            this.DAT_MauGSTD = new HashSet<DAT_MauGSTD>();
            this.DAT_MauXetNghiem = new HashSet<DAT_MauXetNghiem>();
            this.DIC_XaPhuong = new HashSet<DIC_XaPhuong>();
            this.ADM_NoiDangKy = new HashSet<ADM_NoiDangKy>();
        }
    
        public int IDHuyen { get; set; }
        public byte IDTieuDe { get; set; }
        public string TenHuyen { get; set; }
        public short IDTinh { get; set; }
        public bool SuDung { get; set; }
        public bool DaXoa { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH> DAT_ChoLocMauGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH> DAT_ChoLocMauGSPH1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChuyenDoiDiaGioi> DAT_ChuyenDoiDiaGioi { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChuyenDoiDiaGioi> DAT_ChuyenDoiDiaGioi1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH> DAT_MauGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH> DAT_MauGSPH1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSTD> DAT_MauGSTD { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauXetNghiem> DAT_MauXetNghiem { get; set; }
        public virtual DIC_TieuDeHuyen DIC_TieuDeHuyen { get; set; }
        public virtual DIC_TinhThanh DIC_TinhThanh { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_XaPhuong> DIC_XaPhuong { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ADM_NoiDangKy> ADM_NoiDangKy { get; set; }
    }
}
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
    
    public partial class DIC_DoiTuong
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public DIC_DoiTuong()
        {
            this.DAT_ChoLocMauGSPH = new HashSet<DAT_ChoLocMauGSPH>();
            this.DAT_MauGSTD = new HashSet<DAT_MauGSTD>();
            this.DAT_MauXetNghiem = new HashSet<DAT_MauXetNghiem>();
            this.DIC_DoiTuong1 = new HashSet<DIC_DoiTuong>();
            this.DIC_DiaDiemGiamSat = new HashSet<DIC_DiaDiemGiamSat>();
            this.DIC_GioiTinh = new HashSet<DIC_GioiTinh>();
        }
    
        public byte IDDoiTuong { get; set; }
        public Nullable<byte> IDDoiTuongCha { get; set; }
        public string TenVietTat { get; set; }
        public string TenDayDu { get; set; }
        public bool MauGSPH { get; set; }
        public bool MauGSTD { get; set; }
        public bool MauXetNghiem { get; set; }
        public bool SuDung { get; set; }
        public bool DaXoa { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH> DAT_ChoLocMauGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSTD> DAT_MauGSTD { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauXetNghiem> DAT_MauXetNghiem { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_DoiTuong> DIC_DoiTuong1 { get; set; }
        public virtual DIC_DoiTuong DIC_DoiTuong2 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_DiaDiemGiamSat> DIC_DiaDiemGiamSat { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_GioiTinh> DIC_GioiTinh { get; set; }
    }
}

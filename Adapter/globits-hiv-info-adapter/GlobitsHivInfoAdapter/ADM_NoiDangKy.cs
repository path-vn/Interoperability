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
    
    public partial class ADM_NoiDangKy
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public ADM_NoiDangKy()
        {
            this.ADM_CapMaDangKy = new HashSet<ADM_CapMaDangKy>();
            this.ADM_NguoiDung = new HashSet<ADM_NguoiDung>();
            this.DAT_ChoLocMauGSPH = new HashSet<DAT_ChoLocMauGSPH>();
            this.DAT_ChoLocMauGSPH_AIDS = new HashSet<DAT_ChoLocMauGSPH_AIDS>();
            this.DAT_ChoLocMauGSPH_HIV = new HashSet<DAT_ChoLocMauGSPH_HIV>();
            this.DAT_ChoLocMauGSPH_TuVong = new HashSet<DAT_ChoLocMauGSPH_TuVong>();
            this.DAT_ChoLocPhienGuiDuLieu = new HashSet<DAT_ChoLocPhienGuiDuLieu>();
            this.DAT_MauGSPH = new HashSet<DAT_MauGSPH>();
            this.DAT_MauGSPH_TuVong = new HashSet<DAT_MauGSPH_TuVong>();
            this.DAT_MauGSTD = new HashSet<DAT_MauGSTD>();
            this.DAT_MauXetNghiem = new HashSet<DAT_MauXetNghiem>();
            this.DAT_PhienGuiDuLieu = new HashSet<DAT_PhienGuiDuLieu>();
            this.DIC_QuanHuyen = new HashSet<DIC_QuanHuyen>();
            this.ADM_NoiDangKy1 = new HashSet<ADM_NoiDangKy>();
            this.ADM_NoiDangKy2 = new HashSet<ADM_NoiDangKy>();
            this.DIC_TinhThanh = new HashSet<DIC_TinhThanh>();
            this.DIC_XaPhuong = new HashSet<DIC_XaPhuong>();
        }
    
        public int MaNoiDangKy { get; set; }
        public string TenDangKy { get; set; }
        public byte CapDangKy { get; set; }
        public string DienThoai { get; set; }
        public string Email { get; set; }
        public bool SuDung { get; set; }
        public bool DaXoa { get; set; }
    
        public virtual ADM_CapDangKy ADM_CapDangKy { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ADM_CapMaDangKy> ADM_CapMaDangKy { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ADM_NguoiDung> ADM_NguoiDung { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH> DAT_ChoLocMauGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH_AIDS> DAT_ChoLocMauGSPH_AIDS { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH_HIV> DAT_ChoLocMauGSPH_HIV { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocMauGSPH_TuVong> DAT_ChoLocMauGSPH_TuVong { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_ChoLocPhienGuiDuLieu> DAT_ChoLocPhienGuiDuLieu { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH> DAT_MauGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSPH_TuVong> DAT_MauGSPH_TuVong { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauGSTD> DAT_MauGSTD { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_MauXetNghiem> DAT_MauXetNghiem { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_PhienGuiDuLieu> DAT_PhienGuiDuLieu { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_QuanHuyen> DIC_QuanHuyen { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ADM_NoiDangKy> ADM_NoiDangKy1 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ADM_NoiDangKy> ADM_NoiDangKy2 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_TinhThanh> DIC_TinhThanh { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DIC_XaPhuong> DIC_XaPhuong { get; set; }
    }
}

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
    
    public partial class DAT_PhienGuiDuLieu
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public DAT_PhienGuiDuLieu()
        {
            this.DAT_GuiDuLieuGSPH = new HashSet<DAT_GuiDuLieuGSPH>();
            this.DAT_GuiDuLieuGSTD = new HashSet<DAT_GuiDuLieuGSTD>();
            this.DAT_GuiDuLieuXetNghiem = new HashSet<DAT_GuiDuLieuXetNghiem>();
        }
    
        public int IDPhienGui { get; set; }
        public string TenPhien { get; set; }
        public string MoTa { get; set; }
        public System.DateTime NgayGui { get; set; }
        public int MaNoiGui { get; set; }
        public short IDNguoiGui { get; set; }
        public int IDPhienGuiDonVi { get; set; }
        public bool KieuPhienGui { get; set; }
    
        public virtual ADM_NguoiDung ADM_NguoiDung { get; set; }
        public virtual ADM_NoiDangKy ADM_NoiDangKy { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_GuiDuLieuGSPH> DAT_GuiDuLieuGSPH { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_GuiDuLieuGSTD> DAT_GuiDuLieuGSTD { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<DAT_GuiDuLieuXetNghiem> DAT_GuiDuLieuXetNghiem { get; set; }
    }
}

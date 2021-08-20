using GlobitsHivInfoAdapter.Entities;
using GlobitsHivInfoAdapter.Utils;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.service
{
    public class HivMangThaiService
    {
        internal List<HIV_MangThai> getByMaSo(string maSo)
        {
            List<HIV_MangThai> list = new List<HIV_MangThai>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_MangThai WHERE MaSo = '" + maSo + "'";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HIV_MangThai entity = new HIV_MangThai();
                    entity.MaSo = reader["MaSo"].ToString();
                    if (CheckUtils.checkNotNull(reader["KyKinhCuoi"]))
                    {
                        entity.KyKinhCuoi = Convert.ToDateTime(reader["KyKinhCuoi"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayDuKienSinh"]))
                    {
                        entity.NgayDuKienSinh = Convert.ToDateTime(reader["NgayDuKienSinh"]);
                    }
                    entity.DieuTriDuPhongMeCon = reader["DieuTriDuPhongMeCon"] != null ? reader["DieuTriDuPhongMeCon"].ToString() : null;
                    entity.KetQuaMangThai = reader["KetQuaMangThai"] != null ? reader["KetQuaMangThai"].ToString() : null;

                    if (CheckUtils.checkNotNull(reader["NgaySinh"]))
                    {
                        entity.NgaySinh = Convert.ToDateTime(reader["NgaySinh"]);
                    }
                    entity.DieuTriDuPhongCon = reader["DieuTriDuPhongCon"] != null ? reader["DieuTriDuPhongCon"].ToString() : null;
                    entity.ChanDoanHIVCon = reader["ChanDoanHIVCon"] != null ? reader["ChanDoanHIVCon"].ToString() : null;
                    if (CheckUtils.checkNotNull(reader["NgayChanDoan"]))
                    {
                        entity.NgayChanDoan = Convert.ToDateTime(reader["NgayChanDoan"]);
                    }
                    entity.TinhTrangDieuTriCon = reader["TinhTrangDieuTriCon"] != null ? reader["TinhTrangDieuTriCon"].ToString() : null;
                    if (CheckUtils.checkNotNull(reader["IDCoSoDieuTriCon"]))
                    {
                        entity.IDCoSoDieuTriCon = long.Parse(reader["IDCoSoDieuTriCon"].ToString());
                    }
                    entity.TenCoSoDieuTriCon = reader["TenCoSoDieuTriCon"] != null ? reader["TenCoSoDieuTriCon"].ToString() : null;

                    entity.UUID = reader["UUID"].ToString() != null ? (Guid)reader["UUID"] : Guid.Empty;
                    entity.UUIDBenhNhan = reader["UUIDBenhNhan"].ToString() != null ? (Guid)reader["UUIDBenhNhan"] : Guid.Empty;
                    if (CheckUtils.checkNotNull(reader["NgayNhap"]))
                    {
                        entity.NgayNhap = Convert.ToDateTime(reader["NgayNhap"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgaySua"]))
                    {
                        entity.NgaySua = Convert.ToDateTime(reader["NgaySua"]);
                    }
                    entity.NguoiNhap = reader["NguoiNhap"] != null ? reader["NguoiNhap"].ToString() : null;
                    entity.NguoiSua = reader["NguoiSua"] != null ? reader["NguoiSua"].ToString() : null;

                    list.Add(entity);
                }
            }
            con.Close();
            return list;
        }
    }
}

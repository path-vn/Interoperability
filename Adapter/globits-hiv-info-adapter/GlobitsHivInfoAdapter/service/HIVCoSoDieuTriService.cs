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
    public class HivCoSoDieuTriService
    {
        internal List<HIV_CoSoDieuTri> getByMaSo(string maSo)
        {
            List<HIV_CoSoDieuTri> list = new List<HIV_CoSoDieuTri>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_CoSoDieuTri WHERE MaSo = '" + maSo + "' ORDER BY NgayBatDau ";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HIV_CoSoDieuTri entity = new HIV_CoSoDieuTri();
                    if (CheckUtils.checkNotNull(reader["IDCoSoDieuTri"]))
                    {
                        entity.IDCoSoDieuTri = long.Parse(reader["IDCoSoDieuTri"].ToString());
                    }
                    entity.MaSo = reader["MaSo"].ToString();
                    if (CheckUtils.checkNotNull(reader["NgayBatDau"]))
                    {
                        entity.NgayBatDau = Convert.ToDateTime(reader["NgayBatDau"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayKetThuc"]))
                    {
                        entity.NgayKetThuc = Convert.ToDateTime(reader["NgayKetThuc"]);
                    }
                    entity.TenCoSoDieuTri = reader["TenCoSoDieuTri"] != null ? reader["TenCoSoDieuTri"].ToString() : null;
                    if (CheckUtils.checkNotNull(reader["IDLoaiDangKy"]))
                    {
                        entity.IDLoaiDangKy = long.Parse(reader["IDLoaiDangKy"].ToString());
                    }
                    if (CheckUtils.checkNotNull(reader["IDTinhTrangDieuTri"]))
                    {
                        entity.IDTinhTrangDieuTri = long.Parse(reader["IDTinhTrangDieuTri"].ToString());
                    }
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

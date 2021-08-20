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
    public class HivVgbService
    {
        internal List<HIV_VGB_VGC> getByMaSo(string maSo)
        {
            List<HIV_VGB_VGC> list = new List<HIV_VGB_VGC>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_VGB WHERE MaSo = '" + maSo + "'";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HIV_VGB_VGC entity = new HIV_VGB_VGC();
                    entity.MaSo = reader["MaSo"].ToString();
                    if (CheckUtils.checkNotNull(reader["NgayXetNghiem"]))
                    {
                        entity.NgayXetNghiem = Convert.ToDateTime(reader["NgayXetNghiem"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayBatDau"]))
                    {
                        entity.NgayBatDau = Convert.ToDateTime(reader["NgayBatDau"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayKetThuc"]))
                    {
                        entity.NgayKetThuc = Convert.ToDateTime(reader["NgayKetThuc"]);
                    }
                    entity.DaDieuTri = reader["DaDieuTri"] != null ? reader["DaDieuTri"].ToString() : null;
                    entity.KetQua = reader["KetQua"] != null ? reader["KetQua"].ToString() : null;
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

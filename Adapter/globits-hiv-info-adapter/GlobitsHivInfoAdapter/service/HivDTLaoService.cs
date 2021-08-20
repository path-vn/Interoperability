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
    public class HivDTLaoService
    {
        internal List<HIV_DTLao> getByMaSo(string maSo)
        {
            List<HIV_DTLao> list = new List<HIV_DTLao>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_DTLao WHERE MaSo = '" + maSo + "' AND CoMacLao = 1 ORDER BY NgayChanDoan ";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HIV_DTLao entity = new HIV_DTLao();
                    entity.MaSo = reader["MaSo"].ToString();
                    if (CheckUtils.checkNotNull(reader["NgayChanDoan"]))
                    {
                        entity.NgayChanDoan = Convert.ToDateTime(reader["NgayChanDoan"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayBatDau"]))
                    {
                        entity.NgayBatDau = Convert.ToDateTime(reader["NgayBatDau"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayKetThuc"]))
                    {
                        entity.NgayKetThuc = Convert.ToDateTime(reader["NgayKetThuc"]);
                    }
                    entity.CoMacLao = reader["CoMacLao"] != null ? reader["CoMacLao"].ToString() : null;
                    entity.DaDieuTri = reader["DaDieuTri"] != null ? reader["DaDieuTri"].ToString() : null;
                    entity.CoSoDieuTri = reader["CoSoDieuTri"] != null ? reader["CoSoDieuTri"].ToString() : null;
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

using GlobitsHivInfoAdapter;
using GlobitsHivInfoAdapter.Dto;
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
    public class MauGSPHHivService
    {
        public List<DAT_MauGSPH_HIV> getByMaSo(string maSo)
        {
            List<DAT_MauGSPH_HIV> list = new List<DAT_MauGSPH_HIV>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from DAT_MauGSPH_HIV WHERE MaSo = '" + maSo + "' ORDER BY NgayXetNghiem DESC ";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    DAT_MauGSPH_HIV entity = new DAT_MauGSPH_HIV();
                    entity.MaSo = reader["MaSo"].ToString();

                    if (CheckUtils.checkNotNull(reader["NgayXetNghiem"]))
                    {
                        entity.NgayXetNghiem = Convert.ToDateTime(reader["NgayXetNghiem"]);
                    }
                    if (CheckUtils.checkNotNull(reader["IDNoiLayMau"]))
                    {
                        entity.IDNoiLayMau = Convert.ToInt16(reader["IDNoiLayMau"].ToString());
                    }
                    if (CheckUtils.checkNotNull(reader["IDNoiXN"]))
                    {
                        entity.IDNoiXN = Convert.ToInt16(reader["IDNoiXN"].ToString());
                    }
                    if (CheckUtils.checkNotNull(reader["MaNoiDangKy"]))
                    {
                        entity.MaNoiDangKy = (int)reader["MaNoiDangKy"];
                    }
                    if (CheckUtils.checkNotNull(reader["IDTrangThaiDieuTriHIV"]))
                    {
                        entity.IDTrangThaiDieuTriHIV = (byte)reader["IDTrangThaiDieuTriHIV"];
                    }
                    entity.NguonSoLieu = reader["NguonSoLieu"] != null ? (reader["NguonSoLieu"].ToString()) : null;

                    list.Add(entity);
                }
            }
            con.Close();
            return list;
        }
    }
}

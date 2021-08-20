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
    public class HivKhangThuocService
    {
        internal List<HIV_KhangThuoc> getByMaSo(string maSo)
        {
            List<HIV_KhangThuoc> list = new List<HIV_KhangThuoc>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_KhangThuoc WHERE MaSo = '" + maSo + "'";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HIV_KhangThuoc entity = new HIV_KhangThuoc();
                    entity.MaSo = reader["MaSo"].ToString();

                    if (CheckUtils.checkNotNull(reader["NgayLayMau"]))
                    {
                        entity.NgayLayMau = Convert.ToDateTime(reader["NgayLayMau"]);
                    }
                    entity.KetQua = reader["KetQua"] != null ? (reader["KetQua"].ToString()) : null;

                    list.Add(entity);
                }
            }
            con.Close();
            return list;
        }
    }
}

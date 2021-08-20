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
    public class HivVlService
    {
        public List<HIV_VL> getByMaSo(string maSo)
        {
            List<HIV_VL> list = new List<HIV_VL>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_VL WHERE MaSo = '" + maSo + "'";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HIV_VL entity = new HIV_VL();
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

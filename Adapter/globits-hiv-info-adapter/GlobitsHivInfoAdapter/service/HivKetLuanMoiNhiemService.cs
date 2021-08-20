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
    public class HivKetLuanMoiNhiemService
    {
        public HivRecencyTestDto getByMaSo(string maSo)
        {
            List<HivRecencyTestDto> list = new List<HivRecencyTestDto>();
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["hivcdcConnectionString"].ConnectionString);
            con.Open();
            String str = "select * from HIV_KetLuanMoiNhiem WHERE MaSo = '" + maSo + "' ORDER BY NgayNhap DESC ";
            SqlCommand cmd = new SqlCommand(str, con);
            SqlDataReader reader = cmd.ExecuteReader();
            {
                while (reader.Read())
                {
                    HivRecencyTestDto entity = new HivRecencyTestDto();
                    entity.MaSo = reader["MaSo"] != null ? (reader["MaSo"].ToString()) : null;
                    entity.KetQuaSangLoc = reader["KetQuaSangLoc"] != null ? (reader["KetQuaSangLoc"].ToString()) : null;
                    entity.KetQuaTaiLuong = reader["KetQuaTaiLuong"] != null ? (reader["KetQuaTaiLuong"].ToString()) : null;
                    entity.KetQua = reader["KetQua"] != null ? (reader["KetQua"].ToString()) : null;

                    if (CheckUtils.checkNotNull(reader["Ngay"]))
                    {
                        entity.Ngay = Convert.ToDateTime(reader["Ngay"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayXetNghiemSangLoc"]))
                    {
                        entity.NgayXetNghiemSangLoc = Convert.ToDateTime(reader["NgayXetNghiemSangLoc"]);
                    }
                    if (CheckUtils.checkNotNull(reader["NgayXetNghiemTaiLuong"]))
                    {
                        entity.NgayXetNghiemTaiLuong = Convert.ToDateTime(reader["NgayXetNghiemTaiLuong"]);
                    }

                    list.Add(entity);
                }
            }
            con.Close();
            return list.FirstOrDefault();
        }
    }
}

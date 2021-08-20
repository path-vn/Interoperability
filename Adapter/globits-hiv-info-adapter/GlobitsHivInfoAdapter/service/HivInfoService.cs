using GlobitsHivInfoAdapter;
using GlobitsHivInfoAdapter.Dto;
using GlobitsHivInfoAdapter.Entities;
using GlobitsHivInfoAdapter.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GlobitsHivInfoAdapter.service
{
    public class HivInfoService
    {
        private hivcdcEntities hivcdcEntities = new hivcdcEntities();
        private HivCd4Service hivCd4Service = new HivCd4Service();
        private HivVlService hivVlService = new HivVlService();
        private MauGSPHHivService hivDiagnosisService = new MauGSPHHivService();
        private HivKhangThuocService hivKhangThuocService = new HivKhangThuocService();
        private HivCoSoDieuTriService hivCoSoDieuTriService = new HivCoSoDieuTriService();
        private HivPhacDoService hivPhacDoService = new HivPhacDoService();
        private HivVgbService hivVgbService = new HivVgbService();
        private HivVgcService hivVgcService = new HivVgcService();
        private HivDPLaoService hivDPLaoService = new HivDPLaoService();
        private HivDTLaoService hivDTLaoService = new HivDTLaoService();
        private HivMangThaiService hivMangThaiService = new HivMangThaiService();
        private HivBaoHiemService hivBaoHiemService = new HivBaoHiemService();
        private HIVInfoSecurityUtil hivInfoSecurity = new HIVInfoSecurityUtil();
        private HivKetLuanMoiNhiemService hivKetLuanMoiNhiemService = new HivKetLuanMoiNhiemService();
        private HivGiaiDoanLamSangService hivGiaiDoanLamSang = new HivGiaiDoanLamSangService();

        static Dictionary<byte?, DIC_DanToc> danTocmap = new Dictionary<byte?, DIC_DanToc>();
        static Dictionary<short?, DIC_NoiXetNghiem> noiXetNghiemMap = new Dictionary<short?, DIC_NoiXetNghiem>();
        static Dictionary<short?, DIC_NoiLayMau> noiLayMauMap = new Dictionary<short?, DIC_NoiLayMau>();
        static Dictionary<int?, DIC_XaPhuong> xaPhuongMap = new Dictionary<int?, DIC_XaPhuong>();
        static Dictionary<int?, DIC_QuanHuyen> quanHuyenMap = new Dictionary<int?, DIC_QuanHuyen>();
        static Dictionary<int?, DIC_TinhThanh> tinhThanhMap = new Dictionary<int?, DIC_TinhThanh>();
        static Dictionary<long?, DIC_CoSoDieuTri> coSoDieuTriMap = new Dictionary<long?, DIC_CoSoDieuTri>();
        static Dictionary<long?, DIC_LoaiDangKy> loaiDangKyMap = new Dictionary<long?, DIC_LoaiDangKy>();
        static Dictionary<long?, DIC_TrangThaiDieuTri> tinhTrangDieuTriMap = new Dictionary<long?, DIC_TrangThaiDieuTri>();

        internal int countRecord(SearchDto searchDto)
        {
            int count = 0;
            if (searchDto.dateStart != null)
            {
                count = (from mauGsph in hivcdcEntities.DAT_MauGSPH
                         join nhapLieu in hivcdcEntities.DAT_NhapLieuGSPH on mauGsph.MaSo equals nhapLieu.MaSo
                         where nhapLieu.NgaySua >= searchDto.dateStart
                         select mauGsph).AsEnumerable()
                        .Select(e => new MauGsphDto
                        {
                            MaSo = e.MaSo
                        })
                        .Union((from mauGsph_nt in hivcdcEntities.DAT_MauGSPH_NgoaiTinh where mauGsph_nt.NgaySua >= searchDto.dateStart select mauGsph_nt).AsEnumerable()
                        .Select(e => new MauGsphDto
                        {
                            MaSo = e.MaSo
                        })).OrderBy(x => x.MaSo)
                    .ToList().Count();
            }
            else
            {
                count = (from mauGsph in hivcdcEntities.DAT_MauGSPH
                         join nhapLieu in hivcdcEntities.DAT_NhapLieuGSPH on mauGsph.MaSo equals nhapLieu.MaSo
                         select mauGsph).AsEnumerable()
                        .Select(e => new MauGsphDto
                        {
                            MaSo = e.MaSo
                        })
                        .Union((from mauGsph_nt in hivcdcEntities.DAT_MauGSPH_NgoaiTinh select mauGsph_nt).AsEnumerable().Select(e => new MauGsphDto
                        {
                            MaSo = e.MaSo
                        })).OrderBy(x => x.MaSo)
                    .ToList().Count();
            }

            if (searchDto.pageSize > 0)
            {
                return System.Convert.ToInt32(System.Math.Ceiling(count / System.Convert.ToDouble(searchDto.pageSize)));
            }
            else
            {
                return count;
            }
        }

        private HivInfoDto hivInfo = null;

        public List<MauGsphDto> LoadDataWithParams(DateTime? dateStart, int pageIndex, int pageSize)
        {
            var searchDto = new SearchDto() { dateStart = dateStart, pageIndex = pageIndex, pageSize = pageSize };
            return this.LoadListDAT_MauGSPH(searchDto);
        }

        public List<HivInfoDto> LoadData(SearchDto searchDto)
        {
            List<HivInfoDto> listHivInfoEntity = new List<HivInfoDto>();
            List<MauGsphDto> list = this.LoadListDAT_MauGSPH(searchDto);

            if (!searchDto.isRunJob)
            {
                listHivInfoEntity = this.LoadDataDetails(list);
            }
            return listHivInfoEntity;
        }

        public List<MauGsphDto> LoadListDAT_MauGSPH(SearchDto searchDto)
        {
            List<MauGsphDto> list = null;
            // where mauGsph.MaSo == "51030000000000017083"
            String whereCL = !String.IsNullOrEmpty(searchDto.text) ? ("where mauGsph.MaSo == '" + searchDto.text + "'") : null;
            if (searchDto.dateStart != null && searchDto.pageIndex > 0 && searchDto.pageSize > 0)
            {
                list = (from mauGsph in hivcdcEntities.DAT_MauGSPH
                        join nhapLieu in hivcdcEntities.DAT_NhapLieuGSPH on mauGsph.MaSo equals nhapLieu.MaSo
                        where nhapLieu.NgaySua >= searchDto.dateStart
                        select mauGsph).AsEnumerable()
                        .Select(e => new MauGsphDto
                        {
                            isNgoaiTinh = false,
                            MaSo = e.MaSo,
                            HoTen = e.HoTen,
                            IDDanToc = e.IDDanToc,
                            SoCMND = e.SoCMND,
                            IDGioiTinh = e.IDGioiTinh,
                            NamSinh = e.NamSinh,
                            IDXaHK = e.IDXaHK,
                            IDHuyenHK = e.IDHuyenHK,
                            IDTinhHK = e.IDTinhHK,
                            DuongPhoHK = e.DuongPhoHK,
                            ToHK = e.ToHK,
                            SoNhaHK = e.SoNhaHK,
                            IDXaTT = e.IDXaTT,
                            IDHuyenTT = e.IDHuyenTT,
                            IDTinhTT = e.IDTinhTT,
                            DuongPhoTT = e.DuongPhoTT,
                            ToTT = e.ToTT,
                            SoNhaTT = e.SoNhaTT,
                            IDNgheNghiep = e.IDNgheNghiep,
                            IDDoiTuong = e.IDDoiTuong,
                            IDDuongLay = e.IDDuongLay,
                            IDNguyCo = e.IDNguyCo,
                            IDTinhTrang = e.IDTinhTrang,
                            GhiChu = e.GhiChu,
                            MaNoiDangKy = e.MaNoiDangKy,
                            DaGui = e.DaGui,
                            MaEclinica = e.MaEclinica,
                            NgayXetNghiem = null,
                            IDNoiLayMau = null,
                            IDNoiXetNghiem = null,
                            NgayCDAIDS = null,
                            IDNoiCD = null,
                            IDTrangThaiDieuTri = null,
                            NgayTuVong = null,
                            IDLoaiBenh = null,
                            DaRaSoat = null,
                            IDNoiDieuTri = null,
                            NgayDieuTri = null
                        })
                        .Union((from mauGsph_nt in hivcdcEntities.DAT_MauGSPH_NgoaiTinh
                                where mauGsph_nt.NgaySua >= searchDto.dateStart
                                select mauGsph_nt).AsEnumerable().Select(e => new MauGsphDto
                                {
                                    isNgoaiTinh = true,
                                    MaSo = e.MaSo,
                                    HoTen = e.HoTen,
                                    IDDanToc = e.IDDanToc,
                                    SoCMND = e.SoCMND,
                                    IDGioiTinh = e.IDGioiTinh,
                                    NamSinh = e.NamSinh,
                                    IDXaHK = e.IDXaHK,
                                    IDHuyenHK = e.IDHuyenHK,
                                    IDTinhHK = e.IDTinhHK,
                                    DuongPhoHK = e.DuongPhoHK,
                                    ToHK = e.ToHK,
                                    SoNhaHK = e.SoNhaHK,
                                    IDXaTT = e.IDXaTT,
                                    IDHuyenTT = e.IDHuyenTT,
                                    IDTinhTT = e.IDTinhTT,
                                    DuongPhoTT = e.DuongPhoTT,
                                    ToTT = e.ToTT,
                                    SoNhaTT = e.SoNhaTT,
                                    IDNgheNghiep = Convert.ToByte(e.IDNgheNghiep),
                                    IDDoiTuong = Convert.ToByte(e.IDDoiTuong),
                                    IDDuongLay = Convert.ToByte(e.IDDuongLay),
                                    IDNguyCo = e.IDNguyCo,
                                    IDTinhTrang = e.IDTinhTrang,
                                    GhiChu = e.GhiChu,
                                    MaNoiDangKy = e.MaNoiDangKy,
                                    DaGui = e.DaGui,
                                    MaEclinica = null,
                                    NgayXetNghiem = e.NgayXetNghiem,
                                    IDNoiLayMau = e.IDNoiLayMau,
                                    IDNoiXetNghiem = e.IDNoiXetNghiem,
                                    NgayCDAIDS = e.NgayCDAIDS,
                                    IDNoiCD = e.IDNoiCD,
                                    IDTrangThaiDieuTri = e.IDTrangThaiDieuTri,
                                    NgayTuVong = e.NgayTuVong,
                                    IDLoaiBenh = e.IDLoaiBenh,
                                    DaRaSoat = e.DaRaSoat,
                                    IDNoiDieuTri = e.IDNoiDieuTri,
                                    NgayDieuTri = e.NgayDieuTri
                                })).OrderBy(x => x.MaSo)
                        .Skip(searchDto.pageSize * (searchDto.pageIndex - 1)).Take(searchDto.pageSize).ToList();
            }
            else if (searchDto.pageIndex > 0 && searchDto.pageSize > 0)
            {
                list = (from mauGsph in hivcdcEntities.DAT_MauGSPH
                        join nhapLieu in hivcdcEntities.DAT_NhapLieuGSPH on mauGsph.MaSo equals nhapLieu.MaSo
                        select mauGsph).AsEnumerable()
                        .Select(e => new MauGsphDto
                        {
                            isNgoaiTinh = false,
                            MaSo = e.MaSo,
                            HoTen = e.HoTen,
                            IDDanToc = e.IDDanToc,
                            SoCMND = e.SoCMND,
                            IDGioiTinh = e.IDGioiTinh,
                            NamSinh = e.NamSinh,
                            IDXaHK = e.IDXaHK,
                            IDHuyenHK = e.IDHuyenHK,
                            IDTinhHK = e.IDTinhHK,
                            DuongPhoHK = e.DuongPhoHK,
                            ToHK = e.ToHK,
                            SoNhaHK = e.SoNhaHK,
                            IDXaTT = e.IDXaTT,
                            IDHuyenTT = e.IDHuyenTT,
                            IDTinhTT = e.IDTinhTT,
                            DuongPhoTT = e.DuongPhoTT,
                            ToTT = e.ToTT,
                            SoNhaTT = e.SoNhaTT,
                            IDNgheNghiep = e.IDNgheNghiep,
                            IDDoiTuong = e.IDDoiTuong,
                            IDDuongLay = e.IDDuongLay,
                            IDNguyCo = e.IDNguyCo,
                            IDTinhTrang = e.IDTinhTrang,
                            GhiChu = e.GhiChu,
                            MaNoiDangKy = e.MaNoiDangKy,
                            DaGui = e.DaGui,
                            MaEclinica = e.MaEclinica,
                            NgayXetNghiem = null,
                            IDNoiLayMau = null,
                            IDNoiXetNghiem = null,
                            NgayCDAIDS = null,
                            IDNoiCD = null,
                            IDTrangThaiDieuTri = null,
                            NgayTuVong = null,
                            IDLoaiBenh = null,
                            DaRaSoat = null,
                            IDNoiDieuTri = null,
                            NgayDieuTri = null
                        })
                        .Union((from mauGsph_nt in hivcdcEntities.DAT_MauGSPH_NgoaiTinh select mauGsph_nt).AsEnumerable().Select(e => new MauGsphDto
                        {
                            isNgoaiTinh = true,
                            MaSo = e.MaSo,
                            HoTen = e.HoTen,
                            IDDanToc = e.IDDanToc,
                            SoCMND = e.SoCMND,
                            IDGioiTinh = e.IDGioiTinh,
                            NamSinh = e.NamSinh,
                            IDXaHK = e.IDXaHK,
                            IDHuyenHK = e.IDHuyenHK,
                            IDTinhHK = e.IDTinhHK,
                            DuongPhoHK = e.DuongPhoHK,
                            ToHK = e.ToHK,
                            SoNhaHK = e.SoNhaHK,
                            IDXaTT = e.IDXaTT,
                            IDHuyenTT = e.IDHuyenTT,
                            IDTinhTT = e.IDTinhTT,
                            DuongPhoTT = e.DuongPhoTT,
                            ToTT = e.ToTT,
                            SoNhaTT = e.SoNhaTT,
                            IDNgheNghiep = Convert.ToByte(e.IDNgheNghiep),
                            IDDoiTuong = Convert.ToByte(e.IDDoiTuong),
                            IDDuongLay = Convert.ToByte(e.IDDuongLay),
                            IDNguyCo = e.IDNguyCo,
                            IDTinhTrang = e.IDTinhTrang,
                            GhiChu = e.GhiChu,
                            MaNoiDangKy = e.MaNoiDangKy,
                            DaGui = e.DaGui,
                            MaEclinica = null,
                            NgayXetNghiem = e.NgayXetNghiem,
                            IDNoiLayMau = e.IDNoiLayMau,
                            IDNoiXetNghiem = e.IDNoiXetNghiem,
                            NgayCDAIDS = e.NgayCDAIDS,
                            IDNoiCD = e.IDNoiCD,
                            IDTrangThaiDieuTri = e.IDTrangThaiDieuTri,
                            NgayTuVong = e.NgayTuVong,
                            IDLoaiBenh = e.IDLoaiBenh,
                            DaRaSoat = e.DaRaSoat,
                            IDNoiDieuTri = e.IDNoiDieuTri,
                            NgayDieuTri = e.NgayDieuTri
                        })).OrderBy(x => x.MaSo)
                        .Skip(searchDto.pageSize * (searchDto.pageIndex - 1)).Take(searchDto.pageSize).ToList();
            }
            else
            {
                list = (from mauGsph in hivcdcEntities.DAT_MauGSPH
                        join nhapLieu in hivcdcEntities.DAT_NhapLieuGSPH on mauGsph.MaSo equals nhapLieu.MaSo
                        select mauGsph).AsEnumerable()
                        .Select(e => new MauGsphDto
                        {
                            isNgoaiTinh = false,
                            MaSo = e.MaSo,
                            HoTen = e.HoTen,
                            IDDanToc = e.IDDanToc,
                            SoCMND = e.SoCMND,
                            IDGioiTinh = e.IDGioiTinh,
                            NamSinh = e.NamSinh,
                            IDXaHK = e.IDXaHK,
                            IDHuyenHK = e.IDHuyenHK,
                            IDTinhHK = e.IDTinhHK,
                            DuongPhoHK = e.DuongPhoHK,
                            ToHK = e.ToHK,
                            SoNhaHK = e.SoNhaHK,
                            IDXaTT = e.IDXaTT,
                            IDHuyenTT = e.IDHuyenTT,
                            IDTinhTT = e.IDTinhTT,
                            DuongPhoTT = e.DuongPhoTT,
                            ToTT = e.ToTT,
                            SoNhaTT = e.SoNhaTT,
                            IDNgheNghiep = e.IDNgheNghiep,
                            IDDoiTuong = e.IDDoiTuong,
                            IDDuongLay = e.IDDuongLay,
                            IDNguyCo = e.IDNguyCo,
                            IDTinhTrang = e.IDTinhTrang,
                            GhiChu = e.GhiChu,
                            MaNoiDangKy = e.MaNoiDangKy,
                            DaGui = e.DaGui,
                            MaEclinica = e.MaEclinica,
                            NgayXetNghiem = null,
                            IDNoiLayMau = null,
                            IDNoiXetNghiem = null,
                            NgayCDAIDS = null,
                            IDNoiCD = null,
                            IDTrangThaiDieuTri = null,
                            NgayTuVong = null,
                            IDLoaiBenh = null,
                            DaRaSoat = null,
                            IDNoiDieuTri = null,
                            NgayDieuTri = null
                        })
                        .Union((from mauGsph_nt in hivcdcEntities.DAT_MauGSPH_NgoaiTinh select mauGsph_nt).AsEnumerable().Select(e => new MauGsphDto
                        {
                            isNgoaiTinh = true,
                            MaSo = e.MaSo,
                            HoTen = e.HoTen,
                            IDDanToc = e.IDDanToc,
                            SoCMND = e.SoCMND,
                            IDGioiTinh = e.IDGioiTinh,
                            NamSinh = e.NamSinh,
                            IDXaHK = e.IDXaHK,
                            IDHuyenHK = e.IDHuyenHK,
                            IDTinhHK = e.IDTinhHK,
                            DuongPhoHK = e.DuongPhoHK,
                            ToHK = e.ToHK,
                            SoNhaHK = e.SoNhaHK,
                            IDXaTT = e.IDXaTT,
                            IDHuyenTT = e.IDHuyenTT,
                            IDTinhTT = e.IDTinhTT,
                            DuongPhoTT = e.DuongPhoTT,
                            ToTT = e.ToTT,
                            SoNhaTT = e.SoNhaTT,
                            IDNgheNghiep = Convert.ToByte(e.IDNgheNghiep),
                            IDDoiTuong = Convert.ToByte(e.IDDoiTuong),
                            IDDuongLay = Convert.ToByte(e.IDDuongLay),
                            IDNguyCo = e.IDNguyCo,
                            IDTinhTrang = e.IDTinhTrang,
                            GhiChu = e.GhiChu,
                            MaNoiDangKy = e.MaNoiDangKy,
                            DaGui = e.DaGui,
                            MaEclinica = null,
                            NgayXetNghiem = e.NgayXetNghiem,
                            IDNoiLayMau = e.IDNoiLayMau,
                            IDNoiXetNghiem = e.IDNoiXetNghiem,
                            NgayCDAIDS = e.NgayCDAIDS,
                            IDNoiCD = e.IDNoiCD,
                            IDTrangThaiDieuTri = e.IDTrangThaiDieuTri,
                            NgayTuVong = e.NgayTuVong,
                            IDLoaiBenh = e.IDLoaiBenh,
                            DaRaSoat = e.DaRaSoat,
                            IDNoiDieuTri = e.IDNoiDieuTri,
                            NgayDieuTri = e.NgayDieuTri
                        })).OrderBy(x => x.MaSo)
                    .ToList();
            }
            return list;
        }

        public List<HivInfoDto> LoadDataDetails(List<MauGsphDto> listData)
        {
            List<HivInfoDto> listHivInfoEntity = new List<HivInfoDto>();
            foreach (var item in listData)
            {
                hivInfo = new HivInfoDto();
                hivInfo.MaSo = item.MaSo;
                hivInfo.HoTen = hivInfoSecurity.Decrypt(item.HoTen, true);
                hivInfo.NamSinh = item.NamSinh;
                hivInfo.SoCMND = hivInfoSecurity.Decrypt(item.SoCMND, true);
                getBHYT(item.MaSo);
                getDanToc(item.IDDanToc);
                getGioiTinh(item.IDGioiTinh);
                getNgheNghiep(item.IDNgheNghiep);
                getDoiTuong(item.IDDoiTuong);
                getNguyCoLayNhiem(item.IDNguyCo);
                getDuongLay(item.IDDuongLay);
                getHoKhau(item);
                getThuongTru(item);
                getHivDiagnosis(item);
                getHivRecencyTest(item.MaSo);
                getCd4DuringArt(item.MaSo);
                getVlDuringART(item.MaSo);
                getDrugResistanceTest(item.MaSo);
                getArvTreatment(item.MaSo);
                getHBV(item.MaSo);
                getHCV(item.MaSo);
                getTuberculosis(item.MaSo);
                getPregnancies(item.MaSo);
                getDeath(item);
                listHivInfoEntity.Add(hivInfo);
            }
            return listHivInfoEntity;
        }

        private void getHivRecencyTest(string maSo)
        {
            hivInfo.hivRecencyTest = hivKetLuanMoiNhiemService.getByMaSo(maSo);
        }

        private void getBHYT(string maSo)
        {
            List<HIV_BaoHiem> entity = hivBaoHiemService.getByMaSo(maSo);
            if (entity != null && entity.Count() > 0)
            {
                hivInfo.MaBHYT = entity.FirstOrDefault().MaThe;
            }
        }

        private void getDeath(MauGsphDto item)
        {
            DAT_MauGSPH_TuVong entity = (from o in hivcdcEntities.DAT_MauGSPH_TuVong where o.MaSo == item.MaSo select o).FirstOrDefault();
            if (entity != null)
            {
                hivInfo.dateOfDeath = entity.NgayTuVong;
                if (entity.DIC_NguyenNhanTuVong != null && entity.DIC_NguyenNhanTuVong.Count() > 0)
                {
                    hivInfo.causeOfDeath = entity.DIC_NguyenNhanTuVong.FirstOrDefault().TenNguyenNhan;
                }
            }
            if (item.NgayTuVong != null)
            {
                hivInfo.dateOfDeath = item.NgayTuVong;
            }
        }

        private void getPregnancies(string maSo)
        {
            List<HIV_MangThai> listMangThai = hivMangThaiService.getByMaSo(maSo);
            hivInfo.listPregnancies = listMangThai;
        }

        private void getTuberculosis(string maSo)
        {
            TuberculosisDto dto = new TuberculosisDto();
            dto.listTPT = hivDPLaoService.getByMaSo(maSo);
            dto.listTbTreatment = hivDTLaoService.getByMaSo(maSo);
            hivInfo.tuberculosis = dto;
        }

        private void getHCV(string maSo)
        {
            List<HIV_VGB_VGC> listVGC = hivVgcService.getByMaSo(maSo);
            if (listVGC != null && listVGC.Count() > 0)
            {
                hivInfo.listHcv = listVGC;
            }
        }

        private void getHBV(string maSo)
        {
            List<HIV_VGB_VGC> listVGB = hivVgbService.getByMaSo(maSo);
            if (listVGB != null && listVGB.Count() > 0)
            {
                hivInfo.listHbv = listVGB;
            }
        }

        private List<HIV_PhacDo> getRegimen(string maSo)
        {
            List<HIV_PhacDo> listPhacDo = hivPhacDoService.getByMaSo(maSo);
            if (listPhacDo != null && listPhacDo.Count() > 0)
            {
                foreach (var item in listPhacDo)
                {
                    if (String.IsNullOrEmpty(item.TenPhacDo) && item.IDPhacDo != null)
                    {
                        DIC_PhacDo phacDo = (from o in hivcdcEntities.DIC_PhacDo where o.IDPhacDo == item.IDPhacDo select o).FirstOrDefault();
                        if (phacDo != null)
                        {
                            item.TenPhacDo = phacDo.TenPhacDo;
                        }
                    }
                }
            }
            return listPhacDo;
        }

        private void getArvTreatment(string maSo)
        {
            List<ArvTreatmentDto> listArvTreatment = null;
            List<HIV_CoSoDieuTri> listCoSoDieuTri = hivCoSoDieuTriService.getByMaSo(maSo);
            if (listCoSoDieuTri != null && listCoSoDieuTri.Count() > 0)
            {
                listArvTreatment = new List<ArvTreatmentDto>();
                int index = 1;
                foreach (var item in listCoSoDieuTri)
                {
                    ArvTreatmentDto dto = new ArvTreatmentDto();
                    if (index == listCoSoDieuTri.Count())
                    {
                        dto.ListArvRegimen = getRegimen(item.MaSo);
                        dto.ListWhoStage = getWhoStage(item.MaSo);
                    }
                    dto.NgayBatDau = item.NgayBatDau;
                    dto.NgayKetThuc = item.NgayKetThuc;
                    dto.TenCoSoDieuTri = getTenCoSoDieuTri(item);
                    dto.TenLoaiDangKy = getLoaiDangKy(item.IDLoaiDangKy);
                    dto.TenTinhTrangDieuTri = getTinhTrangDieuTri(item.IDTinhTrangDieuTri);
                    listArvTreatment.Add(dto);
                    index++;
                }
            }
            hivInfo.listArvTreatment = listArvTreatment;
        }

        private List<HIV_GiaiDoanLamSang> getWhoStage(string maSo)
        {
            List<HIV_GiaiDoanLamSang> results = hivGiaiDoanLamSang.getByMaSo(maSo);
            if (results != null && results.Count() > 0)
            {
                foreach (var item in results)
                {
                    if (item.IDGiaiDoanLamSang != null)
                    {
                        DIC_GiaiDoanLamSang gdls = (from o in hivcdcEntities.DIC_GiaiDoanLamSang where o.IDGDLS == item.IDGiaiDoanLamSang select o).FirstOrDefault();
                        if (gdls != null)
                        {
                            item.TenGiaiDoanLamSang = gdls.TenGDLS;
                        }
                    }
                }
            }
            return results;
        }

        private String getTinhTrangDieuTri(long? iDTinhTrangDieuTri)
        {
            DIC_TrangThaiDieuTri result = null;
            if (iDTinhTrangDieuTri != null)
            {
                result = tinhTrangDieuTriMap.ContainsKey(iDTinhTrangDieuTri) ? tinhTrangDieuTriMap[iDTinhTrangDieuTri] : null;
                if (result == null)
                {
                    result = (from o in hivcdcEntities.DIC_TrangThaiDieuTri where o.IDTrangThaiDieuTri == iDTinhTrangDieuTri select o).FirstOrDefault();
                    if (result != null)
                    {
                        tinhTrangDieuTriMap.Add(iDTinhTrangDieuTri, result);
                    }
                }
            }
            return (result != null) ? result.MoTa : null;
        }

        private String getLoaiDangKy(long? iDLoaiDangKy)
        {
            DIC_LoaiDangKy result = null;
            if (iDLoaiDangKy != null)
            {
                result = loaiDangKyMap.ContainsKey(iDLoaiDangKy) ? loaiDangKyMap[iDLoaiDangKy] : null;
                if (result == null)
                {
                    result = (from o in hivcdcEntities.DIC_LoaiDangKy where o.IDLoaiDangKy == iDLoaiDangKy select o).FirstOrDefault();
                    if (result != null)
                    {
                        loaiDangKyMap.Add(iDLoaiDangKy, result);
                    }
                }
            }
            return (result != null) ? result.TenLoaiDangKy : null;
        }

        private String getTenCoSoDieuTri(HIV_CoSoDieuTri item)
        {
            DIC_CoSoDieuTri coSoDieuTri = null;
            if (!String.IsNullOrEmpty(item.TenCoSoDieuTri))
            {
                return item.TenCoSoDieuTri;
            }
            else if (item.IDCoSoDieuTri != null)
            {
                coSoDieuTri = coSoDieuTriMap.ContainsKey(item.IDCoSoDieuTri) ? coSoDieuTriMap[item.IDCoSoDieuTri] : null;
                if (coSoDieuTri == null)
                {
                    coSoDieuTri = (from o in hivcdcEntities.DIC_CoSoDieuTri where o.IDCoSo == item.IDCoSoDieuTri select o).FirstOrDefault();
                    if (coSoDieuTri != null)
                    {
                        coSoDieuTriMap.Add(item.IDCoSoDieuTri, coSoDieuTri);
                    }
                }
            }
            if (coSoDieuTri != null)
            {
                return coSoDieuTri.TenCoSo;
            }
            return null;
        }

        private void getDrugResistanceTest(string maSo)
        {
            List<HIV_KhangThuoc> listDrugResistanceTest = hivKhangThuocService.getByMaSo(maSo);
            if (listDrugResistanceTest != null && listDrugResistanceTest.Count() > 0)
            {
                hivInfo.listDrugResistanceTest = listDrugResistanceTest;
            }
        }

        private void getNgheNghiep(byte? iDNgheNghiep)
        {
            DIC_NgheNghiep ngheNghiep = iDNgheNghiep != null ? (from o in hivcdcEntities.DIC_NgheNghiep where o.IDNghe == iDNgheNghiep select o).FirstOrDefault() : null;
            if (ngheNghiep != null)
            {
                hivInfo.IdNgheNghiep = ngheNghiep.IDNghe;
                hivInfo.NgheNghiep = ngheNghiep.TenNghe;
            }
        }

        private void getThuongTru(MauGsphDto item)
        {
            DIC_XaPhuong xaPhuong = null;
            DIC_QuanHuyen quanHuyen = null;
            DIC_TinhThanh tinhThanh = null;
            hivInfo.DuongPhoTT = item.DuongPhoTT != null ? hivInfoSecurity.Decrypt(item.DuongPhoTT, true) : null;
            hivInfo.ToTT = item.ToTT != null ? hivInfoSecurity.Decrypt(item.ToTT, true) : null;
            hivInfo.SoNhaTT = item.SoNhaTT != null ? hivInfoSecurity.Decrypt(item.SoNhaTT, true) : null;

            if (item.IDXaTT != null)
            {
                xaPhuong = xaPhuongMap.ContainsKey(item.IDXaTT) ? xaPhuongMap[item.IDXaTT] : null;
                if (xaPhuong == null)
                {
                    xaPhuong = (from o in hivcdcEntities.DIC_XaPhuong where o.IDXa == item.IDXaTT select o).FirstOrDefault();
                    if (xaPhuong != null)
                    {
                        xaPhuongMap.Add(item.IDXaTT, xaPhuong);
                    }
                }
                if (xaPhuong != null)
                {
                    hivInfo.XaTT = new ValuesetDto(xaPhuong.IDXa.ToString(), xaPhuong.TenXa);
                }
            }

            if (item.IDHuyenTT != null)
            {
                quanHuyen = quanHuyenMap.ContainsKey(item.IDHuyenTT) ? quanHuyenMap[item.IDHuyenTT] : null;
                if (quanHuyen == null)
                {
                    quanHuyen = (from o in hivcdcEntities.DIC_QuanHuyen where o.IDHuyen == item.IDHuyenTT select o).FirstOrDefault();
                    if (quanHuyen != null)
                    {
                        quanHuyenMap.Add(item.IDHuyenTT, quanHuyen);
                    }
                }
                if (quanHuyen != null)
                {
                    hivInfo.HuyenTT = new ValuesetDto(quanHuyen.IDHuyen.ToString(), quanHuyen.TenHuyen);
                }
            }

            if (item.IDTinhTT != null)
            {
                tinhThanh = tinhThanhMap.ContainsKey(item.IDTinhTT) ? tinhThanhMap[item.IDTinhTT] : null;
                if (tinhThanh == null)
                {
                    tinhThanh = (from o in hivcdcEntities.DIC_TinhThanh where o.IDTinh == item.IDTinhTT select o).FirstOrDefault();
                    if (tinhThanh != null)
                    {
                        tinhThanhMap.Add(item.IDTinhTT, tinhThanh);
                    }
                }
                if (tinhThanh != null)
                {
                    hivInfo.TinhTT = new ValuesetDto(tinhThanh.IDTinh.ToString(), tinhThanh.TenTinh);
                }
            }
        }

        private void getHoKhau(MauGsphDto item)
        {
            DIC_XaPhuong xaPhuong = null;
            DIC_QuanHuyen quanHuyen = null;
            DIC_TinhThanh tinhThanh = null;
            hivInfo.DuongPhoHK = item.DuongPhoHK != null ? hivInfoSecurity.Decrypt(item.DuongPhoHK, true) : null;
            hivInfo.ToHK = item.ToHK != null ? hivInfoSecurity.Decrypt(item.ToHK, true) : null;
            hivInfo.SoNhaHK = item.SoNhaHK != null ? hivInfoSecurity.Decrypt(item.SoNhaHK, true) : null;

            if (item.IDXaHK != null)
            {
                xaPhuong = xaPhuongMap.ContainsKey(item.IDXaHK) ? xaPhuongMap[item.IDXaHK] : null;
                if (xaPhuong == null)
                {
                    xaPhuong = (from o in hivcdcEntities.DIC_XaPhuong where o.IDXa == item.IDXaHK select o).FirstOrDefault();
                    if (xaPhuong != null)
                    {
                        xaPhuongMap.Add(item.IDXaHK, xaPhuong);
                    }
                }
                if (xaPhuong != null)
                {
                    hivInfo.XaHK = new ValuesetDto(xaPhuong.IDXa.ToString(), xaPhuong.TenXa);
                }
            }

            if (item.IDHuyenHK != null)
            {
                quanHuyen = quanHuyenMap.ContainsKey(item.IDHuyenHK) ? quanHuyenMap[item.IDHuyenHK] : null;
                if (quanHuyen == null)
                {
                    quanHuyen = (from o in hivcdcEntities.DIC_QuanHuyen where o.IDHuyen == item.IDHuyenHK select o).FirstOrDefault();
                    if (quanHuyen != null)
                    {
                        quanHuyenMap.Add(item.IDHuyenHK, quanHuyen);
                    }
                }
                if (quanHuyen != null)
                {
                    hivInfo.HuyenHK = new ValuesetDto(quanHuyen.IDHuyen.ToString(), quanHuyen.TenHuyen);
                }
            }

            if (item.IDTinhHK != null)
            {
                tinhThanh = tinhThanhMap.ContainsKey(item.IDTinhHK) ? tinhThanhMap[item.IDTinhHK] : null;
                if (tinhThanh == null)
                {
                    tinhThanh = (from o in hivcdcEntities.DIC_TinhThanh where o.IDTinh == item.IDTinhHK select o).FirstOrDefault();
                    if (tinhThanh != null)
                    {
                        tinhThanhMap.Add(item.IDTinhHK, tinhThanh);
                    }
                }
                if (tinhThanh != null)
                {
                    hivInfo.TinhHK = new ValuesetDto(tinhThanh.IDTinh.ToString(), tinhThanh.TenTinh);
                }
            }
        }

        private void getVlDuringART(string maSo)
        {
            List<HIV_VL> listVlTest = hivVlService.getByMaSo(maSo);
            if (listVlTest != null && listVlTest.Count() > 0)
            {
                hivInfo.listVlDuringArt = listVlTest;
            }
        }

        private void getCd4DuringArt(string maSo)
        {
            List<HIV_CD4_Gets_Result> listHIV_CD4 = hivCd4Service.getByMaSo(maSo);
            if (listHIV_CD4 != null && listHIV_CD4.Count() > 0)
            {
                hivInfo.listCd4DuringArt = listHIV_CD4;
            }
        }

        private void getHivDiagnosis(MauGsphDto item)
        {
            hivInfo.listHivDiagnosis = new List<HivDiagnosisDto>();
            if (item.isNgoaiTinh)
            {
                HivDiagnosisDto dto = new HivDiagnosisDto();
                dto.dateOfConfirmation = item.NgayXetNghiem;
                if (item.IDNoiXetNghiem != null)
                {
                    DIC_NoiXetNghiem noiXetNghiem = noiXetNghiemMap.ContainsKey(item.IDNoiXetNghiem) ? noiXetNghiemMap[item.IDNoiXetNghiem] : null;
                    if (noiXetNghiem == null)
                    {
                        noiXetNghiem = (from o in hivcdcEntities.DIC_NoiXetNghiem where o.IDNoiXN == item.IDNoiXetNghiem select o).FirstOrDefault();
                        if (noiXetNghiem != null)
                        {
                            noiXetNghiemMap.Add(item.IDNoiXetNghiem, noiXetNghiem);
                        }
                    }
                    dto.confirmingLab = (noiXetNghiem != null) ? noiXetNghiem.TenNoiXN : null;
                }
                if (item.IDNoiLayMau != null)
                {
                    DIC_NoiLayMau noiLayMau = noiLayMauMap.ContainsKey(item.IDNoiLayMau) ? noiLayMauMap[item.IDNoiLayMau] : null;
                    if (noiLayMau == null)
                    {
                        noiLayMau = (from o in hivcdcEntities.DIC_NoiLayMau where o.IDNoiLayMau == item.IDNoiLayMau select o).FirstOrDefault();
                        if (noiLayMau != null)
                        {
                            noiLayMauMap.Add(item.IDNoiLayMau, noiLayMau);
                        }
                    }
                    dto.placeOfSpecimenCollection = (noiLayMau != null) ? noiLayMau.TenNoiLayMau : null;
                }
                hivInfo.listHivDiagnosis.Add(dto);
            }
            else
            {
                List<DAT_MauGSPH_HIV> listDAT_MauGSPH_HIV = hivDiagnosisService.getByMaSo(item.MaSo);
                if (listDAT_MauGSPH_HIV != null && listDAT_MauGSPH_HIV.Count() > 0)
                {
                    //foreach (var i in listDAT_MauGSPH_HIV)
                    //{
                    //}
                    DAT_MauGSPH_HIV i = listDAT_MauGSPH_HIV.FirstOrDefault();
                    HivDiagnosisDto dto = new HivDiagnosisDto();
                    dto.dateOfConfirmation = i.NgayXetNghiem;
                    if (i.IDNoiXN != null)
                    {
                        DIC_NoiXetNghiem noiXetNghiem = noiXetNghiemMap.ContainsKey(i.IDNoiXN) ? noiXetNghiemMap[i.IDNoiXN] : null;
                        if (noiXetNghiem == null)
                        {
                            noiXetNghiem = (from o in hivcdcEntities.DIC_NoiXetNghiem where o.IDNoiXN == i.IDNoiXN select o).FirstOrDefault();
                            if (noiXetNghiem != null)
                            {
                                noiXetNghiemMap.Add(i.IDNoiXN, noiXetNghiem);
                            }
                        }
                        dto.confirmingLab = (noiXetNghiem != null) ? noiXetNghiem.TenNoiXN : null;
                    }
                    if (i.IDNoiLayMau != null)
                    {
                        DIC_NoiLayMau noiLayMau = noiLayMauMap.ContainsKey(i.IDNoiLayMau) ? noiLayMauMap[i.IDNoiLayMau] : null;
                        if (noiLayMau == null)
                        {
                            noiLayMau = (from o in hivcdcEntities.DIC_NoiLayMau where o.IDNoiLayMau == i.IDNoiLayMau select o).FirstOrDefault();
                            if (noiLayMau != null)
                            {
                                noiLayMauMap.Add(i.IDNoiLayMau, noiLayMau);
                            }
                        }
                        dto.placeOfSpecimenCollection = (noiLayMau != null) ? noiLayMau.TenNoiLayMau : null;
                    }
                    hivInfo.listHivDiagnosis.Add(dto);
                }
            }
        }

        private void getDuongLay(byte? iDDuongLay)
        {
            if (iDDuongLay != null)
            {
                DIC_DuongLay duongLay = iDDuongLay != null ? ((from o in hivcdcEntities.DIC_DuongLay where o.IDDuongLay == iDDuongLay select o).FirstOrDefault()) : null;
                if (duongLay != null)
                {
                    hivInfo.IdDuongLay = duongLay.IDDuongLay;
                    hivInfo.DuongLay = duongLay.TenDuongLay;
                }
            }
        }

        private void getNguyCoLayNhiem(byte? iDNguyCo)
        {
            if (iDNguyCo != null)
            {
                DIC_NguyCoLayNhiem nguyCoLayNhiem = iDNguyCo != null ? ((from o in hivcdcEntities.DIC_NguyCoLayNhiem where o.IDNguyCo == iDNguyCo select o).FirstOrDefault()) : null;
                if (nguyCoLayNhiem != null)
                {
                    hivInfo.IdNguyCo = nguyCoLayNhiem.IDNguyCo;
                    hivInfo.NguyCo = nguyCoLayNhiem.TenNguyCo;
                }
            }
        }

        private void getDoiTuong(byte? iDDoiTuong)
        {
            if (iDDoiTuong != null)
            {
                DIC_DoiTuong doiTuong = iDDoiTuong != null ? ((from o in hivcdcEntities.DIC_DoiTuong where o.IDDoiTuong == iDDoiTuong select o).FirstOrDefault()) : null;
                if (doiTuong != null)
                {
                    hivInfo.IdDoiTuong = doiTuong.IDDoiTuong;
                    hivInfo.DoiTuong = doiTuong.TenDayDu;
                }
            }
        }

        private void getGioiTinh(byte? iDGioiTinh)
        {
            if (iDGioiTinh != null)
            {
                DIC_GioiTinh gioitinh = iDGioiTinh != null ? (from o in hivcdcEntities.DIC_GioiTinh where o.IDGioiTinh == iDGioiTinh select o).FirstOrDefault() : null;
                if (gioitinh != null)
                {
                    hivInfo.IDGioiTinh = gioitinh.IDGioiTinh;
                    hivInfo.GioiTinh = gioitinh.TenGioiTinh;
                }
            }
        }

        private void getDanToc(byte? iDDanToc)
        {
            if (iDDanToc != null)
            {
                DIC_DanToc dantoc = danTocmap.ContainsKey(iDDanToc) ? danTocmap[iDDanToc] : null;
                if (dantoc == null)
                {
                    dantoc = (from o in hivcdcEntities.DIC_DanToc where o.IDDanToc == iDDanToc select o).FirstOrDefault();
                    if (dantoc != null)
                    {
                        danTocmap.Add(iDDanToc, dantoc);
                    }
                }
                if (dantoc != null)
                {
                    hivInfo.IDDanToc = dantoc.IDDanToc;
                    hivInfo.DanToc = dantoc.TenDanToc;
                }
            }
        }
    }
}

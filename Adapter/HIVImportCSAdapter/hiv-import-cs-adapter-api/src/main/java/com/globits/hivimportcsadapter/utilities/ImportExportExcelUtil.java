package com.globits.hivimportcsadapter.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.globits.hivimportcsadapter.Const;
import com.globits.hivimportcsadapter.ServerConst;
import com.globits.hivimportcsadapter.dto.AddressDto;
import com.globits.hivimportcsadapter.dto.EthnicityDto;
import com.globits.hivimportcsadapter.dto.HivDiagnosisDto;
import com.globits.hivimportcsadapter.dto.HivRecencyTestDto;
import com.globits.hivimportcsadapter.dto.HtcElogDto;
import com.globits.hivimportcsadapter.dto.LabTestDto;
import com.globits.hivimportcsadapter.dto.OrganizationDto;
import com.globits.hivimportcsadapter.dto.SystemCodeDto;
//import com.globits.fhir.hiv.utils.HivConst;
import com.globits.hivimportcsadapter.functiondto.HIVInfoDto;

@Component
public class ImportExportExcelUtil {
	public static List<HIVInfoDto> importHIVInfoFromInputStream(InputStream is) throws IOException {
        List<HIVInfoDto> listData = new ArrayList<HIVInfoDto>();
        try {
            // cảnh báo
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowIndex = 1;
            int falseIndex = 1;
            Calendar calendar = Calendar.getInstance();
            int num = datatypeSheet.getLastRowNum();
            while (rowIndex <= num) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if (currentRow != null) {
                	HIVInfoDto dto = new HIVInfoDto();

                    Integer index = 0;
                    if (index != null) {
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String HoTen = String.valueOf(currentCell.getNumericCellValue());
                            dto.setHoTen(HoTen);
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String HoTen = currentCell.getStringCellValue().trim();
                            dto.setHoTen(HoTen);
                        }
                    }
                    
                    index = 1;
                    if (index != null) {
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String DanToc = String.valueOf((int)currentCell.getNumericCellValue());
                            dto.setDanToc(DanToc);
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String DanToc = currentCell.getStringCellValue().trim();
                            dto.setDanToc(DanToc);
                        } else {
                        	dto.setDanToc("");
                        }
                    }

                    index = 2;
                    if (index != null) {
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String GioiTinh = String.valueOf((int)currentCell.getNumericCellValue());
                            dto.setGioiTinh(GioiTinh);
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String GioiTinh = currentCell.getStringCellValue().trim();
                            dto.setGioiTinh(GioiTinh);
                        }
                    }
					index = 3;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String NamSinh	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setNamSinh(NamSinh);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String NamSinh = currentCell.getStringCellValue().trim();
							dto.setNamSinh(NamSinh);
						}
					}
					index = 4;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String SoCMND	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setSoCMND(SoCMND);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String SoCMND = currentCell.getStringCellValue().trim();
							dto.setSoCMND(SoCMND);
						}
					}
					index = 5;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String NgheNghiep	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setNgheNghiep(NgheNghiep);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String NgheNghiep = currentCell.getStringCellValue().trim();
							dto.setNgheNghiep(NgheNghiep);
						}
					}
					index = 6;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String IdDoiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							dto.setIdDoiTuong(IdDoiTuong);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String IdDoiTuong = currentCell.getStringCellValue().trim();
							dto.setIdDoiTuong(IdDoiTuong);
						}
					}
					
                    index = 7;
                    if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String DoiTuong	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setDoiTuong(DoiTuong);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String DoiTuong = currentCell.getStringCellValue().trim();
							dto.setDoiTuong(DoiTuong);
						}
					}
                    
                    index = 8;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String IdNguyCo = String.valueOf((int)currentCell.getNumericCellValue());
							dto.setIdNguyCo(IdNguyCo);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String IdNguyCo = currentCell.getStringCellValue().trim();
							dto.setIdNguyCo(IdNguyCo);
						}
					}
                    index = 9;
                    if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String NguyCo	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setNguyCo(NguyCo);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String NguyCo = currentCell.getStringCellValue().trim();
							dto.setNguyCo(NguyCo);
						}
					}
                    index = 10;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String IdDuongLay = String.valueOf((int)currentCell.getNumericCellValue());
							dto.setIdDuongLay(IdDuongLay);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String IdDuongLay = currentCell.getStringCellValue().trim();
							dto.setIdDuongLay(IdDuongLay);
						}
					}

                    index = 11;
                    if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String DuongLay	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setDuongLay(DuongLay);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String DuongLay = currentCell.getStringCellValue().trim();
							dto.setDuongLay(DuongLay);
						}
					}
					index = 12;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String XaHK	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setXaHK(XaHK);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String XaHK = currentCell.getStringCellValue().trim();
							dto.setXaHK(XaHK);
						} else {
                        	dto.setXaHK("");
                        }
					}
					index = 13;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String HuyenHK	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setHuyenHK(HuyenHK);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String HuyenHK = currentCell.getStringCellValue().trim();
							dto.setHuyenHK(HuyenHK);
						} else {
                        	dto.setHuyenHK("");
                        }
					}
					index = 14;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String TinhHK	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setTinhHK(TinhHK);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String TinhHK = currentCell.getStringCellValue().trim();
							dto.setTinhHK(TinhHK);
						} else {
                        	dto.setTinhHK("");
                        }
					}
					index = 15;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String DuongPhoHK	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setDuongPhoHK(DuongPhoHK);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String DuongPhoHK = currentCell.getStringCellValue().trim();
							dto.setDuongPhoHK(DuongPhoHK);;
						} else {
                        	dto.setDuongPhoHK("");
                        }
					}
					index = 16;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String ToHK	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setToHK(ToHK);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String ToHK = currentCell.getStringCellValue().trim();
							dto.setToHK(ToHK);
						} else {
                        	dto.setToHK("");
                        }
					}
					index = 17;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String SoNhaHK	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setSoNhaHK(SoNhaHK);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String SoNhaHK = currentCell.getStringCellValue().trim();
							dto.setSoNhaHK(SoNhaHK);
						} else {
                        	dto.setSoNhaHK("");
                        }
					}
					index = 18;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String XaTT	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setXaTT(XaTT);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String XaTT = currentCell.getStringCellValue().trim();
							dto.setXaTT(XaTT);
						} else {
                        	dto.setXaTT("");
                        }
					}
					index = 19;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String HuyenTT	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setHuyenTT(HuyenTT);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String HuyenTT = currentCell.getStringCellValue().trim();
							dto.setHuyenTT(HuyenTT);
						} else {
                        	dto.setHuyenTT("");
                        }
					}
					index = 20;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String TinhTT	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setTinhTT(TinhTT);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String TinhTT = currentCell.getStringCellValue().trim();
							dto.setTinhTT(TinhTT);
						} else {
                        	dto.setTinhTT("");
                        }
					}
					index = 21;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String DuongPhoTT	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setDuongPhoTT(DuongPhoTT);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String DuongPhoTT = currentCell.getStringCellValue().trim();
							dto.setDuongPhoTT(DuongPhoTT);
						} else {
                        	dto.setDuongPhoTT("");
                        }
					}
					index = 22;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String ToTT	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setToTT(ToTT);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String ToTT = currentCell.getStringCellValue().trim();
							dto.setToTT(ToTT);
						} else {
                        	dto.setToTT("");
                        }
					}
					index = 23;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String SoNhaTT	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setSoNhaTT(SoNhaTT);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String SoNhaTT = currentCell.getStringCellValue().trim();
							dto.setSoNhaTT(SoNhaTT);
						} else {
                        	dto.setSoNhaTT("");
                        }
					}
					index = 24;
					if (index != null) {
                        currentCell = currentRow.getCell(index);
                        String decisionDateString = "";

                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC
                                && currentCell.getNumericCellValue() > 0) {
                            if (DateUtil.isCellDateFormatted(currentCell)) {
                                calendar.setTime(currentCell.getDateCellValue());
                                dto.setNgayXetNghiem(calendar.getTime());
                            }
 
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            decisionDateString = String.valueOf(currentCell.getStringCellValue());
                            try {
                                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(decisionDateString);
                                dto.setNgayXetNghiem(date);
                            } catch (Exception ex) {
                                System.out.print(ex.getMessage());
                            }
                        }
					}
					index = 25;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String TenNoiXN	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setTenNoiXN(TenNoiXN);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String TenNoiXN = currentCell.getStringCellValue().trim();
							dto.setTenNoiXN(TenNoiXN);
						}
					}
					index = 26;
					if (index != null) {
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String TenNoiLayMau	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setTenNoiLayMau(TenNoiLayMau);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String TenNoiLayMau = currentCell.getStringCellValue().trim();
							dto.setTenNoiLayMau(TenNoiLayMau);
						}
					}
					
                    listData.add(dto);
                }
                rowIndex++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listData;
    }
	/////
	public static List<HtcElogDto> importHTCELOGFromInputStream(InputStream is) throws IOException {
        List<HtcElogDto> listData = new ArrayList<HtcElogDto>();
        try {
            // cảnh báo
            @SuppressWarnings("resource")
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int indexRow = 1;
            Row row = datatypeSheet.getRow(indexRow);
            Cell cell = null;
            Integer indexCell = 1;
            cell = row.getCell(indexCell);
            String placeOfSpecimenCollection = "";
            if (cell != null && cell.getCellTypeEnum() == CellType.NUMERIC) {
                String result = String.valueOf((int)cell.getNumericCellValue());
                placeOfSpecimenCollection = result;
            } else if (cell != null && cell.getCellTypeEnum() == CellType.STRING
                    && cell.getStringCellValue() != null) {
                String result = cell.getStringCellValue().trim();
                placeOfSpecimenCollection = result;
            }
            
            
            int rowIndex = 8;
            int falseIndex = 1;
            Calendar calendar = Calendar.getInstance();
            int num = datatypeSheet.getLastRowNum();
            while (rowIndex <= num) {
                Row currentRow = datatypeSheet.getRow(rowIndex);
                Cell currentCell = null;
                if (currentRow != null) {
                	HtcElogDto dto = new HtcElogDto();
                	HivDiagnosisDto hivDiagnosisDto = new HivDiagnosisDto();
                	AddressDto currentAddress = new AddressDto();
                	AddressDto registeredAddress = new AddressDto();
                	String genderValue ="";
                	HivRecencyTestDto hivRecencyTestDto = new HivRecencyTestDto();
                	LabTestDto rapidTest = new LabTestDto();
                	LabTestDto vlTest = new LabTestDto();
                	OrganizationDto specimenCollectionPlace = new OrganizationDto();
                    Integer index = 58;
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String result = String.valueOf((int)currentCell.getNumericCellValue());
                            if(!result.equals(ServerConst.testResult.Positive.getValue())) {
                            	 rowIndex++;
                            	continue;
                            }
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String result = currentCell.getStringCellValue().trim();
                            if(!result.equals(ServerConst.testResult.Positive.getValue())) {
                            	 rowIndex++;
                            	continue;
                            }
                        }else {
                        	rowIndex++;
                        	continue;
                        }
                    
//                        if(!placeOfSpecimenCollection.equals("")) {
                        	index = 1;
                        	currentCell = currentRow.getCell(index);
                            if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                                placeOfSpecimenCollection = String.valueOf((int)currentCell.getNumericCellValue());

                            } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                    && currentCell.getStringCellValue() != null) {
                            	placeOfSpecimenCollection = currentCell.getStringCellValue().trim();
                            }
//                        }
                    //tên
                    index = 6;
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String fullName = String.valueOf((int)currentCell.getNumericCellValue());
                            dto.setName(fullName);
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String fullName = currentCell.getStringCellValue().trim();
                            dto.setName(fullName);
                        }
                        //Dân tộc
                        index = 9;
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String ethnicity = String.valueOf((int)currentCell.getNumericCellValue());
                            SystemCodeDto value = ServerConst.getEthnicityByText(ethnicity);
                           
                            if(value!= null) {
                            	 EthnicityDto ethnicityDto = new EthnicityDto();
                            	 if(value.getCode()!= null) {
                            		 ethnicityDto.setCode(value.getCode());
                            	 }
                            	 if(value.getDisplay() != null) {
                            		 ethnicityDto.setName(value.getDisplay());
                            	 }
                            	 dto.setEthnicity(ethnicityDto);
                            }
                           
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String ethnicity = currentCell.getStringCellValue().trim();
                            SystemCodeDto value = ServerConst.getEthnicityByText(ethnicity);
                            
                            if(value!= null) {
                            	 EthnicityDto ethnicityDto = new EthnicityDto();
                            	 if(value.getCode()!= null) {
                            		 ethnicityDto.setCode(value.getCode());
                            	 }
                            	 if(value.getDisplay() != null) {
                            		 ethnicityDto.setName(value.getDisplay());
                            	 }
                            	 dto.setEthnicity(ethnicityDto);
                            }
                        }
                     //Giới tính
					index = 10;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String gender	= String.valueOf((int)currentCell.getNumericCellValue());
							genderValue = gender;
							SystemCodeDto systemCode = new SystemCodeDto();
							if(gender.equals("2")) {
								systemCode.setCode(Const.PatientGenderIdentity.Female.getValue());
								systemCode.setDisplay("Female");
							}
							if(gender.equals("1")) {
								systemCode.setCode(Const.PatientGenderIdentity.Male.getValue());
								systemCode.setDisplay("Male");
							}
							dto.setGender(systemCode);
							 
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String gender = currentCell.getStringCellValue().trim();
							SystemCodeDto systemCode = new SystemCodeDto();
							genderValue = gender;
							if(gender.equals("2")) {
								systemCode.setCode(Const.PatientGenderIdentity.Female.getValue());
								systemCode.setDisplay("Female");
							}
							if(gender.equals("1")) {
								systemCode.setCode(Const.PatientGenderIdentity.Male.getValue());
								systemCode.setDisplay("Male");
							}
							dto.setGender(systemCode);
							
						}
						
					//Năm sinh
					index = 11;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String namSinh	= String.valueOf((int)currentCell.getNumericCellValue());
							if (DateUtil.isCellDateFormatted(currentCell)) {
                                calendar.setTime(currentCell.getDateCellValue());
                                dto.setBirthDate(calendar.getTime());
                            }else {
                            	 try {
 	                                Date date = new SimpleDateFormat("yyyy").parse(namSinh);
 	                                dto.setBirthDate(date);
 	                            } catch (Exception ex) {
 	                                System.out.print(ex.getMessage());
 	                            }
                            }
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String namSinh = currentCell.getStringCellValue().trim();
							 try {
	                                Date date = new SimpleDateFormat("yyyy").parse(namSinh);
	                                dto.setBirthDate(date);
	                            } catch (Exception ex) {
	                                System.out.print(ex.getMessage());
	                            }
						}
					//A7. Số chứng minh
					index = 8;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String SoCMND	= String.valueOf((int)currentCell.getNumericCellValue());
							dto.setNationalID(SoCMND);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String SoCMND = currentCell.getStringCellValue().trim();
							dto.setNationalID(SoCMND);
						}
					//A12.4. Địa chỉ tạm trú
					index = 17;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String address	= String.valueOf((int)currentCell.getNumericCellValue());
							currentAddress.setText(address);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String address = currentCell.getStringCellValue().trim();
							currentAddress.setText(address);
						}
					index = 18;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String communeName	= String.valueOf((int)currentCell.getNumericCellValue());
							currentAddress.setCommuneName(communeName);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String communeName = currentCell.getStringCellValue().trim();
							currentAddress.setCommuneName(communeName);
						}
					index = 19;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String districtName	= String.valueOf((int)currentCell.getNumericCellValue());
							currentAddress.setDistrictName(districtName);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String districtName = currentCell.getStringCellValue().trim();
							currentAddress.setDistrictName(districtName);
						}

					index = 20;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String cityName	= String.valueOf((int)currentCell.getNumericCellValue());
							currentAddress.setCityName(cityName);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String cityName = currentCell.getStringCellValue().trim();
							currentAddress.setCityName(cityName);
						}
					//A11.4  Địa chỉ thường trú
					index = 13;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String address = String.valueOf((int)currentCell.getNumericCellValue());
							registeredAddress.setText(address);

						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String address = currentCell.getStringCellValue().trim();
							registeredAddress.setText(address);
						}
					index = 14;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String communeName = String.valueOf((int)currentCell.getNumericCellValue());
							registeredAddress.setCommuneName(communeName);

						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String communeName = currentCell.getStringCellValue().trim();
							registeredAddress.setCommuneName(communeName);
						}

					index = 15;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String districtName = String.valueOf((int)currentCell.getNumericCellValue());
							registeredAddress.setDistrictName(districtName);

						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String districtName = currentCell.getStringCellValue().trim();
							registeredAddress.setDistrictName(districtName);
						}
					index = 16;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String cityName = String.valueOf((int)currentCell.getNumericCellValue());
							registeredAddress.setCityName(cityName);

						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String cityName = currentCell.getStringCellValue().trim();
							registeredAddress.setCityName(cityName);
						}
////
                    index = 21;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String occupationName	= String.valueOf((int)currentCell.getNumericCellValue());
							if(occupationName!= null) {
								SystemCodeDto  occupation = ServerConst.getOccupationByText(occupationName);
								dto.setOccupationName(occupation);
							}
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String occupationName = currentCell.getStringCellValue().trim();
							if(occupationName!= null) {
								SystemCodeDto  occupation = ServerConst.getOccupationByText(occupationName);
								dto.setOccupationName(occupation);
							}
						}

                    //A14.1 Nghiện chích ma túy (NCMT)
                    index = 22;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.PWID.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.PWID.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					// A14.2 Người bán dâm (PNBD)
					index = 23;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.SW.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.SW.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					//A14.3 Phụ nữ mang thai
					index = 24;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.PREGNANT.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.PREGNANT.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					//A14.4 Người hiến máu
					index = 25;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.BLOOD_DONOR.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.BLOOD_DONOR.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					//A14.5 Bệnh nhân lao
					index = 26;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.TB_PATIENT.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.TB_PATIENT.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}
					//A14.6 Người mắc nhiễm trùng lây truyền qua đường TD             
					index = 27;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					//A14.7 Thanh niên khám tuyển nghĩa vụ quân sự
					index = 28;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.MILITARY_RECRUITS.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.MILITARY_RECRUITS.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					//A14.8 Nam quan hệ tình dục với nam (MSM)
					index = 29;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.MSM.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.MSM.getData();
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					//A14.9 Người chuyển giới
					index = 30;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = null;
								if(genderValue.equals("1")) {
									 systemCode = ServerConst.PopulationGroupValueset.TG_MEN.getData();
								}
								if(genderValue.equals("2")) {
									 systemCode = ServerConst.PopulationGroupValueset.TG_WOMEN.getData();
								}
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = null;
								if(genderValue.equals("1")) {
									 systemCode = ServerConst.PopulationGroupValueset.TG_MEN.getData();
								}
								if(genderValue.equals("2")) {
									 systemCode = ServerConst.PopulationGroupValueset.TG_WOMEN.getData();
								}
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}
					//A14.10 Vợ/chồng/ban tình của người nhiễm HIV
					index = 31;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.PARTNER_PLHIV.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.PARTNER_PLHIV.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}
					//A14.11 Vợ/chồng/ban tình của người NCC
					index = 32;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}
					//A14.12Bệnh nhân nghi ngờ AIDS 
					index = 33;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.SUSPECT_HIV.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.SUSPECT_HIV.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}
					//A14.13 Các đối tượng khác
					index = 34;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String doiTuong = String.valueOf((int)currentCell.getNumericCellValue());
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String doiTuong = currentCell.getStringCellValue().trim();
							if(doiTuong!= null && doiTuong.equals("1")) {
								SystemCodeDto systemCode = ServerConst.PopulationGroupValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}
                    index = 60;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String duongLay	= String.valueOf((int)currentCell.getNumericCellValue());
							SystemCodeDto systemCode = ServerConst.getTransmissionRouteByText(duongLay);
							hivDiagnosisDto.setTransmissionRoute(systemCode);
							if(dto.getTransmissionRoutes()==null) {
								List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
								listSystemCode.add(systemCode);
								dto.setTransmissionRoutes(listSystemCode);
							}else {
								dto.getTransmissionRoutes().add(systemCode);
							}
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String duongLay = currentCell.getStringCellValue().trim();
							SystemCodeDto systemCode = ServerConst.getTransmissionRouteByText(duongLay);
							hivDiagnosisDto.setTransmissionRoute(systemCode);
							
							if(dto.getTransmissionRoutes()==null) {
								List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
								listSystemCode.add(systemCode);
								dto.setTransmissionRoutes(listSystemCode);
							}else {
								dto.getTransmissionRoutes().add(systemCode);
							}
							
						}

                    index = 35;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String nguyCo = String.valueOf((int)currentCell.getNumericCellValue());
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.PWID.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String nguyCo = currentCell.getStringCellValue().trim();
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.PWID.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					index = 36;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String nguyCo = String.valueOf((int)currentCell.getNumericCellValue());
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.SEX_PARTNER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String nguyCo = currentCell.getStringCellValue().trim();
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.PWID.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					index = 37;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String nguyCo = String.valueOf((int)currentCell.getNumericCellValue());
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.MSM.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String nguyCo = currentCell.getStringCellValue().trim();
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.MSM.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					index = 38;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String nguyCo = String.valueOf((int)currentCell.getNumericCellValue());
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.SMP.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String nguyCo = currentCell.getStringCellValue().trim();
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.SMP.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

					index = 39;

						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String nguyCo = String.valueOf((int)currentCell.getNumericCellValue());
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String nguyCo = currentCell.getStringCellValue().trim();
							if(nguyCo!= null && nguyCo.equals("1")) {
								SystemCodeDto systemCode = ServerConst.RiskBehaviorValueset.OTHER.getData();
								hivDiagnosisDto.setRiskPopulation(systemCode);
								if(dto.getRiskPopulations()==null) {
									List<SystemCodeDto> listSystemCode = new ArrayList<SystemCodeDto>();
									listSystemCode.add(systemCode);
									dto.setRiskPopulations(listSystemCode);
								}else {
									dto.getRiskPopulations().add(systemCode);
								}
							}
							
						}

                   
					index = 64;
                        currentCell = currentRow.getCell(index);
                        String confirmatoryDate = "";

                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC
                                && currentCell.getNumericCellValue() > 0) {
                            if (DateUtil.isCellDateFormatted(currentCell)) {
                                calendar.setTime(currentCell.getDateCellValue());
                                hivDiagnosisDto.setConfirmatoryDate(calendar.getTime());
                                rapidTest.setTestPerformanceDate(calendar.getTime());
                                vlTest.setTestPerformanceDate(calendar.getTime());
                            }
 
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                        	confirmatoryDate = String.valueOf(currentCell.getStringCellValue());
                            try {
                                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(confirmatoryDate);
                                hivDiagnosisDto.setConfirmatoryDate(date);
                                rapidTest.setTestPerformanceDate(date);
                                vlTest.setTestPerformanceDate(date);
                            } catch (Exception ex) {
                                System.out.print(ex.getMessage());
                            }
                        }

					index = 55;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String confirmatoryLab = String.valueOf((int)currentCell.getNumericCellValue());
							OrganizationDto org = new OrganizationDto();
							org.setName(confirmatoryLab);
							hivDiagnosisDto.setConfirmatoryLab(org);
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String confirmatoryLab = currentCell.getStringCellValue().trim();
							OrganizationDto org = new OrganizationDto();
							org.setName(confirmatoryLab);
							hivDiagnosisDto.setConfirmatoryLab(org);
						}

                    index = 48;
                        currentCell = currentRow.getCell(index);
                        String specimenCollectionDate = "";

                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC
                                && currentCell.getNumericCellValue() > 0) {
                            if (DateUtil.isCellDateFormatted(currentCell)) {
                                calendar.setTime(currentCell.getDateCellValue());
                                hivDiagnosisDto.setSpecimenCollectionDate(calendar.getTime());
                                rapidTest.setTestPerformanceDate(calendar.getTime());
                                vlTest.setTestPerformanceDate(calendar.getTime());
                            }
 
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                        	specimenCollectionDate = String.valueOf(currentCell.getStringCellValue());
                            try {
                                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(specimenCollectionDate);
                                hivDiagnosisDto.setSpecimenCollectionDate(date);
                                rapidTest.setTestPerformanceDate(date);
                                vlTest.setTestPerformanceDate(date);
                            } catch (Exception ex) {
                                System.out.print(ex.getMessage());
                            }
                        }
                        
                        
                        ///
                        index = 59;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String res = String.valueOf((int)currentCell.getNumericCellValue());						
								SystemCodeDto systemCode = ServerConst.getResultRapidTestTypeEnumFromString(res);							
								rapidTest.setTestResultOther(systemCode);
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String res = currentCell.getStringCellValue().trim();
							SystemCodeDto systemCode = ServerConst.getResultRapidTestTypeEnumFromString(res);							
							rapidTest.setTestResultOther(systemCode);
							
						}
						//
                        index = 65;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String res = String.valueOf((int)currentCell.getNumericCellValue());						
								SystemCodeDto systemCode = new SystemCodeDto();
								if(res.equals("1")||res.equals("2")) {
									systemCode.setCode("undetectable");
									systemCode.setDisplay("undetectable");
									vlTest.setTestResultOther(systemCode);
								}
								
							
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String res = currentCell.getStringCellValue().trim();
							SystemCodeDto systemCode = new SystemCodeDto();
							if(res.equals("1")||res.equals("2")) {
								systemCode.setCode("undetectable");
								systemCode.setDisplay("undetectable");
								vlTest.setTestResultOther(systemCode);
							}
						}
						//
						index = 66;
						currentCell = currentRow.getCell(index);
						if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							String res = String.valueOf((int)currentCell.getNumericCellValue());						
							vlTest.setStringValue(res);
							
						} else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
								&& currentCell.getStringCellValue() != null) {
							String res = currentCell.getStringCellValue().trim();
							vlTest.setStringValue(res);
						}
                    specimenCollectionPlace.setName(specimenCollectionDate);
                    rapidTest.setSpecimenCollectionPlace(specimenCollectionPlace);
                    vlTest.setSpecimenCollectionPlace(specimenCollectionPlace);
                    hivRecencyTestDto.setRapidTest(rapidTest);
                    hivRecencyTestDto.setVlTest(vlTest);
                    hivDiagnosisDto.setPlaceOfSpecimenCollection(placeOfSpecimenCollection);
					dto.setDiagnosis(hivDiagnosisDto);
					dto.setHivRecencyTest(hivRecencyTestDto);
					dto.setCurrentAddress(currentAddress);
					dto.setRegisteredAddress(registeredAddress);
                    listData.add(dto);
                }
                rowIndex++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listData;
    }

}

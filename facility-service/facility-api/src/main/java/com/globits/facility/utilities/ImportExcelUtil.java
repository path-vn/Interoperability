package com.globits.facility.utilities;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.globits.facility.dto.FacilityAdministrativeUnitDto;
import com.globits.facility.dto.SystemAddressCodeDto;

public class ImportExcelUtil {
	private static Hashtable<String, Integer> hashAdministrativeUnitColumnConfig = new Hashtable<String, Integer>();

	public static List<FacilityAdministrativeUnitDto> importAdministrativeUnitFromInputStream(
			ByteArrayInputStream is) {
		List<FacilityAdministrativeUnitDto> ret = new ArrayList<FacilityAdministrativeUnitDto>();
		hashAdministrativeUnitColumnConfig.put("city", 0);// tỉnh thành phố
		hashAdministrativeUnitColumnConfig.put("cityCode", 1);// mã tỉnh
		hashAdministrativeUnitColumnConfig.put("district", 2);// quận huyện
		hashAdministrativeUnitColumnConfig.put("districtCode", 3); // mã quận huyện
		hashAdministrativeUnitColumnConfig.put("wards", 4);// phường xã
		hashAdministrativeUnitColumnConfig.put("wardsCode", 5); // mã phường xã
		try {
			@SuppressWarnings("resource")

			Workbook workbook = new XSSFWorkbook(is);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			int rowIndex = 1;
			int num = datatypeSheet.getLastRowNum();
			while (rowIndex <= num) {
				Row currentRow = datatypeSheet.getRow(rowIndex);
				Cell currentCell = null;
				if (currentRow != null) {
					FacilityAdministrativeUnitDto unitCity = new FacilityAdministrativeUnitDto();
					Integer index = hashAdministrativeUnitColumnConfig.get("city");
					if (index != null) {
						currentCell = currentRow.getCell(index);// subjectCode
						if (currentCell != null && currentCell.getStringCellValue() != null) {
							String name = currentCell.getStringCellValue();
							unitCity.setName(name);
						}
					}
					index = hashAdministrativeUnitColumnConfig.get("cityCode");
					if (index != null) {
						currentCell = currentRow.getCell(index);// subjectName
						if (currentCell != null && currentCell.getStringCellValue() != null) {
							String code = currentCell.getStringCellValue();
							unitCity.setCode(code);
							unitCity.setLevel(1);// cấp 1 - tỉnh thành phố
						}
					}
					if (!containsUnit(unitCity.getCode(), ret)) {
						ret.add(unitCity);
					}

					FacilityAdministrativeUnitDto unitDistrict = new FacilityAdministrativeUnitDto();
					index = hashAdministrativeUnitColumnConfig.get("district");
					if (index != null) {
						currentCell = currentRow.getCell(index);// subjectNameEng
						if (currentCell != null && currentCell.getStringCellValue() != null) {
							String name = currentCell.getStringCellValue();
							unitDistrict.setName(name);
						}
					}

					index = hashAdministrativeUnitColumnConfig.get("districtCode");
					if (index != null) {
						currentCell = currentRow.getCell(index);//
						if (currentCell != null && currentCell.getStringCellValue() != null) {
							String code = currentCell.getStringCellValue();
							unitDistrict.setCode(code);
							unitDistrict.setLevel(2);// cấp 2- quận huyện
							unitDistrict.setParent(unitCity);
						}
					}
					if (!containsUnit(unitDistrict.getCode(), ret)) {
						ret.add(unitDistrict);
					}

					FacilityAdministrativeUnitDto unitWards = new FacilityAdministrativeUnitDto();
					index = hashAdministrativeUnitColumnConfig.get("wards");
					if (index != null) {
						currentCell = currentRow.getCell(index);// subjectNameEng
						if (currentCell != null && currentCell.getStringCellValue() != null) {
							String name = currentCell.getStringCellValue();
							unitWards.setName(name);
						}
					}

					index = hashAdministrativeUnitColumnConfig.get("wardsCode");
					if (index != null) {
						currentCell = currentRow.getCell(index);//
						if (currentCell != null && currentCell.getStringCellValue() != null) {
							String code = currentCell.getStringCellValue();
							unitWards.setCode(code);
							unitWards.setLevel(3);// cấp 3- phường / xã/ thị trấn
							unitWards.setParent(unitDistrict);
						}
					}
					if (!containsUnit(unitWards.getCode(), ret)) {
						ret.add(unitWards);
					}

				}
				rowIndex++;
			}
			return ret;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean containsUnit(String code, List<FacilityAdministrativeUnitDto> ret) {
		for (FacilityAdministrativeUnitDto o : ret) {
			if (o != null && o.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public static List<SystemAddressCodeDto> importOpcAssitFromInputStream(InputStream is) throws IOException {
        List<SystemAddressCodeDto> listData = new ArrayList<SystemAddressCodeDto>();
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
                	SystemAddressCodeDto dto = new SystemAddressCodeDto();

                    Integer index = 0;
                    if (index != null) {
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String name = String.valueOf(currentCell.getNumericCellValue());
                            dto.setName(name);
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String name = currentCell.getStringCellValue().trim();
                            dto.setName(name);
                        }
                    }
                    
                    index = 1;
                    if (index != null) {
                        currentCell = currentRow.getCell(index);
                        if (currentCell != null && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                            String code = String.valueOf((int)currentCell.getNumericCellValue());
                            dto.setCode(code);
                        } else if (currentCell != null && currentCell.getCellTypeEnum() == CellType.STRING
                                && currentCell.getStringCellValue() != null) {
                            String code = currentCell.getStringCellValue().trim();
                            dto.setCode(code);
                        } else {
                        	dto.setCode("");
                        }
                    }

					dto.setSystemType("OpcAssist");
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

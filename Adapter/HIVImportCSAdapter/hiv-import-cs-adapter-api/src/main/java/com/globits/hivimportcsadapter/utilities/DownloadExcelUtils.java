package com.globits.hivimportcsadapter.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.globits.hivimportcsadapter.dto.SampleDto;
import com.globits.hivimportcsadapter.repository.SampleRepository;

@Service
public class DownloadExcelUtils {
	@Autowired
	HandleTimeUtils handleTimeUtils;

	@Autowired
	HandleDateUtils handleDateUtils;

	@Autowired
	SampleRepository sampleBagRepository;

	public void handleSheetSample(List<SampleDto> listSample, Sheet sheet) {
		CellReference cr = new CellReference("A4");
		int rowIndexStart = cr.getRow();

		Cell cell;
		for (SampleDto sampleBag : listSample) {
			rowIndexStart++;
			Row row = sheet.createRow(rowIndexStart);

			cr = new CellReference("A" + rowIndexStart);
			cell = row.createCell(cr.getCol());
			if (sampleBag.getCode() != null)
				cell.setCellValue(sampleBag.getCode());

			cr = new CellReference("B" + rowIndexStart);
			cell = row.createCell(cr.getCol());

		}
	}
	
	////
	
	public ByteArrayResource exportHIVInFoToExcel() throws IOException {
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");

		
		/* Tạo font */
		XSSFFont fontBold = workbook.createFont();
		fontBold.setBold(true); // set bold
		fontBold.setFontHeight(10); // add font size

		XSSFFont fontBoldTitle = workbook.createFont();
		fontBoldTitle.setBold(true); // set bold
		fontBoldTitle.setFontHeight(15); // add font size

		/* Tạo cell style */
		XSSFCellStyle titleCellStyle = workbook.createCellStyle();
		titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
		titleCellStyle.setFont(fontBoldTitle);

		XSSFCellStyle tableHeadCellStyle = workbook.createCellStyle();
		tableHeadCellStyle.setFont(fontBold);
		tableHeadCellStyle.setBorderBottom(BorderStyle.THIN);
		tableHeadCellStyle.setBorderTop(BorderStyle.THIN);
		tableHeadCellStyle.setBorderLeft(BorderStyle.THIN);
		tableHeadCellStyle.setBorderRight(BorderStyle.THIN);
			
		XSSFRow row  = sheet.createRow(0);
		XSSFCell cell =null;
		
		cell = row.createCell(0);
		cell.setCellValue("HoTen");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("DanToc");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("GioiTinh");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("NamSinh");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("SoCMND");
		cell.setCellStyle(tableHeadCellStyle);
			
		cell = row.createCell(5);
		cell.setCellValue("NgheNghiep");
		cell.setCellStyle(tableHeadCellStyle);
			
		cell = row.createCell(6);
		cell.setCellValue("DoiTuong");
		cell.setCellStyle(tableHeadCellStyle);
			
		cell = row.createCell(7);
		cell.setCellValue("NguyCo");
		cell.setCellStyle(tableHeadCellStyle);
			
		cell = row.createCell(8);
		cell.setCellValue("DuongLay");
		cell.setCellStyle(tableHeadCellStyle);
			
		cell = row.createCell(9);
		cell.setCellValue("XaHK");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(10);
		cell.setCellValue("HuyenHK");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(11);
		cell.setCellValue("TinhHK");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(12);
		cell.setCellValue("DuongPhoHK");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(13);
		cell.setCellValue("ToHK");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(14);
		cell.setCellValue("SoNhaHK");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(15);
		cell.setCellValue("XaTT");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(16);
		cell.setCellValue("HuyenTT");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(17);
		cell.setCellValue("TinhTT");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(18);
		cell.setCellValue("DuongPhoTT");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(19);
		cell.setCellValue("ToTT");
		cell.setCellStyle(tableHeadCellStyle);
		
		cell = row.createCell(20);
		cell.setCellValue("SoNhaTT");
		cell.setCellStyle(tableHeadCellStyle);
		
		XSSFRow tableDataRow;
		
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		    workbook.write(out);
		    out.close();
		    return new ByteArrayResource(out.toByteArray());
	}


}

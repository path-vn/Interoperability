package com.globits.cbs.deidentification.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.globits.cbs.deidentification.dto.SampleDto;
import com.globits.cbs.deidentification.service.ExportExcelService;
import com.globits.cbs.deidentification.service.SampleService;
import com.globits.cbs.deidentification.utilities.DownloadExcelUtils;

@Service
public class ExportExcelServiceImpl implements ExportExcelService {
	@Autowired
	SampleService sampleService;

	@Autowired
	DownloadExcelUtils downloadExcelUtils;

	@Value("${attachment.path}")
	private String attachmentPath;

	@Override
	public ByteArrayResource exportSample() {
		List<SampleDto> listSample = new ArrayList<SampleDto>();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			FileInputStream inputStream = new FileInputStream(new File(attachmentPath + "sample.xlsx"));

			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			downloadExcelUtils.handleSheetSample(listSample, sheet);

			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ByteArrayResource(outputStream.toByteArray());
	}

}

package com.globits.hivimportcsadapter.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.globits.hivimportcsadapter.service.ImportExcelService;
import com.globits.hivimportcsadapter.utilities.UploadExcelUtils;
import com.globits.security.service.UserService;

@Service
public class ImportExcelServiceImpl implements ImportExcelService {
	@Autowired
	UploadExcelUtils uploadExcelUtilities;

	@Autowired
	UserService userService;

	@Override
	public void importSample(MultipartFile[] uploadfiles) {
		String currentUser = userService.getCurrentUser().getDisplayName();

		for (MultipartFile file : uploadfiles)
			try {
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes());

				Workbook workbook = new XSSFWorkbook(byteArrayInputStream);
				Sheet sheet = workbook.getSheetAt(0);

				uploadExcelUtilities.handleSheetSampleBag(sheet, currentUser);

				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}

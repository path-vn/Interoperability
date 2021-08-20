package com.globits.hivimportcsadapter.utilities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globits.hivimportcsadapter.domain.Sample;
import com.globits.hivimportcsadapter.repository.SampleRepository;
import com.globits.hivimportcsadapter.service.SampleService;

@Service
public class UploadExcelUtils {
	@Autowired
	HandleStringUtils handleString;

	@Autowired
	HandleTimeUtils handleTime;

	@Autowired
	HandleDateUtils handleDate;

	@Autowired
	SampleRepository sampleRepository;

	@Autowired
	SampleService sampleBagService;

	@SuppressWarnings("deprecation")
	public void handleSheetSampleBag(Sheet sheet, String currentUser) {
		String code = "";
		CellReference cr = new CellReference("A5");
		int startRowIndex = cr.getRow();
		int startColumnIndex = cr.getCol();

		// Convert from index java to index position excel
		startRowIndex++;
		Row row = sheet.getRow(startRowIndex);
		Cell cell = null;
		while (row != null && !row.getCell(startColumnIndex).getStringCellValue().isEmpty()) {
			cr = new CellReference("A" + startRowIndex);
			cell = sheet.getRow(cr.getRow()).getCell(cr.getCol());
			code = handleString.convertToString(cell.getStringCellValue());

			Sample sample = null;
			if (!sampleRepository.getByCode(code).isEmpty()) {
				sample = sampleRepository.getByCode(code).get(0);
			} else {
				sample = new Sample();
				sample.setCreateDate(new LocalDateTime());
				sample.setCreatedBy(currentUser);
				sample.setCode(code);
			}
			sample.setModifyDate(new LocalDateTime());

			sampleRepository.save(sample);

			startRowIndex++;
			row = sheet.getRow(startRowIndex);
		}
	}

}

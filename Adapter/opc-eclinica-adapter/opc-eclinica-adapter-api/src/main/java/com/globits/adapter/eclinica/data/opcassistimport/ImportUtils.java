package com.globits.adapter.eclinica.data.opcassistimport;

import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.globits.adapter.eclinica.utils.CommonUtils;

public class ImportUtils {

	private static final DataFormatter formatter = new DataFormatter();

	public static LocalDateTime fromCellValue(Cell cell) {

		if (cell == null) {
			return null;
		}

		String val = formatter.formatCellValue(cell);
		LocalDateTime dateVal = null;

		if (!CommonUtils.isEmpty(val)) {
			String[] arr = val.split("/");

			if (arr == null || arr.length != 3 || CommonUtils.isEmpty(arr[0], true) || CommonUtils.isEmpty(arr[1], true)
					|| CommonUtils.isEmpty(arr[2], true)) {
				return null;
			}

			int day = Integer.parseInt(arr[0]);
			int month = Integer.parseInt(arr[1]);
			int year = Integer.parseInt(arr[2]);

			switch (month) {
				case 2:
					if (day > 28) {
						day = 28;
					}

					break;

				case 4:
				case 6:
				case 9:
				case 11:
					if (day > 30) {
						day = 30;
					}

					break;

				default:
					break;
			}

			dateVal = LocalDateTime.of(year, month, day, 0, 0, 0);
		}

		return dateVal;
	}

	public static String[] getAddressCodes(Cell cell) {

		if (cell == null) {
			return null;
		}

		String val = formatter.formatCellValue(cell);
		if (CommonUtils.isEmpty(val)) {
			return null;
		}

		String[] codes = val.split("\\|");
		if (codes.length < 2) {
			return null;
		}

		int iCommuneCode = Integer.parseInt(codes[0]);
		int iDistrictCode = Integer.parseInt(codes[1]);
		int iProvinceCode = Integer.parseInt(codes[2]);

		if (iCommuneCode <= 0 || iDistrictCode <= 0 || iProvinceCode <= 0) {
			return null;
		}

		String[] addrCodes = { "commune_" + iCommuneCode, "district_" + iDistrictCode, "province_" + iProvinceCode };

		return addrCodes;
	}
}

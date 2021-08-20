package com.globits.cbs.deidentification.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globits.cbs.deidentification.service.ExportExcelService;

@RestController
@RequestMapping("/api/downloadExcel")
public class RestDownloadExcelController {
	@Autowired
	private ExportExcelService exportExcelService;

	@GetMapping("/sample")
	public void exportSampleBag(HttpServletResponse response) {
		ByteArrayResource byteArrayResource = null;

		try {
			byteArrayResource = exportExcelService.exportSample();

			InputStream ins = new ByteArrayInputStream(byteArrayResource.getByteArray());
			IOUtils.copy(ins, response.getOutputStream());

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			// response.setContentType("application/vnd.ms-excel.sheet.macroEnabled.12");
			response.addHeader("Content-Disposition", "attachment; filename=file.xlsx");
			response.flushBuffer();
		} catch (Exception e) {
			System.out.println(e + "");
		}
	}

}

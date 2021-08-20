package com.globits.hivimportcsadapter.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.hivimportcsadapter.ServerConst;
import com.globits.hivimportcsadapter.domain.DataSource;
import com.globits.hivimportcsadapter.repository.DataFileRepository;
import com.globits.hivimportcsadapter.repository.DataSourceRepository;
import com.globits.hivimportcsadapter.service.ExportExcelService;
import com.globits.hivimportcsadapter.utilities.DownloadExcelUtils;
import com.globits.hivimportcsadapter.utilities.ResourceUtil;

@RestController
@RequestMapping("/api/downloadExcel")
public class RestDownloadExcelController {
	@Autowired
	private ExportExcelService exportExcelService;
	@Autowired
	DownloadExcelUtils downloadExcelUtils;
	@Autowired
	private DataFileRepository fileRepository;
	@Autowired
	private DataSourceRepository dataSourceRepository;
	@Autowired
	ResourceLoader resourceLoader;
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
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public void exportHIVInFoToExcel(HttpSession session, HttpServletResponse response, @PathVariable("id") UUID id) throws IOException {
		
		
		
		DataSource dto = dataSourceRepository.getOne(id);
		if (dto.getCode().equals(ServerConst.HIVINFO)) {
			ByteArrayResource excelFile = null;
			excelFile = downloadExcelUtils.exportHIVInFoToExcel();
			InputStream ins = new ByteArrayInputStream(excelFile.getByteArray());
			org.apache.commons.io.IOUtils.copy(ins, response.getOutputStream());	 
		}else if(dto.getCode().equals(ServerConst.HTCELOG)) {
//			File excelFile = new File("E:/test1.xlsx");
//			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inputStream = ResourceUtil.Instance().getFileFromResourceAsStream("sample.xlsx");
//			Resource resource = resourceLoader.getResource("classpath:sample.xlsx");
//			InputStream inputStream = resource.getInputStream();
			org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());	 	
			
//	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//	        response.addHeader("Content-Disposition", "attachment; filename=DanhSachDonViThamGia.xlsx");
		}	
		
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition", "attachment; filename=DanhSachDonViThamGia.xlsx");
	}

}

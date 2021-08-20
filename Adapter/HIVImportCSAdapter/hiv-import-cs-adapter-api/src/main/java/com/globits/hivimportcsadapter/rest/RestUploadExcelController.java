package com.globits.hivimportcsadapter.rest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.globits.hivimportcsadapter.ServerConst;
import com.globits.hivimportcsadapter.domain.DataFile;
import com.globits.hivimportcsadapter.dto.AdapterObjectDto;
import com.globits.hivimportcsadapter.dto.DataFileDto;
import com.globits.hivimportcsadapter.dto.HtcElogDto;
import com.globits.hivimportcsadapter.functiondto.HIVInfoDto;
import com.globits.hivimportcsadapter.repository.DataFileRepository;
import com.globits.hivimportcsadapter.repository.DataSourceRepository;
import com.globits.hivimportcsadapter.service.DataFileService;
import com.globits.hivimportcsadapter.service.ImportExcelService;
import com.globits.hivimportcsadapter.utilities.ImportExportExcelUtil;
import com.globits.hivimportcsadapter.utilities.RestTemplateUtils;

@RestController
@RequestMapping("/api/uploadExcel")
public class RestUploadExcelController {
	@Autowired
	private ImportExcelService importExcelService;
	@Autowired
	private Environment env;
	@Autowired
	private DataFileRepository fileRepository;
	@Autowired
	DataSourceRepository dataSourceRepository;
	@Autowired
	private DataFileService dataFileService;
	@PostMapping("/sampleBag")
	@ResponseBody
	public ResponseEntity<?> importSample(@RequestParam("uploadfiles") MultipartFile[] uploadfiles) {
		importExcelService.importSample(uploadfiles);
		return null;
	}
	
	///
	@RequestMapping(value = "/file/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DataFileDto> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,@RequestParam("dataSourceId") UUID dataSourceId) {
		DataFileDto result = null;
		String path = "";
		if (env.getProperty("hiv.file.folder") != null) {
			path = env.getProperty("hiv.file.folder");
		}
		try {
			String extension = uploadfile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
			String name = uploadfile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[0];
			 SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyhhmmss");
			 Date date = new Date();
			 String dateFormat = formatter.format(date);
			UUID randamCode = UUID.randomUUID();
			String filename = name + dateFormat + "." + extension;
			String filePath = path;
			///
//			String filePath = uploadfile.getOriginalFilename();
//			filePath = ServerConst.PathUploadDocumentAttachment + filePath;
//			FileOutputStream stream = new FileOutputStream(filePath);
			
			try {
				File fileToBeSaved = new File(filePath, filename);
				uploadfile.transferTo(fileToBeSaved); 
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			DataFile file = new DataFile();
			file.setContentSize(uploadfile.getSize());
			file.setContentType(uploadfile.getContentType());
			file.setFileName(filename);
			file.setFilePath(filePath);
			if(dataSourceId != null) {
				file.setDataSource(dataSourceRepository.getOne(dataSourceId));
			}
			file = fileRepository.save(file);
			DataFileDto fileDto = new DataFileDto(file,true);
			result = fileDto;
			return new ResponseEntity<DataFileDto>(result,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
//		return null;
	}
	//
	@RequestMapping(path = "/getFile/{id}", method = RequestMethod.POST)
	public void getImage(HttpServletResponse response, @PathVariable("id") UUID id) throws IOException {
		String path = "";
		if(env.getProperty("hiv.file.folder") != null) {
			path = env.getProperty("hiv.file.folder");
		}
		int splitSize = 100;
		DataFile dto = fileRepository.getOne(id);
	    File file = new File(path+ dto.getFileName() );
	    if(file.exists()) {
	        String contentType = "application/octet-stream";
	        response.setContentType(contentType);
	        OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(file);
	        ByteArrayInputStream bis = new ByteArrayInputStream(in.readAllBytes());
//	        ServerConst.HIVINFOR
	        
	        if(dto.getDataSource().getCode().equals(ServerConst.HIVINFO)) {
	        	List<HIVInfoDto> list = new ArrayList<HIVInfoDto>();
	        	 list = ImportExportExcelUtil.importHIVInfoFromInputStream(bis);
	        	 String responseData = RestTemplateUtils.hIVInfoToOpenHim(list);
	        }
	        if(dto.getDataSource().getCode().equals(ServerConst.HTCELOG)) {
	        	List<HtcElogDto> list = new ArrayList<HtcElogDto>();
	        	 list = ImportExportExcelUtil.importHTCELOGFromInputStream(bis);
	        	 AdapterObjectDto adapterObjectDto = new AdapterObjectDto();
	        //// Check if ListData's size more than 10 QR, then split into parts of 10 QR
	     		if (list != null && list.size() > 0) {
	     			if (list.size() > splitSize) {
	     				if ((list.size() % splitSize) == 0) {
	     					int part = ((int) list.size() / splitSize);
	     					for (int i = 0; i < part; i++) {
	     						List<HtcElogDto> subList = list.subList(splitSize * i,
	     								splitSize * (i + 1));
	     						 adapterObjectDto.setListData(subList);
	     			        	 ResponseEntity<String> responseData = RestTemplateUtils.postObjectToOpenHim(adapterObjectDto);



	     					}
	     				} else {
	     					int part = ((int) list.size() / splitSize) + 1;
	     					for (int i = 0; i < part; i++) {
	     						List<HtcElogDto> subList = new ArrayList<HtcElogDto>();
	     						if (i < (part - 1)) {
	     							subList = list.subList(splitSize * i, splitSize * (i + 1));
	     						} else {
	     							subList = list.subList(splitSize * i, list.size());
	     						}
	     						adapterObjectDto.setListData(subList);
	     			        	 ResponseEntity<String> responseData = RestTemplateUtils.postObjectToOpenHim(adapterObjectDto);


	     					}
	     				}
	     			} else {
	     				
	     				 adapterObjectDto.setListData(list);
	    	        	 ResponseEntity<String> responseData = RestTemplateUtils.postObjectToOpenHim(adapterObjectDto);

	     			}
	     		}
	        	
	        }

	        
	        // copy from in to out
	        IOUtils.copy(in, out);
	        out.close();
	        in.close();
	    }else {
	        throw new FileNotFoundException();
	    }
	}
}

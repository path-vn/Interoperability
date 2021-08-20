package com.globits.cbs.deidentification.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportExcelService {

	void importSample(MultipartFile[] uploadfiles);

}

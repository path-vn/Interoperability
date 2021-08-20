package com.globits.hivimportcsadapter.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportExcelService {

	void importSample(MultipartFile[] uploadfiles);

}

package com.globits;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.globits.cbs.deidentification.dto.DeIdentificationConfigDto;
import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.service.DeIdentificationConfigService;
import com.globits.cbs.es.service.EsPatientService;
import com.globits.config.DatabaseConfig;
import com.globits.security.dto.UserDto;
import com.globits.security.service.UserService;

@RunWith(SpringRunner.class)
@ComponentScan
@SpringBootTest(classes = DatabaseConfig.class)
//@Transactional(propagation = Propagation.REQUIRED)
@EnableAutoConfiguration(exclude = {RestClientAutoConfiguration.class}) 
public class ServiceTest {

	@Autowired
	UserService service;
	@Autowired
	private EsPatientService esPatientService;
	
	@Autowired
	private DeIdentificationConfigService configService;
	@Test
	public void testGetPatient() throws Exception {
//		DeIdentificationConfigDto config = new DeIdentificationConfigDto();
//		config.setCode("lastName");
//		configService.saveOrUpdate(UUID.randomUUID(), config);
		PatientDto dto = esPatientService.searchPatientFromESById("10");
		System.out.print(dto);
		//esPatientService.findByPage(1, 10);
	}
	//@Test
	public void testGetUserWorks() {
		Page<UserDto> page = service.findByPage(1, 10);
		assertTrue(page.getTotalElements() >= 0);
	}
}

package com.globits.listeners;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.globits.core.dto.PersonDto;
import com.globits.core.utils.CommonUtils;
import com.globits.hiv.receive.domain.UserSystemCode;
import com.globits.hiv.receive.repository.UserSystemCodeRepository;
import com.globits.security.domain.Role;
import com.globits.security.domain.User;
import com.globits.security.dto.RoleDto;
import com.globits.security.dto.UserDto;
import com.globits.security.service.RoleService;
import com.globits.security.service.UserService;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {

	private static boolean eventFired = false;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSystemCodeRepository userSystemCodeRepository;
	
	@Autowired
	private Environment env;
//	@Autowired
//	private ResourceDefService resDefService;

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

//		if (eventFired) {
//			return;
//		}
//		if(env.getProperty("server.fhir.url")!=null) {
//			HivConst.serverFhirUrl = env.getProperty("server.fhir.url");
//		}	
//		if(env.getProperty("server.usingencode")!=null) {
//			HivConst.IsUsingEncodeData = Boolean.valueOf(env.getProperty("server.usingencode"));
//		}	
		
		logger.info("Application started.");

		eventFired = true;
		
		try {
			createRoles();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		createAdminUser();
		createTestUser();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	private void createAdminUser() {

		UserDto userDto = userService.findByUsername(com.globits.core.Constants.USER_ADMIN_USERNAME);

		if (CommonUtils.isNotNull(userDto)) {
			return;
		}

		userDto = new UserDto();
		userDto.setUsername(com.globits.core.Constants.USER_ADMIN_USERNAME);
		//userDto.setPassword(SecurityUtils.getHashPassword("admin"));
		userDto.setPassword("admin");
		userDto.setEmail("admin@globits.net");
		userDto.setActive(true);
		userDto.setDisplayName("Admin User");

		Role role = roleService.findByName(com.globits.core.Constants.ROLE_ADMIN);
		
		userDto.getRoles().addAll(Arrays.asList(new RoleDto(role)));
		
		PersonDto person = new PersonDto();
		person.setGender("M");
		person.setFirstName("Admin");
		person.setLastName("User");
		person.setDisplayName("Admin User");
		
		userDto.setPerson(person);
		
		try {
			userService.save(userDto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void createTestUser() {

		UserDto userDto = userService.findByUsername("test");

		if (CommonUtils.isNotNull(userDto)) {
			return;
		}

		userDto = new UserDto();
		userDto.setUsername("test");
		//userDto.setPassword(SecurityUtils.getHashPassword("admin"));
		userDto.setPassword("test@123");
		userDto.setEmail("test@globits.net");
		userDto.setActive(true);
		userDto.setDisplayName("Test User");

		Role role = roleService.findByName(com.globits.core.Constants.ROLE_ADMIN);
		
		userDto.getRoles().addAll(Arrays.asList(new RoleDto(role)));
		
		PersonDto person = new PersonDto();
		person.setGender("M");
		person.setFirstName("Test");
		person.setLastName("User");
		person.setDisplayName("Test User");
		
		userDto.setPerson(person);
		
		try {
			userDto = userService.save(userDto);			
			User u = userService.findById(userDto.getId());
			UserSystemCode code = new UserSystemCode();
			code.setCreateDate(new Date());
			code.setCreatedBy("test");
			code.setModifyDate(new Date());
			code.setModifiedBy("test");
//			code.setId(UUID.randomUUID());
			code.setSystemCode("test");
			code.setUser(u);
			userSystemCodeRepository.save(code);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void createRoles() throws XMLStreamException {

		List<String> roleNames = new ArrayList<>();

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream in = getClass().getClassLoader().getResourceAsStream("sys-roles.xml");
		XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in, "UTF-8");

		streamReader.nextTag();
		streamReader.nextTag();

		while (streamReader.hasNext()) {
			if (streamReader.isStartElement()) {
				switch (streamReader.getLocalName()) {
				case "name": {
					roleNames.add(streamReader.getElementText());
					break;
				}
				}
			}
			streamReader.next();
		}

		streamReader.close();

		for (String roleName : roleNames) {
			createRoleIfNotExist(roleName);
		}
	}

	private void createRoleIfNotExist(String roleName) {

		if (CommonUtils.isEmpty(roleName)) {
			return;
		}

		Role role = roleService.findByName(roleName);

		if (CommonUtils.isNotNull(role)) {
			return;
		}

		if (role == null) {
			role = new Role();
			role.setName(roleName);
		}

		try {
			roleService.save(role);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

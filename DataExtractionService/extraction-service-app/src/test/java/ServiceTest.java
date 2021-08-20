import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.globits.config.DatabaseConfig;
import com.globits.security.dto.UserDto;
import com.globits.security.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseConfig.class)
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceTest {

	@Autowired
	UserService service;

	@Test
	public void testGetUserWorks() {
		Page<UserDto> page = service.findByPage(1, 10);
		assertTrue(page.getTotalElements() >= 0);
	}
}

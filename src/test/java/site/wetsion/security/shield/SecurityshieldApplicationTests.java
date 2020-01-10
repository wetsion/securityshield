package site.wetsion.security.shield;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityshieldApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new BCryptPasswordEncoder(11).encode("123456"));
	}

}

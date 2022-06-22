package com.axonactive.backEndFinalExam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BackEndFinalExamApplicationTests {

	@Test
	void contextLoads() {
		String password=new  BCryptPasswordEncoder().encode("1234");
		System.out.println(password);
	}

}

package com.cognizant.sprintmanagement;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sprintmanagement.main.SprintManagementApplication;

@SpringBootTest(classes = SprintManagementApplication.class)
@ContextConfiguration
class SprintManagementApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}

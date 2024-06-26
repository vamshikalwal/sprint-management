package com.sprintmanagement.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan (basePackages="com.cognizant.entities")
@EnableJpaRepositories(basePackages="com.cognizant.repository")
@ComponentScan(basePackages = "com.cognizant.*")
@EnableDiscoveryClient
public class SprintManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintManagementApplication.class, args);
	}

}
 
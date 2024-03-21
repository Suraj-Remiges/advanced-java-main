package com.remiges.adv_java_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AdvJavaAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvJavaAssignmentApplication.class, args);
	}

}

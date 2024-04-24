package com.abc.tktbookingservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TktbookingServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TktbookingServicesApplication.class, args);
	}

}

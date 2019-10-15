package com.progresssoft.cdw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.progresssoft.cdw")
public class ClusteredDataWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClusteredDataWarehouseApplication.class, args);
		
	}
 
}

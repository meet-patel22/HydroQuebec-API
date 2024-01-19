package com.hydroQ.HydroQApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.hydroQ.Employee.Entity" ,"com.hydroQ.PowerOutages.Entity"})
public class HydroQApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydroQApiApplication.class, args);
	}

}

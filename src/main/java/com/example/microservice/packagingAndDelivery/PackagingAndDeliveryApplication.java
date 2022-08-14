package com.example.microservice.packagingAndDelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PackagingAndDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackagingAndDeliveryApplication.class, args);
	}

}

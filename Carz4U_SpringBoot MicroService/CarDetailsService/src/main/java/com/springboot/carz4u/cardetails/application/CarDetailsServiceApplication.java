package com.springboot.carz4u.cardetails.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDetailsServiceApplication.class, args);
	}

}

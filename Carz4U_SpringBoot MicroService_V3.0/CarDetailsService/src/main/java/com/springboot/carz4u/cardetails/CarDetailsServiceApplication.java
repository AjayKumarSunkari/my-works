package com.springboot.carz4u.cardetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class CarDetailsServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(CarDetailsServiceApplication.class);
	
	public static void main(String[] args) {
		log.info("Start {} Class, main method.",log.getName());
		SpringApplication.run(CarDetailsServiceApplication.class, args);
		log.info("Start {} Class, main method.",log.getName());
	}

}

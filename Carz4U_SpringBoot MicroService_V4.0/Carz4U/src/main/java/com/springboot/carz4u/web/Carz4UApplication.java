package com.springboot.carz4u.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.springboot.carz4u.web.controller.CarDetailsController;
import com.springboot.carz4u.web.repository.CarDetailsRepository;
import com.springboot.carz4u.web.repository.RemoteCarsRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Carz4UApplication {
	
	private static final Logger log = LoggerFactory.
			getLogger(CarDetailsController.class);

	public static final String CAR_SERVICE_URL = "http://CARDETAILSSERVICE-MICROSERVICE";
	
	public static final String RTO_SERVICE_URL = "http://RTOSERVICES-MICROSERVICE";
	
		
	public static void main(String[] args) {
		
		log.info("Start {} Class, main method.",log.getName());
		SpringApplication.run(Carz4UApplication.class, args);
		log.info("End {} Class, main method.",log.getName());
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CarDetailsRepository carDetailsRepository(){
		return new RemoteCarsRepository(CAR_SERVICE_URL, RTO_SERVICE_URL);
	}
}

package com.springboot.carz4u.web.Carz4U;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class Carz4UApplication {

	public static final String CAR_SERVICE_URL = "http://CARDETAILSSERVICE-MICROSERVICE";
	
	public static final String RTO_SERVICE_URL = "http://RTOSERVICES-MICROSERVICE";
	
		
	public static void main(String[] args) {
		SpringApplication.run(Carz4UApplication.class, args);
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

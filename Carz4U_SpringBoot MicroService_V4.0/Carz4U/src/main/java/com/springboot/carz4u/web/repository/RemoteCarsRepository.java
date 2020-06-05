package com.springboot.carz4u.web.repository;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springboot.carz4u.web.exception.RecordNotFoundException;
import com.springboot.carz4u.web.model.Car;
import com.springboot.carz4u.web.model.RtoCharges;

public class RemoteCarsRepository implements CarDetailsRepository{

	@Autowired
	protected RestTemplate restTemplate;
	
	protected String carServiceUrl;
	
	protected String rtoServiceUrl;
	
	private static final Logger log = LoggerFactory.
			getLogger(RemoteCarsRepository.class);
	
	public RemoteCarsRepository(String carServiceUrl, String rtoServiceUrl) {
		this.carServiceUrl = carServiceUrl.startsWith("http") ? carServiceUrl
				: "http://" + carServiceUrl;
		
		this.rtoServiceUrl = rtoServiceUrl.startsWith("http") ? rtoServiceUrl
				: "http://" + rtoServiceUrl;
	}
	
	@Override
	@HystrixCommand(fallbackMethod = "getFallbackAllCars",
						commandProperties = {
								@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50"),
								@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
								@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
								@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
								}
					)
	public List<Car> getAllCars() {
		
		Car[] cars = restTemplate.getForObject(carServiceUrl+"/cars", Car[].class);
		return Arrays.asList(cars);
		
	}
	/**Hystrix Circut breaker fallback method to handle CarDetailsService unavailability, 
	 * down, slowness. */
	public List<Car> getFallbackAllCars() {
		return Arrays.asList(new Car("No cars found","","","",0,""));
	}

	@Override
	@HystrixCommand(fallbackMethod="getFallbackCar")
	public Car getCar(String carName) {
		try{
			
		Car car = restTemplate.getForObject(carServiceUrl + "/cars/{carName}",
				Car.class, carName);
		if(car == null) {
			return car;
		}else{
			return car;
		}
		}catch(RecordNotFoundException rnfe){
			log.error("RecordNotFoundException caught in {} ",log.getName());
			throw new RecordNotFoundException();
		}
	}
	/**Hystrix Circut breaker fallback method to handle CarDetailsService unavailability, 
	 * down, slowness. */
	public Car getFallbackCar(String carName) {
		return new Car("No car found","NA","NA","NA",0,"");
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackRtoCharge")
	public RtoCharges getRtoCharge(String carName){
		return restTemplate.getForObject(rtoServiceUrl + "/rtoCharges/{carName}",
				RtoCharges.class, carName);
	}
	/**Hystrix Circut breaker fallback method to handle RtoServices unavailability, 
	 * down, slowness. */
	public RtoCharges getFallbackRtoCharge(String carName){
		return new RtoCharges(0,"No RTO charge found",0);
	}
}

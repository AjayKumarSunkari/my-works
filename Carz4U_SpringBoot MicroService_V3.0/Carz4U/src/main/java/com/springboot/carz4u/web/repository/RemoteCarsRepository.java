package com.springboot.carz4u.web.repository;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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
	public List<Car> getAllCars() {
		
		Car[] cars = restTemplate.getForObject(carServiceUrl+"/cars", Car[].class);
		return Arrays.asList(cars);
		
	}

	@Override
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
	
	@Override
	public RtoCharges getRtoCharge(String carName){
		return restTemplate.getForObject(rtoServiceUrl + "/rtoCharges/{carName}",
				RtoCharges.class, carName);
	}
}

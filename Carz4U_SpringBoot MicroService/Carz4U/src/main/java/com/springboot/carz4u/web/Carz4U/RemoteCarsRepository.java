package com.springboot.carz4u.web.Carz4U;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteCarsRepository implements CarDetailsRepository{

	@Autowired
	protected RestTemplate restTemplate;
	
	protected String carServiceUrl;
	
	protected String rtoServiceUrl;
	
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
		return restTemplate.getForObject(carServiceUrl + "/cars/{carName}",
				Car.class, carName);
	}
	
	@Override
	public RtoCharges getRtoCharge(String carName){
		return restTemplate.getForObject(rtoServiceUrl + "/rtoCharges/{carName}",
				RtoCharges.class, carName);
	}
}

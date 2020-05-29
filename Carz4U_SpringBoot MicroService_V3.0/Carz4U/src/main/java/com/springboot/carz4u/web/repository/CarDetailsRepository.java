package com.springboot.carz4u.web.repository;

import java.util.List;

import com.springboot.carz4u.web.model.Car;
import com.springboot.carz4u.web.model.RtoCharges;

public interface CarDetailsRepository {

		List<Car> getAllCars();
		
		Car getCar(String carName);
		
		RtoCharges getRtoCharge(String carName);
	
}

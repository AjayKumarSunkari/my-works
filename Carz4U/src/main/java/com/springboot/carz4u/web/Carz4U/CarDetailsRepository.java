package com.springboot.carz4u.web.Carz4U;

import java.util.List;

public interface CarDetailsRepository {

		List<Car> getAllCars();
		
		Car getCar(String carName);
		
		RtoCharges getRtoCharge(String carName);
	
}

package com.springboot.carz4u.cardetails.application;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class CarDetailsController {

	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CarDetailsRepository carDetailsRepository;
	
/** Hit this URL http://localhost:8000/cars/Swift to get details from this service */
	
	@GetMapping("/cars/{carName}")
	public Car findByCarName(@PathVariable String carName){
		Car carDetails = 
				carDetailsRepository.findByCarName(carName);
				
		carDetails.setPort(
			Integer.parseInt(environment.getProperty("local.server.port")));  
		
		return carDetails;
	}

	@GetMapping("/cars")
	public List<Car> findAllCars(){
		
		List<Car> carDetailsList = 
				carDetailsRepository.findAll();
				
		return carDetailsList;
	}
	/** To add new car to the H2 DB, Use Postman and send POST request with a JSON object*/
	@PostMapping("/cars/{carName}")
	public void saveCarDetails(@PathVariable String carName, @RequestBody Car car){
		carDetailsRepository.save(car);
	}
	
	@DeleteMapping("/cars/{carName}")
	public void deleteCar(@PathVariable String carName){
		carDetailsRepository.deleteByCarName(carName);
	}
	
	/** To update car details to the H2 DB, Use Postman and send PUT request with a modified JSON modified object*/
	@PutMapping("/cars/{carName}")
	public void updateCarDetails(@PathVariable String carName, @RequestBody Car car){
		carDetailsRepository.save(car);
	}
	
}

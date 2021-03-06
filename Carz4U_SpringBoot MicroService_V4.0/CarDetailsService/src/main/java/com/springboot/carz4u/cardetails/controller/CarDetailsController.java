package com.springboot.carz4u.cardetails.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.carz4u.cardetails.model.Car;
import com.springboot.carz4u.cardetails.repository.CarDetailsRepository;

@RestController
@Transactional
public class CarDetailsController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CarDetailsRepository carDetailsRepository;
	
	private static final Logger log = LoggerFactory.getLogger(CarDetailsController.class);
	
/** Hit this URL http://localhost:8000/cars/Swift to get details from this service */
	
	@GetMapping("/cars/{carName}")
	public Car findByCarName(@PathVariable String carName){
		log.info("Start {} Class, findByCarName method.",log.getName());
		/**When Resource(Car) is not found/available Spring Boot throws 500 - Internal server error instead of 404- Not Found.
		 * So to handle that error, we choose Optional container object Optional<Car>. */
		Optional<Car> optCar =
				carDetailsRepository.findByCarName(carName);
		Car car;
		if(!optCar.isPresent()){
			car = null;
			return car;
		}
		car = optCar.get();
		car.setPort(
			Integer.parseInt(environment.getProperty("local.server.port")));  
		log.info("End {} Class, findByCarName method.",log.getName());
		return car;
	}

	@GetMapping("/cars")
	public List<Car> findAllCars(){
		log.info("Start {} Class, findAllCars method.",log.getName());
		List<Car> carDetailsList = 
				carDetailsRepository.findAll();
		log.info("End {} Class, findAllCars method.",log.getName());
		return carDetailsList;
	}
	/** To add new car to the H2 DB, Use Postman and send POST request with a JSON object*/
	@PostMapping("/cars/{carName}")
	public void saveCarDetails(@PathVariable String carName, @RequestBody Car car){
		log.info("Start {} Class, saveCarDetails method.",log.getName());
		carDetailsRepository.save(car);
		log.info("End {} Class, saveCarDetails method.",log.getName());
	}
	
	@DeleteMapping("/cars/{carName}")
	public void deleteCar(@PathVariable String carName){
		log.info("Start {} Class, deleteCar method.",log.getName());
		carDetailsRepository.deleteByCarName(carName);
		log.info("End {} Class, deleteCar method.",log.getName());
	}
	
	/** To update car details to the H2 DB, Use Postman and send PUT request with a modified JSON modified object*/
	@PutMapping("/cars/{carName}")
	public void updateCarDetails(@PathVariable String carName, @RequestBody Car car){
		log.info("Start {} Class, updateCarDetails method.",log.getName());
		carDetailsRepository.save(car);
		log.info("End {} Class, updateCarDetails method.",log.getName());
	}
	
}

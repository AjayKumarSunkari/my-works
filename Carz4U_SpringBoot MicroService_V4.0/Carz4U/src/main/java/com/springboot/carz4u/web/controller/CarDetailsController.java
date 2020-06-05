package com.springboot.carz4u.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.carz4u.web.exception.RecordNotFoundException;
import com.springboot.carz4u.web.model.Car;
import com.springboot.carz4u.web.repository.CarDetailsRepository;


@Controller
public class CarDetailsController {

	@Autowired
	CarDetailsRepository carDetailsRepository;
	
	private static final Logger log = LoggerFactory.
			getLogger(CarDetailsController.class);
	
	/** Hit this URL http://localhost:8080/ to access below Car web application index page*/
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/carList")
	public String carsList(Model model) {
		log.info("Start {} Class, carsList method.",log.getName());
		/**Here we are calling getAllCars() which has Hystrix Fallback method 
		 * getFallbackAllCars(). This Hystrix works based on Proxy class 
		 * i.e. it will create Proxy class for RemoteCarsRepository and then calls
		 * getAllCars() of Proxy class. Based on the response from getAllCars() it 
		 * will choose betweeen getAllCars() and getFallbackAllCars(). So you need to
		 * have the restTemplate.getForObject() call in a seperate class like 
		 * RemoteCarsRepository. */
		model.addAttribute("cars", carDetailsRepository.getAllCars());
		log.info("End {} Class, carsList method.",log.getName());
		return "carList";
	}
	
	@RequestMapping("/carDetails")
	public String carDetails(@RequestParam("carName") String carName, Model model) {
		log.info("Start {} Class, carDetails method.",log.getName());
		try{
			Car carRendered = carDetailsRepository.getCar(carName);
			if(carRendered == null){
				throw new RecordNotFoundException(); /**This exception is caught by ApplicationExceptionController class 
				*using Global Exception handling using @ControllerAdvice and @ExceptionHandler */
			}else{
				log.info("if carRendered: {}, carName is {} ", carRendered, carRendered.getCarName());
				model.addAttribute("car", carDetailsRepository.getCar(carName));
			}
			model.addAttribute("rtoBean", carDetailsRepository.getRtoCharge(carName));
			log.info("End {} Class, carDetails method.",log.getName());
		}catch(RecordNotFoundException rnfe){
			log.error("RecordNotFoundException");
			throw new RecordNotFoundException();
		}
		return "carDetails";
	}
	
}

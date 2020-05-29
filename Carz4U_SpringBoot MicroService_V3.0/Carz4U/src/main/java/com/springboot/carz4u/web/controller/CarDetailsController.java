package com.springboot.carz4u.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.carz4u.web.exception.RecordNotFoundException;
import com.springboot.carz4u.web.jwt.util.JwtUtil;
import com.springboot.carz4u.web.model.Car;
import com.springboot.carz4u.web.repository.CarDetailsRepository;


@Controller
public class CarDetailsController {

	@Autowired
	CarDetailsRepository carDetailsRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	private static final Logger log = LoggerFactory.
			getLogger(CarDetailsController.class);
	
	/** Hit this URL http://localhost:8080/login to access 
	 * below Car web application login page*/
	@RequestMapping("/login")
	public String login(){
		log.info("Inside {} Class, login method.",log.getName());
		return "loginPage";
	}
		
	@RequestMapping(value="/welcome", method = RequestMethod.POST)
	public String home(@RequestHeader("Authorization") String jwtToken, HttpServletRequest request){
		log.info("Inside {} class, home method, jwtToken is {}",log.getName(),jwtToken);
		log.info("Inside {} class, home method, request.getHeader(Authorization): {}", log.getName(), request.getHeader("Authorization"));
		return "index";
	}
	
	@RequestMapping("/carList")
	public String carsList(Model model) {
		log.info("Start {} Class, carsList method.",log.getName());
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

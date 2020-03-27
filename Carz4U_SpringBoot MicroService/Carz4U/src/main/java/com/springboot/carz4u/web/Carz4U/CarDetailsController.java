package com.springboot.carz4u.web.Carz4U;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarDetailsController {

	@Autowired
	CarDetailsRepository carDetailsRepository;
	
	/** Hit this URL http://localhost:8080/ to access below Car web application index page*/
	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/carList")
	public String carsList(Model model) {
		model.addAttribute("cars", carDetailsRepository.getAllCars());
		return "carList";
	}
	
	@RequestMapping("/carDetails")
	public String carDetails(@RequestParam("carName") String carName, Model model) {
		model.addAttribute("car", carDetailsRepository.getCar(carName));
		model.addAttribute("rtoBean", carDetailsRepository.getRtoCharge(carName));
		return "carDetails";
	}
}

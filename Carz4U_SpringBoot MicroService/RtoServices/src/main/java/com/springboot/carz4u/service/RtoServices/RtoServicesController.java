package com.springboot.carz4u.service.RtoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RtoServicesController {
	
	@Autowired
	RtoServicesRepository rtoServicesRepository;
	
	@GetMapping("/rtoCharges/{carName}")
	public RtoCharges findByCarName(@PathVariable String carName){
		
		return rtoServicesRepository.findByCarName(carName);
	}

}

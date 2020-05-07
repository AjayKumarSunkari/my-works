package com.springboot.carz4u.web.Carz4U;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** This class Customizes default Whitelabel Error page("/error") in Spring boot. 
 * For this We have to extend ErrorController class and override getErrorPath() method.*/

@Controller
public class AppErrorController implements ErrorController {

	Logger log = LoggerFactory.getLogger(AppErrorController.class);
	
	@RequestMapping("/error")
	 public String handleError(Exception ex, Model model) {
		log.error("{} class, Exception is server error", log.getName());
		log.error("Exception ex.getLocalizedMessage() is ", ex.getLocalizedMessage());
		List<String> details = new ArrayList<>();
	    details.add(ex.getLocalizedMessage());
		ErrorResponseBean errorBean = new ErrorResponseBean("Internal server error!", details);
	    model.addAttribute("errorBean", errorBean);
	    log.info("errorBean.getErrorMsg() {}, errorBean.getErrorDetails() {}",errorBean.getErrorMsg(),errorBean.getErrorDetails());
		
		return "error";
	}
	 
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}

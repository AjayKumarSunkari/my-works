package com.springboot.carz4u.web.Carz4U;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** This class implements Global Exception Handling in Application,
 *  using annotation based implementation @ControllerAdvice and @ExceptionHandler */

@ControllerAdvice
 public class ApplicationExceptionController extends ResponseEntityExceptionHandler {
	
	Logger log = LoggerFactory.getLogger(ApplicationExceptionController.class);
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RecordNotFoundException.class)
	public final ModelAndView handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request, Model model) {
		log.error("Exception is RecordNotFoundException");
	    //List<String> details = new ArrayList<>();
	    //details.add(ex.getLocalizedMessage());
		//List listString = Arrays.asList(error);
		ErrorResponseBean errorBean = new ErrorResponseBean("Resource Not Found", new String[] {"Record Not found/available", ""});
	    model.addAttribute("errorBean", errorBean);
	    log.info("errorBean.getErrorMsg() {}, errorBean.getErrorDetails() {}",errorBean.getErrorMsg(),errorBean.getErrorDetails());
	    return new ModelAndView("error");
	}
		
}

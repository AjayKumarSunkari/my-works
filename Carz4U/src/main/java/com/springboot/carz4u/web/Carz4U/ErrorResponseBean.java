package com.springboot.carz4u.web.Carz4U;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseBean {
	
	private String errorMsg = "";
	private String [] errorDetails;
	
	public ErrorResponseBean(String errorMsg, String [] errorDetails) {
		this.errorMsg = errorMsg;
		this.errorDetails = errorDetails;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String [] getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String [] errorDetails) {
		this.errorDetails = errorDetails;
	}
	

}

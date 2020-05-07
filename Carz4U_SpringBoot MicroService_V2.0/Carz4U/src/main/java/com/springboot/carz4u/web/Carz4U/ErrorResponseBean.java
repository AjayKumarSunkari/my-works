package com.springboot.carz4u.web.Carz4U;

import java.util.List;

public class ErrorResponseBean {
	
	private String errorMsg = "";
	private List<String> errorDetails;
	
	public ErrorResponseBean(String errorMsg, List<String> errorDetails) {
		this.errorMsg = errorMsg;
		this.errorDetails = errorDetails;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<String> getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(List<String> errorDetails) {
		this.errorDetails = errorDetails;
	}
	

}

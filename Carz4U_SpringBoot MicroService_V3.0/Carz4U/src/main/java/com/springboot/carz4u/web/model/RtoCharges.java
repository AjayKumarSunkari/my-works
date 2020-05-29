package com.springboot.carz4u.web.model;


public class RtoCharges {
	
	private int id;
	
	
	private String carName;
	
	
	private int rtoCharge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getRtoCharge() {
		return rtoCharge;
	}

	public void setRtoCharge(int rtoCharge) {
		this.rtoCharge = rtoCharge;
	}

}

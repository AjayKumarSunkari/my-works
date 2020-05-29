package com.springboot.carz4u.cardetails.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car{
	
	@Id
	private Long id;
	
	@Column(name="car_name")
	private String carName;
	
	@Column(name="car_brand")
	private String carBrand;
	
	@Column(name="car_type")
	private String carType;
	
	@Column(name="car_color")
	private String carColor;
	
	@Column(name="car_price")
	private int carPrice;
	
	@Column(name="car_image")
	private String carImage;
	
	
	private int port;
	
	public Car(){
		
	}
	
	public Car(Long id, String carName, String carBrand, String carType, String carColor, int carPrice, String carImage) {
	    super();
	    this.id = id;
	    this.carName = carName;
	    this.carBrand = carBrand;
	    this.carType = carType;
	    this.carColor = carColor;
	    this.carPrice = carPrice;
	    this.carImage = carImage;
	}

	public Long getId() {
		return id;
	}

	public String getCarName() {
		return carName;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public String getCarType() {
		return carType;
	}

	public String getCarColor() {
		return carColor;
	}

	public int getCarPrice() {
		return carPrice;
	}
	
	public int getPort() {
		return port;
	}

	public String getCarImage() {
		return carImage;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
		
}

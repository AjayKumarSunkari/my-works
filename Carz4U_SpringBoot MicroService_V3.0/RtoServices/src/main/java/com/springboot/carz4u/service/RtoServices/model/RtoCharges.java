package com.springboot.carz4u.service.RtoServices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rto_charges")
public class RtoCharges {
	
	@Id
	private int id;
	
	@Column
	private String carName;
	
	@Column
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

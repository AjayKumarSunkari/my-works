package com.springboot.carz4u.web.Carz4U;

import java.io.Serializable;

public class Car implements Serializable {

		private static final long serialVersionUID = 1L;
		private String carName;
		private String carBrand;
		private String carType;
		private String carColor;
		private int carPrice;
		private String carImage;
		
		public String getCarName() {
			return carName;
		}
		public void setCarName(String carName) {
			this.carName = carName;
		}
		public String getCarBrand() {
			return carBrand;
		}
		public void setCarBrand(String carBrand) {
			this.carBrand = carBrand;
		}
		public String getCarType() {
			return carType;
		}
		public void setCarType(String carType) {
			this.carType = carType;
		}
		public String getCarColor() {
			return carColor;
		}
		public void setCarColor(String carColor) {
			this.carColor = carColor;
		}
		public int getCarPrice() {
			return carPrice;
		}
		public void setCarPrice(int carPrice) {
			this.carPrice = carPrice;
		}
		public String getCarImage() {
			return carImage;
		}
		
}

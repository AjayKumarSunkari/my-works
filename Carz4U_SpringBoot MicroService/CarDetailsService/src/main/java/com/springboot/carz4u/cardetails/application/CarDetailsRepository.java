package com.springboot.carz4u.cardetails.application;

import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailsRepository extends JpaRepository<Car, Long>{

	/** This method is part of org.springframework.data.repository.CrudRepository Interface and the method is Optional<T> findById(ID id);*/
	Car findByCarName(String carName);
	
	/** This method is from org.springframework.data.jpa.repository.JpaRepository Interface and the method is List<T> findAll(); */
	List<Car> findAll();
	
	Car save(Car car);
	
	void deleteByCarName(String carName);
	
	
}

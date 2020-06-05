package com.springboot.carz4u.service.RtoServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.carz4u.service.RtoServices.model.RtoCharges;

@Repository
public interface RtoServicesRepository extends JpaRepository<RtoCharges, Integer> {

	RtoCharges findByCarName(String carName);
	

}

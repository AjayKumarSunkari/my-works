package com.springboot.carz4u.service.RtoServices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RtoServicesRepository extends JpaRepository<RtoCharges, Integer> {

	RtoCharges findByCarName(String carName);
	

}

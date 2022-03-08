package com.nrifintech.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.Bus;

@Repository
public interface BusAndRouteRepository extends AbstractBaseRepository<Bus, String> {
	
	@Query("SELECT b FROM Bus b where b.route.startName=?1 and b.route.stopName=?2 and b.activeStatus='YES'")
	public List<Bus> findAllWithSourceDest(String source, String destination);
	
	Bus findByRegistrationNo(String registrationNo);
	
	
	
	
}

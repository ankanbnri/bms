package com.nrifintech.bms.repository;

import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.Bus;

@Repository
public interface BusAndRouteRepository extends AbstractBaseRepository<Bus, String> {
}

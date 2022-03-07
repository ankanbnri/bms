package com.nrifintech.bms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;

@Repository
public interface TicketRepository extends AbstractBaseRepository<Ticket, String> {
	
	//@Query("SELECT t FROM Ticket t where t.registrationNo=?1 and t.dateBought=?2")
	//public List<Ticket> findByRegNoAndDate(String regNo, String date);
	
	@Query("SELECT t FROM Ticket t where t.bus=?1 and t.dateOfTravel=?2")
	public List<Ticket> findAllTicketsByBusAndDateBought(Bus bus, Date date);
	
	
	//@Query("SELECT t FROM Ticket t where t.bus.registrationNo=?1 and t.dateBought='DATEADD(D,1,GETDATE())'")
}

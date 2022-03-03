package com.nrifintech.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.entity.User;

@Repository
public interface TicketRepository extends AbstractBaseRepository<Ticket, String> {
//	@Query("SELECT t FROM Ticket t where t.user=?1")
//	List<Ticket> findAllTicketsWithUser(User user);
	
	@Query("SELECT coalesce(sum(t.seatsBooked),0) FROM Ticket t where t.bus=?1")
	int getTotalSeatsByBus(Bus bus);
	
	@Query("SELECT t FROM Ticket t where t.user=?1 and t.dateOfTravel >= CURRENT_DATE() ORDER BY t.dateOfTravel")
	List<Ticket> findAllUpcomingTicketsWithUser(User user);
	
	@Query("SELECT t FROM Ticket t where t.user=?1 and t.dateOfTravel < CURRENT_DATE() ORDER BY t.dateOfTravel DESC")
	List<Ticket> findAllOldTicketsWithUser(User user);
}

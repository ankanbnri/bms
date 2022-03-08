package com.nrifintech.bms.repository;

// import java.util.Date;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.entity.User;

@Repository
public interface TicketRepository extends AbstractBaseRepository<Ticket, String> {
	
	//@Query("SELECT t FROM Ticket t where t.registrationNo=?1 and t.dateBought=?2")
	//public List<Ticket> findByRegNoAndDate(String regNo, String date);
	
	@Query("SELECT t FROM Ticket t where t.bus=?1 and t.dateOfTravel=?2")
	public List<Ticket> findAllTicketsByBusAndDateBought(Bus bus, java.util.Date date);
	
	
	//@Query("SELECT t FROM Ticket t where t.bus.registrationNo=?1 and t.dateBought='DATEADD(D,1,GETDATE())'")
//	@Query("SELECT t FROM Ticket t where t.user=?1")
//	List<Ticket> findAllTicketsWithUser(User user);
	
	@Query("SELECT coalesce(sum(t.seatsBooked),0) FROM Ticket t where t.bus=?1 and t.dateOfTravel=?2")
	int getTotalSeatsByBusAndDate(Bus bus, Date dateOfTravel);
	
	@Query("SELECT t FROM Ticket t where t.user=?1 and t.dateOfTravel >= CURRENT_DATE() ORDER BY t.dateOfTravel")
	List<Ticket> findAllUpcomingTicketsWithUser(User user);
	
	@Query("SELECT t FROM Ticket t where t.user=?1 and t.dateOfTravel < CURRENT_DATE() ORDER BY t.dateOfTravel DESC")
	List<Ticket> findAllOldTicketsWithUser(User user);
	
//	@Query("select r.routecode, sum(t.total_amount) revenue "
//			+ "from ticket t inner join t.bus b inner join b.route r "
//			+ "group by r.routecode")
//	List<Revenue> getRevenue();

	@Query(value = "select routecode, startname, stopname, sum(total_amount) from ticket join bus using(registration_no) join route using(routecode) where DATEDIFF(curdate(),date_bought) < 31 group by routecode order by sum(total_amount) desc", nativeQuery = true)
	List<Object[]> getRevenue();
}

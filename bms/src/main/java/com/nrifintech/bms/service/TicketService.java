package com.nrifintech.bms.service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	UserService userService;

	public String generatePnrNo(Integer userId) {
		User user = userService.getById(userId);
		String email = user.getEmail();
		
		//Getting the suffix for original string
		int idx = email.indexOf("@"); 
		String suffix=null;
		if (idx != -1) 
		{
			suffix= email.substring(0 , idx); 
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		StringBuilder str = new StringBuilder(timestamp.toString());
		str.append(suffix);
		
		String sha256hex = Hashing.sha256()
				  .hashString(str, StandardCharsets.UTF_8)
				  .toString().substring(0, 10);
		System.out.println("From generate pnr........");
		System.out.println(sha256hex);
		return sha256hex.toUpperCase();
	}

	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

//	public List<Ticket> getTicketsWithUser(User user) {
//		return ticketRepository.findAllTicketsWithUser(user); 
//	}

	public int getTotalSeatsByBusAndDate(Bus bus, String travelDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(travelDate);
        java.sql.Date dateOfTravel = new java.sql.Date(parsed.getTime());
		return ticketRepository.getTotalSeatsByBusAndDate(bus,dateOfTravel);
	}

	public List<Ticket> getUpcomingTicketsWithUser(User user) {
		return ticketRepository.findAllUpcomingTicketsWithUser(user);
	}

	public List<Ticket> getOldTicketsWithUser(User user) {
		return ticketRepository.findAllOldTicketsWithUser(user);
	}
	
	
}

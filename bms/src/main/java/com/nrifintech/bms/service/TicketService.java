package com.nrifintech.bms.service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.nrifintech.bms.entity.Bus;
import com.nrifintech.bms.entity.Ticket;
import com.nrifintech.bms.entity.User;
import com.nrifintech.bms.model.Revenue;
import com.nrifintech.bms.model.RouteInfo;
import com.nrifintech.bms.model.RouteRevenue;
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

		// Getting the suffix for original string
		int idx = email.indexOf("@");
		String suffix = null;
		if (idx != -1) {
			suffix = email.substring(0, idx);
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		StringBuilder str = new StringBuilder(timestamp.toString());
		str.append(suffix);

		String sha256hex = Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString().substring(0, 10);
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
		return ticketRepository.getTotalSeatsByBusAndDate(bus, dateOfTravel);
	}

	public List<Ticket> getUpcomingTicketsWithUser(User user) {
		return ticketRepository.findAllUpcomingTicketsWithUser(user);
	}

	public List<Ticket> getOldTicketsWithUser(User user) {
		return ticketRepository.findAllOldTicketsWithUser(user);
	}

	public List<Ticket> findAllTicketsByBusAndDateBought(Bus bus, Date date) {
		return ticketRepository.findAllTicketsByBusAndDateBought(bus, date);
	}

	public void deleteByID(String pnrNo) {
		ticketRepository.deleteById(pnrNo);
	}

	public long countTickets() {
		return ticketRepository.count();
	}

	public List<Revenue> getRevenue() {
		List<Revenue> list = new ArrayList<>();
		List<Object[]> revenueDetails = ticketRepository.getRevenue();
		revenueDetails.forEach(revRow -> {
			Revenue revenue = new Revenue(revRow[0] + "", revRow[1] + "", revRow[2] + "", revRow[3] + "");
//			revenue.setRoutecode(revRow[0]+"");
//			revenue.setSource(revRow[1]+"");
//			revenue.setDestination(revRow[2]+"");
//			revenue.setTotalrevenue(revRow[3]+"");
			list.add(revenue);
		});
		return list;
	}

	public List<RouteRevenue> getAllRouteRevenue() {
		List<RouteRevenue> list = new ArrayList<>();
		List<Object[]> revenueDetails = ticketRepository.getAllRoutesRevenue();
		revenueDetails.forEach(revRow -> {
			RouteRevenue revenue = new RouteRevenue();
			revenue.setRoutecode(revRow[0] + "");
			revenue.setSource(revRow[1] + "");
			revenue.setDestination(revRow[2] + "");
			revenue.setDistance(revRow[3] + "");
			revenue.setTotalrevenue(revRow[4] + "");
			list.add(revenue);
		});
		return list;
	}
	
	public List<RouteInfo> getBusCountPerRoute() {
		List<RouteInfo> list = new ArrayList<>();
		List<Object[]> routeDetails = ticketRepository.getBusCountPerRoute();
		routeDetails.forEach(revRow -> {
			RouteInfo info = new RouteInfo();
			info.setRoutecode(revRow[0] + "");
			info.setSource(revRow[1] + "");
			info.setDestination(revRow[2] + "");
			info.setDistance(revRow[3] + "");
			info.setBuscount(revRow[4] + "");
			list.add(info);
		});
		return list;
	}

	public Ticket getById(String pnrNo) {
		return ticketRepository.getById(pnrNo);
	}
}

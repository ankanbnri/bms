package com.nrifintech.bms.util;

public class TicketEmailTemplate {

	private String name;
	private String pnrNo;
	private String dateBought;
	private String dateofTravel;
	private String registrationNo;
	private String busName;
	private String facilities;
	private String startTime;
	private String startName;
	private String stopName;
	private int seatsBooked;
	private int totalPaid;

	public TicketEmailTemplate(String name, String pnrNo, String dateBought, String dateofTravel, String registrationNo,
			String busName, String facilities, String startTime, String startName, String stopName, int seatsBooked,
			int totalPaid) {
		super();
		this.name = name;
		this.pnrNo = pnrNo;
		this.dateBought = dateBought;
		this.dateofTravel = dateofTravel;
		this.registrationNo = registrationNo;
		this.busName = busName;
		this.facilities = facilities;
		this.startTime = startTime;
		this.startName = startName;
		this.stopName = stopName;
		this.seatsBooked = seatsBooked;
		this.totalPaid = totalPaid;
	}

	@Override
	public String toString() {
		return "Dear " + name + ",\n" + "Thank you For Using BMS Application For Ticket Booking.Your Booking Details are as follows :" + "\n" + "PNRNO : " + pnrNo + "\n" + "Date of Booking : " + dateBought
				+ "\n" + " Date Of Travel : " + dateofTravel + "\n" + "Bus Registration No : " + registrationNo + "\n"
				+ " Bus Name : " + busName + "\n" + " Facilities : " + facilities + "\n" + " Onboarding Time : "
				+ startTime + "\n" + " Onboarding Point : " + startName + "\n" + " Departure Point : " + stopName + "\n"
				+ " No Of Seats Booked : " + seatsBooked + "\n" + " Total Paid : " + totalPaid + "\n";
	}

}

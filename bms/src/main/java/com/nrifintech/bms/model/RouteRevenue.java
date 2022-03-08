package com.nrifintech.bms.model;


public class RouteRevenue {
	private String routecode;
	private String source;
	private String destination;
	private String distance;
	private String totalrevenue;
	
	
	public RouteRevenue(String routecode, String source, String destination, String distance, String totalrevenue) {
		super();
		this.routecode = routecode;
		this.source = source;
		this.destination = destination;
		this.setDistance(distance);
		this.totalrevenue = totalrevenue;
	}
	
	public RouteRevenue() {
	}

	public String getRoutecode() {
		return routecode;
	}
	public void setRoutecode(String routecode) {
		this.routecode = routecode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTotalrevenue() {
		return totalrevenue;
	}
	public void setTotalrevenue(String totalrevenue) {
		this.totalrevenue = totalrevenue;
	}
	@Override
	public String toString() {
		return "Revenue [routecode=" + routecode + ", source=" + source + ", destination=" + destination
				+ ", totalrevenue=" + totalrevenue + "]";
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
}

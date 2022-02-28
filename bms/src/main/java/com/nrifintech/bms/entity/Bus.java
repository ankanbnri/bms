package com.nrifintech.bms.entity;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="bus")
public class Bus extends AbstractBaseEntity {
	private static final long serialVersionUID = 6715094082833854125L;

	@Id @Column(name="registration_no")
    private String registrationNo;
	
	@Column(name="busname")
	private String busName;
	
	@Column(name="fare_per_km")
	private int fare;
	
	@Column(name="totalseats")
	private int seatCount;
	
	@Column(name="phone_no")
	private String mobileNo;
	
	@Column(name="starttime")
	private Time startTime;

	@Column(name="facilities") @Enumerated(EnumType.STRING)
	private String facilities;
	
	@Column(name="isactive") @Enumerated(EnumType.STRING)
	private boolean activeStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	private Route route;

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	
	
}

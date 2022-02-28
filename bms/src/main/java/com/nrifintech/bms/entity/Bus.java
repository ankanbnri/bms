package com.nrifintech.bms.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	
}

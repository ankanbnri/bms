package com.nrifintech.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="route")
public class Route extends AbstractBaseEntity {
	private static final long serialVersionUID = 6715094082833854125L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="routeid")
    private Long routeid;
	
	@Column(name="startname")
	private String startName;
	
	@Column(name="stopname")
	private String stopName;
	
	@Column(name="distance")
	private int distance;

	public Long getRouteid() {
		return routeid;
	}

	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	public String getStartname() {
		return startname;
	}

	public void setStartname(String startname) {
		this.startname = startname;
	}

	public String getStopname() {
		return stopname;
	}

	public void setStopname(String stopname) {
		this.stopname = stopname;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	
}
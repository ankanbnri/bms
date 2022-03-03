package com.nrifintech.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nrifintech.bms.util.AdminPrivileges;


@Entity
@Table(name="user")
public class User extends AbstractBaseEntity {
	private static final long serialVersionUID = 6715094082833854125L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="userid")
    private Integer userid;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="phone_no")
	private String mobileNo;

	@Column(name="isadmin")
	private AdminPrivileges adminPrivileges;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public AdminPrivileges getAdminPrivileges() {
		return adminPrivileges;
	}

	public void setAdminPrivileges(AdminPrivileges adminPrivileges) {
		this.adminPrivileges = adminPrivileges;
	}


	
}

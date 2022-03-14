package com.nrifintech.bms.dto;

import com.nrifintech.bms.util.AdminPrivileges;

public class UserRequest {
	private Long userid;

	private String name;

	private String password;

	private String email;

	private String mobileNo;

	private AdminPrivileges adminPrivileges = AdminPrivileges.NO;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
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

package com.invitation.biz.member.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMemberVO {

	private String id;
	private String password;
	private String name;
	private String phone;
	private Date datetime_register;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegDate() {
		SimpleDateFormat simpleRegDate = new SimpleDateFormat("yyyy-MM-dd");
		return simpleRegDate.format(datetime_register);
	}
	public void setRegDate(Date datetime_register) {
		this.datetime_register = datetime_register;
	}
	@Override
	public String toString() {
		return "UserMemberVO [id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", datetime_register=" + datetime_register + "]";
	}
}

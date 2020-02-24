package com.invitation.biz.member.user;

public class UserMemberListVO {

	private String id;
	private String name;
	private String phone;
	private String statusSee;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getStatusSee() {
		return statusSee;
	}
	public void setStatusSee(String statusSee) {
		this.statusSee = statusSee;
	}
	
	@Override
	public String toString() {
		return "UserMemberListVO [id=" + id + ", name=" + name + ", phone=" + phone + ", statusSee=" + statusSee + "]";
	}
}

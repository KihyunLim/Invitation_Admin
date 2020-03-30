package com.invitation.biz.member.user;

public class UserMemberInfoVO {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String registerDate;
	private String latestInvitationBegin;
	private String latestInvitationEnd;
	
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
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getLatestInvitationBegin() {
		return latestInvitationBegin;
	}
	public void setLatestInvitationBegin(String latestInvitationBegin) {
		this.latestInvitationBegin = latestInvitationBegin;
	}
	public String getLatestInvitationEnd() {
		return latestInvitationEnd;
	}
	public void setLatestInvitationEnd(String latestInvitationEnd) {
		this.latestInvitationEnd = latestInvitationEnd;
	}
	
	@Override
	public String toString() {
		return "UserMemberInfoVO [id=" + id + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", registerDate=" + registerDate + ", latestInvitationBegin=" + latestInvitationBegin
				+ ", latestInvitationEnd=" + latestInvitationEnd + "]";
	}
}

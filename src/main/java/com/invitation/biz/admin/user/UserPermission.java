package com.invitation.biz.admin.user;

public class UserPermission {

	private String id;
	private String name;
	
	public UserPermission(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}

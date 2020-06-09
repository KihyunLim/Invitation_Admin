package com.invitation.biz.admin.user;

public interface UserAdminService {

	UserAdminVO getUserInfo(String id);
	
	Boolean getUserInfo2(UserAdminVO user);
}
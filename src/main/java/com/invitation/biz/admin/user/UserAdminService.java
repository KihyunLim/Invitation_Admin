package com.invitation.biz.admin.user;

public interface UserAdminService {

	Boolean getUserInfo(UserAdminVO user);
	
	Boolean doSecurityLogin(UserAdminVO user);
}
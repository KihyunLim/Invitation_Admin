package com.invitation.biz.admin.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.admin.user.UserAdminService;
import com.invitation.biz.admin.user.UserAdminVO;

@Service("userAdmin")
public class UserAdminServiceImpl implements UserAdminService {

	@Autowired
	private UserAdminDAOMybatis userAdminDAO;
	
	@Override
	public UserAdminVO getUserInfo(String id) {
		return userAdminDAO.getUserInfo(id);
	}
}

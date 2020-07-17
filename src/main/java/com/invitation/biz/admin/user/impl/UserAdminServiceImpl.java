package com.invitation.biz.admin.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.invitation.biz.admin.user.UserAdminService;
import com.invitation.biz.admin.user.UserAdminVO;
import com.invitation.controller.login.LoginController;

@Service("userAdmin")
public class UserAdminServiceImpl implements UserAdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserAdminDAOMybatis userAdminDAO;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public Boolean getUserInfo(UserAdminVO user) {
		UserAdminVO userInfo = userAdminDAO.getUserInfo(user.getId());
		
		if(userInfo == null) {
			throw new NullPointerException("User information not fuond");
		}
		
		if(userInfo.getPassword().equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean doSecurityLogin(UserAdminVO user) {
		Authentication auth = authenticationManager.authenticate(createAuthToken(user.getId(), user.getPassword()));
		
		LOGGER.debug(auth.toString());
		
		return true;
	}
	
	private UsernamePasswordAuthenticationToken createAuthToken(String principal, String credentials) {
		return new UsernamePasswordAuthenticationToken(principal, credentials);
	}
}

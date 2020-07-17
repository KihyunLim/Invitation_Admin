package com.invitation.controller.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.invitation.biz.admin.user.UserAdminVO;
import com.invitation.biz.admin.user.UserPermission;
import com.invitation.biz.admin.user.impl.UserAdminDAOMybatis;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserAdminDAOMybatis userAdminDAO;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		
		LOGGER.info("CustomAuthenticationProvider!!");
		
		UserAdminVO userInfo = findUserById(authToken.getName());
		if(userInfo == null) {
			throw new UsernameNotFoundException(authToken.getName());
		}
		
		if(!matchPassword(userInfo.getPassword(), authToken.getCredentials())) {
			throw new BadCredentialsException("not matching username or password");
		}
		
		List<GrantedAuthority> authorities = getAuthorities(userInfo.getId());
		return new UsernamePasswordAuthenticationToken(new UserAdminVO(userInfo.getId(), null), null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	private UserAdminVO findUserById(String id) {
		return userAdminDAO.getUserInfo(id);
	}
	
	private boolean matchPassword(String password, Object credentials) {
		return password.equals(credentials);
	}
	
	private List<GrantedAuthority> getAuthorities(String id) {
		List<UserPermission> perms = userAdminDAO.getUserPermission(id);
		if(perms == null) {
			return Collections.emptyList();
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
		for(UserPermission perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm.getName()));
		}
		
		return authorities;
	}
}

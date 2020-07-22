package com.invitation.biz.admin.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.admin.user.UserAdminVO;
import com.invitation.biz.admin.user.UserPermission;

@Repository
public class UserAdminDAOMybatis {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminDAOMybatis.class);

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserAdminVO getUserInfo(String id) {
		return mybatis.selectOne("AdminUserDAO.getUserInfo", id);
	}
	
	public List<UserPermission> getUserPermission(String id) {
		return mybatis.selectList("AdminUserDAO.getUserPermission", id);
	}
}

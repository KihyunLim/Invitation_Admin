package com.invitation.biz.admin.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.admin.user.UserAdminVO;

@Repository
public class UserAdminDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserAdminVO getUserInfo(String id) {
		return mybatis.selectOne("AdminUserDAO.getUserInfo", id);
	}
}

package com.invitation.biz.member.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.member.user.UserMemberListVO;

@Repository
public class UserMemberDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<UserMemberListVO> getMemberList() {
		return mybatis.selectList("MemberUserDAO.getMemberList");
	}
}

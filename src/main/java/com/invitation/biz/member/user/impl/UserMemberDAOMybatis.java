package com.invitation.biz.member.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberVO;

@Repository
public class UserMemberDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<UserMemberVO> getUserList() {
		return mybatis.selectList("MemberUserDAO.getUserList");
	}
	
	public List<UserMemberListVO> getMemberList() {
		return mybatis.selectList("MemberUserDAO.getMemberList");
	}
}

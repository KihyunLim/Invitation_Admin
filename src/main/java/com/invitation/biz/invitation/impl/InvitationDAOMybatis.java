package com.invitation.biz.invitation.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;

@Repository
public class InvitationDAOMybatis {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationDAOMybatis.class);

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public MemberInfoVO getMemberInfo(String id) {
		return mybatis.selectOne("InvitationDAO.getMemberInfo", id);
	}
	
	public int getLastInsertID() {
		return mybatis.selectOne("InvitationDAO.getLastInsertID");
	}
	
	public void registerInvitaiton(SyntheticInvitationVO syntheticInvitationVO) {
		mybatis.insert("InvitationDAO.registerInvitation", syntheticInvitationVO);
	}
	
	public void insertMainInfo(SyntheticInvitationVO syntheticInvitationVO) {
		mybatis.insert("InvitationDAO.insertMainInfo", syntheticInvitationVO);
	}
	
	public void insertLoveStory(SyntheticInvitationVO syntheticInvitationVO) {
		mybatis.insert("InvitationDAO.insertLoveStory", syntheticInvitationVO);
	}
}
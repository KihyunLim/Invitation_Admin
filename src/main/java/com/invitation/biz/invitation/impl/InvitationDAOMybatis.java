package com.invitation.biz.invitation.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;

@Repository
public class InvitationDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public MemberInfoVO getMemberInfo(String id) {
		return mybatis.selectOne("InvitationDAO.getMemberInfo", id);
	}
	
	public int getLastInsertID() {
		return mybatis.selectOne("InvitationDAO.getLastInsertID");
	}
	
	public int registerInvitaiton(SyntheticInvitationVO syntheticInvitationVO) {
		return mybatis.insert("InvitationDAO.registerInvitation", syntheticInvitationVO);
	}
}
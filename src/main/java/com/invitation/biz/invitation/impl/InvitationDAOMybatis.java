package com.invitation.biz.invitation.impl;

import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.invitation.InvitationVO;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MainInfoVO;
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
	
	public void insertWhenWhere(SyntheticInvitationVO syntheticInvitationVO) {
		mybatis.insert("InvitationDAO.insertWhenWhere", syntheticInvitationVO);
	}
	
	public void insertGallery(SyntheticInvitationVO syntheticInvitationVO) {
		mybatis.insert("InvitationDAO.insertGallery", syntheticInvitationVO);
	}
	
	public List<MainInfoVO> getMemberInvitation(String id) {
		return mybatis.selectList("InvitationDAO.getMemberInvitation", id);
	}
	
	public SyntheticInvitationVO getSyntheticInvitation(String invSeq) {
		SyntheticInvitationVO resultSyntheticInvitation = new SyntheticInvitationVO();
		Iterator<Object> item = (mybatis.selectList("invitationDAO.selectLoveStory", invSeq)).iterator();
		
		resultSyntheticInvitation.setInvitationVO(mybatis.selectOne("InvitationDAO.selectInvitation", invSeq));
		resultSyntheticInvitation.setMainInfoVO(mybatis.selectOne("InvitationDAO.selectMainInfo", invSeq));
//		resultSyntheticInvitation.setLoveStoryVO(mybatis.selectList("InvitationDAO.selectLoveStory", invSeq));
		
		return null;
	}
}
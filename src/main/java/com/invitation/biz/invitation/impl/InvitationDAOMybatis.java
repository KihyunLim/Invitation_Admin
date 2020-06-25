package com.invitation.biz.invitation.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.invitation.GalleryVO;
import com.invitation.biz.invitation.InvitationVO;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MainInfoVO;
import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SweetMessageVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;
import com.invitation.biz.invitation.WhenWhereVO;

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
	
	public void insertLoveStoryItem(LoveStoryVO loveStoryVO) {
		mybatis.insert("InvitationDAO.insertLoveStoryItem", loveStoryVO);
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
	
	public List<SweetMessageVO> getSweetMessageList(Criteria cri, String invSeq) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("invSeq", invSeq);
		param.put("pageStart",  cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		
		return mybatis.selectList("InvitationDAO.selectSweetMessage", param);
	}
	
	public int getSweetMessageCount(String invSeq) {
		return mybatis.selectOne("InvitationDAO.getSweetMessageCount", invSeq);
	}
	
	public SyntheticInvitationVO getSyntheticInvitation(String invSeq) {
		SyntheticInvitationVO resultSyntheticInvitation = new SyntheticInvitationVO();
		List<LoveStoryVO> listLS = new ArrayList<>();
		List<WhenWhereVO> listWW = new ArrayList<>();
		List<GalleryVO> listG = new ArrayList<>();
		List<SweetMessageVO> listSM = new ArrayList<>();
		Criteria cri = new Criteria();
		
		resultSyntheticInvitation.setInvitationVO(mybatis.selectOne("InvitationDAO.selectInvitation", invSeq));
		resultSyntheticInvitation.setMainInfoVO(mybatis.selectOne("InvitationDAO.selectMainInfo", invSeq));
		listLS = mybatis.selectList("InvitationDAO.selectLoveStory", invSeq);
		resultSyntheticInvitation.setLoveStoryVO((ArrayList<LoveStoryVO>) listLS);
		listWW = mybatis.selectList("InvitationDAO.selectWhenWhere", invSeq);
		resultSyntheticInvitation.setWhenWhereVO((ArrayList<WhenWhereVO>) listWW);
		listG = mybatis.selectList("InvitationDAO.selectGallery", invSeq);
		resultSyntheticInvitation.setGalleryVO((ArrayList<GalleryVO>) listG);
		listSM = getSweetMessageList(cri, invSeq);
		resultSyntheticInvitation.setSweetMessageVO((ArrayList<SweetMessageVO>) listSM);
		
		return resultSyntheticInvitation;
	}
	
	public String getFormCode(int invSeq) {
		return mybatis.selectOne("InvitationDAO.getFormCode", invSeq);
	}
	
	public InvitationVO modifyInvitationUseFlag(int invSeq, String useCategory, String useFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("invSeq", invSeq);
		param.put("useCategory", useCategory);
		param.put("useFlag", useFlag);
		
		mybatis.update("InvitationDAO.updateInvitationUseFlag", param);
		
		return mybatis.selectOne("InvitationDAO.selectInvitation", invSeq);
	}
	
	public InvitationVO modifyInvitation(InvitationVO invitationVO) {
		mybatis.update("InvitationDAO.updateInvitation", invitationVO);
		
		return mybatis.selectOne("InvitationDAO.selectInvitation", invitationVO.getSeq());
	}
	
	public InvitationVO modifyMainInfo(MainInfoVO mainInfoVO) {
		mybatis.update("InvitationDAO.updateMainInfo", mainInfoVO);
		
		return mybatis.selectOne("InvitationDAO.selectInvitation", mainInfoVO.getInvSeq());
	}
	
	public void modifyLoveStory(LoveStoryVO loveStoryVO) {
		mybatis.update("InvitationDAO.updateLoveStory", loveStoryVO);
	}
}
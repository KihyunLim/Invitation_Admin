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
import com.invitation.biz.invitation.InvitationList;
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
	
	public void insertWhenWhereItem(WhenWhereVO whenWhereVO) {
		mybatis.insert("InvitationDAO.insertWhenWhereItem", whenWhereVO);
	}
	
	public void insertGallery(SyntheticInvitationVO syntheticInvitationVO) {
		mybatis.insert("InvitationDAO.insertGallery", syntheticInvitationVO);
	}
	
	public void insertGalleryItem(GalleryVO galleryVO) {
		mybatis.insert("InvitationDAO.insertGalleryItem", galleryVO);
	}
	
	public void insertSweetMessage(SweetMessageVO sweetMessageVO) {
		mybatis.insert("InvitationDAO.insertSweetMessage", sweetMessageVO);
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
	
	public List<SweetMessageVO> getSweetMessageListAll(String invSeq) {
		return mybatis.selectList("InvitationDAO.selectSweetMessageAll", invSeq);
	}
	
	public SyntheticInvitationVO getSyntheticInvitation(String invSeq) {
		SyntheticInvitationVO resultSyntheticInvitation = new SyntheticInvitationVO();
		List<LoveStoryVO> listLS = new ArrayList<>();
		List<WhenWhereVO> listWW = new ArrayList<>();
		List<GalleryVO> listG = new ArrayList<>();
		List<SweetMessageVO> listSM = new ArrayList<>();
		Criteria cri = new Criteria();
		cri.setPerPageNum(99);
		
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
	
	public void modifyWhenWhere(WhenWhereVO whenWhereVO) {
		mybatis.update("InvitationDAO.updateWhenWhere", whenWhereVO);
	}
	
	public void modifyGallery(GalleryVO galleryVO) {
		mybatis.update("InvitationDAO.updateGallery", galleryVO);
	}
	
	public void modifySweetMessageDeleteFlag(Integer seq, Boolean isDelete) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("seq", seq);
		param.put("isDelete", isDelete);
		
		mybatis.update("InvitationDAO.updateSweetMessageDeleteFlag", param);
	}
	
	public List<InvitationList> getInvitationList(Criteria cri, String id, String name, String beginDate, String endDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("id", id);
		param.put("name", name);
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		param.put("pageStart", cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		
		return mybatis.selectList("InvitationDAO.getInvitationList", param);
	}
	
	public int getInvitationListCount(String id, String name, String beginDate, String endDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("id", id);
		param.put("name", name);
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		
		return mybatis.selectOne("InvitationDAO.getInvitationListCount", param);
	}
	
	public void changeDeleteFlag(String category, Integer invSeq, List<Integer> newList) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("category", category);
		param.put("invSeq", invSeq);
		param.put("newList", newList);
		
		mybatis.update("InvitationDAO.changeDeleteFlag", param);
	}
}
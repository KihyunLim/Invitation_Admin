package com.invitation.biz.member.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.member.user.UserMemberInfoVO;
import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberVO;

@Repository
public class UserMemberDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("pageStart",  cri.getPageStart());
		param.put("perPageNum", cri.getPerPageNum());
		param.put("condition", condition);
		param.put("keyword", keyword);
		
		return mybatis.selectList("MemberUserDAO.getMemberList", param);
	}
	
	public int getMemberListCount(String condition, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("condition", condition);
		param.put("keyword", keyword);
		
		return mybatis.selectOne("MemberUserDAO.getMemberListCount", param);
	}
	
	public int getOverlapCheck(String id) {
		return mybatis.selectOne("MemberUserDAO.getOverlapCheck", id);
	}
	
	public void registerMember(UserMemberVO vo) {
		mybatis.insert("MemberUserDAO.registerMember", vo);
	}
	
	public UserMemberInfoVO getMemberInfo(String id) {
		return mybatis.selectOne("MemberUserDAO.getMemberInfo", id);
	}
	
	public void modifyMember(UserMemberVO vo) {
		mybatis.update("MemberUserDAO.modifyMember", vo);
	}
	
	public void deleteMember(String id) {
		mybatis.update("MemberUserDAO.deleteMember", id);
	}
}

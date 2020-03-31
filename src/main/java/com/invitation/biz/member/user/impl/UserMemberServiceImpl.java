package com.invitation.biz.member.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.member.user.UserMemberInfoVO;
import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberService;
import com.invitation.biz.member.user.UserMemberVO;

@Service("userMember")
public class UserMemberServiceImpl implements UserMemberService {

	@Autowired
	private UserMemberDAOMybatis userMemberDAO;
	
	@Override
	public List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword) {
		return userMemberDAO.getMemberList(cri, condition, keyword);
	}

	@Override
	public int getMemberListCount(String condition, String keyword) {
		return userMemberDAO.getMemberListCount(condition, keyword);
	}
	
	@Override
	public int getOverlapCheck(String id) {
		return userMemberDAO.getOverlapCheck(id);
	}
	
	@Override
	public void registerMember(UserMemberVO vo) {
		userMemberDAO.registerMember(vo);
	}
	
	@Override
	public UserMemberInfoVO getMemberInfo(String id) {
		return userMemberDAO.getMemberInfo(id);
	}
}

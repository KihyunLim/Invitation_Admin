package com.invitation.biz.member.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberService;
import com.invitation.biz.member.user.UserMemberVO;

@Service("userMember")
public class UserMemberServiceImpl implements UserMemberService {

	@Autowired
	private UserMemberDAOMybatis userMemberDAO;
	
	@Override
	public List<UserMemberVO> getUserList() {
		return userMemberDAO.getUserList();
	}
	
	@Override
	public List<UserMemberListVO> getMemberList() {
		return userMemberDAO.getMemberList();
	}
}

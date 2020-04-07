package com.invitation.biz.member.user;

import java.util.List;

import com.invitation.biz.common.paging.Criteria;

public interface UserMemberService {

	List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword);
	
	int getMemberListCount(String condition, String keyword);
	
	int getOverlapCheck(String id);
	
	void registerMember(UserMemberVO vo);
	
	UserMemberInfoVO getMemberInfo(String id);
	
	void modifyMember(UserMemberVO vo);
	
	void deleteMember(String id);
}
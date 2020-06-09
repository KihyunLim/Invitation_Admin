package com.invitation.biz.member.user;

import java.util.List;

import com.invitation.biz.common.paging.Criteria;

public interface UserMemberService {

	List<UserMemberListVO> getMemberList(Criteria cri, String condition, String keyword);
	
	int getMemberListCount(String condition, String keyword);
	
	int getOverlapCheck(String id);
	
	void registerMember(UserMemberVO vo);
	
	UserMemberInfoVO getMemberInfo(String id) throws Exception;
	
	void modifyMember(String id, UserMemberVO vo) throws Exception;
	
	void deleteMember(String id) throws Exception;
}
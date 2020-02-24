package com.invitation.biz.member.user;

import java.util.List;

public interface UserMemberService {

	List<UserMemberVO> getUserList();

	List<UserMemberListVO> getMemberList();
}
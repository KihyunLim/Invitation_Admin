package com.invitation.biz.invitation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.MemberInfoVO;

@Service("invitation")
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationDAOMybatis invitationDAO;
	
	@Override
	public MemberInfoVO getMemberInfo(String id) {
		return invitationDAO.getMemberInfo(id);
	}
}

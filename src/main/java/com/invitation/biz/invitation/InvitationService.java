package com.invitation.biz.invitation;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id);

	int getLastInsertID();
	
	int registerInvitaiton(SyntheticInvitationVO syntheticInvitationVO);
}
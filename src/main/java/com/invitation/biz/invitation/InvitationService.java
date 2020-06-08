package com.invitation.biz.invitation;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id);

	int getLastInsertID();
	
	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
}
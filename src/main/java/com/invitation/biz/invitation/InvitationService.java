package com.invitation.biz.invitation;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id) throws Exception;

	int getLastInsertID();
	
	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
}
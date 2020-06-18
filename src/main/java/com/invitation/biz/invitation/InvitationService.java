package com.invitation.biz.invitation;

import java.util.Map;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id) throws Exception;

	int getLastInsertID();
	
	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
	
	Map<String, Object> getMemberInvitation(String id) throws Exception;
	
	SyntheticInvitationVO getSyntheticInvitation(String invSeq);
}
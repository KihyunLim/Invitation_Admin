package com.invitation.biz.invitation;

import java.util.List;
import java.util.Map;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id) throws Exception;

	int getLastInsertID();
	
	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
	
//	List<MainInfoVO> getMemberInvitation(String id) throws Exception;
	Map<String, Object> getMemberInvitation(String id) throws Exception;
}
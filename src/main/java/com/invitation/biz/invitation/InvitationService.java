package com.invitation.biz.invitation;

import java.util.List;
import java.util.Map;

import com.invitation.biz.common.paging.Criteria;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id) throws Exception;

	int getLastInsertID();
	
	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
	
	Map<String, Object> getMemberInvitation(String id) throws Exception;
	
	List<SweetMessageVO> getSweetMessageList(Criteria cri, String invSeq);
	
	int getSweetMessageCount(String invSeq);
	
	SyntheticInvitationVO getSyntheticInvitation(String invSeq);
}
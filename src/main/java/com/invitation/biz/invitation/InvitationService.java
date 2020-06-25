package com.invitation.biz.invitation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.invitation.biz.common.paging.Criteria;

public interface InvitationService {

	MemberInfoVO getMemberInfo(String id) throws Exception;

	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
	
	Map<String, Object> getMemberInvitation(String id) throws Exception;
	
	List<SweetMessageVO> getSweetMessageList(Criteria cri, String invSeq);
	
	int getSweetMessageCount(String invSeq);
	
	SyntheticInvitationVO getSyntheticInvitation(String invSeq);
	
	InvitationVO modifyInvitation(InvitationVO invitationVO);
	
	InvitationVO modifyMainInfo(MainInfoVO mainInfoVO);
	
	InvitationVO modifyLoveStory(String useLS, ArrayList<LoveStoryVO> loveStoryVO);
	
	int getLastInsertID();
}
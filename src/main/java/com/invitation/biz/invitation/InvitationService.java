package com.invitation.biz.invitation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.invitation.biz.common.paging.Criteria;

public interface InvitationService {

	// 관리자
	MemberInfoVO getMemberInfo(String id) throws Exception;

	void registerInvitation(SyntheticInvitationVO syntheticInvitationVO);
	
	Map<String, Object> getMemberInvitation(String id) throws Exception;
	
	List<SweetMessageVO> getSweetMessageList(Criteria cri, String invSeq);
	
	int getSweetMessageCount(String invSeq);
	
	SXSSFWorkbook getSweetMessageListAll(String invSeq);
	
	SyntheticInvitationVO getSyntheticInvitation(String invSeq);
	
	InvitationVO modifyInvitation(InvitationVO invitationVO);
	
	InvitationVO modifyMainInfo(MainInfoVO mainInfoVO);
	
	InvitationVO modifyLoveStory(String useLS, ArrayList<LoveStoryVO> loveStoryVO);
	
	InvitationVO modifyWhenWhere(ArrayList<WhenWhereVO> whenWhereVO);
	
	InvitationVO modifyGallery(String useG, ArrayList<GalleryVO> galleryVO);
	
	InvitationVO modifySweetMessage(String useSM, String invSeq);
	
	void modifySweetMessageDeleteFlag(Integer seq, Boolean isDelete);
	
	List<InvitationList> getInvitationList(Criteria cri, String id, String name, String beginDate, String endDate);
	
	int getInvitationListCount(String id, String name, String beginDate, String endDate);
	
	int getLastInsertID();
	
	// 청첩장 수신자 조회
	SyntheticInvitationVO receiveSyntheticInvitation(String invSeq) throws Exception;
	
	void registerSweetMessage(SweetMessageVO sweetMessageVO);
	
	List<SweetMessageVO> getInvitationSweetMessageList(String invSeq);
}
package com.invitation.biz.invitation.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invitation.biz.common.exception.CommonException;
import com.invitation.biz.common.fileUpload.FileUploadService;
import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;

@Service("invitation")
public class InvitationServiceImpl implements InvitationService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationServiceImpl.class);

	@Autowired
	private InvitationDAOMybatis invitationDAO;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Override
	public MemberInfoVO getMemberInfo(String id) throws Exception {
		MemberInfoVO memberInfoVO = invitationDAO.getMemberInfo(id);
		
		if(memberInfoVO.equals(null)) {
			throw new CommonException("조회 결과 없음!!");
		}
		
		return memberInfoVO;
	}

	@Override
	public int getLastInsertID() {
		return invitationDAO.getLastInsertID();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerInvitation(SyntheticInvitationVO vo) {
		Integer lastInsertID_vo = null;
		Integer lastInsertID_file = null;
		Map<String, Object> mapAttach = new HashMap<String, Object>();
		Iterator<LoveStoryVO> iteratorLS = vo.getLoveStoryVO().iterator();
		
		/**
		 * + invitationVO insert
		 * + get lastInsertId_inv and set lastInsertId_inv
		 * + set mainInfoVO
		 * 		+ mainInfoVO_fullName1 > addFile
		 * 		+ get lastInsertId_fullName and set seqImg1
		 * 		+ mainInfoVO_fullName2 > addFile
		 * 		+ get lastInsertId_fullName and set seqImg2
		 * 		+ mainInfoVO_fullName3 > addFile
		 * 		+ get lastInsertId_fullName and set seqImg3
		 * 		+ mainInfoVO insert
		 * - set loveStoryVO >>> for(loveStoryVO.length)
		 * 		- loveStoryVO[i]_fullName > addFile
		 * 		- get lastInsertId_fullName and set seqImg[i]
		 * 		- loveStoryVO[i] insert
		 * - set whenWhereVO >>> for(whereWhenVO.length)
		 * 		- whenWhereVO[i] insert
		 * - set galleryVO >>> for(galleryVO.length)
		 * 		- galleryVO[i]_fullName and set seqImg[i]
		 * 		- get lastInertId_fullName and set seqImg[i]
		 * 		- galleryVO[i] insert
		 */
		LOGGER.debug("registerInviation >>> ");
		invitationDAO.registerInvitaiton(vo);
		lastInsertID_vo = getLastInsertID();
		
		LOGGER.debug("insertMainInfo >>> ");
		vo.getMainInfoVO().setInvSeq(lastInsertID_vo);
		//
		mapAttach.put("invSeq", lastInsertID_vo);
		mapAttach.put("fullName", vo.getMainInfoVO().getFullNameMain());
		mapAttach.put("category", "MI");
		mapAttach.put("formCode", vo.getInvitationVO().getFormCode());
		fileUploadService.insertFileInfo(mapAttach);
		lastInsertID_file = getLastInsertID();
		vo.getMainInfoVO().setSeqImgMain(lastInsertID_file);
		//
		mapAttach.put("invSeq",  lastInsertID_vo);
		mapAttach.put("fullName",  vo.getMainInfoVO().getFullNameGroom());
		mapAttach.put("category",  "MI");
		mapAttach.put("formCode",  vo.getInvitationVO().getFormCode());
		fileUploadService.insertFileInfo(mapAttach);
		lastInsertID_file = getLastInsertID();
		vo.getMainInfoVO().setSeqImgGroom(lastInsertID_file);
		//
		mapAttach.put("invSeq",  lastInsertID_vo);
		mapAttach.put("fullName",  vo.getMainInfoVO().getFullNameBride());
		mapAttach.put("category",  "MI");
		mapAttach.put("formCode",  vo.getInvitationVO().getFormCode());
		fileUploadService.insertFileInfo(mapAttach);
		lastInsertID_file = getLastInsertID();
		vo.getMainInfoVO().setSeqImgBride(lastInsertID_file);
		//
		invitationDAO.insertMainInfo(vo);
		
		LOGGER.debug("insertLoveStory >>> ");
//		for(LoveStoryVO item : vo.getLoveStoryVO()) { 
//			LOGGER.debug(item.toString());
//		}
		while(iteratorLS.hasNext()) {
			LoveStoryVO item = iteratorLS.next();
			mapAttach.put("invSeq", lastInsertID_vo);
			mapAttach.put("fullName", item.getFullNameImg());
			mapAttach.put("category", "LS");
			mapAttach.put("formCode", vo.getInvitationVO().getFormCode());
			fileUploadService.insertFileInfo(mapAttach);
			lastInsertID_file = getLastInsertID();
			item.setSeqImage(lastInsertID_file);
		}
		invitationDAO.insertLoveStory(vo);
	}
}

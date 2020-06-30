package com.invitation.biz.invitation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invitation.biz.common.exception.CommonException;
import com.invitation.biz.common.fileUpload.FileUploadService;
import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.invitation.GalleryVO;
import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.InvitationVO;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MainInfoVO;
import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SweetMessageVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;
import com.invitation.biz.invitation.WhenWhereVO;

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
		
		if(memberInfoVO == null) {
			throw new CommonException("조회 결과 없음!!");
		}
		
		return memberInfoVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerInvitation(SyntheticInvitationVO vo) {
		Integer lastInsertID_vo = null;
		Integer lastInsertID_file = null;
		Iterator<LoveStoryVO> iteratorLS = vo.getLoveStoryVO().iterator();
		Iterator<WhenWhereVO> iteratorWW = vo.getWhenWhereVO().iterator();
		Iterator<GalleryVO> iteratorG = vo.getGalleryVO().iterator();
		
		LOGGER.debug("registerInviation >>> ");
		invitationDAO.registerInvitaiton(vo);
		lastInsertID_vo = getLastInsertID();
		
		LOGGER.debug("insertMainInfo >>> ");
		vo.getMainInfoVO().setInvSeq(lastInsertID_vo);
		vo.getMainInfoVO().setId(vo.getInvitationVO().getId());
		//
		lastInsertID_file = getFileLastInsertID(lastInsertID_vo, vo.getMainInfoVO().getFullNameMain(), "MI", vo.getInvitationVO().getFormCode());
		vo.getMainInfoVO().setSeqImgMain(lastInsertID_file);
		//
		lastInsertID_file = getFileLastInsertID(lastInsertID_vo, vo.getMainInfoVO().getFullNameGroom(), "MI", vo.getInvitationVO().getFormCode());
		vo.getMainInfoVO().setSeqImgGroom(lastInsertID_file);
		//
		lastInsertID_file = getFileLastInsertID(lastInsertID_vo, vo.getMainInfoVO().getFullNameBride(), "MI", vo.getInvitationVO().getFormCode());
		vo.getMainInfoVO().setSeqImgBride(lastInsertID_file);
		//
		invitationDAO.insertMainInfo(vo);
		
		LOGGER.debug("insertLoveStory >>> ");
		for(LoveStoryVO item : vo.getLoveStoryVO()) { 
			lastInsertID_file = getFileLastInsertID(lastInsertID_vo, item.getFullNameImg(), "LS", vo.getInvitationVO().getFormCode());
			item.setInvSeq(lastInsertID_vo);
			item.setId(vo.getInvitationVO().getId());
			item.setIsDelete(false);
			item.setSeqImage(lastInsertID_file);
		}
//		while(iteratorLS.hasNext()) {
//			LoveStoryVO item = iteratorLS.next();
//			lastInsertID_file = getFileLastInsertID(lastInsertID_vo, item.getFullNameImg(), "LS", vo.getInvitationVO().getFormCode());
//			item.setInvSeq(lastInsertID_vo);
//			item.setId(vo.getInvitationVO().getId());
//			item.setIsDelete(false);
//			item.setSeqImage(lastInsertID_file);
//		}
		if(vo.getLoveStoryVO().size() > 0) {
			invitationDAO.insertLoveStory(vo);
		}
		
		LOGGER.debug("insertWhenWhere >>> ");
		while(iteratorWW.hasNext()) {
			WhenWhereVO item = iteratorWW.next();
			item.setInvSeq(lastInsertID_vo);
			item.setId(vo.getInvitationVO().getId());
		}
		invitationDAO.insertWhenWhere(vo);
		
		LOGGER.debug("insertGallery >>> ");
		for(GalleryVO item : vo.getGalleryVO()) { 
			lastInsertID_file = getFileLastInsertID(lastInsertID_vo, item.getFullName(), "G", vo.getInvitationVO().getFormCode());
			item.setInvSeq(lastInsertID_vo);
			item.setId(vo.getInvitationVO().getId());
			item.setIsDelete(false);
			item.setSeqImage(lastInsertID_file);
		}
//		while(iteratorG.hasNext()) {
//			GalleryVO item = iteratorG.next();
//			lastInsertID_file = getFileLastInsertID(lastInsertID_vo, item.getFullName(), "G", vo.getInvitationVO().getFormCode());
//			item.setInvSeq(lastInsertID_vo);
//			item.setId(vo.getInvitationVO().getId());
//			item.setIsDelete(false);
//			item.setSeqImage(lastInsertID_file);
//		}
		if(vo.getGalleryVO().size() > 0) {
			invitationDAO.insertGallery(vo);
		}
	}

	@Override
	public Map<String, Object> getMemberInvitation(String id) throws Exception {
		Map<String, Object> memberInvitationInfo = new HashMap<>();
		MemberInfoVO memberInfo = getMemberInfo(id);
		List<MainInfoVO> invitationList = invitationDAO.getMemberInvitation(memberInfo.getId());
		
		memberInvitationInfo.put("memberInfo", memberInfo);
		memberInvitationInfo.put("memberInvitationList", invitationList);
		
		return memberInvitationInfo;
	}

	@Override
	public List<SweetMessageVO> getSweetMessageList(Criteria cri, String invSeq) {
		return invitationDAO.getSweetMessageList(cri, invSeq);
	}
	
	@Override
	public int getSweetMessageCount(String invSeq) {
		return invitationDAO.getSweetMessageCount(invSeq);
	}
	
	@Override
	public SXSSFWorkbook getSweetMessageListAll(String invSeq) {
		return excelDownloadSweetMessageList(invitationDAO.getSweetMessageListAll(invSeq));
	}
	
	@Override
	public SyntheticInvitationVO getSyntheticInvitation(String invSeq) {
		return invitationDAO.getSyntheticInvitation(invSeq);
	}

	@Override
	public InvitationVO modifyInvitation(InvitationVO invitationVO) {
		return invitationDAO.modifyInvitation(invitationVO);
	}

	/**
	 * trigger 실습을 위해 MAIN_INFO update 후 아래 내용 trigger 적용 
	 * INVITATION_LIST > USE_EACH_IMAGE / WHEN_WHERE > ADDRESS, X_PALCE, Y_PALCE, DATE_WW, TIME_WW
	 * (나머지 INVITATION_LIST의 USE_*플래그들은 직접 UPDATE 처리)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public InvitationVO modifyMainInfo(MainInfoVO mainInfoVO) {
		Integer lastInsertID_file = null;
		String formCode = "";
		
		if(mainInfoVO.getSeqImgMain() == -1 || mainInfoVO.getSeqImgGroom() == -1 || mainInfoVO.getSeqImgBride() == -1) {
			formCode = invitationDAO.getFormCode(mainInfoVO.getInvSeq());
			
			if(mainInfoVO.getSeqImgMain() == -1) {
				lastInsertID_file = getFileLastInsertID(mainInfoVO.getInvSeq(), mainInfoVO.getFullNameMain(), "MI", formCode);
				mainInfoVO.setSeqImgMain(lastInsertID_file);
			}
			if(mainInfoVO.getSeqImgGroom() == -1) {
				lastInsertID_file = getFileLastInsertID(mainInfoVO.getInvSeq(), mainInfoVO.getFullNameGroom(), "MI", formCode);
				mainInfoVO.setSeqImgGroom(lastInsertID_file);
			}
			if(mainInfoVO.getSeqImgBride() == -1) {
				lastInsertID_file = getFileLastInsertID(mainInfoVO.getInvSeq(), mainInfoVO.getFullNameBride(), "MI", formCode);
				mainInfoVO.setSeqImgBride(lastInsertID_file);
			}
		}
		
		return invitationDAO.modifyMainInfo(mainInfoVO);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public InvitationVO modifyLoveStory(String useLS, ArrayList<LoveStoryVO> loveStoryVO) {
		Integer lastInsertID_file = null;
		Iterator<LoveStoryVO> iteratorLS = loveStoryVO.iterator();
		String formCode = invitationDAO.getFormCode(loveStoryVO.get(0).getInvSeq());
		List<Integer> newLoveStory = new ArrayList<Integer>();
		
		while(iteratorLS.hasNext()) {
			LoveStoryVO item = iteratorLS.next();
			
			if(item.getSeqImage() == -1) {
				lastInsertID_file = getFileLastInsertID(item.getInvSeq(), item.getFullNameImg(), "LS", formCode);
				item.setSeqImage(lastInsertID_file);
				invitationDAO.insertLoveStoryItem(item);
				newLoveStory.add(getLastInsertID());
			} else {
				invitationDAO.modifyLoveStory(item);
				newLoveStory.add(item.getSeq());
			}
		}
		LOGGER.info("newLoveStory : " + newLoveStory.toString());
		if(newLoveStory.size() > 0) {
			invitationDAO.changeDeleteFlag("ls", loveStoryVO.get(0).getInvSeq(), newLoveStory);
		}
		
		return modifyInvitationUseFlag(loveStoryVO.get(0).getInvSeq(), "ls", useLS);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public InvitationVO modifyWhenWhere(ArrayList<WhenWhereVO> whenWhereVO) {
		invitationDAO.modifyWhenWhere(whenWhereVO.get(0));
		
		if(whenWhereVO.size() == 2) {
			if(whenWhereVO.get(1).getModifyType().equals("add")) {
				invitationDAO.insertWhenWhereItem(whenWhereVO.get(1));
			} else {
				invitationDAO.modifyWhenWhere(whenWhereVO.get(1));
			}
		}
		
		return modifyInvitationUseFlag(whenWhereVO.get(0).getInvSeq(), "ww", whenWhereVO.get(0).getFlagPyebaek());
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public InvitationVO modifyGallery(String useG, ArrayList<GalleryVO> galleryVO) {
		Integer lastInsertID_file = null;
		String formCode = invitationDAO.getFormCode(galleryVO.get(0).getInvSeq());
		List<Integer> newGallery = new ArrayList<Integer>();
		
		for(GalleryVO item : galleryVO) {
			if(item.getSeqImage() == -1) {
				lastInsertID_file = getFileLastInsertID(item.getInvSeq(), item.getFullName(), "G", formCode);
				item.setSeqImage(lastInsertID_file);
				invitationDAO.insertGalleryItem(item);
				newGallery.add(getLastInsertID());
			} else {
				invitationDAO.modifyGallery(item);
				newGallery.add(item.getSeq());
			}
		}
		LOGGER.info("newGallery : " + newGallery.toString());
		if(newGallery.size() > 0) {
			invitationDAO.changeDeleteFlag("gallery", galleryVO.get(0).getInvSeq(), newGallery);
		}
		
		return modifyInvitationUseFlag(galleryVO.get(0).getInvSeq(), "gallery", useG);
	}
	
	@Override
	public InvitationVO modifySweetMessage(String useSM, String invSeq) {
		return modifyInvitationUseFlag(Integer.parseInt(invSeq), "sm", useSM);
	}
	
	@Override
	public void modifySweetMessageDeleteFlag(Integer seq, Boolean isDelete) {
		invitationDAO.modifySweetMessageDeleteFlag(seq, isDelete);
	}
	
	@Override
	public int getLastInsertID() {
		return invitationDAO.getLastInsertID();
	}
	
	private int getFileLastInsertID(int invSeq, String fullName, String category, String formCode) {
		Map<String, Object> mapAttach = new HashMap<String, Object>();
		
		mapAttach.put("invSeq", invSeq);
		mapAttach.put("fullName", fullName);
		mapAttach.put("category", category);
		mapAttach.put("formCode", formCode);
		
		fileUploadService.insertFileInfo(mapAttach);
		
		return getLastInsertID();
	}
	
	private InvitationVO modifyInvitationUseFlag(int invSeq, String useCategory, String useFlag) {
		return invitationDAO.modifyInvitationUseFlag(invSeq, useCategory, useFlag);
	}
	
	private SXSSFWorkbook excelDownloadSweetMessageList(List<SweetMessageVO> listSweetMessage) {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		
		SXSSFSheet sheet = workbook.createSheet("방명록");
		
		// 순번, 일시, 이름, 내용, 비밀번호
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		
		Row headerRow = sheet.createRow(0);
		
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("순번");
		
		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("일시");
		
		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("이름");
		
		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("내용");
		
		Row bodyRow = null;
		Cell bodyCell = null;
		for(int i = 0 ; i < listSweetMessage.size() ; i++) {
			SweetMessageVO item = listSweetMessage.get(i);
			
			bodyRow = sheet.createRow(i+1);
			
			bodyCell = bodyRow.createCell(0);
			bodyCell.setCellValue(item.getSeq());
			
			bodyCell = bodyRow.createCell(1);
			bodyCell.setCellValue(item.getDateTimeUpdate());
			
			bodyCell = bodyRow.createCell(2);
			bodyCell.setCellValue(item.getRegisterName());
			
			bodyCell = bodyRow.createCell(3);
			bodyCell.setCellValue(item.getRegisterContent());
		}
		
		return workbook;
	}
}

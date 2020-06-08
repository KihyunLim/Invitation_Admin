package com.invitation.controller.invitation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.common.exception.CommonException;
import com.invitation.biz.common.fileUpload.FileUploadService;
import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;

@Controller
@RequestMapping(value="/invitation")
public class InvitationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationController.class);
	
	@Autowired
	private InvitationService invitationService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/invitationAdd.do", method=RequestMethod.GET)
	public String temp1(Model model) {
		LOGGER.info("invitationAdd.do");
		
		return "invitation/invitationAdd";
	}
	
	@RequestMapping(value="/invitationDetail.do", method=RequestMethod.GET)
	public String temp2(Model model) {
		LOGGER.info("invitationDetail.do");
		
		return "invitation/invitationDetail";
	}
	
	@RequestMapping(value="/invitationList.do", method=RequestMethod.GET)
	public String temp3(Model model) {
		LOGGER.info("invitationList.do");
		
		return "invitation/invitationList";
	}
	
	@GetMapping(value="/getMemberInfo")
	@ResponseBody
	public Map<String, Object> getMemberInfo(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		MemberInfoVO resMemberInfo = null;
		
		LOGGER.info("getMemberInfo");
		try {
			resMemberInfo = invitationService.getMemberInfo(id);
			
			if(resMemberInfo == null) {
				throw new CommonException("조회 결과 없음!!");
			}
			
			resFlag = true;
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "일치하는 회원이 없습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage",  resMessage);
			result.put("resMemberInfo", resMemberInfo);
		}
		
		return result;
	}
	
	@PostMapping(value="/registerInvitation.do", headers= {"Content-type=application/json"})
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Map<String, Object> registerInvitaiton(@RequestBody SyntheticInvitationVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		Integer lastInsertID_vo = null;
		Integer lastInsertID_file = null;
		Map<String, Object> mapAttach = new HashMap<String, Object>();
		
		LOGGER.info("registerInvitation.do");
		try {
			LOGGER.debug(vo.toString());
			
			/**
			 * + invitationVO insert
			 * + get lastInsertId_inv and set lastInsertId_inv
			 * - set mainInfoVO
			 * 	- mainInfoVO_fullName1 > addFile
			 * 	- get lastInsertId_fullName and set seqImg1
			 * 	- mainInfoVO_fullName2 > addFile
			 * 	- get lastInsertId_fullName and set seqImg2
			 * 	- mainInfoVO_fullName3 > addFile
			 * 	- get lastInsertId_fullName and set seqImg3
			 * 	- mainInfoVO insert
			 * - set loveStoryVO >>> for(loveStoryVO.length)
			 * 	- loveStoryVO[i]_fullName > addFile
			 * 	- get lastInsertId_fullName and set seqImg[i]
			 * 	- loveStoryVO[i] insert
			 * - set whenWhereVO >>> for(whereWhenVO.length)
			 * 	- whenWhereVO[i] insert
			 * - set galleryVO >>> for(galleryVO.length)
			 * 	- galleryVO[i]_fullName and set seqImg[i]
			 * 	- get lastInertId_fullName and set seqImg[i]
			 * 	- galleryVO[i] insert
			 */
			invitationService.registerInvitaiton(vo);
			lastInsertID_vo = invitationService.getLastInsertID();
			
			LOGGER.debug("##### lastInsertID_vo : " + lastInsertID_vo);
			if(lastInsertID_vo.equals(null)) {
				throw new CommonException("lastInsertID_vo is null");
			}
			
			vo.getMainInfoVO().setInvSeq(lastInsertID_vo);
			if(vo.getMainInfoVO().getFullNameMain().equals(null)) {
				throw new CommonException("mainImage is null");
			}
			mapAttach.put("invSeq", lastInsertID_vo);
			mapAttach.put("fullName", vo.getMainInfoVO().getFullNameMain());
			mapAttach.put("category", "MIasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf");
			mapAttach.put("formCode", vo.getInvitationVO().getFormCode());
			fileUploadService.insertFileInfo(mapAttach);
			lastInsertID_file = invitationService.getLastInsertID();
			LOGGER.debug("##### lastInsertID_file : " + lastInsertID_file);
			if(lastInsertID_file.equals(null)) {
				throw new CommonException("lastInsertID_file is null");
			}
			
			resFlag = true;
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			if(e.getMessage().equals("lastInsertID_vo is null")) {
				resMessage = "청첩장 추가 실패";
			} else if(e.getMessage().equals("mainImage is null")) {
				resMessage = "메인 이미지는 필수입니다.";
			} else if(e.getMessage().equals("lastInsertID_file is null")) {
				resMessage = "메인 이미지 저장 실패";
			}
			
			resFlag = false;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 추가에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
		}
		
		return result;
	}
}

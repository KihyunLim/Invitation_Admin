package com.invitation.controller.invitation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.common.exception.CommonException;
import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.common.paging.PageMaker;
import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.InvitationVO;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MainInfoVO;
import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SweetMessageVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;

@Controller
@RequestMapping(value="/invitation")
public class InvitationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationController.class);
	
	@Autowired
	private InvitationService invitationService;
	
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
	@ResponseBody
	public Map<String, Object> registerInvitaiton(@RequestBody SyntheticInvitationVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("registerInvitation.do");
		try {
			invitationService.registerInvitation(vo);
			
			resFlag = true;
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
	
	@GetMapping(value="/getMemberInvitation.do")
	@ResponseBody
	public Map<String, Object> getMemberInvitation(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		Map<String, Object> resMemberInvitation = new HashMap<String, Object>();
		
		LOGGER.info("getMemberInvitation.do");
		try {
			resMemberInvitation = invitationService.getMemberInvitation(id);
			
			resFlag = true;
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 목록 조회에 실패했습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 목록 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resMemberInvitation", resMemberInvitation);
		}
		
		return result;
	}
	
	@GetMapping(value="/getSyntheticInvitation.do")
	@ResponseBody
	public Map<String, Object> getSyntheticInvitation(@RequestParam(value="invSeq", required=true) String invSeq) {
		Map<String, Object>	result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		SyntheticInvitationVO resSyntheticInvitation = null;
		PageMaker resPageMaker = null;
		
		LOGGER.info("getSyntheTicInvitation.do");
		try {
			resSyntheticInvitation = invitationService.getSyntheticInvitation(invSeq);
			
			PageMaker pageMaker = new PageMaker();
			Criteria cri = new Criteria();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(invitationService.getSweetMessageCount(invSeq));
			
			resFlag = true;
			resPageMaker = pageMaker;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 상세 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resSyntheticInvitation", resSyntheticInvitation);
			result.put("pageMaker", resPageMaker);
		}
		
		return result;
	}
	
	@GetMapping(value="/getSweetMessageList.do")
	@ResponseBody
	public Map<String, Object> getSweetMessageList(Criteria cri,
			@RequestParam(value="invSeq", required=true) String invSeq) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<SweetMessageVO> resSweetMessageList = null;
		PageMaker resPageMaker = null;
		
		LOGGER.info("getSweetMessageList");
		try {
			List<SweetMessageVO> sweetMessageList = invitationService.getSweetMessageList(cri, invSeq);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(invitationService.getSweetMessageCount(invSeq));
			
			resFlag = true;
			resSweetMessageList = sweetMessageList;
			resPageMaker = pageMaker;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 댓글 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resSweetMessageList", resSweetMessageList);
			result.put("pageMaker", resPageMaker);
		}
		
		return result;
	}
	
	@PostMapping(value="/modifyInvitation.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifyInvitation(@RequestBody InvitationVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifyInvitation.do");
		try {
			resInvitationVO = invitationService.modifyInvitation(vo);
			
			resFlag = true;
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "기본정보 수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resInvitationVO", resInvitationVO);
		}
		
		return result;
	}
	
	@PostMapping(value="/modifyMainInfo.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifyMainInfo(@RequestBody MainInfoVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifyMainInfo.do");
		try {
			resInvitationVO = invitationService.modifyMainInfo(vo);
			
			resFlag = true;
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "메인정보 수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resInvitationVO", resInvitationVO);
		}
		
		return result;
	}
	
	@PostMapping(value="/modifyLoveStory.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifyLoveStory(@RequestParam(value="useLS", required=true) String useLS, 
			@RequestBody ArrayList<LoveStoryVO> vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifyLoveStory.do");
		try {
			
			
			resFlag = true;
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "Love Story 수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resInvitationVO", resInvitationVO);
		}
		
		return result;
	}
}

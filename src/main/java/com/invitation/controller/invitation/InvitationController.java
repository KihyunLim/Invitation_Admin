package com.invitation.controller.invitation;

import java.util.HashMap;
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
import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.MemberInfoVO;
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
	@ResponseBody
	public Map<String, Object> registerInvitaiton(@RequestBody SyntheticInvitationVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("registerInvitation.do");
		try {
			LOGGER.debug(vo.toString());
			LOGGER.debug(vo.getInvitationVO().toString());
			
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
}

package com.invitation.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private UserMemberService userMemberService;
	
	@RequestMapping(value="/memberList.do", method=RequestMethod.GET)
	public String viewMemberList(Model model) {
		LOGGER.info("memberList.do");
		
		return "member/memberList";
	}
	
	@RequestMapping(value="/getMemberList", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMemberList() {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<UserMemberListVO> resUserMemberList = null;
		
		LOGGER.info("getMemberList");
		try {
			List<UserMemberListVO> userMemberList = userMemberService.getMemberList();
			
			resFlag = true;
			resUserMemberList = userMemberList;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 목록 조회에 실패앴습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("list", resUserMemberList);
		}
		
		return result;
	}
}
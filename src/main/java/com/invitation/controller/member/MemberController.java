package com.invitation.controller.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/memberList.do", method=RequestMethod.GET)
	public String viewMemberList(Model model) {
		LOGGER.info("memberList.do");
		
		return "member/memberList";
	}
}

package com.invitation.controller.invitation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/invitation")
public class InvitationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvitationController.class);
	
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
}

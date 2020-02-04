package com.invitation.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login(Model model) {
		LOGGER.info("login page!!");
		
		return "login/login";
	}
	
	// 로그인 요청 ㄱㄱ
}

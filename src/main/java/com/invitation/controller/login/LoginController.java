package com.invitation.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.admin.user.UserAdminService;
import com.invitation.biz.admin.user.UserAdminVO;
import com.invitation.biz.common.exception.CommonException;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserAdminService userAdminService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login(Model model) {
		LOGGER.info("login page!!");
		
		return "login/login";
	}
	
	@PostMapping(value="/login.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> doLogin(@RequestBody UserAdminVO user, HttpSession session) throws CommonException {
		Boolean resFlag = false;
		String resMessage = "";
		Map<String, Object> result = new HashMap<String, Object>();
		
		LOGGER.info("doLogin");
		try {
			if(userAdminService.getUserInfo2(user)) {
				session.setAttribute("id", user.getId());
				resFlag = true;
			} else {
				throw new CommonException("비밀번호 불일치!!");
			}
		} catch(NullPointerException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "등록된 아이디가 없습니다.";
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "비밀번호가 일지하지 않습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "로그인 중 에러가 발생했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
		}
		
		return result;
	}
	
	@GetMapping(value="/logout.do")
	public String doLogout(HttpSession session) {
		LOGGER.info("logout!!");
		
		session.invalidate();
		
		return "login/login";
	}
}

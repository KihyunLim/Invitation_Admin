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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.common.paging.PageMaker;
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
	public Map<String, Object> getMemberList(Criteria cri,
			@RequestParam(value="searchCondition", defaultValue="", required=false) String condition,
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword
			) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<UserMemberListVO> resUserMemberList = null;
		PageMaker resPageMaker = null;
		
		LOGGER.info("getMemberList");
		try {
			List<UserMemberListVO> userMemberList = userMemberService.getMemberList(cri, condition, keyword);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(userMemberService.getMemberListCount(condition, keyword));
			
			resFlag = true;
			resUserMemberList = userMemberList;
			resPageMaker = pageMaker;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 목록 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("list", resUserMemberList);
			result.put("pageMaker", resPageMaker);
		}
		
		return result;
	}
}
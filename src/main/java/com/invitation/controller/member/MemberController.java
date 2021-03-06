package com.invitation.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invitation.biz.common.exception.CommonException;
import com.invitation.biz.common.paging.Criteria;
import com.invitation.biz.common.paging.PageMaker;
import com.invitation.biz.member.user.UserMemberInfoVO;
import com.invitation.biz.member.user.UserMemberListVO;
import com.invitation.biz.member.user.UserMemberService;
import com.invitation.biz.member.user.UserMemberVO;

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
	
	@RequestMapping(value="/getMemberList.do", method=RequestMethod.GET)
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
		
		LOGGER.info("getMemberList.do");
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
	
//	@RequestMapping(value="/getOverlapCheck.do", method=RequestMethod.GET)
	@GetMapping(value="/getOverlapCheck.do")
	@ResponseBody
	public Map<String, Object> getOverlapCheck(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resOverlapCheckedId = "";
		String resMessage = "";
		int resOverlapCheck = 0;
		
		LOGGER.info("getOverlapCheck.do");
		try {
			resOverlapCheck = userMemberService.getOverlapCheck(id);
			
			if(resOverlapCheck == 0) {
				resFlag = true;
				resOverlapCheckedId = id;
				resMessage = "사용 가능한 아이디입니다.";
			} else {
				resMessage = "사용중인 아이디입니다.";
			}
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "아이디 중복확인에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resOverlapCheckedId", resOverlapCheckedId);
			result.put("resMessage",  resMessage);
		}
		
		return result;
	}
	
	@PostMapping(value="/registerMember.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> registerMember(@RequestBody UserMemberVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("registerMember.do");
		try {
			userMemberService.registerMember(vo);
			
			resFlag = true;
			resMessage = "회원 등록이 완료되었습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 등록에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage",  resMessage);
		}
		
		return result;
	}
	
	@GetMapping(value="/{id}.do")
	@ResponseBody
	public Map<String, Object> getMemberInfo(@PathVariable String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		UserMemberInfoVO resMemberInfo = null;
		
		LOGGER.info("getMemberInfo.do");
		try {
			resMemberInfo = userMemberService.getMemberInfo(id);
			
			resFlag = true;
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "일치하는 회원이 없습니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 상세조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resMemberInfo", resMemberInfo);
		}
		
		return result;
	}
	
	@PutMapping(value="/{id}.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifyMember(@PathVariable String id, @RequestBody UserMemberVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("modifyMember.do");
		try {
			userMemberService.modifyMember(id, vo);
			
			resFlag = true;
			resMessage = "회원정보 수정이 완료되었습니다.";
		} catch(CommonException e) {
			LOGGER.error("error message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "부적절한 회원정보 수정 요청입니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 정보 수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
		}
		
		return result;
	}
	
	@DeleteMapping(value="/{id}.do")
	@ResponseBody
	public Map<String, Object> deleteMember(@PathVariable String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("deleteMember.do");
		try {
			userMemberService.deleteMember(id);
			
			resFlag = true;
			resMessage = "회원 삭제가 완료되었습니다.";
		} catch(CommonException e) {
			LOGGER.error("error Message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "삭제할 아이디를 확인해주세요.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "회원 삭제에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
		}
		
		return result;
	}
}
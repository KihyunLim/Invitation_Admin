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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.invitation.biz.invitation.GalleryVO;
import com.invitation.biz.invitation.InvitationList;
import com.invitation.biz.invitation.InvitationService;
import com.invitation.biz.invitation.InvitationVO;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MainInfoVO;
import com.invitation.biz.invitation.MemberInfoVO;
import com.invitation.biz.invitation.SweetMessageVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;
import com.invitation.biz.invitation.WhenWhereVO;

@CrossOrigin(origins="*")
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
	
	@GetMapping(value="/getMemberInfo.do")
	@ResponseBody
	public Map<String, Object> getMemberInfo(@RequestParam(value="id", required=true) String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		MemberInfoVO resMemberInfo = null;
		
		LOGGER.info("getMemberInfo.do");
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
			
			resFlag = false;
			resMessage = "일치하는 회원이 없습니다.";
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
			@RequestBody LoveStoryVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifyLoveStory.do");
		try {
			resInvitationVO = invitationService.modifyLoveStory(useLS, (ArrayList<LoveStoryVO>) vo.getListLoveStory());
			
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
	
	@PostMapping(value="/modifyWhenWhere.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifyWhenWhere(@RequestBody WhenWhereVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifyWhenWhere.do");
		try {
			resInvitationVO = invitationService.modifyWhenWhere((ArrayList<WhenWhereVO>) vo.getListWhenWhere());
			
			resFlag = true;
		} catch (Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "When Where 수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resInvitationVO", resInvitationVO);
		}
		
		return result;
	}
	
	@PostMapping(value="/modifyGallery.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifyGallery(@RequestParam(value="useG", required=true) String useG,
			@RequestBody GalleryVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifyGallery.do");
		try {
			resInvitationVO = invitationService.modifyGallery(useG, (ArrayList<GalleryVO>) vo.getListGallery());
			
			resFlag = true;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "Gallery 수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resInvitationVO", resInvitationVO);
		}
		
		return result;
	}
	
	@PostMapping(value="/modifySweetMessage.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> modifySweetMessage(@RequestParam(value="useSM", required=true) String useSM,
			@RequestBody String invSeq) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		InvitationVO resInvitationVO = new InvitationVO();
		
		LOGGER.info("modifySweetMessage.do");
		try {
			resInvitationVO = invitationService.modifySweetMessage(useSM, invSeq);
			
			resFlag = true;
		} catch(Exception e) {
			LOGGER.error("error emssage : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "Sweet Message  수정에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resInvitationVO", resInvitationVO);
		}
		
		return result;
	}
	
	@GetMapping(value="/deleteSweetMessage.do")
	@ResponseBody
	public Map<String, Object> deleteSweetMessage(@RequestParam(value="seq", required=true) Integer seq,
			@RequestParam(value="isDelete", required=true) Boolean isDelete) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		
		LOGGER.info("deleteSweetMessage.do");
		try {
			invitationService.modifySweetMessageDeleteFlag(seq, isDelete);
			
			resFlag = true;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "Sweet Message 삭제에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
		}
		
		return result;
	}
	
	@GetMapping(value="/getInvitationList.do")
	@ResponseBody
	public Map<String, Object> getInvitationList(Criteria cri,
			@RequestParam(value="searchId", defaultValue="", required=true) String id, 
			@RequestParam(value="searchName", defaultValue="", required=true) String name,
			@RequestParam(value="searchWeddingBegin", defaultValue="", required=true) String beginDate,
			@RequestParam(value="searchWeddingEnd", defaultValue="", required=true) String endDate) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<InvitationList> resInvitationList = null;
		PageMaker resPageMaker = null;
		
		LOGGER.info("getInvitationList.do");
		try {
			resInvitationList = invitationService.getInvitationList(cri, id, name, beginDate, endDate);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(invitationService.getInvitationListCount(id, name, beginDate, endDate));
			
			resFlag = true;
			resPageMaker = pageMaker;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 목록 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("list", resInvitationList);
			result.put("pageMaker", resPageMaker);
		}
		
		return result;
	}
	
	@GetMapping(value="/receiveInvitation.do")
	@ResponseBody
	public Map<String, Object> receiveSyntheticInvitation(@RequestParam(value="invSeq", required=true) String invSeq) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		SyntheticInvitationVO resSyntheticInvitationVO = null;
		
		LOGGER.info("receiveInvitation.do");
		try {
			resSyntheticInvitationVO = invitationService.receiveSyntheticInvitation(invSeq);
			
			resFlag = true;
		} catch(CommonException e) {
			LOGGER.warn("warn message : " + e.getMessage());
			
			resFlag = false;
			resMessage = "조회 할 수 없는 청첩장입니다.";
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "청첩장 조회에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resSyntheticInvitation", resSyntheticInvitationVO);
		}
		
		return result;
	}
	
	@PostMapping(value="/registerSweetMessage.do", headers= {"Content-type=application/json"})
	@ResponseBody
	public Map<String, Object> registerSweetMessage(@RequestBody SweetMessageVO vo) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean resFlag = false;
		String resMessage = "";
		List<SweetMessageVO> resSweetMessageVO = null;
		
		LOGGER.info("registerSweetMessage.do");
		try {
			invitationService.registerSweetMessage(vo);
			resSweetMessageVO = invitationService.getInvitationSweetMessageList(Integer.toString(vo.getInvSeq()));
			
			resFlag = true;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			resFlag = false;
			resMessage = "Sweet Message 등록에 실패했습니다.";
		} finally {
			result.put("resFlag", resFlag);
			result.put("resMessage", resMessage);
			result.put("resSweetMessage", resSweetMessageVO);
		}
		
		return result;
	}
}
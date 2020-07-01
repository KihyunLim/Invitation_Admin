package com.invitation.controller.common.excelDownload;

import java.util.Locale;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invitation.biz.invitation.InvitationService;

@Controller
@RequestMapping(value="/common")
public class ExcelDownloadController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDownloadController.class);
	
	@Autowired
	private InvitationService invitationService;
	
	@GetMapping(value="/excelDownload.do")
	public String excelDownloadSweetMessageList(Model model, @RequestParam(value="invSeq", required=true) String invSeq) {
		
		LOGGER.info("excelDownload.do");
		try {
			SXSSFWorkbook workbook = invitationService.getSweetMessageListAll(invSeq);
			
			model.addAttribute("locale", Locale.KOREA);
			model.addAttribute("workbook", workbook);
			model.addAttribute("workbookName", "방명록");
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
		}
		
		return "excelDownloadView";
	}
}

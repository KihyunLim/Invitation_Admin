package com.invitation.biz.common.excelDownloadView;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.invitation.controller.invitation.InvitationController;

public class ExcelDownloadView extends AbstractView {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDownloadView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Locale locale = (Locale) model.get("locale");
		String workbookName = (String) model.get("workbookName");
		
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd_HHmmss", locale);
		String fileName = workbookName + "_" + formatDate.format(date) + ".xlsx";
		
		String browser = request.getHeader("User-Agent");
		if(browser.indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		} else if(browser.indexOf("Trident") > -1) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		} else if(browser.indexOf("Firefox") > -1) {
			fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if(browser.indexOf("Opera") > -1) {
			fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if(browser.indexOf("Chrome") > -1) {
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0 ; i < fileName.length() ; i++) {
				char c = fileName.charAt(i);
				
				if(c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			
			fileName = sb.toString();
		} else if(browser.indexOf("Safari") > -1) {
			fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else {
			fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
		}
		
		response.setContentType("application/download;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream os = null;
		SXSSFWorkbook workbook = null;
		
		try {
			workbook = (SXSSFWorkbook) model.get("workbook");
			os = response.getOutputStream();
			
			workbook.write(os);
		} catch(Exception e) {
			LOGGER.error("(excel1) error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
		} finally {
			if(workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					LOGGER.error("(excel2) error message : " + e.getMessage());
					LOGGER.error("error trace : ", e);
				}
			}
			
			if(os != null) {
				try {
					os.close();
				} catch(Exception e) {
					LOGGER.error("(excel3) error message : " + e.getMessage());
					LOGGER.error("error trace : ", e);
				}
			}
		}
	}
}

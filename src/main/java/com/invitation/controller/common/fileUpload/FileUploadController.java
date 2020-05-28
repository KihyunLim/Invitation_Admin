package com.invitation.controller.common.fileUpload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.invitation.biz.common.fileUpload.MediaUtils;
import com.invitation.biz.common.fileUpload.UploadFileUtils;

@Controller
@RequestMapping(value="/common")
public class FileUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	/*@Value("${IA.IMG_SRC") private String IMG_SRC;
	@Value("${IA.IMG_SRC_ICO") private String IMG_SRC_ICO;
	@Value("${IA.ORIGINAL_FILE_URL") private String ORIGINAL_FILE_URL;
	
	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> uploadFile(MultipartFile file, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String originalFileName = "";
		String imgSrc = "";
		String uuidFileName = "";
		String originalFileUrl = "";
		String fullName = "";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		LOGGER.info("fileUpload.do");
		try {
			fullName = UploadFileUtils.uploadFile(file, request);
//			/2020/05/18/s_88385895-783f-4dd8-bb2a-35fa13ff12ae_sample1.jpg
			
			MediaType mediaType = MediaUtils.getMediaType(fullName);
			if(mediaType != null) {
				imgSrc = IMG_SRC + fullName;
				uuidFileName = fullName.substring(14);
				String originalImg = fullName.substring(0, 12) + fullName.substring(14);
				
				originalFileUrl = ORIGINAL_FILE_URL + originalImg; 
			} else {
				imgSrc = IMG_SRC_ICO;
				uuidFileName = fullName.substring(12);
				originalFileUrl = ORIGINAL_FILE_URL + fullName;
			}
			originalFileName = uuidFileName.substring(uuidFileName.indexOf("_") + 1);
			
			result.put("originalFileName", originalFileName);
			result.put("imgSrc", imgSrc);
			result.put("originalFileUrl", originalFileUrl);
			result.put("fullName", fullName);
			status = HttpStatus.CREATED;
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}*/
	@RequestMapping(value="/fileUpload.do", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request) {
		String resSavedFilePath = "";
		
		ResponseEntity<String> entity = null;
		
		LOGGER.info("fileUpload.do");
		try {
			resSavedFilePath = UploadFileUtils.uploadFile(file, request);
//			/2020/05/18/s_88385895-783f-4dd8-bb2a-35fa13ff12ae_sample1.jpg
			
			entity = new ResponseEntity<>(resSavedFilePath, HttpStatus.CREATED);
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error trace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/fileDisplay.do", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception {
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
		String rootPath = UploadFileUtils.getRootPath(fileName, request);
		ResponseEntity<byte[]> entity = null;
		
		LOGGER.info("fileDisplay.do");
		try(InputStream inputStream = new FileInputStream(rootPath + fileName)) {
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.OK);
		} catch(FileNotFoundException e) {
			LOGGER.info("info message : 파일을 찾지 못함!! " + e.getMessage());
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			LOGGER.error("error message : " + e.getMessage());
			LOGGER.error("error treace : ", e);
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}

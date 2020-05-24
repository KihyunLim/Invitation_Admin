package com.invitation.biz.common.fileUpload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
		String originalFileName = file.getOriginalFilename();
		byte[] fileData = file.getBytes();
		
		LOGGER.debug("uploadFile util : " + originalFileName);
		String uuidFileName = getUuidFileName(originalFileName);
		
		String rootPath = getRootPath(originalFileName, request);
		String datePath = getDatePath(rootPath);
		
		File target = new File(rootPath + datePath, uuidFileName);
		FileCopyUtils.copy(fileData, target);
		
		if(MediaUtils.getMediaType(originalFileName) != null) {
			uuidFileName = makeThumbnail(rootPath, datePath, uuidFileName);
		}
		
		return replaceSavedFilePath(datePath, uuidFileName);
	}
	
	public static void deleteFile(String fileName, HttpServletRequest request) {
		String rootPath = getRootPath(fileName, request);
		
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		if(mediaType != null) {
			LOGGER.debug("fileName 0 to end : " + fileName);
			LOGGER.debug("fileName 0 to 12 : " + fileName.substring(0, 12));
			LOGGER.debug("fileName 14 to end : " + fileName.substring(14));
			String originalImg = fileName.substring(0, 12) + fileName.substring(14);
			
			new File(rootPath + originalImg.replace('/', File.separatorChar)).delete();
		}
		
		new File(rootPath + fileName.replace('/', File.separatorChar)).delete();
	}
	
	public static HttpHeaders getHttpHeaders(String fileName) throws Exception {
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		HttpHeaders httpHeaders = new HttpHeaders();
		
		if(mediaType != null) {
			httpHeaders.setContentType(mediaType);
			return httpHeaders;
		}
		
		fileName = fileName.substring(fileName.indexOf("_") + 1);
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.add("Content-Disposition", "attachment); filename=\''" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\''");
		
		LOGGER.debug("################ 여로모로 이상한 라인인디;; ##################");
		LOGGER.debug("attachment); filename=\''" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\''");
		return httpHeaders;
	}
	
	public static String getRootPath(String fileName, HttpServletRequest request) {
		String rootPath = "/resources/upload";
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		
		LOGGER.debug("getRootPath : " + request.getSession().getServletContext().getRealPath(rootPath));
		if(mediaType != null) {
			return request.getSession().getServletContext().getRealPath(rootPath + "/images");
		}
		
		return request.getSession().getServletContext().getRealPath(rootPath + "/files");
	}
	
	private static String getDatePath(String uploadPath) {
		Calendar calendar = Calendar.getInstance();
		String yearPath = File.separator + calendar.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		
		makeDateDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	private static void makeDateDir(String uploadPath, String... paths) {
		if(new File(uploadPath + paths[paths.length - 1]).exists()) {
			return;
		}
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	private static String replaceSavedFilePath(String datePath, String fileName) {
		String savedFilePath = datePath + File.separator + fileName;
		
		return savedFilePath.replace(File.separatorChar, '/');
	}
	
	private static String getUuidFileName(String originalFileName) {
		return UUID.randomUUID().toString() + "_" + originalFileName;
	}
	
	private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws Exception {
		BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
		
		BufferedImage thumnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		String thumbnailImgName = "s_" + fileName;
		
		String fullPath = uploadRootPath + datePath + File.separator + thumbnailImgName;
		File newFile = new File(fullPath);
		String formatName = MediaUtils.getFormatName(fileName);
		
		ImageIO.write(thumnailImg, formatName, newFile);
		
		return thumbnailImgName;
	}
}

package com.invitation.biz.common.fileUpload.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invitation.biz.common.fileUpload.FileUploadService;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService{

	@Autowired
	private FileUploadDAOMybatis fileUploadDAOMybatis;
	
	@Override
	public void insertFileInfo(Map<String, Object> mapAttach) {
		fileUploadDAOMybatis.insertFileInfo(mapAttach);
	}
}

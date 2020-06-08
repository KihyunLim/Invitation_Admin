package com.invitation.biz.common.fileUpload.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertFileInfo(Map<String, Object> mapAttach) {
		mybatis.insert("UploadDAO.insertFileInfo", mapAttach);
	}
}

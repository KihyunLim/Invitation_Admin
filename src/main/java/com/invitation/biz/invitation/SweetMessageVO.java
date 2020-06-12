package com.invitation.biz.invitation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SweetMessageVO {

	private int seq;
	private int invSeq;
	private String id;
	private Boolean isDelete;
	private String registerName;
	private String registerContent;
	private String registerPassword;
	private Date dateTimeRegister;
	private Date dateTimeUpdate;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getInvSeq() {
		return invSeq;
	}
	public void setInvSeq(int invSeq) {
		this.invSeq = invSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getRegisterContent() {
		return registerContent;
	}
	public void setRegisterContent(String registerContent) {
		this.registerContent = registerContent;
	}
	public String getRegisterPassword() {
		return registerPassword;
	}
	public void setRegisterPassword(String registerPassword) {
		this.registerPassword = registerPassword;
	}
	public String getDateTimeRegister() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(dateTimeRegister);
	}
	public void setDateTimeRegister(Date dateTimeRegister) {
		this.dateTimeRegister = dateTimeRegister;
	}
	public String getDateTimeUpdate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(dateTimeUpdate);
	}
	public void setDateTimeUpdate(Date dateTimeUpdate) {
		this.dateTimeUpdate = dateTimeUpdate;
	}
	
	@Override
	public String toString() {
		return "SweetMessageVO [seq=" + seq + ", invSeq=" + invSeq + ", id=" + id + ", isDelete=" + isDelete
				+ ", registerName=" + registerName + ", registerContent=" + registerContent + ", registerPassword="
				+ registerPassword + ", dateTimeRegister=" + dateTimeRegister + ", dateTimeUpdate=" + dateTimeUpdate
				+ "]";
	}
}

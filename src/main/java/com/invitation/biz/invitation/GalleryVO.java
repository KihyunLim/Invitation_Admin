package com.invitation.biz.invitation;

import java.util.Date;

public class GalleryVO {

	private int seq;
	private int invSeq;
	private String id;
	private Boolean isDelete;
	private int seqImage;
	private String fullName;
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
	public int getSeqImage() {
		return seqImage;
	}
	public void setSeqImage(int seqImage) {
		this.seqImage = seqImage;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDateTimeRegister() {
		return dateTimeRegister;
	}
	public void setDateTimeRegister(Date dateTimeRegister) {
		this.dateTimeRegister = dateTimeRegister;
	}
	public Date getDateTimeUpdate() {
		return dateTimeUpdate;
	}
	public void setDateTimeUpdate(Date dateTimeUpdate) {
		this.dateTimeUpdate = dateTimeUpdate;
	}
	
	@Override
	public String toString() {
		return "GalleryVO [seq=" + seq + ", invSeq=" + invSeq + ", id=" + id + ", isDelete=" + isDelete + ", seqImage="
				+ seqImage + ", fullName=" + fullName + ", dateTimeRegister=" + dateTimeRegister + ", dateTimeUpdate="
				+ dateTimeUpdate + "]";
	}
}

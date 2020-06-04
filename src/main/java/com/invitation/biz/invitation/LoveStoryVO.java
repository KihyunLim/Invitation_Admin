package com.invitation.biz.invitation;

import java.util.Date;

public class LoveStoryVO {

	private int seq;
	private int invSeq;
	private String id;
	private Boolean isDelete;
	private String dateStory;
	private String title;
	private String content;
	private int seqImage;
	private String fullNameImg;
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
	public String getDateStory() {
		return dateStory;
	}
	public void setDateStory(String dateStory) {
		this.dateStory = dateStory;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSeqImage() {
		return seqImage;
	}
	public void setSeqImage(int seqImage) {
		this.seqImage = seqImage;
	}
	public String getFullNameImg() {
		return fullNameImg;
	}
	public void setFullNameImg(String fullNameImg) {
		this.fullNameImg = fullNameImg;
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
		return "LoveStoryVO [seq=" + seq + ", invSeq=" + invSeq + ", id=" + id + ", isDelete=" + isDelete
				+ ", dateStory=" + dateStory + ", title=" + title + ", content=" + content + ", seqImage=" + seqImage
				+ ", fullNameImg=" + fullNameImg + ", dateTimeRegister=" + dateTimeRegister + ", dateTimeUpdate="
				+ dateTimeUpdate + "]";
	}
}

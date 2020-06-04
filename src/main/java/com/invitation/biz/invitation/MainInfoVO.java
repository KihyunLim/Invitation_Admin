package com.invitation.biz.invitation;

import java.util.Date;

public class MainInfoVO {

	private int seq;
	private int invSeq;
	private String id;
	private String dateWedding;
	private String timeWedding;
	private String placeX;
	private String placeY;
	private String address;
	private String contentGroom;
	private String contentBride;
	private int seqImgMain;
	private String fullNameMain;
	private String useEachImage;
	private int seqImgGroom;
	private String fullNameGroom;
	private int seqImgBride;
	private String fullNameBride;
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
	public String getDateWedding() {
		return dateWedding;
	}
	public void setDateWedding(String dateWedding) {
		this.dateWedding = dateWedding;
	}
	public String getTimeWedding() {
		return timeWedding;
	}
	public void setTimeWedding(String timeWedding) {
		this.timeWedding = timeWedding;
	}
	public String getPlaceX() {
		return placeX;
	}
	public void setPlaceX(String placeX) {
		this.placeX = placeX;
	}
	public String getPlaceY() {
		return placeY;
	}
	public void setPlaceY(String placeY) {
		this.placeY = placeY;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContentGroom() {
		return contentGroom;
	}
	public void setContentGroom(String contentGroom) {
		this.contentGroom = contentGroom;
	}
	public String getContentBride() {
		return contentBride;
	}
	public void setContentBride(String contentBride) {
		this.contentBride = contentBride;
	}
	public int getSeqImgMain() {
		return seqImgMain;
	}
	public void setSeqImgMain(int seqImgMain) {
		this.seqImgMain = seqImgMain;
	}
	public String getFullNameMain() {
		return fullNameMain;
	}
	public void setFullNameMain(String fullNameMain) {
		this.fullNameMain = fullNameMain;
	}
	public String getUseEachImage() {
		return useEachImage;
	}
	public void setUseEachImage(String useEachImage) {
		this.useEachImage = useEachImage;
	}
	public int getSeqImgGroom() {
		return seqImgGroom;
	}
	public void setSeqImgGroom(int seqImgGroom) {
		this.seqImgGroom = seqImgGroom;
	}
	public String getFullNameGroom() {
		return fullNameGroom;
	}
	public void setFullNameGroom(String fullNameGroom) {
		this.fullNameGroom = fullNameGroom;
	}
	public int getSeqImgBride() {
		return seqImgBride;
	}
	public void setSeqImgBride(int seqImgBride) {
		this.seqImgBride = seqImgBride;
	}
	public String getFullNameBride() {
		return fullNameBride;
	}
	public void setFullNameBride(String fullNameBride) {
		this.fullNameBride = fullNameBride;
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
		return "MainInfoVO [seq=" + seq + ", invSeq=" + invSeq + ", id=" + id + ", dateWedding=" + dateWedding
				+ ", timeWedding=" + timeWedding + ", placeX=" + placeX + ", placeY=" + placeY + ", address=" + address
				+ ", contentGroom=" + contentGroom + ", contentBride=" + contentBride + ", seqImgMain=" + seqImgMain
				+ ", fullNameMain=" + fullNameMain + ", useEachImage=" + useEachImage + ", seqImgGroom=" + seqImgGroom
				+ ", fullNameGroom=" + fullNameGroom + ", seqImgBride=" + seqImgBride + ", fullNameBride="
				+ fullNameBride + ", dateTimeRegister=" + dateTimeRegister + ", dateTimeUpdate=" + dateTimeUpdate + "]";
	}
}

package com.invitation.biz.invitation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WhenWhereVO {

	private int seq;
	private int invSeq;
	private String id;
	private String flagPyebaek;
	private String dateWedding;
	private String timeWedding;
	private String placeX;
	private String placeY;
	private String address;
	private String title;
	private String content;
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
	public String getFlagPyebaek() {
		return flagPyebaek;
	}
	public void setFlagPyebaek(String flagPyebaek) {
		this.flagPyebaek = flagPyebaek;
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
		return "WhenWhereVO [seq=" + seq + ", invSeq=" + invSeq + ", id=" + id + ", flagPyebaek=" + flagPyebaek
				+ ", dateWedding=" + dateWedding + ", timeWedding=" + timeWedding + ", placeX=" + placeX + ", placeY="
				+ placeY + ", address=" + address + ", title=" + title + ", content=" + content + ", dateTimeRegister="
				+ dateTimeRegister + ", dateTimeUpdate=" + dateTimeUpdate + "]";
	}
	
}

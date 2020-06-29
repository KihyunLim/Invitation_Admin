package com.invitation.biz.invitation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GalleryVO {

	private int seq;
	private int invSeq;
	private int orderSeq;
	private String id;
	private Boolean isDelete;
	private int seqImage;
	private String fullName;
	private Date dateTimeRegister;
	private Date dateTimeUpdate;
	private List<GalleryVO> listGallery;
	
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
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
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
	public String getDateTimeRegister() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (dateTimeRegister == null) ? "" : simpleDateFormat.format(dateTimeRegister);
	}
	public void setDateTimeRegister(Date dateTimeRegister) {
		this.dateTimeRegister = dateTimeRegister;
	}
	public String getDateTimeUpdate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (dateTimeUpdate == null) ? "" : simpleDateFormat.format(dateTimeUpdate);
	}
	public void setDateTimeUpdate(Date dateTimeUpdate) {
		this.dateTimeUpdate = dateTimeUpdate;
	}
	public List<GalleryVO> getListGallery() {
		return listGallery;
	}
	public void setListGallery(List<GalleryVO> listGallery) {
		this.listGallery = listGallery;
	}
	
	@Override
	public String toString() {
		return "GalleryVO [seq=" + seq + ", invSeq=" + invSeq + ", orderSeq=" + orderSeq + ", id=" + id + ", isDelete="
				+ isDelete + ", seqImage=" + seqImage + ", fullName=" + fullName + ", dateTimeRegister="
				+ dateTimeRegister + ", dateTimeUpdate=" + dateTimeUpdate + ", listGallery=" + listGallery + "]";
	}
}

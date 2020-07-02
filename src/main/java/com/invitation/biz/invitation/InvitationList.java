package com.invitation.biz.invitation;

public class InvitationList {

	private int invSeq;
	private String id;
	private String name;
	private String visible;
	private String periodBegin;
	private String periodEnd;
	private String weddingDate;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getPeriodBegin() {
		return periodBegin;
	}
	public void setPeriodBegin(String periodBegin) {
		this.periodBegin = periodBegin;
	}
	public String getPeriodEnd() {
		return periodEnd;
	}
	public void setPeriodEnd(String periodEnd) {
		this.periodEnd = periodEnd;
	}
	public String getWeddingDate() {
		return weddingDate;
	}
	public void setWeddingDate(String weddingDate) {
		this.weddingDate = weddingDate;
	}
	
	@Override
	public String toString() {
		return "InvitationList [invSeq=" + invSeq + ", id=" + id + ", name=" + name + ", visible=" + visible
				+ ", periodBegin=" + periodBegin + ", periodEnd=" + periodEnd + ", weddingDate=" + weddingDate + "]";
	}
}

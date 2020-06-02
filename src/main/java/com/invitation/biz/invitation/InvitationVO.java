package com.invitation.biz.invitation;

import java.util.Date;

public class InvitationVO {

	private String id;
	private String name;
	private String visible;
	private String periodBegin;
	private String periodEnd;
	private String formCode;
	private String useEachImage;
	private String useLS;
	private String usePyebaek;
	private String useG;
	private String useSM;
	private Date dateTimeRegister;
	private Date dateTimeUpdate;
	
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
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getUseEachImage() {
		return useEachImage;
	}
	public void setUseEachImage(String useEachImage) {
		this.useEachImage = useEachImage;
	}
	public String getUseLS() {
		return useLS;
	}
	public void setUseLS(String useLS) {
		this.useLS = useLS;
	}
	public String getUsePyebaek() {
		return usePyebaek;
	}
	public void setUsePyebaek(String usePyebaek) {
		this.usePyebaek = usePyebaek;
	}
	public String getUseG() {
		return useG;
	}
	public void setUseG(String useG) {
		this.useG = useG;
	}
	public String getUseSM() {
		return useSM;
	}
	public void setUseSM(String useSM) {
		this.useSM = useSM;
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
		return "InvitationVO [id=" + id + ", name=" + name + ", visible=" + visible + ", periodBegin=" + periodBegin
				+ ", periodEnd=" + periodEnd + ", formCode=" + formCode + ", useEachImage=" + useEachImage + ", useLS="
				+ useLS + ", usePyebaek=" + usePyebaek + ", useG=" + useG + ", useSM=" + useSM + ", dateTimeRegister="
				+ dateTimeRegister + ", dateTimeUpdate=" + dateTimeUpdate + "]";
	}
}

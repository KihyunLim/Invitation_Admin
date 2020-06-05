package com.invitation.biz.invitation;

import java.util.ArrayList;

public class SyntheticInvitationVO {

	private InvitationVO invitationVO;
	private MainInfoVO mainInfoVO;
	private ArrayList<LoveStoryVO> loveStoryVO;
	private ArrayList<WhenWhereVO> whenWhereVO;
	private ArrayList<GalleryVO> galleryVO;
	private ArrayList<SweetMessageVO> sweetMessageVO;
	
	public InvitationVO getInvitationVO() {
		return invitationVO;
	}
	public void setInvitationVO(InvitationVO invitationVO) {
		this.invitationVO = invitationVO;
	}
	public MainInfoVO getMainInfoVO() {
		return mainInfoVO;
	}
	public void setMainInfoVO(MainInfoVO mainInfoVO) {
		this.mainInfoVO = mainInfoVO;
	}
	public ArrayList<LoveStoryVO> getLoveStoryVO() {
		return loveStoryVO;
	}
	public void setLoveStoryVO(ArrayList<LoveStoryVO> loveStoryVO) {
		this.loveStoryVO = loveStoryVO;
	}
	public ArrayList<WhenWhereVO> getWhenWhereVO() {
		return whenWhereVO;
	}
	public void setWhenWhereVO(ArrayList<WhenWhereVO> whenWhereVO) {
		this.whenWhereVO = whenWhereVO;
	}
	public ArrayList<GalleryVO> getGalleryVO() {
		return galleryVO;
	}
	public void setGalleryVO(ArrayList<GalleryVO> galleryVO) {
		this.galleryVO = galleryVO;
	}
	public ArrayList<SweetMessageVO> getSweetMessageVO() {
		return sweetMessageVO;
	}
	public void setSweetMessageVO(ArrayList<SweetMessageVO> sweetMessageVO) {
		this.sweetMessageVO = sweetMessageVO;
	}
	
	@Override
	public String toString() {
		return "SyntheticInvitationVO [invitationVO=" + invitationVO + ", mainInfoVO=" + mainInfoVO + ", loveStoryVO="
				+ loveStoryVO + ", whenWhereVO=" + whenWhereVO + ", galleryVO=" + galleryVO + ", sweetMessageVO="
				+ sweetMessageVO + "]";
	}
	
}

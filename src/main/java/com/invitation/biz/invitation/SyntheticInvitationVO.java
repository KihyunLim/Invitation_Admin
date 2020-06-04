package com.invitation.biz.invitation;

public class SyntheticInvitationVO {

	private InvitationVO invitationVO;
	private MainInfoVO mainInfoVO;
	// array<lovestory> ???? 가능???
	private LoveStoryVO loveStoryVO;

	public InvitationVO getInvitationVO() {
		return invitationVO;
	}
	public void setInvitationVO(InvitationVO invitationVO) {
		this.invitationVO = invitationVO;
	}
	
	@Override
	public String toString() {
		return "SyntheticInvitationVO [invitationVO=" + invitationVO + "]";
	}
}

package com.invitation.biz.invitation;

public class SyntheticInvitationVO {

	private InvitationVO invitationVO;

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

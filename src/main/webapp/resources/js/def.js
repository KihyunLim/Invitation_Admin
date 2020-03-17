/**
 * 
 */

var GO_TO_MAIN = "/admin/member/memberList.do";

var MAP_SIDEBAR = {
	"member" : {
		"isUnder" : false,
		"super" : "",
		"memberList.do" : "aMember"
	},
	"invitation"  : {
		"isUnder" : true,
		"super" : "aInvitation",
		"invitationList.do" : "aInvitation_List",
		"invitationDetail.do" : "aInvitation_Detail",
		"invitationAdd.do" : "aInvitation_Add"
	}
};

var DATATABLE = undefined;
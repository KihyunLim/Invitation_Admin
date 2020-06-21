/**
 * 
 */

var GO_TO_MAIN = "/admin/member/memberList.do";

// upload file info
var IMG_SRC = "/admin/common/fileDisplay.do?fileName=";
var IMG_SRC_ICO = "resources/upload/files/fileIcon.png";
var ORIGINAL_FILE_URL = "/admin/common/fileDisplay.do?fileName=";
var DEFAULT_IMG_SRC = "../css/img/uploadImage.png";

// modify url info
var URL = {
	invitation : "modifyInvitation.do",
	mi : "modifyMainInfo.do",
	ls : "modifyLoveStory.do",
	ww : "modifyWhenWhere.do",
	gallery : "modifyGallery.do",
	sm : "modifySweetMessage.do"
};

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
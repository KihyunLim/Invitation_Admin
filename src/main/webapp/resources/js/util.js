/**
 * 
 */

function setActiveSidebar() {
	$("#navSidebar a").removeClass("active");
	$("#navSidebar li").removeClass("menu-open");
	
	var arrPathName = window.location.pathname.split("/", 4);
	if(MAP_SIDEBAR[arrPathName[2]]["isUnder"]) {
		$("#" + MAP_SIDEBAR[arrPathName[2]]["super"])
			.addClass("active")
			.parent().addClass("menu-open");
	}
	$("#" + MAP_SIDEBAR[arrPathName[2]][arrPathName[3]]).addClass("active");
};

function utilGoToMain() {
	$.ajax({
		url : "member/memberList.do",
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "/nHttp error msg : " + msg);
		},
		success : function() {
		}
	});
};
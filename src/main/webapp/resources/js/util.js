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

function utilDataTable(targetId, addOption, total) {
	var option = {
		lengthChange : false,
		searching : false,
		ordering : false,
		autoWidth : false,
		paging : false,
	    info : true,
	    infoCallback : function(settings, start, end, max, total, pre) {
	    	return "총 겸색 결과 : " + total;
	    },
		data : [],
		columnDefs : []	
	};
	
	option = $.extend(true, option, addOption);
	
	if(total != undefined || typeof total != "number") {
		console.warn("utilDataTable >>>>> total");
	} else if(!(option.data instanceof "Array")) {
		console.warn("utilDataTable >>>>> data");
	} else if(!(option.columnDefs instanceof "Array") && option.columnDefs.length < 1) {
		console.warn("utilDataTable >>>>> columnDefs");
	} else if(targetId == undefined || document.getElementById(targetId) == null) {
		console.warn("utilDataTable >>>>> targetId");
	} else {
		$("#" + targetId).DataTable(option);
	}
};

function utilDataTablePaging($target, pageMaker) {
	// 클론해서 쓰는 걸로
	if($target == undefined) {
		
	} else if(pageMaker == undefined || typeof pageMaker != "object") {
		
	}
};
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
		return;
	} else if(!(option.data instanceof "Array")) {
		console.warn("utilDataTable >>>>> data");
		return;
	} else if(!(option.columnDefs instanceof "Array") && option.columnDefs.length < 1) {
		console.warn("utilDataTable >>>>> columnDefs");
		return;
	} else if(targetId == undefined || document.getElementById(targetId) == null) {
		console.warn("utilDataTable >>>>> targetId");
		return;
	}
	
	$("#" + targetId).DataTable(option);
};

function utilDataTablePaging($target, pageMaker) {
	// 클론해서 쓰는 걸로
	if($target == undefined) {
		console.warn("utilDataTable >>>>> $target");
		return;
	} else if(pageMaker == undefined || typeof pageMaker != "object") {
		console.warn("utilDataTable >>>>> pageMaker");
		return;
	}
	
	var liFirstPrevious = [
		{
			"class" : "paginate_button page-item first disabled",
			"id" : "tableMemberList_first",
			"child_a" : {
				"href" : "#",
				"aria-controls" : "tableMemberList",
				"aria-label" : "First",
				"data-dt-idx" : "0",
				"class" : "page-link"
			}
		},
		{
			"class" : "paginate_button page-item previous disabled",
			"id" : "tableMemberList_previous",
			"child_a" : {
				"href" : "#",
				"aria-controls" : "tableMemberList",
				"aria-label" : "First",
				"data-dt-idx" : "1",
				"class" : "page-link"
			}
		}
	], liPage = [
		{
			"class" : "paginate_button page-item active",
			"id" : "",
			"child_a" : {
				"href" : "#",
				"aria-controls" : "tableMemberList",
				"aria-label" : "",
				"data-dt-idx" : "4",
				"class" : "page-link"
			}
		}
	], liNextLast = [
		{
			"class" : "paginate_button page-item next disabled",
			"id" : "tableMemberList_next",
			"child_a" : {
				"href" : "#",
				"aria-controls" : "tableMemberList",
				"aria-label" : "First",
				"data-dt-idx" : "2",
				"class" : "page-link"
			}
		},
		{
			"class" : "paginate_button page-item last disabled",
			"id" : "tableMemberList_last",
			"child_a" : {
				"href" : "#",
				"aria-controls" : "tableMemberList",
				"aria-label" : "First",
				"data-dt-idx" : "3",
				"class" : "page-link"
			}
		}
	];
	
	/**
	 * 페이지 부분 반복문 돌리면서 클론한거에 위에 데이터 넣어주고 콘캣으로 배열 합해서 페이징 랜더
	 */
//	test = new Array;
//	test1 = [1, 2];
//	test2 = [3, 4];
//	test3 = [5, 6];
//
//	test = test1.concat(test2, test3);
};
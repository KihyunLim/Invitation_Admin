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
		language : {emptyTable : "조회 결과가 없습니다."},
		paging : false,
	    info : true,
	    infoCallback : function(settings, start, end, max, total_, pre) {
	    	return "총 겸색 결과 : " + total;
	    },
		data : [],
		columnDefs : []	
	};
	
	option = $.extend(true, option, addOption);
	
	if(total == undefined || typeof total != "number") {
		console.warn("utilDataTable >>>>> total");
		return;
	} else if(!(Array.isArray(option.data))) {
		console.warn("utilDataTable >>>>> data");
		return;
	} else if(!(Array.isArray(option.columnDefs)) && option.columnDefs.length < 1) {
		console.warn("utilDataTable >>>>> columnDefs");
		return;
	} else if(targetId == undefined || document.getElementById(targetId) == null) {
		console.warn("utilDataTable >>>>> targetId");
		return;
	}
	
	$("#" + targetId).DataTable(option);
};

function utilDataTableDestroy(targetId) {
	$("#" + targetId).DataTable().destroy();
};

function utilDataTablePaging(idTarget, idDataTable, pageMaker) {
	if(idTarget == undefined) {
		console.warn("utilDataTable >>>>> idTarget");
		return;
	} else if(idDataTable == undefined) {
		console.warn("utilDataTable >>>>> idDataTable");
		return;
	} else if(pageMaker == undefined || typeof pageMaker != "object") {
		console.warn("utilDataTable >>>>> pageMaker");
		return;
	}
	
	var $idTarget = $("#" + idTarget),
		pagePrevious = (pageMaker.startPage - 1) < 1 ? 1 : pageMaker.startPage - 1,
		pageLast = Math.ceil(pageMaker.totalCount/pageMaker.cri.perPageNum),
		pageNext = (pageMaker.endPage + 1) > pageLast ? pageLast : pageMaker.endPage + 1
		pageList = [], 
		liPage = [],
		liFirstPrevious = [
			{
				"class" : "paginate_button page-item first disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "First",
					"data-dt-idx" : "first",
					"class" : "page-link aPaging",
					"tabindex" : "1",
					"text" : "<<"
				}
			},
			{
				"class" : "paginate_button page-item previous disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "Previous",
					"data-dt-idx" : "previous",
					"class" : "page-link aPaging",
					"tabindex" : "",	// previous버튼의 페이지는 유동적으로 해야함
					"text" : "<"
				}
			}
		], liNextLast = [
			{
				"class" : "paginate_button page-item next disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "Next",
					"data-dt-idx" : "next",
					"class" : "page-link aPaging",
					"tabindex" : "",	// next버튼의 페이지는 유동적으로 해야함
					"text" : ">"
				}
			},
			{
				"class" : "paginate_button page-item last disabled",
				"child_a" : {
					"href" : "#",
					"aria-controls" : idDataTable,
					"aria-label" : "Last",
					"data-dt-idx" : "last",
					"class" : "page-link aPaging",
					"tabindex" : Math.ceil(pageMaker.totalCount/pageMaker.cri.perPageNum),
					"text" : ">>"
				}
			}
		];
	
	for(var index1 = pageMaker.startPage ; index1<= pageMaker.endPage ; index1++) {
		var active = pageMaker.cri.page == index1 ? " active" : "";
		
		liPage.push({
			"class" : "paginate_button page-item" + active,
			"child_a" : {
				"href" : "#",
				"aria-controls" : idDataTable,
				"aria-label" : "Page",
				"data-dt-idx" : index1,
				"class" : "page-link aPaging",
				"tabindex" : index1,
				"text" : index1
			}
		});
	}
	
	$idTarget.find("li:first").siblings().remove();
	$idTarget.find("li:first").show();
	
	pageList = liFirstPrevious.concat(liPage, liNextLast);
	pageList.forEach(function(item){
		var $liPage = $("#liRecord").clone();
		
		$liPage.attr({
			"class" : item["class"],
			"id" : item["id"]
		}).find("a").attr({
			"href" : item["child_a"]["href"],
			"aria-controls" : item["child_a"]["aria-controls"],
			"aria-label" : item["child_a"]["aria-label"],
			"data-dt-idx" : item["child_a"]["data-dt-idx"],
			"tabindex" : item["child_a"]["tabindex"],
			"class" : item["child_a"]["class"]
		}).text(item["child_a"]["text"]);
		
		$idTarget.find("ul").append($liPage);
	});
	
	$idTarget.find("li:first").hide();
	
	if(pageMaker.prev) {
		$idTarget.find(".first").removeClass("disabled");
		$idTarget.find(".previous").removeClass("disabled");
	} else {
		if(pageMaker.cri.page == pageMaker.startPage) {
			$idTarget.find(".first").addClass("disabled");
			$idTarget.find(".previous").addClass("disabled");
		} else {
			$idTarget.find(".first").removeClass("disabled");
			$idTarget.find(".previous").removeClass("disabled");
		}
	}
	
	if(pageMaker.next) {
		$idTarget.find(".next").removeClass("disabled");
		$idTarget.find(".last").removeClass("disabled");
	} else {
		if(pageMaker.cri.page == pageMaker.endPage) {
			$idTarget.find(".next").addClass("disabled");
			$idTarget.find(".last").addClass("disabled");
		} else {
			$idTarget.find(".next").removeClass("disabled");
			$idTarget.find(".last").removeClass("disabled");
		}
	}
};
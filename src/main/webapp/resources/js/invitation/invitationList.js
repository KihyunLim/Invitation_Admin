/**
 * 
 */

console.log("########## invitationList.js ##########");

var searchData = {
	id : "",
	name : "",
	weddingBegin : "",
	weddingEnd : "",
}

$(function(){
	setActiveSidebar();
	
	getInvitationList(1);
	
	$("#inputSearchWeddingDate").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	$("#btnSearch").on("click", function(){
		var searchWeddingDate = $("#inputSearchWeddingDate").val().split(" - "),
			searchWeddingBegin = (searchWeddingDate[0]).replace(/-/g, ""),
			searchWeddingEnd = (searchWeddingDate[1]).replace(/-/g, "");
		
		if(Number(searchWeddingBegin) > Number(searchWeddingEnd)) {
			alert("결혼일자를 확인해주세요.");
			return;
		}
		
		searchData.id = $("#inputSearchId").val();
		searchData.name = $("#inputSearchName").val();
		searchData.weddingBegin = searchWeddingBegin;
		searchData.weddingEnd = searchWeddingEnd;
		
		utilDataTableDestroy("tableInvitationList");
		getInvitationList(1);
	});
	
	$("#divPagingWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		var $this = $(this),
			tabindex = $this.attr("tabindex");
		
		if($this.data("dt-idx") == "next") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) + 1;
		} else if($this.data("dt-idx") == "previous") {
			tabindex = Number($(".active .aPing").attr("tabindex")) - 1;
		}
		
		utilDataTableDestroy("tableInvitationList");
		getInvitationList(Number(tabindex));
	});
});

function getInvitationList(pageItem) {
	var requestParam = {
		page : pageItem,
		searchId : searchData.id,
		searchName : searchData.name,
		searchWeddingBegin : searchData.weddingBegin,
		searchWeddingEnd : searchData.weddingEnd
	};
	
	$.ajax({
		url : "/admin/invitation/getInvitationList.do?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, sutatus, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			var option = {
				data : result.list,
				columnDefs : [{
					targets : 0,
					data : 'invSeq'
				}, {
					targets : 1,
					data : 'id'
				}, {
					targets : 2,
					data : 'name'
				}, {
					targets : 3,
					data : 'visible'
				}, {
					targets : 4,
					data : function(row, type, val, meta) {
						var periodBegin = row.periodBegin.substr(0,4)
						+ "-" + row.periodBegin.substr(4,2)
						+ "-" + row.periodBegin.substr(6,2)
						
						return periodBegin;
					}
				}, {
					targets : 5,
					data : function(row, type, val, meta) {
						var periodEnd = row.periodEnd.substr(0,4)
						+ "-" + row.periodEnd.substr(4,2)
						+ "-" + row.periodEnd.substr(6,2)
						
						return periodEnd;
					}
				}, {
					targets : 6,
					data : function(row, type, val, meta) {
						var weddingDate = row.weddingDate.substr(0,4)
						+ "-" + row.weddingDate.substr(4,2)
						+ "-" + row.weddingDate.substr(6,2)
						
						return weddingDate;
					}
				}, {
					targets : 7,
					data : function(row, type, val, meta) {
						return "<button type='button' class='btn btn-default btn-sm btnMoveDetail'>보기</button>";
					}
				}, {
					targets : 8,
					data : function(row, type, val, meta) {
						return "<button type='button' class='btn btn-default btn-sm btnPopInvitation'>이동</button>";
					}
				}],
				rowId : function(row) {
					return row.invSeq;
				}
			};
			
			utilDataTable("tableInvitationList", option, result.pageMaker.totalCount);
			utilDataTablePaging("divPagingWrap", "tableInvitationList", result.pageMaker);
		}
	});
}
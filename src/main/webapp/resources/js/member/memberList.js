/**
 * 
 */

console.log("########## memberList.js ##########");

var modifyId = "";

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
	
	$("#btnSearch").on("click", function(){
		utilDataTableDestroy("tableMemberList");
		getMemberList(1);
	});
	
	$("#modal-memberRegister").on("hide.bs.modal", function(e){
//		getMemberList(1);
	})
	$("#btnMemberRegister").on("click", function(){
		$("#modal-memberRegister").modal("show");
	});
	
	$("#modal-memberModify").on("show.bs.modal", function (e) {
//		getMemberInfo();
		console.log(modifyId);
	});
	$("#modal-memberModify").on("hide.bs.modal", function (e) {
		modifyId = "";
		
		console.log(modifyId);
	});
	$("#tableMemberList").on("dblclick", "tr", function(){
		modifyId = $(this).attr("id");
		
		$("#modal-memberModify").modal("show");
	});
	
	$("#divPagingWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		var $this = $(this),
			tabindex = $this.attr("tabindex");
		
		if($this.data("dt-idx") == "next") {
			tabindex = $this.parent().prev().find(".aPaging").attr("tabindex");
		}
		
		utilDataTableDestroy("tableMemberList");
		getMemberList(Number(tabindex));
	});
});

function getMemberList(pageItem) {
	var requestParam = {
			page : pageItem,
			searchCondition : "",
			searchKeyword : $("#inputKeyword").val().trim()
	};
	
	if(requestParam.searchKeyword != "") {
		requestParam.searchCondition = $("#selectCondition").val();
	}
	
	$.ajax({
		url : "/admin/member/getMemberList?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			var option = {
				data : result.list,
				columnDefs : [{
					targets : 0,
					defaultContent : '',
					data : null,
					className : 'select-checkbox'
				}, {
					targets : 1,
					data : 'id'
				}, {
					targets : 2,
					data : 'name'
				}, {
					targets : 3,
					data : 'phone'
				}, {
					targets : 4,
					data : function(row, type, val, meta) {
						var statusSee = '';
						
						if(row.statusSee == 'Y') {
							statusSee = '게시';
						} else if(row.statusSee == 'N') {
							statusSee = '비게시'; 
						} else {
							statusSee = '-';
						}
						
						return statusSee;
					}
				}],
				select : {
					style : 'os',
					selector : 'td:first-child'
				},
				rowId : function(row) {
					return row.id;
				}
			};
			
			utilDataTable("tableMemberList", option, result.pageMaker.totalCount);
			utilDataTablePaging("divPagingWrap", "tableMemberList", result.pageMaker);
		}
	});
};

function getMemberInfo() {
	$.ajax({
		url : "/admin/member/getMemberInfo?" + $.param({memberId : modifyId}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			
		}
	});
};
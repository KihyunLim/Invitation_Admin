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
	
	$("#modal-memberModify").on("show.bs.modal", function (e) {
		getMemberInfo();
	});
	$("#modal-memberModify").on("hide.bs.modal", function (e) {
		modifyId = "";
	});
	$("#tableMemberList").on("dblclick", "tr", function(){
		/**
		 * - 테이블 렌더할 때 데이터 속성으로 아이디 저장하고
		 * - 더블클릭하면 아이디 읽어와서 전역 변수에 저장하고
		 * 팝업창 열리면 저장된 아이디로 회원 정보 조회 하도록 ㄱ
		 */
		var modifyId = $(this).attr("id");
		
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
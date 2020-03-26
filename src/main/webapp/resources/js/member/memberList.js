/**
 * 
 */

console.log("########## memberList.js ##########");

var modifyId = "";
var isOverlapCheck = false;

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
	
	$("#btnSearch").on("click", function(){
		utilDataTableDestroy("tableMemberList");
		getMemberList(1);
	});
	
	$("#btnOverlapCheck").on("click", function(){
		var id = $("#inputRegisterId").val();
		
		if(id == "") {
			alert("ID를 입력해주세요.");
		} else if(id.length > 20) {
			alert("ID는 20자 이내로 입력해주세요.");
		} else {
			overlapCheck(id);
		}
	});
	
	$("#modal-memberRegister").on("hide.bs.modal", function(e){
		getMemberList(1);
	})
	$("#btnMemberRegister").on("click", function(){
		$("#modal-memberRegister").modal("show");
	});
	
	$("#btnRegister").on("click", function() {
		if(validateInfo()) {
			registerMember();
		}
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

function overlapCheck(id) {
	$.ajax({
		url : "/admin/member/getOverlapCheck?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			$("#btnRegister").prop("disabled", true);
			isOverlapCheck = false;
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				$("#btnRegister").prop("disabled", false);
				isOverlapCheck = true;
			} else {
				$("#btnRegister").prop("disabled", true);
				isOverlapCheck = false;
			}
			
			alert(result.resMessage);
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

function validateInfo() {
	if($("#inputRegisterId").val() == "") {
		alert("아이디를 입력해주세요");
		isOverlapCheck = false;
		return false;
	} else if(isOverlapChek == false) {
		alert("아이디 중복확인을 해주세요");
		return false;
	} else if($("#inputRegisterPassword").val() == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if($("#inputRegisterName").val() == "") {
		alert("이름을 입력해주세요");
		return false;
	} else if($("#inputRegisterPhone").val() == "") {
		alert("전화번호를 입력해주세요.");
		return false;
	} else {
		return true;
	}
};

function registerMember() {
	var data = {
		id : $("#inputRegisterId").val(),
		password : $("#inputRegisterPassword").val(),
		name : $("#inputRegisterName").val(),
		phone : $("#inputRegisterPhone").val()
	};
	
	$.ajax({
		url : "/admin/member/registerMember",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(data),
		contentType : "application/json",
		mimeType : "application/json",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				isOverlapCheck = false;
			} else {
				isOverlapCheck = true;
			}
			
			alert(result.resMessage);
			
			$("#modal-memberRegister").modal("hide");
		}
	})
};
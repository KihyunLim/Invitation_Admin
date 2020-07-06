/**
 * 
 */

console.log("########## memberList.js ##########");

var modifyTargetId = "",
	isOverlapCheck = false,
	overlapCheckedId = ""
	isSuccess = false;

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
	
	$("#btnSearch").on("click", function(){
		utilDataTableDestroy("tableMemberList");
		getMemberList(1);
	});
	
	$("#btnMemberDelete").on("click", function(){
		var $selected = $(".selected");
		
		if($selected.length == 1) {
			var selectedId = $selected.attr("id");
			
			deleteMember(selectedId);
		} else {
			alert("삭제할 회원을 1명 선택해주세요.");
		}
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
		$(".resetInput").val("");
		
		if(isSuccess) {
			isSuccess = false;
			
			utilDataTableDestroy("tableMemberList");
			getMemberList(1);
		}
	})
	$("#btnMemberRegister").on("click", function(){
		$("#modal-memberRegister").modal("show");
	});
	
	$("#btnRegist").on("click", function() {
		if(validateRegistInfo()) {
			registerMember();
		}
	});
	
	$("#modal-memberModify").on("show.bs.modal", function (e) {
		console.log("target ID : ", modifyTargetId);
		
		getMemberInfo();
	});
	$("#modal-memberModify").on("hide.bs.modal", function (e) {
		modifyTargetId = "";
		
		console.log(modifyTargetId);
		
		$("#btnModify").removeData("resMemberInfo");
		$(".resetInput").val("");
	});
	$("#tableMemberList").on("dblclick", "tr", function(){
		modifyTargetId = $(this).attr("id");
		
		$("#modal-memberModify").modal("show");
	});
	
	$("#btnModify").on("click", function() {
		if(validateModifyInfo()) {
			modifyMember();
		}
	});
	
	$("#divPagingWrap").on("click", ".aPaging", function(e){
		e.preventDefault();
		var $this = $(this),
			tabindex = $this.attr("tabindex");
		
		if($this.data("dt-idx") == "next") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) + 1;
		} else if($this.data("dt-idx") == "previous") {
			tabindex = Number($(".active .aPaging").attr("tabindex")) - 1;
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
		url : "/admin/member/getMemberList.do?" + $.param(requestParam),
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
		url : "/admin/member/getOverlapCheck.do?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			isOverlapCheck = false;
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag || (result.resOverlapCheckedId != "")) {
				isOverlapCheck = true;
				overlapCheckedId = result.resOverlapCheckedId;
			} else {
				isOverlapCheck = false;
			}
			
			alert(result.resMessage);
		}
	});
};

function validateRegistInfo() {
	if($("#inputRegisterId").val() == "") {
		alert("아이디를 입력해주세요");
		isOverlapCheck = false;
		return false;
	} else if(isOverlapCheck == false) {
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
	if(overlapCheckedId != $("#inputRegisterId").val()) {
		overlapCheckedId = "";
		isOverlapCheck = false;
		
		alert("아이디가 변경되었습니다. 아이디 중복확인을 해주세요.");
		return false;
	}
	
	var data = {
		id : overlapCheckedId,
		password : $("#inputRegisterPassword").val(),
		name : $("#inputRegisterName").val(),
		phone : $("#inputRegisterPhone").val()
	};
	
	$.ajax({
		url : "/admin/member/registerMember.do",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(data),
		contentType : "application/json;charset=utf-8;",
		error : function(xhr, status, msg) {
			isOverlapCheck = true;
			isSuccess = false;
			
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				isOverlapCheck = false;
				overlapCheckedId = "";
				isSuccess = true;
				
				alert(result.resMessage);
				$("#modal-memberRegister").modal("hide");
			} else {
				isOverlapCheck = true;
				isSuccess = false;
				
				alert(result.resMessage);
			}
		}
	})
};

function getMemberInfo() {
	$.ajax({
		url : "/admin/member/" + modifyTargetId + ".do",
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag){
				$("#btnModify").data("resMemberInfo", result.resMemberInfo);
				$("#inputModifyId").val(result.resMemberInfo.id);
				$("#inputModifyPassword").val(result.resMemberInfo.password);
				$("#inputModifyName").val(result.resMemberInfo.name);
				$("#inputModifyPhone").val(result.resMemberInfo.phone);
				$("#inputModifyRegDate").val(result.resMemberInfo.registerDate);
				if(result.resMemberInfo.latestInvitationBegin != null && result.resMemberInfo.latestInvitationEnd != null) {
					$("#inputModifyLatestInvitation").val(result.resMemberInfo.latestInvitationBegin.substr(0,4) + "-"
							+ result.resMemberInfo.latestInvitationBegin.substr(4,2) + "-"
							+ result.resMemberInfo.latestInvitationBegin.substr(6,2)
							+ " ~ " 
							+ result.resMemberInfo.latestInvitationEnd.substr(0,4) + "-"
							+ result.resMemberInfo.latestInvitationEnd.substr(4,2) + "-"
							+ result.resMemberInfo.latestInvitationEnd.substr(6,2));
				}
			} else {
				alert(result.resMessage);
			}
		}
	});
};

function validateModifyInfo() {
	if($("#inputModifyPassword").val() == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else if($("#inputModifyName").val() == "") {
		alert("이름을 입력해주세요");
		return false;
	} else if($("#inputModifyPhone").val() == "") {
		alert("전화번호를 입력해주세요.");
		return false;
	} else {
		return true;
	}
};

function modifyMember() {
	var recordData = $("#btnModify").data("resMemberInfo"),
		data = {
			id : recordData.id,
			password : recordData.password,
			name : recordData.name,
			phone : recordData.phone
		},
		inputModifyPassword = $("#inputModifyPassword").val(),
		inputModifyName = $("#inputModifyName").val(),
		inputModifyPhone = $("#inputModifyPhone").val();
	
	if(recordData.password != inputModifyPassword) {
		data["password"] = inputModifyPassword;
	}
	if(recordData.name != inputModifyName) {
		data["name"] = inputModifyName;
	}
	if(recordData.phone != inputModifyPhone) {
		data["phone"] = inputModifyPhone;
	}
	
	$.ajax({
		url : "/admin/member/" + data.id + ".do",
		type : "PUT",
		dataType : "json",
		data : JSON.stringify(data),
		contentType : "application/json;charset=utf-8;",
		error : function(xhr, status, msg) {
			isSuccess = false;
			
			alert("status : ", status, "\nHttp error msg : ", msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				isSuccess = true;
				
				alert(result.resMessage);
				
				$("#btnModify").removeData("resMemberInfo");
				$(".resetInput").val("");
				
				getMemberInfo();
				
				utilDataTableDestroy("tableMemberList");
				getMemberList(Number($(".active .aPaging").attr("tabindex")));
			} else {
				isSuccess = false;
				
				alert(result.resMessage);
			}
		}
	});
};

function deleteMember(id) {
	$.ajax({
		url : "/admin/member/" + id + ".do",
		type : "DELETE",
		error : function(xhr, status, msg) {
			alert("status : ", status, "\nHttp error msg : ", msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				utilDataTableDestroy("tableMemberList");
				getMemberList(1);
			}
			
			alert(result.resMessage);
		}
	});
};
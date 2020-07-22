/**
 * 
 */

console.log("########## login.js ##########");

$(function(){
	checkSaveId();
	
	$("#btnLogin").on("click", function(e){
		e.stopPropagation();
		console.log("asdfasd");
		var id = $("#inputId").val(),
			password = $("#inputPassword").val();
		
		if(id == "" || password == "") {
			alert("아이디 혹은 비밀번호를 입력해주세요.");
			return;
		}
		
		var reqData = {
			id : id,
			password : password
		};
		
		$.ajax({
			url : "/admin/login/securityLogin.do",
			type : "POST",
			dataType : "json",
			data : JSON.stringify(reqData),
			contentType : "application/json",
			mimeType : "application/json",
			error : function(xhr, status, msg) {
				alert("status : " + status + "/nHttp error msg : " + msg);
			},
			success : function(result) {
				if(result.resFlag) {
					var flagSaveId = $("#checkboxSaveId").prop("checked") == true ? "Y" : "N";
					
					if(flagSaveId == "Y") {
						flagSaveId = localStorage.setItem("flagSaveId", flagSaveId);
						
						localStorage.setItem("saveId", reqData.id);
					} else {
						flagSaveId = localStorage.setItem("flagSaveId", flagSaveId);
						
						localStorage.removeItem("saveId");
					}
					
					window.location.href = GO_TO_MAIN;
				} else {
					alert(result.resMessage);
				}
			}
		})
	});
	
	$("#inputId, #inputPassword").on("keyup", function(e){
		if(e.keyCode == 13) {
			$("#btnLogin").trigger("click");
		}
	});
});

function checkSaveId() {
	if(localStorage.getItem("flagSaveId") == "Y") {
		var saveId = localStorage.getItem("saveId") || "";
		
		$("#checkboxSaveId").prop("checked", true);
		$("#inputId").val(saveId);
	}
}
/**
 * 
 */

console.log("########## login.js ##########");

$(function(){
	$("#btnLogin").on("click", function(){
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
			url : "/admin/login/login.do",
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
					window.location.href = GO_TO_MAIN;
				} else {
					alert(result.resMessage);
				}
			}
		})
	});
});
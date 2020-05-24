/**
 * 
 */

console.log("########## invitationAdd.js ##########");

var $btnGetAddress = "";

$(function(){
	setActiveSidebar();
	
	$("#btnMemberSearch").on("click", function(){
		var inputId = $("#inputId").val();
		
		if(inputId == "") {
			alert("아이디를 입력해주세요.");
		} else {
			getMemberInfo(inputId);
		}
	});
	
	$("#inputDatePeriod").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
		/*autoUpdateInput: false
		
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
		  });*/
	});
	
	$(".inputDateTime").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	$(".inputDateTime").on("apply.daterangepicker", function(){
		var $this = $(this);
		
		if($this.parents("#divHomeGroomBride").length == 1) {
			$("#inputDateTimeWedding_copy").val($this.val());
		}
	});
	
	$(".btnGetAddress").on("click", function(){
		$btnGetAddress = $(".btnGetAddress").index($(this));
		var pop = window.open("/admin/popup/jusoPopup.jsp", "pop", "scrollbars=yes, resizeable=yes");
	});
	
	$("#btnRegisterInvitation").on("click", function(){
		var result = validateData();
		
		if(result.resFlag) {
			console.log(result.resData);
		} else {
			alert(result.resMessage);
		}
	});
	
	$(".btnUploadFile").change(function(e){
		uploadFile($(this), function(res){
			renderFile(res);
		});
	});
	
	//------------------------------------------------------------------------------------------------------
	
	// 유동적으로 추가한것도 먹힐려나??
	$(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	$("#sectionContent").on("click", "[data-toggle='lightbox']", function(event) {
		event.preventDefault();
		
		$(this).ekkoLightbox({
			alwaysShowClose: true
		});
	});
});

function getMemberInfo(id) {
	$("#inputId").removeData("id");
	$("#inputName").val("");
	
	$.ajax({
		url : "/admin/invitation/getMemberInfo?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : ", status, "\nHttp error msg : ", msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				$("#inputId").data("id", result.resMemberInfo.id);
				$("#inputName").val(result.resMemberInfo.name);
			} else {
				alert(result.resMessage);
			}
		}
	});
}

function jusoCallBack(...res) {
	// res = ["서울특별시 중구 청구로 지하 77, 걍 써봄 (신당동)", "서울특별시 중구 청구로 지하 77", "걍 써봄", "(신당동)", "B 77, Cheonggu-ro, Jung-gu, Seoul", "서울특별시 중구 신당동 295-2 청구역 5,6호선", "04608", "1114016200", "111403101008", "1114016200102950002000001", "5,6호선", "청구역 5,6호선", "0", "서울특별시", "중구", "신당동", "", "청구로", "1", "77", "0", "0", "295", "2", "01", "957058.9352199801", "1951330.378632207"]
	console.log(res);
	
	if($btnGetAddress == 0) {
		$(".inputAddrWeddingPlace").val(res[0]);
		$("#infoWeddingPlace").data("infoWeddingPlace", {
			addr : res[0],
			placeX : res[25],
			placeY : res[26]
		});
	} else {
		$("#inputAddrPyebaek").val(res[0]).data("infoPyebaek", {
			addr : res[0],
			placeX : res[25],
			placeY : res[26]
		});
	}
}

function validateData() {
	var result = {
			resFlag : true,
			resData : {
				id : $("#inputId").data("id") || "",
				invitationBegin : ($("#inputDatePeriod").val().split(" - "))[0] || "",
				invitationEnd : ($("#inputDatePeriod").val().split(" - "))[1] || "",
				openYN : $("input[name=checkboxOpenYN]").prop("checked") == true ? "N" : "Y",
				dateTimeWedding : $("#inputDateTimeWedding").val() || "",
				weddingPlace : $("#infoWeddingPlace").data("infoWeddingPlace") || "",
				contentGroom : $("#contentGroom").val() || "",
				contentBride : $("#contentBride").val() || ""
			},
			resMessage : ""
	};
	
	if(result.resData.id == "") {
		result.resFlag = false;
		result.resMessage = "아이디를 입력해주세요."
	} else if(result.resData.invitationBegin == "" || result.resData.invitationEnd == "") {
		result.resFlag = false;
		result.resMessage = "게시기간을 선택해주세요.";
	} else if(result.resData.dateTimeWedding == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 일자를 선택해주세요.";
	} else if(result.resData.weddingPlace == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 장소를 입력해주세요.";
	} else if(result.resData.contentGroom.length >= 500) {
		result.resFlag = false;
		result.resMessage = "신랑 간단소개는 500자 이내로 입력해주세요.";
	} else if(result.resData.contentBride.length >= 500) {
		result.resFlag = false;
		result.resMessage = "신부 간단소개는 500자 이내로 입력해주세요.";
	}
	
	return result;
}

function renderFile(data) {
	console.log(data);
}
/**
 * 
 */

console.log("########## invitationAdd.js ##########");

var $btnGetAddress = "",
	$tableRecordLoveStory = undefined;

$(function(){
	setActiveSidebar();
	setClone();
	
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
			registerInvitation(result.resData);
		} else {
			alert(result.resMessage);
		}
	});
	
	$("#sectionContent").on("change", ".btnUploadFile", function(){
		var $this = $(this);
		
		uploadFile($(this), function(res){
			$this.prev().hide();
			$this.hide()
					.parents(".wrapUploadFile")
						.find("a").attr("href", res.originalFileUrl).data("title", res.originalFileName)
						.find("img").attr("src", res.imgSrc).data("fullName", res.fullName);
			$this.next().show();
		});
	});
	
	$("#sectionContent").on("click", "[data-toggle='lightbox']", function(event) {
		event.preventDefault();
		var $this = $(this);

		if($this.attr("href") != "" && $this.find("img").attr("src") != DEFAULT_IMG_SRC) {
			$this.ekkoLightbox({
				alwaysShowClose: true
			});
		}
	});
	
	$(".btnDeleteImage").on("click", function(){
		var $wrapUploadFile = $(this).parents(".wrapUploadFile");
		
		$wrapUploadFile.find("a").attr("href", "").removeData("title", "")
								.find("img").attr("src", DEFAULT_IMG_SRC).removeData("fullName");
		$wrapUploadFile.find(".btnUploadFile").val("").show()
														.prev().show()
														.next().next().hide();
	});
	
	$("#btnAddLS").on("click", function(){
		cloneLoveStory();
	});
	
	$("#wrapListLS").on("click", ".btnRemoveLS", function(){
		$(this).parents(".itemLoveStory").remove();
		
		resetTagId();
	});
});

function setClone() {
	$tableRecordLoveStory = $("#tableRecordLoveStory").clone();
	$("#tableRecordLoveStory").remove();
	$("#wrapListLS").sortable();
}

function cloneLoveStory() {
	var $itemLoveStory = $tableRecordLoveStory.clone();
	
	$itemLoveStory.find(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	$("#wrapListLS").append($itemLoveStory);
	
	resetTagId();
}

function resetTagId() {
	var index = 1;
	
	$(".itemLoveStory").each(function(i){
		$(this).find(".btnUploadFile").attr("id", "imgLS" + String(index))
					.prev().attr("for", "imgLS" + String(index));
		
		index = index + 1;
	});
}

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
	var invitation = {}, mi = {}, ls = {}, ww = {}, gallery = {}, sm = {},
		result = {
			resFlag : true,
			resData : {},
			resMessage : ""
		};
	
	invitation.id = $("#inputId").data("id") || "";
	if(invitation.id == "") {
		result.resFlag = false;
		result.resMessage = "아이디를 확인해주세요.";
		return result;
	}
	
	var datePeriod = $("#inputDatePeriod").val().split(" - "),
		today = getFormattedDate(new Date());
	invitation.periodBegin = (datePeriod[0]).replace(/-/g, "");
	invitation.periodEnd = (datePeriod[1]).replace(/-/g, "");
	if(Number(today) > Number(invitation.periodEnd)) {
		result.resFlag = false;
		result.resMessage = "게시 기간을 확인해주세요.";
		return result;
	}
	
	invitation.visible = $("input[name=checkboxVisibleYN]").prop("checked") == true ? "N" : "Y";
	invitation.formCode = "hookup";
	invitation.useEachImage = $("input[name=checkboxEachImgYN]").prop("checked") == true ? "Y" : "N";
	invitation.useLS = $("input[name=checkboxUseLSYN]").prop("checked") == true ? "Y" : "N";
	invitation.usePyebaek = $("input[name=checkboxDoPyebaek]").prop("checked") == true ? "Y" : "N";
	invitation.useG = $("input[name=checkboxUseG]").prop("checked") == true ? "Y" : "N";
	invitation.useSM = $("input[name=checkboxUseSM]").prop("checked") == true ? "Y" : "N";
	
	var dateTimeWedding = $("#inputDateTimeWedding").val() || "";
	dateTimeWedding = dateTimeWedding.split(" ");
	mi.dateWedding = (dateTimeWedding[0]).replace(/-/g, "");
	mi.timeWedding = (dateTimeWedding[1]).replace(/:/g, "");
	if(Number(mi.dateWedding) < Number(invitation.periodBegin) || Number(mi.dateWedding) > Number(invitation.periodEnd)) {
		result.resFlag = false;
		result.resMessage = "결혼 일자를 확인해주세요.";
		return result;
	}
	
	var infoAddr = $("#infoWeddingPlace").data("infoWeddingPlace") || "";
	mi.address = infoAddr.addr || "";
	mi.placeX = infoAddr.placeX || "";
	mi.placeY = infoAddr.placeY || "";
	if(infoAddr == undefined || infoAddr == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 장소를 확인해주세요.";
		return result;
	}
	if(mi.address == "" || mi.placeX == "" || mi.placeY == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 장소를 확인해주세요.";
		return result;
	}
	
	mi.contentGroom = $("#contentGroom").val();
	mi.contentBride = $("#contentBride").val();
	
	mi.fullNameMain = $("#imgMI1").parents(".wrapUploadFile").find("img").data("fullName");
	if(mi.fullNameMain == undefined) {
		result.resFlag = false;
		result.resMessage = "메인 사진을 확인해주세요.";
		return result;
	}
	
	mi.fullNameGroom = $("#imgMI2").parents(".wrapUploadFile").find("img").data("fullName");
	mi.fullNameBride = $("#imgMI3").parents(".wrapUploadFile").find("img").data("fullName");
	mi.useEachImage = invitation.useEachImage;
	if(mi.useEachImage == "Y") {
		if(mi.fullNameGroom == undefined) {
			result.resFlag = false;
			result.resMessage = "신랑 사진을 확인해주세요.";
			return result;
		}
		
		if(mi.fullNameBride == undefined) {
			result.resFlag = false;
			result.resMessage = "신부 사진을 확인해주세요.";
			return result;
		}
	} else {
		mi.fullNameGroom = "";
		mi.fullNameBride = "";
	}
	
	ls.listLS = [];
	var noImageCount = 0;
	$(".itemLoveStory").each(function(index){
		var $this = $(this),
			fullNameImg = $this.find("img").data("fullName") || "";
		
		if(fullNameImg == "") {
			noImageCount++;
		}
		
		ls.listLS.push({
			dateStory : ($this.find(".inputDateLoveStory").val()).replace(/-/g, ""),
			title : $this.find(".inputTitleLS").val(),
			content : $this.find(".inputContentLS").val(),
			fullNameImg : fullNameImg ,
			orderSeq : index + 1
		});
	});
	if(noImageCount > 0){
		result.resFlag = false;
		result.resMessage = "Love Story에 사진을 확인해주세요.";
		return result;
	} else if(invitation.useLS == "Y" && ls.listLS.length < 1) {
		result.resFlag = false;
		result.resMessage = "Love Story에 사진을 확인해주세요.";
		return result;
	} else if(invitation.useLS == "N" && ls.listLS.length > 0) {
		result.resFlag = false;
		result.resMessage = "Love Story의 사용 여부를 확인해주세요.";
		return result;
	}
	
	ww.listWW = [];
	ww.listWW.push({
		flagPyebaek : "N",
		dateWedding : mi.dateWedding,
		timeWedding : mi.timeWedding,
		address : mi.address,
		placeX : mi.placeX,
		placeY : mi.placeY,
		title : $("#inputTitleWeddingWW").val(),
		content : $("#inputContentWeddingWW").val()
	});
	if(invitation.usePyebaek == "Y") {
		var dateTimePyebaek = $("#inputDatePyebaek").val() || "";
		dateTimePyebaek = dateTimePyebaek.split(" ");
		
		var infoAddrPyebaek = $("#inputAddrPyebaek").data("infoPyebaek") || "";
		
		if(infoAddrPyebaek == undefined || infoAddrPyebaek == "") {
			result.resFlag = false;
			result.resMessage = "폐백 장소를 확인해주세요.";
			return result;
		}
		if(infoAddrPyebaek.address == "" || infoAddrPyebaek.placeX == "" || infoAddrPyebaek.placeY == "") {
			result.resFlag = false;
			result.resMessage = "폐백 장소를 확인해주세요.";
			return result;
		}
		
		ww.listWW.push({
			flagPyebaek : "Y",
			dateWedding : (dateTimePyebaek[0]).replace(/-/g, ""),
			timeWedding : (dateTimePyebaek[1]).replace(/:/g, ""),
			address : infoAddrPyebaek.addr || "",
			placeX : infoAddrPyebaek.placeX || "",
			placeY : infoAddrPyebaek.placeY || "",
			title : $("#inputTitlePyebaekWW").val(),
			content : $("#inputContentPyebaekWW").val()
		});
	}
	
	gallery.listG = [];
	if(invitation.useG == "Y") {
		$("#tableGallery").find(".wrapUploadFile").each(function(index){
			var fullName = $(this).find("img").data("fullName") || "";
			
			if(fullName != "") {
				gallery.listG.push({fullName : fullName, orderSeq : index + 1});
			}
		});
		
		if(gallery.listG.length < 1) {
			result.resFlag = false;
			result.resMessage = "Gallery에 사진을 확인해주세요.";
			return result;
		}
	}
	
	result.resData = {
		invitationVO : invitation,
		mainInfoVO : mi,
		loveStoryVO : ls.listLS,
		whenWhereVO : ww.listWW,
		galleryVO : gallery.listG
	}
	
	return result;
}

function registerInvitation(data) {
	$.ajax({
		url : "/admin/invitation/registerInvitation.do",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(data),
		contentType : "application/json;charset=utf-8;",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			if(result.resFlag) {
				alert("청첩장 추가가 완료 되었습니다.");
				location.reload();
			} else {
				alert(result.resMessage);
			}
		}
	});
}
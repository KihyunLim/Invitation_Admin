/**
 * 
 */

console.log("########## invitationDetail.js ##########");

var $btnGetAddress = "",
	$tableRecordLoveStory = undefined,
	$divRecordMemberInvitation = undefined,
	syntheticInvitation = undefined;

$(function(){
	setActiveSidebar();
	setClone();
	
	$("#btnMemberSearch").on("click", function(){
		var inputId = $("#inputId").val();
		
		if(inputId == "") {
			alert("아이디를 입력해주세요.");
		} else {
			$("#modal-invitationList").modal("show");
		}
	});
	
	$("#modal-invitationList").on("show.bs.modal", function (e) {
		var inputId = $("#inputId").val();
		
		getMemberInvitationInfo(inputId);
	});
	$("#modal-invitationList").on("hide.bs.modal", function (e) {
	});
	
	$("#btnChoose").on("click", function(){
		var invSeq = $("input[name=radioInvitation]:checked").data("invSeq");
		
		if(invSeq != undefined && invSeq != "") {
			getSyntheticInvitation(invSeq);
		}
	});
	
	$("#inputDatePeriod").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	$(".inputDateTime").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
	$(".inputDateTime").on("show.daterangepicker", function(){
		var timeWedding = ($(this).val().split(" "))[1],
			timeHour = String(Number((timeWedding.split(":"))[0])),
			timeMinute = String(Number((timeWedding.split(":"))[1])),
			$daterangepicker = $(".daterangepicker[style*='display: block'");
		
		$daterangepicker.find(".hourselect").val(timeHour);
		$daterangepicker.find(".minuteselect").val(timeMinute);
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
	
	$("#sectionContent").on("change", ".btnUploadFile", function(){
		var $this = $(this);
		
		uploadFile($(this), function(res){
			$this.prev().hide();
			$this.hide()
					.parents(".wrapUploadFile")
						.find("a").attr("href", res.originalFileUrl).data("title", res.originalFileName)
						.find("img").attr("src", res.imgSrc).data("fullName", res.fullName).removeData("seqImage");
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
	
	$("#sectionContent").on("click", ".btnDeleteImage", function(){
		var $wrapUploadFile = $(this).parents(".wrapUploadFile");
		
		$wrapUploadFile.find("a").attr("href", "").removeData("title", "")
								.find("img").attr("src", DEFAULT_IMG_SRC)
									.removeData("fullName")
									.removeData("seqImage")
									.removeData("seq");
		$wrapUploadFile.find(".btnUploadFile").val("").show()
								.prev().show()
								.next().next().hide();
	});
	
	$("#btnAddLS").on("click", function(){
		cloneLoveStory(false);
	});
	
	$("#wrapListLS").on("click", ".btnRemoveLS", function(){
		$(this).parents(".itemLoveStory").remove();
		
		resetTagId();
	});
	
	$("#btnExcelDownload").on("click", function(){
		excelDownload(syntheticInvitation.invitationVO.seq);
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
		
		utilDataTableDestroy("tableSweetMessageList");
		getSweetMessageList(Number(tabindex));
	});
	
	$("#tableSweetMessageList").on("click", ".btnSweetMessageDelete", function(){
		deleteSweetMessage($(this).parents("tr").attr("id"), $(this).data("statedata"));
	});
	
	$(".btnModifyInvitation").on("click", function(){
		var result = "";
		
		switch($(this).data("category")) {
		case "invitation" : 
			result = validModifyInvitation();
			break;
		case "mi" : 
			result = validModifyMainInfo();
			break;
		case "ls" : 
			result = validModifyLoveStory();
			break;
		case "ww" : 
			result = validModifyWhenWhere();
			break;
		case "gallery" : 
			result = validModifyGallery();
			break;
		case "sm" : 
			result = validModifySweetMessage();
			break;
		}
		
		if(result.resFlag) {
			modifyInvitationInfo(result);
		} else {
			alert(result.resMessage);
		}
	});
});

function setClone() {
	$tableRecordLoveStory = $("#tableRecordLoveStory").clone();
	$("#tableRecordLoveStory").remove();
	$("#wrapListLS").sortable();
	
	$divRecordMemberInvitation = $("#divRecordMemberInvitation").clone();
	$("divRecordMemberInvitation").remove();
}

function cloneLoveStory(isSet, data) {
	var $itemLoveStory = $tableRecordLoveStory.clone();
	
	$itemLoveStory.find(".inputDateLoveStory").daterangepicker({
		singleDatePicker : true,
		locale : {
			format : "YYYY-MM-DD"
		}
	});
	
	if(isSet) {
		var fileInfo = setFileInfo(data.fullNameImg),
			$btnUploadFile = $itemLoveStory.find(".btnUploadFile");
		
		if(fileInfo.isEmptyImgInfo == false) {
			$btnUploadFile.prev().hide();
			$btnUploadFile.hide()
				.parents(".wrapUploadFile")
				.find("a").attr("href", fileInfo.originalFileUrl).data("title", fileInfo.originalFileName)
				.find("img").attr("src", fileInfo.imgSrc)
					.data("fullName", fileInfo.fullName)
					.data("seqImage", data.seqImage)
					.data("seq", data.seq);
			$btnUploadFile.next().show();
		}
		
		$itemLoveStory.find(".inputDateLoveStory").data("daterangepicker").setStartDate(data.dateStory);
		$itemLoveStory.find(".inputDateLoveStory").data("daterangepicker").setEndDate(data.dateStory);
		$itemLoveStory.find(".inputTitleLS").val(data.title);
		$itemLoveStory.find(".inputContentLS").val(data.content);
	}
	
	$("#wrapListLS").append($itemLoveStory);
	
	resetTagId();
}

function cloneMemberInvitation(data) {
	var $itemMemberInvitation = $divRecordMemberInvitation.clone();
	
	$itemMemberInvitation.find("input[name=radioInvitation]").data("invSeq", data.invSeq);
	$itemMemberInvitation.find(".spanInvitationSeq").text(data.invSeq);
	$itemMemberInvitation.find(".spanInvitationDateWedding").text(
			data.dateWedding.substr(0,4) + " - " +
			data.dateWedding.substr(4,2) + " - " +
			data.dateWedding.substr(6,2) + " " +
			data.timeWedding.substr(0,2) + " : " +
			data.timeWedding.substr(2,2));
	
	$("#divMemberInvitationWrap").append($itemMemberInvitation);
}

function resetTagId() {
	var index = 1;
	
	$(".itemLoveStory").each(function(i){
		$(this).find(".btnUploadFile").attr("id", "imgLS" + String(index))
					.prev().attr("for", "imgLS" + String(index));
		
		index = index + 1;
	});
}

function getMemberInvitationInfo(id) {
	$("#inputId").removeData("id");
	$("#inputName").val("");
	
	$.ajax({
		url : "/admin/invitation/getMemberInvitation.do?" + $.param({id : id}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			if(result.resFlag) {
				$("#inputId").data("id", result.resMemberInvitation.memberInfo.id);
				$("#inputName").val(result.resMemberInvitation.memberInfo.name);
				$("#divMemberInvitationWrap").empty();
				
				for(var i = 0 ; i < result.resMemberInvitation.memberInvitationList.length ; i++) {
					cloneMemberInvitation(result.resMemberInvitation.memberInvitationList[i]);
				}
			} else {
				$("#inputId").removeData("id");
				$("#inputName").val("");
				$("#divMemberInvitationWrap").empty();
				
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

function getSyntheticInvitation(invSeq) {
	$.ajax({
		url : "/admin/invitation/getSyntheticInvitation.do?" + $.param({invSeq : invSeq}),
		type : "GET",
		error :function(xhr, status, msg) {
			alert("status : " + satus + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				syntheticInvitation = result.resSyntheticInvitation;
				renderSyntheticInvitation(syntheticInvitation, result.pageMaker);
				$("#modal-invitationList").modal("hide");
			} else {
				alert(result.resMessage);
				$("#modal-invitationList").modal("hide");
			}
		}
	});
}

function renderSyntheticInvitation(data, pageMaker) {
	resetRender();
	
	$("#inputId").data("id", data.invitationVO.id);
	$("#inputName").val(data.invitationVO.name);
	$("#inputDatePeriod").data("daterangepicker").setStartDate(data.invitationVO.periodBegin);
	$("#inputDatePeriod").data("daterangepicker").setEndDate(data.invitationVO.periodEnd);
	$("input[name=checkboxVisibleYN]").prop("checked", data.invitationVO.visible == "Y" ? false : true);
	
	var dateTimeWedding1 = data.mainInfoVO.dateWedding.substr(0,4)
									+ "-" + data.mainInfoVO.dateWedding.substr(4,2)
									+ "-" + data.mainInfoVO.dateWedding.substr(6,2)
									+ " " + data.mainInfoVO.timeWedding.substr(0,2)
									+ ":" + data.mainInfoVO.timeWedding.substr(2,2);
	$("#inputDateTimeWedding").data("daterangepicker").setStartDate(data.mainInfoVO.dateWedding);
	$("#inputDateTimeWedding").data("daterangepicker").setEndDate(data.mainInfoVO.dateWedding);
	$("#inputDateTimeWedding").val(dateTimeWedding1);
	$(".inputAddrWeddingPlace").val(data.mainInfoVO.address);
	$("#infoWeddingPlace").data("infoWeddingPlace", {
		addr : data.mainInfoVO.address,
		placeX : data.mainInfoVO.placeX,
		placeY : data.mainInfoVO.placeY
	});
	$("#contentGroom").val(data.mainInfoVO.contentGroom);
	$("#contentBride").val(data.mainInfoVO.contentBride);
	var fileInfo1 = setFileInfo(data.mainInfoVO.fullNameMain),
		$imgMI1 = $("#imgMI1");
	if(fileInfo1.isEmptyImgInfo == false) {
		$imgMI1.prev().hide();
		$imgMI1.hide()
			.parents(".wrapUploadFile")
			.find("a").attr("href", fileInfo1.originalFileUrl).data("title", fileInfo1.originalFileName)
			.find("img").attr("src", fileInfo1.imgSrc).data("fullName", fileInfo1.fullName).data("seqImage", data.mainInfoVO.seqImgMain);
		$imgMI1.next().show();
	}
	$("input[name=checkboxEachImgYN]").prop("checked", data.mainInfoVO.useEachImage == "Y" ? true : false);
	var fileInfo2 = setFileInfo(data.mainInfoVO.fullNameGroom),
		$imgMI2 = $("#imgMI2");
	if(fileInfo2.isEmptyImgInfo == false) {
		$imgMI2.prev().hide();
		$imgMI2.hide()
			.parents(".wrapUploadFile")
			.find("a").attr("href", fileInfo2.originalFileUrl).data("title", fileInfo2.originalFileName)
			.find("img").attr("src", fileInfo2.imgSrc).data("fullName", fileInfo2.fullName).data("seqImage", data.mainInfoVO.seqImgGroom);
		$imgMI2.next().show();
	}
	var fileInfo3 = setFileInfo(data.mainInfoVO.fullNameBride),
		$imgMI3 = $("#imgMI3");
	if(fileInfo3.isEmptyImgInfo == false) {
		$imgMI3.prev().hide();
		$imgMI3.hide()
			.parents(".wrapUploadFile")
			.find("a").attr("href", fileInfo3.originalFileUrl).data("title", fileInfo3.originalFileName)
			.find("img").attr("src", fileInfo3.imgSrc).data("fullName", fileInfo3.fullName).data("seqImage", data.mainInfoVO.seqImgBride);
		$imgMI3.next().show();
	}
	
	$("input[name=checkboxUseLSYN]").prop("checked", data.invitationVO.useLS == "Y" ? true : false);
	data.loveStoryVO.forEach(function(item){
		cloneLoveStory(true, item);
	});
	
	$("#inputDateTimeWedding_copy").data("seq", data.whenWhereVO[0].seq).data("invSeq", data.whenWhereVO[0].invSeq);
	$("#inputDateTimeWedding_copy").val(dateTimeWedding1);
	$("#inputTitleWeddingWW").val(data.whenWhereVO[0].title);
	$("#inputContentWeddingWW").val(data.whenWhereVO[0].content);
	if(data.whenWhereVO.length == 2) {
		$("input[name=checkboxDoPyebaek]").prop("checked", data.whenWhereVO[1].flagPyebaek == "Y" ? true : false);
		$("#inputDatePyebaek").data("seq", data.whenWhereVO[1].seq).data("invSeq", data.whenWhereVO[1].invSeq);
		var dateTimeWedding2 = data.whenWhereVO[1].dateWedding.substr(0,4)
											+ "-" + data.whenWhereVO[1].dateWedding.substr(4,2)
											+ "-" + data.whenWhereVO[1].dateWedding.substr(6,2)
											+ " " + data.whenWhereVO[1].timeWedding.substr(0,2)
											+ ":" + data.whenWhereVO[1].timeWedding.substr(2,2);
		$("#inputDatePyebaek").data("daterangepicker").setStartDate(data.whenWhereVO[1].dateWedding);
		$("#inputDatePyebaek").data("daterangepicker").setEndDate(data.whenWhereVO[1].dateWedding);
		$("#inputDatePyebaek").val(dateTimeWedding2);
		$("#inputAddrPyebaek").val(data.whenWhereVO[1].address);
		$("#inputAddrPyebaek").data("infoPyebaek", {
			addr : data.whenWhereVO[1].address,
			placeX : data.whenWhereVO[1].placeX,
			placeY : data.whenWhereVO[1].placeY
		});
		$("#inputTitlePyebaekWW").val(data.whenWhereVO[1].title);
		$("#inputContentPyebaekWW").val(data.whenWhereVO[1].content);
	}
	
	$("input[name=checkboxUseG]").prop("checked", data.invitationVO.useG == "Y" ? true : false);
	data.galleryVO.forEach(function(item, index){
		var fileInfo4 = setFileInfo(item.fullName),
			$btnUploadFile = $("#tableGallery").find(".btnUploadFile:eq(" + index + ")");
		if(fileInfo4.isEmptyImgInfo == false) {
			$btnUploadFile.prev().hide();
			$btnUploadFile.hide()
				.parents(".wrapUploadFile")
				.find("a").attr("href", fileInfo4.originalFileUrl).data("title", fileInfo4.originalFileName)
				.find("img").attr("src", fileInfo4.imgSrc).data("fullName", fileInfo4.fullName)
																	.data("seq", item.seq)
																	.data("seqImage", item.seqImage);
			$btnUploadFile.next().show();
		}
	});
	
	$("input[name=checkboxUseSM]").prop("checked", data.invitationVO.useSM == "Y" ? true : false);
	utilDataTableDestroy("tableSweetMessageList");
	renderSweetMessageList(data.sweetMessageVO, pageMaker);
}

function getSweetMessageList(pageItem) {
	var requestParam = {
			page : pageItem,
			invSeq : syntheticInvitation.invitationVO.seq
	};
	
	$.ajax({
		url : "/admin/invitation/getSweetMessageList.do?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			renderSweetMessageList(result.resSweetMessageList, result.pageMaker);
		}
	});
}

function renderSweetMessageList(list, pageMaker) {
	var option = {
			data : list,
			columnDefs : [{
				targets : 0,
				data : 'seq'
			}, {
				targets : 1,
				data : 'dateTimeUpdate'
			}, {
				targets : 2,
				data : 'registerName'
			}, {
				targets : 3,
				data : 'registerContent'
			}, {
				targets : 4,
				data : 'registerPassword'
			}, {
				targets : 5,
				data : function(row, type, val, meta) {
					var $btn = "";
					
					if(row.isDelete) {
						stateText = "비게시";
						stateData = true;
					} else {
						stateText = "게시";
						stateData = false;
					}
					
					$btn = "<button type='button' class='btn btn-warning btn-sm btnSweetMessageDelete' data-statedata=" + stateData + ">" + stateText + "</button>";
					
					return $btn;
				}
			}],
			rowId : function(row) {
				return row.seq;
			}
		};
		
		utilDataTable("tableSweetMessageList", option, pageMaker.totalCount);
		utilDataTablePaging("divPagingWrap", "tableSweetMessageList", pageMaker);
}

function resetRender() {
	$(".btnDeleteImage").each(function(){
		var $wrapUploadFile = $(this).parents(".wrapUploadFile");
		
		$wrapUploadFile.find("a").attr("href", "").removeData("title", "")
								.find("img").attr("src", DEFAULT_IMG_SRC).removeData("fullName");
		$wrapUploadFile.find(".btnUploadFile").val("").show()
								.prev().show()
								.next().next().hide();
	});
	
	$("#wrapListLS").empty();
}

function validModifyInvitation() {
	var result = {
			resFlag : true,
			resData : {},
			resMessage : "",
			modifyCategory : "invitation"
		},
		datePeriod = $("#inputDatePeriod").val().split(" - "),
		today = getFormattedDate(new Date()),
		periodBegin = "",
		periodEnd = "",
		visible = $("input[name=checkboxVisibleYN]").prop("checked") == true ? "N" : "Y",
		formCode = "hookup";
	
	if($("#inputId").val() != $("#inputId").data("id")) {
		result.resFlag = false;
		result.resMessage = "기본정보의 아이디를 정확히 입력 해주세요.";
		return result;
	}
	
	periodBegin = (datePeriod[0]).replace(/-/g, "");
	periodEnd = (datePeriod[1]).replace(/-/g, "");
	if(Number(today) > Number(periodEnd)) {
		result.resFlag = false;
		result.resMessage = "게시 기간을 확인해주세요.";
		return result;
	}
	
	result.resData = {
			seq : syntheticInvitation.invitationVO.seq,
			visible : visible,
			periodBegin : periodBegin,
			periodEnd : periodEnd,
			formCode : formCode
	}
	return result;
}

function validModifyMainInfo() {
	var result = {
			resFlag : true,
			resData : {},
			resMessage : "",
			modifyCategory : "mi"
	};
	
	result.resData.seq = syntheticInvitation.mainInfoVO.seq;
	result.resData.invSeq = syntheticInvitation.invitationVO.seq;
	
	var today = getFormattedDate(new Date()),
		datePeriod = $("#inputDatePeriod").val().split(" - "),
		periodBegin = (datePeriod[0]).replace(/-/g, ""),
		periodEnd = (datePeriod[1]).replace(/-/g, "");
	if(Number(today) > Number(periodEnd)) {
		result.resFlag = false;
		result.resMessage = "기본정보의 게시 기간을 확인해주세요.";
		return result;
	}
	
	var dateTimeWedding = $("#inputDateTimeWedding").val().split(" ");
	result.resData.dateWedding = (dateTimeWedding[0]).replace(/-/g, "");
	result.resData.timeWedding = (dateTimeWedding[1]).replace(/:/g, "");
	if(Number(result.resData.dateWedding) < Number(periodBegin) || Number(result.resData.dateWedding) > Number(periodEnd)) {
		result.resFlag = false;
		result.resMessage = "결혼 일자를 확인해주세요.";
		return result;
	}
	
	var infoAddr = $("#infoWeddingPlace").data("infoWeddingPlace") || "";
	result.resData.address = infoAddr.addr || "";
	result.resData.placeX = infoAddr.placeX || "";
	result.resData.placeY = infoAddr.placeY || "";
	if(infoAddr == undefined || infoAddr == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 장소를 확인해주세요.";
		return result;
	}
	if(result.resData.address == "" || result.resData.placeX == "" || result.resData.placeY == "") {
		result.resFlag = false;
		result.resMessage = "결혼식 장소를 확인해주세요.";
		return result;
	}
	
	result.resData.contentGroom = $("#contentGroom").val();
	result.resData.contentBride = $("#contentBride").val();
	
	result.resData.fullNameMain = $("#imgMI1").parents(".wrapUploadFile").find("img").data("fullName");
	result.resData.seqImgMain = $("#imgMI1").parents(".wrapUploadFile").find("img").data("seqImage") || -1;
	if(result.resData.fullNameMain == undefined) {
		result.resFlag = false;
		result.resMessage = "메인 사진을 확인해주세요.";
		return result;
	}
	
	result.resData.useEachImage = $("input[name=checkboxEachImgYN]").prop("checked") == true ? "Y" : "N";
	
	result.resData.fullNameGroom = $("#imgMI2").parents(".wrapUploadFile").find("img").data("fullName");
	result.resData.seqImgGroom = $("#imgMI2").parents(".wrapUploadFile").find("img").data("seqImage") || -1;
	result.resData.fullNameBride = $("#imgMI3").parents(".wrapUploadFile").find("img").data("fullName");
	result.resData.seqImgBride = $("#imgMI3").parents(".wrapUploadFile").find("img").data("seqImage") || -1;
	if(result.resData.useEachImage == "Y") {
		if(result.resData.fullNameGroom == undefined) {
			result.resFlag = false;
			result.resMessage = "신랑 사진을 확인해주세요.";
			return result;
		}
		
		if(result.resData.fullNameBride == undefined) {
			result.resFlag = false;
			result.resMessage = "신부 사진을 확인해주세요.";
			return result;
		}
	}
	
	return result;
}

function validModifyLoveStory() {
	var result = {
			resFlag : true,
			resData : {},
			resMessage : "",
			useLS : $("input[name=checkboxUseLSYN]").prop("checked") == true ? "Y" : "N",
			modifyCategory : "ls"
	};
	
	result.resData.listLoveStory = [];
	var noImageCount = 0,
		isUndefinedImg = false;
	$(".itemLoveStory").each(function(index){
		var $this = $(this),
			fullNameImg = $this.find("img").data("fullName") || "";
		
		if(fullNameImg == "") {
			isUndefinedImg = true;
			return false;
		}
		
		result.resData.listLoveStory.push({
			seq : $this.find("img").data("seq") || 0,
			invSeq : syntheticInvitation.invitationVO.seq,
			id : syntheticInvitation.invitationVO.id,
			isDelete : false,
			dateStory : ($this.find(".inputDateLoveStory").val()).replace(/-/g, ""),
			title : $this.find(".inputTitleLS").val(),
			content : $this.find(".inputContentLS").val(),
			fullNameImg : fullNameImg,
			seqImage : $this.find("img").data("seqImage") || -1,
			orderSeq : index + 1
		});
	});
	
	if(isUndefinedImg){
		result.resFlag = false;
		result.resMessage = "Love Story에 사진을 확인해주세요.";
		return result;
	} else if(result.useLS == "Y" && result.resData.listLoveStory.length < 1) {
		result.resFlag = false;
		result.resMessage = "Love Story에 사진을 확인해주세요.";
		return result;
	}
	
	return result;
}

function validModifyWhenWhere() {
	var result = {
			resFlag : true,
			resData : {},
			resMessage : "",
			usePyebaek : $("input[name=checkboxDoPyebaek]").prop("checked") == true ? "Y" : "N",
			modifyCategory : "ww"
	},
	checkPhebaek = $("#inputDatePyebaek").data("seq") || -1;
	
	result.resData.listWhenWhere = [];
	result.resData.listWhenWhere.push({
		seq : $("#inputDateTimeWedding_copy").data("seq") || -1,
		invSeq : $("#inputDateTimeWedding_copy").data("invSeq"),
		flagPyebaek : result.usePyebaek,
		title : $("#inputTitleWeddingWW").val(),
		content : $("#inputContentWeddingWW").val(),
		modifyType : "part"
	});
	if(result.usePyebaek == "Y" || (result.usePyebaek == "N" && checkPhebaek != -1)) {
		var modifyType = checkPhebaek == -1 ? "add" : "all",
			dateTimePyebaek = $("#inputDatePyebaek").val() || "";
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
		
		result.resData.listWhenWhere.push({
			seq : $("#inputDatePyebaek").data("seq") || -1,
			invSeq : syntheticInvitation.invitationVO.seq,
			id : syntheticInvitation.invitationVO.id,
			flagPyebaek : result.usePyebaek,
			dateWedding : (dateTimePyebaek[0]).replace(/-/g, ""),
			timeWedding : (dateTimePyebaek[1]).replace(/:/g, ""),
			address : infoAddrPyebaek.addr || "",
			placeX : infoAddrPyebaek.placeX || "",
			placeY : infoAddrPyebaek.placeY || "",
			title : $("#inputTitlePyebaekWW").val(),
			content : $("#inputContentPyebaekWW").val(),
			modifyType : modifyType
		});
	}
	
	return result;
}

function validModifyGallery() {
	var result = {
			resFlag : true,
			resData : {},
			resMessage : "",
			useG : $("input[name=checkboxUseG]").prop("checked") == true ? "Y" : "N",
			modifyCategory : "gallery"
	};
	
	result.resData.listGallery = [];
	$("#tableGallery").find(".wrapUploadFile").each(function(index){
		var $this = $(this),
			fullName = $this.find("img").data("fullName") || "";
		
		if(fullName != "") {
			result.resData.listGallery.push({
				seq : $this.find("img").data("seq") || 0,
				invSeq : syntheticInvitation.invitationVO.seq,
				orderSeq : index + 1,
				id : syntheticInvitation.invitationVO.id,
				isDelete : false,
				seqImage : $this.find("img").data("seqImage") || -1,
				fullName : fullName
			});
		}
	});
	if(result.useG == "Y" && result.resData.listGallery.length < 1) {
		result.resFlag = false;
		result.resMessage = "Gallery에 사진을 확인해주세요.";
		return result;
	}
	
	return result;
}

function validModifySweetMessage() {
	var result = {
			resFlag : true,
			resData : syntheticInvitation.invitationVO.seq,
			resMessage : "",
			useSM : $("input[name=checkboxUseSM]").prop("checked") == true ? "Y" : "N",
			modifyCategory : "sm"
	};
	
	return result;
}

function modifyInvitationInfo(data) {
	var addParam = "";
	
	if(data.modifyCategory == "ls") {
		addParam = "?" + $.param({useLS : data.useLS});
	} else if(data.modifyCategory == "gallery") {
		addParam = "?" + $.param({useG : data.useG});
	} else if(data.modifyCategory == "sm") {
		addParam = "?" + $.param({useSM : data.useSM});
	}
	
	$.ajax({
		url : "/admin/invitation/" + URL[data.modifyCategory] + addParam,
		type : "POST",
		dataType : "json",
		data : JSON.stringify(data.resData),
		contentType : "application/json;charset=utf-8;",
		error : function(xhr, status, msg) {
			alert("satus : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			if(result.resFlag) {
				syntheticInvitation.invitationVO = result.resInvitationVO;
				alert("수정이 완료되었습니다.");
			} else {
				alert(result.resMessage);
			}
		}
	});
}

function deleteSweetMessage(seq, stateData) {
	$.ajax({
		url : "/admin/invitation/deleteSweetMessage.do?" + $.param({seq : Number(seq), isDelete : !stateData}),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			if(result.resFlag) {
				var tabindex = Number($(".active .aPaging").attr("tabindex"));
				
				utilDataTableDestroy("tableSweetMessageList");
				getSweetMessageList(Number(tabindex));
			} else {
				alert(result.resMessage);
			}
		}
	});
}
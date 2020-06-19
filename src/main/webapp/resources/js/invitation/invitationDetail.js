/**
 * 
 */

console.log("########## invitationDetail.js ##########");

var $btnGetAddress = "",
	$tableRecordLoveStory = undefined,
	$divRecordMemberInvitation = undefined;

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
	
	$("#sectionContent").on("click", ".btnDeleteImage", function(){
		var $wrapUploadFile = $(this).parents(".wrapUploadFile");
		
		$wrapUploadFile.find("a").attr("href", "").removeData("title", "")
								.find("img").attr("src", DEFAULT_IMG_SRC).removeData("fullName");
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
		$btnUploadFile.prev().hide();
		$btnUploadFile.hide()
				.parents(".wrapUploadFile")
					.find("a").attr("href", fileInfo.originalFileUrl).data("title", fileInfo.originalFileName)
					.find("img").attr("src", fileInfo.imgSrc).data("fullName", fileInfo.fullName).data("seqImage", data.seqImage);
		$btnUploadFile.next().show();
		
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
				renderSyntheticInvitation(result.resSyntheticInvitation);
				$("#modal-invitationList").modal("hide");
			} else {
				alert(result.resMessage);
				$("#modal-invitationList").modal("hide");
			}
		}
	});
}

function renderSyntheticInvitation(data) {
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
		$imgHGB1 = $("#imgHGB1");
	$imgHGB1.prev().hide();
	$imgHGB1.hide()
			.parents(".wrapUploadFile")
				.find("a").attr("href", fileInfo1.originalFileUrl).data("title", fileInfo1.originalFileName)
				.find("img").attr("src", fileInfo1.imgSrc).data("fullName", fileInfo1.fullName).data("seqImage", data.mainInfoVO.seqImgMain);
	$imgHGB1.next().show();
	$("input[name=checkboxEachImgYN]").prop("checked", data.mainInfoVO.useEachImage == "Y" ? true : false);
	var fileInfo2 = setFileInfo(data.mainInfoVO.fullNameGroom),
		$imgHGB2 = $("#imgHGB2");
	$imgHGB2.prev().hide();
	$imgHGB2.hide()
			.parents(".wrapUploadFile")
				.find("a").attr("href", fileInfo2.originalFileUrl).data("title", fileInfo2.originalFileName)
				.find("img").attr("src", fileInfo2.imgSrc).data("fullName", fileInfo2.fullName).data("seqImage", data.mainInfoVO.seqImgGroom);
	$imgHGB2.next().show();
	var fileInfo3 = setFileInfo(data.mainInfoVO.fullNameBride),
		$imgHGB3 = $("#imgHGB3");
	$imgHGB3.prev().hide();
	$imgHGB3.hide()
			.parents(".wrapUploadFile")
				.find("a").attr("href", fileInfo3.originalFileUrl).data("title", fileInfo3.originalFileName)
				.find("img").attr("src", fileInfo3.imgSrc).data("fullName", fileInfo3.fullName).data("seqImage", data.mainInfoVO.seqImgBride);
	$imgHGB3.next().show();
	
	$("input[name=checkboxUseLSYN]").prop("checked", data.invitationVO.useLS == "Y" ? true : false);
	data.loveStoryVO.forEach(function(item){
		cloneLoveStory(true, item);
	});
	
	$("#inputDateTimeWedding_copy").val(dateTimeWedding1);
	$("#inputTitleWeddingWW").val(data.whenWhereVO[0].title);
	$("#inputContentWeddingWW").val(data.whenWhereVO[0].content);
	if(data.invitationVO.usePyebaek == "Y") {
		$("input[name=checkboxDoPyebaek]").prop("checked", true);
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
		$btnUploadFile.prev().hide();
		$btnUploadFile.hide()
				.parents(".wrapUploadFile")
					.find("a").attr("href", fileInfo4.originalFileUrl).data("title", fileInfo4.originalFileName)
					.find("img").attr("src", fileInfo4.imgSrc).data("fullName", fileInfo4.fullName).data("seqImage", item.seq);
		$btnUploadFile.next().show();
	});
	
//	getSweetMessageList(1);
}

function getSweetMessageList(pageItem) {
	var requestParam = {
			page : pageItem
	};
	
	$.ajax({
		url : "/admin/invitation/getSweetMessageList?" + $.param(requestParam),
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
						if(row.isDelete) {
							return "삭제됨";
						} else {
							return "삭제";
						}
					}
				}],
				rowId : function(row) {
					return row.seq;
				}
			};
			
			utilDataTable("tableSweetMessageList", option, result.pageMaker.totalCount);
			utilDataTablePaging("divPagingWrap", "tableSweetMessageList", result.pageMaker);
		}
	});
}
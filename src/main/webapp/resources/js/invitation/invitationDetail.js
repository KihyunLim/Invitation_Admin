/**
 * 
 */

console.log("########## invitationDetail.js ##########");

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
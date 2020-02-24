/**
 * 
 */

console.log("########## memberList.js ##########");

$(function(){
	setActiveSidebar();
	
	getMemberList();
});

function getMemberList() {
	$.ajax({
		url : "/admin/member/getMemberList",
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			renderMemberList(result.list);
		}
	});
};

function renderMemberList(data) {
	// 공통으로 할 수 있게 유틸로 빼고
	// 오름/내림차순 정렬 할 수 있게도 해야하고
	// 맨 앞에 체크박스 컬럼도 추가해야함
	
	$("#tableMemberList").DataTable({
		lengthChange : false,
		searching : false,
		ordering : false,
		autoWidth : false,
		pagingType: 'full_numbers',
	    language: {
	        paginate: {
	            first:    '«',
	            previous: '‹',
	            next:     '›',
	            last:     '»'
	        },
	        aria: {
	            paginate: {
	                first:    'First',
	                previous: 'Previous',
	                next:     'Next',
	                last:     'Last'
	            }
	        }
	    },
	    info : true,
	    infoCallback : function(settings, start, end, max, total, pre) {
	    	return "총 겸색 결과 : " + 5;
	    },
		data : data,
		
		columns : [
			{data : "id"},
			{data : "name"},
			{data : "phone"},
			{
				data : "statusSee", 
				render : function(data, type, row, meta) {
					var statusSee = "";
					
					if(data == "Y") {
						statusSee = "게시";
					} else if(data == "N") {
						statusSee = "비게시"; 
					} else {
						statusSee = "-";
					}
					
					return statusSee;
				}
			}
		]
	});
}
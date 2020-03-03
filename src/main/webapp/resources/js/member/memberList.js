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
	// - 맨 앞에 체크박스 컬럼도 추가해야함
	// 페이징 처리 구현
		// elementId, total, data, columnDefs... 을 파라미터로 넘기고 나머진 유틸 기본값으로
	// 검색 기능 구현
	// 오름/내림차순 정렬 할 수 있게도 해야하고
	// 공통으로 할 수 있게 유틸로 빼고
	$("#tableMemberList").DataTable({
		lengthChange : false,
		searching : false,
		ordering : false,
		autoWidth : false,
		paging : false,
	    info : true,
	    infoCallback : function(settings, start, end, max, total, pre) {
	    	return "총 겸색 결과 : " + 29;
	    },
		data : data,
		
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
		}
	});
}
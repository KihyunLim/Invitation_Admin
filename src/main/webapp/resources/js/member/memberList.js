/**
 * 
 */

console.log("########## memberList.js ##########");

$(function(){
	setActiveSidebar();
	
	getMemberList(1);
});

function getMemberList(pageItem) {
	var requestParam = {
			page : pageItem,
			condition : "",
			keyword : $("#inputKeyword").val().trim()
	};
	
	if(requestParam.keyword != "") {
		requestParam.condition = $("#selectCondition").val();
	}
	
	$.ajax({
		url : "/admin/member/getMemberList?" + $.param(requestParam),
		type : "GET",
		error : function(xhr, status, msg) {
			alert("status : " + status + "\nHttp error msg : " + msg);
		},
		success : function(result) {
			console.log(result);
			
			// 회원 선택 시 수정 팝업 띄우는 이벤트 추가 필요 (수정 시 필요한 정보는 어디서든 읽어 지겠지 아니면 data 태그라두)
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
				}
			};
			
			utilDataTable("tableMemberList", option, result.pageMaker.totalCount);
//			renderMemberList(result.list);
		}
	});
};

function renderMemberList(data) {
	// - 맨 앞에 체크박스 컬럼도 추가해야함
	// - 페이징 처리 구현
		// elementId, total, data, columnDefs... 을 파라미터로 넘기고 나머진 유틸 기본값으로
	// - 검색 기능 구현
		// 검색란이 ""면 조건도 ""해서 걍 ㄱ / 입력된게 있으면 조건 같이 ㄱ
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
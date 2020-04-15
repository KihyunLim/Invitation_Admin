/**
 * 
 */

console.log("########## invitationDetail.js ##########");

$(function(){
	setActiveSidebar();
	
	$("#inputViewDate").daterangepicker({
		locale : {
			format : "YYYY-MM-DD"
		}
		/*autoUpdateInput: false
		
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
		  });*/
	});
	
	$("#inputWeddingDateTime").daterangepicker({
		singleDatePicker : true,
		timePicker : true,
		timePicker24Hour : true,
		locale : {
			format : "YYYY-MM-DD HH:mm"
		}
	});
});
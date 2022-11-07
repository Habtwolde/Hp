/*$(document).ready(function() {
	$('#cal_mini #cal_mini_dates span:not(.placeholder)').bind('click', function() {
		$('#d').val($(this).text());
		$("form[name='cal_mini']").submit();
	});
});*/

var days_in_month = new Array('', '31', '28', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31');

function check_day_in_range(new_month_index) {
 	// determine leap year
 	var year = document.getElementById('y').options[document.getElementById('y').selectedIndex].value;
 	if (year % 400 == 0) {
 		days_in_month[2] = 29;
 	} else if (year % 100 == 0) {
 		days_in_month[2] = 28;
 	} else if (year % 4 == 0) {
 		days_in_month[2] = 29;
 	} else {
 		days_in_month[2] = 28;
 	}
 	
 	// check day isn't out of range for new month
 	var selected_day = document.getElementById('d').value;
 	// +1 offsets index to actual month number
 	if (selected_day > days_in_month[new_month_index + 1]) {
 		document.getElementById('d').value = days_in_month[new_month_index + 1];
 	}
 }

function prev_month() {
 	// if at january
 	if (document.getElementById('m').selectedIndex == 0) {
 		
 		//month to december
 		document.getElementById('m').selectedIndex = 11;
 			
 		//set year - 1
 		var new_year_index = document.getElementById('y').selectedIndex - 1;
 		// if year-1 exists
 		if (new_year_index < 0) {
 			//if not add
 			var selected_year_index = document.getElementById('y').selectedIndex;
 			var selected_year = document.getElementById('y').option[selected_year_index].text;
 			document.getElementById('y').add(new Option(selected_year - 1, new_year_index), 0);
 		} else {
 			// year + 1
 			document.getElementById('y').selectedIndex = new_year_index;
 		}
 	// month - 1
 	} else {
 		var new_month_index = document.getElementById('m').selectedIndex - 1;
 		document.getElementById('m').selectedIndex = new_month_index;
 		
 		check_day_in_range(new_month_index);
 	}
 }

 function next_month() {
 	// if at december
 	if (document.getElementById('m').selectedIndex == 11) {
 		//month to january
 		document.getElementById('m').selectedIndex = 0;
 		
 		// set year + 1
 		var new_year_index = document.getElementById('y').selectedIndex + 1;
 		// check year + 1 exists
 		if (new_year_index > document.getElementById('y').length) {
 			// if not add year+1
 			var selected_year_index = document.getElementById('y').selectedIndex;
 			var selected_year = document.getElementById('y').option[selected_year_index].text;
 			document.getElementById('y').add(new Option(selected_year + 1, new_year_index));
 		} else {
 			// select year+1
 			document.getElementById('y').selectedIndex = new_year_index;
 		}
 	// month + 1
 	} else {
 		var new_month_index = document.getElementById('m').selectedIndex + 1;
 		document.getElementById('m').selectedIndex = new_month_index;
 		
 		check_day_in_range(new_month_index);
 	}
 }
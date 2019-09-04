/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	// occupancy=2-9,4 -> adult 2, 9 years old 1, 4 years old 1
    $(".input-number-group").on('mousedown', function () {
    	var person_type = $(this).children().attr("id")
    	$(this).children().each(function(idx, val) {
    		console.log($(val).attr("id"));
    		var adult_count = parseInt($("#adult_count").val());
    		var child_count = parseInt($("#child_count").val());
    		var id = $(val).attr("id")
    		if (id == "adult_count_increase") {
    			adult_count += 1;
    			$("#adult_count").val(adult_count);
    		} 
    		if (id == "adult_count_decrease") {
    			if (adult_count != 0) {
    				adult_count -= 1;
    				$("#adult_count").val(adult_count);
    			}
    		} 
    		if (id == "child_count_increase") {
    			child_count += 1;
    			$("#child_count").val(adult_count);
    		} 
    		if (id == "child_count_decrease") {
    			if (child_count != 0) {
    				child_count -= 1;
    				$("#child_count").val(adult_count);
    			}
    		} 

		});
//    	var adult_count = $("#adult_count").val()+1;
//    	 $("#adult_count").val(adult_count);
	});
	
});
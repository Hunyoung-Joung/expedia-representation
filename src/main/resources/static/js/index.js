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
    		
    		var adult_count = parseInt($("#adult_count").val());
    		var child_count = parseInt($("#child_count").val());
    		
    		var id = $(val).attr("id")
    		if (id == "adult_count_increase") {
    			adult_count = parseInt($("#adult_count").val())+1;
    			$("#adult_count").val(adult_count);
    			console.log(adult_count+", "+$("#adult_count").val());
    			return;
    		} 
    		else if (id == "adult_count_decrease") {
    			if (adult_count > 0) {
    				adult_count = parseInt($("#adult_count").val())-1;
    				$("#adult_count").val(adult_count);
    				console.log(adult_count+", "+$("#adult_count").val());
    			}
    			return;
    		} 
    		else if (id == "child_count_increase") {
    			child_count = parseInt($("#child_count").val())+1;
    			$("#child_count").val(child_count);
    			console.log(adult_count+", "+$("#child_count").val());
    			return;
    		} 
    		else if (id == "child_count_decrease") {
    			if (child_count > 0) {
    				child_count = parseInt($("#child_count").val())-1;
    				$("#child_count").val(child_count);
    				console.log(adult_count+", "+$("#child_count").val());
    			}
    			return;
    		} 
		});
//    	var adult_count = $("#adult_count").val()+1;
//    	 $("#adult_count").val(adult_count);
	});
	
});
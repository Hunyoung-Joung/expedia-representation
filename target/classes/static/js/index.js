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
    		if (person_type == "adult_count_increase") {
    			if (idx == 0) {
    				$(val).val(parseInt($(val).val())+1)
    			} 
    			if (idx == 2) {
    				$(val).val(parseInt($(val).val())-1)
    			} 
    		} else {
    			if (idx == 0) {
    				$(val).val(parseInt($(val).val())+1)
    			} 
    			if (idx == 2) {
    				$(val).val(parseInt($(val).val())-1)
    			} 
    		}

			console.log(idx+" - "+val);

		});
//    	var adult_count = $("#adult_count").val()+1;
//    	 $("#adult_count").val(adult_count);
	});
	
});
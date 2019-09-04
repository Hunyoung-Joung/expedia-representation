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
    			var adult_count = parseInt($("#adult_count").val());
    			console.log(adult_count);
    			if (idx == 0) {
    				adult_count += 1
    			} 
    			if (idx == 2) {
    				if (adult_count != 0) {
    					adult_count -= 1
    				}
    			} 
    			$("#adult_count").val(adult_count);
    		} else {
    			if (idx == 0) {

    			} 
    			if (idx == 2) {

    			} 
    		}

		});
//    	var adult_count = $("#adult_count").val()+1;
//    	 $("#adult_count").val(adult_count);
	});
	
});
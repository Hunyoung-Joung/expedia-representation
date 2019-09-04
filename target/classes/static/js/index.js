/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	// occupancy=2-9,4 -> adult 2, 9 years old 1, 4 years old 1
    $(".input-number-group").on('mousedown', function (event) {

//    	$(this).children().each(function(idx, val) {
    		console.log("event.target? "+event.target.nodeName);
    		console.log("event.target? "+event.target.id);
    		console.log("event.target? "+$(this).target);
    		var adult_count = parseInt($("#adult_count").val());
    		var child_count = parseInt($("#child_count").val());
//    		var id = $(val).attr("id");
    		var id = $(event.target).attr("id");
    		
    		if (id == "adult_count_increase") {
    			adult_count = parseInt($("#adult_count").val())+1;
    			$("#adult_count").val(adult_count);
    			console.log(adult_count+", "+$("#adult_count").val());

    		} else if (id == "adult_count_decrease") {
    			if (adult_count > 0) {
    				adult_count = parseInt($("#adult_count").val())-1;
    				$("#adult_count").val(adult_count);
    				console.log(adult_count+", "+$("#adult_count").val());
    			}

    		} else if (id == "child_count_increase") {
    			child_count = parseInt($("#child_count").val())+1;
    			$("#child_count").val(child_count);
    			console.log(adult_count+", "+$("#child_count").val());

    		} else if (id == "child_count_decrease") {
    			if (child_count > 0) {
    				child_count = parseInt($("#child_count").val())-1;
    				$("#child_count").val(child_count);
    				console.log(adult_count+", "+$("#child_count").val());
    			}

    		} 
//		});
	});
	
    $("#person_count_modal_confirm").click(function(){
    	$("#person_count").val("大人"+$("#adult_count").val()+","+"子供"+$("#child_count").val());
    	$("#occupancy").val($("#adult_count").val()+"-"+$("#child_count").val());
	});
});
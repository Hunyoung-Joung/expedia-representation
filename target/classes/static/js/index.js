/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	
    $(".input-number-group").on('mousedown', function () {
    	console.log($(this).children().attr("id"));
    	$(this).children().each(function(idx, val) {

			console.log(idx+" - "+val);

		});
    	var adult_count = $("#adult_count").val()+1;
    	 $("#adult_count").val(adult_count);
	});
	
});
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
    	$(this).each(function() {
			var target_id = $(this).attr("id");
			console.log(target_id);

		});
    	var adult_count = $("#adult_count").val()+1;
    	 $("#adult_count").val(adult_count);
	});
	
});
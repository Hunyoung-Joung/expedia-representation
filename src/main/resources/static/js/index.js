/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	
    $("#input-number-group").on('click', function () {
    	console.log($(this).children().attr("id"));
    	var adult_count = $("#adult_count").val()+1;
    	 $("#adult_count").val(adult_count);
	});
	
});
/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	
    $("#adult_count_increase").on('change', function () {
    	var adult_count = $("#adult_count").val()+1;
    	 $("#adult_count").val(adult_count);
	});
	
});
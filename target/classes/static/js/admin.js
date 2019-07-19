/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;

$(document).ready(function(){
	
    $.each(adminQuestionInfos, function(idx, val) {
    	console.log(idx+" : "+val);
        $.each(val, function(idx_, val_) {
        	console.log(idx+" : "+val);
//			if (key == "q_no") {
//				questionNo = JSON.parse(JSON.stringify(val)).survey_answer;
//				$($(".questions").get(parseInt(value)-1)).val(ans);
//			}
  		});
	});
});


/**
 * 
 * Error response
 * 
 * @param err
 * @returns
 */
function showError(err) {
	$("div.error_division").text(err).attr("tabindex",-1).focus();
}
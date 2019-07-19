/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;

$(document).ready(function(){
	
    $.each(adminQuestionInfos, function(idx, val) {
        $.each(val, function(idx_, val_) {
        	console.log(idx_+" : "+JSON.parse(JSON.stringify(val_)));
			if (idx_ == "personalInfo") {
				$.each(val_, function(key, value) {
					console.log(key+" : "+JSON.parse(JSON.stringify(value)));
				});
//				questionNo = JSON.parse(JSON.stringify(val)).survey_answer;
//				$($(".questions").get(parseInt(value)-1)).val(ans);
			} else if (idx_ == "questionInfo") {
//				questionNo = JSON.parse(JSON.stringify(val)).survey_answer;
//				$($(".questions").get(parseInt(value)-1)).val(ans);
			} else {
				
			}
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
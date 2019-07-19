/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
let questions = [];
let questionList = [];

$(document).ready(function(){
	
    $.each(adminQuestionInfos, function(idx, val) {
        $.each(val, function(idx_, val_) {
        	console.log(idx_+" : "+JSON.parse(JSON.stringify(val_)));
			if (idx_ == "personalInfo") {
				$.each(val_, function(key, value) {
					questions.push(JSON.parse(JSON.stringify(value)).display_name);
				});
//				questionNo = JSON.parse(JSON.stringify(val)).survey_answer;
//				$($(".questions").get(parseInt(value)-1)).val(ans);
			} else if (idx_ == "questionInfo") {
				$.each(val_, function(key, value) {
					questions.push(JSON.parse(JSON.stringify(value)).q_no);
					var categoryName;
					switch (JSON.parse(JSON.stringify(value)).q_category) {
					  case 1:
						  categoryName = "最新API状況アップデート";
					    break;
					  case 2:
						  categoryName = "LINE Pay APIのご紹介";
					    break;
					  case 3:
						  categoryName = "クラスメソッド様事例紹介";
					    break;
					  case 4:
						  categoryName = "その他";
					    break;
					}
					questions.push(categoryName);
					questions.push(JSON.parse(JSON.stringify(value)).q_contents);
					questions.push(JSON.parse(JSON.stringify(value)).is_selected);
					questions.push(JSON.parse(JSON.stringify(value)).update_at);
				});
			} else {
				
			}
  		});
        questionList.push(questions);
	});
    
    $('.q').each(function(ind) {
    	$(this).find('tr').each(function() {
    		$(this).find('td').each(function(i) {
    			$(this).text(ind * 2 + i + 1);
    		});
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
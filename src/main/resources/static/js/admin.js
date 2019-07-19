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
			if (idx_ == "personalInfo") {
				$.each(val_, function(key, value) {
					if (key == "display_name") {
						questions.push(value);
					}
				});
//				questionNo = JSON.parse(JSON.stringify(val)).survey_answer;
//				$($(".questions").get(parseInt(value)-1)).val(ans);
			} else if (idx_ == "questionInfo") {
				$.each(val_, function(key, value) {
					if (key == "q_no") {
						questions.push(value);
					} else if (key == "q_category") {
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
					} else if (key == "q_contents") {
						questions.push(value);
					} else if (key == "is_selected") {
						questions.push(value);
					} else if (key == "update_at") {
						questions.push(value);
					}
				});
			} else {
				
			}
  		});
        questionList.push(questions);
	});
    
    $('.q').each(function() {
    	$(this).find('tr').each(function(i) {
    		$(this).find('td').each(function(j) {
//    			$(this).text(ind * 2 + i + 1);
    			console.log(questionList[i]);
    			$(this).text(questionList[i]);
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
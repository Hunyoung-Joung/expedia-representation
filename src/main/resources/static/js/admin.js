/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	
    $.each(adminQuestionInfos, function(idx, val) {
    	var questions = [];
        $.each(val, function(idx_, val_) {
			if (idx_ == "personalInfo") {
				$.each(val_, function(key, value) {
					if (key == "display_name") {
						questions.push(value);
					}
				});
			} else if (idx_ == "questionInfo") {
				$.each(val_, function(key, value) {
					if (key == "q_no") {
						questions.push(value);
					} else if (key == "q_category") {
						var categoryName;
						switch (parseInt(value)) {
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
    
    $.each(questionList, function(idx, val) {
    	var addHtml = $('.q tbody').html();
//    	console.log( idx + ": " + val);
//    	console.log("#######################"+$('.q tbody').html());
        $('.q').each(function() {
        	$(this).find('tbody').each(function() {
        		$(this).find('tr').each(function() {
        			$(this).find('td').each(function(i) {
	        			console.log("#######################"+val[i]);
	        			$(this).text(val[i]);
        			});
        		});
        	});        	
    	});
        $('.q tbody').append(addHtml);
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
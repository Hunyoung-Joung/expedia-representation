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
    
	var innerHtml = "<tr style='word-wrap: break-word'><td class='col-sm-3'>" +
			"</td><td>" +
			"</td><td class='col-sm-3'>" +
			"</td><td class='col-sm-5'>" +
			"</td><td class='col-sm-2'><form th:action='@{/seminar/{q_no}(q_no=*{q_no})}' th:method='update'><input class='btn btn-default btn-xs' type='submit' value='削除' /></form></td>" +
			"<td class='col-sm-2'></td>" +
			"</tr>";
    // Array stack to reverse order by question no
    $.each(questionList.reverse(), function(idx, val) {
//    	$('.q tbody').append(innerHtml);
    	$('.q tbody').append(innerHtml).each(function() {
        	$(this).find('tbody').each(function() {
        		$(this).find('tr').each(function() {
        			$(this).find('td').each(function(i) {
	        			console.log("#######################"+val[i]);
	        			$(this).text(val[i]);
        			});
        		});
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
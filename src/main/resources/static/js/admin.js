/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	
	$('#showData').on('click', function () {
		var isChecked = $(this).is(':checked');
		
		console.log(" -- "+$(this).index());
//		if (isChecked) {
//			checkBoxVals.push($(this).val());
//		} else {
//			var idx = checkBoxVals.indexOf($(this).val());
//			if (idx > -1) {
//				checkBoxVals.splice(idx, 1);
//			}
//		}
	});
	
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
    
	var innerHtml = "<tr class='inner' style='word-wrap: break-word'><td class='col-sm-3'>" +
			"</td><input id='qNo' name='q_no' type='hidden' class='form-control'/><td>" +
			"</td><td class='col-sm-3'>" +
			"</td><td class='col-sm-5'>" +
			"</td><td class='col-sm-2'>" +
			"<form th:action='@{/seminar/{q_no}(q_no=*{q_no})}' th:method='put' th:object='${questionInfo}'>" +
			"<div class='radio text-center'>" +
			"<label><input id='isSelected1' name='is_selected' type='radio' value='true' th:field='*{is_selected}'>可能</label> " +
			"<label><input id='isSelected2' name='is_selected' type='radio' value='false' th:field='*{is_selected}'>不可</label>" +
			"</div>" +
			"</form>" +
			"</td>" +
			"<td class='col-sm-2'></td>" +
			"</tr>";
    // Array stack to reverse order by question no
    $.each((questionList.reverse()), function(idx, val) {
    	$('.q tbody').append(innerHtml);
        $('.q').each(function() {
        	$(this).find('tbody').each(function() {
        		$(this).find('.inner').each(function() {
        			if ($(this).index() == idx) {
            			$(this).find('td').each(function(i) {
            				if (i == 1) {
            					$($(this).find("#qNo")).val(val[i]);
            				} else if (i == 4) {
            					if (val[i] == true) {
            						$($(this).find("#isSelected1")).prop("checked", true);
            					} else {
            						$($(this).find("#isSelected2")).prop("checked", true);
            					}
            				} else {
            					$(this).text(val[i]);
            				}
            			});
        			}
        		});
        	});        	
    	});

	});
    
    var isSelected = $("input:radio[name='is_selected']");
    var selectedIndex = isSelected.index(radioButtons.filter(':checked'));
    $("input:radio[name='is_selected']").on('change', function () {
    	console.log(" -- "+selectedIndex);
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
/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	
    $("input:radio[name='showData']").on('change', function () {
		
		if ($(this).attr("id") == "showData1") {
			$(".users").show();
			$(".questions").hide();
			$(".survey").hide();
		} else if ($(this).attr("id") == "showData2") {
			$(".users").hide();
			$(".questions").show();
			$(".survey").hide();
		} else {
			$(".users").hide();
			$(".questions").hide();
			$(".survey").show();
		}
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
    
	var innerHtml = "<tr class='inner' style='word-wrap: break-word'>" +
			"<td ><td>" +
			"<td ><input id='qNo' name='q_no' type='hidden' class='form-control'/></td>" +
			"<td ></td>" +
			"<td >" +
			"<form action='' method='post' >" +
			"<input type='hidden' name='_method' value='put'>" +
			"<div class='radio text-center'>" +
			"<label><input id='isSelected1' name='is_selected' type='radio' value='true' >可能</label> " +
			"<label><input id='isSelected2' name='is_selected' type='radio' value='false' >不可</label>" +
			"</div>" +
			"</form>" +
			"</td>" +
			"<td ></td>" +
			"<td ></td>" +
			"</tr>";
    // Array stack to reverse order by question no
    $.each((questionList), function(idx, val) {
    	$('.q tbody').append(innerHtml);
        $('.q').each(function() {
        	$(this).find('tbody').each(function() {
        		$(this).find('.inner').each(function() {
        			if ($(this).index() == idx) {
            			$(this).find('td').each(function(i) {
            				$(this).text(val[i]);
            				if (i == 1) {
            					$($(this).find("#qNo")).val(val[i]);
            				} else if (i == 3) {
            					$(this).find("form").attr("action","/"+val[1]);
            					if (val[i] == true) {
            						$($(this).find("#isSelected1")).prop("checked", true);
            						$($(this).find("#isSelected2")).prop("checked", false);
            					} else {
            						$($(this).find("#isSelected1")).prop("checked", false);
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
    
    $("input:radio[name='is_selected']").on('change', function () {
    	$(this).closest("form").submit();
    });
    
//	$(".users").show();
//	$(".questions").hide();
//	$(".survey").hide();
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
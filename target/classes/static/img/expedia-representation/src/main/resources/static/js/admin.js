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
			$(".question").show();
			$(".survey").hide();
			$(".users").hide();
		} else if ($(this).attr("id") == "showData2") {
			$(".question").hide();
			$(".survey").show();
			$(".users").hide();
		} else {
			$(".question").hide();
			$(".survey").hide();
			$(".users").show();
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
    
	var innerHtml = "<tr class='inner' >" +
			"<td style='word-wrap: break-word'><td>" +
			"<td width='1px' style='word-wrap: break-word'></td>" +
			"<td style='word-wrap: break-word'></td>" +
			"<td width='40%' style='word-wrap: break-word'>" +
			"<form action='' method='post' >" +
			"<input type='hidden' name='_method' value='put'>" +
			"<div class='radio text-center'>" +
			"<label><input id='isSelected1' name='is_selected' type='radio' value='true' >済み</label> " +
			"<label><input id='isSelected2' name='is_selected' type='radio' value='false' >準備</label>" +
			"</div>" +
			"</form>" +
			"</td>" +
			"<td style='word-wrap: break-word'></td>" +
			"<td style='word-wrap: break-word'></td>" +
			"</tr>";
    // Array stack to reverse order by question no
    $.each((questionList), function(idx, val) {
    	$('.q tbody').append(innerHtml);
        $('.q').each(function() {
        	$(this).find('tbody').each(function() {
        		$(this).find('.inner').each(function() {
        			if ($(this).index() == idx) {
        				console.log(idx+" >> "+val);
            			$(this).find('td').each(function(i) {
            				if (i == 1) {
            				} else if (i == 4) {
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
    
	var innerHtmlTot = "<tr class='innerTot' >" +
	"<td style='word-wrap: break-word'><td>" +
	"<td style='word-wrap: break-word'><td>" +
	"<td style='word-wrap: break-word'></td>" +
	"<td style='word-wrap: break-word'></td>" +
	"</tr>";
	
    $.each(surveySums, function(idx, val) {
    	
    	$('.s tbody').append(innerHtmlTot);
        $('.s').each(function() {
        	$(this).find('tbody').each(function() {
        		$(this).find('.innerTot').each(function() {
        			if ($(this).index() == idx) {
            			$(this).find('td').each(function(i) {
            				$(this).text(val[i]);
            			});
        			}
        		});
        	});        	
    	});
	});
    $("input:radio[name='is_selected']").on('change', function () {
    	$(this).closest("form").submit();
    });
	$(".question").show();
	$(".survey").hide();
	$(".users").hide();
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
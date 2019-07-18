/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var apiKey = "";
const seminarId = "20190313-01";
// elements
var encryptId = "";
var surveyQuestions = [];
var surveyAnswers = [];
var checkBoxVals = [];

var surveyAnswerInfos;

$(document).ready(function(){

	// when range bar value changes, then the answer field  is changed follow to the range bar value
	// some of browser couldn't detect class selector
	$('input[type=range]').on('input', function () {
		var target_id = $(this).attr("id");
		var target_number = target_id.substring(1,2);
		console.log(target_number);
		// reset the data, and focus out from the range
		$("input[id=q"+target_number+"_answer]").val($(this).val()).blur();
	});
	
	// Synchronize with range bar
	$('input[type=text]').on('change', function () {
		var target_id = $(this).attr("id");
		console.log(target_id+": "+target_id.substring(1,2)+" triggerd ");
		var target_number = target_id.substring(1,2);
		// reset the data, and focus out from the range and input field
		$("input[id=q"+target_number+"]").val($(this).val()).blur();
		$(this).blur();
	});
	
	// Synchronize with hidden value
	$('input[type=hidden]').on('change', function () {
		checkBoxVals = ($(this).val()).replace('"','').replace('[','').replace(']','').split(',');
		for (var i=0; i<$("input[id=q4_multple_answer]").length; i++) {
			for (var j=0; j<checkBoxVals.length; j++) {
				if ($($("input[id=q4_multple_answer]").get(i)).val() == checkBoxVals[j]) {
					// reset the data, and focus out from the range and input field
					$($("input[id=q4_multple_answer]").get(i)).prop('checked', true).blur();
					$(this).blur();
				}
			}
		}
	});
	
	// gathering the check box values. 
	// If it has checked value then put into hidden element value. Otherwise, remove from the array.
	$('input#q4_multple_answer').on('change', function () {
		var isChecked = $(this).is(':checked');
		if (isChecked) {
			checkBoxVals.push($(this).val());
		} else {
			var idx = checkBoxVals.indexOf($(this).val());
			if (idx > -1) {
				checkBoxVals.splice(idx, 1);
			}
		}
	});
	
	// Submit survey answers
	$("#submit").click(function(){
		var encryptId = $('#encryptId').val();
		var seminarId = $('#seminarId').val();
		let surveyInfo = [];
		$('input#q4_answer').val(checkBoxVals);
		$(".survey_answers").each(function() {
			var target_id = $(this).attr("id");
			var target_number = target_id.substring(1,2);
			var obj = {"seminar_id":seminarId,"encrypt_id":encryptId,"survey_no":target_number,"survey_answer": $(this).val()};
			surveyInfo.push(obj);
		});
	    
	    console.log(JSON.stringify(surveyInfo));
	    
        $.ajax({
	            url: 'https://seminar-web.herokuapp.com/survey/api/add',
//	            headers: {"api-key": apiKey},
	            type: 'POST',
	            contentType: "application/json",
	            dataType: 'json',
	            data: JSON.stringify(surveyInfo),
	            // if it could put user data
            success: function(data, status, xhr) { 

            },
            // if it couldn't put user data by error
            error: function(xhr, status, err) { 
    			// show error if it has
            	showError(err);
            },
            // very necessary, if it is not work, then callback function never ending
            complete: function (xhr, status) {

            }
        });
	});
	
    $.each(surveyAnswerInfos, function(idx, val) {
        $.each(val, function(key, value) {
			if (key == "survey_no") {
				ans = JSON.parse(JSON.stringify(val)).survey_answer;
				$($(".survey_answers").get(parseInt(value)-1)).val(ans).trigger("change");
			}
  		});
	});
});



/**
 * survey questions set up to each HTML question field
 * 
 * @param questions
 * @returns
 */
function setSurveyQuestion(questions) {
	// questions filed length
	var questionsFieldLength = $(".survey_question").length;
	// validate questions count
	if (questionsFieldLength != questions.length) {
		showError("アンケート問題に不正があります");
		return;
	}
	// the HTML question field already has been field value as the default.
	// As this function make a reset to renewal each field through the got data.
	for(var i=0; i<questions.length; i++) {
		surveyQuestions[i] = JSON.parse(JSON.stringify(questions[i].surveyContent));
		$($(".survey_question").get(i)).text("Q"+(i+1)+". "+surveyQuestions[i]);
	}
}

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
/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
// API key
var apiKey = "";
// parameters from privacy policy viewer
var param = "";
// elements
var userId = "";
var displayName = "";
var userName = "";
var companyName = "";
var jobType = "";
var isConfirmed = false;

$(document).ready(function(){
	/**
	 * Initializing for LIFF
	 * 
	 * @returns null
	 */
	liff.init( function (data) {
		// set userId from liff.init data
		userId = data.context.userId;
		$("#userId").val(userId);
		// get user profile from LIFF API
		getProfile();
		//
	    var sendData = {"userId":userId}
//
//        $.ajax({
//            url: 'https://seminar-web.herokuapp.com/personal_information/api/'+userId,
////            headers: {"api-key": apiKey},
//            type: 'GET',
//            contentType: "application/json",
//            dataType: 'json',
////            data: JSON.stringify(sendData),
//            // if it could put user data
//            success: function(data, status, xhr) { 
//            	// nothing to do
//            },
//            // if it couldn't put user data by error
//            error: function(xhr, status, err) { 
//    			// show error if it has
//            	showError(err);
//            },
//            // very necessary, if it is not work, then callback function never ending
//            complete: function (xhr, status) {
//alert(data);
//            }
//        });
	}, err => {
		showError(err);
	});

	// show privacy policy viewer
	$("a#show_privacy_policy").click(function(){
        liff.openWindow({
            url: 'line://app/1588275184-MOX7DGRm/?user_name='
            	+$("input#user_name").val()+'&company_name='+$("input#company_name").val()
            	+'&job_type='+$($('select#position_name').find(":selected")).val(),
            external: false
        });
	});
	
	// seminar join
	$("button#confirm").click(function(){
	    var sendData = {
	    	"userId":userId,
	    	"displayName":displayName,
	    	"userName":$("input#user_name").val(),
	    	"companyName":$("input#company_name").val(),
	    	"jobType":$($('select#position_name').find(":selected")).val(),
	    	"policyFlag":($("span#confirmation").text() == "済み")?"y":"n"
	    }

        $.ajax({
            url: 'https://www.changchao.me/api/u/join',
            headers: {"api-key": apiKey},
            type: 'POST',
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(sendData),
            // if it could put user data
            success: function(data, status, xhr) { 
            	// nothing to do
            },
            // if it couldn't put user data by error
            error: function(xhr, status, err) { 
    			// show error if it has
            	showError(err);
            },
            // very necessary, if it is not work, then callback function never ending
            complete: function (xhr, status) {
            	liff.closeWindow();
            }
        });
	});
	
	// Seminar join submit
	$(document).on("click", "#submit", function(){
		
//		alert("submit >> "+$("#userId").val()+", "+$("#displayName").val()+", "+$("#displayName").val());
	    var sendData = {
	    	"userId":userId
	    }

//        $.ajax({
//            url: 'https://www.changchao.me/api/u/quit',
//            headers: {"api-key": apiKey},
//            type: 'POST',
//            contentType: "application/json",
//            dataType: 'json',
//            data: JSON.stringify(sendData),
//            // if it could put user data
//            success: function(data, status, xhr) { 
//            	// nothing to do
//            },
//            // if it couldn't put user data by error
//            error: function(xhr, status, err) { 
//    			// show error if it has
//            	showError(err);
//            },
//            // very necessary, if it is not work, then callback function never ending
//            complete: function (xhr, status) {
//            	liff.closeWindow();
//            }
//        });
	});
});

/**
 * getProfile
 * 
 * @returns null
 */
function getProfile() {
	liff.getProfile().then(function(profile) {
		displayName = profile.displayName;
		
		$("#userPhoto").attr("src", profile.pictureUrl).attr('alt', profile.pictureUrl);
		$("#headerDisplayName").text(displayName);
		$("#displayName").val(displayName);

	}).catch((err) => {
		showError(err);
	});
}

/**
 * setProfile
 * 
 * @returns null
 */
function setProfile(data_, isConfirmed) {
	// get user profile from LIFF API
	getProfile();
	// if it has not user data
	if ('{}' == JSON.stringify(data_)) {
		// show the privacy policy confirmation
		if ("" != isConfirmed) {
			$("input#user_name").val(userName);
			$("input#company_name").val(companyName);
			$("select#position_name").val(jobType);
			togglePrivacyPolicyConfirmation(true);
		}
	} else {
		var data = JSON.parse(JSON.stringify(data_));
		$("input#user_name").val(data.userName);
		$("input#company_name").val(data.companyName);
		$("select#position_name").val(data.jobType);
		// from privacy policy
		if ("" != isConfirmed) {
			togglePrivacyPolicyConfirmation(true)
		} else {
			(data.policyFlag == "y")?togglePrivacyPolicyConfirmation(true):togglePrivacyPolicyConfirmation(false);
		}
		$("button#confirm").text("　修　正　").after("　<button class='btn remove' id='remove'>　不参加　</button>");
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
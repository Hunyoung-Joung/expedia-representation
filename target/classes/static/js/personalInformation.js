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
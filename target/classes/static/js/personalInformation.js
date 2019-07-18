/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

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
 * 
 * Error response
 * 
 * @param err
 * @returns
 */
function showError(err) {
	$("div.error_division").text(err).attr("tabindex",-1).focus();
}
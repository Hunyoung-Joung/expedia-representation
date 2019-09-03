/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var encryptId = "";

$(document).ready(function(){

	$("#show_map").click(function(){
        window.open('https://www.relo-kaigi.jp/comfort/shinjyuku/access/','popUpWindow','height=500,width=500,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes');
	});
	
    $("#reflesh").click(function() {
        location.replace("https://seminar-web.herokuapp.com/seminar?encryptId="+encryptId);
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
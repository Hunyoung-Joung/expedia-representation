/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */

var category = "";
var content = "";

$(document).ready(function(){

	$("#show_map").click(function(){
        window.open('https://www.relo-kaigi.jp/comfort/shinjyuku/access/','popUpWindow','height=500,width=500,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes');
	});
	
    $(".reflesh").click(function() {
        location.reload();
    });
    
//    $("#submit").on('click', function () {
//    	console.log(" click "+$(this).closest("form").html());
//    	$(this).closest("form").submit();
//    });

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
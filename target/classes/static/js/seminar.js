/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
// API key
// API key
var apiKey = "";
// elements
var userId = "";
var seminarId = "";
var category = "";
var content = "";

$(document).ready(function(){

	$("#show_map").click(function(){
        window.open("https://www.relo-kaigi.jp/comfort/shinjyuku/access/",'popUpWindow','height=500,width=400,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');
	});
	
	$("textarea#content").mouseover(function(){
		$(this).val("");
	}).mouseleave(function(){
		if ($(this).val() == "") {
			$(this).val("セミナー内容に対して質問をしてください");
		}
	});
	
	// send question
	$("button#submit").click(function(){
		category = $($('select#category').find(":selected")).val();
		content = $("textarea#content").val();
		
		if ((content == "") || (content == "セミナー内容に対して質問をしてください") || (category == "0")) {
			showError("エラー：カテゴリーが選択されていないか、質問が入力されていません");
			return;
		}
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
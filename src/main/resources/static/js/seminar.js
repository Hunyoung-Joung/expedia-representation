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
	
	$("#submit").click(function(){
		category = $($('#qCategory').find(":selected")).val();
		content = $("#qContents").val();
		
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
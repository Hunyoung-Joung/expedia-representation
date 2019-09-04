/**
 * Copyright@
 * by jounghunyoung@gmail.com
 * 
 */

var adminQuestionInfos;
var questionList = [];

$(document).ready(function(){
	// occupancy=2-9,4 -> adult 2, 9 years old 1, 4 years old 1
    $(".input-number-group").on('mousedown', function (event) {
    	var id = event.target.id;
		var adult_count = parseInt($("#adult_count").val());
		var child_count = parseInt($("#child_count").val());
		var room_count = parseInt($("#room_count").val());
		
		if (id == "adult_count_increase") {
			if (adult_count < 14) {
				adult_count = parseInt($("#adult_count").val())+1;
				$("#adult_count").val(adult_count);
			}
		} else if (id == "adult_count_decrease") {
			if (adult_count > 1) {
				adult_count = parseInt($("#adult_count").val())-1;
				$("#adult_count").val(adult_count);
			}
		} else if (id == "child_count_increase") {
			if (child_count < 6) {
				child_count = parseInt($("#child_count").val())+1;
				$("#child_count").val(child_count);
				$("#child_ages").append("<div class='dropdown'>" +
						"<button class='btn btn-primary dropdown-toggle' type='button' data-toggle='dropdown'>　年齢 "+child_count+"　<span class='caret'></span></button" +
						"<ul class='dropdown-menu'>" +
						"<li><a href="#">0</a></li>" +
						"<li><a href="#">1</a></li>" +
						"<li><a href="#">2</a></li>" +
						"<li><a href="#">3</a></li>" +
						"<li><a href="#">4</a></li>" +
						"<li><a href="#">5</a></li>" +
						"<li><a href="#">6</a></li>" +
						"<li><a href="#">7</a></li>" +
						"<li><a href="#">8</a></li>" +
						"<li><a href="#">9</a></li>" +
						"<li><a href="#">10</a></li>" +
						"<li><a href="#">11</a></li>" +
						"<li><a href="#">12</a></li>" +
						"<li><a href="#">13</a></li>" +
						"<li><a href="#">14</a></li>" +
						"<li><a href="#">15</a></li>" +
						"<li><a href="#">16</a></li>" +
						"<li><a href="#">17</a></li>" +
						"</ul></div>");
			}
		} else if (id == "child_count_decrease") {
			if (child_count > 0) {
				child_count = parseInt($("#child_count").val())-1;
				$("#child_count").val(child_count);
			}
		} else if (id == "room_count_increase") {
			if (child_count < 8) {
				child_count = parseInt($("#room_count").val())+1;
				$("#room_count").val(room_count);
			}
		} else if (id == "room_count_decrease") {
			if (room_count > 1) {
				room_count = parseInt($("#room_count").val())-1;
				$("#room_count").val(child_count);
			}
		} 
	});
	
    $("#person_count_modal_confirm").click(function(){
    	$("#person_count").val("大人"+$("#adult_count").val()+","+"子供"+$("#child_count").val());
    	$("#occupancy").val($("#adult_count").val()+"-"+$("#child_count").val());
	});
});
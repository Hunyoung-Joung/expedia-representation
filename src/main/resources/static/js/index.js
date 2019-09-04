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
				$("#child_ages").html("<div class='dropdown'>" +
						"<button class='btn btn-primary dropdown-toggle' type='button' data-toggle='dropdown'>年齢<span class='caret'></span></button" +
						"<ul class='dropdown-menu'>" +
						"<li>0</li>" +
						"<li>1</li>" +
						"<li>2</li>" +
						"<li>3</li>" +
						"<li>4</li>" +
						"<li>5</li>" +
						"<li>6</li>" +
						"<li>7</li>" +
						"<li>8</li>" +
						"<li>9</li>" +
						"<li>10</li>" +
						"<li>11</li>" +
						"<li>12</li>" +
						"<li>13</li>" +
						"<li>14</li>" +
						"<li>15</li>" +
						"<li>16</li>" +
						"<li>17</li>" +
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
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
				$("#child_ages_field").append("<div class='col-xs-2'><input type='text' class='form-control' id='child_ages_field_"+child_count+"' readonly></div>");
				$("#child_ages").append("<li id='child_age_"+child_count+"' class='find_out'><div class='dropdown'>" +
						"<button class='btn btn-primary dropdown-toggle' type='button' data-toggle='dropdown'>子供年齢 "+child_count+"<span class='caret'></span></button>" +
						"<ul class='dropdown-menu'>" +
						"<li><a href='#'>0</a></li>" +
						"<li><a href='#'>1</a></li>" +
						"<li><a href='#'>2</a></li>" +
						"<li><a href='#'>3</a></li>" +
						"<li><a href='#'>4</a></li>" +
						"<li><a href='#'>5</a></li>" +
						"<li><a href='#'>6</a></li>" +
						"<li><a href='#'>7</a></li>" +
						"<li><a href='#'>8</a></li>" +
						"<li><a href='#'>9</a></li>" +
						"<li><a href='#'>10</a></li>" +
						"<li><a href='#'>11</a></li>" +
						"<li><a href='#'>12</a></li>" +
						"<li><a href='#'>13</a></li>" +
						"<li><a href='#'>14</a></li>" +
						"<li><a href='#'>15</a></li>" +
						"<li><a href='#'>16</a></li>" +
						"<li><a href='#'>17</a></li>" +
						"</ul></div></li>");
			}
		} else if (id == "child_count_decrease") {
			if (child_count > 0) {
				child_count = parseInt($("#child_count").val())-1;
				$("#child_count").val(child_count);
				var child_ages_list = document.getElementById("child_ages");
				child_ages_list.removeChild(child_ages_list.childNodes[child_count]);
				var child_ages_field_list = document.getElementById("child_ages_field");
				child_ages_field_list.removeChild(child_ages_field_list.childNodes[child_count]);				
			}
		} else if (id == "room_count_increase") {
			if (room_count < 8) {
				room_count = parseInt($("#room_count").val())+1;
				$("#room_count").val(room_count);
			}
		} else if (id == "room_count_decrease") {
			if (room_count > 1) {
				room_count = parseInt($("#room_count").val())-1;
				$("#room_count").val(room_count);
			}
		} 
	});
    
    $("#child_ages").on('click', function (event) {
    	var id =  $(event.target).parents().map(function() {
    		if ($(this).attr("class") == "find_out") {
    			return $(this).attr("id");
    		}
    	}).get();

    	console.log("id? "+id.toString().replace("child_age_",""));
    	var val = $(event.target).text();
    	console.log("val? "+val);
    	if (val != val.startsWith("子供年齢")) {
    		$("#child_ages_field_"+id.toString().replace("child_age_","")+"").val(val);
    	}
    });
	
    $("#person_count_modal_confirm").click(function(){
    	$("#child_ages_field").each(function () {
    		console.log(idx+" - "+$(val).attr());
    		$(this).find("input").each(function(idx, val){
    			console.log(idx+" - "+$(val).attr("id"));
    		});
    	});
    	
    	$("#person_count").val("大人"+$("#adult_count").val()+","+"子供"+$("#child_count").val()+","+"部屋"+$("#room_count").val());
    	$("#occupancy").val($("#adult_count").val()+"-"+$("#child_count").val());
	});
});

function sendDataToController() {
	debugger;
	var username = $("#username").val();
	var password = $("#password").val();
	var email = $("#email").val();
	var role = $("#role").val();
/*var user = $("#form").serialize();*/

var user = {}
    user["name"] = $("#username").val();
/*data: JSON.stringify({
			name: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			role: $("#role").val()
		}),*/

	$.ajax({
		type: "POST",
		url: "/registration",
		data: JSON.stringify({
			name: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			role: $("#role").val()
		}),
		contentType: "application/json",
		success: function(data) {
			console.log("SUCCESS: ", data);
		},
		error: function(e) {
			console.log("ERROR: ", e);
		},
		done: function(e) {
			console.log("DONE");
		}
	});

}

function authenticate() {
	debugger;
	$.ajax({
		type: "POST",
		url: "/Dologin",
		data: {},
		contentType: "application/json",
		success: function(data) {
			console.log("SUCCESS: ", data);
		},
		error: function(e) {
			console.log("ERROR: ", e);
		},
		done: function(e) {
			console.log("DONE");
		}
	});

}
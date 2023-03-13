
function hideElement()
{
	document.getElementById("successMessage").style.display === "none";
}

function sendDataToController() {
	debugger;

var sus = null;
	$.ajax({
		type: "POST",
		url: "/registration",
		async: false,
		data: JSON.stringify({
			name: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			role: $("#role").val(),
			contactNumber: $("#contactNumber").val()
		}),
		contentType: "application/json",
		success: function(data) {
			window.location.href = '/home'; 
		},
		error: function(e) {
			debugger;
			console.log("ERROR: ", e);
			document.getElementById("successMessage1").innerHTML="Failed to Register";
		},
		done: function(e) {
			console.log("DONE");
		}
	});
event.preventDefault();
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
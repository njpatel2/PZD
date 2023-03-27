
function hideElement()
{
	document.getElementById("successMessage").style.display === "none";
}

function sendDataToController() {
	debugger;

var sus = null;
var email= $("#email").val();
	var	password= $("#password").val();
	var	role= $("#role").val();

	$.ajax({
		type: "POST",
		url: "/registration",
		async: false,
		data: JSON.stringify({
			name: $("#name").val(),
			email: $("#email").val(),
			password: $("#password").val(),
			role: $("#role").val(),
		}),
		contentType: "application/json",
		success: function(data) {
			window.location.href = '/login'; 
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
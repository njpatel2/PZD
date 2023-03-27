/**
 * 
 */

 
 $(document).ready(function() {

});

function doAjaxCall(callUrl, callType, callData) {
	debugger;
	var returnData;
	$.ajax({
		type: callType,
		url: callUrl,
		async: false,
		data: callData,
		contentType: "application/json",
		success: function(data) {
			returnData = data;
			debugger;

		},
		error: function(e) {
			console.log(e);
		},
		done: function(e) {
			console.log("DONE");
		}
	});
	/*event.preventDefault();*/
	debugger;
	return returnData;
}

function openUpdateProfileModel(email) {
	debugger;
	document.getElementById("emailBlock").innerHTML = email;
	$('#updateProfileModel').modal('show');
	debugger;
	
	document.getElementById("updatedUsername").value =document.getElementById("name").innerHTML;
	document.getElementById("updatedContact").value = document.getElementById("contact").innerHTML;
	document.getElementById("updatedAddress").value = document.getElementById("address").innerHTML;
	
}
function updateUserProfile()
{
		document.getElementById("closeUpdateProfileModel").click();	
	debugger;
	var sendData = JSON.stringify({
		email: document.getElementById("email").innerHTML,
		name:  $("#updatedUsername").val(),
		contact: $("#updatedContact").val(),
		address: $("#updatedAddress").val()
	});
	

	
	var result = doAjaxCall('/user/updateUserProfile', 'POST', sendData);

}

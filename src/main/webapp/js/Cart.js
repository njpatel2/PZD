/**
 * 
 */

var stompClient = null;
var socket = null;

/*export{stompClient , socket};*/

$(document).ready(function() {

	if (document.querySelector("#cartItemsDiv")) {
		getAllCartItems();

	}

	getTotalCartPrice();
	connect();
});

function connect(){
	
	 socket = new SockJS('/websocket');
	 stompClient = Stomp.over(socket);

		stompClient.connect({}, function(frame) {
			console.log('Connected: ' + frame);
		});
	
}

function sendAlert(){
	var message = {
				'username': 1,
				'orderDetails': 1
			};
	stompClient.send('/app/admin/alert', {}, JSON.stringify(message), function() {
				console.log('Message sent'); // add this line
			});
}
/*function confirmUserDetails(){
	debugger;
	var sendData = JSON.stringify({
		contact:  $("#contactNumber").val(),
		address: $("#address").val(),
	});
	debugger;
	var encodedData = encodeURIComponent(sendData);
	var returnData;
	$.ajax({
		type: 'POST',
		url: '/user/confirmUserDetails',
		async: false,
		data: {data: encodedData},
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
	debugger;
	/*var result = doAjaxCall('/user/confirmUserDetails', 'GET', encodedData);
}*/

function getUserDetails(){
	
	$('#confirmDetailsModel').modal('show');
	
		var sendData = JSON.stringify();
	var result = doAjaxCall('/user/getUserDetails', 'GET', sendData);
debugger;	
	document.getElementById("contactNumber").value =result.contact;
	document.getElementById("address").value = result.address;
	
}

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

function getAllCartItems() {
	debugger;
	var sendData = JSON.stringify();
	var result = doAjaxCall('/cart/getAllCartItems', 'GET', sendData);
	/*var mainContainer = document.createElement("div");*/
	document.getElementById("totalCartItems").textContent = result.length;
	document.getElementById('cartItemsDiv').innerHTML = '';
	debugger;
	for (var i = 0; i < result.length; i++) {

		// Create a table row element
		var tableRow = document.createElement("tr");
		tableRow.classList.add("on-hover-change-color");
		tableRow.id = 'product' + result[i].productId;



		var pImage = document.createElement("td");
		pImage.setAttribute("data-th", "image");

		// Create a column div element for the product image
		var imageDiv = document.createElement("div");
		/*imageDiv.classList.add("col-sm-3", "box", "box2", "text-left");*/

		var productImage = document.createElement("img");
		productImage.setAttribute("src", "/images/" + result[i].productImage);
		productImage.setAttribute("alt", "");
		productImage.classList.add("img-fluid", "d-none", "d-md-block", "rounded", "mb-2", "shadow", "custom-img-size");


		imageDiv.appendChild(productImage);
		pImage.appendChild(imageDiv);



		// Create a table data element for the product information
		var productData = document.createElement("td");
		productData.setAttribute("data-th", "Product");

		// Create a row div element for the product information
		var rowDiv = document.createElement("div");
		rowDiv.classList.add("row");



		// Create a column div element for the product name
		var nameDiv = document.createElement("div");
		/*nameDiv.classList.add("col-md-9", "text-left", "mt-sm-2","d-flex","align-items-center");*/
		nameDiv.classList.add("col-md-9", "text-left", "mt-sm-2");

		// Create a heading element for the product name
		var nameHeading = document.createElement("h4");
		nameHeading.textContent = result[i].productName;

		// Add the heading element to the name div
		nameDiv.appendChild(nameHeading);

		// Create a hidden input element for the product ID
		var productIdInput = document.createElement("input");
		productIdInput.setAttribute("type", "hidden");
		productIdInput.setAttribute("name", "productId");
		productIdInput.setAttribute("value", result[i].productId);

		// Add the image div, name div, and product ID input to the row div
		rowDiv.appendChild(nameDiv);
		rowDiv.appendChild(productIdInput);

		// Add the row div to the product data element
		productData.appendChild(rowDiv);

		// create the table cell element
		var tableCell = document.createElement("td");
		tableCell.setAttribute("data-th", "Price");
		tableCell.setAttribute("style", "color: white;");

		// create the price span element
		const priceSpan = document.createElement("span");
		priceSpan.classList.add("mr-2", "text-center", "white-text");
		priceSpan.innerText = '$' + (result[i].productPrice * 1).toFixed(2);

		// create the input container div
		const inputContainer = document.createElement("div");
		inputContainer.classList.add("d-flex", "align-items-center", "flex-wrap", "input-container");

		// create the input element
		const inputElement = document.createElement("input");
		inputElement.setAttribute("type", "number");
		inputElement.setAttribute("class", "form-control form-control-sm text-center mb-2");
		inputElement.setAttribute("name", "quantity");
		inputElement.setAttribute("value", result[i].productQuantity);
		inputElement.setAttribute("required", "required");
		inputElement.setAttribute("min", "1");
		inputElement.setAttribute("onchange", "updateCartProductQuantity(" + result[i].productId + ',' + result[i].productPrice + ")");
		inputElement.setAttribute("id", "quantity-input-"+result[i].productId);

		// add the input element and the delete button to the input container div
		inputContainer.appendChild(inputElement);
		/*inputContainer.appendChild(deleteButton);*/

		// add the price span element and the input container div to the table cell
		tableCell.appendChild(priceSpan);
		tableCell.appendChild(inputContainer);


		var delbtn = document.createElement("td");
		delbtn.setAttribute("class", "delbtn");

		const deleteButton = document.createElement("button");
		deleteButton.setAttribute("class", "btn btn-white border-secondary bg-white btn-md mb-2 ml-md-auto rounded");
		deleteButton.setAttribute("onclick", "deleteProductFromCart(" + result[i].productId + ")");

		const deleteIcon = document.createElement("i");
		deleteIcon.setAttribute("class", "fa fa-trash-o");
		deleteIcon.setAttribute("style", "font-size: 24px; color: red;");

		deleteButton.appendChild(deleteIcon);
		delbtn.appendChild(deleteButton);

		var subTotal = document.createElement("td");
		subTotal.setAttribute("data-th", "subTotal");

		// Create a row div element for the product information
		const subTotalValue = document.createElement("span");
		subTotalValue.classList.add("text-right", "white-text");
		subTotalValue.setAttribute("id", "subTotal" + result[i].productId);
		subTotalValue.innerText = '$' + (result[i].subTotal * 1).toFixed(2);

		subTotal.appendChild(subTotalValue);

		tableRow.appendChild(pImage);
		tableRow.appendChild(productData);
		tableRow.appendChild(tableCell);
		tableRow.appendChild(delbtn);
		tableRow.appendChild(subTotal);
		document.getElementById('cartItemsDiv').appendChild(tableRow);
	}




}

function addToCart(productId) {
	debugger;
	/*var productId = $("#productId").val();*/
	var quantity = $("#orderItemModelQuantity").val();
	var sendData = JSON.stringify({
		productId: productId,
		quantity: quantity

	});
	var result = doAjaxCall('/cart/addToCart', 'POST', sendData);

	var modal = document.querySelector('#orderItemModel');
	var inputFields = modal.querySelectorAll('input');
	inputFields.forEach((input) => {
		input.value = '';
	});
	document.getElementById("closeOrderItemModel").click();

	document.getElementById('orderItemModelQuantity').value = '1';

	/*$('#orderItemModel').modal('hide');*/
	getTotalCartPrice();
}

function getTotalCartPrice() {
	debugger;
	var sendData = JSON.stringify();
	var result = doAjaxCall('/cart/getTotalCartPrice', 'GET', sendData);

	document.getElementById("totalBill").textContent = result + ' $';

	if (document.querySelector("#totalPriceOfCart")) {
		document.getElementById("totalPriceOfCart").textContent = '$ ' + result;
	}
}

function deleteProductFromCart(productId) {
	debugger;

	var sendData = JSON.stringify({
		productId: productId
	});
	var result = doAjaxCall('/cart/deleteProductFromCart', 'POST', sendData);
	getAllCartItems();
	getTotalCartPrice();
}

function updateCartProductQuantity(productId, price) {
	debugger;

	var quantity = document.getElementById("quantity-input-"+productId).value;

	var sendData = JSON.stringify({
		productId: productId,
		quantity: quantity

	});
	var result = doAjaxCall('/cart/updateCartProductQuantity', 'POST', sendData);
	document.getElementById('subTotal' + productId).innerHTML = '$' + (quantity * price).toFixed(2);
	getTotalCartPrice();
}
function roughcode() {

	document.getElementById('totalCartItems').innerHTML = '5';

}
function resetModal(modalName) {
	var modal = document.querySelector('#' + modalName);
	modal.style.display = 'none';
	var inputFields = modal.querySelectorAll('input');
	inputFields.forEach((input) => {
		input.value = '';
	});
}

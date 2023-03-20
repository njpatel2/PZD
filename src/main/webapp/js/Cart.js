/**
 * 
 */


$(document).ready(function() {

	if (document.querySelector("#cartItemsDiv")) {
		getAllCartItems();

	}
	getTotalCartPrice();

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
		tableRow.id = 'product'+result[i].productId;

		// Create a table data element for the product information
		var productData = document.createElement("td");
		productData.setAttribute("data-th", "Product");

		// Create a row div element for the product information
		var rowDiv = document.createElement("div");
		rowDiv.classList.add("row");

		// Create a column div element for the product image
		var imageDiv = document.createElement("div");
		imageDiv.classList.add("col-sm-3", "box", "box2", "text-left");

		// Create an image element for the product image
		var image = document.createElement("img");
		image.setAttribute("src", "/images/" + result[i].productImage);
		image.setAttribute("alt", "");
		image.classList.add("img-fluid", "d-none", "d-md-block", "rounded", "mb-2", "shadow");

		// Add the image element to the image div
		imageDiv.appendChild(image);

		// Create a column div element for the product name
		var nameDiv = document.createElement("div");
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
		rowDiv.appendChild(imageDiv);
		rowDiv.appendChild(nameDiv);
		rowDiv.appendChild(productIdInput);

		// Add the row div to the product data element
		productData.appendChild(rowDiv);
		tableRow.appendChild(productData);
		// Create a table data element for the product price
		var priceData = document.createElement("td");
		priceData.setAttribute("data-th", "Price");
		priceData.style.color = "#fff";
		priceData.textContent = '$' + result[i].productPrice;
		tableRow.appendChild(priceData);
		// Create a table data element for the product quantity
		var quantityData = document.createElement("td");
		quantityData.setAttribute("data-th", "Quantity");
		quantityData.style.color = "#fff";

		// Create a quantity input element
		var quantityInput = document.createElement("input");
		quantityInput.setAttribute("type", "text");
		quantityInput.setAttribute("id",result[i].productId);
		quantityInput.setAttribute("name", "productQuantity");
		quantityInput.setAttribute("value", result[i].productQuantity);
		quantityInput.setAttribute("required", "true");
		quantityInput.setAttribute("min", "1");
		quantityInput.setAttribute("class", "form-control form-control-sm text-center");
		quantityInput.setAttribute("onchange", "updateCartProductQuantity(this.id)");

		// Add the quantity input element to the quantity data element
		quantityData.appendChild(quantityInput);
		tableRow.appendChild(quantityData);
		// Create a table data element for the product actions
		var actionsData = document.createElement("td");
		actionsData.setAttribute("data-th", "");
		actionsData.classList.add("actions");

		// Create a div element for the product actions
		var actionsDiv = document.createElement("div");
		actionsDiv.classList.add("text-center");

		// Create a button element for deleting the product from the cart
		var deleteButton = document.createElement("button");
		deleteButton.setAttribute("type", "button");
		deleteButton.setAttribute("id", result[i].productId);
		deleteButton.setAttribute("class", "btn btn-white border-secondary bg-white btn-md mb-2 rounded");
		deleteButton.setAttribute("onclick", "deleteProductFromCart(this.id)");
		deleteButton.innerHTML = '<i class="fa fa-trash-o" style="font-size: 31px; color: red;"></i>';

		actionsDiv.appendChild(deleteButton);
		actionsData.appendChild(actionsDiv);
		tableRow.appendChild(actionsData);
		/*mainContainer.appendChild(tableRow);*/
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

function updateCartProductQuantity(productId) {
	debugger;
	var quantity = document.getElementById("product"+productId).children[2].querySelector("input[name='productQuantity']").value;

	var sendData = JSON.stringify({
		productId: productId,
		quantity: quantity

	});
	var result = doAjaxCall('/cart/updateCartProductQuantity', 'POST', sendData);
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

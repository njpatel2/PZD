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
		/*rowDiv.appendChild(imageDiv);*/
		rowDiv.appendChild(nameDiv);
		rowDiv.appendChild(productIdInput);

		// Add the row div to the product data element
		productData.appendChild(rowDiv);
		
		// Create a table data element for the product price
		/*var priceData = document.createElement("td");
		priceData.setAttribute("data-th", "Price");
		priceData.style.color = "#fff";
		priceData.textContent = '$' + result[i].productPrice;
		tableRow.appendChild(priceData);*/

		// create the table cell element
		var tableCell = document.createElement("td");
		tableCell.setAttribute("data-th", "Price");
		tableCell.setAttribute("style", "color: white;");

		// create the price span element
		const priceSpan = document.createElement("span");
		priceSpan.classList.add("mr-2", "text-center", "white-text");
		priceSpan.innerText = "$492.00";

		// create the input container div
		const inputContainer = document.createElement("div");
		inputContainer.classList.add("d-flex", "align-items-center", "flex-wrap", "input-container");

		// create the input element
		const inputElement = document.createElement("input");
		inputElement.setAttribute("type", "text");
		inputElement.setAttribute("class", "form-control form-control-sm text-center mb-2");
		inputElement.setAttribute("name", "quantity");
		inputElement.setAttribute("value", "1");
		inputElement.setAttribute("required", "required");
		inputElement.setAttribute("min", "1");
		inputElement.setAttribute("onchange", "updateCartProductQuantity()");
		inputElement.setAttribute("id", "quantity-input");

		// create the delete button element


		// add the input element and the delete button to the input container div
		inputContainer.appendChild(inputElement);
		/*inputContainer.appendChild(deleteButton);*/

		// add the price span element and the input container div to the table cell
		tableCell.appendChild(priceSpan);
		tableCell.appendChild(inputContainer);

		// add the table cell to the table row or table body element
		// e.g., let tableRow = document.createElement("tr"); tableRow.appendChild(tableCell);
		// or, let tableBody = document.createElement("tbody"); tableBody.appendChild(tableRow);


		/*// Create a table data element for the product quantity
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
		actionsData.appendChild(actionsDiv);*/

		var delbtn = document.createElement("td");
		delbtn.setAttribute("class", "delbtn");

		const deleteButton = document.createElement("button");
		deleteButton.setAttribute("class", "btn btn-white border-secondary bg-white btn-md mb-2 ml-md-auto rounded");
		deleteButton.setAttribute("onclick", "deleteProductFromCart(this)");

		const deleteIcon = document.createElement("i");
		deleteIcon.setAttribute("class", "fa fa-trash-o");
		deleteIcon.setAttribute("style", "font-size: 24px; color: red;");

		deleteButton.appendChild(deleteIcon);
		delbtn.appendChild(deleteButton);

		var subTotal = document.createElement("td");
		subTotal.setAttribute("data-th", "subTotal");

		// Create a row div element for the product information
		const subTotalValue = document.createElement("span");
		subTotalValue.classList.add( "text-right", "white-text");
		subTotalValue.innerText = "$492.00";

		subTotal.appendChild(subTotalValue);
		
		tableRow.appendChild(pImage);
		tableRow.appendChild(productData);
		tableRow.appendChild(tableCell);
		tableRow.appendChild(delbtn);
		tableRow.appendChild(subTotal);
		/*tableRow.appendChild(actionsData);*/
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
	var quantity = document.getElementById("product" + productId).children[2].querySelector("input[name='productQuantity']").value;

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

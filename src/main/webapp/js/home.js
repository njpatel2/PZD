/**
 * 
 */

//code for showing item on ui is written in main.js to apply animation	

$(document).ready(function() {
	makeItemList();
});

function showOrderItemPopUp(selectedProduct) {
	debugger;
	var xx = selectedProduct;
	$('#orderItemModel').modal('show');
	const id = selectedProduct.getAttribute('id');
	var imgURL = selectedProduct.querySelector(".img").style.backgroundImage;
	const productName = selectedProduct.querySelector("h3").textContent;
	const productDescription = selectedProduct.querySelector("p:nth-of-type(1)").textContent;
	const productPrice = selectedProduct.querySelector(".box-price").textContent;

	imgURL = imgURL.replace('url("', '').replace('")', '');
	console.log(imgURL); // Output: /images/pizza-1.jpg

	const modal = document.getElementById("orderItemModel");
	modal.querySelector(".product-image img").src = imgURL;
	modal.querySelector(".product-info h2").textContent = productName;
	modal.querySelector(".product-info p").textContent = productDescription;
	modal.querySelector(".price span").textContent = productPrice;
	modal.querySelector('input[name="productId"]').textContent = id;

	document.querySelector('.plus-btn').removeEventListener('click', incrementQuantity);
	document.querySelector('.plus-btn').addEventListener('click', incrementQuantity);

	document.querySelector('.minus-btn').removeEventListener('click', decrementQuantity);
	document.querySelector('.minus-btn').addEventListener('click', decrementQuantity);

	function incrementQuantity() {
		document.getElementById('orderItemModelQuantity').value++;
	}

	function decrementQuantity() {
		if (document.getElementById('orderItemModelQuantity').value > 1) {
			document.getElementById('orderItemModelQuantity').value--;
		}
	}
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
function sendForgotPasswordEmail(){
	debugger;
	var email = $('#email').val();
	
	var sendData = JSON.stringify({
		email: email
	});
	
	var result = doAjaxCall('/sendForgotPasswordEmail', 'POST', sendData);
	
	var sendBtn = document.getElementById("sendButton");
        sendBtn.textContent = "Resend";
    var otpField = document.getElementById("otpField");
        otpField.style.display = "block";
    var otpField = document.getElementById("verifyButton");
        otpField.style.display = "block";
	
}

function verifyOTP(){
	var OTP = $('#OTP').val();
	
	
	var sendData = JSON.stringify({
		OTP: OTP
	});
	
	var result = doAjaxCall('/sendForgotPasswordEmail', 'POST', sendData);
	
	if(result = 'Verified successfully'){
	}
}

function makeItemList()
{
	
	var sendData = JSON.stringify();
	var result = doAjaxCall('/product/getCategoryId', 'POST', sendData);
	debugger;
	for (var i = 0; i < result.length; i++) {
		const link = document.createElement("a");
  link.classList.add("nav-link", "active");
  link.id = "v-pills-1-tab";
  link.setAttribute("data-toggle", "pill");
  link.textContent = result[i].categoryTitle;
  link.setAttribute("onclick", 'displayProducts('+result[i].categoryId+')');
  const menuItemsDiv = document.getElementById("menuItems");
  menuItemsDiv.appendChild(link);
		
	}
	
  
}







/*
//get list of category(id,name)
function getProductList() {
	debugger;
	var productList;
	$.ajax({
		type: "GET",
		url: "/user/getItems",
		async: false,
		data: {},
		contentType: "application/json",
		success: function(data) {
			productList = data;
			debugger;

		},
		error: function(e) {
			console.log(e);
		},
		done: function(e) {
			console.log("DONE");
		}
	});

	return productList;

}

function showProducts() {
	debugger;
	var productList = getProductList();

var container = document.getElementById('productsList')
var isRight = true;
var n=0;
	for (var i = 0; i < productList.length; i++) {
	
			// create the outer div element
			var outerDiv = document.createElement("div");
			outerDiv.classList.add("col-lg-4", "d-flex", "ftco-animate");
			outerDiv.setAttribute("onclick","showOrderItemPopUp()");
			// create the inner div element
			var innerDiv = document.createElement("div");
			innerDiv.classList.add("services-wrap", "d-flex");

			// create the anchor element with background image
			var anchor = document.createElement("a");
			anchor.href = "#";
			anchor.classList.add("img");
			if(n<2 ){
				if(isRight){
				anchor.classList.add("order-lg-last");}
				n++;
			}
			else{
				n=0;
				isRight = !isRight
			}
			anchor.setAttribute('style','background-image: url(/images/'+ productList[i].pPhoto+');');
			
			//anchor.style.backgroundImage = productList[i].pPhoto;

			// create the text div element
			var textDiv = document.createElement("div");
			textDiv.classList.add("text", "p-4");

			// create the h3 element for the pizza name
			var pizzaName = document.createElement("h3");
			pizzaName.textContent = productList[i].pName;

			// create the p element for the description
			var description = document.createElement("p");
			description.textContent = productList[i].pDesc;

			// create the p element for the price and order button
			var priceOrder = document.createElement("p");
			priceOrder.classList.add("price");

			var price = document.createElement("span");
			price.textContent = '$ '+productList[i].pPrice;

			var orderButton = document.createElement("a");
			orderButton.href = "#";
			orderButton.classList.add("ml-2", "btn", "btn-white", "btn-outline-white");
			orderButton.id = productList[i].pId;
			orderButton.setAttribute('onclick', 'OrderItem('+productList[i].pId+')');
			orderButton.textContent = "Order";

			// append the elements to the parent elements
			priceOrder.appendChild(price);
			priceOrder.appendChild(orderButton);

			textDiv.appendChild(pizzaName);
			textDiv.appendChild(description);
			textDiv.appendChild(priceOrder);

			innerDiv.appendChild(anchor);
			innerDiv.appendChild(textDiv);

			outerDiv.appendChild(innerDiv);

			container.appendChild(outerDiv);

		
	}
//document.getElementById('productsList').innerHTML = '';
	document.getElementById('productsListParent').appendChild(container);

}
function OrderItem(id)
{
	
}*/
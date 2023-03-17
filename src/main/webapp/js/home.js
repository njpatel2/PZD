/**
 * 
 */

$(document).ready(function() {
  debugger;
  showProducts();
});

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
	
}
/**
 * 
 */


function doAjaxCall(callUrl,callType,callData)
{
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
	return returnData;
}

function getAllCartItems(){
	
	var result = doAjaxCall('/cart/getAllCartItems','GET',callData);
}

function addToCart(){
	
	var productId;
	var quantity;
	var sendData= JSON.stringify({
			productId:productId,
			quantity:quantity

		});
	var result = doAjaxCall('/cart/addToCart','POST',sendData);
}

function getTotalCartPrice(){
	
	var result = doAjaxCall('/cart/getTotalCartPrice','GET',callData);
}

function deleteProductFromCart(){
	
	var productId;
	var sendData= JSON.stringify({
			productId:productId
		});
	var result = doAjaxCall('/cart/deleteProductFromCart','POST',sendData);
}

function updateCartProductQuantity(){
	
	var productId;
	var quantity;
	
	var sendData= JSON.stringify({
			productId:productId,
			quantity:quantity

		});
	var result = doAjaxCall('/cart/updateCartProductQuantity','POST',sendData);
}


 
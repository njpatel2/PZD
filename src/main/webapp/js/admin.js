/**
 * 
 */

$(document).ready(function() {
	debugger;
	getCountOfCustomerProductCategory();
});

//get list of category(id,name)
function getCategorieList() {
	debugger;
	var categoryList;
	$.ajax({
		type: "GET",
		url: "/admin/getCategoryList",
		async: false,
		data: {},
		contentType: "application/json",
		success: function(data) {
			categoryList = data;
			debugger;

		},
		error: function(e) {
			console.log(e);
		},
		done: function(e) {
			console.log("DONE");
		}
	});

	return categoryList;

}

//functions for adding the category

function addCategory() {

	document.getElementById("addCategoryForm").reset();
	//debugger;
	$('#addCategoryModel').modal('show');
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


function getCountOfCustomerProductCategory()
{
	var sendData = JSON.stringify();
	var result = doAjaxCall('/admin/getCountOfCustomerProductCategory', 'GET', sendData);
	document.getElementById("userCount").innerHTML = result.userCount;
	document.getElementById("productCount").innerHTML = result.productCount;
	document.getElementById("categoryCount").innerHTML = result.categoryCount;
	
}

function getCustomerList()
{
	var sendData = JSON.stringify();
	var result = doAjaxCall('/admin/getCustomerList', 'GET', sendData);
	

}
function addCategoryToDatabase() {
	debugger;
	var category_Title = $("#categoryName").val();
	var category_Description = $("#categoryDescription").val();

	debugger;

	$.ajax({
		type: "POST",
		url: "/admin/addCategory",
		async: false,
		data: JSON.stringify({
			categoryTitle: $("#categoryName").val(),
			categoryDescription: $("#categoryDescription").val()

		}),
		contentType: "application/json",
		success: function(data) {

		},
		error: function(e) {

		},
		done: function(e) {
			console.log("DONE");
		}
	});
	event.preventDefault();

}

//functions for adding the product
function addProduct() {
	document.getElementById("addProductForm").reset();
	displayCategoryDropdown()
	debugger;
	$('#addProductModel').modal('show');
}
//make html code for category dropdown
function getCategotydropdown() {
	//get the list of categories
	var list = getCategorieList();

	const select = document.createElement('select');
	select.setAttribute('class', 'select2 categories');
	select.setAttribute('id', 'category');
	select.setAttribute('name', 'category');
	const option = document.createElement('option');
	option.setAttribute('class', 'categories');
	option.setAttribute('value', '');
	option.textContent = '--Select a category--';
	select.appendChild(option);

	for (var key in list) {
		if (list.hasOwnProperty(key)) {
			var value = list[key];
			const option = document.createElement('option');
			option.setAttribute('id', key)
			option.setAttribute('value', value);
			option.textContent = value;
			select.appendChild(option);
		}
	}
	//returning the html of dropdown of category
	return select;

}
//display the category dropdown in addProductModel
function displayCategoryDropdown() {
	debugger;
	var select = getCategotydropdown();

document.getElementById('listOfCategories').innerHTML = '';
	document.getElementById('listOfCategories').appendChild(select);

}
//insert the new product to database
function addProductToDatabase() {
	
	var selectElement = document.getElementById("category");
	var selectedOption = selectElement.querySelector("option:checked");
	var selectedOptionId = selectedOption.id;
	debugger;
	
	var formData = new FormData();
formData.append('pName', $("#product_name").val());
formData.append('pDesc', $("#product_description").val());
formData.append('pPrice', $("#product_price").val());
formData.append('pDiscount', $("#product_discount").val());
formData.append('categoryId', selectedOptionId);
formData.append('pQuantity', $("#product_count").val());
formData.append('pPhoto', document.getElementById("product_photo").files[0]);

$.ajax({
	type: "POST",
	url: "/admin/addProduct",
	 enctype: 'multipart/form-data',
	data: formData,
	contentType: false,
	processData: false,
	success: function(data) {
		console.log(data);
		debugger;
		// handle success
	},
	error: function(e) {
		// handle error
		console.log(e);
		debugger;
	},
	done: function(e) {
		console.log(e);
		debugger;
		// handle completion
	}
});

	event.preventDefault();

}

//functions for removing the category
//show the remove category model display the radio list of category
function removeCategoryShowModel() {

	document.getElementById("removeCategoryForm").reset();
	var radioDiv = displayCategoryList();

	document.getElementById('CategoryListContainer').innerHTML = '';
	document.getElementById('CategoryListContainer').appendChild(radioDiv);

	$('#removeCategoryModel').modal('show');

}

//delete category and its products from database
function RemoveCategoryFromDatabase() {
	var selectedProduct = document.querySelector('input[name="category"]:checked').id;

	$.ajax({
		type: "POST",
		url: "/admin/deleteCategory",
		async: false,
		data: JSON.stringify({
			categoryId: selectedProduct
		}),
		contentType: "application/json",
		success: function(data) {

		},
		error: function(e) {

		},
		done: function(e) {
			console.log("DONE");
		}
	});
	event.preventDefault();
}

//get the radio list of category
function displayCategoryList() {
	var categoryList = getCategorieList();

	var radioDiv = document.createElement('div');

	debugger;
	for (var key in categoryList) {
		if (categoryList.hasOwnProperty(key)) {
			var value = categoryList[key];

			var productRadio = document.createElement('input');
			productRadio.type = 'radio';
			productRadio.id = key;
			productRadio.name = 'category';
			productRadio.value = value;
			var productLabel = document.createElement('label');
			productLabel.htmlFor = key;
			productLabel.appendChild(document.createTextNode(value));
			radioDiv.appendChild(productRadio);
			radioDiv.appendChild(productLabel);
			radioDiv.appendChild(document.createElement("br"));
			console.log(key + ": " + value);
		}
	}

	return radioDiv;
}

//functions for removing the product
//making the category dropdown and setting onchange attribute to show user the products of selected category, then show the remove category model
function removeProduct() {
	document.getElementById('listOfProducts').innerHTML = '';
	document.getElementById("removeProductForm").reset();
	var list = getCategorieList();

	const select = document.createElement('select');
	select.setAttribute('class', 'select2 categories');
	select.setAttribute('id', 'category');
	select.setAttribute('name', 'category');
	select.setAttribute('onchange', 'showProductsForSelectedCategory()');
	const option = document.createElement('option');
	option.setAttribute('class', 'categories');
	option.setAttribute('value', '');
	option.textContent = '--Select a category--';
	select.appendChild(option);

	for (var key in list) {
		if (list.hasOwnProperty(key)) {
			var value = list[key];
			const option = document.createElement('option');
			option.setAttribute('id', key)
			option.setAttribute('value', value);
			option.textContent = value;
			select.appendChild(option);

		}
	}
	select.appendChild(document.createElement("br"));
	document.getElementById('listOfCategoriesToRemoveProduct').innerHTML = '';
	document.getElementById('listOfCategoriesToRemoveProduct').appendChild(select);
	$('#removeProductModel').modal('show');
}

//get the product list from database and show them as a radio list
function showProductsForSelectedCategory() {
	document.getElementById('listOfProducts').innerHTML = '';
debugger;
	var selectElement = document.getElementById("category");
	var selectedOption = selectElement.querySelector("option:checked");
	var selectedOptionId = selectedOption.id;

	var productList;

	$.ajax({
		type: "POST",
		url: "/admin/getProductList",
		async: false,
		data: JSON.stringify({
			categoryId: selectedOptionId
		}),
		contentType: "application/json",
		success: function(data) {
			productList = data;
		},
		error: function(e) {

		},
		done: function(e) {
			console.log("DONE");
		}
	});
	event.preventDefault();

	var radioDiv = document.createElement('div');

	debugger;
	for (var key in productList) {
		if (productList.hasOwnProperty(key)) {
			var value = productList[key];

			var productRadio = document.createElement('input');
			productRadio.type = 'radio';
			productRadio.id = key;
			productRadio.name = 'product';
			productRadio.value = value;
			var productLabel = document.createElement('label');
			productLabel.htmlFor = key;
			productLabel.appendChild(document.createTextNode(value));
			radioDiv.appendChild(productRadio);
			radioDiv.appendChild(productLabel);
			radioDiv.appendChild(document.createElement("br"));
			console.log(key + ": " + value);
		}
	}
document.getElementById('listOfProducts').innerHTML = '';
	document.getElementById('listOfProducts').appendChild(radioDiv);
}
// delete the product from database
function RemoveProductFromDatabase() {
	debugger;
	var selectedProduct = document.querySelector('input[name="product"]:checked').id;

	$.ajax({
		type: "POST",
		url: "/admin/deleteProduct",
		async: false,
		data: JSON.stringify({
			productId: selectedProduct
		}),
		contentType: "application/json",
		success: function(data) {
			productList = data;
		},
		error: function(e) {

		},
		done: function(e) {
			console.log("DONE");
		}
	});
}
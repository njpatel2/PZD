/**
 * 
 */

var audio = new Audio('/audio/alert.mp3');
var flag = false;
var inter = null;
var inter2 = null;
function myFunction() {
	console.log("Hello world!");
}

function showAlert(data) {
	debugger;
	$('#alertModel').modal('show');

	var body = data.body;
	var details = JSON.parse(body);


	var username = details.username;
	var orderId = details.orderDetails;

	var alertModel = document.querySelector('#alertModel');

	$("#alertModel").on('hide.bs.modal', function() {
		debugger;
		stopAlertAudio();
		console.log('The modal is about to be hidden.');
	});

	flag = false;
	playAlertAudio();
	inter = setInterval(playAlertAudio, 5000);
	var usernameSpan = document.querySelector('#username');
	var orderIdSpan = document.querySelector('#orderId');

	usernameSpan.textContent = username;
	orderIdSpan.textContent = orderId;


}


$(document).ready(function() {

	debugger;
	getCountOfCustomerProductCategory();
	$(function() {
		var socket = new SockJS('/websocket');
		var stompClient = Stomp.over(socket);

		stompClient.connect({}, function(frame) {
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/receivedAlert', function(message) {
				debugger;
				var alertMessage = JSON.parse(message.body).content;
				showAlert(message);

			});
		});


	});
});

function playAlertAudio() {
	debugger;
	if (!flag) {

		audio.play();
	}

}
function stopAlertAudio() {
	debugger;
	flag = true;
	audio.pause();
	audio.currentTime = 0;
	clearInterval(inter);
}


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


function getCountOfCustomerProductCategory() {
	var sendData = JSON.stringify();
	var result = doAjaxCall('/admin/getCountOfCustomerProductCategory', 'GET', sendData);
	document.getElementById("userCount").innerHTML = result.userCount;
	document.getElementById("productCount").innerHTML = result.productCount;
	document.getElementById("categoryCount").innerHTML = result.categoryCount;

}

/*function getCustomerList() {
	var sendData = JSON.stringify();
	var result = doAjaxCall('/admin/getCustomerList', 'GET', sendData);



}*/

var customerDetails = (function() {

	var getcustomerDetails = function() {
		bindcustomerDetailsGrid();

	}
	var bindcustomerDetailsGrid = function() {

		var cellRendererForOpen = function(row, columnfield, value, defaulthtml, columnproperties, rowdata) {
			if ($("#customerDetailsJqxGrid").jqxGrid('getrowdata', row) != undefined) {
				var theStatus = rowdata.isOpened;
				if (theStatus == false) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/hourGlassIcn.png">  ' + rowdata.invId + '  </div>'
				} else if (theStatus == true) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/searchIcn.png">  ' + rowdata.invId + '  </div>'
				}
			}
		};

		var url = '/admin/getCustomerDetails';
		var source = {
			datatype: "json",
			id: 'id',
			url: url,
			root: 'results',
		};
		var sendData = JSON.stringify({
			orderId: OrderId,
		});
		/*$("#customerDetailsJqxGrid").jqxGrid("clear");*/
		var adapter = new $.jqx.dataAdapter(source,
			{
				loadServerData: function(postdata, source, callback) {
					$.ajax({
						dataType: "json",
						cache: false,
						type: "GET",
						url: source.url,
						/*data: sendData,
						data: JSON.stringify({
							orderId: OrderId,
							postdata: postdata
						}),*/
						tryCount: 0,
						retryLimit: 2,
						/*data: postdata,*/
						contentType: "application/json",
						success: function(data, status, xhr) {
							debugger;
							var records = encodeXMLEscapeCharsForJQXGrid(data, data);
							adapter.loadjson(null, records, source);
							debugger;
							callback({ records: adapter.records });
							debugger;
						},
						error: function(xhr, textStatus, errorThrown) {

							if (textStatus == 'error' || textStatus == 'timeout') {
								this.tryCount++;
								function showMessage() {
									setTimeout(function() {
										$('.showError').show();
									}, 10000);
								}
								if (this.tryCount < this.retryLimit) {
									$.ajax(this);
									return;
								}
								return showMessage();
							}
						}
					});
				}
			});
		console.log(adapter.records)

		$("#customerDetailsJqxGrid").jqxGrid({
			width: '100%',
			height: 'auto',
			rowsheight: 60,
			columnsheight: 40,
			columnsresize: true,
			pageable: true,
			source: adapter,
			pagesize: 10,
			virtualmode: true,
			autoheight: true,
			rendergridrows: function() {
				debugger;
				return adapter.records;
			},
			editable: false,
			sortable: true,
			sorttogglestates: 0,
			filterable: true,
			showfilterrow: true,
			showsortcolumnbackground: true,
			groupable: true,

			columns: [
				{ text: 'Id', datafield: 'id', width: 50 },
				{ text: 'username', datafield: 'username', width: 100 },
				{ text: 'email', datafield: 'email', width: 130 },
				{ text: 'contact', datafield: 'contact', width: 100 },
				{ text: 'role', datafield: 'role', width: 80 },

			]
		});
	}
	return {
		getcustomerDetails: getcustomerDetails
	}

})();

function populatecustomerDetailsModal(orderId) {
	/*open modal*/
debugger;
	$('#customerDetailsModel').modal('show');

	OrderId = orderId;
	customerDetails.getcustomerDetails();

	/*bind grid data*/
}
function encodeXMLEscapeCharsForJQXGrid(records, original) {
	var data = new Array();
	data = original;
	// 	update the loaded records to escape special characters 
	for (var i = 0; i < records.length; i++) {
		for (var key in records[i]) {
			if (records[i].hasOwnProperty(key) && records[i][key] != null && records[i][key].constructor === String) {
				records[i][key] = encodeXMLEscapeChars(records[i][key]);
			}
		}
	}
	return records;
}
function encodeXMLEscapeChars(value) {
	var outPut = value;
	if ($.trim(outPut) != "") {
		outPut = outPut.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#39;");
		outPut = outPut.replace(/&(?!(amp;)|(lt;)|(gt;)|(quot;)|(#39;)|(apos;))/g, "&amp;");
		outPut = outPut.replace(/([^\\])((\\\\)*)\\(?![\\/{])/g, "$1\\\\$2");  //replaces odd backslash(\\) with even.
	} else {
		outPut = "";
	}

	return outPut;
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

var productDetails = (function() {

	var getproductDetails = function() {
		bindproductDetailsGrid();

	}
	var bindproductDetailsGrid = function() {

		var cellRendererForOpen = function(row, columnfield, value, defaulthtml, columnproperties, rowdata) {
			if ($("#productDetailsJqxGrid").jqxGrid('getrowdata', row) != undefined) {
				var theStatus = rowdata.isOpened;
				if (theStatus == false) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/hourGlassIcn.png">  ' + rowdata.invId + '  </div>'
				} else if (theStatus == true) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/searchIcn.png">  ' + rowdata.invId + '  </div>'
				}
			}
		};

		var url = '/admin/getProductDetails';
		var source = {
			datatype: "json",
			id: 'id',
			url: url,
			root: 'results',
		};
		var sendData = JSON.stringify({
			orderId: OrderId,
		});
		/*$("#productDetailsJqxGrid").jqxGrid("clear");*/
		var adapter = new $.jqx.dataAdapter(source,
			{
				loadServerData: function(postdata, source, callback) {
					$.ajax({
						dataType: "json",
						cache: false,
						type: "GET",
						url: source.url,
						/*data: sendData,
						data: JSON.stringify({
							orderId: OrderId,
							postdata: postdata
						}),*/
						tryCount: 0,
						retryLimit: 2,
						/*data: postdata,*/
						contentType: "application/json",
						success: function(data, status, xhr) {
							debugger;
							var records = encodeXMLEscapeCharsForJQXGrid(data, data);
							adapter.loadjson(null, records, source);
							debugger;
							callback({ records: adapter.records });
							debugger;
						},
						error: function(xhr, textStatus, errorThrown) {

							if (textStatus == 'error' || textStatus == 'timeout') {
								this.tryCount++;
								function showMessage() {
									setTimeout(function() {
										$('.showError').show();
									}, 10000);
								}
								if (this.tryCount < this.retryLimit) {
									$.ajax(this);
									return;
								}
								return showMessage();
							}
						}
					});
				}
			});
		console.log(adapter.records)

		$("#productDetailsJqxGrid").jqxGrid({
			width: '100%',
			height: 'auto',
			rowsheight: 60,
			columnsheight: 40,
			columnsresize: true,
			pageable: true,
			source: adapter,
			pagesize: 10,
			virtualmode: true,
			autoheight: true,
			rendergridrows: function() {
				debugger;
				return adapter.records;
			},
			editable: false,
			sortable: true,
			sorttogglestates: 0,
			filterable: true,
			showfilterrow: true,
			showsortcolumnbackground: true,
			groupable: true,

			columns: [
				{ text: 'Id', datafield: 'id', width: 50 },
				{ text: 'Name', datafield: 'name', width: 115 },
				{ text: 'Price', datafield: 'price', width: 60 },
				{ text: 'Discount', datafield: 'discount', width: 70 },
				{ text: 'Quantity', datafield: 'quantity', width: 70 },
				{ text: 'Cateogry', datafield: 'category', width: 100 },
			]
		});
	}
	return {
		getproductDetails: getproductDetails
	}

})();

function populateProductDetailsModal(orderId) {
	/*open modal*/
debugger;
	$('#productDetailsModel').modal('show');

	OrderId = orderId;
	productDetails.getproductDetails();

	/*bind grid data*/
}
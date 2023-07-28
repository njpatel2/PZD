/**
 * 
 */

var OrderId = null;
var audio = new Audio('/audio/alert.mp3');
var flag = false;
var inter = null;
var inter2 = null;
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

var orderDetails = (function() {

	var getOrderDetails = function() {
		bingOrderdetailsGrid();

	}
	var bingOrderdetailsGrid = function() {

		var cellRendererForOpen = function(row, columnfield, value, defaulthtml, columnproperties, rowdata) {
			if ($("#orderJqxGrid").jqxGrid('getrowdata', row) != undefined) {
				var theStatus = rowdata.isOpened;
				if (theStatus == false) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/hourGlassIcn.png">  ' + rowdata.invId + '  </div>'
				} else if (theStatus == true) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/searchIcn.png">  ' + rowdata.invId + '  </div>'
				}
			}
		};

		var url = '/orders/getOrderDetails';
		var source = {
			datatype: "json",
			id: 'id',
			url: url,
			root: 'results',
		};
		var sendData = JSON.stringify({
			orderId: OrderId,
		});
		/*$("#orderDetailsJqxGrid").jqxGrid("clear");*/
		var adapter = new $.jqx.dataAdapter(source,
			{
				loadServerData: function(postdata, source, callback) {
					$.ajax({
						dataType: "json",
						cache: false,
						type: "POST",
						url: source.url,
						data: sendData,
						data: JSON.stringify({
							orderId: OrderId,
							postdata: postdata
						}),
						tryCount: 0,
						retryLimit: 2,
						/*data: postdata,*/
						contentType: "application/json",
						success: function(data, status, xhr) {
							debugger;
							var records = encodeXMLEscapeCharsForJQXGrid(data, data);
							adapter.loadjson(null, records, source);
							callback({ records: adapter.records });
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

		$("#orderDetailsJqxGrid").jqxGrid({
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

			showsortcolumnbackground: false,
			columns: [
				{ text: 'Product Id', datafield: 'productID', width: 78 },
				{
					text: 'Image', datafield: 'productImage', width: 100, cellsalign: 'center', cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties) {
						var imgSrc = value;
						var imgHtml = '<div style="display: flex; justify-content: center; align-items: center; height: 100%;"><img src="' + imgSrc + '" style="max-width: 100%; max-height: 100%;"/></div>';
						return imgHtml;
					}
				},
				{ text: 'Product Name', datafield: 'productName', width: 215 },
				{ text: 'Quantity', datafield: 'quantity', width: 70 }

			]
		});
	}
	return {
		getOrderDetails: getOrderDetails
	}

})();

function populateOrderDetailsModal(orderId) {
	/*open modal*/

	$('#orderDetailsModel').modal('show');

	OrderId = orderId;
	orderDetails.getOrderDetails();

	/*bind grid data*/
}
function populateConfirmationModal(orderId) {
	/*open modal*/

	$('#confirmationModel').modal('show');

	const confirmButton = document.getElementById("confirmButton");
	confirmButton.setAttribute("onclick", "changeStatus(" + orderId + ")");


	/*bind grid data*/
}
function changeStatus(orderId) {
	debugger;
	var sendData = JSON.stringify({
		orderId: orderId,
	});
	var result = doAjaxCall('/orders/changeStatusToCompleted', 'POST', sendData);
	$('#confirmationModel').modal('hide');
debugger;
var grid = $("#orderJqxGrid");
    var rowIndex = grid.jqxGrid('getrowboundindexbyid', orderId);
    
    if (rowIndex !== -1) {
        grid.jqxGrid('deleterow', orderId);
    }
}


var Orders = (function() {

	var getOrder = function() {
		bindOrderGrid();

	}
	var bindOrderGrid = function() {

		var cellRendererForOpen = function(row, columnfield, value, defaulthtml, columnproperties, rowdata) {
			if ($("#orderJqxGrid").jqxGrid('getrowdata', row) != undefined) {
				var theStatus = rowdata.isOpened;
				if (theStatus == false) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/hourGlassIcn.png">  ' + rowdata.invId + '  </div>'
				} else if (theStatus == true) {
					return ' <div style="padding-top:10px;padding-left: 8px;"><img style="width:15px;" src="' + baseURL + '/img/searchIcn.png">  ' + rowdata.invId + '  </div>'
				}
			}
		};


		var url = '/orders/getPendingOrders';
		var source = {
			datatype: "json",
			id: 'id',
			url: url,
			root: 'results',
			/*sortcolumn: "date",
			sortdirection: 'desc',*/
		};

		$("#orderJqxGrid").jqxGrid("clear");
		//commented to introduce retrylimit NOD-13489
		//var adapter = new $.jqx.dataAdapter(source);
		var adapter = new $.jqx.dataAdapter(source,
			{
				loadServerData: function(postdata, source, callback) {
					$.ajax({
						dataType: "json",
						cache: false,
						type: "GET",
						url: source.url,
						tryCount: 0,
						retryLimit: 2,
						data: postdata,
						success: function(data, status, xhr) {
							/*if ($.isFunction(source.beforeprocessing)) {
								source.beforeprocessing(data, status, xhr);
							}*/
							debugger;
							var records = encodeXMLEscapeCharsForJQXGrid(data, data);
							adapter.loadjson(null, records, source);
							/*callback({records: adapter.records, totalrecords: source.totalrecords});*/
							callback({ records: adapter.records });
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
		var dateSortFn = function(date1, date2) {
			var date1Parts = date1.split('-');
			var date2Parts = date2.split('-');
			var time1 = date1Parts[1].split(':');
			var time2 = date2Parts[1].split(':');
			var jsDate1 = new Date(date1Parts[2], date1Parts[0] - 1, date1Parts[1], time1[0], time1[1], 0, 0);
			var jsDate2 = new Date(date2Parts[2], date2Parts[0] - 1, date2Parts[1], time2[0], time2[1], 0, 0);
			debugger;
			console.log(jsDate1.getTime() - jsDate2.getTime());
			return jsDate1.getTime() - jsDate2.getTime();
		};
		/*$("#orderJqxGrid").on("filter", function() {
			debugger;
			var filterinformation = $("#orderJqxGrid").jqxGrid("getfilterinformation");
			var filtervalue = filterinformation[0].filter.getfilters()[0].value;
			console.log(filtervalue);
		});*/
		var customFilter = function(filterValue, rowData, dataField) {
			debugger;
			// Convert the rowData to a string for easier substring search
			var rowString = JSON.stringify(rowData.username).toLowerCase();

			// Check if the filterValue is present as a substring in the rowString
			return rowString.indexOf(filterValue.toLowerCase()) >= 0;
		};

		$("#orderJqxGrid").jqxGrid({
			width: '90%',
			height: 'auto',
			pagermode: 'default',
			rowsheight: 60,
			columnsheight: 40,
			columnsresize: true,
			pageable: true,
			source: adapter,
			pagesize: 10,
			autoheight: true,
			editable: false,
			sortable: true,
			sortmode: 'multiple',
			filterable: true,
			filtermode: 'custom',
			filter: customFilter,
			updatefilterconditions: function(type, filterConditions) {
				filterConditions.push({ text: 'Custom Filter', value: 'custom' });
			},
			/*filter: function(value, filterValue) {
				debugger;
				console.log(filterValue.toString().toLowerCase());
				return value.toString().toLowerCase().indexOf(filterValue.toString().toLowerCase()) >= 0;
			},*/
			/*updatefilterconditions: function(type, defaultconditions) {
				var stringcomparisonoperators = ['CONTAINS'];
				var numericcomparisonoperators = ['CONTAINS'];
				var datecomparisonoperators = ['CONTAINS'];
				var booleancomparisonoperators = ['CONTAINS'];
				switch (type) {
					case 'stringfilter':
						return stringcomparisonoperators;
					case 'numericfilter':
						return numericcomparisonoperators;
					case 'datefilter':
						return datecomparisonoperators;
					case 'booleanfilter':
						return booleancomparisonoperators;
				}
			},*/
			showfilterrow: true,
			showsortcolumnbackground: true,
			groupable: true,
			columns: [
				{ text: 'Order No.', datafield: 'id', width: 100, type: 'string' },
				{ text: 'Customer Name', datafield: 'username', width: 500, type: 'string' },
				{ text: 'Price', datafield: 'price', width: 180 },
				{ text: 'Date', datafield: 'date', width: 300 },
				{
					text: 'Change Status',
					datafield: 'Status',
					width: 134,
					cellsalign: 'right',
					columntype: 'button',
					cellsrenderer: function(row, column, value, defaultHtml, columnSettings, rowData) {
						var buttonId = 'btn_' + rowData.id;
						var buttonHtml = '<button class="custom-button" id="' + buttonId + '">completed</button>';
						return '<div class="custom-button-container">' + buttonHtml + '</div>';

					}
				}
			]
		});
	}

	return {
		getOrder: getOrder
	}

})();
$('#orderJqxGrid').on('cellclick', function(event) {
	debugger;
	if (event.args.datafield === 'id') {
		var buttonId = 'btn_' + event.args.row.id;
		if (event.args.originalEvent.target.id === buttonId) {
			// call your function to open the modal
			openModal(event.args.row.id);
		}
	}
});


$(document).ready(function() {


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
	Orders.getOrder();

	var previousGroupBy = "";
	console.log("Step : 0");

	$("#orderJqxGrid").on('rowclick', function(event) {
		debugger;
		if (!event.args.rightclick) {

			var target = $(event.args.originalEvent.target);
			if (target[0].outerText == "completed") {
				var rowdata = $("#orderJqxGrid").jqxGrid('getrowdata', rowindex);
				populateConfirmationModal(rowdata.id);

			}
			else {
				var rowindex = event.args.rowindex;
				if (rowindex >= 0) {
					/*loader.show();*/
					rowdata = $("#orderJqxGrid").jqxGrid('getrowdata', rowindex);
					populateOrderDetailsModal(rowdata.id);
				}
			}
		}
	});

});



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



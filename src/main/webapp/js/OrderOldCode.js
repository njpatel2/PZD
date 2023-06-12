/**
 * 
 */

var OrderId = null;
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

		/* var colonneinfo = [];*/
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
			rowsheight: 50,
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

var Orders = (function() {
	/*var previousCustomCols = [];
	var groupByName = "";
	var groupby = "";
	var getCommonObjects = function(){
		var aPromise = $ajax.get("/secure/list/Order/getCommonObjects");
		aPromise.done(function(data){
			var json = $select.createJson(data,'strkey','value');
			$select.remove("OrderGrpby");
			$select.append("OrderGrpby",json,false);
			
			$('#OrderGrpby').val('OrderDate');
			$('#OrderGrpby').selectpicker('refresh');
			console.log(data)
			loader.hide();
			}
		).error(function(date){
			loader.hide();
			console.log("-----------Error-------------");
			console.log("/secure/list/Order/getCommonObjects");
		});
	};*/
	var getOrder = function() {
		bindOrderGrid();

	}
	/*var setGroupBy = function(arg){
		groupby = arg
	};
	
	var setGroupByName = function(arg){
		groupByName = arg
	};*/


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
		var dateSortFn = function (date1, date2) {
    var date1Parts = date1.split('-');
    var date2Parts = date2.split('-');
    var time1 = date1Parts[1].split(':');
    var time2 = date2Parts[1].split(':');
    var jsDate1 = new Date(date1Parts[2], date1Parts[0] - 1, date1Parts[1], time1[0], time1[1], 0, 0);
    var jsDate2 = new Date(date2Parts[2], date2Parts[0] - 1, date2Parts[1], time2[0], time2[1], 0, 0);
    debugger;
    return jsDate1.getTime() - jsDate2.getTime();
};

		/* var colonneinfo = [];*/
		var url = '/orders/getPendingOrders';
		var source = {
			datatype: "json",
			id: 'id',
			url: url,
			root: 'results',
			sortcolumn: "date",
			sortdirection: 'desc',
			/*sort: function() {
				// update the grid and send a request to the server.
				debugger;
				$("#orderJqxGrid").jqxGrid('updatebounddata', 'sort');

			},
			filter: function() {
				// update the grid and send a request to the server.
				$("#orderJqxGrid").jqxGrid('updatebounddata', 'filter');
			},
			beforeprocessing: function(data) {
				/*console.log("Step : 5");
				if(!jQuery.isEmptyObject(data) && data.message!=undefined && data.message.length>0){
					message.error([data.message]);
				}else{
					$(".failureId").hide();
				}*/
				/*source.totalrecords = data.total;
				data.results = buildCustomColumns.setCustomColumns(colonneinfo,previousCustomCols,data);*/
				/*previousCustomCols = buildCustomColumns.getPreviousColumns();*/
				/*return data.results;*/
				/*return data;
			}*/
		};
		/*$("#orderJqxGrid").bind("sort", function(event) {
			var sortinformation = event.args.sortinformation;
			var sortdirection = sortinformation.sortdirection;
			var sortcolumn = sortinformation.sortcolumn;
		});*/
		/**
		 * Group HTML renderer function
		 */
		/*var groupsrenderer = function (text, group, expanded) {
			var grpName = group.split('-')[1];
			if(grpName == "" || grpName == undefined){
				grpName = "None";
			}
			var div = '<div class="jqx-grid-groups-row" style="position: absolute;">';
			var a = "<span>" + groupByName + ": </span>";
			var b = '<span class="jqx-grid-groups-row-details">' + grpName + '</span></div>';
			return div + a + b;
		}*/

		/*$("#orderJqxGrid").on("bindingcomplete", function (event) {
			
			setLocalizationObj("#orderJqxGrid");
			$('.jqx-grid-groupby-icon').parents('li').remove();
			loader.hide();
			var groups = $("#orderJqxGrid").jqxGrid('groups');
			console.log(groups);
			if(groups.length == 0) {	
				
				$("#orderJqxGrid").jqxGrid('hidecolumn', 'groupValue');
				$('#orderJqxGrid').jqxGrid('insertgroup', 0, 'groupValue');
				$("#orderJqxGrid").jqxGrid('gotopage', 0);
				
	//				console.log("Group by Column" + $('#orderJqxGrid').jqxGrid('iscolumngroupable', 'groupValue'));
//			
//				if(groupby == 'OrderDate' || (groupby != 'subject' && groupby != 'workFlowStepName' &&
//						groupby != 'OrderTypeName')){
//					setTimeout(function(){
//						$("#orderJqxGrid").jqxGrid('hidecolumn', 'groupValue');
//						 $("#orderJqxGrid").jqxGrid('gotopage', 0);
//						
//					}, 20);
//					$("#orderJqxGrid").jqxGrid('insertgroup', 0, 'groupValue');
//				}else{
//					setTimeout(function(){
//						$('#orderJqxGrid').jqxGrid('insertgroup', 0, 'groupValue');
//						$('#orderJqxGrid').jqxGrid('sortby', groupby, 'asc');
//						 $("#orderJqxGrid").jqxGrid('gotopage', 0);
//					}, 20);
//				}
			}
				setTimeout(function(){
					$('#orderJqxGrid').jqxGrid('expandallgroups');
//					$(".jqx-grid-groups-row").each(function(k, v){
//						$($(v).find("span")[0]).html(groupByName + ": ");
//					});
				}, 20);
		});*/


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
		$("#orderJqxGrid").jqxGrid({
			width: '90%',
			height: 'auto',
			//theme: "dark",
			 pagermode:'default',
			rowsheight: 40,
			columnsheight: 40,
			columnsresize: true,
			pageable: true,
			/*selectionmode: 'singlecell',*/
			source: adapter,
			pagesize: 10,
			/*virtualmode: true,*/
			autoheight: true,
			/*rendergridrows: function() {
				debugger;
				return adapter.records;
			},*/
			//	columnsmenu: false, 
			editable: false,
			sortable: true,
    sortmode: 'multiple',
    filterable: true,
    filtermode: 'default',
			updatefilterconditions: function(type, defaultconditions) {
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
			},
			showfilterrow: true,
			/*groupsrenderer : groupsrenderer,*/
			/*
			groupsexpandedbydefault: true,
			showgroupsheader: false,*/
			showsortcolumnbackground: true,
			groupable: true,
			columns: [
				{ text: 'Id', datafield: 'id', width: 100 },
				{ text: 'Customer Name', datafield: 'username', width: 500 },
				{ text: 'Price', datafield: 'price', width: 180 },
				/*{ text: 'Date', datafield: 'date', width: 300 },*/
				{ text: 'Date', datafield: 'date', cellsformat: 'dd-MM-yyyy HH:mm', cellsalign: 'right', align: 'right', sortable: true, sorttype: 'custom', 
          sortdirection: 'asc', sorting: dateSortFn },
				{ text: 'Completed?', datafield: 'completed', width: 134, cellsalign: 'right' }
			]
		});
	}

	/* $("#orderJqxGrid").on('groupexpand', function (event) {
			setTimeout(function(){
				$(".jqx-grid-groups-row").each(function(k, v){
				$($(v).find("span")[0]).html(groupByName + ": ");
				});
			}, 10);  
			});
		 
		 $('#orderJqxGrid').on('groupcollapse', function (event)  { 
			setTimeout(function(){
				$(".jqx-grid-groups-row").each(function(k, v){
					$($(v).find("span")[0]).html(groupByName + ": ");
				});}, 10); 
			
		});*/
	return {
		/*getCommonObjects : getCommonObjects,*/
		getOrder: getOrder
		/*setGroupBy : setGroupBy,
		setGroupByName : setGroupByName*/
	}

})();



$(document).ready(function() {

	/*var subjectProfileMessage = localStorage.getItem('subjectProfileMessage');
	if(subjectProfileMessage != null){
		message.success(subjectProfileMessage);
		localStorage.removeItem('subjectProfileMessage')
	}
	
	
	$('.selectpicker').selectpicker();
	Orders.getCommonObjects();
	Orders.setGroupBy("OrderDate");
	Orders.setGroupByName("Order Date");
	 loader.hide();*/

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
	/*$("#OrderGrpby").change(function(){
		console.log("Step : 1");
		console.log($(this).val());
		
		$('#orderJqxGrid').jqxGrid('cleargroups');
		
		previousGroupBy = $(this).val();
		var tmpS = $("#orderJqxGrid").jqxGrid('source');
		
		tmpS._source.sortcolumn = $(this).val();
		tmpS._source.sortorder='asc';
		
		$("#orderJqxGrid").jqxGrid('refreshdata');
		Orders.setGroupByName($(this).find("option:selected").text());
		Orders.setGroupBy($(this).val());
		
		tmpS._source.url =serverURL + '/secure/list/Order/getOrderList?groupby='+$(this).val();
		$("#orderJqxGrid").jqxGrid('source', tmpS);
	});
	$("#linkFieldChooser").click(function(){
		customFields.getcustomFields('CUSTOME_FIELDS_OrderS_BASED');
	});
	$("#sortOptionChooserOrder").click(function(){
		gridSortOptions.openUserSortOptions('Order');
	});*/


	/*$("#btnSaveCustomFields").click(function(){
		var reloadGrid = function(){
			$('#orderJqxGrid').jqxGrid('cleargroups');
			
			previousGroupBy = $("#OrderGrpby").val();
			var tmpS = $("#orderJqxGrid").jqxGrid('source');
			
			tmpS._source.sortcolumn = $("#OrderGrpby").val();
			tmpS._source.sortorder='asc';
			
			$("#orderJqxGrid").jqxGrid('refreshdata');
			Orders.setGroupByName($("#OrderGrpby").find("option:selected").text());
			Orders.setGroupBy($("#OrderGrpby").val());
			
			tmpS._source.url =serverURL + '/secure/list/Order/getOrderList?groupby='+$("#OrderGrpby").val();
			$("#orderJqxGrid").jqxGrid('source', tmpS);
		}
		customFields.savecustomFields('CUSTOME_FIELDS_OrderS_BASED',reloadGrid);
	});*/

	$("#orderJqxGrid").on('rowclick', function(event) {
		debugger;
		if (!event.args.rightclick) {
			rowindex = event.args.rowindex;
			if (rowindex >= 0) {
				/*loader.show();*/
				rowdata = $("#orderJqxGrid").jqxGrid('getrowdata', rowindex);
				populateOrderDetailsModal(rowdata.id);
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



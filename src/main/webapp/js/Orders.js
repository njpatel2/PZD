/**
 * 
 */

var OrderId = null;

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
					data:sendData,
					data:JSON.stringify({
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
			{ text: 'Image', datafield: 'productImage', width: 100, cellsalign: 'center', cellsrenderer: function(row, columnfield, value, defaulthtml, columnproperties) {
			var imgSrc = value;
			var imgHtml = '<div style="display: flex; justify-content: center; align-items: center; height: 100%;"><img src="' + imgSrc + '" style="max-width: 100%; max-height: 100%;"/></div>';
			return imgHtml;
		}},
			{ text: 'Product Name', datafield: 'productName', width: 215 },
			{ text: 'Quantity', datafield: 'quantity', width: 70 }

		]
	});
	}
	return {
		getOrderDetails: getOrderDetails
	}

})();

function populateOrderDetailsModal(orderId){
	/*open modal*/
	
	$('#orderDetailsModel').modal('show');
	
	OrderId = orderId;
	orderDetails.getOrderDetails();
	
	/*bind grid data*/
}

var referrals = (function() {
	/*var previousCustomCols = [];
	var groupByName = "";
	var groupby = "";
	var getCommonObjects = function(){
		var aPromise = $ajax.get("/secure/list/referral/getCommonObjects");
		aPromise.done(function(data){
			var json = $select.createJson(data,'strkey','value');
			$select.remove("referralGrpby");
			$select.append("referralGrpby",json,false);
			
			$('#referralGrpby').val('referralDate');
			$('#referralGrpby').selectpicker('refresh');
			console.log(data)
			loader.hide();
			}
		).error(function(date){
			loader.hide();
			console.log("-----------Error-------------");
			console.log("/secure/list/referral/getCommonObjects");
		});
	};*/
	var getReferral = function() {
		bindReferralGrid();

	}
	/*var setGroupBy = function(arg){
		groupby = arg
	};
	
	var setGroupByName = function(arg){
		groupByName = arg
	};*/


	var bindReferralGrid = function() {

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
		var url = '/orders/getPendingOrders';
		var source = {
			datatype: "json",
			id: 'id',
			url: url,
			root: 'results',
			/*sortcolumn: "groupValue",
			sortdirection: 'desc',
			sort: function () {
				// update the grid and send a request to the server.
				$("#referraljqxgrid").jqxGrid('updatebounddata', 'sort');
			  
			},
			filter: function () {
				//BUA-1268 : added filter function for filtering
				console.log("Step : filter");
				// update the grid and send a request to the server.
				$("#referraljqxgrid").jqxGrid('updatebounddata', 'filter');
			},*/
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
				return data;
			}
		};

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

		/*$("#referraljqxgrid").on("bindingcomplete", function (event) {
			
			setLocalizationObj("#referraljqxgrid");
			$('.jqx-grid-groupby-icon').parents('li').remove();
			loader.hide();
			var groups = $("#referraljqxgrid").jqxGrid('groups');
			console.log(groups);
			if(groups.length == 0) {	
				
				$("#referraljqxgrid").jqxGrid('hidecolumn', 'groupValue');
				$('#referraljqxgrid').jqxGrid('insertgroup', 0, 'groupValue');
				$("#referraljqxgrid").jqxGrid('gotopage', 0);
				
	//				console.log("Group by Column" + $('#referraljqxgrid').jqxGrid('iscolumngroupable', 'groupValue'));
//			
//				if(groupby == 'referralDate' || (groupby != 'subject' && groupby != 'workFlowStepName' &&
//						groupby != 'referralTypeName')){
//					setTimeout(function(){
//						$("#referraljqxgrid").jqxGrid('hidecolumn', 'groupValue');
//						 $("#referraljqxgrid").jqxGrid('gotopage', 0);
//						
//					}, 20);
//					$("#referraljqxgrid").jqxGrid('insertgroup', 0, 'groupValue');
//				}else{
//					setTimeout(function(){
//						$('#referraljqxgrid').jqxGrid('insertgroup', 0, 'groupValue');
//						$('#referraljqxgrid').jqxGrid('sortby', groupby, 'asc');
//						 $("#referraljqxgrid").jqxGrid('gotopage', 0);
//					}, 20);
//				}
			}
				setTimeout(function(){
					$('#referraljqxgrid').jqxGrid('expandallgroups');
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
			// pagermode:'default',
			rowsheight: 40,
			columnsheight: 40,
			columnsresize: true,
			pageable: true,
			/*selectionmode: 'singlecell',*/
			source: adapter,
			pagesize: 10,
			virtualmode: true,
			autoheight: true,
			rendergridrows: function() {
				debugger;
				return adapter.records;
			},
			//	columnsmenu: false, 
			editable: false,
			sortable: true,
			sorttogglestates: 0,
			filterable: true,
			/*updatefilterconditions: function (type, defaultconditions) {
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
			//	showfilterrow : true,
			/*groupsrenderer : groupsrenderer,*/
			/*groupable: true,
			groupsexpandedbydefault: true,
			showgroupsheader: false,*/
			showsortcolumnbackground: false,
			columns: [
				{ text: 'Id', datafield: 'id', width: 100 },
				{ text: 'Customer Name', datafield: 'username', width: 500 },
				{ text: 'Price', datafield: 'price', width: 180 },
				{ text: 'Date', datafield: 'date', width: 300 },
				{ text: 'Completed?', datafield: 'completed', width: 134, cellsalign: 'right' }
			]
		});
	}

	/* $("#referraljqxgrid").on('groupexpand', function (event) {
			setTimeout(function(){
				$(".jqx-grid-groups-row").each(function(k, v){
				$($(v).find("span")[0]).html(groupByName + ": ");
				});
			}, 10);  
			});
		 
		 $('#referraljqxgrid').on('groupcollapse', function (event)  { 
			setTimeout(function(){
				$(".jqx-grid-groups-row").each(function(k, v){
					$($(v).find("span")[0]).html(groupByName + ": ");
				});}, 10); 
			
		});*/
	return {
		/*getCommonObjects : getCommonObjects,*/
		getReferral: getReferral
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
	referrals.getCommonObjects();
	referrals.setGroupBy("referralDate");
	referrals.setGroupByName("Referral Date");
	 loader.hide();*/
	referrals.getReferral();

	var previousGroupBy = "";
	console.log("Step : 0");
	/*$("#referralGrpby").change(function(){
		console.log("Step : 1");
		console.log($(this).val());
		
		$('#referraljqxgrid').jqxGrid('cleargroups');
		
		previousGroupBy = $(this).val();
		var tmpS = $("#referraljqxgrid").jqxGrid('source');
		
		tmpS._source.sortcolumn = $(this).val();
		tmpS._source.sortorder='asc';
		
		$("#referraljqxgrid").jqxGrid('refreshdata');
		referrals.setGroupByName($(this).find("option:selected").text());
		referrals.setGroupBy($(this).val());
		
		tmpS._source.url =serverURL + '/secure/list/referral/getReferralList?groupby='+$(this).val();
		$("#referraljqxgrid").jqxGrid('source', tmpS);
	});
	$("#linkFieldChooser").click(function(){
		customFields.getcustomFields('CUSTOME_FIELDS_REFERRALS_BASED');
	});
	$("#sortOptionChooserReferral").click(function(){
		gridSortOptions.openUserSortOptions('Referral');
	});*/


	/*$("#btnSaveCustomFields").click(function(){
		var reloadGrid = function(){
			$('#referraljqxgrid').jqxGrid('cleargroups');
			
			previousGroupBy = $("#referralGrpby").val();
			var tmpS = $("#referraljqxgrid").jqxGrid('source');
			
			tmpS._source.sortcolumn = $("#referralGrpby").val();
			tmpS._source.sortorder='asc';
			
			$("#referraljqxgrid").jqxGrid('refreshdata');
			referrals.setGroupByName($("#referralGrpby").find("option:selected").text());
			referrals.setGroupBy($("#referralGrpby").val());
			
			tmpS._source.url =serverURL + '/secure/list/referral/getReferralList?groupby='+$("#referralGrpby").val();
			$("#referraljqxgrid").jqxGrid('source', tmpS);
		}
		customFields.savecustomFields('CUSTOME_FIELDS_REFERRALS_BASED',reloadGrid);
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



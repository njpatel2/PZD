var PageRenderer = (function() {
	return{
		pagerrenderer : function(){	
			var theme = 'metro';
			var element = $("<div id='pagerId' style='margin-top: 5px; width: 100%; height: 100%;'></div>");
            var datainfo = "";
            var jqxGridElement = "";
			var flag = true;
			$(".buckeyejqxgrid").each(function(key, val){
				if(val !== undefined || val !== null){
					var jqxElement = val.children[0].innerHTML;
					if(flag && (jqxElement.indexOf("pagerId") == -1)){
						jqxGridElement = $(val.children[0]);
						datainfo = jqxGridElement.jqxGrid('getdatainformation');
						flag = false;
					}
				}
			});
			//var datainfo = $("#jqxgrid").jqxGrid('getdatainformation');
			if(datainfo === undefined){
				var blankDatainfo = {rowscount:0,paginginformation:{pagescount:0,pagesize:1,pagenum:0}};
				datainfo = blankDatainfo;
			}
			var paginginfo = datainfo.paginginformation;
			var pagecount = paginginfo.pagescount;
			var leftButton = $("<div style='padding: 0px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
			leftButton.find('div').addClass('jqx-icon-arrow-left');
			leftButton.width(36);
			leftButton.jqxButton({
				theme : theme
			});
	
			var rightButton = $("<div style='padding: 0px; margin: 0px 3px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
			rightButton.find('div').addClass('jqx-icon-arrow-right');
			rightButton.width(36);
			rightButton.jqxButton({
				theme : theme
			});
	
			var firstPageButton = $("<div style='padding: 0px; margin: 0px 3px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
			firstPageButton.find('div').addClass('jqx-icon-arrow-first');
			firstPageButton.width(36);
			firstPageButton.jqxButton({
				theme : theme
			});
	
			var lastPageButton = $("<div style='padding: 0px; margin: 0px 3px; float: left;'><div style='margin-left: 9px; width: 16px; height: 16px;'></div></div>");
			lastPageButton.find('div').addClass('jqx-icon-arrow-last');
			lastPageButton.width(36);
			lastPageButton.jqxButton({
				theme : theme
			});

			var pageBox = $("<input type='text' name='page-number' style='width: 20px; text-align: center; height: 20px; margin-right: 5px;' />");
			var totalPages = (1 + Math.ceil((datainfo.rowscount - paginginfo.pagesize) / (paginginfo.pagesize)));
			(totalPages == 0) ? $(pageBox).val(0) : $(pageBox).val(1 + paginginfo.pagenum);
			$(pageBox).blur(function(event) {
				var val = $(this).val();
				if(val != '' && val > 0 && val <= totalPages) {
					jqxGridElement.jqxGrid('gotopage', parseInt(val) - 1);
				}
				else {
					(totalPages == 0) ? $(this).val(0) : $(this).val(1 + paginginfo.pagenum);
				}
			});

			var div = $("<div class='spaceLeftSm spaceRightSm pull-right'></div>");
			var label = $("<label> of " + totalPages + "</label>");
			var pageLabel = $("<label style='margin-right: 5px;'>Page </label>");
			var pageIcon = $('<div style="margin-left: 9px; width: 15px; height: 17px; float: right !important; cursor: pointer;" class="jqx-icon-refresh"></div>');
			$(pageIcon).click(function(event) {
				jqxGridElement.jqxGrid('gotopage', 0);
			});
			
			pageLabel.appendTo(div);
			pageBox.appendTo(div);
			label.appendTo(div);
			pageIcon.appendTo(div);
			div.appendTo(element);
			
			firstPageButton.appendTo(element);
			leftButton.appendTo(element);
			rightButton.appendTo(element);
			lastPageButton.appendTo(element);
			
			// update buttons states.
			var handleStates = function(event, button, className, add) {
				button.on(event, function() {
					if (add == true) {
						button.find('div').addClass(className);
					} else
						button.find('div').removeClass(className);
				});
			}
	
			if (theme != '') {
				handleStates('mousedown', rightButton,
						'jqx-icon-arrow-right-selected-' + theme, true);
				handleStates('mouseup', rightButton,
						'jqx-icon-arrow-right-selected-' + theme, false);
				handleStates('mousedown', leftButton,
						'jqx-icon-arrow-left-selected-' + theme, true);
				handleStates('mouseup', leftButton, 'jqx-icon-arrow-left-selected-'
						+ theme, false);
	
				handleStates('mouseenter', rightButton,
						'jqx-icon-arrow-right-hover-' + theme, true);
				handleStates('mouseleave', rightButton,
						'jqx-icon-arrow-right-hover-' + theme, false);
				handleStates('mouseenter', leftButton, 'jqx-icon-arrow-left-hover-'
						+ theme, true);
				handleStates('mouseleave', leftButton, 'jqx-icon-arrow-left-hover-'
						+ theme, false);
			}
			
			rightButton.click(function() {
				jqxGridElement.jqxGrid('gotonextpage');
			});
	
			leftButton.click(function() {
				jqxGridElement.jqxGrid('gotoprevpage');
			});
	
			firstPageButton.click(function() {
				jqxGridElement.jqxGrid('gotopage', 0);
			});
			lastPageButton.click(function() {
				jqxGridElement.jqxGrid('gotopage', pagecount);
			});
			//$("<span class='info fr spaceRightSm'>Go to page: </span>").appendTo(element);
			
			return element;
		}
	};
})();
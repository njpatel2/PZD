<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title id='Description'>Orders</title>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="/jqwidgets/styles/jqx.base.css">
<link rel="stylesheet" href="/jqwidgets/styles/jqx.dark.css"
	type="text/css" />

<script type="text/javascript" src="/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxmenu.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxgrid.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxgrid.selection.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxgrid.grouping.js"></script>

<script type="text/javascript" src="/jqwidgets/jqxgrid.pager.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxgrid.filter.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxgrid.columnsresize.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxgrid.sort.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxlistbox.js"></script>
<script type="text/javascript" src="/jqwidgets/jqxdropdownlist.js"></script>

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do"
	rel="stylesheet">

<link rel="stylesheet" href="/css/jqxGrid.css" type="text/css" />
<script type="text/javascript" src="/js/Orders.js"></script>

<style>
/* Custom styles for the button */
.custom-button-container {
	display: flex;
	justify-content: center;
	align-items: center;
	left: 25px;
}

.custom-button {
	padding: 5px 10px; /* Adjust the padding to make the button smaller */
	font-size: 12px; /* Adjust the font size to make the text smaller */
	background-color: rgb(48, 153, 30);
	color: #fff;
	border: none;
	border-radius: 3px;
	/* Adjust the border radius to make the button corners round */
	cursor: pointer;
	transition: background-color 0.2s;
	position: relative;
	justify-content: center;
	left: 20px;
}

.custom-button:hover {
	background-color: rgb(58, 131, 37);
}

.custom-button:active {
	background-color: rgb(48, 153, 30);
}
span {
  color: #000000; 
  display: flex;
  justify-content: center;
  align-items: center;}
</style>
</head>
<body>


	<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;">
		<div id="orderJqxGrid"></div>
	</div>



	<div class=" modal fade" id="orderDetailsModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color: black"
						id="orderDetailsModelTitle">Order Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeOrderDetailsModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;">
						<div id="orderDetailsJqxGrid"></div>
					</div>

				</div>

			</div>
		</div>
	</div>

	<!-- confirmation modal -->
	<div class=" modal fade" id="confirmationModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color: black"
						id="confirmationModelTitle">Confirmation Model</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeConfirmationModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"
					style="color: black; font-weight: 500; font-size: large;">Is
					this order completed?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success" id="confirmButton">Confirm</button>
				</div>


			</div>
		</div>
	</div>
	<!-- show alert model -->
	<div class=" modal fade" id="alertModel" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
		onclick="myFunction()">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" id="alertModelTitle">New Order
						Received</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closealertModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div>
						<p>
							Username: <span id="username"></span>
						</p>
						<p>
							Order ID: <span id="orderId"></span>
						</p>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- show alert model end -->
	<%@include file="footer.jsp"%>
</body>
</html>

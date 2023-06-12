<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>



<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="/js/jquery-3.3.1.slim.min.js"></script>
<script type="text/javascript" src="/js/Cart.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<%@include file="header.jsp"%>
<style>
.white-text {
	color: white;
}

table {
	border-collapse: separate;
	border-spacing: 0px 15px;
	margin-bottom: 20px;
	width: 100%;
}

table th, table td {
	/* border: 1px solid white; */
	padding: 10px;
	text-align: left;
	color: white;
}

table th {
	background-color: #101315;
	color: #fff;
	border-bottom: 2px solid white;
}

td[data-th="Price"] {
	font-size: 25px;
	color: white !important;
	text-align: center;
}

td[data-th="subTotal"] {
	font-size: 25px;
	color: white !important;
	text-align: right;
}

input[type="number"] {
	background-color: #313a40 !important;
}

.font-style-for-cart {
	color: white !important;
	font-size: 23px !important;
}

tr:hover {
	background-color: #1b2023;
}

.on-hover-change-color {
	padding: 5px;
}

.form-control {
	border-radius: 20px;
	border-color: #ccc;
}

.form-control:hover {
	border-color: #999;
}

.form-control:focus {
	box-shadow: none;
	border-color: #5fa9f8;
}

.btn {
	border-radius: 8px;
	/* adjust the value to change the amount of rounding */
	transition: all 0.2s ease-in-out;
	/* add a transition for smooth size change on hover */
}

.btn:hover {
	transform: scale(1.3); /* increase the size of the button on hover */
}

.box2 img {
	object-fit: contain;
}

input[name="quantity"] {
	width: 90px;
	height: 45px !important;
}

.delbtn {
	padding-top: 59px !important;
}

.input-container {
	display: flex !important;
	align-items: center;
}

.input-container input {
	margin-right: 5px;
	/* add some space on the right side of the input box */
}

.custom-img-size {
	max-width: 100%;
	height: auto;
}
.updateProfileModel{

    margin-top: 0px!important;
    box-shadow: 0px 0px 0px;
    color : black;
    }
</style>
</head>
<body>

	<!-- 
  Bootstrap docs: https://getbootstrap.com/docs
  Get more snippet on https://bootstraptor.com/snippets
-->

	<section class="pt-5 pb-5">
		<div class="container">
			<div class="row w-100">
				<div class="col-lg-12 col-md-12 col-12">
					<%-- <h3 class="display-5 mb-2 text-center">${userName}</h3> --%>
					<h5 class="text-center">
						<i id="totalCartItems" class="text font-weight-bold"
							style="color: white"></i> items in your cart
					</h5>
					<table id="shoppingCart"
						class="table table-condensed table-responsive">
						<thead>
							<tr>
								<th class=" font-style-for-cart" style="width: 15%;"></th>
								<th class=" font-style-for-cart" style="width: 50%;">Product</th>

								<th class=" font-style-for-cart" style="width: 10%;">Price</th>
								<th class=" font-style-for-cart" style="width: 2%;"></th>
								<th class=" font-style-for-cart"
									style="width: 23%; text-align: right;">Sub Total</th>
							</tr>

						</thead>
						<tbody id="cartItemsDiv">


							<!-- <tr class="on-hover-change-color">
								<td data-th="Product">
									<div class="row">
										<div class="col-sm-2 box box2 text-left">
											<img src="/images/pizza-1.jpg" alt=""
												class="img-fluid d-none d-md-block rounded mb-2 shadow">
										</div>
										<div class="col-md-9 text-left mt-sm-2">
											<h4>Product Name</h4>
										</div>
										<input type="hidden" name="productId">
									</div>
								</td>
								<td data-th="Price" style="color: #fff;">$49.00<br/>
								<input
									type="text" class="form-control form-control-sm text-center"
									name="quantity" value="1" required min="1"
									onchange="updateCartProductQuantity()">
									<div class="text-center">
										<button
											class="btn btn-white border-secondary bg-white btn-md mb-2 rounded" onclick="deleteProductFromCart(this)">
											<i class="fa fa-trash-o" style="font-size: 31px; color: red;"></i>
										</button>
									</div></td> 
								<td class="actions" data-th="">
									<div class="text-center">
										<button
											class="btn btn-white border-secondary bg-white btn-md mb-2 rounded" onclick="deleteProductFromCart(this)">
											<i class="fa fa-trash-o" style="font-size: 31px; color: red;"></i>
										</button>
									</div>
								</td>
							</tr> 
							<tr class="on-hover-change-color">
  <td data-th="Product">
    <div class="row">
      <div class="col-sm-2 box box2 text-left">
        <img src="/images/pizza-1.jpg" alt=""
          class="img-fluid d-none d-md-block rounded mb-2 shadow">
      </div>
      <div class="col-md-9 text-left mt-sm-2">
        <h4>Product Name</h4>
      </div>
      <input type="hidden" name="productId">
    </div>
  </td>
  <td data-th="Price" style="color: #fff;">	
    <span class="mr-2 center">$492.00</span>

    <div class="d-flex align-items-center flex-wrap input-container">
      <input type="text" class="form-control form-control-sm text-center mb-2"
        name="quantity" value="1" required min="1" onchange="updateCartProductQuantity()" id="quantity-input">
      <button class="btn btn-white border-secondary bg-white btn-md mb-2 ml-md-auto rounded" onclick="deleteProductFromCart(this)">
        <i class="fa fa-trash-o" style="font-size: 24px; color: red;"></i>
      </button>
    </div>
    </td>
 
</tr>
				 -->
						</tbody>
					</table>
					<div class="float-right text-right">
						<!-- <h4>Total:</h4><h1 id="totalPriceOfCart">$ 99.00</h1> -->
						<h4 style="display: inline-block;">Total :</h4>
						<span> </span>
						<h1 id="totalPriceOfCart"
							style="display: inline-block; margin-left: 10px;"></h1>

					</div>
				</div>
			</div>
			<div class="row mt-4 d-flex align-items-center">
				<div class="col-sm-6 order-md-2 text-right">
					<button id="placeOrderButton"
						class="btn btn-primary mb-4 btn-lg pl-5 pr-5"
						style="margin-right: 39px;" onclick="getUserDetails()">Check
						Out</button>

				</div>
				<div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
					<a href="/home"> <i class="fas fa-arrow-left mr-2"></i>
						Continue Shopping
					</a>
				</div>
			</div>
		</div>
	</section>
<div class=" modal fade" id="confirmDetailsModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color : black" id="confirmDetailsModelTitle">Confirm Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeConfirmDetailsModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form name="form" id="form" method="post" class="form-login updateProfileModel" action="/user/confirmUserDetails" onsubmit="sendAlert()"><!-- onsubmit="confirmUserDetails()" --> 
			         
			
			          <label for="contact">Contact Number:</label>
			          <input type="text"  id="contactNumber"  name = "contactNumber" required>
			          
			          <label for="address">Address</label>
			          <input type="text" id="address" name = "address"  required>

          <input type="submit" value="Confirm">
          </form>
				</div>

			</div>
		</div>
	</div>
	
	<%@include file="footer.jsp"%>
</body>
</html>
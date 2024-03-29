<!DOCTYPE html>
<html lang="en">
<head>
<title>Crazy Italian Pizza</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do"
	rel="stylesheet">

<%@include file="header.jsp"%>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/home.js"></script>
<script type="text/javascript" src="/js/Cart.js"></script>



<style>
.quantity {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-bottom: 10px;
}

.quantity input[type="number"] {
	-moz-appearance: textfield;
	margin: 0;
	width: 40px;
	font-size: 14px;
	border: none;
	text-align: center;
}

.quantity input[type="number"]:focus {
	outline: none;
}

.quantity button {
	height: 25px;
	width: 25px;
	background-color: #eee;
	border-radius: 50%;
	border: none;
	color: #000;
	font-weight: bold;
	text-align: center;
	font-size: 18px;
	line-height: 1.5;
	cursor: pointer;
	margin: 0 10px;
	transition: background-color 0.3s ease;
}

.quantity button:hover {
	background-color: #ccc;
}

.product-image img {
	max-width: 100%;
}

.product-image {
	text-align: center;
}

.product-info {
	text-align: center;
}

.product-info h2 {
	margin-top: 0;
}

.modal-header .modal-title {
	color: black;
}

.modal-body {
	background-color: #101315;
}

.box-price {
	float: right;
	font-size: 1.25rem;
	padding: 0.75rem 1.5rem;
	line-height: 1.5;
	color: #fac564;
	transition: transform 0.2s ease-in-out; /* add transition effect */
}

.box-price:hover {
	transform: scale(1.25); /* increase size by 10% */
}

.price {
	float: right;
	font-size: 1.25rem;
	padding: 0.75rem 1.5rem;
	line-height: 1.5;
}

.order-button {
	font-size: 1.0rem;
	padding: 0.75rem 1.5rem;
	line-height: 0.7;
}

.btn:hover {
	transform: scale(1.2); /* increase the size of the button on hover */
}

.btn {
	border-radius: 5px;
	/* adjust the value to change the amount of rounding */
	transition: all 0.3s ease-in-out;
	/* add a transition for smooth size change on hover */
}
.menu {
  display: flex;
  justify-content: center;
  margin-bottom: 2.5rem !important;
}
/* Pagination styles */
/* Pagination styles */
.pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}

.pagination a {
    color: #007BFF;
    padding: 8px 12px;
    text-decoration: none;
    border: 1px solid #007BFF;
    margin: 0 5px;
    border-radius: 4px;
}

/* Styling for active link */
.pagination a:active,
.pagination a:target {
    background-color: #007BFF;
    color: white;
}

.pagination a:hover {
    background-color: #f2f2f2;
}
.searchbar-input-group {
            display: grid;
            text-align: center;
            margin-top: 20px;
        }

        .searchbar-form-outline {
            flex: 1;
            margin-right: 10px;
        }

        .searchbar-form-control {
            width: 20%;
            -webkit-appearance: auto;
        }

        .searchbar-btn-primary {
            margin-left:10px;
        }
        .categoryNames {
        color: cornsilk !important;
        }
        

</style>

</head>
<body>

<div class="searchbar-input-group">
        <div class="searchbar-form-outline">
            <input type="search" id="searchbar-form" class="searchbar-form-control" />
            <!-- <label class="searchbar-form-label" for="searchbar-form1">Search</label> -->
             <button type="button" class="btn btn-primary searchbar-btn-primary" onclick="getItemsBySearchQuery()">search
            <!-- <i class="fas fa-search"></i> -->
        </button>
        </div>
       
    </div>
    
	<section class="ftco-section">
	
		<div class="container">
	
			<div class="row justify-content-center mb-5 pb-3">
				<div class="col-md-7 heading-section ftco-animate text-center">
				<div class="nav ftco-animate nav-pills fadeInUp ftco-animated menu" id="menuItems" role="tablist" aria-orientation="vertical">
		              <!-- <a class="nav-link active" id="v-pills-1-tab" data-toggle="pill" onclick="getItem()" role="tab" aria-controls="v-pills-1" aria-selected="true">Pizza</a>

		              <a class="nav-link" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="false">Drinks</a>

		              <a class="nav-link" id="v-pills-3-tab" data-toggle="pill" href="#v-pills-3" role="tab" aria-controls="v-pills-3" aria-selected="false">Vapes</a>
 -->
		             </div>
					<h2 class="mb-4">Hot Pizza Meals</h2>
					<p>Far far away, behind the word mountains, far from the
						countries Vokalia and Consonantia, there live the blind texts.</p>
				</div>
			</div>
		</div>
		<div class="container-wrap" id="productsListParent">
			<div class="row no-gutters d-flex" id="productsList">


				<!--
				<div class="col-lg-4 d-flex ftco-animate" id ="product1" onclick="showOrderItemPopUp()">
					<div class="services-wrap d-flex">
						<a href="#" class="img"
							style="background-image: url(/images/pizza-2.jpg);"></a>
						<div class="text p-4">
							<h3>Greek Pizza</h3>
							<p>Far far away, behind the word mountains, far from the
								countries Vokalia and Consonantia</p>
							<p class="price">
								<span>$2.90</span> <a href="#"
									class="ml-2 btn btn-white btn-outline-white">Order</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 d-flex ftco-animate" id ="product2" onclick="showOrderItemPopUp()">
					<div class="services-wrap d-flex">
						<a href="#" class="img order-lg-last"
							style="background-image: url(/images/pizza-3.jpg);"></a>
						<div class="text p-4">
							<h3>Caucasian Pizza</h3>
							<p>Far far away, behind the word mountains, far from the
								countries Vokalia and Consonantia</p>
							<p class="price">
								<span>$2.90</span> <a href="#"
									class="ml-2 btn btn-white btn-outline-white">Order</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-lg-4 d-flex ftco-animate">
					<div class="services-wrap d-flex">
						<a href="#" class="img order-lg-last"
							style="background-image: url(/images/pizza-4.jpg);"></a>
						<div class="text p-4">
							<h3>American Pizza</h3>
							<p>Far far away, behind the word mountains, far from the
								countries Vokalia and Consonantia</p>
							<p class="price">
								<span>$2.90</span> <a href="#"
									class="ml-2 btn btn-white btn-outline-white">Order</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 d-flex ftco-animate">
					<div class="services-wrap d-flex">
						<a href="#" class="img"
							style="background-image: url(/images/pizza-5.jpg);"></a>
						<div class="text p-4">
							<h3>Tomatoe Pie</h3>
							<p>Far far away, behind the word mountains, far from the
								countries Vokalia and Consonantia</p>
							<p class="price">
								<span>$2.90</span> <a href="#"
									class="ml-2 btn btn-white btn-outline-white">Order</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 d-flex ftco-animate">
					<div class="services-wrap d-flex">
						<a href="#" class="img"
							style="background-image: url(/images/pizza-6.jpg);"></a>
						<div class="text p-4">
							<h3>Margherita</h3>
							<p>Far far away, behind the word mountains, far from the
								countries Vokalia and Consonantia</p>
							<p class="price">
								<span>$2.90</span> <a href="#"
									class="ml-2 btn btn-white btn-outline-white">Order</a>
							</p>
						</div>
					</div>
				</div> -->
			</div>
		</div>
<div class="pagination" id="pagination">
		<a href="#">Previous</a>...
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>...
        <a href="#">Next</a>
        
        
        <!-- Add more page links here as needed -->
    </div>
		<!-- <div class="container">
			<div class="row justify-content-center mb-5 pb-3 mt-5 pt-5">
				<div class="col-md-7 heading-section text-center ftco-animate">
					<h2 class="mb-4">Our Menu Pricing</h2>
					<p class="flip">
						<span class="deg1"></span><span class="deg2"></span><span
							class="deg3"></span>
					</p>
					<p class="mt-5">Far far away, behind the word mountains, far
						from the countries Vokalia and Consonantia, there live the blind
						texts.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-1.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Italian Pizza</span>
								</h3>
								<span class="price">$20.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-2.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Hawaiian Pizza</span>
								</h3>
								<span class="price">$29.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-3.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Greek Pizza</span>
								</h3>
								<span class="price">$20.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-4.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Bacon Crispy Thins</span>
								</h3>
								<span class="price">$20.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-5.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Hawaiian Special</span>
								</h3>
								<span class="price">$49.91</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-6.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Ultimate Overload</span>
								</h3>
								<span class="price">$20.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-7.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Bacon Pizza</span>
								</h3>
								<span class="price">$20.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
					<div class="pricing-entry d-flex ftco-animate">
						<div class="img"
							style="background-image: url(/images/pizza-8.jpg);"></div>
						<div class="desc pl-3">
							<div class="d-flex text align-items-center">
								<h3>
									<span>Ham &amp; Pineapple</span>
								</h3>
								<span class="price">$20.00</span>
							</div>
							<div class="d-block">
								<p>A small river named Duden flows by their place and
									supplies</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> -->
	</section>

	<div class=" modal fade" id="orderItemModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" id="orderItemModelTitle">Add to Cart</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeOrderItemModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="hidden" name="productId">
					<form id="addCategoryForm">
						<div class="product-image">
							<img src="" alt="Product Image">
						</div>
						<div class="product-info">
							<h2>Product Name</h2>
							<p>Product Description</p>
							<label for="quantity">Quantity:</label>
							<div class="quantity">
								<button class="minus-btn" type="button" name="button">-</button>
								<input type="number" id="orderItemModelQuantity" name="quantity"
									min="1" value="1">
								<button class="plus-btn" type="button" name="button">+</button>
							</div>
						</div>
					</form>
					<p class="price">
						<span>$ 20 </span>
						<button class="ml-2 btn btn-white btn-outline-white order-button"
							onclick="addToCart(document.getElementById('orderItemModel').querySelector('input[name=\'productId\']').textContent)">
							Add to Cart</button>

					</p>
				</div>

			</div>
		</div>
	</div>

	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>

	<%@include file="footer.jsp"%>

</body>
</html>
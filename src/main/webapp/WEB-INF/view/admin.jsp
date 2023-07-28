<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Page - eCommerce Website</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css"
	rel="stylesheet" />
	<link rel="stylesheet" href="/jqwidgets/styles/jqx.base.css">
<link rel="stylesheet" href="/jqwidgets/styles/jqx.dark.css"
	type="text/css" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>

<%@include file="header.jsp"%>
<script type="text/javascript" src="/js/Cart.js"></script>
<script type="text/javascript" src="/js/admin.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
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
<link rel="stylesheet" href="/css/jqxGrid.css" type="text/css" />

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do"
	rel="stylesheet">
<style>
.col-md-4 {
	margin-top: 30px;
}

.col-lg-3 {
	margin-left: 70px;
}

.col-lg-5 {
	margin-left: 63px;
	margin-bottom: 30px;
}

body {
	color: white;
}

.card-title {
	color: white;
}

.card {
	background: #101315;
	border: none;
}

.card:hover {
	background-color: #1f2529;
	transition: all 0.2s ease-in-out;
	transform: scale(1.15);
}

 .modal-header .modal-title {
	color: black;
}

.modal-body {
	background-color: #101315;
} 

.categories {
	background-color: #101315;
	color: white;
}
span {
  color: #000000; 
  display: flex;
  justify-content: center;
  align-items: center;}
/* select#category {
  background-color: #101315;
  color: white;
} */
/* form, label, input, textarea, button {
    color: black;
  } */
</style>
<script>
	$(document).ready(function() {
		$('.select2').select2();
	});
</script>

</head>

<body>

	<!-- Main Content -->
	<div class="container mt-3">
		<h1>Dashboard</h1>
		<div class="row">
			<!-- <div class=" col-lg-3 col-md-4 ftco-animate text-center"
				onclick="getCustomerList()">
				<div class="card">
					<div class="card-body">
						<div>
							<img src="/gif/user.gif" alt="Animated GIF" style="width: 50px;">
						</div>
						<h5 class="card-title">Customers</h5>
						<p class="card-text" id="userCount">50</p>
						<a href="#" class="btn btn-primary">View</a>
					</div>
				</div>
			</div> -->
			<div class="col-lg-3 col-md-4 col-sm-6 col-12 ftco-animate"
				onclick="populatecustomerDetailsModal()">
				<div class="card rounded-lg">
					<div class="card-body">
						<div class="text-center">
							<!-- <img src="/gif/user.gif" alt="Animated GIF" class="w-50 zoom"> -->
						</div>
						<h5 class="card-title text-center mt-3">Customers</h5>
						<p class="card-text text-center" id="userCount">50</p>
						<div class="text-center">
							<a class="btn btn-primary rounded-pill mt-3"
								style="background-color: transparent; color: #f9a825;">View</a>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-4 col-sm-6 col-12 ftco-animate"
				onclick="populateProductDetailsModal()">
				<div class="card rounded-lg">
					<div class="card-body">
						<div class="text-center">
							<!-- <img src="/gif/user.gif" alt="Animated GIF" class="w-50 zoom"> -->
						</div>
						<h5 class="card-title text-center mt-3">Products</h5>
						<p class="card-text text-center" id="productCount">50</p>
						<div class="text-center">
							<a href="#" class="btn btn-primary rounded-pill mt-3"
								style="background-color: transparent; color: #f9a825;">View</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-4 col-sm-6 col-12 ftco-animate"
				onclick="myFunction()">
				<div class="card rounded-lg">
					<div class="card-body">
						<div class="text-center">
							<!-- <img src="/gif/user.gif" alt="Animated GIF" class="w-50 zoom"> -->
						</div>
						<h5 class="card-title text-center mt-3">Categories</h5>
						<p class="card-text text-center" id="categoryCount">50</p>
						<div class="text-center">
							<a href="#" class="btn btn-primary rounded-pill mt-3"
								style="background-color: transparent; color: #f9a825;">View</a>
						</div>
					</div>
				</div>
			</div>


			<div class=" col-lg-5 col-md-4 ftco-animate text-center"
				onclick="addProduct()">
				<div class="card">
					<div class="card-body">
						<div>
							<!-- <img src="/gif/add-product.gif" alt="Animated GIF"
								style="width: 50px;"> -->
						</div>
						<h5 class="card-title">Add Product</h5>
						<a href="#" class="btn btn-primary">Add</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-5 col-md-4 ftco-animate text-center"
				onclick="removeProduct()">
				<div class="card">
					<div class="card-body">
						<div>
							<!-- <img src="/gif/remove-product.gif" alt="Animated GIF"
								style="width: 50px;"> -->
						</div>
						<h5 class="card-title">Remove Product</h5>
						<a href="#" class="btn btn-primary">Remove</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-5 col-md-4 ftco-animate text-center"
				onclick="addCategory()">
				<div class="card">
					<div class="card-body">
						<div>
							<!-- <img src="/gif/add-product.gif" alt="Animated GIF"
								style="width: 50px;"> -->
						</div>
						<h5 class="card-title">Add category</h5>
						<a href="#" class="btn btn-primary">Add</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-5 col-md-4 ftco-animate text-center"
				onclick="removeCategoryShowModel()">
				<div class="card">
					<div class="card-body">
						<div>
							<!-- <img src="/gif/remove-product.gif" alt="Animated GIF"
								style="width: 50px;"> -->
						</div>
						<h5 class="card-title">Remove category</h5>
						<a href="#" class="btn btn-primary">Remove</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- add category model start -->

	<div class="modal fade" id="addCategoryModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" id="addCategoryModelTitle">Add
						Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="addCategoryForm">
						<div class="form-group">
							<label for="categoryName">Category Name</label> <input
								type="text" class="form-control" id="categoryName"
								name="categoryName" placeholder="Enter category name" required>
						</div>
						<div class="form-group">
							<label for="categoryDescription">Category Description</label>
							<textarea class="form-control" id="categoryDescription"
								name="categoryDescription" rows="3"
								placeholder="Enter category description"></textarea>
						</div>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							id="addCategoryModelAddButton" data-dismiss="modal"
							onclick="addCategoryToDatabase()">Add Category</button>
					</form>

				</div>

			</div>
		</div>
	</div>

	<!--  add category model end -->

	<!-- add product model start -->

	<div class="modal fade" id="addProductModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" id="addProductModelTitle">Add Product</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="addProductForm">
						<div class="form">
							<label for="category">Choose a Category of Product:</label>
							<div id="listOfCategories"></div>
						</div>
						<br>
						<div class="form-group">
							<label for="product_name">Product Name:</label> <input
								type="text" class="form-control" id="product_name"
								name="product_name" required>
						</div>
						<div class="form-group">
							<label for="product_description">Product Description:</label>
							<textarea class="form-control" id="product_description"
								name="product_description" required></textarea>
						</div>
						<div class="form-group">
							<label for="product_photo">Product Photo:</label> <input
								type="file" class="form-control-file" id="product_photo"
								name="product_photo" accept="image/*" required>
						</div>
						<div class="form-group">
							<label for="product_price">Product Price:</label> <input
								type="number" class="form-control" id="product_price"
								name="product_price" min="0.01" step="0.01" required>
						</div>
						<div class="form-group">
							<label for="product_discount">Product Discount:</label> <input
								type="number" class="form-control" id="product_discount"
								name="product_discount" min="0" max="100" step="1" required>
						</div>
						<div class="form-group">
							<label for="product_count">Product Count:</label> <input
								type="number" class="form-control" id="product_count"
								name="product_count" min="1" step="1" required>
						</div>

						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							id="addProductModelAddButton" data-dismiss="modal"
							onclick="addProductToDatabase()">Add Product</button>
					</form>


				</div>
			</div>
		</div>
	</div>
	<audio src="/audio/alert.mp3" id="alertAudio"></audio>

	<!-- add product model end -->

	<!-- delete category start -->
	<div class="modal fade" id="removeCategoryModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" id="removeCategoryModelTitle">Remove
						Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="removeCategoryForm">
						<div id="CategoryListContainer"></div>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							id="removeCategoryModel" data-dismiss="modal"
							onclick="RemoveCategoryFromDatabase()">Remove Category</button>
					</form>

				</div>

			</div>
		</div>
	</div>
	<!-- delete category end -->

	<!-- delete product start -->
	<div class="modal fade" id="removeProductModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" id="removeProductModelTitle">Remove
						Product</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="removeProductForm">
						<div id="listOfCategoriesToRemoveProduct"></div>

						<div id="listOfProducts"></div>
						<br />
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							id="removeProductModel" data-dismiss="modal"
							onclick="RemoveProductFromDatabase()">Remove Product</button>
					</form>

				</div>

			</div>
		</div>
	</div>
	<!-- delete product end -->

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

	<!-- <div class=" modal fade" id="customerDetailsModel" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
		onclick="myFunction()">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color: black" id="customerDetailsModelTitle">Customer Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closecustomerDetailsModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;">
						<div id="customerDetailsJqxGrid"></div>
					</div>

				</div>

			</div>
		</div>
	</div> -->
	
	<div class=" modal fade" id="customerDetailsModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color: black"
						id="customerDetailsModalTitle">Customer Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeCustomerDetailsModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;">
						<div id="customerDetailsJqxGrid"></div>
					</div>

				</div>

			</div>
		</div>
	</div>
	
	<div class=" modal fade" id="productDetailsModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color: black"
						id="productDetailsModalTitle">Product Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeProductDetailsModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;">
						<div id="productDetailsJqxGrid"></div>
					</div>

				</div>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>


</body>
</html>

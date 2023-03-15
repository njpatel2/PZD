<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Admin Page - eCommerce Website</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
	integrity="sha512-QnGe+vNwF8YG4Wad7CjxunmfNbz+v6Ksw+YrHJcMLeh6CJjwOnO2+uKKP6E/lF6fg/hGkyCUjK6ZtyKMq3E2Q=="
	crossorigin="anonymous" />
<%@include file="Common.jsp"%>
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
}
</style>

</head>

<body>

	<!-- Main Content -->
	<div class="container mt-3">
		<h1>Dashboard</h1>
		<div class="row">
			<div class=" col-lg-3 col-md-4 ftco-animate text-center"
				onclick="myFunction()">
				<div class="card">
					<div class="card-body">
						<div>
							<img src="/gif/user.gif" alt="Animated GIF" style="width: 50px;">
						</div>
						<h5 class="card-title">Customers</h5>
						<p class="card-text">50</p>
						<a href="#" class="btn btn-primary">View</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-3 col-md-4 ftco-animate text-center"
				onclick="myFunction()">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Products</h5>
						<p class="card-text">100</p>
						<a href="#" class="btn btn-primary">View</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-3 col-md-4 ftco-animate text-center"
				onclick="myFunction()">
				<div class="card">
					<div class="card-body">
						<div>
							<img src="/gif/category.gif" alt="Animated GIF"
								style="width: 50px;">
						</div>
						<h5 class="card-title">Categories</h5>
						<p class="card-text">20</p>
						<a href="#" class="btn btn-primary">View</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-5 col-md-4 ftco-animate text-center"
				onclick="myFunction()">
				<div class="card">
					<div class="card-body">
						<div>
							<img src="/gif/add-product.gif" alt="Animated GIF"
								style="width: 50px;">
						</div>
						<h5 class="card-title">Add Product</h5>
						<a href="#" class="btn btn-primary">Add</a>
					</div>
				</div>
			</div>
			<div class=" col-lg-5 col-md-4 ftco-animate text-center"
				onclick="myFunction()">
				<div class="card">
					<div class="card-body">
						<div>
							<img src="/gif/remove-product.gif" alt="Animated GIF"
								style="width: 50px;">
						</div>
						<h5 class="card-title">Remove Product</h5>
						<a href="#" class="btn btn-primary">Remove</a>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- model for showing the data -->
<div class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	<%@include file="footer.jsp"%>

</body>
</html>

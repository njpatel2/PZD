<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css"
	rel="stylesheet">
<!-- <link
	href="/css/UserProfileStyle.css"
	rel="stylesheet"> -->
<%@include file="header.jsp"%>
<script type="text/javascript" src="/js/User.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
<style>
.user-info {
	
} /* padding: 20px; */

.order-button {

	font-size: 0.8rem;
	padding: 0.60rem 1.0rem;
	line-height: 0.6;
}

.btn:hover {
	transform: scale(1.15); /* increase the size of the button on hover */
}

.btn {
	border-radius: 5px;
	/* adjust the value to change the amount of rounding */
	transition: all 0.3s ease-in-out;
	/* add a transition for smooth size change on hover */
}
.updateProfileModel{

    margin-top: 0px!important;
    box-shadow: 0px 0px 0px;
    color : black;
    }
    
    
   
.modal-body {
	padding : 0px;
}
    
</style>

</head>
<body>
	<section style="background-color: transparent;">
		<div class="container py-4">


			<div class="row">
				<div class="col-lg-3">
					<div class="card mb-4">
						<div class="card-body text-center">
							<!--   <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
              class="rounded-circle img-fluid" style="width: 150px;"> -->
							<br /> <img src="/images/user.png" alt="avatar"
								class="rounded-circle img-fluid" style="width: 150px;"> <br />
							<br /> <br />
							<h5 class="my-3" id="name" style="color: black">${name}</h5>
							<div class="d-flex justify-content-center mb-2"></div>
						</div>
					</div>

				</div>
				<div class="col-lg-8">
					<div class="card mb-6 user-info">
						<div class="card-body">
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Full Name</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0" id="name">${name}</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Email</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0" id="email">${email}</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Phone</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0" id="contact">${contact}</p>
								</div>
							</div>

							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Address</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0" id="address">${address}</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Items in Cart</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0" id="itemsInCart">${itemsInCart}</p>
								</div>
							</div>
							<button class="ml-2 btn btn-white  order-button float-right"
								onclick="openUpdateProfileModel(document.getElementById('email').innerHTML)">Edit</button>

						</div>
					</div>

				</div>
			</div>
		</div>
	</section>
	
	<div class=" modal fade" id="updateProfileModel" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true" onclick="">
		<div class="modal-dialog modal-dialog-centered model-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header  ">
					<h5 class="modal-title" style="color : black" id="updateProfileModelTitle">Update Profile</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeUpdateProfileModel">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form name="form" id="form" method="post" class="form-login updateProfileModel" onsubmit="updateUserProfile()" >
			         <label for="email">Email:</label>
			          <label id ="emailBlock"></label><br/>
			          
			         
			          <label for="username">Username:</label>
			          <input type="text" id="updatedUsername" required>
			
			          <label for="contact">Contact Number:</label>
			          <input type="text"  id="updatedContact" required>
			          
			          <label for="address">Address</label>
			          <input type="text" id="updatedAddress" required>
          

          <input type="hidden" name = "role" id="role" class="form-control form-control-lg" value="ROLE_USER"/>
          
          <input type="submit" value="Update">
				</div>

			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>
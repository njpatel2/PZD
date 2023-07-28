
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/commonc.css">
<link rel="stylesheet" href="/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="/css/animate.css">
<link rel="stylesheet" href="/css/owl.carousel.min.css">
<link rel="stylesheet" href="/css/owl.theme.default.min.css">
<link rel="stylesheet" href="/css/magnific-popup.css">
<link rel="stylesheet" href="/css/aos.css">
<link rel="stylesheet" href="/css/ionicons.min.css">
<link rel="stylesheet" href="/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="/css/jquery.timepicker.css">
<link rel="stylesheet" href="/css/flaticon.css">
<link rel="stylesheet" href="/css/icomoon.css">
<link rel="stylesheet" href="/css/style.css">



<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-migrate-3.0.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/aos.js"></script>
<script type="text/javascript" src="/js/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="/js/scrollax.min.js"></script>
<script type="text/javascript" src="/js/lib/jquery/jquery.form.min.js"></script>
<script type="text/javascript" src="/js/header.js"></script>
<script type="text/javascript" src="/js/jquery.stellar.min.js"></script>
<script src="/js/owl.carousel.min.js"></script>
<script src="/js/jquery.waypoints.min.js"></script>
<script src="/js/jquery.magnific-popup.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>

<style type="text/css">

</style>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand"> <span> <img
					src="/images/pizzalogo.png" width="200" height="70"></img>
			</span>
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="/home" class="nav-link">Home</a></li>


					<li class="nav-item"><a href="/cart/cart"><img
							class="hover-effect" src="/gif/cart.png" width="60px"></img><span
							id="totalBill" class="hover-effect totalBill">0.00 $</span></a></li>
					<li class="nav-item"><a href="/user/getProfile"
						class="nav-link hover-effect">Profile</a></li>
					<c:if test="${sessionScope.userRole == '[ROLE_ADMIN]'}">
						<li class="nav-item"><a href="/admin/admin" class="nav-link">Admin</a></li>
						<li class="nav-item"><a href="/Orders"
							class="nav-link hover-effect">Orders</a></li>
					</c:if>
					<li class="nav-item"><a href="/logout"
						class="nav-link hover-effect">Logout</a></li>
					

				</ul>

			</div>
		</div>
	</nav>
	<!-- END nav -->


</body>
</html>


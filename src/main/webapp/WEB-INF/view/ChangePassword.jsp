<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/commonc.css">
<link rel="stylesheet" href="/css/LoginStyle.css">
<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
	rel='stylesheet'>

<script type="text/javascript" src="/js/RegistrationPage.js"></script>
<script type="text/javascript" src="/js/home.js"></script>
<!-- Boxicons CSS -->

<%@include file="header.jsp"%>

</head>
<body>
	<h2 class="text-center" id="isOTPValid">${isOTPValid}</h2>
	<section class="login-container forms">
		<div class="form signup">
			<div class="form-content">
				<header>Update Password</header>
				<form name="form" id="form" method="post" action="/updatePassword">

					<!-- <div class="field input-field">
                            <input type="email" name="email" id="email"   placeholder="Email" class="input">
                        </div> -->
					<h5 class="text-center" id="email" style="color: black;">${email}</h5>

					<div class="field input-field">
						<input type="password" name="password" id="password"
							placeholder="Create password" class="password"> <i
							class='bx bx-hide eye-icon'></i>
					</div>

					<div class="field input-field">
						<input type="password" name="password2" id="password2"
							placeholder="Confirm password" class="password"> <i
							class='bx bx-hide eye-icon'></i>
					</div>

					<div class="field button-field">
						<button type="submit" >Update Password</button>
					</div>

				</form>
			</div>

		</div>
	</section>
	<%@include file="footer.jsp"%>
</body>
</html>
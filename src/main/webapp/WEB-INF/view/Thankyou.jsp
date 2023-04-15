<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thank you</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />

<%@include file="header.jsp"%>

<style>
	/* center the image */
	.text-center {
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}

	/* make the image responsive */
	img {
		max-width: 100%;
		height: auto;
	}
</style>

</head>
<body>
	<div style="background: transparent; width: 100%; height: 100%;">
		<div class="text-center">
			<img src="/gif/Thankyou.png" alt="Animated GIF">
		</div>
		<h2 class="text-center">Thank you for shopping with us</h2>
	</div>
	
	
	
		<%@include file="footer.jsp"%>
</body>
</html>

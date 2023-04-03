<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<tr class="on-hover-change-color" id="product7">
		<td data-th="image"><div>
				<img src="/images/pizza-2.jpg" alt=""
					class="img-fluid d-none d-md-block rounded mb-2 shadow custom-img-size">
			</div></td>
		<td data-th="Product"><div class="row">
				<div class="col-md-9 text-left mt-sm-2">
					<h4>Pizza 2</h4>
				</div>
				<input type="hidden" name="productId" value="7">
			</div></td>
		<td data-th="Price" style="color: white;"><span
			class="mr-2 text-center white-text">$25.0</span>
		<div class="d-flex align-items-center flex-wrap input-container">
				<input type="text"
					class="form-control form-control-sm text-center mb-2"
					name="quantity" value="1" required="required" min="1"
					onchange="updateCartProductQuantity()" id="quantity-input">
			</div></td>
		<td class="delbtn"><button
				class="btn btn-white border-secondary bg-white btn-md mb-2 ml-md-auto rounded"
				onclick="deleteProductFromCart(this)">
				<i class="fa fa-trash-o" style="font-size: 24px; color: red;"></i>
			</button></td>
		<td data-th="subTotal"><span class="text-right white-text"></span></td>
	</tr>
</body>
</html>
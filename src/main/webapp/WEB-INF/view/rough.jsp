<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<tr class="on-hover-change-color" id="product6">
		<td data-th="Product"><div class="row">
				<div class="col-sm-2 box box2 text-left">
					<img src="/images/pizza-1.jpg" alt=""
						class="img-fluid d-none d-md-block rounded mb-2 shadow">
				</div>
				<div class="col-md-9 text-left mt-sm-2">
					<h4>Pizza-1</h4>
				</div>
				<input type="hidden" name="productId" value="6">
			</div></td>
		<td data-th="Price" style="color: rgb(255, 255, 255);">$ 20.0</td>
		<td data-th="Quantity" style="color: rgb(255, 255, 255);"><input
			type="text" id="6" name="productQuantity" value="18" required="true"
			min="1" class="form-control form-control-sm text-center"
			onchange="updateCartProductQuantity(this.id)"></td>
		<td data-th="" class="actions"><div class="text-center">
				<button type="button" id="6"
					class="btn btn-white border-secondary bg-white btn-md mb-2 rounded"
					onclick="deleteProductFromCart(this.id)">
					<i class="fa fa-trash-o" style="font-size: 31px; color: red;"></i>
				</button>
			</div></td>
	</tr>
</body>
</html>
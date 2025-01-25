<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Three Sections Page</title>
<style>
body, html {
	margin: 0;
	padding: 0;
	height: 100%;
	font-family: Arial, sans-serif;
}

.section {
	box-sizing: border-box;
	padding: 20px;
	/* border: 1px solid #ccc; */
	border-radius: 10px; /* Rounded corners */
	margin-bottom: 20px; /* Add margin between sections */
	overflow: hidden; /* Prevent content from overflowing */
}

.section-1 {
	background-color: #f0f0f0;
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
}

.section-1 .image-container {
	width: 90%; /* Set width to desired value */
	height: 50%; /* Set height to desired value */
	max-height: 300px; /* Maximum height for the container */
	overflow: hidden; /* Hide overflow from the rounded corners */
	border-radius: 15px; /* Make the corners slightly rounded */
	display: flex; /* Center content vertically */
	justify-content: center; /* Center content vertically */
	position: relative; /* Add position property back */
}

.section-1 img {
	width: 100%;
	height: auto;
	object-fit: cover; /* Maintain aspect ratio without cropping */
	object-position: center center;
	/* Center the image within the container */
	display: block;
	align-self: center;
}

.section-1 .section-1-content {
	text-align: left;
	padding: 20px;
	width: 90%;
}

.section-1 h1, .section-1 p {
	margin: 0;
}

.section-2 {
	height: auto;
	display: flex;
}

.section-2 .sub-section {
	padding: 20px;
	/* border: 1px solid #ccc; */
	border-radius: 10px; /* Rounded corners */
	overflow: auto; /* Prevent content from overflowing */
}

.section-2 .sub-section-1 {
	flex: 70%;
	margin-right: 10px; /* Add margin between sub-sections */
}

.section-2 .sub-section-2 {
	flex: 30%;
}
/* css for the section2A */

/* Styles for Section 2A menu and product list */
/* Styles for Section 2A menu and product list */
.sub-section-1 {
	display: flex;
	flex-direction: row; /* Display side by side */
	align-items: flex-start; /* Align menu and products at the top */
	justify-content: space-between; /* Space between menu and products */
}

.menu {
	list-style: none;
	padding: 0;
	width: 125px; /* Limit the maximum width of the menu */
	margin-right: 20px; /* Add margin between menu and products */
}

.menu h2 {
	margin-bottom: 10px;
}

.menu-item {
	margin-bottom: 10px;
	position: relative;
	cursor: pointer;
	transition: background-color 0.3s, color 0.3s; /* Add smooth transitions */
	
}

.sub-menu {
	display: none;
	position: absolute;
	top: 0;
	left: 100%;
	background-color: #fff;
	/* border: 1px solid #ccc; */
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
	padding: 10px;
	border-radius: 5px;
	width: 125px;
}

.menu-item:hover {
  background-color: #f0f0f0; /* Change background color on hover */
  color: #007bff; /* Change text color on hover */
}

.sub-menu {
  display: none;
  position: absolute;
  top: -10px;
  left: 100%;
  background-color: #fff;
  /* border: 1px solid #ccc; */
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
  padding: 10px;
  border-radius: 5px;
  width: 150px;
  animation: fadeInRight 0.3s ease; /* Add fade-in animation */
  background-color: #f0f0f0;
}

.menu-item:hover .sub-menu {
  display: block;
}


.product-list {
	display: flex;
	flex-wrap: wrap;
}

.product-card {
	/* flex: 1; */
	width: 650px;
	margin: 5px;
	padding: 10px;
	/* border: 1px solid #ccc; */
	border-radius: 10px; /* Rounded corners for the card */
	display: flex;
	flex-direction: row;
	align-items: center;
	text-align: left;
}

.product-image {
	width: auto;
	height: 100%;
	margin-right: 10px;
	flex-shrink: 0;
}

.product-image img {
	width: 100px;
	height: 100%;
	object-fit: cover;
	border-radius: 5px;
}

.product-details {
	flex: 1;
	width: 100%;
}

.product-details p {
	margin: 5px 0; /* Reduce top and bottom margin */
}

.product-details h3 {
	font-weight: bold;
	margin: 0; /* Remove default margin */
}

.add-to-cart-button {
	padding: 5px 10px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 10px;
}
.sub-section-2 {
  padding: 20px;
 /*  border: 1px solid #ccc; */
  border-radius: 10px;
  overflow: hidden;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.data-table th, .data-table td {
  padding: 10px;
 /*  border: 1px solid #ccc; */
  text-align: left;
}

.table-button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;
}
.column1 {
  width: 75%;
}

.column2 {
  width: 25%;
}

.google-map {
  width: 100%;
  padding-bottom: 100%; /* Create a square aspect ratio */
  background-color: #f0f0f0;
  border-radius: 10px;
  position: relative;
  overflow: hidden;
}
/* Responsive styles */
@media ( max-width : 768px) {
	.section {
		height: auto;
	}
	.section-1 .section-1-content {
		text-align: center;
		padding: 20px;
	}
	.section-2 {
		flex-direction: column;
	}
	.section-2 .sub-section {
		margin-right: 0;
		margin-bottom: 10px;
	}
	.sub-section-1 {
		flex-direction: column;
	}
	.menu {
		max-width: none; /* Remove the maximum width for menu in mobile view */
		margin-right: 0;
		/* Remove margin between menu and products in mobile view */
	}
	.product-card {
		width: 100%; /* Occupy full width in mobile view */
	}
	.sub-section-2 {
    padding: 10px;
  }
	/* Define fade-in animation */

}
@keyframes fadeInRight {
  from {
    opacity: 0;
    transform: translateX(10px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}
</style>
</head>
<body>
	<div class="section section-1">
		<div class="image-container">
			<img src="/images/pizza-2.jpg" alt="Image">
		</div>
		<div class="section-1-content">
			<h1>Image Title</h1>
			<p>This is a brief explanation about the image. This text should
				fit within the section and be visible. If the text is longer, the
				section will dynamically adjust its height to accommodate it.This is
				a brief explanation about the image. This text should fit within the
				section and be visible. If the text is longer, the section will
				dynamically adjust its height to accommodate it.This is a brief
				explanation about the image. This text should fit within the section
				and be visible. If the text is longer, the section will dynamically
				adjust its height to accommodate it.This is a brief explanation
				about the image. This text should fit within the section and be
				visible. If the text is longer, the section will dynamically adjust
				its height to accommodate it.This is a brief explanation about the
				image. This text should fit within the section and be visible. If
				the text is longer, the section will dynamically adjust its height
				to accommodate it.</p>
		</div>
	</div>
	<div class="section section-2">







		<div class="sub-section sub-section-1">
			<div class="menu">
				<h2>Menu</h2>
				<ul class="menu">
					<li class="menu-item">Menu Item 1
						<ul class="sub-menu">
							<li>Sub Item 1</li>
							<li>Sub Item 2</li>
							<li>Sub Item 3</li>
						</ul>
					</li>
					<li class="menu-item">Menu Item 2
						<ul class="sub-menu">
							<li>Sub Item 1</li>
							<li>Sub Item 2</li>
						</ul>
					</li>
					<li class="menu-item">Menu Item 3
						<ul class="sub-menu">
							<li>Sub Item 1</li>
						</ul>
					</li>
				</ul>
			</div>

			<div class="product-list">

				<div class="product-card">
					<div class="product-image">
						<img src="/images/pizza-2.jpg" alt="Product 1">
					</div>
					<div class="product-details">
						<h3>Product 1</h3>
						<p>Details about Product 1. Lorem ipsum dolor sit amet,
							consectetur adipiscing elit.</p>
						<button class="add-to-cart-button">Add to Cart</button>
					</div>
				</div>
				<div class="product-card">
					<div class="product-image">
						<img src="/images/pizza-2.jpg" alt="Product 1">
					</div>
					<div class="product-details">
						<h3>Product 1</h3>
						<p>Details about Product 1. Lorem ipsum dolor sit amet,
							consectetur adipiscing elit.</p>
						<button class="add-to-cart-button">Add to Cart</button>
					</div>
				</div>
				<div class="product-card">
					<div class="product-image">
						<img src="/images/pizza-2.jpg" alt="Product 1">
					</div>
					<div class="product-details">
						<h3>Product 1</h3>
						<p>Details about Product 1. Lorem ipsum dolor sit amet,
							consectetur adipiscing elit.</p>
						<button class="add-to-cart-button">Add to Cart</button>
					</div>
				</div>
				<div class="product-card">
					<div class="product-image">
						<img src="/images/pizza-2.jpg" alt="Product 1">
					</div>
					<div class="product-details">
						<h3>Product 1</h3>
						<p>Details about Product 1. Lorem ipsum dolor sit amet,
							consectetur adipiscing elit.</p>
						<button class="add-to-cart-button">Add to Cart</button>
					</div>
				</div>
				<div class="product-card">
					<div class="product-image">
						<img src="/images/pizza-2.jpg" alt="Product 1">
					</div>
					<div class="product-details">
						<h3>Product 1</h3>
						<p>Details about Product 1. Lorem ipsum dolor sit amet,
							consectetur adipiscing elit.</p>
						<button class="add-to-cart-button">Add to Cart</button>
					</div>
				</div>
				<div class="product-card">
					<div class="product-image">
						<img src="/images/pizza-2.jpg" alt="Product 1">
					</div>
					<div class="product-details">
						<h3>Product 1</h3>
						<p>Details about Product 1. Lorem ipsum dolor sit amet,
							consectetur adipiscing elit.</p>
						<button class="add-to-cart-button">Add to Cart</button>
					</div>
				</div>



			</div>

		</div>






		<div class="sub-section sub-section-2">
  <h2>Bill</h2>
  
  <!-- Table -->
  <table class="data-table">
    <tr>
      <th class="column1">Column 1</th>
      <th class="column2">Column 2</th>
    </tr>
    <tr>
      <td class="column1">Row 1 Data 1</td>
      <td class="column2">Row 1 Data 2</td>
    </tr>
    <tr>
      <td class="column1">Row 2 Data 1</td>
      <td class="column2">Row 2 Data 2</td>
    </tr>
    <!-- Add more rows as needed -->
  </table>
  
  <!-- Button -->
  <button class="table-button">Click Me</button>
  
  <!-- Google Map -->
  <h5>Google Map</h5>
  <div class="google-map"></div>
</div>

	</div>
</body>
</html>

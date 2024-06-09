<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<title>Green Valley - Customer Order</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="keywords" />
<meta content="" name="description" />

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
	rel="stylesheet" />

<!-- Icon Font Stylesheet -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet" />

<!-- Libraries Stylesheet -->
<link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet" />

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet" />
</head>

<body>
	<!-- Spinner Start -->
	<div id="spinner"
		class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50 d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->

	<!-- Navbar start -->
	<div class="container-fluid fixed-top">
		<div class="container topbar bg-primary d-none d-lg-block">
			<div class="d-flex justify-content-between">
				<div class="top-info ps-2">
					<small class="me-3"><i
						class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#"
						class="text-white">97 Man Thien Street, Thu Duc City</a></small> <small
						class="me-3"><i
						class="fas fa-envelope me-2 text-secondary"></i><a href="#"
						class="text-white">vuquocbao673@gmail.com</a></small>
				</div>
				<div class="top-link pe-2">
					<a href="#" class="text-white"><small class="text-white mx-2">Privacy
							Policy</small>/</a> <a href="#" class="text-white"><small
						class="text-white mx-2">Terms of Use</small>/</a> <a href="#"
						class="text-white"><small class="text-white ms-2">Sales
							and Refunds</small></a>
				</div>
			</div>
		</div>
		<div class="container px-0">
			<nav class="navbar navbar-light bg-white navbar-expand-xl">
				<a href="home.htm" class="navbar-brand">
					<h1 class="text-primary display-6">Green Valley</h1>
				</a>
				<button class="navbar-toggler py-2 px-3" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
					<span class="fa fa-bars text-primary"></span>
				</button>
				<div class="collapse navbar-collapse bg-white" id="navbarCollapse">
					<div class="navbar-nav mx-auto">
						<a href="home.htm" class="nav-item nav-link">Home</a> <a
							href="cart.htm" class="nav-item nav-link">Cart</a> <a
							href="contact.htm" class="nav-item nav-link">Contact</a>
					</div>
					<div class="d-flex m-3 me-0">
						<a href="cart.htm" class="position-relative me-4 my-auto"> <i
							class="fa fa-shopping-bag fa-2x"></i>
						</a>
						<div class="nav-item dropdown">
							<a href="#" class="my-auto nav-link dropdown-toggle"
								data-bs-toggle="dropdown"> <i class="fas fa-user fa-2x"></i>
							</a>
							<button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
								data-bs-target="#navbarCollapse">
								<span class="fa fa-bars text-primary"></span>
							</button>
										<div class="dropdown-menu m-0 bg-secondary rounded-0">
											<c:choose>
												<c:when test="${empty sessionScope.customerId}">
													<a href="login.htm" class="dropdown-item">Login</a>
													<a href="register.htm" class="dropdown-item">Register</a>
												</c:when>
									<c:otherwise>
										<a href="checkout.htm" class="dropdown-item">Checkout</a>
										<a href="customer-profile.htm" class="dropdown-item">View
											profile</a>
										<a href="customer-order.htm" class="dropdown-item">View
											orders</a>
										<div class="dropdown-divider"></div>
										<form id="logout-form"
											action="${pageContext.request.contextPath}/logout.htm"
											method="post">
											<button type="submit" class="dropdown-item text-danger">
												Logout</button>
										</form>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->

	<!-- Single Page Header start -->
	<div class="container-fluid page-header py-5">
		<h1 class="text-center text-white display-6">Checkout</h1>
	</div>
	<!-- Single Page Header End -->

	<!-- Checkout Page Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<h1 class="mb-4">Billing details</h1>
			<form action="checkout.htm" method="post">
				<div class="row g-5">
					<div class="col-md-12 col-lg-6 col-xl-7">
						<div class="row">
							<div class="col-md-12 col-lg-6">
								<div class="form-item w-100">
									<label class="form-label my-3">First Name<sup>*</sup>
									</label> <input type="text" class="form-control" required
										name="firstName" maxlength="50"
										value="${profileDto.firstName}" />
								</div>
							</div>
							<div class="col-md-12 col-lg-6">
								<div class="form-item w-100">
									<label class="form-label my-3">Last Name<sup>*</sup></label> <input
										type="text" class="form-control" required name="lastName"
										maxlength="50" value="${profileDto.lastName}" />
								</div>
							</div>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Email Address</label> <input
								type="email" class="form-control" name="email"
								value="${profileDto.email}" />
						</div>
						<div class="form-item">
							<label class="form-label my-3">Mobile<sup>*</sup></label> <input
								type="tel" class="form-control" required name="phoneNumber"
								maxlength="11" value="${profileDto.phoneNumber}" />
						</div>
						<div class="form-item">
							<label class="form-label my-3">Address <sup>*</sup></label> <input
								type="text" class="form-control"
								placeholder="House Number Street Name" required name="address"
								maxlength="200" value="${profileDto.address}" />
						</div>
						<div class="form-item my-3">
							<textarea name="note" class="form-control" spellcheck="false"
								cols="30" rows="4" placeholder="Order Note" maxlength="200"></textarea>
						</div>
					</div>
					<div class="col-md-12 col-lg-6 col-xl-5">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Products</th>
										<th scope="col">Name</th>
										<th scope="col">Price</th>
										<th scope="col">Quantity</th>
										<th scope="col">Total</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="subTotal" value="0" />
									<c:choose>

										<c:when test="${not empty carts}">
											<c:forEach var="cart" items="${carts}">
												<tr data-product-id="${cart.cartId.productId}"
													data-product-price="${cart.productPrice}">
													<td>
														<div class="d-flex align-items-center mt-2">
															<img src="${cart.image}" class="img-fluid rounded-circle"
																style="width: 80px; height: 80px" alt="">
														</div>
													</td>

													<td class="py-5">${cart.productName}</td>
													<td class="py-5">${cart.productPrice}$</td>
													<td class="py-5">${cart.quantity}</td>
													<td class="py-5">$ <fmt:formatNumber type="number"
															maxFractionDigits="2"
															value="${cart.quantity * cart.productPrice}" />
													</td>
												</tr>
												<c:set var="subTotal"
													value="${subTotal + (cart.quantity * cart.productPrice)}" />
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="6" class="text-center"><strong>Your
														cart is currently empty.</strong></td>
											</tr>
										</c:otherwise>
									</c:choose>
									<tr>
										<th scope="row"></th>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5">
											<p class="mb-0 text-dark py-3">Subtotal</p>
										</td>
										<td class="py-5">
											<div class="py-3 border-bottom border-top">
												<p id="subTotal" class="mb-0 text-dark">
													$
													<fmt:formatNumber type="number" maxFractionDigits="2"
														value="${subTotal}" />
												</p>
											</div>
										</td>
									</tr>
									<tr>
										<td class="py-5">
											<p class="mb-0 text-dark py-4">Shipping</p>
										</td>
										<td colspan="4" class="py-5">
											<div class="form-check text-start">
												<input type="radio"
													class="form-check-input bg-primary border-0"
													id="Shipping-1" name="shipping" value="0" required
													onchange="updateTotalPrice()" /> <label
													class="form-check-label" for="Shipping-1">Free
													Shipping</label>
											</div>
											<div class="form-check text-start">
												<input type="radio"
													class="form-check-input bg-primary border-0"
													id="Shipping-2" name="shipping" value="15" required
													onchange="updateTotalPrice()" /> <label
													class="form-check-label" for="Shipping-2">Flat
													rate: $15.00</label>
											</div>
											<div class="form-check text-start">
												<input type="radio"
													class="form-check-input bg-primary border-0"
													id="Shipping-3" name="shipping" value="8" required
													onchange="updateTotalPrice()" /> <label
													class="form-check-label" for="Shipping-3">Local
													Pickup: $8.00</label>
											</div>
										</td>
									</tr>
									<tr>
										<td class="py-5">
											<p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
										</td>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5"></td>
										<td class="py-5">
											<div class="py-3 border-bottom border-top">
												<p id="totalPrice" class="mb-0 text-dark">
													$
													<fmt:formatNumber type="number" maxFractionDigits="2"
														value="${subTotal}" />
												</p>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div
							class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
							<div class="col-12">
								<div class="form-check text-start my-3">
									<input type="radio"
										class="form-check-input bg-primary border-0" id="payment-1"
										name="payment" required value="cod" /> <label
										class="form-check-label" for="payment-1">Cash On
										Delivery</label>
								</div>
							</div>
						</div>
						<div
							class="row g-4 text-center align-items-center justify-content-center pt-4">
							<button type="submit"
								class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">
								Place Order</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- Checkout Page End -->

	<!-- Footer Start -->
	<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
		<div class="container py-5">
			<div class="pb-4 mb-4"
				style="border-bottom: 1px solid rgba(226, 175, 24, 0.5)">
				<div class="row g-4">
					<div class="col-lg-3">
						<a href="#">
							<h1 class="text-primary mb-0">Green Valley</h1>
							<p class="text-secondary mb-0">Fresh products</p>
						</a>
					</div>
					<div class="col-lg-6">
						<div class="position-relative mx-auto">
							<input class="form-control border-0 w-100 py-3 px-4 rounded-pill"
								type="number" placeholder="Your Email" />
							<button type="submit"
								class="btn btn-primary border-0 border-secondary py-3 px-4 position-absolute rounded-pill text-white"
								style="top: 0; right: 0">Subscribe Now</button>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="d-flex justify-content-end pt-3">
							<a
								class="btn btn-outline-secondary me-2 btn-md-square rounded-circle"
								href=""><i class="fab fa-twitter"></i></a> <a
								class="btn btn-outline-secondary me-2 btn-md-square rounded-circle"
								href=""><i class="fab fa-facebook-f"></i></a> <a
								class="btn btn-outline-secondary me-2 btn-md-square rounded-circle"
								href=""><i class="fab fa-youtube"></i></a> <a
								class="btn btn-outline-secondary btn-md-square rounded-circle"
								href=""><i class="fab fa-linkedin-in"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row g-5">
				<div class="col-lg-3 col-md-6">
					<div class="footer-item">
						<h4 class="text-light mb-3">Why People Like us!</h4>
						<p class="mb-4">typesetting, remaining essentially unchanged.
							It was popularised in the 1960s with the like Aldus PageMaker
							including of Lorem Ipsum.</p>
						<a href=""
							class="btn border-secondary py-2 px-4 rounded-pill text-primary">Read
							More</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="d-flex flex-column text-start footer-item">
						<h4 class="text-light mb-3">Shop Info</h4>
						<a class="btn-link" href="">About Us</a> <a class="btn-link"
							href="">Contact Us</a> <a class="btn-link" href="">Privacy
							Policy</a> <a class="btn-link" href="">Terms & Condition</a> <a
							class="btn-link" href="">Return Policy</a> <a class="btn-link"
							href="">FAQs & Help</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="d-flex flex-column text-start footer-item">
						<h4 class="text-light mb-3">Account</h4>
						<a class="btn-link" href="">My Account</a> <a class="btn-link"
							href="">Shop details</a> <a class="btn-link" href="">Shopping
							Cart</a> <a class="btn-link" href="">Wishlist</a> <a class="btn-link"
							href="">Order History</a> <a class="btn-link" href="">International
							Orders</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="footer-item">
						<h4 class="text-light mb-3">Contact</h4>
						<p>Address: 97 Man Thien Street, Thu Duc City</p>
						<p>Email: vuquocbao673@gmail.com</p>
						<p>Phone: +84964949942</p>
						<p>Payment Accepted</p>
						<img src="img/payment.png" class="img-fluid" alt="" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer End -->

	<!-- Copyright Start -->
	<div class="container-fluid copyright bg-dark py-4">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
					<span class="text-light"><a href="#"><i
							class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All
						right reserved.</span>
				</div>
				<div class="col-md-6 my-auto text-center text-md-end text-white">
					Designed By <a class="border-bottom"
						href="https://github.com/baoo2003">Group 15</a> Distributed By <a
						class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Copyright End -->

	<!-- Back to Top -->
	<a href="#"
		class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
		class="fa fa-arrow-up"></i></a>

	<!-- JavaScript Libraries -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/lightbox/js/lightbox.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="js/main.js"></script>

	<script>
		function updateTotalPrice() {
			var shippingOptions = document.getElementsByName('shipping');
			var shippingCost = 0;
			for (var i = 0; i < shippingOptions.length; i++) {
				if (shippingOptions[i].checked) {
					shippingCost = parseFloat(shippingOptions[i].value);
					break;
				}
			}
			var subTotal = parseFloat(document.getElementById('subTotal').innerText.slice(2));
			var total = subTotal + shippingCost;
			document.getElementById('totalPrice').innerText = '$'
					+ total.toFixed(2);
		}
	</script>
</body>

</html>
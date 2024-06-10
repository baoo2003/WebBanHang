
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Green Valley - Vegetable Website Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">


<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">

<style>
.error {
	color: red;
	font-style: italic;
}
</style>
</head>

<body>

	<!-- Spinner Start -->
	<div id="spinner"
		class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
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
				<a href="home.htm" class="navbar-brand"><h1
						class="text-primary display-6">Green Valley</h1></a>
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
							class="fa fa-shopping-bag fa-2x"></i> <!-- <span id="quantity-product" class=" position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span> -->
						</a>
						<c:if test="${not empty sessionScope.customerId}">
							<div class="position-relative me-4 my-auto">
								<a href="#" id="notification-btn"
									class="position-relative me-4 my-auto"> <i
									class="fa fa-bell fa-2x"></i> <c:set var="unreadCount"
										value="0" /> <c:forEach items="${notifications}"
										var="notification">
										<c:if test="${not notification.status}">
											<c:set var="unreadCount" value="${unreadCount + 1}" />
										</c:if>
									</c:forEach> <c:if test="${unreadCount > 0}">
										<span
											class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
											style="top: -5px; left: 15px; height: 20px; min-width: 20px;">${unreadCount}
										</span>
									</c:if>
								</a>
								<div class="notification" id="notification-box">
									<div class="notification-header"
										nstyle="display: flex; justify-content: space-between; align-items: center;">
										Notification <a
											href="${pageContext.request.contextPath}/readAllNotifi.htm"
											style="font-size: 14px; text-align: right;">Mark all as
											read</a>
									</div>
									<div class="notification-body">
										<c:forEach items="${notifications}" var="notification">
											<div class="notification-item"
												onclick="submitForm('formNotifi-${notification.id}')">
												<form id="formNotifi-${notification.id}"
													action="updateNotifi.htm" method="POST">
													<input type="hidden" name="id" value="${notification.id}">
													<input type="hidden" name="message"
														value="${notification.message}"> <input
														type="hidden" name="status" value="${notification.status}">
													<input type="hidden" name="createTime"
														value="${notification.createTime}"> <input
														type="hidden" name="url" value="${notification.url}">
													<a class="notification-link">
														<div class="notification-content">
															<p>${notification.message}</p>
															<span class="notification-time"
																style="font-size: 14px; color:${notification.status ? '#000' : '#81C408'}"><fmt:formatDate
																	value="${notification.createTime}"
																	pattern="HH:mm dd/MM/yyyy" /></span>
														</div> <c:if test="${not notification.status}">
															<span class="status-dot"
																style="flex-shrink:0;height: 10px; width: 10px; background-color: ${notification.status ? '#fff' : '#81C408'}; border-radius: 50%; display: inline-block;"></span>
														</c:if>
													</a>
												</form>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>

						</c:if>

						<div class=" nav-item dropdown">
							<a href="#" class="my-auto nav-link dropdown-toggle"
								data-bs-toggle="dropdown"> <i class="fas fa-user fa-2x"></i>
							</a>
							<div class="dropdown-menu m-0 bg-secondary rounded-0">
								<c:choose>
									<c:when test="${empty sessionScope.customerId}">
										<a href="login.htm" class="dropdown-item">Login</a>
										<a href="register.htm" class="dropdown-item">Register</a>
									</c:when>

									<c:otherwise>
										<a href="customer-profile.htm" class="dropdown-item">View
											profile</a>
										<a href="customer-order.htm" class="dropdown-item">View
											orders</a>
										<div class="dropdown-divider"></div>
										<a href="customer-change-password.htm" class="dropdown-item">Change
											password</a>
										<div class="dropdown-divider"></div>
										<form id="logout-form"
											action="${pageContext.request.contextPath}/logout.htm"
											method="post">
											<button type="submit" class="dropdown-item text-danger">Logout</button>
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
		<h1 class="text-center text-white display-6">Your Orders</h1>
	</div>
	<!-- Single Page Header End -->

	<c:if test="${not empty message}">
		<div class="alert alert-warning text-center">${message}</div>
	</c:if>

	<div class="container-fluid py-5">
		<div class="container py-5">
			<div class="d-flex flex-column">
				<span class="d-flex justify-content-center">
					<p style="color: red;">
						<c:if test="${not empty errorMessage}">${errorMessage} </c:if>
					</p>
				</span>
				<c:choose>
					<c:when test="${not empty orders}">
						<c:forEach var="order" items="${orders}">
							<div class="d-flex flex-column gap-3 mb-4"
								style="border-radius: 20px; border: 1px solid #f1f1f1; padding: 1rem;">
								<div class="d-flex justify-content-between">
									<p class="fs-5 fw-bolder">Order ID: ${order.id}</p>
									<p class="fs-5 fw-bolder">Order Status: ${order.status}</p>
								</div>
								<div class="d-flex flex-column">
									<c:choose>
										<c:when test="${not empty order.orderDetails}">
											<c:forEach var="orderDetail" items="${order.orderDetails}">
												<div class="d-flex gap-3 mb-3">
													<img src="${orderDetail.product.image}"
														class="img-fluid me-5 rounded-circle"
														style="width: 80px; height: 80px;" alt="" />
													<div class="d-flex flex-column align-items-start" style = "flex:1;">
														<p class='mb-0'>
															<strong>${orderDetail.product.name}</strong>
														</p>
														<p class='mb-0'>
															<strong>x ${orderDetail.quantity}</strong>
														</p>
														<p>
															$
															<fmt:formatNumber type="number" maxFractionDigits="2"
																value="${orderDetail.price}" />
														</p>
													</div>
														<p>
															$
															<fmt:formatNumber type="number" maxFractionDigits="2"
																value="${orderDetail.quantity * orderDetail.price}" />
														</p>
												</div>

											</c:forEach>
										</c:when>
									</c:choose>

								</div>
								<p style="border-top:1px solid #f1f1f1; padding-top: 12px; text-align:right;">
									Total: $
									<fmt:formatNumber type="number" maxFractionDigits="2"
										value="${order.totalPrice}" />
								</p>
								<c:if
									test="${order.status == 'Placed' or order.status == 'Delivered'}">
									<div class='d-flex justify-content-end align-items-center'>
										<form action="customer-order.htm" method="post"
											class='d-flex align-items-center gap-3'>
											<input type="hidden" name="orderId" value="${order.id }" />
											<input type="hidden" name="status"
												value="${order.status == 'Placed' ? 'Cancel' : 'Received' }" />

											<input
												type="${order.status == 'Delivered' ? 'hidden' : 'text' }"
												name="cancelReason" value="" placeholder="Cancel reason"
												style="border-radius: 20px; border: 1px solid #f1f1f1; padding: 0.5rem 1rem;" />
											<button
												class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase"
												type="submit"
												onclick="return confirm('Are you sure to change status?')">${order.status == 'Placed' ? 'Cancel' : 'Received'}</button>
										</form>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<h4>
							<strong>Your orders is currently empty.</strong>
						</h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>


	<!-- Contact Start -->
	<div class="container-fluid contact py-5">
		<div class="container py-5"></div>
	</div>
	<!-- Contact End -->


	<!-- Footer Start -->
	<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
		<div class="container py-5">
			<div class="pb-4 mb-4"
				style="border-bottom: 1px solid rgba(226, 175, 24, 0.5);">
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
								type="number" placeholder="Your Email">
							<button type="submit"
								class="btn btn-primary border-0 border-secondary py-3 px-4 position-absolute rounded-pill text-white"
								style="top: 0; right: 0;">Subscribe Now</button>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="d-flex justify-content-end pt-3">
							<a
								class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle"
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
						<img src="img/payment.png" class="img-fluid" alt="">
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
		function submitForm(formId) {
			document.getElementById(formId).submit();
		}
		document.getElementById('notification-btn').addEventListener(
				'click',
				function(event) {
					event.preventDefault();
					document.getElementById('notification-box').classList
							.toggle('active');
				});
		window.addEventListener('click', function(event) {
			if (!event.target.closest('#notification-btn')
					&& !event.target.closest('#notification-box')) {
				document.getElementById('notification-box').classList
						.remove('active');
			}
		});
	</script>
</body>

</html>
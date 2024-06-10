<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Order ${order.id}</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/logos/favicon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.min.css" />" />
    <base href="${pageContext.servletContext.contextPath}/">
    
    <style>
		.error {
			color: red;
			font-style: italic;
		}
	</style>
</head>
<body>

<!--  Body Wrapper -->
<div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed" data-header-position="fixed">
    <!-- Sidebar Start -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div>
            <div class="brand-logo d-flex align-items-center justify-content-between">
                <a href="${pageContext.request.contextPath}/admin.htm" class="text-nowrap logo-img">
                    <h2 class="">Green Valley</h2>
                </a>
                <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                    <i class="ti ti-x fs-8"></i>
                </div>
            </div>
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
                <ul id="sidebarnav">
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/admin.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-layout-dashboard"></i>
                            </span>
                            <span class="hide-menu">Dashboard</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/manage-brand.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Brand</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/manage-category.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Category</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/manage-product.htm?page=1" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Product</span>
                        </a>
                    </li>                    
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/manage-order.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Order</span>
                        </a>
                    </li>
                    
                    <div class="sidebar-item" style="position: absolute; bottom: 10px">
                    	<li class="sidebar-item">
	                        <a class="sidebar-link" href="${pageContext.request.contextPath}/change-password.htm" aria-expanded="false">
	                            <span>
	                                <i class="ti ti-alert-circle"></i>
	                            </span>
	                            <span class="hide-menu">Change password</span>
	                        </a>
	                    </li>
	                    <li class="sidebar-item">
	                    	<form action="${pageContext.request.contextPath}/admin-logout.htm" method="post">
	                    		<button class="sidebar-link btn" aria-expanded="false">
		                            <span>
		                                <i class="ti ti-login"></i>
		                            </span>
		                            <span class="hide-menu">Logout</span>
		                        </button>
	                    	</form>
	                    </li>
                    </div>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!--  Sidebar End -->

    <!--  Main wrapper -->
    <div class="body-wrapper">
        <!--  Header Start -->
        <header class="app-header">
            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
                    <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
                        <li class="nav-item dropdown">
                            <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                <img src="<c:url value="/resources/images/profile/user-1.jpg" />" alt="" width="35" height="35" class="rounded-circle">
                            </a>
                            <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                                <div class="message-body">
                                    <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                        <i class="ti ti-user fs-6"></i>
                                        <p class="mb-0 fs-3">My Profile</p>
                                    </a>
                                    <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                        <i class="ti ti-mail fs-6"></i>
                                        <p class="mb-0 fs-3">My Account</p>
                                    </a>
                                    <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                                        <i class="ti ti-list-check fs-6"></i>
                                        <p class="mb-0 fs-3">My Task</p>
                                    </a>
                                    <a href="" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!--  Header End -->

        <div class="container-fluid">
            <div class="position-relative overflow-hidden radial-gradient min-vh-100 d-flex align-items-center justify-content-center">
                <div class="d-flex align-items-center justify-content-center w-100">
                    <div class="row justify-content-center w-100">
                        <div class="col-md-8 col-lg-6 col-xxl-3">
                            <div class="card mb-0">
                                <div class="card-body">
                                    <h5 class="text-center">Order Detail</h5>
                                    <div class="mb-4 d-flex justify-content-between">
                                        <label class="form-label">ID: ${order.id}</label>
                                        <c:choose>
                                        	<c:when test="${order.status == 'Canceled'}">
                                        		<span class="form-label text-danger">Status: ${order.status}</span>
                                        	</c:when>
                                        	<c:otherwise>
                                        		<span class="form-label text-primary">Status: ${order.status}</span>
                                        	</c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label">Full name: ${order.fullname}</label>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label">Phone number: ${order.phoneNumber}</label>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label">Address: ${order.address}</label>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label">
                                        	Order time: 
                                        	<fmt:formatDate value="${order.orderTime}" pattern="dd-MM-yyyy hh:ss"/>
										</label>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label">
                                        	Delivery time: 
                                        	<fmt:formatDate value="${order.deliveryTime}" pattern="dd-MM-yyyy hh:ss"/>
										</label>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label">Products:</label>
                                        <ul>
                                        	<c:forEach items="${orderDetails}" var="detail">
                                        		<li>
                                        			${detail.product.id} - ${detail.product.name} - $${detail.price} x ${detail.quantity}
                                        		</li>
                                        	</c:forEach>
                                        </ul>
                                    </div>
                                    <c:if test="${order.cancelReason != ''}">
                                    	<div class="mb-4">
	                                        <label class="form-label">Cancel reason: ${order.cancelReason}</label>
	                                    </div>
                                    </c:if>
                                    <c:if test="${order.status == 'Delivered'}">
                                    	<div class="d-flex">
		                                    <a href="manage-bill.htm?id=${order.id}" class="btn btn-primary mx-auto py-8 fs-4 mb-4 mx-auto rounded-2">
		                                    	See bill
		                                    </a>
	                                    </div>
                                    </c:if>                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/libs/jquery/dist/jquery.min.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap/dist/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/resources/js/sidebarmenu.js" />"></script>
<script src="<c:url value="/resources/js/app.min.js" />"></script>
<script src="<c:url value="/resources/libs/apexcharts/dist/apexcharts.min.js" />"></script>
<script src="<c:url value="/resources/libs/simplebar/dist/simplebar.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>
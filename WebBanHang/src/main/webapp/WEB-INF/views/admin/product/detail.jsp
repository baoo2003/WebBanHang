<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Product ${product.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/logos/favicon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.min.css" />" />
    
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
                        <a class="sidebar-link activate" href="${pageContext.request.contextPath}/manage-product.htm?page=1" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Product</span>
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
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/manage-order.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Order</span>
                        </a>
                    </li>
                    
                    <div class="sidebar-item" style="position: absolute; bottom: 10px">
                    	<li class="sidebar-item">
	                    	<form action="${pageContext.request.contextPath}/logout.htm" >
	                    	
	                    	</form>
	                        <a class="sidebar-link" href="${pageContext.request.contextPath}/change-password.htm" aria-expanded="false">
	                            <span>
	                                <i class="ti ti-alert-circle"></i>
	                            </span>
	                            <span class="hide-menu">Change password</span>
	                        </a>
	                    </li>
	                    <li class="sidebar-item">
	                    	<form action="${pageContext.request.contextPath}/logout.htm" method="post">
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
	                <a href="manage-product.htm?page=1">
	                	<span style="font-size: 20px;">&#x2190;</span>
	                	Back
	                </a>
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
                                    <a href="./authentication-login.html" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <!--  Header End -->
        
        <div class="container-fluid">
        	<div class="card-body p-4">
        		<div class="d-flex justify-content-center">
        			<h3>Detail of ${product.name}</h3>
        		</div>
        		<div class="d-flex justify-content-center">
        			<span class="error">${message}</span>
        		</div>
        		<div class="row g-4">
        			<div class="col-lg-12">
                        <div class="row g-4">
                            <div class="col-lg-4">
                                <div class="border rounded">
                                    <a href="#">
                                        <img src="${product.imagePath}" class="img-fluid rounded" alt="Image">
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <form:form action="update-product.htm?id=${param.id}" method="post" modelAttribute="product" enctype="multipart/form-data">
                                	<div class="mb-3">
					                    <form:label path="name" for="productName" class="form-label">Name</form:label>
					                    <form:input path="name" class="form-control" id="productName" />
					                    <form:errors path="name" cssClass="error" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="brandId" for="productBrand" class="form-label">Brand</form:label>
					                    <form:select path="brandId" class="form-control" id="productBrand" items="${brands}" itemLabel="name" itemValue="id"/>
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="categoryId" for="productCategory" class="form-label">Category</form:label>
					                    <form:select path="categoryId" class="form-control" id="productCategory" items="${categories}" itemLabel="name" itemValue="id" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="description" for="productDescription" class="form-label">Description</form:label>
					                    <form:textarea path="description" class="form-control" id="productDescription" />
					                    <form:errors path="description" cssClass="error" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="origin" for="productOrigin" class="form-label">Origin</form:label>
					                    <form:input path="origin" class="form-control" id="productOrigin" />
					                    <form:errors path="origin" cssClass="error" />
				                    </div>
				                    
				                    <form:hidden path="imagePath" />
				                    
				                    <div class="mb-3">
					                    <form:label path="image" for="productImage" class="form-label">New image</form:label>
					                    <form:input path="image" type="file" class="form-control" id="productImage" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="unit" for="productUnit" class="form-label">Unit</form:label>
					                    <form:input path="unit" class="form-control" id="productUnit" />
					                    <form:errors path="unit" cssClass="error" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="quantity" for="productQuantity" class="form-label">Quantity</form:label>
					                    <form:input path="quantity" type="number" class="form-control" id="productQuantity" />
					                    <form:errors path="quantity" cssClass="error" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="price" for="productPrice" class="form-label">Price</form:label>
					                    <form:input path="price" type="number" class="form-control" id="productPrice" />
					                    <form:errors path="price" cssClass="error" />
				                    </div>
				                    
				                    <div class="mb-3">
					                    <form:label path="discount" for="productDiscount" class="form-label">Discount</form:label>
					                    <form:input path="discount" type="number" class="form-control" id="productDiscount" />
					                    <form:errors path="discount" cssClass="error" />
				                    </div>
				                    
				                    <div class="d-flex">
				                    	<button type="submit" class="btn btn-primary mx-auto py-8 fs-4 mb-4 rounded-2">
					                    	Update
					                    </button>
				                    </div>
                                </form:form>
                                <form:form action="delete-product.htm?id=${param.id}" method="post" modelAttribute="product">
                                    <div class="d-flex">
				                    	<button type="submit" onclick="return confirm('Are you sure to delete this product?')" class="btn btn-danger mx-auto py-8 fs-4 mb-4 rounded-2">
	                                        Delete
	                                    </button>
				                    </div>
                                </form:form>
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
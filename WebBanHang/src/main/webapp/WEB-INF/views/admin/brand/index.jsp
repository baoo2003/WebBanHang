<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Brands</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/logos/favicon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.min.css" />" />
    <base href="${pageContext.servletContext.contextPath}/">
    
    <style>
		.error {
			color: red;
			font-style: italic;
		}
	</style>
	
	<script type="text/javascript">
		function appendParam(paramName, paramValue) {
		    var currentUrl = window.location.href;
		    var newUrl;

		    if (paramValue === '') {
		        // Remove the parameter from the URL if paramValue is empty
		        var regex = new RegExp("([&?])" + paramName + "=([^&]*)", "i");
		        newUrl = currentUrl.replace(regex, function(match, p1) {
		            // If the match is preceded by ?, change it to ? otherwise remove &
		            return p1 === '?' ? '?' : '';
		        });
		        // Clean up any trailing '?' or '&'
		        newUrl = newUrl.replace(/(\?|&)$/, '');
		        // Remove '&' if it directly follows '?'
		        newUrl = newUrl.replace('?&', '?');
		    } else {
		        if (currentUrl.includes(paramName + "=")) {
		            var regex = new RegExp(paramName + "=([^&]*)", "i");
		            newUrl = currentUrl.replace(regex, paramName + "=" + paramValue);
		        } else {
		            if (currentUrl.indexOf('?') > -1)
		                newUrl = currentUrl + "&" + paramName + "=" + paramValue;
		            else
		                newUrl = currentUrl + '?' + paramName + "=" + paramValue;
		        }
		    }
		    
		    window.location.href = newUrl;
		}
		</script>
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
            <div class="card-body p-4">
                <div class="row justify-content-around">
                    <h5 class="col align-items-start card-title fw-semibold mb-4">All Brands</h5>
                    <div class="row justify-content-between mb-2">
        			<form action="manage-brand.htm" class="d-flex w-auto gap-1">
        				<input name="filter" value="${filter}" class="form-control w-auto" placeholder="Enter keyword"/>   			
        				<button type="submit" class="btn btn-outline-primary">
        					Search
        				</button>        				
        			</form>                    
                    <a class=" col-1 m-1 align-items-end btn btn-primary" href="manage-brand-create.htm">
                        Create
                    </a>
                	</div>
                </div>
                <div class="d-flex justify-content-center">
        			<span class="error">${message}</span>
        		</div>
                <div class="table-responsive">
                    <table class="table text-nowrap mb-0 align-middle">
                        <thead class="text-dark fs-4">
                            <tr>
                                <th class="border-bottom-0 w-25">
                                    <h6 class="fw-semibold mb-0">Id</h6>
                                </th>
                                <th class="border-bottom-0 w-25">
                                    <h6 class="fw-semibold mb-0">Name</h6>
                                </th>                                
                                <th class="border-bottom-0 w-25">
                                    <h6 class="fw-semibold mb-0 text-center">Action</h6>
                                </th>
                            </tr>
                        </thead>
                        <tbody>                       	                           
                            <c:forEach var="brand" items="${brands}">
                                <tr
                                        onmouseover="this.style.backgroundColor='rgba(93, 135, 255, 0.1)'; this.style.color='#5D87FF'"
                                        onmouseout="this.style.backgroundColor=''; this.style.color=''"
                                        ondblclick="navigateTo('manage-brand-update.htm?brandId=${brand.id}')"
                                >
                                    <td class="border-bottom-0">
                                        <h6 class="fw-semibold mb-0">${brand.id}</h6>
                                    </td>
                                    <td class="border-bottom-0">
                                        <p class="mb-0 fw-normal">${brand.name}</p>
                                    </td>                                                                      
                                    <td class="border-bottom-0 d-flex justify-content-center align-items-center">
                                    	<a href="manage-brand-update.htm?brandId=${brand.id}" class="btn btn-secondary me-2">
                                            Update
                                        </a> 
                                    	<form action="manage-brand-delete.htm?brandId=${brand.id}" method="post" class="mb-0">
		                                	<button type="submit" onclick="return confirm('Are you sure to delete this brand?')" class="btn btn-secondary">
		                                        Delete
		                                    </button>
		                                </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function navigateTo(url) {
        window.location.href = url;
    }
</script>

<script src="<c:url value="/resources/libs/jquery/dist/jquery.min.js" />"></script>
<script src="<c:url value="/resources/libs/bootstrap/dist/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/resources/js/sidebarmenu.js" />"></script>
<script src="<c:url value="/resources/js/app.min.js" />"></script>
<script src="<c:url value="/resources/libs/apexcharts/dist/apexcharts.min.js" />"></script>
<script src="<c:url value="/resources/libs/simplebar/dist/simplebar.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>
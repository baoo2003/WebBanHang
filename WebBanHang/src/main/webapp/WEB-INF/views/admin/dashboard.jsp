<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="<c:url value="/resources/images/logos/favicon.png" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.min.css" />" />
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
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/products.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Brand</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/categories.htm" aria-expanded="false">
                            <span>
                                <i class="ti ti-article"></i>
                            </span>
                            <span class="hide-menu">Category</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/orders.htm" aria-expanded="false">
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
	                        <a class="sidebar-link" href="${pageContext.request.contextPath}/admin/change-password.htm" aria-expanded="false">
	                            <span>
	                                <i class="ti ti-alert-circle"></i>
	                            </span>
	                            <span class="hide-menu">Change password</span>
	                        </a>
	                    </li>
	                    <li class="sidebar-item">
	                    	<form action="${pageContext.request.contextPath}/logout.htm" >
	                    	
	                    	</form>
	                        <a class="sidebar-link" href="${pageContext.request.contextPath}/orders.htm" aria-expanded="false">
	                            <span>
	                                <i class="ti ti-login"></i>
	                            </span>
	                            <span class="hide-menu">Logout</span>
	                        </a>
	                    </li>
                    </div>
                    
<!--                     <li class="sidebar-item" style="position: absolute; bottom: 10px"> -->
<%--                     	<form action="${pageContext.request.contextPath}/logout.htm" > --%>
                    	
<!--                     	</form> -->
<%--                         <a class="sidebar-link" href="${pageContext.request.contextPath}/admin/change-password.htm" aria-expanded="false"> --%>
<!--                             <span> -->
<!--                                 <i class="ti ti-login"></i> -->
<!--                             </span> -->
<!--                             <span class="hide-menu">Change password</span> -->
<!--                         </a> -->
<!--                     </li> -->
<!--                     <li class="sidebar-item" style="position: absolute; bottom: 10px"> -->
<%--                     	<form action="${pageContext.request.contextPath}/logout.htm" > --%>
                    	
<!--                     	</form> -->
<%--                         <a class="sidebar-link" href="${pageContext.request.contextPath}/orders.htm" aria-expanded="false"> --%>
<!--                             <span> -->
<!--                                 <i class="ti ti-login"></i> -->
<!--                             </span> -->
<!--                             <span class="hide-menu">Logout</span> -->
<!--                         </a> -->
<!--                     </li> -->
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
            <!--  Row 1 -->
            <div class="row">
                <div class="col-lg-8 d-flex align-items-strech">
                    <div class="card w-100">
                        <div class="card-body">
                            <div class="d-sm-flex d-block align-items-center justify-content-between mb-9">
                                <div class="mb-3 mb-sm-0">
                                    <h5 class="card-title fw-semibold">Sales Overview</h5>
                                </div>
                            </div>
                            <div id="chart"></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="row">
                        <div class="col-lg-12">
                            <!-- Yearly Breakup -->
                            <div class="card overflow-hidden">
                                <div class="card-body p-4">
                                    <h5 class="card-title mb-9 fw-semibold">Yearly Breakup</h5>
                                    <div class="row align-items-center">
                                        <div class="col-8">
                                            <h4 class="fw-semibold mb-3">$ ${yearBreakDto.currentYearTotal}</h4>
                                            <div class="d-flex align-items-center mb-3">
                                               
                                               <c:choose>
                                            	<c:when test="${yearBreakDto.currentYearTotal < yearBreakDto.previousYearToTal}">
                                            		<span class="me-2 rounded-circle bg-light-danger round-20 d-flex align-items-center justify-content-center">
	                                                    <i class="ti ti-arrow-down-right text-danger"></i>
	                                                </span>
	                                                <p class="text-dark me-1 fs-3 mb-0">- ${yearBreakDto.percentageChange}</p>
	                                                <p class="fs-3 mb-0">last year</p>
                                            	</c:when>
                                            	<c:otherwise>
                                            		<c:if test="${yearBreakDto.previousYearToTal != 0.0}">
                                            		<span class="me-1 rounded-circle bg-light-success round-20 d-flex align-items-center justify-content-center">
                                                    <i class="ti ti-arrow-up-left text-success"></i>
                                               			 </span>
	                                                <p class="text-dark me-1 fs-3 mb-0">+ ${yearBreakDto.percentageChange}</p>
	                                                <p class="fs-3 mb-0">last year</p>
                                            		</c:if>
                                            		
                                            		<c:if test="${yearBreakDto.previousYearToTal == 0.0}">
                                           
	                                                <p class="fs-3 mb-0">Last year's data is not available</p>
                                            		</c:if>
                                          
                                            	</c:otherwise>
                                            </c:choose>
                                               
                                               
                                               
                                               
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <div class="me-4">
                                                    <span class="round-8 bg-primary rounded-circle me-2 d-inline-block"></span>
                                                    <span class="fs-2">${yearBreakDto.currentYear}</span>
                                                </div>
                                                <div>
                                                    <span class="round-8 bg-light-primary rounded-circle me-2 d-inline-block"></span>
                                                    <span class="fs-2">${yearBreakDto.currentYear - 1}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="d-flex justify-content-center">
                                                <div id="breakup"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <!-- Monthly Earnings -->
                            <div class="card">
                                <div class="card-body">
                                    <div class="row alig n-items-start">
                                        <div class="col-8">
                                            <h5 class="card-title mb-9 fw-semibold"> Monthly Earnings </h5>
                                            <h4 class="fw-semibold mb-3">$ ${salesOverviewDTOs.get(5).total}</h4>
                                            <div class="d-flex align-items-center pb-1">
                                            
                                            <c:choose>
                                            	<c:when test="${salesOverviewDTOs.get(5).total < salesOverviewDTOs.get(4).total}">
                                            		<span class="me-2 rounded-circle bg-light-danger round-20 d-flex align-items-center justify-content-center">
	                                                    <i class="ti ti-arrow-down-right text-danger"></i>
	                                                </span>
	                                                <p class="text-dark me-1 fs-3 mb-0">-  ${pChange}</p>
	                                                <p class="fs-3 mb-0">last month</p>
                                            	</c:when>
                                            	<c:otherwise>
                                            		<c:if test="${salesOverviewDTOs.get(4).total != 0.0}">
                                            		<span class="me-1 rounded-circle bg-light-success round-20 d-flex align-items-center justify-content-center">
                                                    <i class="ti ti-arrow-up-left text-success"></i>
                                               			 </span>
	                                                <p class="text-dark me-1 fs-3 mb-0">+${pChange}</p>
	                                                <p class="fs-3 mb-0">last month</p>
                                            		</c:if>
                                            		
                                            		<c:if test="${salesOverviewDTOs.get(4).total == 0.0}">
                                           
	                                                <p class="fs-3 mb-0">Last month's data is not available</p>
                                            		</c:if>
                                          
                                            	</c:otherwise>
                                            </c:choose>
                                            
                                            	
                                            	
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="d-flex justify-content-end">
                                                <div
                                                        class="text-white bg-secondary rounded-circle p-6 d-flex align-items-center justify-content-center">
                                                    <i class="ti ti-currency-dollar fs-6"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="earning"></div>
                            </div>
                        </div>
                        
                        <p>Prodct: ${totalProduct}</p>
                        <p>Customer: ${totalCustomer}</p>
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

<script>
    var salesData = [];
    var categories = [];
	var maxTotal = ${maxTotal+20};
	<c:forEach var="dto" items="${salesOverviewDTOs}">
    salesData.push(${dto.total});
    categories.push('${dto.month}/${dto.year}');
    
	</c:forEach>;
	
	var currentYear =${yearBreakDto.currentYear};
	var currentYearTotal =${yearBreakDto.currentYearTotal};
	var previousYearToTal =${yearBreakDto.previousYearToTal};
	

    </script>


</body>
</html>
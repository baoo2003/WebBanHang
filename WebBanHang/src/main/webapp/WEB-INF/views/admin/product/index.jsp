<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Product</title>
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
                        <a class="sidebar-link" href="${pageContext.request.contextPath}/manage-product.htm?page=1" aria-expanded="false">
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
        		<h5 class="col align-items-start card-title fw-semibold mb-4">
                   	All Products
                </h5>
        		<div class="row justify-content-between mb-2">
        			<form:form action="manage-product.htm?page=1" method="post" modelAttribute="filter" class="d-flex w-auto gap-1">
        				<form:input path="keyword" class="form-control w-auto" placeholder="Enter keyword"/>
        				<form:select path="categoryId" cssClass="form-control w-auto">
        					<form:option value="" label="All" />
        					<form:options items="${categories}" itemValue="id" itemLabel="name" />
        				</form:select>
        				<form:select path="priceGroupId" cssClass="form-control w-auto">
        					<form:option value="0" label="All" />
        					<form:option value="1" label="Under $10" />
        					<form:option value="2" label="$10 - $50" />
        					<form:option value="3" label="$50 - $100" />
        					<form:option value="4" label="Over $100" />
        					<form:option value="5" label="Discount" />
        				</form:select>
        				<button type="submit" class="btn btn-outline-primary">
        					Search
        				</button>
        			</form:form>
                    <a class=" col-1 m-1 align-items-end btn btn-primary" href="./brands/form">
                        Create
                    </a>
                </div>
        		<div class="row">
        			<c:choose>
        				<c:when test="${empty products}">
        					<div class="d-flex justify-content-center">
        						<h3>There is no product that matches with your request!</h3>
        					</div>
        				</c:when>
        				
        				<c:otherwise>
        					<c:forEach var="product" items="${products}">
		        				<div class="col-sm-6 col-xl-3">
				                    <div class="card overflow-hidden rounded-2 border border-primary" onclick="window.location.href='${pageContext.request.contextPath}/admin.htm';" style="cursor: pointer">
				                        <div class="position-relative">
				                            <img src="${product.image}" class="card-img-top rounded-0" alt="Image ${product.name}">
										</div>
				                        <div class="card-body pt-3 p-4">
				                            <h6 class="fw-semibold fs-4">${product.name}</h6>
				                            <div class="d-flex align-items-center justify-content-between">
				                                <h6 class="fw-semibold fs-4 mb-0">
				                                	<c:if test="${product.discount == 0}">
				                                		$${product.price} / ${product.unit}
				                                	</c:if>
				                                	<c:if test="${product.discount != 0}">
				                                		$${product.price*(1-(product.discount/100.0))} / ${product.unit}
				                                		<span class="ms-2 fw-normal text-muted fs-3">
					                                		<del>${product.price} / ${product.unit}</del>
					                                	</span>
				                                	</c:if>
				                                </h6>
				                            </div>
				                        </div>
				                    </div>
				                </div>
		        			</c:forEach>
        				</c:otherwise>
        			</c:choose>
	            </div>
	            <nav aria-label="Page navigation">
	            	<ul class="pagination d-flex justify-content-center">
		            	<c:if test="${numOfPages > 2}">
		            		<li class="page-item">
			            		<c:choose>
			            			<c:when test="${param.page == 1}">
			            				<a class="page-link border border-primary" aria-label="First" style="cursor: pointer">
								        	<span class="sr-only">First</span>
								        </a>
			            			</c:when>
			            			
			            			<c:otherwise>
			            				<a class="page-link border border-primary" href="${pageContext.request.contextPath}/manage-product.htm?page=1" aria-label="First">
								        	<span class="sr-only">First</span>
								        </a>
			            			</c:otherwise>
			            		</c:choose>
						    </li>
		            	</c:if>
		            	
		            	<c:choose>
		            		<c:when test="${numOfPages <= 10}">
		            			<c:forEach begin="1" end="${numOfPages}" varStatus="status">
			            			<c:choose>
			            				<c:when test="${param.page == status.index}">
			            					<li class="page-item active">
						            			<a class="page-link" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:when>
			            				
			            				<c:otherwise>
			            					<li class="page-item">
						            			<a class="page-link border border-primary" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:otherwise>
			            			</c:choose>
			                	</c:forEach>
		            		</c:when>
		            	
		            		<c:when test="${numOfPages > 10 and param.page <= 4}">
		            			<c:forEach begin="1" end="5" varStatus="status">
			            			<c:choose>
			            				<c:when test="${param.page == status.index}">
			            					<li class="page-item active">
						            			<a class="page-link" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:when>
			            				
			            				<c:otherwise>
			            					<li class="page-item">
						            			<a class="page-link border border-primary" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:otherwise>
			            			</c:choose>
			                	</c:forEach>
			                	<li class="page-item">
			            			<a class="page-link border border-primary">
		 	                			...
		 	                		</a>
			            		</li>
		            		</c:when>
		            		
		            		<c:when test="${numOfPages > 10 and param.page > numOfPages - 4}">
			                	<li class="page-item">
			            			<a class="page-link border border-primary">
		 	                			...
		 	                		</a>
			            		</li>
		            			<c:forEach begin="${numOfPages - 4}" end="${numOfPages}" varStatus="status">
			            			<c:choose>
			            				<c:when test="${param.page == status.index}">
			            					<li class="page-item active">
						            			<a class="page-link" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:when>
			            				
			            				<c:otherwise>
			            					<li class="page-item">
						            			<a class="page-link border border-primary" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:otherwise>
			            			</c:choose>
			                	</c:forEach>
		            		</c:when>
		            		
		            		<c:when test="${numOfPages > 10 and param.page > 4 and param.page <= numOfPages - 4}">
		            			<li class="page-item">
			            			<a class="page-link border border-primary">
		 	                			...
		 	                		</a>
			            		</li>
		            			<c:forEach begin="${param.page - 2}" end="${param.page + 2}" varStatus="status">
			            			<c:choose>
			            				<c:when test="${param.page == status.index}">
			            					<li class="page-item active">
						            			<a class="page-link" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:when>
			            				
			            				<c:otherwise>
			            					<li class="page-item">
						            			<a class="page-link border border-primary" href="${pageContext.request.contextPath}/manage-product.htm?page=${status.index}">
					 	                			${status.index}
					 	                		</a>
						            		</li>
			            				</c:otherwise>
			            			</c:choose>
			                	</c:forEach>
			                	<li class="page-item">
			            			<a class="page-link border border-primary">
		 	                			...
		 	                		</a>
			            		</li>
		            		</c:when>
		            	</c:choose>
	            		
	                	<c:if test="${numOfPages > 2}">
	                		<li class="page-item">
		                		<c:choose>
			            			<c:when test="${param.page == numOfPages}">
			            				<a class="page-link border border-primary" aria-label="Last" style="cursor: pointer">
								        	<span class="sr-only">Last</span>
								        </a>
			            			</c:when>
			            			
			            			<c:otherwise>
			            				<a class="page-link border border-primary" href="${pageContext.request.contextPath}/manage-product.htm?page=${numOfPages}" aria-label="Last">
								        	<span class="sr-only">Last</span>
								        </a>
			            			</c:otherwise>
			            		</c:choose>
						    </li>
	                	</c:if>
	            	</ul>
	            </nav>
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
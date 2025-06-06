<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        
        <link href="css/bootstrap.min.css" rel="stylesheet">
        
		<link href="css/style.css" rel="stylesheet">
		
		<style>
.error {
	color: red;
	font-style: italic;
}
.button-chatbot{
    position: fixed;
    right: 30px;
    bottom: 30px;
    display: flex;
    width: 45px;
    height: 45px;
    align-items: center;
    justify-content: center;
    transition: 0.5s;
    z-index: 99;
}

.chatbox {
    width: 350px;
    height: 500px;
    position: fixed;
    bottom: 80px;
    right: 80px;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.chatbox-header {
    background-color: #81C408;
    color: #fff;
    padding: 10px;
    text-align: center;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    font-weight: bold;
}

.chatbox-messages {
    height: 390px;
    flex: 1;
    padding: 10px;
    overflow-y: auto;
    background-color: #fff;
}

.message {
    margin: 5px 0;
}

.message.user {
    text-align: right;
    padding-right: 12px;
}

.message.ai {
    text-align: left;
}

.message .content {
    display: inline-block;
    padding: 10px;
    border-radius: 15px;
    max-width: 70%;
}

.message.user .content {
    background-color: #81C408;
    text-align: left;
    color: #fff;
}

.message.ai .content {
    background-color: #e6e6e6;
    color: #333;
}

.chatbox-input {
display: flex;
padding: 10px;
border-top: 1px solid #ddd;
position: absolute; /* Added to fix the position */
bottom: 0; /* Aligns the input area to the bottom */
left: 0; /* Aligns the input area to the left */
right: 0; /* Aligns the input area to the right */
}

.chatbox-input input {
    flex: 1;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 15px;
    outline: none;
}

.chatbox-input button {
    margin-left: 10px;
    padding: 10px 15px;
    border: none;
    background-color: #81C408;
    color: #fff;
    border-radius: 15px;
    cursor: pointer;
}

.chatbox-input button:hover {
    background-color: #81C408;
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
        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">97 Man Thien Street, Thu Duc City</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">vuquocbao673@gmail.com</a></small>
                    </div>
                    <div class="top-link pe-2">
                        <a href="#" class="text-white"><small class="text-white mx-2">Privacy Policy</small>/</a>
                        <a href="#" class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
                        <a href="#" class="text-white"><small class="text-white ms-2">Sales and Refunds</small></a>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="home.htm" class="navbar-brand"><h1 class="text-primary display-6">Green Valley</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">                            
                            <a href="home.htm" class="nav-item nav-link active">Home</a>
                            <a href="cart.htm" class="nav-item nav-link">Cart</a>
                            <a href="contact.htm" class="nav-item nav-link">Contact</a>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <!-- <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button> -->
                            <a href="cart.htm" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                                <!-- <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span> -->
                            </a>
                            
                            <c:if test = "${not empty sessionScope.customerId}">
                            <div class="position-relative me-4 my-auto">
								<a href="#" id="notification-btn" class="position-relative me-4 my-auto"> 
									<i class="fa fa-bell fa-2x"></i> 
									<c:set var="unreadCount" value="0" />
										<c:forEach items="${notifications}" var="notification">
											<c:if test="${not notification.status}">
												<c:set var="unreadCount" value="${unreadCount + 1}" />
											</c:if>
										</c:forEach>
										<c:if test="${unreadCount > 0}">
											<span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">${unreadCount} </span>
									</c:if>
								</a>
								<div class="notification" id="notification-box">
									<div class="notification-header"nstyle="display: flex; justify-content: space-between; align-items: center;">Notification 
										<a href="${pageContext.request.contextPath}/readAllNotifi.htm" style="font-size: 14px; text-align: right;">Mark all as read</a>
								</div>
								<div class="notification-body">
									<c:forEach items="${notifications}" var="notification">
										<div class="notification-item" onclick="submitForm('formNotifi-${notification.id}')">
											<form id="formNotifi-${notification.id}" action="updateNotifi.htm" method="POST">
												<input type="hidden" name="id" value="${notification.id}">
												<input type="hidden" name="message" value="${notification.message}"> 
												<input type="hidden" name="status" value="${notification.status}">
												<input type="hidden" name="createTime" value="${notification.createTime}"> 
												<input type="hidden" name="url" value="${notification.url}">
												<a class="notification-link">
													<div class="notification-content">
														<p>${notification.message}</p>
														<span class="notification-time" style="font-size: 14px; color:${notification.status ? '#000' : '#81C408'}"><fmt:formatDate value="${notification.createTime}" pattern="HH:mm dd/MM/yyyy" /></span>
													</div> 
													<c:if test="${not notification.status}">
														<span class="status-dot" style="flex-shrink:0;height: 10px; width: 10px; background-color: ${notification.status ? '#fff' : '#81C408'}; border-radius: 50%; display: inline-block;"></span>
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
                            	<a href="#" class="my-auto nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                	<i class="fas fa-user fa-2x"></i>
                            	</a>
                            	<div class="dropdown-menu m-0 bg-secondary rounded-0">
                                	<c:choose>
                        				<c:when test="${empty sessionScope.customerId}">
                        					<a href="login.htm" class="dropdown-item">Login</a>
		                                	<a href="register.htm" class="dropdown-item">Register</a>
		                       			</c:when>
                       			
		                       			<c:otherwise>
                                            			                            	<a href="customer-profile.htm" class="dropdown-item">View profile</a>
			                            	<a href="customer-order.htm" class="dropdown-item">View orders</a>
			                            	<div class="dropdown-divider"></div>
			                            	<a href="customer-change-password.htm" class="dropdown-item">Change password</a>
											<div class="dropdown-divider"></div>
											<form id="logout-form" action="${pageContext.request.contextPath}/logout.htm" method="post">
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


        <!-- Modal Search Start -->
        <!-- <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body d-flex align-items-center">
                        <div class="input-group w-75 mx-auto d-flex">
                            <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                            <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Modal Search End -->


        <!-- Single Page Header start -->
        <div class="container-fluid page-header py-5">
            <h1 class="text-center text-white display-6">Product</h1>
            <!-- <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item"><a href="#">Pages</a></li>
                <li class="breadcrumb-item active text-white">Product</li>
            </ol> -->
        </div>
        <!-- Single Page Header End -->


        <!-- Fruits Shop Start-->
        <div class="container-fluid fruite py-5">
            <div class="container py-5">
                <!-- <h1 class="mb-4">Fresh fruits shop</h1> -->
                <div class="row g-4">
                    <div class="col-lg-12">
                        <div class="row g-4">
                            <div class="col-xl-3">
                                <div class="input-group w-100 mx-auto d-flex">
                                    <input type="search" id="search-input" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1" value="${keyWord}">
                                    <span id="search-icon-1" class="input-group-text p-3" onclick="appendParam('keyWord',document.getElementById('search-input').value);"><i class="fa fa-search"></i></span>
                                </div>
                            </div>
                            <div class="col-6"></div>
                           		<!-- <div class="col-xl-3">
                                <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                                    <label for="fruits">Default Sorting:</label>
                                    <select id="fruits" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                        <option value="volvo">Nothing</option>
                                        <option value="saab">Popularity</option>
                                        <option value="opel">Organic</option>
                                        <option value="audi">Fantastic</option>
                                    </select>
                                </div> -->
                            </div>
                        </div>
                        <div class="row g-4">
                            <div class="col-lg-3">
                                <div class="row g-4">
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <h4>Categories</h4>
                                            
                                            <ul class="list-unstyled fruite-categorie">
                                            	<form action="home.htm" method="get">
                                            	<li>                                      
	                                            	<div class="d-flex justify-content-between fruite-name active">
	                                            		<c:if test="${empty categoryActive}">
    														<a href="home.htm" style=" color: var(--bs-secondary);"><i class="fas me-2"></i>All</a>
														</c:if>
														<c:if test="${not empty categoryActive and filterActive == 0}">
    														<a href="home.htm"><i class="fas me-2"></i>All</a>
														</c:if>	
														<c:if test="${not empty categoryActive and filterActive != 0}">
															<a href="javascript:void(0);" onclick="appendParam('categoryId','')"><i class="fas me-2"></i>All</a>
														</c:if>												                                           		                        	                                                		                        
	                                                </div>
                                                </li> 
                                            	<c:forEach var="category" items="${categories}">
											    	<li>
	                                                    <div class="d-flex justify-content-between fruite-name">
	                                                    	<c:if test="${categoryActive eq category.id}">														   
														   		<a href="javascript:void(0);" onclick="appendParam('categoryId',${category.id})" style=" color: var(--bs-secondary);"><i class="fas me-2"></i>${category.name}</a>
															</c:if>
															<c:if test="${categoryActive != category.id}">														   
														   		<a href="javascript:void(0);" onclick="appendParam('categoryId',${category.id})"><i class="fas me-2"></i>${category.name}</a>
															</c:if>														    	                                                	                        
	                                                    </div>
                                                	</li>            	
                                				</c:forEach>  
                                				</form>                                                                         
                                            </ul>
                                        </div>
                                    </div>
                                    
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <h4 class="mb-2">Price</h4>
                                            <form action="home.htm" method="get">
                                            <div class="mb-2">
                                            	<c:if test="${filterActive == 0}">	
                                                	<input type="radio" class="me-2" id="Categories-0" name="filterByPrice" value="0" onclick="appendParam('filterByPrice',0)" checked="true">
                                                </c:if>
                                                <c:if test="${filterActive != 0}">
                                                	<input type="radio" class="me-2" id="Categories-0" name="filterByPrice" value="0" onclick="appendParam('filterByPrice',0)">
                                                </c:if>
                                                <label for="Categories-0"> All</label>
                                                
                                            </div>
                                            <div class="mb-2">
                                            	<c:if test="${filterActive == 1}">
													<input type="radio" class="me-2" id="Categories-1" name="filterByPrice" value="1" onclick="appendParam('filterByPrice',1)" checked="true">
												</c:if>
												<c:if test="${filterActive != 1}">                                            	                                         
                                                	<input type="radio" class="me-2" id="Categories-1" name="filterByPrice" value="1" onclick="appendParam('filterByPrice',1)">
                                                </c:if>
                                                <label for="Categories-1"> Under $10</label>
                                            </div>
                                            <div class="mb-2">
                                            	<c:if test="${filterActive == 2}">
													<input type="radio" class="me-2" id="Categories-2" name="filterByPrice" value="2" onclick="appendParam('filterByPrice',2)" checked="true">
												</c:if>
												<c:if test="${filterActive != 2}">                                            	                                         
                                                	<input type="radio" class="me-2" id="Categories-2" name="filterByPrice" value="2" onclick="appendParam('filterByPrice',2)">
                                                </c:if>                                             
                                                <label for="Categories-2"> $10 - $50</label>
                                            </div>
                                            <div class="mb-2">
                                            	<c:if test="${filterActive == 3}">
                                            		<input type="radio" class="me-2" id="Categories-3" name="filterByPrice" value="3" onclick="appendParam('filterByPrice',3)" checked="true">
                                            	</c:if>
                                            	<c:if test="${filterActive != 3}">
                                            		<input type="radio" class="me-2" id="Categories-3" name="filterByPrice" value="3" onclick="appendParam('filterByPrice',3)">   
                                            	</c:if>                                                
                                                <label for="Categories-3"> $50 - $100</label>
                                            </div>
                                            <div class="mb-2">
                                            	<c:if test="${filterActive == 4}">
                                            		<input type="radio" class="me-2" id="Categories-4" name="filterByPrice" value="4" onclick="appendParam('filterByPrice',4)" checked="true">
                                            	</c:if>
                                            	<c:if test="${filterActive != 4}">
                                            		<input type="radio" class="me-2" id="Categories-4" name="filterByPrice" value="4" onclick="appendParam('filterByPrice',4)">
                                            	</c:if>                                                
                                                <label for="Categories-4"> Over $100</label>
                                            </div>     
                                            <div class="mb-2">
                                            	<c:if test="${filterActive == 5}">
                                                	<input type="radio" class="me-2" id="Categories-5" name="filterByPrice" value="5" onclick="appendParam('filterByPrice',5)" checked="true">
                                                </c:if>
                                                <c:if test="${filterActive != 5}">
                                                	<input type="radio" class="me-2" id="Categories-5" name="filterByPrice" value="5" onclick="appendParam('filterByPrice',5)">
                                                </c:if>
                                                <label for="Categories-5"> Discount</label>
                                            </div>
                                            </form>  
                                        </div>
                                    </div>                             
                                    
                                    <div class="col-lg-12">
                                        <div class="position-relative">
                                            <img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded" alt="">
                                            <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">
                                                <h3 class="text-secondary fw-bold">Fresh <br> Fruits <br> Banner</h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                                                                             						                          
                            <div class="col-lg-9">
                                <div class="row g-4 justify-content-center">
                                	<span class="d-flex justify-content-center">
										<p style="color: red;">
								<c:if test="${not empty errorMessage}">${errorMessage} </c:if>
							</p>
						</span>
                                                                	
                                	<c:forEach var="product" items="${products}">
										<div class="col-md-6 col-lg-6 col-xl-4" onclick="window.location.href='product-detail.htm?productId=${product.id}';" style="cursor: pointer;">
	                                        <div class="rounded position-relative fruite-item border border-secondary" style="bottom: 0px;">
	                                            <div class="fruite-img">
	                                                <img src="${product.image}" class="img-fluid w-100 rounded-top" alt="">
	                                            </div>
	                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${product.category.name}</div>
	                                            <div class="p-4 rounded-bottom">
	                                                <h4>${product.name}</h4>
	                                                <p>
									                    <c:choose>
									                        <c:when test="${fn:length(product.describe) > 100}">
									                            ${fn:substring(product.describe, 0, 100)}...
									                        </c:when>
									                        <c:otherwise>
									                            ${product.describe}
									                        </c:otherwise>
									                    </c:choose>
									                </p>	                                                
	                                                <c:if test="${product.discount == 0}"><p class="text-dark fs-5 fw-bold mb-2">$${product.price} / ${product.unit}</p></c:if>
	                                                <c:if test="${product.discount != 0}"><p class="text-dark fs-5 fw-bold mb-2">$${product.price*(1-(product.discount/100.0))} <span class="text-danger text-decoration-line-through">${product.price}</span>  / ${product.unit}</p></c:if>
	                                                    	                                                    
                                                    <form action="addToCart.htm" method = "post">
                                                    	<input type="hidden" name="productId" value="${product.id}"/>
                                                    	<div class="inline-block">
	                                                    	<c:if test="${product.quantity > 0}">
	                                                    		<button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</button>
	                                                    	</c:if>
	                                                    	<c:if test="${product.quantity == 0}">
	                                                    		<p style="color: red;">SOLD OUT</p>
	                                                    	</c:if>
                                                    	</div>	                                                    
                                                    </form>	                     	                                                
	                                            </div>
	                                        </div>
                                    	</div>                                	
                                	</c:forEach>                                                                    
                                </div>
                                <div class="col-12">                                
                                        <div class="pagination d-flex justify-content-center mt-5">
                                        	<c:if test="${pageActive > 6}"><a href="home.htm?startPage=${startPage - 6}" class="rounded">&laquo;</a></c:if>                                            
                                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                				<c:if test="${pageActive == i}"><a href="#" class="active rounded">${i}</a></c:if>
                                				<c:if test="${pageActive != i}"><a href="home.htm?pageActive=${i}" class="rounded">${i}</a></c:if>
                                			</c:forEach>                       
                                			<c:if test="${endPage < maxPage}"><a href="home.htm?startPage=${endPage + 1}" class="rounded">&raquo;</a></c:if>                                                                         
                                        </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>       
        <!-- Fruits Shop End-->


        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
            <div class="container py-5">
                <div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5) ;">
                    <div class="row g-4">
                        <div class="col-lg-3">
                            <a href="#">
                                <h1 class="text-primary mb-0">Green Valley</h1>
                                <p class="text-secondary mb-0">Fresh products</p>
                            </a>
                        </div>
                        <div class="col-lg-6">
                        
                            <div class="position-relative mx-auto">
                            <form action = "sendMail.htm" method = "post">
                       			<input name = "emailReceiver" class = "form-control border-0 w-100 py-3 px-4 rounded-pill" type = "text" placeholder="Your Email" /> 
                               
          
                                <button type="submit" class="btn btn-primary border-0 border-secondary py-3 px-4 position-absolute rounded-pill text-white" style="top: 0; right: 0;">Subscribe Now</button>
                            </form>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="d-flex justify-content-end pt-3">
                                <a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-youtube"></i></a>
                                <a class="btn btn-outline-secondary btn-md-square rounded-circle" href=""><i class="fab fa-linkedin-in"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-item">
                            <h4 class="text-light mb-3">Why People Like us!</h4>
                            <p class="mb-4">typesetting, remaining essentially unchanged. It was 
                                popularised in the 1960s with the like Aldus PageMaker including of Lorem Ipsum.</p>
                            <a href="" class="btn border-secondary py-2 px-4 rounded-pill text-primary">Read More</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="d-flex flex-column text-start footer-item">
                            <h4 class="text-light mb-3">Shop Info</h4>
                            <a class="btn-link" href="">About Us</a>
                            <a class="btn-link" href="">Contact Us</a>
                            <a class="btn-link" href="">Privacy Policy</a>
                            <a class="btn-link" href="">Terms & Condition</a>
                            <a class="btn-link" href="">Return Policy</a>
                            <a class="btn-link" href="">FAQs & Help</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="d-flex flex-column text-start footer-item">
                            <h4 class="text-light mb-3">Account</h4>
                            <a class="btn-link" href="">My Account</a>
                            <a class="btn-link" href="">Shop details</a>
                            <a class="btn-link" href="">Shopping Cart</a>
                            <a class="btn-link" href="">Wishlist</a>
                            <a class="btn-link" href="">Order History</a>
                            <a class="btn-link" href="">International Orders</a>
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
                        <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
                    </div>
                    <div class="col-md-6 my-auto text-center text-md-end text-white">                        
                        Designed By <a class="border-bottom" href="https://github.com/baoo2003">Group 15</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->



        <!-- Back to Top -->
        <button 
	class="btn btn-primary border-3 border-primary rounded-circle button-chatbot" id="chatbot-btn"><i
	class="fa fa-robot"></i></button>

	<div class="chatbox" id="chatbot" style="display: none;">	
        <div class="chatbox-header">Green Valley Support
        	<span id="close-chatbot" style="cursor: pointer; float: right; font-size: 20px;">&times;</span>
		</div>
        <div class="chatbox-messages" id="messages"></div>
        <div class="chatbox-input">
            <input type="text" id="userInput" placeholder="Type a message...">
            <button onclick="sendMessage()">Send</button>
        </div>
    </div> 

        
    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
    
    <script >
    function submitForm(formId) {
		document.getElementById(formId).submit();
	}
    var customerID = "${sessionScope.customerId}";
    if(customerID !== null && customerID !== undefined && customerID !== ""){
	    document.getElementById('notification-btn').addEventListener('click', function(event) {
			event.preventDefault();
			document.getElementById('notification-box').classList.toggle('active');
		});
	    window.addEventListener('click', function(event) {
			if (!event.target.closest('#notification-btn') && !event.target.closest('#notification-box')) {
				document.getElementById('notification-box').classList.remove('active');
				}
			});
		
    }
	
    const messagesDiv = document.getElementById('messages');

document.getElementById('chatbot-btn').addEventListener('click', function() {
   const chatbot = document.getElementById('chatbot');
   chatbot.style.display = chatbot.style.display === 'none' ? 'block' : 'none';
});

document.getElementById('close-chatbot').addEventListener('click', function() {
    document.getElementById('chatbot').style.display = 'none';
});

document.getElementById('userInput').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault(); 
        sendMessage(); 
    }
});

function sendMessage() {
    const userInput = document.getElementById('userInput');
    const message = userInput.value.trim();

    if (message) {
        addMessage('user', message);
        userInput.value = '';
        simulateAIResponse(message);
    }
}

function addMessage(sender, text) {
    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message', sender);

    const contentDiv = document.createElement('div');
    contentDiv.classList.add('content');
    
    contentDiv.innerHTML = text;

    messageDiv.appendChild(contentDiv);
    messagesDiv.appendChild(messageDiv);
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
    
    saveMessage(sender, text);
}

function loadMessage(sender, text) {
    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message', sender);

    const contentDiv = document.createElement('div');
    contentDiv.classList.add('content');
    
    contentDiv.innerHTML = text;

    messageDiv.appendChild(contentDiv);
    messagesDiv.appendChild(messageDiv);
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
}
window.onload = function() {
    const savedMessages = JSON.parse(sessionStorage.getItem('chatMessages')) || [];
    savedMessages.forEach(msg => loadMessage(msg.sender, msg.text));
};
function saveMessage(sender, text) {
    const savedMessages = JSON.parse(sessionStorage.getItem('chatMessages')) || [];
    savedMessages.push({ sender, text });
    sessionStorage.setItem('chatMessages', JSON.stringify(savedMessages));
}

function simulateAIResponse(userMessage) {
    setTimeout(async () => {
        const aiResponse = await generateAIResponse(userMessage);
        console.log("1" + aiResponse);
        addMessage('ai', aiResponse);
    }, 1000);
}

async function generateAIResponse(userMessage) {
    const response = await fetch('http://127.0.0.1:5000/handle_message', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ "message": userMessage }),
    });
    const data = await response.json();
    return data.response;
}

 
    </script>
    
    
    
    
    </body>

</html>
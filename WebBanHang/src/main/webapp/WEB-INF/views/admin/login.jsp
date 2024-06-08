<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
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
  	<div class="position-relative overflow-hidden radial-gradient min-vh-100 d-flex align-items-center justify-content-center">
    	<div class="d-flex align-items-center justify-content-center w-100">
      		<div class="row justify-content-center w-100">
        		<div class="col-md-8 col-lg-6 col-xxl-3">
          			<div class="card mb-0">
            			<div class="card-body">
              				<h3 class="text-center">Welcome to Green Valley</h3>
              				<div class="d-flex justify-content-center">
			        			<span class="error">${message}</span>
			        		</div>
              				<form:form action="admin-login.htm" method="post" modelAttribute="login">
	                			<div class="mb-3">
	                  				<form:label path="username" for="username" class="form-label">Username</form:label>
	                  				<form:input path="username" class="form-control" id="username" />
	                  				<form:errors path="username" cssClass="error" />
	                			</div>
	                			<div class="mb-4">
	                  				<form:label path="password" for="password" class="form-label">Password</form:label>
	                  				<form:input path="password" type="password" class="form-control" id="password" />
	                  				<form:errors path="password" cssClass="error" />
	                			</div>
	                			<div class="d-flex">
	                				<button class="btn btn-primary mx-auto py-8 fs-4 mb-4 rounded-2" >
	                					Sign in
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

<script src="<c:url value="/resources/assets/libs/jquery/dist/jquery.min.js" />"></script>
<script src="<c:url value="/resources/assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js" />"></script>

</body>
</html>
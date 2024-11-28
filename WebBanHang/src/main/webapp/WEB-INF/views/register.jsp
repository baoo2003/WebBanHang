<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
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
                            <a href="home.htm" class="nav-item nav-link">Home</a>
                            <a href="cart.htm" class="nav-item nav-link">Cart</a>
                            <a href="contact.htm" class="nav-item nav-link">Contact</a>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <a href="cart.htm" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>                                
                            </a>
                            <div class=" nav-item dropdown">
                            	<a href="#" class="my-auto nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                	<i class="fas fa-user fa-2x"></i>
                            	</a>
                            	<div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="login.htm" class="dropdown-item">Login</a>
		                            <a href="register.htm" class="dropdown-item">Register</a>                                   
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
            <h1 class="text-center text-white display-6">Register</h1>            
        </div>
        <!-- Single Page Header End -->
        
        <c:if test="${not empty message}">
        	<div class="alert alert-warning text-center">${message}</div>
        </c:if>


        <!-- Contact Start -->
        <div class="container-fluid contact py-5">
            <div class="container py-5">
                <div class="p-5 bg-light rounded">
                    <div class="d-flex justify-content-center">
                        <div class="col-lg-7">
                            <form:form action="register.htm" method="post" modelAttribute="register">
                                <form:input path="username" type="text" class="w-100 form-control border-0 py-3" placeholder="Enter Your Username" />
                                <form:errors path="username" cssClass="error" />
                                <form:input path="firstName" type="text" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your First Name" />
                                <form:errors path="firstName" cssClass="error" />
                                <form:input path="lastName" type="text" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your Last Name" />
                                <form:errors path="lastName" cssClass="error" />
                                <form:select path="gender" type="text" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your Gender">
                                	<form:option value="0">Female</form:option>
                                	<form:option value="1">Male</form:option>
                                </form:select>
                                <form:input path="address" type="text" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your Address" />
                                <form:errors path="address" cssClass="error" />
                                <form:input path="phoneNumber" type="text" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your Phone Number" />
                                <form:errors path="phoneNumber" cssClass="error" />
                                <form:input path="email" type="email" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your Email" />
                                <form:input path="password" type="password" class="w-100 form-control border-0 py-3 mt-4" placeholder="Enter Your Password" />
                                <form:errors path="password" cssClass="error" />
                                <div class="d-flex">
                                	<button class="mx-auto px-4 btn border-secondary py-3 bg-white text-primary mt-4" type="submit">Submit</button>
                                </div>                                
                            </form:form>
                            <br />
                            <span class="d-flex justify-content-center">
                            	Already have an account?&nbsp;
                            	<a href="login.htm">Login</a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Contact End -->


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
    <script>
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
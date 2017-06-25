<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="biraj_rest" type="java.util.List" scope="request"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Choose restaurant</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  
  <!-- CSS ZA FORME SAMO -->
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="assets/css/form-elements.css">
  <link rel="stylesheet" href="assets/css/style.css">
		
  
 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
  
  body {
      font: 400 15px/1.8 Lato, sans-serif;
      color: #777;
  }
  h3, h4 {
      margin: 10px 0 30px 0;
      letter-spacing: 10px;      
      font-size: 20px;
      color: #111;
  }
  .container {
      padding: 80px 120px;
  }
  .person {
      border: 10px solid transparent;
      margin-bottom: 25px;
      width: 80%;
      height: 80%;
      opacity: 0.7;
  }
  .person:hover {
      border-color: #f1f1f1;
  }
  .carousel-inner img {
      -webkit-filter: grayscale(90%);
       
      width: 100%; /* Set width to 100% */
      margin: auto;
  }
  .carousel-caption h3 {
      color: #fff !important;
  }
  @media (max-width: 600px) {
    .carousel-caption {
      display: none; /* Hide the carousel text when the screen is less than 600 pixels wide */
    }
  }
  .bg-1 {
      background: #2d2d30;
      color: #bdbdbd;
  }
  .bg-1 h3 {color: #fff;}
  .bg-1 p {font-style: italic;}
  .list-group-item:first-child {
      border-top-right-radius: 0;
      border-top-left-radius: 0;
  }
  .list-group-item:last-child {
      border-bottom-right-radius: 0;
      border-bottom-left-radius: 0;
  }
  .thumbnail {
      padding: 0 0 15px 0;
      border: none;
      border-radius: 0;
  }
  .thumbnail p {
      margin-top: 15px;
      color: #555;
  }
  .btn {
      padding: 10px 20px;
      background-color: #34821A;
      color: #f1f1f1;
      border-radius: 0;
      transition: .2s;
  }
  .btn:hover, .btn:focus {
      border: 1px solid #333;
      background-color: #fff;
      color: #000;
  }
  .modal-header, h4, .close {
      background-color: #333;
      color: #fff !important;
      text-align: center;
      font-size: 30px;
  }
  .modal-header, .modal-body {
      padding: 40px 50px;
  }
  .nav-tabs li a {
      color: #777;
  }
  #googleMap {
      width: 100%;
      height: 400px;
	  -webkit-filter: grayscale(100%);
  }  
  .navbar {
      font-family: Montserrat, sans-serif;
      margin-bottom: 0;
      background-color: #3B961D;
      border: 0;
      font-size: 11px !important;
      letter-spacing: 4px;
      opacity: 0.9;
  }
  .navbar li a, .navbar .navbar-brand { 
      color: #d5d5d5 !important;
  }
  .navbar-nav li a:hover {
      color: #fff !important;
  }
  .navbar-nav li.active a {
      color: #fff !important;
      background-color: #29292c !important;
  }
  .navbar-default .navbar-toggle {
      border-color: transparent;
  }
  .open .dropdown-toggle {
      color: #fff;
      background-color: #555 !important;
  }
  .dropdown-menu li a {
      color: #000 !important;
  }
  .dropdown-menu li a:hover {
      background-color: red !important;
  }
  footer {
      background-color: #2d2d30;
      color: #f5f5f5;
      padding: 32px;
  }
  footer a {
      color: #f5f5f5;
  }
  footer a:hover {
      color: #777;
      text-decoration: none;
  }  
  .form-control {
      border-radius: 0;
  }
  textarea {
      resize: none;
  }
  </style>


</head>
<c:if test="${(sessionScope.gost==null) || (sessionScope.gost.validiran == false)}">
		<c:redirect url="./redirectToLogin.jsp" />
	</c:if>

<body id="chooseres" data-target=".navbar" data-offset="50">
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>      
                  
      </button>
      <a class="navbar-brand" href="#myPage">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#myPage" onclick="show_visits()">HOME</a></li>
        <li><a href="#restaurants" onclick="view_restaurants()">RESTAURANTS</a></li>
        <li class="active"><a href="#chooseres">BOOK A TABLE</a></li>
        <li><a href="#" onclick="show_reservations()">RESERVATIONS</a></li>
        <li><a href="#" onclick="show_orders()">ORDERS</a></li>
        
        
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">REQUESTS
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#" onclick="view_friend_req()">FRIEND REQUESTS</a></li>
            <li><a href="#">EVENT REQUESTS</a></li>
          </ul>
        </li>
        <li><a href="#" onclick="add_friends()">ADD FRIENDS</a></li>
		<li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">PROFILE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#my_profile" onclick="view_profile()">My profile</a></li>
            <li><a href="#settings" onclick="view_friends()">Friends</a></li>
            <li><a href="#" onclick="logout()">Log Out</a></li> 
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>



<div class="bg-1">
	<div id="restaurant">
			
		<div class="container">
		
		<h3 class="text-center">Step 1</h3>
			<div class="form-top">           		
		            <h3 class="text-center">CHOOSE RESTAURANT</h3>           		
		        </div>
			
				<c:forEach var="res" items="${biraj_rest}">
					<div class="form-box">
						
				        <div class="form-bottom">
							<form role="form" class="restaurant_update">
							    <div class="form-group">
							        <input type="text" value = "${res.nazivRestoran}" name="form-name" class="form-name form-control" id="form-name"  disabled="disabled">
							    </div>
							    <div class="form-group">
		
							        <input type="text" name="form-type" value = "${res.tipRestoran}" class="form-type form-control" id="form-type"  disabled="disabled">
							    </div>
								
								
							</form>
							<form action="./ChooseRestaurantController?restoran=${res.idRestorana}" method="post" accept-charset="ISO-8859-1">
			
							    	<button type="submit" class="btn">Choose</button> 
								</form>
						</div>
					</div>
				</c:forEach>
				
		</div>
		
	</div>
	</div>


							





<!-- Container (The restaurant Section) -->


<!-- Container (MENU Section) -->
<div id="menu">

</div>

<!-- Container (DRINK CART Section) -->
<div id="drink_cart">

</div>


<!-- Container (STAFF Section) -->
<div id="staff">
 
</div>

<!-- Container (Waiters Section) -->
<div id="waiters">

</div>

<!-- Container (Bartenders Section) -->
<div id="bartenders">

</div>

<!-- Container (Cooks Section) -->
<div id="cooks">

</div>
<!-- Container (REPORT Section) -->
<div id="reports" class="container">
  
</div>

<!-- Container (RESTAURANT REPORT Section) -->
<div id="restaurant_report">

</div>

<!-- Container (WAITER REPORT Section) -->
<div id="waiter_report">

</div>

<!-- Container (VISIT REPORT Section) -->
<div id="visit_report">

</div>

<!-- Container (TIME REPORT Section) -->
<div id="time_period_report">

</div>

<div id="log_out" onclick="logout()">

</div>
<script>
function logout() {
    window.location.href = "./LogoutController";
}

</script>

<script>
function view_profile() {
    window.location.href = "./show_profile.jsp";
}

</script>

<script>
function sortByName() {
    window.location.href = "./SortRestaurantsByNameController";
}

</script>


<script>
function sortByType() {
    window.location.href = "./SortRestaurantsByTypeController";
}

</script>

<script>
function view_friends() {
    window.location.href = "./ShowFreindsController";
}

</script>

<script>
function searchRestaurants() {
    window.location.href = "./SearchRestaurantsController";
}

</script>

<script>
function view_restaurants() {
    window.location.href = "./ShowRestaurantsController";
}

</script>

<script>
function add_friends() {
    window.location.href = "./AddFriendsController";
}

</script>

<script>
function view_friend_req() {
    window.location.href = "./ViewFriendRequestsController";
}

</script>

<script>
function choose_restaurant() {
    window.location.href = "./RestaurantsToChooseController";
}

</script>

<script>
function show_reservations() {
    window.location.href = "./ShowReservationsController";
}

</script>

<script>
function show_orders() {
    window.location.href = "./ShowOrdersController";
}

</script>

<script>
function show_visits() {
    window.location.href = "./ShowPoseteController";
}

</script>

<!-- Footer -->

<script>
$(document).ready(function(){
  // Initialize Tooltip
  $("#log_out").click(function(e){
		window.location.href = "LogoutController.jsp";
		
		
	});
  $('[data-toggle="tooltip"]').tooltip(); 
  
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {

    // Prevent default anchor click behavior
    event.preventDefault();

    // Store hash
    var hash = this.hash;

    // Using jQuery's animate() method to add smooth page scroll
    // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
    $('html, body').animate({
      scrollTop: $(hash).offset().top
    }, 900, function(){
   
      // Add hash (#) to URL when done scrolling (default click behavior)
      window.location.hash = hash;
    });
  });
})
</script>





</body>
</html>
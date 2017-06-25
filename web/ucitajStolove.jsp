<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="rasporedi" type="java.util.List" scope="session"/>
<html>
<head>
 <title>Choose table</title>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
  <script src="AndroidToast.js"></script>
  <link rel="stylesheet" href="AndroidToast.css"/> 
    
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
  <link rel="stylesheet" href="css/bootstrap.css">
  
  <script src="jquery.js"></script>
  <script src="fabric.js"></script>
  <script src="fabric.canvasex.js"></script>
  
    
  
  <style>
	  #canvas-container {
	    position: relative;
	    width: 600px;
	    height: 600px;
	    box-shadow: 0 0 5px 1px black;
	    margin: 10px auto;
	    border: 5px solid;
		border-color: #90C3D4;
		background-color: #F0F8FF;
		
	}
	
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

<script>function toast() {
    var toast = $(window).AndroidToast({
		message : "Already taken. Please choose another time for reservation",
		stayTime: "3000"
	});
    toast.AndroidToast('show');
}
</script>


<c:if test="${(sessionScope.gost==null) || (sessionScope.gost.validiran == false)}">
		<c:redirect url="./redirectToLogin.jsp" />
	</c:if>
	
<body id="chooseres" data-target=".navbar" data-offset="50">

<c:if test="${(sessionScope.zauzeto == true)}">
		<script> toast();</script>
	</c:if>

<script>
var json_data1;
<c:forEach items="${rasporedi}" var="raspored">
		json_data1 = ${raspored.jsonRaspored}	
</c:forEach>
</script>
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
        <li class="active"><a href="#" onclick="choose_restaurant()">BOOK A TABLE</a></li>
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
			
		<div class="container">
			<h3 class="text-center">Step 3</h3>
			<div class="form-top">           		
		            <h3 class="text-center">CHOOSE TABLE FOR RESERVATION(DOUBLE CLICK ON TABLE)</h3>           		
		        </div>
		      <div class="form-box">
				<div class="form-bottom">
					<div class="form-group">
							<button type="submit" class="btn" onclick="back_to_time()">Back to time selection</button> 
					</div>
					<div id="canvas-container">
					    <canvas id="canvas" width="600" height="600" style="border:1px solid #A1D490;"></canvas>
					    <script>
					    	var canvas_ = new fabric.CanvasEx('canvas');
					    	//canvas_.fireEventForObjectInsideGroup = true;
					    	canvas_.loadFromJSON(JSON.parse(JSON.stringify(json_data1)), function(obj) {
								canvas_.renderAll();
								console.log(' this is a callback. invoked when canvas is loaded!xxx ');
				   			//canvas_.selection = false;
								canvas_.forEachObject(function(obj){
									console.log(obj.name);
									obj.selectable = false;
									if(obj.name === 'recta'){      
										obj.set({
											left: 100,
											top:200,
											height: 700,
											width: 700,
											scaleX: .35,
											scaleY:.35,
											lockScalingY: .35
										});
				            
										canvas_.add(obj); 
										
									}
									obj.on('object:dblclick', function (options) {
										var stoLeft = obj.getLeft();
										var stoTop = obj.getTop();
							        	//window.alert('dblclick inside object');
										window.location.href='./CheckTableController?sto_x=' + stoLeft + '&sto_y=' + stoTop;
							            
							        });
									
				    
								});
							
							});
					    	
					    	
					    	</script>
					    
					</div>
					
				</div>
				</div>
				</div>
				</div>
<script>
/*if ("Event" in window) {
    // Listen to double click event on canvas element
    Event.add(canvas_.upperCanvasEl, 'dblclick', function (e, self) {
        var target = canvas_.findTarget(e);
        if (target) {
            window.alert('dblclick inside object');
        } else {
            window.alert('dblclick outside object');
        }
        
        
    });
}*/

</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
function dupliKlik(e) {
	e.target.get('name');
    window.location.href = "./CreateRezervacijaController";
}
</script>

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
function back_to_time() {
    window.location.href = "./choose_time.jsp";
}

</script>


<script>
function choose_table() {
    window.location.href = "./ReadRasporedStolovaController";
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
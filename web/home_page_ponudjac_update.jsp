<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<jsp:useBean id="ponude" type="java.util.List" scope="session"/>
<jsp:useBean id="namirnice" type="java.util.List" scope="session"/>
<jsp:useBean id="ponudjac" type="server.entity.Ponudjac" scope="session"/>

<html lang="en">
<head>
  <title>Home page - supplier</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  
   <!-- CSS ZA TABELE -->
  <link href="startbootstrap-sb-admin-2-1.0.8/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

  <!-- CSS ZA FORME SAMO -->
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
  
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="font-awesome.min.css">
  <link rel="stylesheet" href="form-elements.css">
  <link rel="stylesheet" href="form-style.css">
		
   <!-- CSS ZA MENU U COSKU -->
    <link href="stylish-portfolio.css" rel="stylesheet">

  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
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
  h5 {
	  margin: 0 0 30px 0;
      letter-spacing: 10px;      
      font-size: 15px;
      color: #d5d5d5;
  }
  
  .container {
	  width: 100%; /* Set width to 100% */
      margin: auto;
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
      color: #34821A;
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
      background-color: #333;
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
      background-color: #34821A;
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
  
/* Side Menu */

#sidebar-wrapper {
    z-index: 1000;
    position: fixed;
    right: 0;
    width: 250px;
    height: 100%;
    margin-right: -250px;
    overflow-y: auto;
    background: #222;
    -webkit-transition: all 0.4s ease 0s;
    -moz-transition: all 0.4s ease 0s;
    -ms-transition: all 0.4s ease 0s;
    -o-transition: all 0.4s ease 0s;
    transition: all 0.4s ease 0s;
}

.sidebar-nav {
    position: absolute;
    top: 0;
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
}

.sidebar-nav li {
    text-indent: 20px;
    line-height: 40px;
}

.sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
}

.sidebar-nav li a:hover {
    text-decoration: none;
    color: #fff;
    background: rgba(255,255,255,0.2);
}

.sidebar-nav li a:active,
.sidebar-nav li a:focus {
    text-decoration: none;
}

.sidebar-nav > .sidebar-brand {
    height: 55px;
    font-size: 15px;
    line-height: 55px;
}

.sidebar-nav > .sidebar-brand a {
    color: #999;
}

.sidebar-nav > .sidebar-brand a:hover {
    color: #fff;
    background: none;
}

#menu-toggle {
    z-index: 1;
    position: fixed;
    top: 0;
    right: 0;
}

#sidebar-wrapper.active {
    right: 250px;
    width: 250px;
    -webkit-transition: all 0.4s ease 0s;
    -moz-transition: all 0.4s ease 0s;
    -ms-transition: all 0.4s ease 0s;
    -o-transition: all 0.4s ease 0s;
    transition: all 0.4s ease 0s;
}

.toggle {
    margin: 5px 5px 0 0;
}

/* Custom Button Styles */

.btn-dark {
    border-radius: 0;
    color: #fff;
    background-color: rgba(0,0,0,0.4);
}

.btn-dark:hover,
.btn-dark:focus,
.btn-dark:active {
    color: #fff;
    background-color: rgba(0,0,0,0.7);
}

.btn-light {
    border-radius: 0;
    color: #333;
    background-color: rgb(255,255,255);
}

.btn-light:hover,
.btn-light:focus,
.btn-light:active {
    color: #333;
    background-color: rgba(255,255,255,0.8);
}

  </style>
</head>
<c:if test="${sessionScope.ponudjac==null}">
		<c:redirect url="./redirectToLogin.jsp" />
</c:if>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid"> <!-- TOOLBAR -->
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav ">
        <li><a href="#myPage">HOME</a></li>
		<li><a href="#announcements">ANNOUNCEMENTS</a></li>
        <li><a href="#active_supplies">ACTIVE SUPPLIES</a></li>
        <li><a href="#history">HISTORY</a></li>
		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">PROFILE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#my_profile">MY PROFILE</a></li>
            <li><a href="#" onclick="logout()">LOGOUT</a></li> 
          </ul>
        </li>
		<li><a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a><!-- PADAJUCI MENI -->
			<nav id="sidebar-wrapper">
				<ul class="sidebar-nav"><a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
					<li class="sidebar-brand"> <a href="#myPage">UPDATES</a> </li>
					<li><a href="#update_supply">CREATE NEW SUPPLY</a></li> 
					<li><a href="#update_profile">UPDATE PROFILE</a></li> 
				</ul>
			</nav>
		</li>
      </ul>
    </div>
  </div>
</nav> <!-- KRAJ TOOLBARA -->


<!-- PADAJUCI MENI -->
<!-- Container (Create new supply Section) -->
<div id="update_supply" class="bg-1"><div class="container"><h3 class="text-center">CREATE NEW SUPPLY</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">CREATE NEW SUPPLY</h3></div>
	    <div class="form-bottom">
			<form action="./UpdatePonudaController" method="post" class="supply_update">
				<div class="form-group">
			        <label class="sr-only" for="form-price">Price</label>
			        <input type="text" name="form-price" placeholder="Price" class="form-price form-control" id="form-price" >
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-delivery">Delivery time</label>
				    <input type="text" name="form-delivery" placeholder="Delivery time (dd/mm/gggg)" class="form-delivery form-control" id="form-delivery" >
			    </div>
			    <div class="form-group">
			        <label class="sr-only" for="form-guarantee">Guarantee</label>
			        <input type="text" name="form-guarantee" placeholder="Guarantee" class="form-guarantee form-control" id="form-guarantee" pattern="[A-Za-z]{4,20}">
			    </div>
				
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button> 
			</form>
		</div>
	</div><div>
</div>

<!-- LOGOUT -->
<script>
function logout() {
    window.location.href = "./LogoutController";
}
</script>
<div id="googleMap"></div>
<!-- Add Google Maps -->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<!-- DataTables JavaScript -->
	<script src="jquery.dataTables.min.js"></script>
	<script src="dataTables.bootstrap.min.js"></script>
<script>
var myCenter = new google.maps.LatLng(45.257131, 19.813652);

function initialize() {
var mapProp = {
center:myCenter,
zoom:12,
scrollwheel:false,
draggable:false,
mapTypeId:google.maps.MapTypeId.ROADMAP
};

var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

var marker = new google.maps.Marker({
position:myCenter,
});

marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

<!-- Footer -->
<footer class="text-center">
  <a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TO TOP">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a><br><br> 
  <p>All rights reserved by isa_mrs_tim14</p>
</footer>

<script>
$(document).ready(function(){
	
	
	
	 // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

	//create_new_supply
	$("#form-price").on('change',function(){
		if(document.getElementById("form-price").validity.patternMismatch){
			toastr.error('Uneta cena nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-price").value == ""){
			toastr.error('Niste uneli cenu!');
		}
	});
	$("#form-delivery").on('change',function(){
		
		if(document.getElementById("form-delivery").value == ""){
			toastr.error('Niste uneli vreme dostave!');
		}
	});
	$("#form-guarantee").on('change',function(){
		if(document.getElementById("form-guarantee").validity.patternMismatch){
			toastr.error('Uneta garancija nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-guarantee").value == ""){
			toastr.error('Niste uneli garanciju!');
		}
	});

	// Initialize Tooltip
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
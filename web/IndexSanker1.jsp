<!DOCTYPE html>
<html lang="en">
<head>
  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reoni" type="java.util.List" scope="session"/>
<jsp:useBean id="jelovnici" type="java.util.List" scope="session"/>
<jsp:useBean id="kartePica" type="java.util.List" scope="session"/>
<jsp:useBean id="jela" type="java.util.List" scope="session"/>
<jsp:useBean id="konobari" type="java.util.List" scope="session"/>
<jsp:useBean id="kuvari" type="java.util.List" scope="session"/>
<jsp:useBean id="sankeri" type="java.util.List" scope="session"/>
<jsp:useBean id="restorani" type="java.util.List" scope="session"/>
<jsp:useBean id="namirnice" type="java.util.List" scope="session"/>
<jsp:useBean id="ponude" type="java.util.List" scope="session"/>
<jsp:useBean id="restoran" type="server.entity.Restoran" scope="session"/>
<jsp:useBean id="rasporedi" type="java.util.List" scope="session"/>
<jsp:useBean id="posete" type="java.util.List" scope="session"/>
<jsp:useBean id="rezervacije" type="java.util.List" scope="session"/>
<jsp:useBean id="narudzbine" type="java.util.List" scope="session"/>
<jsp:useBean id="ocenaKonobara" type="java.lang.Integer" scope="session"/>
<jsp:useBean id="ocenaJela" type="java.lang.Integer" scope="session"/>
<jsp:useBean id="obrociSize" type="java.lang.Integer" scope="session"/>
<jsp:useBean id="ocenaRestorana" type="java.lang.Integer" scope="session"/>
<jsp:useBean id="prihodKonobara" type="java.lang.Double" scope="session"/>
<jsp:useBean id="zaradaJelo" type="java.lang.Double" scope="session"/>
<jsp:useBean id="zaradaPice" type="java.lang.Double" scope="session"/>
  
  <title>Home page - restaurant manager</title>
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
  h5 {
	  margin: 10px 0 30px 0;
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
  </style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">

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
        <li><a href="#menu">CALENDAR</a></li>
        <li><a href="#drink_cart">ORDERS</a></li>
		<li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">PROFILE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#my_profile">My profile</a></li>
            <li><a href="#settings">Settings</a></li>
            <li><a href="#log_out">Log Out</a></li> 
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
	  <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
      <li data-target="#myCarousel" data-slide-to="5"></li>
	  
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="1.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>  
        </div>      
      </div>

      <div class="item">
        <img src="2.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>
        </div>      
      </div>
    
      <div class="item">
        <img src="3.jpg" alt="1bite2go" width="1200" height="700">      
      </div>
	
	  <div class="item ">
        <img src="5.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3> 
        </div>      
      </div>

      <div class="item">
        <img src="6.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>       
        </div>      
      </div>
    
      <div class="item">
        <img src="7.jpg" alt="1bite2go" width="1200" height="700">      
      </div>
	
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>

<!-- Container (Orders Section)-->
<div id="Orders" class="bg-1"><div class="container"><h3 class="text-center">ORDERS</h3>
<div class="form-box"><div class="form-top"><h3 class="text-center"></h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="ordersTable">
				<thead>
					<tr>
						<th>Drinks</th>
						<th>Sto</th>
						<th>Narudzbina</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${narudzbine}" var="narudzbina">
					<tr>
					<td>
					<table class="table dataTable table-bordered table-hover" id="ordersTableDrinks">
						<tbody>
							<c:forEach items="${narudzbina.porucenoPice.pica}" var="pice1">
								<td>${pice1.nazivPica}</td>
							</c:forEach>
						</tbody>
					</table>
					</td>
					<td>${narudzbina.rezervacija.sto.idStola}</td>
					<td>${narudzbina.idNarudzbine}</td>
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div></div>
</div>
</div>

<!-- Container (Update profile Section) -->
<div id="my_profile" class="bg-1"><div class="container"><h3 class="text-center">PROFILE UPDATE</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center"></h3></div>
	    <div class="form-bottom">
			<form action="./UpdateSankerController" method="post" class="profile_update">
				<div class="form-group">
			        <label class="sr-only" for="form-email-sanker">Email</label>
			        <input type="text" name="form-email-sanker" placeholder="Username" class="form-email-sanker form-control" id="form-email-sanker" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-password-sanker">Password</label>
			        <input type="password" name="form-password-sanker" placeholder="Password" class="form-password-sanker form-control" id="form-password-sanker" pattern=".{6,}">
			    </div>
				<!--
				<div class="form-group">
			        <label class="sr-only" for="form-old_password">Old password</label>
			        <input type="password" name="form-old_password" placeholder="Old password" class="form-old_password form-control" id="form-old_password">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-new_password1">New password</label>
			        <input type="text" name="form-new_password1" placeholder="New password" class="form-new_password1 form-control" id="form-new_password1">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-new_password2">New password</label>
			        <input type="text" name="form-new_password2" placeholder="New password" class="form-new_password2 form-control" id="form-new_password2">
			    </div>
				-->
			    <div class="form-group">
			        <label class="sr-only" for="form-name-sanker">Name</label>
			        <input type="text" name="form-name-sanker" placeholder="Name" class="form-name-sanker form-control" id="form-name-sanker" pattern="[A-Za-z]{4,10}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-lastname-sanker">Last name</label>
			        <input type="text" name="form-lastname-sanker" placeholder="Last name" class="form-lastname-sanker form-control" id="form-lastname-sanker" pattern="[A-Za-z]{4,10}">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-date-sanker">Date of birth</label>
			        <input type="text" name="form-date-sanker" placeholder="Date of birth(dd/mm/yyyy)" class="form-date-sanker form-control" id="form-date-sanker" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
			    </div>
				<div class="form-group"><h5> Clothes size </h5>   
					<select name="form-konfekcijski-broj-sanker" class="form-konfekcijski-broj-sanker form-control" id="form-konfekcijski-broj-sanker">
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="L">L</option>
						<option value="XL">XL</option>
						<option value="XXL">XXL</option>
						<option value="XXXL">XXXL</option>
					</select> 
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-velicina-obuce-sanker">Shoe size</label>
			        <input type="number" name="form-velicina-obuce-sanker" placeholder="Shoe size" class="form-velicina-obuce-sanker form-control" id="form-velicina-obuce-sanker" pattern="[A-Za-z]{4,10}">
			    </div>
				
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button> 
			</form>
		</div>
	</div></div>
</div>

<!-- Container (The restaurant Section) -->

<!-- Container (MENU Section) -->
<div id="menu" class="bg-1">
	<div class="container">
		<h3 class="text-center">MENU</h3>
	</div>
</div>

<!-- Container (DRINK CART Section) -->
<div id="drink_cart" class="bg-1">
	<div class="container">
		<h3 class="text-center">DRINK CART</h3>
	</div>
</div>


<!-- Container (STAFF Section) -->
<div id="staff" class="bg-1">
 
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
<div id="reports" >
  
</div>

<!-- Container (RESTAURANT REPORT Section) -->
<div id="restaurant_report" >

</div>

<!-- Container (WAITER REPORT Section) -->
<div id="waiter_report" >

</div>

<!-- Container (VISIT REPORT Section) -->
<div id="visit_report" >

</div>

<!-- Container (TIME REPORT Section) -->
<div id="time_period_report">

</div>

<div id="groceries" class="bg-1">
	<div class="container">
		<h3 class="text-center">GROCERIES</h3>
	</div>
</div>

<div id="suppliers" class="bg-1">
	<div class="container">
		<h3 class="text-center">SUPPLIERS</h3>
	</div>
</div>

<div id="googleMap"></div>

<!-- Add Google Maps -->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
var myCenter = new google.maps.LatLng(41.878114, -87.629798);

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
</footer>

<script>
$(document).ready(function(){
	
	$('#ordersTable').DataTable({
        responsive: true
	
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
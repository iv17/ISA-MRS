<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="moje_posete" type="java.util.List" scope="request"/>

<html lang="en">
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>

<head>

  <title>Home page - guest</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="star-rating.css">
  <script src="star-rating.js"></script>


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
<body id="myPage" data-target=".navbar" data-offset="50">
<c:if test="${(sessionScope.rocena!=null)}">
		<script> toastr.error('Mark for restaurant already exixts.'); </script>
	</c:if>

<c:if test="${(sessionScope.jocena!=null)}">
		<script> toastr.error('Mark for meal already exixts.'); </script>
	</c:if>

<c:if test="${(sessionScope.kocena!=null)}">
		<script> toastr.error('Mark for waiter already exixts.'); </script>
	</c:if>

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
        <li class="active"><a href="#myPage" onclick="show_visits()">HOME</a></li>
        <li><a href="#" onclick="view_restaurants()">RESTAURANTS</a></li>
        <li><a href="#" onclick="choose_restaurant()">BOOK A TABLE</a></li>
        <li><a href="#" onclick="show_reservations()">RESERVATIONS</a></li>
        <li><a href="#" onclick="show_orders()">ORDERS</a></li>


        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#requests">REQUESTS
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#" onclick="view_friend_req()">FRIEND REQUESTS</a></li>
            <li><a href="#" onclick="view_event_requests()">EVENT REQUESTS</a></li>
          </ul>
        </li>
        <li><a href="#addfriends" onclick="add_friends()">ADD FRIENDS</a></li>
		<li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#profile">PROFILE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#" onclick="view_profile()">My profile</a></li>
            <li><a href="#" onclick="view_friends()">Friends</a></li>
            <li><a href="#" onclick="logout()">Log Out</a></li> 
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
        <img src="images/1.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>

        </div>
      </div>

      <div class="item">
        <img src="images/2.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>

        </div>
      </div>

      <div class="item">
        <img src="images/3.jpg" alt="1bite2go" width="1200" height="700">
        <div class="1bite2go">
          <h3>1bite2go</h3>

        </div>
      </div>

	  <div class="item ">
        <img src="images/5.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>

        </div>
      </div>

      <div class="item">
        <img src="images/6.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption">
          <h3>1bite2go</h3>

        </div>
      </div>

      <div class="item">
        <img src="images/7.jpg" alt="1bite2go" width="1200" height="700">
        <div class="1bite2go">
          <h3>1bite2go</h3>

        </div>
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

<!-- Container (The restaurant Section) -->

<div id="restaurant" class="bg-1">

		<div class="container">
			<h3 class="text-center">VISIT HISTORY</h3>
			<c:forEach var="res" items="${moje_posete}">
				<form action="./AddOcenaController" method="post" accept-charset="ISO-8859-1">

						<div class="form-box">

					        <div class="form-bottom">
									<label>You visited:</label>

								    <div class="form-group">
								    	<input type="hidden" name="poseta_id" value = "${res.poseta.idPoseta}" id="poseta_id">

								        <input type="text" value = "${res.restoran.nazivRestoran}" name="form-name" class="form-name form-control" id="form-name"  disabled="disabled">
								    </div>
								    <div class="form-group">

								        <input type="text" name="form-type" value = "${res.rezervacija.datumDolaskaRezervacija}" class="form-type form-control" id="form-type"  disabled="disabled">
								    </div>
									<div class="form-group">

								        <input type="text" name="form-type" value = "${res.rezervacija.rezervacija_duzina_boravka}" class="form-type form-control" id="form-type"  disabled="disabled">
								    </div>

								    <div class="form-group">
										<label>Waiter:</label>
								        <input type="text" name="form-type" value = "${res.konobar.korisnik_ime}" class="form-type form-control" id="form-type"  disabled="disabled">
								    	<input type="text" name="form-type" value = "${res.konobar.korisnik_prezime}" class="form-type form-control" id="form-type"  disabled="disabled">

								    </div>
									<label>Drink</label>
									<c:forEach var="p" items="${res.porucenoPice.pica}">

										<div class="form-group">

								        	<input type="text" name="form-type" value = "${p.nazivPica}" class="form-type form-control" id="form-type"  disabled="disabled">
								    	</div>

									</c:forEach>
									<label>Meal</label>
									<c:forEach var="j" items="${res.obrok.jela}">

										<div class="form-group">

								        	<input type="text" name="form-type" value = "${j.nazivJela}" class="form-type form-control" id="form-type"  disabled="disabled">
								    	</div>

									</c:forEach>
									<div class="form-group">
								        <label for="input-1" class="control-label">Rate restaurant</label>
		    							<input id="input-1" name="input-1" class="rating rating-loading" data-min="0" data-max="5" data-step="1" data-size="xs">
									</div>
									<div class="form-group">
								        <label for="input-2" class="control-label">Rate meal</label>
		    							<input id="input-2" name="input-2" class="rating rating-loading" data-min="0" data-max="5" data-step="1" data-size="xs">
									</div>
									<div class="form-group">
								        <label for="input-3" class="control-label">Rate waiter</label>
		    							<input id="input-3" name="input-3" class="rating rating-loading" data-min="0" data-max="5" data-step="1" data-size="xs">
									</div>
									<button type="submit" class="btn">Add marks</button>


							</div>
						</div>
				</form>
			</c:forEach>

		</div>

	</div>





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
function view_restaurants() {
    window.location.href = "./ShowRestaurantsController";
}

</script>

<script>
function view_friends() {
    window.location.href = "./ShowFreindsController";
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
function view_event_requests() {
    window.location.href = "./ViewEventRequestsController";
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

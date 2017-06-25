<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!-- ZA POPUNJAVANJE COMBO-BOX-A KOD DODAVANJA KONOBARA -->
<jsp:useBean id="reoni" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE COMBO-BOX-A KOD DODAVANJA JELA -->
<jsp:useBean id="jelovnici" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE COMBO-BOX-A KOD DODAVANJA PICA -->
<jsp:useBean id="kartePica" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM JELOVNIKA -->
<jsp:useBean id="jela" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH KONOBARA -->
<jsp:useBean id="konobari" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH KUVARA -->
<jsp:useBean id="kuvari" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH SANKERA -->
<jsp:useBean id="sankeri" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH RESTORANA -->
<jsp:useBean id="restorani" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH POTREBNIH NAMIRNICA -->
<jsp:useBean id="namirnice" type="java.util.List" scope="session"/>
<!-- ZA POPUNJAVANJE TABELE SA PRIKAZOM SVIH POTREBNIH NAMIRNICA -->
<jsp:useBean id="ponude" type="java.util.List" scope="session"/>
<!-- ZA UPDATE RESTORANA -->
<jsp:useBean id="restoran" type="server.entity.Restoran" scope="session"/>
<!-- ZA UPDATE RESTORANA -->
<jsp:useBean id="rasporedi" type="java.util.List" scope="session"/>																<!-- IZGLEDA DA SE NE KORISTI NIGDE-->
<jsp:useBean id="posete" type="java.util.List" scope="session"/>
<jsp:useBean id="rezervacije" type="java.util.List" scope="session"/>
<jsp:useBean id="narudzbine" type="java.util.List" scope="session"/>
<!-- ZA STAR RATING KONOBAR -->
<jsp:useBean id="ocenaKonobara" type="java.lang.Integer" scope="session"/>
<!-- ZA STAR RATING JELO -->
<jsp:useBean id="ocenaJela" type="java.lang.Integer" scope="session"/>
<!-- FLAG ZA NOTIFIKACIJE -->
<jsp:useBean id="obrociSize" type="java.lang.Integer" scope="session"/>
<!-- ZA STAR RATING RESTORAN -->
<jsp:useBean id="ocenaRestorana" type="java.lang.Integer" scope="session"/>
<!-- FLAG ZA NOTIFIKACIJE -->
<jsp:useBean id="prihodKonobara" type="java.lang.Double" scope="session"/>
<!-- FLAG ZA NOTIFIKACIJE -->
<jsp:useBean id="prihodRestorana" type="java.lang.Double" scope="session"/>
<!-- ZA GRAFICKI PRIKAZ ZARADE KONOBARA -->
<jsp:useBean id="zaradaJelo" type="java.lang.Double" scope="session"/>
<jsp:useBean id="zaradaPice" type="java.lang.Double" scope="session"/>
<!-- FLAG ZA NOTIFIKACIJE -->
<jsp:useBean id="brojPosetaDnevni" type="java.lang.Integer" scope="session"/>
<!-- FLAG ZA NOTIFIKACIJE -->
<jsp:useBean id="brojPosetaNedeljni" type="java.lang.Integer" scope="session"/>
<!-- ZA GRAFICKI PRIKAZ ZARADE RESTORANA -->
<jsp:useBean id="json" type="java.util.ArrayList" scope="session"/>
<!-- ZA GRAFIK POSECENOSTI NA NEDELJNOM NIVOU -->
<jsp:useBean id="jsonNedeljni" type="java.util.ArrayList" scope="session"/>
<!-- ZA GRAFIK POSECENOSTI NA DNEVNOM NIVOU -->
<jsp:useBean id="jsonDnevni" type="java.util.ArrayList" scope="session"/>
<jsp:useBean id="ponudjaci" type="java.util.List" scope="session"/>
<html lang="en">
<head>
  <title>Home page - restaurant manager</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  
  <!-- CSS ZA TABELE -->
  <link href="dataTables.bootstrap.css" rel="stylesheet">
  <link href="dataTables.responsive.css" rel="stylesheet">
  
  <!-- CSS ZA FORME -->
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
  <link rel="stylesheet" href="font-awesome.min.css">
  <link rel="stylesheet" href="form-elements.css">
  <link rel="stylesheet" href="form-style.css">
		
  <!-- CSS ZA MENU U COSKU -->
  <link href="stylish-portfolio.css" rel="stylesheet">
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
  
  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
  
  <!-- TOASTR -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>

  <!-- STAR RATING -->
  <link href="star-rating.css" media="all" rel="stylesheet" type="text/css" />
  <script src="star-rating.js" type="text/javascript"></script>
  
  <!-- MORISS.JS -->
  <link href="morris.css" rel="stylesheet">
  <script src="raphael-min.js"></script>
  <script src="morris.min.js"></script>
	
  <!-- DATE PICKER -->
  <script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
  <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
  
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
      //-webkit-filter: grayscale(90%);
       
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
	  //-webkit-filter: grayscale(100%);
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
<c:if test="${sessionScope.menadzer_restorana==null}">
		<c:redirect url="./redirectToLogin.jsp" />
</c:if>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid"> <!-- TOOLBAR -->
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#myPage">HOME</a></li>
        <li><a href="#restaurant">RESTAURANT</a></li>
        <li><a href="#menu">MENU</a></li>
        <li><a href="#drink_cart">DRINK CART</a></li>
		<li><a href="#pickers">DATE PICKERS</a></li>
		<li><a href="#smene">WORK SCHEDULES</a></li>
        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">STAFF
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#waiters">WAITERS</a></li>
            <li><a href="#bartenders">BARTENDERS</a></li>
            <li><a href="#cooks">COOKS</a></li> 
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">REPORTS
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            
			<li><a href="#waiters_report">WAITERS</a></li>
			<li><a href="#meal_report">MEALS</a></li>
			<li><a href="#meal_rating">MEAL RATING</a></li>
            <li><a href="#waiter_rating">WAITER RATING</a></li>
            <li><a href="#waiter_income">WAITER INCOME</a></li> 
			<li><a href="#restaurant_rating">RESTAURANT RATING</a></li>
            <li><a href="#restaurant_income">RESTAURANT INCOME</a></li> 
			<li><a href="#day_visitation_report">DAY VISITATION REPORT</a></li> 
			<li><a href="#week_visitation_report">WEEK VISITATION REPORT</a></li> 
          </ul>
        </li>
		<li><a href="#necessities">NECESSITIES</a></li>
		<li><a href="#supplies">SUPPLIES</a></li>
		<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">PROFILE
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#my_profile">MY PROFILE</a></li>
            <li><a href="#settings">SETTINGS</a></li>
            <li><a href="#" onclick="logout()">LOGOUT</a></li> 
          </ul>
        </li>
		<li><a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i class="fa fa-bars"></i></a><!-- PADAJUCI MENI -->
			<nav id="sidebar-wrapper">
				<ul class="sidebar-nav"><a id="menu-close" href="#" class="btn btn-light btn-lg pull-right toggle"><i class="fa fa-times"></i></a>
					<li class="sidebar-brand"> <a href="#notifications">NOTIF</a> </li>
					<li><a href="#add_new_menu">ADD NEW MENU</a></li>
					<li><a href="#add_new_drink_cart">ADD NEW DRINK CART</a></li>
					<li><a href="#add_new_meal" >ADD MEAL</a></li>
					<li><a href="#add_new_drink">ADD DRINK</a></li>
					<li><a href="#add_new_waiter">ADD NEW WAITER</a></li>   
					<li><a href="#add_new_bartender">ADD NEW BARTENDER</a></li>   
					<li><a href="#add_new_cook">ADD NEW COOK</a></li>
					<li><a href="#add_new_supplier">ADD NEW SUPPLIER</a></li>
					<li><a href="#add_new_necessity">ADD NEW NECESSITY</a></li>	
					<li><a href="#update_restaurant">UPDATE RESTAURANT</a></li>		
					<li><a href="./kalendarskiPrikaz.jsp">CREATE WORK SCHEDULE</a></li>
					<li><a href="./rasporedStolova.jsp">CREATE TABLE ARRANGEMENT</a></li>					
				</ul>
			</nav>
		</li>
      </ul>
    </div>
  </div>
</nav> <!-- KRAJ TOOLBARA -->
<div id="myCarousel" class="carousel slide" data-ride="carousel"> <!-- CAROUSEL SLIKE -->
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
	  <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
      <li data-target="#myCarousel" data-slide-to="5"></li>  
    </ol>

    <!-- Wrapper for slides SLIKE-->
    <div class="carousel-inner" role="listbox">
      <div class="item active"><img src="images/1.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption"><h3>1bite2go</h3></div>      
      </div>
      <div class="item"><img src="images/2.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption"><h3>1bite2go</h3></div>      
      </div>
      <div class="item"><img src="images/3.jpg" alt="1bite2go" width="1200" height="700">      
      </div>
	  <div class="item "><img src="images/5.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption"><h3>1bite2go</h3></div>      
      </div>
      <div class="item"><img src="images/6.jpg" alt="1bite2go" width="1200" height="700">
        <div class="carousel-caption"><h3>1bite2go</h3></div>      
      </div>
      <div class="item"><img src="images/7.jpg" alt="1bite2go" width="1200" height="700">      
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
</div> <!--KRAJ CAROUSEL SLIKE -->

<!-- TOOLBAR MENUS -->
<!-- Container (RESTAURANT Section)-->
<div id="restaurant" class="bg-1"><div class="container"><h3 class="text-center">RESTAURANT</h3>
<c:if test="${ocenaRestorana == -1}">
	<script>toastr.error('U restoranu nije bilo poseta!');</script>
</c:if>
<c:if test="${ocenaRestorana != -1 && ocenaRestorana != 0}">
	<script>toastr.success('Izvestaj o restoranu uspesno kreiran!');</script>
</c:if>
<div class="form-box"><div class="form-top"><h3 class="text-center">UPDATE RESTAURANT</h3></div>
		<a href="./IzvestajRestoranOcenaController">IZVESTAJ</a>
	    <div class="form-bottom">
			<form action="./PrepareUpdateRestoranController?id=${restoran.idRestorana}" method="post" role="form" class="restaurant_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
					<label class="sr-only" for="form-name">Name</label>
				    <input type="text" value="${restoran.nazivRestoran}" name="form-name" class="form-name form-control" id="form-name">
				</div>
				<div class="form-group"><h5> TYPE </h5>   
					<select name="form-type" class="form-type form-control" id="form-type">
						<option value="DOMACA KUHINJA">DOMACA KUHINJA</option>
						<option value="BANATSKA KUHINJA">DOMACA KUHINJA</option>
						<option value="INTERNACIONALNA KUHINJA">INTERNACIONALNA</option>
						<option value="KINESKA KUHINJA">KINESKA KUHINJA</option>
						<option value="ITALIJANSKA KUHINJA">ITALIJANSKA KUHINJA</option>
						<option value="MEDITERANSKA KUHINJA">MEDITERANSKA KUHINJA</option>
						<option value="HRONO KUHINJA">HRONO KUHINJA</option>
						<option value="VEGANSKA KUHINJA">VEGANSKA KUHINJA</option>
					</select> 
			    </div>
				<div class="form-group"><h5> MENU </h5>   
					<select name="form-menu" class="form-menu form-control" id="form-menu">
						<c:forEach items="${jelovnici}" var="jelovnik">
							<c:if test="${restoran.jelovnik.idJelovnika == jelovnik.idJelovnika}">
								<option value="${jelovnik.idJelovnika}" selected="selected">${jelovnik.nazivJelovnik}</option>
							</c:if>
							<c:if test="${restoran.jelovnik.idJelovnika != jelovnik.idJelovnika}">
								<option value="${jelovnik.idJelovnika}" >${jelovnik.nazivJelovnik}</option>
							</c:if>
						</c:forEach>
					</select> 
				</div>
				<div class="form-group"><h5> DRINK CART </h5>   
					<select name="form-drink_cart" class="form-drink_cart form-control" id="form-drink_cart">
						<c:forEach items="${kartePica}" var="kartaPica">
							<c:if test="${restoran.kartaPica.idKartaPica == kartaPica.idKartaPica}">
								<option value="${kartaPica.idKartaPica}" selected="selected">${kartaPica.nazivKartaPica}</option>
							</c:if>
							<c:if test="${restoran.kartaPica.idKartaPica != kartaPica.idKartaPica}">
								<option value="${kartaPica.idKartaPica}" >${jelovnik.nazivKartaPica}</option>
							</c:if>
						</c:forEach>
					</select> 
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div>
</div></div>
<!-- Container (Menu Section)-->
<div id="menu" class="bg-1"><div class="container"><h3 class="text-center">MENU</h3>
<div class="form-box"><div class="form-top"><h3 class="text-center">MENU</h3></div>
        <div class="form-bottom">
			<c:forEach items="${jelovnici}" var="jelovnik">
			<div class="form-group">
			        <label class="sr-only" for="form-name">Name</label>
			        <input type="text" name="form-name" placeholder=${jelovnik.nazivJelovnik} class="form-name form-control" id="form-name">
			</div>
			</c:forEach>
			<c:forEach items="${jelovnici}" var="jelovnik">
			<div class="form-group" style="width:50%;">
					<label class="sr-only" for="form-date1">From date (dd/mm/yyyy)</label>
				    <input type="text" name="form-date1" placeholder=${jelovnik.datumOdJelovnikString} class="form-date1 form-control" id="form-date1">
		    </div>
			</c:forEach>
			<c:forEach items="${jelovnici}" var="jelovnik">
			<div class="form-group" style="width:50%;">
			        <label class="sr-only" for="form-date2">To date (dd.mm.yyyy)</label>
			        <input type="text" name="form-date2" placeholder=${jelovnik.datumDoJelovnikString} class="form-date2 form-control" id="form-date2">
		    </div>
			</c:forEach>
			<table class="table dataTable table-bordered table-hover" id="menuTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Type</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${jela}" var="jelo">
					<tr>
						<td>${jelo.nazivJela}</td>
						<td>${jelo.opisJela}</td>
						<td>${jelo.cenaJela}</td>
						<td>${jelo.tipJela}</td>	
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div></div>
</div>
<!-- Container (Drink cart Section)-->
<div id="drink_cart" class="bg-1"><div class="container">
<div class="form-box"><div class="form-top"><h3 class="text-center">DRINK CART</h3></div>
        <div class="form-bottom">
			<c:forEach items="${kartePica}" var="kartaPica">
			<div class="form-group">
			        <label class="sr-only" for="form-name">Name</label>
			        <input type="text" name="form-name" placeholder=${kartaPica.nazivKartaPica} class="form-name form-control" id="form-name">
			</div>
			</c:forEach>
			<c:forEach items="${kartePica}" var="kartaPica">
			<div class="form-group">
					<label class="sr-only" for="form-date1">From date (dd/mm/yyyy)</label>
				    <input type="text" name="form-date1" placeholder=${kartaPica.datumOdKartaPicaString} class="form-date1 form-control" id="form-date1">
		    </div>
			</c:forEach>
			<c:forEach items="${kartePica}" var="kartaPica">
			<div class="form-group" >
			        <label class="sr-only" for="form-date2">To date (dd.mm.yyyy)</label>
			        <input type="text" name="form-date2" placeholder=${kartaPica.datumDoKartaPicaString} class="form-date2 form-control" id="form-date2">
		    </div>
			</c:forEach>
			<table class="table dataTable table-bordered table-hover" id="drinkCartTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Type</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${pica}" var="pice">
					<tr>
						<td>${pice.nazivPica}</td>
						<td>${pice.opisPica}</td>
						<td>${pice.cenaPica}</td>
						<td>${pice.tipPica}</td>	
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div>
</div></div>
<!-- Container (Waiters Section) -->
<div id="waiters" class="bg-1"><div class="container">
    <div class="form-box"><div class="form-top"><h3 class="text-center">ALL WAITERS</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="waitersTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Date of birth</th>
						<th>Clothes size</th>
						<th>Shoe size</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${konobari}" var="konobar">
					<tr>
						<td>${konobar.korisnik_ime}</td>
						<td>${konobar.korisnik_prezime}</td>
						<td>${konobar.korisnik_email}</td>
						<td>${konobar.datumRodjenjaKonobarString}</td>
						<td>${konobar.konfekcijskiBrojKonobar}</td>
						<td>${konobar.velicinaObuceKonobar}</td>
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div>
    </div></div>
</div>
<!-- Container (Bartenders Section) -->
<div id="bartenders" class="bg-1"><div class="container">
    <div class="form-box"><div class="form-top"><h3 class="text-center">ALL BARTENDERS</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="bartendersTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Date of birth</th>
						<th>Clothes size</th>
						<th>Shoe size</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${sankeri}" var="sanker">
					<tr>
                        <td>${sanker.korisnik_ime}</td>
						<td>${sanker.korisnik_prezime}</td>
						<td>${sanker.korisnik_email}</td>
						<td>${sanker.datumRodjenjaSankerString}</td>
						<td>${sanker.konfekcijskiBrojSanker}</td>
						<td>${sanker.velicinaObuceSanker}</td>
                    </tr>
				</c:forEach>
				</tbody>
			</table>	
        </div>
    </div></div>
</div>
<!-- Container (Cooks Section) -->
<div id="cooks" class="bg-1"><div class="container">
    <div class="form-box"><div class="form-top"><h3 class="text-center">ALL COOKS</h3></div>
		<div class="form-bottom">
			<table class="table  dataTable table-bordered table-hover" id="cooksTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Date of birth</th>
						<th>Clothes size</th>
						<th>Shoe size</th>
						<th>Type</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${kuvari}" var="kuvar">
					<tr>
                        <td>${kuvar.korisnik_ime}</td>
						<td>${kuvar.korisnik_prezime}</td>
						<td>${kuvar.korisnik_email}</td>
						<td>${kuvar.datumRodjenjaKuvarString}</td>
						<td>${kuvar.konfekcijskiBrojKuvar}</td>
						<td>${kuvar.velicinaObuceKuvar}</td>
						<td>${kuvar.tipKuvar}</td>
                    </tr>
				</c:forEach>
				</tbody>
			</table>	
		</div>
    </div></div>
</div>
<!-- Container (Smene Section) -->
<div id="smene" class="bg-1"><div class="container">
    <div class="form-box"><div class="form-top"><h3 class="text-center">DEFINISANI RASPOERED RADA</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="smeneTable">
				<thead>
					<tr>
						<th>Konobar</th>
						<th>Od</th>
						<th>Do</th>
						<th>Reon</th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${smene}" var="smena">
					<tr>
						<td>${smena.korisnik.korisnik_ime}</td>
						<td>${smena.smena_odString}</td>
						<td>${smena.smena_doString}</td>
						<td>${smena.reon.nazivReona}</td>
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div>
    </div></div>
</div>
<div id="pickers" class="bg-1"><div class="container">
<div class="form-box"><div class="form-top"><h3 class="text-center">ALL PICKERS</h3></div>
<div class="form-bottom">
<ul id="picker-tabs" class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#restaurant_picker">RESTAURANT PICKER</a></li>
  <li><a data-toggle="tab" href="#day_visitation_picker">DAY VISITATION PICKER</a></li>
  <li><a data-toggle="tab" href="#week_visitation_picker">WEEK VISITATION PICKER</a></li>			
</ul>
<div class="tab-content">
<div id="restaurant_picker" class="tab-pane fade in active">

<c:if test="${prihodRestorana == 0.0}">
	<script>toastr.error('U izabranom periodu restoran nije imao prihode!');</script>
</c:if>
<c:if test="${prihodRestorana != 0.0 && prihodRestorana != -1.0}">
	<script>toastr.success('Izvestaj o prihodima restorana je uspesno kreiran!');</script>
</c:if>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; margin: 0% 40% 0% 10%; border: 1px solid #ccc; ">
    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
    <span></span> <b class="caret"></b>
</div>
<script type="text/javascript">
var startDay;
var endDay;
$(function() {

    function cb(start, end) {
		startDay = start.format('DD/MM/YYYY');
		endDay = end.format('DD/MM/YYYY');
        $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		
    }
    cb(moment().subtract(29, 'days'), moment());

    $('#reportrange').daterangepicker({
        ranges: {
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
		"autoApply": true,
		"locale": {
			"format": "DD/MM/YYYY",
		 },
		"linkedCalendars": false
    }, cb);
	
	$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
		window.location.href = "./IzvestajRestoranPrihodiController?startDay=" + startDay + "&endDay=" + endDay;
	});

});
</script>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<div id="day_visitation_picker" class="tab-pane fade">
<c:if test="${brojPosetaDnevni == 0}">
	<script>toastr.error('U izabranom periodu nije bilo poseta!');</script>
</c:if>
<c:if test="${brojPosetaDnevni != 0 && brojPosetaDnevni != -1}">
	<script>toastr.success('Izvestaj o broju poseta na dnevnom nivou je uspesno kreiran!');</script>
</c:if>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div id="day_picker" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; margin: 0% 40% 0% 10%; border: 1px solid #ccc; ">
    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
    <span></span> <b class="caret"></b>
</div>
<script type="text/javascript">
var startTime;
var endTime;
$(function() {

	 function cb(start, end) {
		 startTime = start.format('DD/MM/YYYY H:mm');
		 endTime = end.format('DD/MM/YYYY H:mm');
        $('#day_picker span').html(start.format('MMMM D, YYYY H:mm') + ' - ' + end.format('MMMM D, YYYY H:mm'));
    }
    cb(moment().subtract(29, 'days'), moment());
	
	$('#day_picker').daterangepicker({
		ranges: {
           'Today': [moment(), moment()]
        },
		"timePicker": true,
		"timePicker24Hour": true,
        "timePickerIncrement": 30,
		"autoApply": true,
		"locale": {
			"format": "DD/MM/YYYY H:mm",
		 },
		"linkedCalendars": false
	}, cb);	
	
	$('#day_picker').on('apply.daterangepicker', function(ev, picker) {
		window.location.href = "./DnevniIzvestajPosecenostiController?startTime=" + startTime + "&endTime=" + endTime;
	});
});
</script>  
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<div id="week_visitation_picker" class="tab-pane fade">
<c:if test="${brojPosetaNedeljni == 0}">
	<script>toastr.error('U izabranom periodu nije bilo poseta!');</script>
</c:if>
<c:if test="${brojPosetaNedeljni != 0 && brojPosetaNedeljni != -1}">
	<script>toastr.success('Izvestaj o broju poseta na nedeljnom nivou je uspesno kreiran!');</script>
</c:if>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div id="week_picker" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; margin: 0% 40% 0% 10%; border: 1px solid #ccc; ">
    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
    <span></span> <b class="caret"></b>
</div>
<script type="text/javascript">
var startDate;
var endDate;
$(function() {

	function cb(start, end) {
		startDate = moment(start, 'DD/MM/YYYY').day(0).format('DD/MM/YYYY');
		endDate = moment(start, 'DD/MM/YYYY').day(6).format('DD/MM/YYYY');
        $('#week_picker span').html(moment(start, 'MMMM D, YYYY').day(0).format('MMMM D, YYYY') + ' - ' + moment(start, 'MMMM D, YYYY').day(6).format('MMMM D, YYYY'));
		
    }
    cb(moment().subtract(29, 'days'), moment());
	
	$('#week_picker').daterangepicker({
		ranges: {
		   'Last 7 Days': [moment().subtract(6, 'days'), moment()]
        },
		"linkedCalendars": false
	}, cb);	
	
	$('#week_picker').on('apply.daterangepicker', function(ev, picker) {
		window.location.href = "./NedeljniIzvestajPosecenostiController?startDate=" + startDate + "&endDate=" + endDate;
	});
});
</script>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
</div>
</div>
</div></div>
</div>
<!-- Container (REPORTS Section) -->
<!-- Container (WAITERS FOR REPORT Section) -->
<div id="waiters_report" class="bg-1"><div class="container"><h3 class="text-center">WAITERS</h3>
<c:if test="${brKonobara == fn:length(narudzbine) && ocenaKonobara == -1}">
	<script>toastr.error('Izabrano konobar nije posluzivao goste!');</script>
</c:if>
<c:if test="${brKonobara != fn:length(narudzbine) && ocenaKonobara != -1 && ocenaKonobara != 0}">
	<script>toastr.success('Izvestaj o konobarima  je uspesno kreiran!');</script>
</c:if>
<c:if test="${prihodKonobara == 0.0}">
	<script>toastr.error('Izabrani konobar nije posluzivao goste!');</script>
</c:if>
<c:if test="${prihodKonobara != 0.0 && prihodKonobara != -1.0}">
	<script>toastr.success('Izvestaj o prihodima konobara je uspesno kreiran!');</script>
</c:if>
<div class="form-box"><div class="form-top"><h3 class="text-center">ALL WAITERS</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="waiters_reportTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Last name</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${konobari}" var="konobar">
					<tr>
						<td>${konobar.korisnik_ime}</td>
						<td>${konobar.korisnik_prezime}</td>
						<td><a href="./IzvestajKonobarOcenaController?konobarId=${konobar.korisnik_id}">OCENA</a></td>
						<td><a href="./IzvestajKonobarPrihodiController?konobarId=${konobar.korisnik_id}">PRIHODI</a></td>
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div>
    </div></div>
</div>

<!-- Container (WAITERS FOR REPORT Section) -->
<div id="meal_report" class="bg-1"><div class="container"><h3 class="text-center">MEALS</h3>
<c:if test="${obrociSize == 0 && ocenaJela == -1}">
	<script>toastr.error('Izabrano jelo nije ocenjeno!');</script>
</c:if>
<c:if test="${obrociSize != 0 && obrociSize != -1 && ocenaJela != -1 && ocenaJela != 0}">
	<script>toastr.success('Izvestaj o jelima je uspesno kreiran!');</script>
</c:if>
<div class="form-box"><div class="form-top"><h3 class="text-center">ALL MEALS</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="meals_reportTable">
				<thead>
					<tr>
						<th>Name</th>
						<th></th>
					</tr>
				</thead>
                <tbody>
				<c:forEach items="${jela}" var="jelo">
					<tr>
						<td>${jelo.nazivJela}</td>
						<td><a href="./IzvestajJeloOcenaController?jeloId=${jelo.idJela}">OCENA</a></td>
					</tr> 
				</c:forEach>
				</tbody>
			</table>	
        </div>
    </div>
</div>
</div>
<!-- Container (MEAL RATING Section) -->
<div id="meal_rating" class="bg-1"><div class="container"><h3 class="text-center">MEAL RATING</h3>
<form>
<input id="starOcenaJela" value=${ocenaJela} type="number" class="rating" min=0 max=5 step=1 data-size="xl" disabled="true">
</form>
</div>
<!-- Container (WAITER RATING Section) -->
<div id="waiter_rating" class="bg-1"><div class="container"><h3 class="text-center">WAITER RATING</h3>
<form>
<input id="starOcenaKonobara" value=${ocenaKonobara} type="number" class="rating" min=0 max=5 step=1 data-size="xl" disabled="true">
</form>
</div>
<!-- Container (WAITER INCOME Section) -->
<div id="waiter_income" class="bg-1"><div class="container"><h3 class="text-center">WAITER INCOME</h3>
<div id="donut-konobarPrihod"></div>
<script>
$(function() {
	Morris.Donut({
		element: 'donut-konobarPrihod',
		data: [
			{label: "zaradaJelo", value: ${zaradaJelo}},
			{label: "zaradaPice", value: ${zaradaPice}}
				]
	});
});

</script>
</div>
<!-- Container (RESTAURANT RATING Section) -->
<div id="restaurant_rating" class="bg-1"><div class="container"><h3 class="text-center">RESTAURANT RATING</h3>
<form>
<input id="starOcenaRestorana" value=${ocenaRestorana} type="number" class="rating" min=0 max=5 step=1 data-size="xl" disabled="true">
</form>
</div>
<!-- Container (RESTAURANT INCOME Section) -->
<div id="restaurant_income" class="bg-1"><div class="container"><h3 class="text-center">RESTAURANT INCOME</h3>
<div id="morris-area-chart-income"></div>
<script>
$(function() {

    Morris.Area({
        element: 'morris-area-chart-income',
		
        data: ${json},
        xkey: 'period',
        ykeys: ['cena'],
        labels: ['Cena'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });
});
</script>
</div>
</div>

<!-- Container (DAY VISITATION REPORT Section) -->
<div id="day_visitation_report" class="bg-1"><div class="container"><h3 class="text-center">DAY VISITATION REPORT</h3>
<div id="morris-area-chart-day"></div>
<script>
$(function() {

    Morris.Area({
        element: 'morris-area-chart-day',
		
        data: ${jsonDnevni},
        xkey: 'period',
        ykeys: ['brojGostiju'],
        labels: ['brojGostiju'],
        pointSize: 1,
        hideHover: 'auto',
        resize: true
    });
});
</script>
</div>
</div>
<!-- Container (WEEK VISITATION REPORT Section) -->
<div id="week_visitation_report" class="bg-1"><div class="container"><h3 class="text-center">WEEK VISITATION REPORT</h3>
<div id="morris-area-chart-week"></div>
<script>
$(function() {

    Morris.Area({
        element: 'morris-area-chart-week',
		
        data: ${jsonNedeljni},
        xkey: 'period',
        ykeys: ['brojGostiju'],
        labels: ['brojGostiju'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });
});
</script>
</div>
</div>
<div id="necessities" class="bg-1"><div class="container"><h3 class="text-center">NEEDED GROCERIES</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">NEEDED GROCERIES</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="groceriesTable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Quantity</th>
						<th>Delivery time</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${namirnice}" var="namirnica">
				<c:if test="${namirnica.izabranaPonuda == null}">
					<tr>
                        <td>${namirnica.nazivNamirnice}</td>
						<td>${namirnica.opis_namirnice}</td>
						<td>${namirnica.kolicinaNamirnice}</td>
						<td>${namirnica.rokNamirniceString}</td>
                    </tr>
					</c:if> 
				</c:forEach>					
				</tbody>
			</table>	
        </div>
    </div></div>
</div>
<!-- Container (SUPPLIES Section) -->
<c:if test="${preuzetaPonuda == 1}">
	<script>toastr.succsess('Ponuda uspesno izabrana!');</script>
</c:if>
<div id="supplies" class="bg-1"><div class="container"><h3 class="text-center">SUPPLIES</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">SUPPLIES</h3></div>
        <div class="form-bottom">
			<table class="table dataTable table-bordered table-hover" id="suppliesTable">
				<thead>
					<tr>
						<th>Grocery id</th>
						<th>Price</th>
						<th>Delivery time</th>
						<th>Supplier</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ponude}" var="ponuda">
					<c:if test="${ponuda.ponudaStanje == true && ponuda.izabrana == false}">
					<tr>
                        <td>${ponuda.namirnice.nazivNamirnice}</td>
						<td>${ponuda.cenaPonuda}</td>
						<td>${ponuda.ponudaRokIsporukeString}</td>
						<td>${ponuda.ponudjac.korisnik_ime}</td>
						<td><a href="./TakeSupplyController?ponudaId=${ponuda.idPonuda}&namirnicaId=${ponuda.namirnice.idNamirnice}">Take supply</a></td>
					</tr>
					</c:if> 
				</c:forEach>									 
				</tbody>
			</table>	
        </div>
    </div></div>
</div>
<!-- PADAJUCI MENI -->
<!-- Container (Add new waiters Section) -->
<div id="add_new_waiter" class="bg-1"><div class="container"><h3 class="text-center">WAITERS</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">WAITERS UPDATE</h3></div>
	    <div class="form-bottom">
			<form action="./CreateKonobarController" method="post" role="form" class="waiter_update" accept-charset="ISO-8859-1">
				<div id="form-name-waiter-group" class="form-group">
			        <label class="sr-only" for="form-name-waiter">Name</label>
			        <input type="text" name="form-name-waiter" placeholder="Name" class="form-name-waiter form-control" id="form-name-waiter" pattern="[A-Za-z]{4,15}">
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-lastname-waiter">Last name</label>
				    <input type="text" name="form-lastname-waiter" placeholder="Last name" class="form-lastname-waiter form-control" id="form-lastname-waiter" pattern="[A-Za-z]{4,15}">
			    </div>
			    <div class="form-group">
			        <label class="sr-only" for="form-date-waiter">Date of birth (dd/mm/yyyy)</label>
			        <input type="text" name="form-date-waiter" placeholder="Date of birth (dd/mm/yyyy)" class="form-date-waiter form-control" id="form-date-waiter" pattern="[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-email-waiter">Email</label>
			        <input type="text" name="form-email-waiter" placeholder="Email (username@mail.com)" class="form-email-waiter form-control" id="form-email-waiter" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-password-waiter">Password</label>
			        <input type="password" name="form-password-waiter" placeholder="Password" class="form-password-waiter form-control" id="form-password-waiter" pattern=".{6,}">
			    </div>
				<div class="form-group"><h5> CLOTHES SIZE </h5>   
					<select name="form-clothes-size-waiter" class="form-clothes-size-waiter form-control" id="form-clothes-size-waiter">
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="L">L</option>
						<option value="XL">XL</option>
						<option value="XXL">XXL</option>
					</select> 
			    </div>
				<div class="form-group"><h5> SHOE SIZE </h5>   
					<select name="form-shoe-size-waiter" class="form-shoe-size-waiter form-control" id="form-shoe-size-waiter">
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
					</select> 
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div><div>
</div>
<!-- Container (Add new bartender Section) -->
<div id="add_new_bartender" class="bg-1"><div class="container"><h3 class="text-center">BARTENDERS</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">BARTENDER UPDATE</h3></div>
	    <div class="form-bottom">
			<form action="./CreateSankerController" method="post" class="bartender_update" accept-charset="ISO-8859-1"> 
			    <div class="form-group">
			        <label class="sr-only" for="form-name-bartender">Name</label>
			        <input type="text" name="form-name-bartender" placeholder="Name" class="form-name-bartender form-control" id="form-name-bartender" pattern="[A-Za-z]{4,15}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-lastname-bartender">Last name</label>
			        <input type="text" name="form-lastname-bartender" placeholder="Last name" class="form-lastname-bartender form-control" id="form-lastname-bartender" pattern="[A-Za-z]{4,15}">
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-date-bartender">Date of birth (dd/mm/yyyy)</label>
				    <input type="text" name="form-date-bartender" placeholder="Date of birth (dd/mm/yyyy)" class="form-date-bartender form-control" id="form-date-bartender" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-email-bartender">Email</label>
			        <input type="text" name="form-email-bartender" placeholder="Email" class="form-email-bartender form-control" id="form-email-bartender" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-password-bartender">Password</label>
			        <input type="password" name="form-password-bartender" placeholder="Password" class="form-password-bartender form-control" id="form-password-bartender" pattern=".{6,}">
			    </div>
			    <div class="form-group"><h5> CLOTHES SIZE </h5>   
					<select name="form-clothes-size-bartender" class="form-clothes-size-bartender form-control" id="form-clothes-size-bartender">
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="L">L</option>
						<option value="XL">XL</option>
						<option value="XXL">XXL</option>
					</select> 
			    </div>
				<div class="form-group"><h5> SHOE SIZE </h5>   
					<select name="form-shoe-size-bartender" class="form-shoe-size-bartender form-control" id="form-shoe-size-bartender">
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
					</select> 	
				</div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Add new cook Section) -->
<div id="add_new_cook" class="bg-1"><div class="container"><h3 class="text-center">COOKS</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">COOK UPDATE</h3></div>
	    <div class="form-bottom">
			<form action="./CreateKuvarController" method="post" class="cook_update" accept-charset="ISO-8859-1"> 
			    <div class="form-group">
			        <label class="sr-only" for="form-name-cook">Name</label>
			        <input type="text" name="form-name-cook" placeholder="Name" class="form-name-cook form-control" id="form-name-cook" pattern="[A-Za-z]{4,20}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-lastname-cook">Last name</label>
			        <input type="text" name="form-lastname-cook" placeholder="Last name" class="form-lastname-cook form-control" id="form-lastname-cook" pattern="[A-Za-z]{4,20}">
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-date-cook">Date of birth (dd/mm/yyyy)</label>
				    <input type="text" name="form-date-cook" placeholder="Date of birth (dd/mm/yyyy)" class="form-date-cook form-control" id="form-date-cook" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-email-cook">Email</label>
			        <input type="text" name="form-email-cook" placeholder="Email" class="form-email-cook form-control" id="form-email-cook" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-password-cook">Password</label>
			        <input type="password" name="form-password-cook" placeholder="Password" class="form-password-cook form-control" id="form-password-cook" pattern=".{6,}">
			    </div>
			    <div class="form-group"><h5> CLOTHES SIZE </h5>   
					<select name="form-clothes-size" class="form-clothes-size form-control" id="form-clothes-size">
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="L">L</option>
						<option value="XL">XL</option>
						<option value="XXL">XXL</option>
					</select> 
			    </div>
				<div class="form-group"><h5> SHOE SIZE </h5>   
					<select name="form-shoe-size" class="form-shoe-size form-control" id="form-shoe-size">
						<option value="36">36</option>
						<option value="37">37</option>
						<option value="38">38</option>
						<option value="39">39</option>
						<option value="40">40</option>
						<option value="41">41</option>
						<option value="42">42</option>
						<option value="43">43</option>
						<option value="44">44</option>
						<option value="45">45</option>
						<option value="46">46</option>
						<option value="47">47</option>
						<option value="48">48</option>
						<option value="49">49</option>
					</select> 	
				</div>
				 <div class="form-group"><h5> TYPE </h5>   
					<select name="form-type" class="form-type form-control" id="form-type">
						<option value="ZA_SALATE">ZA SALATE</option>
						<option value="ZA_GLAVNA_JELA">ZA GLAVNA JELA</option>
						<option value="ZA_PREDJELA">ZA PREDJELA</option>
						<option value="ZA_DESERTE">ZA DEZERTE</option>
						<option value="ZA_SUPE">ZA SUPE</option>
						<option value="ZA_JELA_SA_ROSTILJA">ZA JELA SA ROSTILJA</option>
					</select> 
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Add new supplier Section) -->
<div id="add_new_supplier" class="bg-1"><div class="container"><h3 class="text-center">SUPPLIER</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">SUPPLIER UPDATE</h3></div>
	    <div class="form-bottom">
			<form action="./CreatePonudjacController" method="post" class="supplier_update" accept-charset="ISO-8859-1"> 
			    <div class="form-group">
			        <label class="sr-only" for="form-name-supplier">Name</label>
			        <input type="text" name="form-name-supplier" placeholder="Name" class="form-name-supplier form-control" id="form-name-supplier" pattern="[A-Za-z]{4,20}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-lastname-supplier">Last name</label>
			        <input type="text" name="form-lastname-supplier" placeholder="Last name" class="form-lastname-supplier form-control" id="form-lastname-supplier" pattern="[A-Za-z]{4,20}">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-email-supplier">Email</label>
			        <input type="text" name="form-email-supplier" placeholder="Email" class="form-email-supplier form-control" id="form-email-supplier" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-password-supplier">Password</label>
			        <input type="password" name="form-password-supplier" placeholder="Password" class="form-password-supplier form-control" id="form-password-supplier" pattern=".{6,}">
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Add new menu Section) -->
<div id="add_new_menu" class="bg-1"><div class="container"><h3 class="text-center">MENU</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">MENU UPDATE</h3></div>
	    <div class="form-bottom">
			<form action="./CreateJelovnikController" method="post" role="form" class="menu_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
					<label class="sr-only" for="form-name-menu">Name</label>
				    <input type="text" name="form-name-menu" placeholder="Name" class="form-name-menu form-control" id="form-name-menu" pattern="[A-Za-z]{4,20}">
			    </div>
			    <div class="form-group">
			        <label class="sr-only" for="form-date1-menu">From date (dd/mm/yyyy)</label>
			        <input type="text" name="form-date1-menu" placeholder="From date (dd/mm/yyyy)" class="form-date1-menu form-control" id="form-date1-menu" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
				</div>
				<div class="form-group">
			        <label class="sr-only" for="form-date2-menu">To date (dd.mm.yyyy)</label>
			        <input type="text" name="form-date2-menu" placeholder="To date (dd.mm.yyyy)" class="form-date2-menu form-control" id="form-date2-menu" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div>
	</div>
</div>
<!-- Container (Add new drink cart Section) -->
<div id="add_new_drink_cart" class="bg-1"><div class="container"><h3 class="text-center">DRINK CART</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">DRINK CART UPDATE</h3></div>
		<div class="form-bottom">
			<form action="./CreateKartaPicaController" method="post" role="form" class="drink_cart_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
			        <label class="sr-only" for="form-name-drink_cart">Name</label>
			        <input type="text" name="form-name-drink_cart" placeholder="Name" class="form-name-drink_cart form-control" id="form-name-drink_cart" pattern="[A-Za-z]{4,20}">
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-date1-drink_cart">From date (dd/mm/yyyy)</label>
				    <input type="text" name="form-date1-drink_cart" placeholder="From date (dd/mm/yyyy)" class="form-date1-drink_cart form-control" id="form-date1-drink_cart" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-date2-drink_cart">To date (dd.mm.yyyy)</label>
			        <input type="text" name="form-date2-drink_cart" placeholder="To date (dd.mm.yyyy)" class="form-date2-drink_cart form-control" id="form-date2-drink_cart" pattern="\[0-9]{1,2}/\[0-9]{1,2}/\[0-9]{4}">
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Add new meal Section) -->
<div id="add_new_meal" class="bg-1"><div class="container"><h3 class="text-center">MEAL</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">MEAL UPDATE</h3></div>
		<div class="form-bottom">
			<form action="./CreateJeloController" method="post" role="form" class="meal_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
			        <label class="sr-only" for="form-name-meal">Name</label>
			        <input type="text" name="form-name-meal" placeholder="Name" class="form-name-meal form-control" id="form-name-meal" pattern="[A-Za-z]{4,20}">
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-desc-meal">Description</label>
				    <input type="text" name="form-desc-meal" placeholder="Description" class="form-desc-meal form-control" id="form-desc-meal" pattern="[A-Za-z]{7,20}">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-price-meal">Price</label>
			        <input type="text" name="form-price-meal" placeholder="Price" class="form-price-meal form-control" id="form-price-meal" pattern="[0-9]{1,6}">
			    </div>
				<div class="form-group"><h5> TYPE </h5>   
					<select name="form-type" class="form-type form-control" id="form-type">
						<option value="DEZERT">DEZERT</option>
						<option value="SUPA">SUPA</option>
						<option value="SALATA">SALATA</option>
						<option value="PREDJELO">PREDJELO</option>
						<option value="GLAVNO JELO">GLAVNO JELO</option>	
					</select> 
			    </div>
				<div class="form-group"><h5> MENU </h5>   
					<select name="form-menu" class="form-menu form-control" id="form-menu">
						<c:forEach items="${jelovnici}" var="jelovnik">
						<option value=${jelovnik.idJelovnika}>${jelovnik.nazivJelovnik}</option>
						</c:forEach>
					</select> 
			    </div>
				<div class="form-group"><h5> DRINK CART </h5>   
					<select name="form-drink_cart" class="form-drink_cart form-control" id="form-drink_cart">
						<c:forEach items="${kartePica}" var="kartaPica">
						<option value=${kartaPica.idKartaPica}>${kartaPica.nazivKartaPica}</option>
						</c:forEach>
					</select> 
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>	
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Add new drink Section) -->
<div id="add_new_drink" class="bg-1"><div class="container"><h3 class="text-center">DRINK</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">DRINK UPDATE</h3></div>
		<div class="form-bottom">
			<form action="./CreatePiceController" method="post" class="drink_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
			        <label class="sr-only" for="form-name-drink">Name</label>
			        <input type="text" name="form-name-drink" placeholder="Name" class="form-name-drink form-control" id="form-name-drink" pattern="[A-Za-z]{4,20}">
			    </div>
				<div class="form-group">
					<label class="sr-only" for="form-desc-drink">Description</label>
				    <input type="text" name="form-desc-drink" placeholder="Description" class="form-desc-drink form-control" id="form-desc-drink" pattern="[A-Za-z]{7,20}">
			    </div>
				<div class="form-group">
			        <label class="sr-only" for="form-price-drink">Price</label>
			        <input type="text" name="form-price-drink" placeholder="Price" class="form-price-drink form-control" id="form-price-drink" pattern="[0-9]{1,6}">
			    </div>
				<div class="form-group"><h5> TYPE </h5>   
					<select name="form-type" class="form-type form-control" id="form-type">
						<option value="BEZALKOHOLNO">BEZALKOHOLNO</option>
						<option value="ALKOHOL">ALKOHOL</option>
						<option value="VODA">VODA</option>
						<option value="PIVO">PIVO</option>
						<option value="VINO">VINO</option>
					</select> 
			    </div>
				<div class="form-group"><h5> DRINK CART </h5>   
					<select name="form-drink_cart" class="form-drink_cart form-control" id="form-drink_cart">
						<c:forEach items="${kartePica}" var="kartaPica">
						<option value=${kartaPica.idKartaPica}>${kartaPica.idKartaPica}</option>
						</c:forEach>
					</select> 
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Add new necessity Section) -->
<div id="add_new_necessity" class="bg-1"><div class="container"><h3 class="text-center">NECESSITY</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">NECESSITY UPDATE</h3></div>
	    <div class="form-bottom">
			<form action="./CreateNamirniceController" method="get" role="form" class="necessity_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
					<label class="sr-only" for="form-name-necessity">Name</label>
				    <input type="text" name="form-name-necessity" placeholder="Name" class="form-name-necessity form-control" id="form-name-necessity" pattern="[A-Za-z]{4,20}">
				</div>
				<div class="form-group">
					<label class="sr-only" for="form-desc-necessity">Description</label>
				    <input type="text" name="form-desc-necessity" placeholder="Description" class="form-desc-necessity form-control" id="form-desc-necessity" pattern="[A-Za-z]{7,20}">
				</div>
				<div class="form-group">
					<label class="sr-only" for="form-quantity-necessity">Quantity</label>
				    <input type="text" name="form-quantity-necessity" placeholder="Quantity " class="form-quantity-necessity form-control" id="form-quantity-necessity" pattern="[0-9]{1,4}">
				</div>
				<div class="form-group">
				    <label class="sr-only" for="form-time-necessity">Time limit (dd/mm/yyyy)</label>
					<input type="text" name="form-time-necessity" placeholder="Time limit (dd/mm/yyyy)" class="form-time-necessity form-control" id="form-time-necessity" >
				</div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button> 
			</form>
		</div>
	</div></div>
</div>
<!-- Container (Update restaurant Section) -->
<div id="update_restaurant" class="bg-1"><div class="container"><h3 class="text-center">RESTAURANT</h3>
	<div class="form-box"><div class="form-top"><h3 class="text-center">UPDATE RESTAURANT</h3></div>
	    <div class="form-bottom">
			<form action="./UpdateRestoranController" method="post" role="form" class="restaurant_update" accept-charset="ISO-8859-1">
			    <div class="form-group">
					<label class="sr-only" for="form-name-restoran">Name</label>
				    <input type="text" value="${restoran.nazivRestoran}" name="form-name-restoran" class="form-name-restoran form-control" id="form-name-restoran" pattern="[A-Za-z]{4,20}" >
				</div>
				<div class="form-group"><h5> TYPE </h5>   
					<select name="form-type" class="form-type form-control" id="form-type">
						<option value="DOMACA KUHINJA">DOMACA KUHINJA</option>
						<option value="BANATSKA KUHINJA">DOMACA KUHINJA</option>
						<option value="INTERNACIONALNA KUHINJA">INTERNACIONALNA</option>
						<option value="KINESKA KUHINJA">KINESKA KUHINJA</option>
						<option value="ITALIJANSKA KUHINJA">ITALIJANSKA KUHINJA</option>
						<option value="MEDITERANSKA KUHINJA">MEDITERANSKA KUHINJA</option>
						<option value="HRONO KUHINJA">HRONO KUHINJA</option>
						<option value="VEGANSKA KUHINJA">VEGANSKA KUHINJA</option>
					</select> 
			    </div>
				<div class="form-group"><h5> MENU </h5>   
					<select name="form-menu" class="form-menu form-control" id="form-menu">
						<c:forEach items="${jelovnici}" var="jelovnik">
							<c:if test="${restoran.jelovnik.idJelovnika == jelovnik.idJelovnika}">
								<option value="${jelovnik.idJelovnika}" selected="selected">${jelovnik.nazivJelovnik}</option>
							</c:if>
							<c:if test="${restoran.jelovnik.idJelovnika != jelovnik.idJelovnika}">
								<option value="${jelovnik.idJelovnika}" >${jelovnik.nazivJelovnik}</option>
							</c:if>
						</c:forEach>
					</select> 
					<div class="form-group"><h5> DRINK CART </h5>   
					<select name="form-drink_cart" class="form-drink_cart form-control" id="form-drink_cart">
						<c:forEach items="${kartePica}" var="kartaPica">
							<c:if test="${restoran.kartaPica.idKartaPica == kartaPica.idKartaPica}">
								<option value="${kartaPica.idKartaPica}" selected="selected">${kartaPica.nazivKartaPica}</option>
							</c:if>
							<c:if test="${restoran.kartaPica.idKartaPica != kartaPica.idKartaPica}">
								<option value="${kartaPica.idKartaPica}" >${kartaPica.nazivKartaPica}</option>
							</c:if>
						</c:forEach>
					</select> 
			    </div>
			    </div>
				<button type="submit" class="btn">UPDATE</button><button type="reset" class="btn">CANCEL</button>
			</form>
		</div>
	</div></div>
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
</script>
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
	
	$('#waitersTable').DataTable({
                responsive: true
        });
	$('#bartendersTable').DataTable({
                responsive: true
        });
	$('#cooksTable').DataTable({
                responsive: true
        });
	$('#groceriesTable').DataTable({
                responsive: true
        });
	$('#suppliesTable').DataTable({
                responsive: true
        });
	$('#menuTable').DataTable({
                responsive: true
        });
	$('#drinkCartTable').DataTable({
                responsive: true
        });
	$('#tableTable').DataTable({
                responsive: true
        });
	$('#waiters_reportTable').DataTable({
                responsive: true
        });
	$('#meals_reportTable').DataTable({
                responsive: true
        });
	$('#smeneTable').DataTable({
                responsive: true
        });
	
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
	//add_new_waiter - TOASTR FUNKCIJE
	$("#form-name-waiter").on('change',function(){
		if(document.getElementById("form-name-waiter").validity.patternMismatch){
			toastr.error('Uneto ime konobara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-waiter").value == ""){
			toastr.error('Niste uneli ime konobara!');
		}
	});
	$("#form-lastname-waiter").on('change',function(){
		if(document.getElementById("form-lastname-waiter").validity.patternMismatch){
			toastr.error('Uneto prezime konobara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-lastname-waiter!").value == ""){
			toastr.error('Niste uneli prezime konobara!');
		}
	});
	$("#form-date-waiter").on('change',function(){
		if(document.getElementById("form-date-waiter!").validity.patternMismatch){
			toastr.error('Uneti datum rodjenja konobara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date-waiter!").value == ""){
			toastr.error('Niste uneli datum rodjenja konobara!');
		}
	});
	$("#form-email-waiter").on('change',function(){
		if(document.getElementById("form-email-waiter").validity.patternMismatch){
			toastr.error('Uneti email konobara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-email-waiter").value == ""){
			toastr.error('Niste uneli email konobara!');
		}
	});
	$("#form-password-waiter").on('change',function(){
		if(document.getElementById("form-password-waiter").validity.patternMismatch){
			toastr.error('Uneti password konobara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-password-waiter").value == ""){
			toastr.error('Niste uneli password konobara!');
		}
	});
 
	//add_new_bartender
	$("#form-name-bartender").on('change',function(){
		if(document.getElementById("form-name-bartender").validity.patternMismatch){
			toastr.error('Uneto ime sankera nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-bartender").value == ""){
			toastr.error('Niste uneli ime sankera!');
		}
	});
	$("#form-lastname-bartender").on('change',function(){
		if(document.getElementById("form-lastname-bartender").validity.patternMismatch){
			toastr.error('Uneto prezime sankera nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-lastname-bartender").value == ""){
			toastr.error('Niste uneli prezime sankera!');
		}
	});
	$("#form-date-bartender").on('change',function(){
		if(document.getElementById("form-date-bartender").validity.patternMismatch){
			toastr.error('Uneti datum rodjenja sankera nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date-bartender").value == ""){
			toastr.error('Niste uneli datum rodjenja sankera!');
		}
	});
	$("#form-email-bartender").on('change',function(){
		if(document.getElementById("form-email-bartender").validity.patternMismatch){
			toastr.error('Uneti email sankera nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-email-bartender").value == ""){
			toastr.error('Niste uneli email sankera!');
		}
	});
	$("#form-password-bartender").on('change',function(){
		if(document.getElementById("form-email-bartender").validity.patternMismatch){
			toastr.error('Uneti password sankera nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-password-bartender").value == ""){
			toastr.error('Niste uneli password sankera!');
		}
	});
	//add_new_cook
	$("#form-name-cook").on('change',function(){
		if(document.getElementById("form-name-cook").validity.patternMismatch){
			toastr.error('Uneto ime kuvara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-cook").value == ""){
			toastr.error('Niste uneli ime kuvara!');
		}
	});
	$("#form-lastname-cook").on('change',function(){
		if(document.getElementById("form-lastname-cook").validity.patternMismatch){
			toastr.error('Uneto prezime kuvara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-lastname-cook").value == ""){
			toastr.error('Niste uneli prezime kuvara!');
		}
	});
	$("#form-date-cook").on('change',function(){
		if(document.getElementById("form-date-cook").validity.patternMismatch){
			toastr.error('Uneti datum rodjenja kuvara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date-cook").value == ""){
			toastr.error('Niste uneli datum rodjenja kuvara!');
		}
	});
	$("#form-email-cook").on('change',function(){
		if(document.getElementById("form-email-cook").validity.patternMismatch){
			toastr.error('Uneti email kuvara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-email-cook").value == ""){
			toastr.error('Niste uneli email kuvara!');
		}
	});
	$("#form-password-cook").on('change',function(){
		if(document.getElementById("form-password-cook").validity.patternMismatch){
			toastr.error('Uneti password kuvara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-password-cook").value == ""){
			toastr.error('Niste uneli password kuvara!');
		}
	});
	//add_new_supplier - TOASTR FUNKCIJE
	$("#form-name-supplier").on('change',function(){
		if(document.getElementById("form-name-supplier").validity.patternMismatch){
			toastr.error('Uneto ime ponudjaca nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-supplier").value == ""){
			toastr.error('Niste uneli ime ponudjaca!');
		}
	});
	$("#form-lastname-supplier").on('change',function(){
		if(document.getElementById("form-lastname-supplier").validity.patternMismatch){
			toastr.error('Uneto prezime ponudjaca nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-lastname-supplier").value == ""){
			toastr.error('Niste uneli prezime ponudjaca!');
		}
	});
	$("#form-email-supplier").on('change',function(){
		if(document.getElementById("form-email-supplier").validity.patternMismatch){
			toastr.error('Uneti email ponudjaca nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-email-supplier").value == ""){
			toastr.error('Niste uneli email ponudjaca!');
		}
	});
	$("#form-password-supplier").on('change',function(){
		if(document.getElementById("form-password-supplier").validity.patternMismatch){
			toastr.error('Uneti password kuvara nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-password-supplier").value == ""){
			toastr.error('Niste uneli password ponudjaca!');
		}
	});
	//add_new_menu - TOASTR FUNKCIJE
	$("#form-name-menu").on('change',function(){
		if(document.getElementById("form-name-menu").validity.patternMismatch){
			toastr.error('Uneti naziv jelovnika nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-menu").value == ""){
			toastr.error('Niste uneli naziv jelovnika!');
		}
	});
	$("#form-date1-menu").on('change',function(){
		if(document.getElementById("form-date1-menu").validity.patternMismatch){
			toastr.error('Uneti datum vazenja jelovnika nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date1-menu").value == ""){
			toastr.error('Niste uneli datum vazenja jelovnika!');
		}
	});
	$("#form-date2-menu").on('change',function(){
		if(document.getElementById("form-date2-menu").validity.patternMismatch){
			toastr.error('Uneti datum vazenja jelovnika nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date2-menu").value == ""){
			toastr.error('Niste datum vazenja jelovnika!');
		}
	});
	//add_new_drink_cart - TOASTR FUNKCIJE
	$("#form-name-drink_cart").on('change',function(){
		if(document.getElementById("form-name-drink_cart").validity.patternMismatch){
			toastr.error('Uneti naziv karte pica nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-drink_cart").value == ""){
			toastr.error('Niste uneli naziv karte pica!');
		}
	});
	$("#form-date1-drink_cart").on('change',function(){
		if(document.getElementById("form-date1-drink_cart").validity.patternMismatch){
			toastr.error('Uneti datum vazenja karte pica nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date1-drink_cart").value == ""){
			toastr.error('Niste uneli datum vazenja karte pica!');
		}
	});
	$("#form-date2-drink_cart").on('change',function(){
		if(document.getElementById("form-date2-drink_cart").validity.patternMismatch){
			toastr.error('Uneti datum vazenja karte pica nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-date2-drink_cart").value == ""){
			toastr.error('Niste uneli datum vazenja karte pica!');
		}
	});
	//add_new_meal
	$("#form-name-meal").on('change',function(){
		if(document.getElementById("form-name-meal").validity.patternMismatch){
			toastr.error('Uneti naziv jela nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-meal").value == ""){
			toastr.error('Niste uneli naziv jela!');
		}
	});
	$("#form-desc-meal").on('change',function(){
		if(document.getElementById("form-desc-meal").validity.patternMismatch){
			toastr.error('Uneti opis jela nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-desc-meal").value == ""){
			toastr.error('Niste uneli opis jela!');
		}
	});
	$("#form-price-meal").on('change',function(){
		if(document.getElementById("form-price-meal").validity.patternMismatch){
			toastr.error('Uneta cena jela nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-price-meal").value == ""){
			toastr.error('Niste uneli cenu jela!');
		}
	});
	//add_new_drink
	$("#form-name-drink").on('change',function(){
		if(document.getElementById("form-name-drink").validity.patternMismatch){
			toastr.error('Uneti naziv pica nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-drink").value == ""){
			toastr.error('Niste uneli naziv pica!');
		}
	});
	$("#form-desc-drink").on('change',function(){
		if(document.getElementById("form-desc-drink").validity.patternMismatch){
			toastr.error('Uneti opis pica nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-desc-drink").value == ""){
			toastr.error('Niste uneli opis pica!');
		}
	});
	$("#form-price-drink").on('change',function(){
		if(document.getElementById("form-price-drink").validity.patternMismatch){
			toastr.error('Uneta cena pica nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-price-drink").value == ""){
			toastr.error('Niste uneli cenu pica!');
		}
	});
	//add_new_necessity
	$("#form-name-necessity").on('change',function(){
		if(document.getElementById("form-name-necessity").validity.patternMismatch){
			toastr.error('Uneti naziv namirnice nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-necessity").value == ""){
			toastr.error('Niste uneli naziv namirnice!');
		}
	});
	$("#form-desc-necessity").on('change',function(){
		if(document.getElementById("form-desc-necessity").validity.patternMismatch){
			toastr.error('Uneti opis namirnice nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-desc-necessity").value == ""){
			toastr.error('Niste uneli opis namirnice!');
		}
	});
	$("#form-quantity-necessity").on('change',function(){
		if(document.getElementById("form-quantity-necessity").validity.patternMismatch){
			toastr.error('Uneta kolicina namirnice nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-quantity-necessity").value == ""){
			toastr.error('Niste uneli kolicinu namirnice!');
		}
	});
	$("#form-time-necessity").on('change',function(){
		if(document.getElementById("form-time-necessity").validity.patternMismatch){
			toastr.error('Uneti rok za nabavku namirnice nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-time-necessity").value == ""){
			toastr.error('Niste uneli rok za nabavku namirnice!');
		}
	});
	//update_restaurant
	$("#form-name-restoran").on('change',function(){
		if(document.getElementById("form-name-restoran").validity.patternMismatch){
			toastr.error('Uneti naziv restorana nije u odgovarajucem formatu!');
		}
		if(document.getElementById("form-name-restoran").value == ""){
			toastr.error('Niste uneli naziv restorana!');
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
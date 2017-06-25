<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurants</title>
		<meta charset="utf-8">
		<link href="style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> 
		addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
		function hideURLbar(){ window.scrollTo(0,1); } 
		
		var image1 = new Image()
		image1.src = "images/slika1.jpg"
		var image2 = new Image()
		image2.src = "images/slika2.jpg"
		var image3 = new Image()
		image3.src = "images/slika3.jpg"
		var image4 = new Image()
		image4.src = "images/slika4.jpg"
		var image5 = new Image()
		image5.src = "images/slika5.jpg"
		</script>
		<!--webfonts-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet' type='text/css'>
		<!--//webfonts-->
</head>
<body>
<c:if test="${(sessionScope.wrong!=null) && (sessionScope.gost==null)}">
		<script> toastr.error('Wrong username or password! Try again.'); </script>
	</c:if>
	

<form action="./LoginController" method="post" class="prijavaForma" accept-charset="ISO-8859-1">
		
	<script type="text/javascript">
					var step=1;
					function slideit()
					{
						document.body.background = eval("image"+step+".src");
						if(step<5)
							step++;
						else
							step=1;
						setTimeout("slideit()",4000);
					}
					slideit();
				</script>
				 <!-----start-main---->
				 <div class="logo">
					<img src="images/icon3.png" style="width:304px;height:215px;">
				</div>
		
				<div class="login-form">
						<h1>Welcome!</h1>
						<h2><a href="./RedirectToRegisterController">Sign up</a></h2>
						<form>
							<li style="top: 170px; left: 50px; margin-bottom:30px;">
								<input type="text" class="text" name="email_korisnika" value="email"   onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'email';}" ><a href="#" class=" icon user" ></a>
							</li>
							<li style="top: 250px; left: 50px">
								<input type="password" name="lozinka_korisnika" value="Password"   onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"><a href="#" class=" icon lock" ></a>
							</li>
							
							 <div class ="forgot">
							 <li style="top: 350px; left: 50px; background: none; border:none;">
								<input type="submit" onclick="myFunction()" value="Log in" > <a href="#" class=" icon arrow"></a>                                                                                                                                                                                                                                 </h4>
							</li>
							</div>
								
						</form>
						
						
				</div>
						
				
			<!--//End-login-form-->
					<div class="ad728x90" style="text-align:center">
				<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- w3layouts_demo_728x90 -->
				<ins class="adsbygoogle"
				     style="display:inline-block;width:728px;height:90px"
				     data-ad-client="ca-pub-9153409599391170"
				     data-ad-slot="8639520288"></ins>
				<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
		   </div>
	
</form>
<script type="text/javascript">
function showMessage() {
	toastr.error('Mars');
}
</script>
</body>
</html>
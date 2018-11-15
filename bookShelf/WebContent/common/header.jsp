<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<!-- <head>
	Required meta tags
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" href="img/favicon.png" type="image/png">
	<title>Fashiop</title>
	Bootstrap CSS
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="vendors/linericon/style.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
	<link rel="stylesheet" href="vendors/lightbox/simpleLightbox.css">
	<link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
	<link rel="stylesheet" href="vendors/animate-css/animate.css">
	<link rel="stylesheet" href="vendors/jquery-ui/jquery-ui.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	main css
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/responsive.css">
</head> -->

<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" href="img/favicon.png" type="image/png">
	<title>Fashiop</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="vendors/linericon/style.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
	<link rel="stylesheet" href="vendors/lightbox/simpleLightbox.css">
	<link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
	<link rel="stylesheet" href="vendors/animate-css/animate.css">
	<link rel="stylesheet" href="vendors/jquery-ui/jquery-ui.css">
	<!-- main css -->
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/responsive.css">

</head>

<body>

		<!--================Header Menu Area =================-->
	<header class="header_area">
		<div class="top_menu row m0">
			<div class="container-fluid">
				<div class="float-left">											
					<%-- <p>Hi. ${username} </p> --%>
					<% if  (request.getSession().getAttribute("name")==null) {%>
					<p>Hi. </p> 
					<% }else {%>
 					<p>Hi. <%=request.getSession().getAttribute("name") %></p> 
 					<% }%>
				</div>
				<div class="float-right">
					<ul class="right_side">
						<li>
							<a href="login.jsp">
								Login/Register
							</a>
						</li>
						<li>
							<a href="#">
								My Account
							</a>
						</li>
						<li>
							<a href="contact.html">
								Contact Us
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="autoheight navbar-brand logo_h" href="index.jsp">
						<img src="img/logo.png" alt="">
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
					 aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<div class="row w-100">
							<div class="col-lg-7 pr-0">
								<ul class="nav navbar-nav center_nav pull-right">
									<li class="nav-item active">
										<a class="nav-link" href="index.jsp">Home</a>
									</li>
									<li class="nav-item submenu dropdown">
										<a href="showallbook.jsp" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Book</a>
										<ul class="dropdown-menu">
											<li class="nav-item">
												<a class="nav-link" href="showallbook.jsp">View Book</a>
												<li class="nav-item">
													
													<li class="nav-item">
														<a class="nav-link" href="uploadbook.jsp">Upload Book</a>
											
										</ul>
										</li>
										<!-- <li class="nav-item submenu dropdown">
											<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Blog</a>
											<ul class="dropdown-menu">
												<li class="nav-item">
													<a class="nav-link" href="blog.html">Blog</a>
												</li>
												<li class="nav-item">
													<a class="nav-link" href="single-blog.html">Blog Details</a>
												</li>
											</ul>
										</li> -->
										<li class="nav-item submenu dropdown">
											<a href="showcart.jsp" class="nav-link dropdown-toggle"   aria-haspopup="true" aria-expanded="false">Cart</a>
											<!-- <ul class="dropdown-menu">
												<li class="nav-item">
													<a class="nav-link" href="showcart.jsp">View Cart</a>
													<li class="nav-item">
													
											</ul> -->
											</li>
											
								</ul>
							</div>

							<div class="col-lg-5">
								<ul class="nav navbar-nav navbar-right right_nav pull-right">
									<hr>
									<li class="nav-item">
										<a href="#" class="nopadding icons">
											<i class="fa fa-search" aria-hidden="true"></i>
											<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
										</a>
									</li>

									<hr>

									<li class="nav-item">
										<a href="#" class="nopadding icons">
											<i class="fa fa-user" aria-hidden="true"></i>
											<!-- <i class="lnr lnr lnr-user" aria-hidden="true"></i> -->
											<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
										</a>
									</li>

									<hr>

									<li class="nav-item">
										<a href="showfavorite.jsp" class="nopadding icons">
											<!-- <i class="fa fa-heart-o" aria-hidden="true"></i> -->
											<i class="lnr lnr lnr-heart" aria-hidden="true"></i>
										</a>
									</li>

									<hr>

									<li class="nav-item">
										<a href="showcart.jsp" class="nopadding icons">
											<i class="lnr lnr lnr-cart"></i>
										</a>
									</li>

									<hr>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!--================Header Menu Area =================-->

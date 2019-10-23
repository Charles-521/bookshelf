<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.BookBean" %>
<%@ page import="com.dao.BookDao" %>
<%@ page import="com.dao.impl.BookDaoImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<body>
<% String msg = request.getParameter("msg"); %>
	<!--================Home Banner Area =================-->
	<section class="banner_area">
		<div class="banner_inner d-flex align-items-center">
			<div class="container">
				
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->

	<!--================Single Product Area =================-->
	<div class="product_image_area">
		<%
		BookDao bookDao = new BookDaoImpl();
		BookBean book = bookDao.findBookById(Integer.valueOf(request.getParameter("id")));
		String hide = request.getParameter("hide");
    			 %>
		<div class="container">
			<div class="row s_product_inner">
				<div class="col-lg-6">
					<div class="s_product_img">
						<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img class="d-block w-100" src="${pageContext.request.contextPath}/images/<%=book.getPicturePath()%>/<%=book.getFilename()%>" alt="${book.picturePath}">
								</div>
	
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5 offset-lg-1">
					<div class="s_product_text">
						<h3><%=book.getName() %></h3>
						<h2>$<%=book.getPrice() %></h2>
						<ul class="list">
							<li>
								<a  href="#">
									<span>ISBN</span> : <%=book.getISBN() %></a>
							</li>
							<li>
								<a href="#">
									<span>Course Code</span> : <%=book.getCourseCode() %></a>
							</li>
						</ul>
						<p><span>Description</span> :  <%=book.getDescription() %></p>
						<div class="card_area <%=request.getParameter("hide")%>">
							<form class="row tracking_form" method="post" enctype="multipart/form-data" action="AddCartServlet?bookID=<%=book.getId() %>&url=single-product.jsp?id=<%=book.getId()%>" novalidate="novalidate">
								<input class="main_btn" type="submit" value="Add to Cart" />
							</form>
	<!-- 						<a class="icon_btn" href="#">
								<i class="lnr lnr lnr-diamond"></i>
							</a> -->
							<!-- <input class="icon_ blnr lnr lnr-heart" type="submit" value="" /> -->
							 <a class="icon_btn" href="AddLikeBooks?bookID=<%=book.getId()%>&url=single-product.jsp?id=<%=book.getId()%>">
								<i class="lnr lnr lnr-heart"></i>
							</a> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--================End Single Product Area =================-->

	<!--================ start footer Area  =================-->
	<footer class="footer-area section_gap">
		<div class="container">
			<div class="row">
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6 class="footer_title">About Us</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore dolore magna aliqua.</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6 class="footer_title">Newsletter</h6>
						<p>Stay updated with our latest trends</p>
						<div id="mc_embed_signup">
							<form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
							 method="get" class="subscribe_form relative">
								<div class="input-group d-flex flex-row">
									<input name="EMAIL" placeholder="Email Address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email Address '"
									 required="" type="email">
									<button class="btn sub-btn">
										<span class="lnr lnr-arrow-right"></span>
									</button>
								</div>
								<div class="mt-10 info"></div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="single-footer-widget instafeed">
						<h6 class="footer_title">Instagram Feed</h6>
						<ul class="list instafeed d-flex flex-wrap">
							<li>
								<img src="img/instagram/Image-01.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-02.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-03.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-04.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-05.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-06.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-07.jpg" alt="">
							</li>
							<li>
								<img src="img/instagram/Image-08.jpg" alt="">
							</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-6 col-sm-6">
					<div class="single-footer-widget f_social_wd">
						<h6 class="footer_title">Follow Us</h6>
						<p>Let us be social</p>
						<div class="f_social">
							<a href="#">
								<i class="fa fa-facebook"></i>
							</a>
							<a href="#">
								<i class="fa fa-twitter"></i>
							</a>
							<a href="#">
								<i class="fa fa-dribbble"></i>
							</a>
							<a href="#">
								<i class="fa fa-behance"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row footer-bottom d-flex justify-content-between align-items-center">
				<p class="col-lg-12 footer-text text-center"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</p>
			</div>
		</div>
	</footer>
	<!--================ End footer Area  =================-->
	<!-- Modal -->
	<div id="msgbox" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="msgbox" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header row" style="padding: 0px; margin:0">
	      	<div class="col-lg-12"><i class="lnr lnr-warning"></i></div>
	       <!--  <h3 class="modal-title" style="color:white"></h3>    -->     
	      </div>
	      	<div class="modal-body " style="margin: 0;">
	        	<h4><%= msg %> </h4>
	        	<input type="hidden" value="<%=  msg %>" id="msg" />
	      	</div>      
	    </div>
	  </div>
	</div>
	<!-- Modal -->



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/stellar.js"></script>
	<script src="vendors/lightbox/simpleLightbox.min.js"></script>
	<script src="vendors/nice-select/js/jquery.nice-select.min.js"></script>
	<script src="vendors/isotope/imagesloaded.pkgd.min.js"></script>
	<script src="vendors/isotope/isotope-min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/mail-script.js"></script>
	<script src="vendors/jquery-ui/jquery-ui.js"></script>
	<script src="vendors/counter-up/jquery.waypoints.min.js"></script>
	<script src="vendors/counter-up/jquery.counterup.js"></script>
	<script src="js/theme.js"></script>
	<script>
		$(document).ready(function() {
			if($('#msg').val() === 'undefined' 
					|| $('#msg').val() == 'null'
					|| $('#msg').val() === '')
				return;
			$('#msgbox').modal('toggle');
		})
	</script>
</body>

</html>
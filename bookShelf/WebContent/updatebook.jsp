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
    <!--================Home Banner Area =================-->
    <section class="banner_area">
        <div class="banner_inner d-flex align-items-center">
            <div class="container">
                <div class="banner_content text-center">
                    <h2>Update Book</h2>
                    <div class="page_link">
                        <a href="index.jsp">Home</a>
                        <a href="uploadbook.jsp">Update Book</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Home Banner Area =================-->

    <!--================Tracking Box Area =================-->
    <section class="tracking_box_area p_120">
        <div class="container">
        <%
               BookDao bookDao = new BookDaoImpl();
               int id = Integer.valueOf(request.getParameter("bookID"));
               String url = request.getParameter("url");
               BookBean book = bookDao.findBookById(Integer.valueOf(request.getParameter("bookID")));
    			 %>
            <div class="tracking_box_inner">
                <form class="row tracking_form" method="post" enctype="multipart/form-data" action="UpdateBookServlet?bookID=<%= id %>&url=<%= url %>" novalidate="novalidate">
                  
                    <div class="col-md-12 form-group">
                        <input type="text" class="form-control" id="name" name="name" value=<%=book.getName() %>>
                    </div>
                    <div class="col-md-12 form-group">
                        <input type="text" class="form-control" id="price" name="price" value=<%=book.getPrice() %>>
                    </div>
                    <div class="col-md-12 form-group">
                        <input type="text" class="form-control" id="ISBN" name="ISBN" value=<%=book.getISBN() %>>
                    </div>
                    <div class="col-md-12 form-group">
                        <input type="text" class="form-control" id="courseCode" name="courseCode" value=<%=book.getCourseCode() %> >
                    </div>
                    <div class="col-md-12 form-group">
                        <input type="text" class="form-control" id="description" name="description" value=<%=book.getDescription() %> >
                    </div>
                     <!--<div class="col-md-12 form-group">
                        <input rows="3" type="file" class="form-control" id="file" name="file"  placeholder="--remain unchanged--" onchange="fileChange(this);">
                    </div>-->
                    <div class="col-md-12 form-group">
                        <button type="submit" value="submit" class="btn submit_btn">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <!--================End Tracking Box Area =================-->

    <!--================ start footer Area  =================-->
    <footer class="footer-area section_gap">
        <div class="container">
            <div class="row">
                <div class="col-lg-3  col-md-6 col-sm-6">
                    <div class="single-footer-widget">
                        <h6 class="footer_title">About Us</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                            dolore magna aliqua.</p>
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
    <script type="text/javascript" >
	function fileChange(target) {
		  var fileSize = 0;         
	       if (!target.files) {     
	         var filePath = target.value;     
	         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
	         var file = fileSystem.GetFile (filePath);     
	         fileSize = file.Size;    
	       } else {    
	        fileSize = target.files[0].size;     
	        }   
	        var size = fileSize / 1024;    
	        if(size>2000){  
	         alert("The picture cannot be greater than 2M");
	         target.value="";
	         return
	        }
	        var name=target.value;
	        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
	        if(fileName !="jpg" && fileName !="jpeg" && fileName !="pdf" && fileName !="png" && fileName !="dwg" && fileName !="gif" ){
	          alert("Please upload a picture(jpg,png,gif,dwg,pdf,gif)!");
	            target.value="";
	            return
	        }
	   } 


   	</script>
</body>


</html>
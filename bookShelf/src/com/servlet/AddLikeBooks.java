package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;
import com.util.AuthUtil;


/**
 * Servlet implementation class AddLikeBooks
 */
@WebServlet("/AddLikeBooks")
public class AddLikeBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLikeBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getParameter("url");
		if (!AuthUtil.IsLogin(request)) {
			if (!AuthUtil.IsLogin(request)) {
				response.sendRedirect(request.getContextPath() + "/" + url); 
				return;
			}
			return;
		}
		
		
		Service s= new ServiceImpl();	
		UserDao user= new UserDaoImpl();
		
		String name = (String) request.getSession().getAttribute("name");
		String id = request.getParameter("bookID");
		int bookID = 0;
		if(id!=null&&!id.equals("")){
			bookID = Integer.parseInt(id);
		}
		int userID = user.findIdByName(name);
		int rs = s.addLikeBook(userID, bookID);
		PrintWriter out = response.getWriter();
		if (rs == 2) {
			out.printf("<script language='javascript'>window.location.href='%s';</script>", url + "&msg=This book has been added to favorite"); 
			return;
		}
		if (rs == 3) {
			out.printf("<script language='javascript'>window.location.href='%s';</script>", url + "&msg=Fail to add to favorite books"); 
			return;
		}
		
		out.printf("<script language='javascript'>window.location.href='%s';</script>", url + "&msg=Add to favorite  book successfully"); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

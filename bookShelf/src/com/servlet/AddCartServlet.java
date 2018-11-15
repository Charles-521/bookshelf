package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;
import com.util.AuthUtil;


/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
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
		
		
		String id = request.getParameter("bookID");
		int bookID = 0;
		if(id!=null&&!id.equals("")){
			bookID = Integer.parseInt(id);
		}
		String name = (String) request.getSession().getAttribute("name");
		int userID = user.findIdByName(name);
		boolean rs = s.addCartBook(userID, bookID);
		PrintWriter out = response.getWriter();
		if (!rs) {
			out.printf("<script language='javascript'>alert('Fail to add to cart!');window.location.href='%s';</script>", url); 
			return;
		}
		
		out.printf("<script language='javascript'>alert('Add to cart successfully!');window.location.href='%s';</script>", url); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

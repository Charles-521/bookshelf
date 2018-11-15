package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class RemoveLikeBooks
 */
@WebServlet("/RemoveLikeBooks")
public class RemoveLikeBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveLikeBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.IsLogin(request)) return;
		Service s= new ServiceImpl();	
		UserDao user= new UserDaoImpl();
		
		String name = (String) request.getSession().getAttribute("name");
		String id = request.getParameter("bookID");
		String url = request.getParameter("url");
		int bookID = 0;
		if(id!=null&&!id.equals("")){
			bookID = Integer.parseInt(id);
		}
		int userID = user.findIdByName(name);
		boolean rs = s.removeLikeBook(userID, bookID);
		PrintWriter out = response.getWriter();
		if (!rs) {
			out.printf("<script language='javascript'>alert('Fail to remove to favorite!');window.location.href='%s';</script>", url); 
			return;
		}
		
		out.printf("<script language='javascript'>alert('remove the favorite book successfully!');window.location.href='%s';</script>", url); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

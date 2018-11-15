package com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.IsLogin(request)) {
			response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			return;
		}
		UserDao user= new UserDaoImpl();		
		BookDao bd = new BookDaoImpl();
		
		String ids = request.getParameter("bookIds");
		BigDecimal total = new BigDecimal(request.getParameter("total"));
		
		String name = (String) request.getSession().getAttribute("name");
		int userID = user.findIdByName(name);
		
		try {
			int rs = bd.checkout(userID, total, ids);
			response.getWriter().append(String.valueOf(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

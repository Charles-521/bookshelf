package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Page;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class ShowFavoriteBooks
 */
@WebServlet("/ShowFavoriteBooks")
public class ShowFavoriteBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFavoriteBooks() {
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
		
		String num = request.getParameter("num");
		String name = (String) request.getSession().getAttribute("name");
		int userID = user.findIdByName(name);
		Page page = s.findFavoriteBooks(num, userID);
		page.setUrl("/ShowFavoriteBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/favoritebooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}

package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.OrderBean;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class GetSellsServlet
 */
@WebServlet("/GetSellsServlet")
public class GetSellsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSellsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.IsLogin(request)) {
			response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			return;
		}
		UserDao user= new UserDaoImpl();
		OrderDao order= new OrderDaoImpl();
		
		String name = (String) request.getSession().getAttribute("name");
		int userID = user.findIdByName(name);
		List<OrderBean> records = order.findSellOrderByUserID(userID);
		
		request.setAttribute("records", records);
		request.setAttribute("visibility", records.size() == 0 ? "hidden" : "show");
		request.setAttribute("emptycart", records.size() == 0 ? "show" : "hidden");
		
		request.getRequestDispatcher("/sells.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

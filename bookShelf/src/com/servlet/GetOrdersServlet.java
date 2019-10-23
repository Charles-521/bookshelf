package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import com.bean.BookBean;
import com.bean.OrderBean;
import com.bean.OrderDetailBean;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class GetOrdersServlet
 */
@WebServlet("/GetOrdersServlet")
public class GetOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrdersServlet() {
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
		List<OrderBean> records = order.findBuyOrderByUserID(userID);
		
		request.setAttribute("records", records);
		request.setAttribute("visibility", records.size() == 0 ? "hidden" : "show");
		request.setAttribute("emptycart", records.size() == 0 ? "show" : "hidden");
		
		request.getRequestDispatcher("/orders.jsp").forward(request, response);      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}

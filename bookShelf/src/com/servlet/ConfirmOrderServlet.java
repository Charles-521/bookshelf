package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.OrderInfo;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class ConfirmOrderServlet
 */
@WebServlet("/ConfirmOrderServlet")
public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrderServlet() {
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

		OrderDao order= new OrderDaoImpl();
		String orderID = (String) request.getParameter("orderID");
		List<OrderInfo> records = order.findOrderInfoByOrderID(Integer.parseInt(orderID));
		request.setAttribute("records", records);
		request.setAttribute("total", records.get(0).getPayment());
		request.setAttribute("orderNo", records.get(0).getOrderNo());
		request.setAttribute("orderDate", records.get(0).getCreateDate());
		request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

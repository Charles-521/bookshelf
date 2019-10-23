package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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

import com.bean.OrderDetailBean;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.util.AuthUtil;

/**
 * Servlet implementation class GetOrderDetailsServlet
 */
@WebServlet("/GetOrderDetailsServlet")
public class GetOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.IsLogin(request)) {
			response.sendRedirect(request.getContextPath() + "/login.jsp"); 
			return;
		}
		OrderDao order= new OrderDaoImpl();	
		UserDao user= new UserDaoImpl();
		
		String name = (String) request.getSession().getAttribute("name");
		int userID = user.findIdByName(name);
		List<OrderDetailBean> records = order.findBuyOrderDetailsByUserID(userID);
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (records.size() == 0) {
			JSONObject obj = new JSONObject();
			obj.append("isEmpty", true);
			out.print(obj);
		}
		
        JSONArray objs = new JSONArray();
        for (OrderDetailBean od : records ) {
        	JSONObject obj = new JSONObject();
        	Map<String, String> properties;
			try {
				properties = BeanUtils.describe(od);
				for (Map.Entry<String, String> e : properties.entrySet()) {
	            	obj.put(e.getKey(), e.getValue());
	            }
	            objs.put(obj);
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        }
        
        JSONObject obj = new JSONObject();
        obj.put("Data", objs);
        obj.put("DataCount", objs.length());
        out.print(objs);
        out.flush();
	}

}

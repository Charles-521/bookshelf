package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.UserBean;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name"); //��ȡjspҳ�洫�����Ĳ���
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phonenumber");
		
		UserBean user = new UserBean(); //ʵ����һ��������װ����
		user.setName(name);
		user.setEmailAddress(email);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		
		UserDao ud = new UserDaoImpl();
		if (ud.findUserByName(name)) {
			response.getWriter().append("0");
		}
		else {
			ud.register(user);
			response.getWriter().append("1");
		}
	}
	

}

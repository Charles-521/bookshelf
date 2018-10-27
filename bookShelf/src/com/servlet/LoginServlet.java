package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String name = request.getParameter("name"); //��ҳ��õ�����
		String pwd = request.getParameter("password");
		
		UserDao ud = new UserDaoImpl();
	//	PrintWriter out = response.getWriter();//��ʼ��out����
		if(ud.login(name, pwd)){
			   /*
			    * ��servlet��ʵ�ַ���������ת��������תҳ�洫�ݲ���
	         */
			
			//request.setAttribute("name", "haiyun");  // Ϊrequest������Ӳ���
/*			��jsp�еõ������ַ�������һ�֣�
			<%=request.getAttribute("str");%>
			��${str}*/ //����һ��jsp����õ��ò���
	        //RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");    // ʹ��req�����ȡRequestDispatcher����
	        //dispatcher.forward(request, response);
			//System.out.println("success");
			//request.getRequestDispatcher("/success.jsp").forward(request, response);//��ת
			request.getSession().setAttribute("username", name);
			response.getWriter().append("0");
		}else{
			//response.sendRedirect("index.jsp"); //���ɹ����ض���
			response.getWriter().append("1");
			//out.print("<script language='javascript'>alert('UserName or password Wrong!!');window.location.href='login.jsp';</script>");  
		}
	}
}

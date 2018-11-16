package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Page;
import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;

/**
 * Servlet implementation class ShowAccountServlet
 */
@WebServlet("/ShowAccountServlet")
public class ShowAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service s= new ServiceImpl();
		String num = request.getParameter("num");//�û�Ҫ����ҳ��
		BookDao bookDao = new BookDaoImpl();
		UserDao user= new UserDaoImpl();
		String name = (String) request.getSession().getAttribute("name");
		PrintWriter out = response.getWriter();//��ʼ��out����
		if(name==null||name.equals("")){
			out.print("<script language='javascript'>alert('Please Login First!');window.location.href='login.jsp';</script>"); 
		}else {
		int ownerID = user.findIdByName(name);	
		
		Page page = null;
		int totalRecordsNum = 0;
		totalRecordsNum = bookDao.getTotalRecordsNumByOwnerID(ownerID);
		page = s.findBookPageRecordsByOwnerID(num,ownerID);
		
		//------------------------
			//	Service s= new ServiceImpl();
		//		String num = request.getParameter("num");//�û�Ҫ����ҳ��		
			//	Page page = s.findBookPageRecordsByOwnerID(num);
			//	page.setUrl("/ShowBookPages");
			//	request.setAttribute("page", page);
			//	request.getRequestDispatcher("/listbook.jsp").forward(request, response);
		//----------------------------------
		System.out.println(totalRecordsNum);
		if (totalRecordsNum!=0) {
			page.setUrl("/ShowAccountServlet");
			request.setAttribute("page", page);
			request.getRequestDispatcher("/listmybook.jsp").forward(request, response);}
		else {
			out.print("<script language='javascript'>alert('No book have been uploaded');window.location.href='index.jsp';</script>");
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
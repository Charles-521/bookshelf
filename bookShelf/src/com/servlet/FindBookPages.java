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
import com.dao.impl.BookDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;

/**
 * Servlet implementation class FindBookPages
 */
@WebServlet("/FindBookPages")
public class FindBookPages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBookPages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service s= new ServiceImpl();
		BookDao bookDao = new BookDaoImpl();
		String keyWordsType = request.getParameter("keywordstype");
		String keyWords = request.getParameter("keywords");
		System.out.println(keyWords);
		PrintWriter out = response.getWriter();
		Page page = null;
		int totalRecordsNum = 0;
		if(keyWordsType.equals("ISBN")){
			totalRecordsNum = bookDao.getTotalRecordsNumByISBN(keyWords);
			page = s.findBookPageRecordsByISBN(keyWords);
		}
		if(keyWordsType.equals("Name")){
			totalRecordsNum = bookDao.getTotalRecordsNumByName(keyWords);
			page = s.findBookPageRecordsByName(keyWords);
		}
		if(keyWordsType.equals("Coursecode")){
			totalRecordsNum = bookDao.getTotalRecordsNumByCourseCode(keyWords);
			page = s.findBookPageRecordsByCourseCode(keyWords);
		}
		System.out.println(totalRecordsNum);
		if (totalRecordsNum!=0) {
			page.setUrl("/FindBookPages");
			request.setAttribute("page", page);
			request.getRequestDispatcher("/listbook.jsp").forward(request, response);}
		else {
			out.print("<script language='javascript'>alert('No Matching Results');window.location.href='showallbook.jsp';</script>");
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

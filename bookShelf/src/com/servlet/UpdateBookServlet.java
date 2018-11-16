package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.bean.BookBean;
import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;
import com.util.IdGenertor;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
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
		// TODO Auto-generated method stub
		
		UserDao user= new UserDaoImpl();
		PrintWriter out = response.getWriter();
		int owenrid = 0;
		String name = (String) request.getSession().getAttribute("name");
		
		if(name==null||name.equals("")){
			out.print("<script language='javascript'>alert('Please Login First!');window.location.href='login.jsp';</script>"); 
		}else {
			owenrid = user.findIdByName(name);
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		BookDao bookDao = new BookDaoImpl();
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		BookBean book = new BookBean();
		for(FileItem item:items){
			book.setOwnerId(owenrid);
			processFormFiled(item,book);
			
		}
        int bookId = Integer.valueOf(request.getParameter("bookID"));
        Service s= new ServiceImpl();	
        String url = request.getParameter("url");
		
		boolean rs = s.updateBook(bookId, book.getName(), book.getPrice(), book.getISBN(), book.getCourseCode(), book.getDescription());
		if (!rs) {
			out.printf("<script language='javascript'>window.location.href='%s';</script>", url + "?msg=Fail to update"); 
			return;
		}
		out.printf("<script language='javascript'>window.location.href='%s';</script>", url);
     }
	
	private void processFormFiled(FileItem item, BookBean book) {
		try {
			String fieldName = item.getFieldName();//name
			String fieldValue = item.getString("UTF-8");//jpm
			BeanUtils.setProperty(book, fieldName, fieldValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
	
		



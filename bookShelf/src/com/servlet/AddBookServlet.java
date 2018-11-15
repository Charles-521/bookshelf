package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.util.IdGenertor;


/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�жϱ��ǲ���multipart/form-data���͵�
/*		String name = request.getParameter("name");
		System.out.println(name);
		String pricetmp = request.getParameter("price");
		System.out.println(pricetmp);
		float price=Float.parseFloat(pricetmp);
		System.out.println(price);
		String ISBN = request.getParameter("ISBN");
		String coursecode = request.getParameter("coursecode");
		int owenrid = 1;*/
		//**��Ҫ��userid��session��ȡ��Ȼ��д�����ݿ�
		UserDao user= new UserDaoImpl();
		PrintWriter out = response.getWriter();//��ʼ��out����
		int owenrid = 0;
		String name = (String) request.getSession().getAttribute("name");
		//System.out.println(name);
		if(name==null||name.equals("")){
			out.print("<script language='javascript'>alert('Please Login First!');window.location.href='login.jsp';</script>"); 
		}else {
			owenrid = user.findIdByName(name);
		}
		//System.out.println(owenrid);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(!isMultipart){
			throw new RuntimeException("The form is not multipart/form-data");
		}
		//������������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		BookBean book = new BookBean();//�ն���
		BookDao bookDao = new BookDaoImpl();
		for(FileItem item:items){
			//��ͨ�ֶΣ������ݷ�װ��Book������
			if(item.isFormField()){
				book.setOwnerId(owenrid);
				processFormFiled(item,book);
				
			}else{
			//�ϴ��ֶΣ��ϴ�
				processUploadFiled(item,book,out);
			}
		}
		//���鼮��Ϣ���浽���ݿ���
		BigDecimal a = book.getPrice();
//		if ((Math.abs(a)<0.00000001)) {
//			out.print("<script language='javascript'>alert('Price must be a number!');window.location.href='uploadbook.jsp';</script>"); 
//		}
		if (book.getName().equals("")||book.getISBN().equals("")||book.getCourseCode().equals("")||book.getDescription().equals("")) {
			out.print("<script language='javascript'>alert('Please fill all the information!');window.location.href='uploadbook.jsp';</script>"); 
		}
		else {
		bookDao.addBook(book);}
		out.print("<script language='javascript'>alert('Upload successfully!');window.location.href='uploadbook.jsp';</script>"); 
	}
	//���ͼ��
		//�����ļ��ϴ�
		private void processUploadFiled(FileItem item, BookBean book,PrintWriter out) {
			//���·������Ҫ����WEB-INF��
			String storeDirectory = getServletContext().getRealPath("/images");
			File rootDirectory = new File(storeDirectory);
			if(!rootDirectory.exists()){
				rootDirectory.mkdirs();
			}
			//���ļ���
			String filename = item.getName();//  a.jpg
			if (filename=="")
			{
				out.print("<script language='javascript'>alert('Please Select A Picture!');window.location.href='uploadbook.jsp';</script>"); 
			}
			
			if(filename!=null){
				filename = IdGenertor.genGUID()+"."+ FilenameUtils.getExtension(filename);//LKDSJFLKSFKS.jpg
				book.setFilename(filename);
			}
			
			//������Ŀ¼
			String path = genChildDirectory(storeDirectory, filename);
			book.setPicturePath(path);
			
			//�ļ��ϴ�	
			try {
				item.write(new File(rootDirectory, path+"/"+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		private String genChildDirectory(String realPath, String fileName) {
			int hashCode = fileName.hashCode();
			int dir1 = hashCode&0xf;
			int dir2 = (hashCode&0xf0)>>4;
			
			String str = dir1+File.separator+dir2;
			
			File file = new File(realPath,str);
			if(!file.exists()){
				file.mkdirs();
			}
			
			return str;
			
		}
		//��FileItem�е����ݷ�װ��Book��
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

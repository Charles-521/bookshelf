package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bean.BookBean;
import com.bean.OrderBean;
import com.bean.UserBean;
import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.UserDaoImpl;

public class test {

	public static void main(String[] args) throws ParseException {
		// 
/*		OrderDao order = new OrderDaoImpl();
		List<OrderBean> list = order.findOrderByBuyerID(1);
		for(int i =0;i<list.size();i++) {
			OrderBean test = list.get(i);
			System.out.println(test.getPayment());
			System.out.println(test.getCreateDate());
		}*/
/*		String pricetmp = "132.11";
		float price=Float.parseFloat(pricetmp);
		System.out.println(price);*/
/*		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println(df.format(day)); */
/*		Date today = new Date();    
		System.out.println(today);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		String day =sdf.format(today);
		System.out.println(day);*/
/*		BookDao book = new BookDaoImpl();
		List<BookBean> list = book.findPageRecords(1, 3);
		System.out.println(list.size());
		for(int i =0;i<list.size();i++) {
			BookBean test = list.get(i);;
			System.out.println(test.getName());
		}*/
		BookDao bookDao = new BookDaoImpl();
		BookBean book = bookDao.findBookById(9);
		//int totalRecordsNum = bookDao.getTotalRecordsNumByISBN("456");
		System.out.println(book.getName());
/*		BookDao book = new BookDaoImpl();
		BookBean booktest = new BookBean();
		booktest.setName("10.26");
		booktest.setPrice(11.1f);
		booktest.setISBN("2");
		booktest.setCourseCode("ci6222");
		booktest.setPicturePath("hahah");
		booktest.setPicturePath("1");
		booktest.setOwnerId(1);
		booktest.setFilename("1");
		book.addBook(booktest);*/
/*		BookDao bookdao = new BookDaoImpl();
		int i = bookdao.getTotalRecordsNum();
		System.out.println(i);*/
/*		List<BookBean> list = book.findBookByCourseCode("12");
		BookBean test = list.get(0);
		System.out.println(test.getName());
		List<BookBean> list = book.findBookByName("life");
		BookBean test = list.get(0);*/
		
		//System.out.println(test.getISBN());

/*		UserDao user = new UserDaoImpl();
		UserBean usertest = new UserBean();
		usertest.setName("zhang");
		usertest.setPassword("1");
		usertest.setPhoneNumber("123");
		user.register(usertest);
		boolean i = user.login("b","b");
		System.out.println(i);
		user.update(13,"bin","1","13213");*/
/*		public boolean login(String name,String pwd);
		public boolean register(UserBean user);
		public boolean update(int id,String name, String pwd,String phoneNum);
*/	}

}

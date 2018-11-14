package com.servlet;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.BookBean;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.Service;
import com.service.impl.ServiceImpl;

/**
 * Servlet implementation class ShowCartBooks
 */
@WebServlet("/ShowCartBooks")
public class ShowCartBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Service s= new ServiceImpl();	
		UserDao user= new UserDaoImpl();
		
		String name = (String) request.getSession().getAttribute("name");
		int userID = user.findIdByName(name);
		List<BookBean> records = s.findBookCartByUserID(userID);
		float total = 0;
		for (int i = 0; i < records.size(); i++) {
			total += records.get(i).getPrice();
		}		
		request.setAttribute("records", records);
		request.setAttribute("total", total);
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

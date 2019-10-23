package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.BookBean;
import com.bean.OrderBean;
import com.bean.OrderDetailBean;
import com.bean.OrderInfo;
import com.dao.OrderDao;
import com.util.DBconn;

public class OrderDaoImpl implements OrderDao {
	
	@Override
	public List<OrderBean> findSellOrderByUserID(int userID){
		List<OrderBean> list = new ArrayList<OrderBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from userorder uo, userinfo ui where uo.buyerId=ui.id and buyerId = ?");
        	ps.setInt(1, userID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	OrderBean order = new OrderBean();
            	order.setId(rs.getInt("id"));
            	order.setBuyerName(rs.getString("ui.name"));
            	order.setPayment(rs.getBigDecimal("payment"));
            	order.setOrderNo(rs.getString("orderNo"));
            	order.setCreateDate(rs.getString("createDate"));
            	list.add(order);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}
	
	@Override
	public List<OrderBean> findBuyOrderByUserID(int userID){
		List<OrderBean> list = new ArrayList<OrderBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from userorder where buyerId = ?");
        	ps.setInt(1, userID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	OrderBean order = new OrderBean();
            	order.setId(rs.getInt("id"));
            	order.setBuyerID(rs.getInt("buyerId"));
            	order.setPayment(rs.getBigDecimal("payment"));
            	order.setOrderNo(rs.getString("orderNo"));
            	order.setCreateDate(rs.getString("createDate"));
            	list.add(order);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}
	
	@Override
	public List<OrderDetailBean> findBuyOrderDetailsByUserID(int userID){
		
		List<OrderDetailBean> list = new ArrayList<OrderDetailBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select u.phonenumber, od.*, b.* from orderdetail od, userorder uo, book b, userinfo u where u.id=b.ownerid and b.id=od.bookID and uo.id=od.orderID and uo.buyerId = ?");
        	ps.setInt(1, userID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	OrderDetailBean order = new OrderDetailBean();
            	order.setId(rs.getInt("od.id"));
            	order.setOrderID(rs.getInt("od.orderID"));
            	order.setName(rs.getString("b.name"));
            	order.setPrice(rs.getBigDecimal("b.price"));
            	order.setPrice(rs.getBigDecimal("b.price"));
            	order.setPhoneNumber(rs.getString("u.phonenumber"));
            	list.add(order);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
		
	}

	@Override
	public boolean createOrder(int buyerID, float payment) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;  
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		String sql = "insert into userorder(buyerID,payment,createDate)values(?,?,?)";
		try {
			conn = DBconn.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setInt(1, buyerID);
			ps.setFloat(2, payment);
			ps.setString(3, currentTime);
			int i =  ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch (SQLException e) {
			System.out.println("create order error");
			e.printStackTrace();
		}finally {
    		DBconn.close(null, ps, conn);
    	}
		return flag;
	}

	@Override
	public List<OrderBean> findOrderByBuyerID(int buyerID) {
		
		List<OrderBean> list = new ArrayList<OrderBean>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select * from userorder where buyerId = ?");
        	ps.setInt(1, buyerID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	OrderBean order = new OrderBean();
            	order.setId(rs.getInt("id"));
            	order.setBuyerID(rs.getInt("buyerId"));
            	order.setPayment(rs.getBigDecimal("payment"));
            	order.setCreateDate(rs.getString("createDate"));
            	list.add(order);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}
	
	@Override
	public List<OrderInfo> findOrderInfoByOrderID(int orderID) {
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBconn.getConnection();
        	ps = conn.prepareStatement("select u.*, o.*, b.*, ui.* from userorder u, orderdetail o, book b, userinfo ui where u.id=o.orderID and o.bookID = b.id and b.ownerid=ui.id and u.id = ?");
        	ps.setInt(1, orderID);
            rs=ps.executeQuery();
            while(rs.next()) {
            	OrderInfo order = new OrderInfo();
            	order.setId(rs.getInt("u.id"));
            	order.setBuyerID(rs.getInt("u.buyerID"));
            	order.setPayment(rs.getBigDecimal("u.payment"));
            	order.setCreateDate(rs.getString("u.createDate"));
            	order.setOrderNo(rs.getString("u.orderNo"));
            	order.setBookID(rs.getInt("b.id"));
            	order.setName(rs.getString("b.name"));
            	order.setPrice(rs.getFloat("b.price"));
            	order.setISBN(rs.getString("b.ISBN"));
            	order.setPhoneNumber(rs.getString("ui.phonenumber"));
            	order.setCourseCode(rs.getString("b.coursecode"));
            	order.setPicturePath(rs.getString("b.picturepath"));
            	order.setFilename(rs.getString("b.filename"));
            	
            	list.add(order);
            }
            return list;
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
    		DBconn.close(rs, ps, conn);
    	}
		return null;
	}

}

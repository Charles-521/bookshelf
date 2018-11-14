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
import com.dao.OrderDao;
import com.util.DBconn;

public class OrderDaoImpl implements OrderDao {

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
		// TODO Auto-generated method stub
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
            	order.setPayment(rs.getFloat("payment"));
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

}

package com.dao;

import java.sql.Date;
import java.util.List;

import com.bean.OrderBean;
import com.bean.OrderInfo;



public interface OrderDao {
	public boolean createOrder(int buyerID, float payment);
	public List<OrderBean> findOrderByBuyerID(int buyerID);
	public List<OrderInfo> findOrderInfoByOrderID(int orderID);
}

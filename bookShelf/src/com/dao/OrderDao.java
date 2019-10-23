package com.dao;

import java.util.List;

import com.bean.OrderBean;
import com.bean.OrderDetailBean;
import com.bean.OrderInfo;



public interface OrderDao {
	public boolean createOrder(int buyerID, float payment);
	public List<OrderBean> findOrderByBuyerID(int buyerID);
	public List<OrderInfo> findOrderInfoByOrderID(int orderID);
	public List<OrderBean> findBuyOrderByUserID(int userID);
	public List<OrderDetailBean> findBuyOrderDetailsByUserID(int userID);
	public List<OrderBean> findSellOrderByUserID(int userID);
}

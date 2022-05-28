package com.analytics.analytics.dao;

import com.analytics.analytics.entity.Order;

import java.util.List;

public interface IOrderDAO {
	
	List<Order> getOrders();
	Order getOrder(int OrderId);
	Order createOrder(Order Order);
	Order updateOrder(int OrderId,Order Order);
	boolean deleteOrder(int OrderId);

}

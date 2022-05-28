package com.analytics.analytics.services;

import com.analytics.analytics.entity.Order;

import java.util.List;

public interface IOrderService {
	
	List<Order> getOrders();
	Order createOrder(Order order);
	Order updateOrder(int orderId, Order order);
	Order getOrder(int orderId);
	boolean deleteOrder(int orderId);

}

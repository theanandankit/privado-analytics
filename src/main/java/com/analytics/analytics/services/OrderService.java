package com.analytics.analytics.services;

import com.analytics.analytics.dao.IOrderDAO;
import com.analytics.analytics.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	private IOrderDAO dao;

	@Override
	public List<Order> getOrders() {
		return dao.getOrders();
	}

	@Override
	public Order createOrder(Order order) {
		return dao.createOrder(order);
	}

	@Override
	public Order updateOrder(int orderId, Order order) {
		return dao.updateOrder(orderId, order);
	}

	@Override
	public Order getOrder(int orderId) {
		return dao.getOrder(orderId);
	}

	@Override
	public boolean deleteOrder(int orderId) {
		return dao.deleteOrder(orderId);
	}

}

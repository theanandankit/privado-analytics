package com.analytics.analytics.services;

import com.analytics.analytics.dao.IOrderDAO;
import com.analytics.analytics.entity.Order;
import com.analytics.analytics.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	private IOrderDAO dao;

	@Autowired
	private ProductService productService;

	@Override
	public List<Order> getOrders() {
		return dao.getOrders();
	}

	@Override
	public Order createOrder(Order order) {
		Product product = new Product();
		product.setOrderId(order.getId());
		product.setBuyerEmail(order.getBuyer().getEmail());

		productService.saveProduct(product);
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

package com.analytics.analytics.services;

import com.analytics.analytics.dao.IOrderDAO;
import com.analytics.analytics.entity.Order;
import com.analytics.analytics.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

	Logger logger = LoggerFactory.getLogger(OrderService.class);


	@Autowired
	private IOrderDAO dao;

	@Autowired
	private ProductService productService;

	@Autowired
	private JiraService jiraService;

	@Override
	public List<Order> getOrders() {
		return dao.getOrders();
	}

	@Override
	public Order createOrder(Order order) {
		try {
			Product product = new Product();
			product.setOrderId(order.getId());
			String buyerEmail = order.getBuyer().email;
			System.out.println("Buyer Email: " + buyerEmail);
			logger.debug("Buyer Email: " + buyerEmail);
			product.setBuyerEmail(buyerEmail);

			productService.saveProduct(product);
		}
		catch (Exception e) {
			logger.error("Exception while creating order: " +
					""+ order.getId() + "" +
					"Buyer Email"+ order.getBuyer().email);
			String issueId = jiraService.createIssue(1l, "Exception while creating order for "+ order.getBuyer().email);
			return null;
		}
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

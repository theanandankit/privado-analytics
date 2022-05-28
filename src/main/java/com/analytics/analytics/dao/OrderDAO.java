package com.analytics.analytics.dao;

import com.analytics.analytics.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Transactional
@Repository
public class OrderDAO implements IOrderDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all Orders available in database and return it as List<Order>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders() {
		
		String hql = "FROM Order as atcl ORDER BY atcl.id";
		return (List<Order>) entityManager.createQuery(hql).getResultList();
	}

	/**
	 * This method is responsible to get a particular Order detail by given Order id 
	 */
	@Override
	public Order getOrder(int orderId) {
		
		return entityManager.find(Order.class, orderId);
	}

	/**
	 * This method is responsible to create new Order in database
	 */
	@Override
	public Order createOrder(Order order) {
		entityManager.persist(order);
		Order b = getLastInsertedOrder();
		return b;
	}

	/**
	 * This method is responsible to update Order detail in database
	 */
	@Override
	public Order updateOrder(int orderId, Order order) {
		
		//First We are taking Order detail from database by given Order id and 
		// then updating detail with provided Order object
		Order orderFromDB = getOrder(orderId);

		orderFromDB.setStatus(order.getStatus());
		orderFromDB.setTrackingId(order.getTrackingId());
		orderFromDB.setProductId(order.getProductId());
		
		entityManager.flush();
		
		//again i am taking updated result of Order and returning the Order object
		Order updatedOrder = getOrder(orderId);
		
		return updatedOrder;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteOrder(int orderId) {
		Order order = getOrder(orderId);
		entityManager.remove(order);
		
		//we are checking here that whether entityManager contains earlier deleted Order or not
		// if contains then Order is not deleted from DB that's why returning false;
		boolean status = entityManager.contains(order);
		if(status){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Order class
	 * @return Order
	 */
	private Order getLastInsertedOrder() {
		String hql = "from Order as atcl order by atcl.id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Order order = (Order)query.getSingleResult();
		return order;
	}

}

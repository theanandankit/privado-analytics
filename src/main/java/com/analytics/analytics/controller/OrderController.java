package com.analytics.analytics.controller;

import com.analytics.analytics.entity.Order;

import com.analytics.analytics.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orderservice")
public class OrderController {
	
	@Autowired
	private IOrderService service;
	
	@GetMapping("orders")
	public ResponseEntity<List<Order>> getOrders(){
		
		List<Order> orders = service.getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
		
	}
	
	@GetMapping("orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Integer id){
		Order order = service.getOrder(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PostMapping("orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		Order b = service.createOrder(order);
		return new ResponseEntity<Order>(b, HttpStatus.OK);
		
	}
	
	@PutMapping("orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order){
		
		Order b = service.updateOrder(id, order);
		return new ResponseEntity<Order>(b, HttpStatus.OK);
	}
	
	@DeleteMapping("orders/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable("id") int id){
		boolean isDeleted = service.deleteOrder(id);
		if(isDeleted){
			String responseContent = "Order has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting order from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

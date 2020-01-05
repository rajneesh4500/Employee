package org.employee.ResponseEntities;

import java.util.List;

import org.employee.interfaces.ResponseClasses;
import org.employee.models.Order;
import org.springframework.stereotype.Component;




@Component
public class OrdersResponse implements ResponseClasses{

	private int statusCode;
	private String message;
	private List<Order> orders;
	
	
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getMessage() {
		return message;
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	@Override
	public void setStatusCode(int code) {
		this.statusCode = code;
	}
	
	

}

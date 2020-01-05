package org.employee.ResponseEntities;

import org.employee.interfaces.ResponseClasses;
import org.employee.models.Order;
import org.springframework.stereotype.Component;



@Component
public class OrderResponse implements ResponseClasses{
	private int statusCode;
	private String message;
	private Order order;
	
	public int getStatusCode() {
		return statusCode;
	}
	@Override
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}

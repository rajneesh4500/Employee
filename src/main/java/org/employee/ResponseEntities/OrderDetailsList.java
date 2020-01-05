package org.employee.ResponseEntities;

import java.util.List;

import org.employee.interfaces.ResponseClasses;
import org.employee.models.OrderDetails;

public class OrderDetailsList implements ResponseClasses {
	private int statusCode;
	private String message;
	
	
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
	public List<OrderDetails> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}
	private List<OrderDetails> orders;
}

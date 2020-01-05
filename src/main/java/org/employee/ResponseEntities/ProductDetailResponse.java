package org.employee.ResponseEntities;

import org.employee.interfaces.ResponseClasses;
import org.employee.models.ProductDetail;



public class ProductDetailResponse implements ResponseClasses{
	private int statusCode;
	private String message;
	private ProductDetail product;
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
	public ProductDetail getProduct() {
		return product;
	}
	public void setProduct(ProductDetail product) {
		this.product = product;
	}
	
}

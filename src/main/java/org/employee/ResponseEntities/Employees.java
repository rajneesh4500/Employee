package org.employee.ResponseEntities;

import java.util.List;

import org.employee.interfaces.ResponseClasses;
import org.employee.models.Employee;

public class Employees implements ResponseClasses {
	private int statusCode;
	private String message;
	private List<Employee> Employees;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Employee> getEmployees() {
		return Employees;
	}
	public void setEmployees(List<Employee> employees) {
		Employees = employees;
	}
	
	
}

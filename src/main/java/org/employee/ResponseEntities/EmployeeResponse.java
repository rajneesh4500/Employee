package org.employee.ResponseEntities;

import org.employee.interfaces.ResponseClasses;
import org.employee.models.Employee;

public class EmployeeResponse implements ResponseClasses{
	
	private int statusCode;
	private String message;
	
	private Employee emp;
	
	
	
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
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

}

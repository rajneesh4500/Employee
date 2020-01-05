package org.employee.control;

import java.util.List;

import org.employee.ResponseEntities.EmployeeResponse;
import org.employee.ResponseEntities.Employees;
import org.employee.ResponseEntities.OrderDetailsList;
import org.employee.ResponseEntities.OrderResponse;
import org.employee.ResponseEntities.OrdersResponse;
import org.employee.ResponseEntities.ProductDetailResponse;
import org.employee.Utils.EmployeeUtils;
import org.employee.models.Employee;
import org.employee.models.Order;
import org.employee.models.OrderDetails;
import org.employee.models.ProductDetail;
import org.employee.repositories.EmployeeRepo;
import org.employee.repositories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@CrossOrigin
@Controller
@RequestMapping(value="/v1")
public class EmployeeController {
	
	@Autowired
	EmployeeRepo empDAO;
	
	@Autowired
	EmployeeUtils emputils;
	
	@Autowired
	OrdersRepo ordersDAO;
	
	
	@RequestMapping("/getEmployee/{emp_id}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable(name = "emp_id") int empId) {
		EmployeeResponse emp = new EmployeeResponse();
		Employee er= empDAO.getEmployeeByID(empId);
		if(er!=null) {
			emp.setEmp(er);
			emputils.successMessage(emp);
		}else {
			emputils.failureMessage(emp);	
		}
		return new ResponseEntity<EmployeeResponse>(emp,HttpStatus.OK); 
		
	}
	
	
	@RequestMapping("/getAllOrders")
	public ResponseEntity<OrdersResponse> getOrders() {
		OrdersResponse orders = new OrdersResponse();
		List<Order> ord = ordersDAO.getAllOrders();
		if(ord.size()>0) {
			orders.setOrders(ord);
			emputils.successMessage(orders);
		}else {
			emputils.failureMessage(orders);
		}
		return new ResponseEntity(orders,HttpStatus.OK);
		
	}
	
	
	@RequestMapping("/getOrderInfoById/{id}")
	public ResponseEntity<OrderResponse> getOrderInfoById(@PathVariable(value="id") int order_id) {
		OrderResponse order = new OrderResponse();
		Order ord = ordersDAO.getOrderInfoByID(order_id);
		if(ord!= null) {
			order.setOrder(ord);
			emputils.successMessage(order);
		}else {
			emputils.failureMessage(order);
		}
		return new ResponseEntity(order,HttpStatus.OK);
	}
	
	@RequestMapping("/getProductDetails/{pid}")
	public ResponseEntity<ProductDetailResponse> getProductDetails(@PathVariable(value="pid") int productID) {
		ProductDetailResponse productResponse = new ProductDetailResponse();
		ProductDetail pD = ordersDAO.getProductDetails(productID);
		if(pD!=null) {
			productResponse.setProduct(pD);
			emputils.successMessage(productResponse);
		}else {
			emputils.failureMessage(productResponse);
		}
		return new ResponseEntity(productResponse,HttpStatus.OK);
	}
	
	
	@RequestMapping("/getOrderById/{id}")
	public ResponseEntity<OrderDetailsList> getOrderDetailsById(@PathVariable(value="id") int order_id) {
		OrderDetailsList ord = new OrderDetailsList();
		List<OrderDetails> orr = ordersDAO.getOrderDetails(order_id);
		if(orr.size()>0) {
			ord.setOrders(orr);
			emputils.successMessage(ord);
		}else {
			emputils.failureMessage(ord);
		}
		return new ResponseEntity(ord,HttpStatus.OK);
	}
	 
	

	
	@RequestMapping("/getAllEmployees")
	public ResponseEntity<Employees> getAllEmployees(){
		List<Employee> emp = empDAO.getAllEmployees();
		Employees employees = new Employees();
		employees.setEmployees(emp);
		emputils.successMessage(employees);
		return new ResponseEntity(employees,HttpStatus.OK);
		
	}
}

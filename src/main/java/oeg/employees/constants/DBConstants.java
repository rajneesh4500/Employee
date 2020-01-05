package oeg.employees.constants;

public class DBConstants {
	
	
		
	
	
	
	public static final String GET_EMP_BY_ID = "select employee_id,first_name,last_name,email,phone,job_title from employees"+
												" where employee_id = ?";
	
	public static final String GET_ALL_EMP_NEW = "{call emp_pkg.get_all_emp(?)}";
	
	public static final String GET_EMP_BYID = "{call emp_pkg.GET_EMP_ID(?,?)}";
	
	public static final String GET_ALL_ORDERS ="select order_id,customer_id,status,salesman_id,order_date from orders";
	
	public static final String GET_ORDER_BY_ID = "select order_id,customer_id,status,salesman_id,order_date from orders where order_id = ?";
	
	public static final String GET_ORDER_DETAILS = "{call emp_pkg.GET_ORDER_DETAILS(?,?)}";

}

package org.employee.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.employee.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import oeg.employees.constants.DBConstants;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.oracore.OracleType;





@Repository
public class EmployeeRepo {
	
	@Autowired
	private JdbcTemplate jdbcTmplt;
	
	
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = jdbcTmplt.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement calla = con.prepareCall(DBConstants.GET_ALL_EMP_NEW);
				calla.registerOutParameter(1, OracleTypes.CURSOR);
				return calla;
			}
		}, new CallableStatementCallback<List<Employee>>() {

			@Override
			public List<Employee> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				ResultSet rs = (ResultSet)cs.getObject(1);
				List<Employee> emplist = new LinkedList<Employee>();
				while(rs.next()) {
					Employee emp = new Employee();
					emp.setEmployee_id(Integer.parseInt(rs.getString(1)));
					emp.setFirstName(rs.getString(2));
					emp.setLastName(rs.getString(3));
					emp.setEmailId(rs.getString(4));
					emp.setPhoneNum(rs.getString(5));
					emp.setJob_title(rs.getString(6));
					emp.setManager_name(rs.getString(7));
					emp.setManager_id(rs.getInt(8));
					emplist.add(emp);
				}
				return emplist;
			}
			
		});
		
		return employeeList;
		
		
	}
	
	
	
	public Employee getEmployeeByID(int employee_id) {
		Employee emp = new Employee();
		emp = jdbcTmplt.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(DBConstants.GET_EMP_BYID);
				cs.registerOutParameter(2, OracleTypes.CURSOR);
				cs.setInt(1, employee_id);
				return cs;
			}
		}, new CallableStatementCallback<Employee>() {

			@Override
			public Employee doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				Employee emp = new Employee();
				cs.execute();
				ResultSet rs = (ResultSet)cs.getObject(2);
				while(rs.next()) {
					emp.setEmployee_id(Integer.parseInt(rs.getString(1)));
					emp.setFirstName(rs.getString(2));
					emp.setLastName(rs.getString(3));
					emp.setEmailId(rs.getString(4));
					emp.setPhoneNum(rs.getString(5));
					emp.setJob_title(rs.getString(6));
				}
				return emp;
			}
		});
		return emp;
	}

	
	
	

	
	
}

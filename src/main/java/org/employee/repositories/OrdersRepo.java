package org.employee.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.employee.models.Order;
import org.employee.models.OrderDetails;
import org.employee.models.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import oeg.employees.constants.DBConstants;
import oracle.jdbc.OracleTypes;




@Repository
public class OrdersRepo {
	
	@Autowired
	private JdbcTemplate jdTemplate;
	
	private class OrdersRowMapper implements RowMapper<Order> {

		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrder_id(rs.getInt(1));
			order.setCustomer_id(rs.getInt(2));
			order.setStatus(rs.getString(3));
			order.setSalesman_id(rs.getInt(4));
			order.setOrder_date(rs.getDate(5));
			return order;
		}
		
		
	}
	
	
	public List<OrderDetails> getOrderDetails(int order_id) {
		String sql = DBConstants.GET_ORDER_DETAILS;
		List<OrderDetails> orders = jdTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(sql);
				cs.registerOutParameter(2, OracleTypes.CURSOR);
				cs.setInt(1, order_id);
				return cs;
			}
		}, new CallableStatementCallback<List<OrderDetails>>() {

			@Override
			public List<OrderDetails> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				List<OrderDetails> ord= new LinkedList<OrderDetails>();
				ResultSet rs = (ResultSet)cs.getObject(2);
				while(rs.next()) {
					OrderDetails order = new OrderDetails();
					order.setOrder_id(order_id);
					order.setItem_id(rs.getInt(2));
					order.setProductName(rs.getString(3));
					order.setQuantity(rs.getInt(4));
					order.setUnitPrice(rs.getFloat(5));
					order.setProductID(rs.getInt(6));
					ord.add(order);
				}
				return ord;
			}
		});
		
		return orders;
	}
	
	
	public ProductDetail getProductDetails(int productID) {
		String sql = DBConstants.GET_PRODUCT_DETAILS;
		ProductDetail productDetail = jdTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(sql);
				cs.registerOutParameter(2, OracleTypes.CURSOR);
				cs.setInt(1, productID);
				return cs;
			}
		}, new CallableStatementCallback<ProductDetail>() {

			@Override
			public ProductDetail doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				ProductDetail pD = new ProductDetail();
				ResultSet rs = (ResultSet)cs.getObject(2);
				while(rs.next()) {
					pD.setProductId(rs.getInt(1));
					pD.setProductName(rs.getString(2));
					pD.setDescription(rs.getString(3));
					pD.setsCost(rs.getFloat(4));
					pD.setLprice(rs.getFloat(5));
					pD.setCategory(rs.getString(6));
				}
				return pD;
			}
			
		});
		return productDetail;
	}
	
	
	
	public Order getOrderInfoByID(int order_id) {
		String sql = DBConstants.GET_ORDER_BY_ID;
		Order order = jdTemplate.queryForObject(sql, new Object[] {order_id}, new OrdersRowMapper());
		return order;
	}
	
	
	public List<Order> getAllOrders() {
		String sql = DBConstants.GET_ALL_ORDERS;
		List<Order> orders = jdTemplate.query(sql ,new OrdersRowMapper());
		return orders;
	}
}

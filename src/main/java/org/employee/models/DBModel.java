package org.employee.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;




@Component
public class DBModel {
	@Value("spring.datasource.username")
	private String username;
	@Value("spring.datasource.password")
	private String password;
	@Value("spring.datasource.url")
	private String url;
	@Value("spring.datasource.driver-class-name")
	private String driverClass;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
}

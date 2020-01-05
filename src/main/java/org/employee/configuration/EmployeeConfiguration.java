package org.employee.configuration;

import javax.sql.DataSource;

import org.employee.models.DBModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;




@Configuration
public class EmployeeConfiguration {

	@Autowired
	private DBModel dbModel;
	
	@Bean("datas")
	@ConfigurationProperties("spring.datasource")
	public DataSource ds() {
		return  DataSourceBuilder.create().username(dbModel.getUsername()).password(dbModel.getPassword())
								.url(dbModel.getUrl()).driverClassName(dbModel.getDriverClass()).build();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource")
	public JdbcTemplate jdtemp(@Qualifier("datas") DataSource datasSource) {
		return new JdbcTemplate(datasSource);
	}
	
}

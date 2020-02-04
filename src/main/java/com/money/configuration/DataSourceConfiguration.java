package com.money.configuration;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import lombok.Getter;

@Getter
public class DataSourceConfiguration {

	@Inject
	@Named("db.driver.className")
	private String driverClassName;
	
	@Inject
	@Named("db.jdbcUrl")
	private String jdbcUrl;
	
	@Inject
	@Named("db.username")
	private String userName;
	
	@Inject
	@Named("db.password")
	private String password;
	
	@Inject
	@Named("jooq.sql.dialect")
	private String sqlDialect;
}

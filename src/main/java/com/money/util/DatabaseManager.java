package com.money.util;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import com.google.inject.Inject;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.money.configuration.DataSourceConfiguration;

public class DatabaseManager {

	private static DSLContext dslContext;
	private static DataSource dataSource;
	private DataSourceConfiguration dataSourceConfiguration;

	@Inject
	public DatabaseManager(DataSourceConfiguration dataSourceConfiguration) {
		this.dataSourceConfiguration = dataSourceConfiguration;
	}

	private void createDSLContext() {
		try {
			ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
			pooledDataSource.setDriverClass(dataSourceConfiguration.getDriverClassName());
			pooledDataSource.setUser(dataSourceConfiguration.getUserName());
			pooledDataSource.setPassword(dataSourceConfiguration.getPassword());
			pooledDataSource.setJdbcUrl(dataSourceConfiguration.getJdbcUrl());
			dataSource = pooledDataSource;
			dslContext = DSL.using(dataSource, SQLDialect.H2, new Settings().withExecuteWithOptimisticLocking(true));
		} catch (Exception e) {
			throw new RuntimeException("Error while connecting to database", e);
		}
	}

	public DSLContext getDSLContext() {

		if (dslContext == null) {
			createDSLContext();
		}
		return dslContext;
	}

	public void close() {
		try {
			DataSources.destroy(dataSource);
			dslContext.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

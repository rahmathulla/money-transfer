package com.money.module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.money.dao.AccountDAO;
import com.money.dao.AccountDAOImpl;
import com.money.dao.TransferDAO;
import com.money.dao.TransferDAOImpl;
import com.money.resource.ResourceExceptionMapper;
import com.money.service.AccountService;
import com.money.service.AccountServiceImpl;
import com.money.service.TransferService;
import com.money.service.TransferServiceImpl;
import com.money.util.DatabaseManager;

public class MoneyTransferModule extends AbstractModule {

	private static final String SERVER_FILE_NAME = "application.properties";
	private static final String DATASOURCE_FILE_NAME = "database.properties";

	@Override
	protected void configure() {

		Names.bindProperties(binder(), loadProperties(SERVER_FILE_NAME));
		Names.bindProperties(binder(), loadProperties(DATASOURCE_FILE_NAME));
		bind(DatabaseManager.class).asEagerSingleton();
		bind(ResourceExceptionMapper.class);
		bind(AccountService.class).to(AccountServiceImpl.class).asEagerSingleton();
		bind(TransferService.class).to(TransferServiceImpl.class).asEagerSingleton();
		bind(AccountDAO.class).to(AccountDAOImpl.class).asEagerSingleton();
		bind(TransferDAO.class).to(TransferDAOImpl.class).asEagerSingleton();

	}

	private Properties loadProperties(String file) {
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File '" + file + "' not found");
		} catch (IOException e) {
			throw new RuntimeException("File '" + file + "' not found");
		}
		return properties;
	}

}

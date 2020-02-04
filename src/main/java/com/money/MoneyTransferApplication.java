package com.money;

import com.money.configuration.ServerConfiguration;
import com.money.provider.GuiceInjectorProvider;
import com.money.util.JettyServer;

public class MoneyTransferApplication {

	public static void main(String[] args) {

		ServerConfiguration configuration = GuiceInjectorProvider.getInjector().getInstance(ServerConfiguration.class);

		JettyServer.start(configuration);
	}

}

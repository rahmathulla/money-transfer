package com.money.provider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.money.module.MoneyTransferModule;

public class GuiceInjectorProvider {

	private static Injector injector;

	static {
		injector = Guice.createInjector(new MoneyTransferModule());
	}

	public static Injector getInjector() {
		return injector;
	}
}

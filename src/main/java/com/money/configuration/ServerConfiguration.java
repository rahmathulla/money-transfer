package com.money.configuration;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import lombok.Getter;

@Getter
public class ServerConfiguration {

	@Inject
	@Named("app.server.port")
	private int serverPort;

	@Inject
	@Named("app.server.contextPath")
	private String contextPath;

	@Inject
	@Named("app.server.prefixPath")
	private String prefixPath;

	@Inject
	@Named("app.server.resourcePackage")
	private String resourcePackage;

	@Inject
	@Named("app.server.jettyPackages")
	private String jettyPackages;

}

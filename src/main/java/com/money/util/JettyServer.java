package com.money.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.money.configuration.ServerConfiguration;
import com.money.resource.AccountResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

public class JettyServer {

	public static Server start(ServerConfiguration configuration) {
		try {
			Resource.setDefaultUseCaches(false);
			buildSwagger(configuration);

			final HandlerList handlers = new HandlerList();
			handlers.addHandler(buildSwaggerUI(configuration));
			handlers.addHandler(buildContext(configuration));

			final Server server = new Server(configuration.getServerPort());
			server.setHandler(handlers);
			startServer(server);

			return server;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	private static void buildSwagger(ServerConfiguration configuration) {

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setResourcePackage(configuration.getResourcePackage());
		beanConfig.setScan(true);
		beanConfig.setBasePath(configuration.getContextPath());
		beanConfig.setTitle("Swagger");
	}

	private static ContextHandler buildContext(ServerConfiguration configuration) {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.packages(AccountResource.class.getPackage().getName(),
				ApiListingResource.class.getPackage().getName());

		ServletContainer servletContainer = new ServletContainer(resourceConfig);
		ServletHolder entityBrowser = new ServletHolder(servletContainer);
		entityBrowser.setInitOrder(1);
		entityBrowser.setInitParameter(configuration.getJettyPackages(), configuration.getResourcePackage());
		ServletContextHandler entityBrowserContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		entityBrowserContext.setContextPath(configuration.getContextPath());
		entityBrowserContext.addServlet(entityBrowser, configuration.getPrefixPath());

		return entityBrowserContext;
	}

	private static ContextHandler buildSwaggerUI(ServerConfiguration configuration) throws Exception {
		final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
		swaggerUIResourceHandler.setResourceBase(JettyServer.class.getClassLoader()
				.getResource("META-INF/resources/webjars/swagger-ui/2.1.4").toURI().toString());
		final ContextHandler swaggerUIContext = new ContextHandler();
		swaggerUIContext.setContextPath(configuration.getContextPath());
		swaggerUIContext.setHandler(swaggerUIResourceHandler);

		return swaggerUIContext;
	}

	private static void startServer(Server server) {
		try {
			server.start();
			server.join();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			server.destroy();
		}
	}

}

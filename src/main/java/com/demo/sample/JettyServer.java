package com.demo.sample;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.demo.sample.controller.BooksHandler;
import com.demo.sample.service.BooksService;

public class JettyServer {
	private static final Integer port = 3000;

	private BooksService booksService;
	private Server server;

	public JettyServer(BooksService booksService) {
		this.booksService = booksService;
	}

	public void start() throws Exception {
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setName("server");

		server = new Server(threadPool);

		HttpConfiguration httpConfiguration = new HttpConfiguration();
		HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfiguration);

		ServerConnector serverConnector = new ServerConnector(server, httpConnectionFactory);
		serverConnector.setPort(port);
		server.addConnector(serverConnector);

		ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
		contextHandlerCollection.addHandler(new ContextHandler(new BooksHandler(booksService), "/books"));
		server.setHandler(contextHandlerCollection);

		server.start();
		server.join();
	}
}

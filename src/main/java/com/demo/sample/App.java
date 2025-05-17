package com.demo.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.sample.dao.BooksDictionary;
import com.demo.sample.service.BooksService;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main( String[] args ) {
		logger.debug("App.main(): start");

		BooksDictionary booksDictionary = new BooksDictionary();
		BooksService booksService = new BooksService(booksDictionary);

		JettyServer jettyServer = new JettyServer(booksService);
		try {
			jettyServer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("App.main(): koniec");
	}
}

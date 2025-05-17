package com.demo.sample.service;

import java.util.List;

import com.demo.sample.dao.BooksDictionary;
import com.demo.sample.model.Book;

public class BooksService {
	private BooksDictionary booksDictionary;

	public BooksService(BooksDictionary booksDictionary) {
		this.booksDictionary = booksDictionary;
	}

	public List<Book> findAll(String instanceId) {
		return booksDictionary.findAll();
	}

	public Book findByUid(String instanceId, String uid) {
		return booksDictionary.findByUid(uid);
	}

	public Book save(String instanceId, Book book) {
		return booksDictionary.save(book);
	}

	public Book modify(String instanceId, String uid, Book book) {
		return booksDictionary.modify(uid, book);
	}

	public void deleteByUid(String instanceId, String uid) {
		booksDictionary.deleteByUid(uid);
	}
}

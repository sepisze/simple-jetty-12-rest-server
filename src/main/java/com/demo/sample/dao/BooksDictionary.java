package com.demo.sample.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.demo.sample.model.Book;

public class BooksDictionary {
	private List<Book> books;

	public BooksDictionary() {
		this.books = new ArrayList<>();

		initnialize();
	}

	private void initnialize() {
		books.add(new Book("c98432bf-12a9-4619-b451-ef406468e861", "Sto lat samotności", "Gabriel Garcia Marquez"));
		books.add(new Book("4fae0478-6e49-4eb1-8441-a5a6f9e3b3e2", "Cień wiatru", "Carlos Ruiz Zafon"));
		books.add(new Book("8ab83f35-4e7f-47dc-9013-4f02758a9c08", "Góry śpiewają", "Nguyen Phan Que Mai"));
	}

	public List<Book> findAll() {
		return books;
	}

	public Book findByUid(String uid) {
		Book found = books.stream().filter(item -> item.uid.equals(uid)).findFirst().orElse(null);
		return found;
	}

	public Book save(Book book) {
		String uid = UUID.randomUUID().toString();
		Book newBook = new Book(uid, book.title, book.author);
		books.add(newBook);
		return newBook;
	}

	public Book modify(String uid, Book book) {
		Book found = books.stream().filter(item -> item.uid.equals(uid)).findFirst().orElse(null);
		if (found == null) {
			throw new RuntimeException("Item not found uid=[" + uid + "].");
		}
		found.title = book.title;
		found.author = book.author;
		return found;
	}

	public void deleteByUid(String uid) {
		Book found = books.stream().filter(item -> item.uid.equals(uid)).findFirst().orElse(null);
		if (found == null) {
			throw new RuntimeException("Item not found uid=[" + uid + "].");
		}
		books.remove(found);
	}
}

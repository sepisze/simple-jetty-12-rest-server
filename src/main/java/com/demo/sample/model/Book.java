package com.demo.sample.model;

public class Book {

	public String uid;
	public String title;
	public String author;

	public Book() {
	}

	public Book(String uid, String title, String author) {
		this.uid = uid;
		this.title = title;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [uid=" + uid + ", title=" + title + ", author=" + author + "]";
	}

}

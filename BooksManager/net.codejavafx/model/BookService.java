package model;

import java.util.ArrayList;
import java.util.List;

public class BookService {
	private List<Book> bookList;
	private long nextId;

	public BookService() {
		this.bookList = new ArrayList<>();
		this.nextId = 1;
		initializeBookList();
	}

	private void initializeBookList() {
		create(new Book("Java ist auch eine Insel", "Rheinwerk Computing", "978-3-8362-1802-3", "Christian Ullenboom",
				"2011"));
	}

	public void create(Book book) {
		book.setId(nextId);
		bookList.add(book);
		nextId++;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void update(Book book) {
		int index = bookList.indexOf(book);
		bookList.set(index, book);
	}

	public void delete(Book book) {
		bookList.remove(book);
	}
}

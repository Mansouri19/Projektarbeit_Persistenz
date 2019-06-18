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
		create(new Book("Professionell entwickeln mit Java EE 7", "Rheinwerk Computing", "978-3-8362-2004-0",
				"Alexander Salvanos", "2014"));
		create(new Book("OCEJWCD Study Companion", "Garner Press", "978-0955160349", "Charles Lyons",
				"15. August 2012"));
	}

	public void create(Book book) {
		book.setId(nextId); // nächste freie ID
		bookList.add(book); // Bei Einsatz von Datenbanken, wird das Buch in die Datenbank gespeichert.
		nextId++;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void update(Book book) {
		int index = bookList.indexOf(book); // basiert auf die equals() Methode (basiert auf Gleichheit der id's)
		bookList.set(index, book);
	}

	public void delete(Book book) {
		bookList.remove(book);
	}
}

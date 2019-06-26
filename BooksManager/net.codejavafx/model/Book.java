package model;

import java.util.Objects;

public class Book {
	private Long id;
	private String title;
	private String publisher;
	private String isbn;
	private String author; // Person author ???
	private String date;

	public Book() {
	}

	public Book(String title, String publisher, String isbn, String author, String date) {
		this.title = title;
		this.publisher = publisher;
		this.isbn = isbn;
		this.author = author;
		this.date = date;
	}

	public Book(Long id, String title, String publisher, String isbn, String author, String date) {
		this(title, publisher, isbn, author, date);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Book other = (Book) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		/* , isbn, author, date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) */
		return String.format("(%d) - %s - %s", id, title, publisher);
	}
}

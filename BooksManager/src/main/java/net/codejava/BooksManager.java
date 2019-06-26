package net.codejava;

import javax.persistence.Query;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.codejava.Book;

public class BooksManager {

	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {

		begin();

//		create();
		update();
//		find();
//		query();
//		remove();

		end();
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	public static void create() {
		Book newBook = new Book();
		newBook.setTitle("Java 7 Das Übungsbuch");
		newBook.setAuthor("Elisabeth Jung ");
		newBook.setPrice(38);
		entityManager.persist(newBook);
	}

	public static void update() {
		Book existBook = new Book();
		existBook.setBookId(6);
		existBook.setTitle("Java SE 8 Programmer II");
		existBook.setAuthor("Bert Bates");
		existBook.setPrice(50);
		entityManager.merge(existBook);
	}

	public static void find() {
		Integer primaryKey = 2;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
	}

	public static List<Book> getBookList() {
		String jpql = "Select b From Book b Where b.price < 40";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Book> listBooks = query.getResultList();

		for (Book aBook : listBooks) {
			System.out.println(aBook.getTitle() + ", " + aBook.getAuthor() + ", " + aBook.getPrice());
			assertTrue(aBook.getPrice() < 40);
		}
		return listBooks;
	}

	public static void remove() {
		Integer primaryKey = 8;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}

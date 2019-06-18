package net.codejava;

import javax.persistence.Query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	private static void create() {
		Book newBook = new Book();
		newBook.setTitle("Java 7 Das Übungsbuch");
		newBook.setAuthor("Elisabeth Jung ");
		newBook.setPrice(40);
		entityManager.persist(newBook);
	}

	private static void update() {
		Book existBook = new Book();
		existBook.setBookId(3);
		existBook.setTitle("Thinking in Java (2nd Edition)");
		existBook.setAuthor("Bruce Eckel");
		existBook.setPrice(30);
		entityManager.merge(existBook);
	}

	private static void find() {
		Integer primaryKey = 2;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
	}

	private static void query() {
		String jpql = "Select b From Book b Where b.price < 40";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Book> listBooks = query.getResultList();
		
		for (Book aBook : listBooks) {
			System.out.println(aBook.getTitle() + ", "+ aBook.getAuthor() + ", " + aBook.getPrice() );
		}
	}
	
	private static void remove() {
		Integer primaryKey = 7;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}

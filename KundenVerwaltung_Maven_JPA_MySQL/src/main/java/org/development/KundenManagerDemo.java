package org.development;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class KundenManagerDemo {
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
		factory = Persistence.createEntityManagerFactory("KundeDemoUnit");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	private static void create() {
		KundeDemo newKunde = new KundeDemo();
		newKunde.setKundeNachname("Smith");
		newKunde.setKundeVorname("Adam");
		newKunde.setKundeAlter(40);
		newKunde.setKundeAbteilung("Production");
		entityManager.persist(newKunde);
	}

	private static void update() {
		KundeDemo existKunde = new KundeDemo();
		existKunde.setKundeId(2);
		existKunde.setKundeNachname("Kaiser");
		existKunde.setKundeVorname("Michael");
		existKunde.setKundeAlter(30);
		existKunde.setKundeAbteilung("Research");
		entityManager.merge(existKunde);
	}

	private static void find() {
		Integer primaryKey = 2;
		KundeDemo kundeDemo = entityManager.find(KundeDemo.class, primaryKey);
		System.out.println(kundeDemo.getKundeNachname());
		System.out.println(kundeDemo.getKundeVorname());
		System.out.println(kundeDemo.getKundeAlter());
		System.out.println(kundeDemo.getKundeAbteilung());
	}

	private static void query() {
		String jpql = "Select b From KundeDemo b Where b.alter < 40";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<KundeDemo> listBooks = query.getResultList();
		
		for (KundeDemo aKunde : listBooks) {
			System.out.println(aKunde.getKundeNachname() + ", "+ aKunde.getKundeVorname() + ", " + aKunde.getKundeAlter()
			+ aKunde.getKundeAbteilung());
		}
	}
	
	private static void remove() {
		Integer primaryKey = 3;
		KundeDemo reference = entityManager.getReference(KundeDemo.class, primaryKey);
		entityManager.remove(reference);
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}

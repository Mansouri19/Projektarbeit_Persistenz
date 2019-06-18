package development.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KundenManagerDemo {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("KundeDemoUnit");
		EntityManager entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();

		KundeDemo newKundeDemo = new KundeDemo();
		newKundeDemo.setKundeNachname("Smith");
		newKundeDemo.setKundeVorname("Adam");
		entityManager.persist(newKundeDemo);

		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}

package org.development;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class KundeDemoTest {

	static EntityManagerFactory factory;
	static EntityManager entityManager;

	@BeforeAll
	void begin() {
		factory = Persistence.createEntityManagerFactory("KundeDemoUnit");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@Test
	void query() {
		String jpql = "Select b From KundeDemo b Where b.alter < 40";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<KundeDemo> listKunden = query.getResultList();

		for (KundeDemo aKunde : listKunden) {
			System.out.println(aKunde.getKundeNachname() + ", " + aKunde.getKundeVorname() + ", "
					+ aKunde.getKundeAlter() + aKunde.getKundeAbteilung());
			assertTrue(aKunde.getKundeAlter() < 40);
		}
	}

	@AfterAll
	void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}

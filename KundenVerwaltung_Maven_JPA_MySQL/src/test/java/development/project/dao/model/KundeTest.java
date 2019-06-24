package development.project.dao.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class KundeTest {

	private static EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("KundeUnit");

	@Test
	void updateKunde(Integer kundeId, String vorname, String nachname, String geburtsdatum, String adresse,
			String telNr) {
		EntityManager entityManager = EM_FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		Kunde existkunde = null;

		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Integer primaryKey = 2;
			existkunde = entityManager.find(Kunde.class, primaryKey);

			existkunde.setKundeId(kundeId);
			Integer actual = existkunde.getKundeId();

			existkunde.setVorname(vorname);
			existkunde.setNachname(nachname);
			existkunde.setGeburtsdatum(geburtsdatum);
			existkunde.setAdresse(adresse);
			existkunde.setTelNr(telNr);

			Integer expected = kundeId;
			assertEquals(expected, actual);  // JUnit Test
		} catch (Exception e) {
			if (entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@Test
	void getKundenList() {
		EntityManager entityManager = EM_FACTORY.createEntityManager();
		entityManager.getTransaction().begin();
		String query = "SELECT k FROM Kunde k WHERE k.kundeId < 3";
		TypedQuery<Kunde> tq = entityManager.createQuery(query, Kunde.class);
		List<Kunde> kunden;
		try {
			kunden = tq.getResultList();
			kunden.forEach(k -> {
				System.out.println(k);
				assertTrue(k.getKundeId() > 7); // JUnit Test
			});
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	@AfterAll
	static void end() {
		EM_FACTORY.close();
	}

}

package development.project.dao.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class KundeTest {

	private static EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("KundeUnit");

	@Test
	public static List<Kunde> getKundenList() {
		EntityManager entityManager = EM_FACTORY.createEntityManager();
		String query = "SELECT k FROM Kunde k WHERE k.kundeId < 3";
		TypedQuery<Kunde> tq = entityManager.createQuery(query, Kunde.class);
		List<Kunde> kunden;
		try {
			kunden = tq.getResultList();
			kunden.forEach(k -> System.out.println(k));
			return kunden;
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}
	
	@AfterAll
	static void end() {
		EM_FACTORY.close();
	}

}

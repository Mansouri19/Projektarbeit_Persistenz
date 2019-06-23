package development.project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import development.project.dao.DAOFactory;
import development.project.dao.model.Kunde;
import development.project.persistent.KundenCache;
import kunden.lib.CommonLib;

public class KundenTest {

	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("KundeUnit");

	public static void main(String[] args) {

		addKunde(1, "Adam", "Smith", "12.06.85", "GartenStr. 6", "+49881233");
		FACTORY.close();

//		kundentest();
	}

	public static void addKunde(Integer kundeId, String vorname, String nachname, String geburtsdatum, String adresse,
			String telNr) {
		EntityManager entityManager = FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Kunde neukunde = new Kunde();
			neukunde.setKundeId(kundeId);
			neukunde.setVorname(vorname);
			neukunde.setNachname(nachname);
			neukunde.setGeburtsdatum(geburtsdatum);
			neukunde.setAdresse(adresse);
			neukunde.setTelNr(telNr);

			entityManager.persist(neukunde);
			entityTransaction.commit();
		} catch (Exception e) {
			if (entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public static void getKunde(Integer kundeId) {
		EntityManager entityManager = FACTORY.createEntityManager();
		String query = "SELECT k FROM Kunde k WHERE k.kundeId = : kunde_id";
		TypedQuery<Kunde> tq = entityManager.createQuery(query, Kunde.class);
		tq.setParameter("kunde_id", kundeId);
		Kunde kunde = null;
		try {
			kunde = tq.getSingleResult();
			System.out.println(kunde);
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

	}

	public static void getKundenList(Integer kundeId) {
		EntityManager entityManager = FACTORY.createEntityManager();
		String query = "SELECT k FROM Kunde k WHERE k.kundeId IS NOT NULL";
		TypedQuery<Kunde> tq = entityManager.createQuery(query, Kunde.class);
		List<Kunde> kunden;
		try {
			kunden = tq.getResultList();
			kunden.forEach(k -> System.out.println(k.getVorname() + " " + k.getNachname()));
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

	}

	public static void updateName(Integer kundeId, String vorname, String nachname) {
		EntityManager entityManager = FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		Kunde existkunde = null;

		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			existkunde = entityManager.find(Kunde.class, kundeId);
			existkunde.setVorname(vorname);
			existkunde.setNachname(nachname);

			entityManager.persist(existkunde);
			entityTransaction.commit();
		} catch (Exception e) {
			if (entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public static void deleteKunde(Integer kundeId) {
		EntityManager entityManager = FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		Kunde kunde = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			entityManager.find(Kunde.class, kundeId);
			entityManager.remove(kunde);

			entityManager.persist(kunde);
			entityTransaction.commit();
		} catch (Exception e) {
			if (entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

// ------------------------------------
	private static void kundentest() {
		List<Kunde> kundenListeOriginal = DAOFactory.getKundenDAO().simulateIncomingKundenData();

		System.out.println("Frisch hereingekommene Kundendaten:\n-----");
		kundenListeOriginal.forEach((Kunde k) -> System.out.println(k.toString()));
		System.out.println();

		System.out.println("Referenzen der Kunden-Objekte in Original-Kunden-Liste:\n-----");
		showListReferences(kundenListeOriginal);

		/* Kunden im Applikations-Cache ablegen */
		kundenListeOriginal.forEach((Kunde k) -> k.store());

		/* Eine Bearbeitungsliste erstellen */
		List<Kunde> kundenListeCopy = new ArrayList<>();

		System.out.println("Erstelle Kopie der Original-Kunden-Liste zur Bearbeitung!");
		System.out.println();

		for (Kunde k : kundenListeOriginal) {
			kundenListeCopy.add(k.getCopy());
		}

		System.out.println("Referenzen der Kunden-Objekte in der Kopie-Kunden-Liste:\n-----");
		showListReferences(kundenListeCopy);

		System.out.println("Bearbeitungsliste vor Bearbeitung:\n-----");
		kundenListeCopy.forEach((Kunde k) -> System.out.println(k.toString()));

		KundenBearbeitung.correctNames(kundenListeCopy);

		System.out.println();
		System.out.println("Bearbeitungsliste nach Bearbeitung:\n-----");
		kundenListeCopy.forEach((Kunde k) -> System.out.println(k.toString()));

		System.out.println();
		System.out.println("Inhalt der Original-Kundenliste:\n-----");
		kundenListeOriginal.forEach((Kunde k) -> System.out.println(k.toString()));
		// @TODO:
		System.out.println();
		System.out.println(new StringBuilder("Inhalt der Kundendaten im Cache (Size ")
				.append(KundenCache.getInstance().size()).append("):\n-----").toString());
		KundenCache.showCacheEntries();

		System.out.println();
		System.out.println("Aktualisiere Orginal-Kunden-Liste");
		kundenListeOriginal = kundenListeCopy;

		System.out.println();
		System.out.println("Inhalt der Original-Kundenliste nach Aktualisierung:\n-----");
		kundenListeOriginal.forEach((Kunde k) -> System.out.println(k.toString()));

		System.out.println();
		System.out.println("Speichere aktualisierte Kundenliste im Cache und in der DB!\n-----");
		DAOFactory.getKundenDAO().storeList(kundenListeCopy);

		System.out.println();
		System.out.println(new StringBuilder("Inhalt der Kundendaten im Cache nach Aktualisierung (Size ")
				.append(KundenCache.getInstance().size()).append("):\n-----").toString());
		KundenCache.showCacheEntries();

		kundenListeOriginal.clear();
		kundenListeOriginal = null;
		kundenListeCopy.clear();
		kundenListeCopy = null;
		System.gc();
	}

	private static void showListReferences(List<Kunde> kunden) {
		for (Kunde k : kunden) {
			System.out.println(CommonLib.objectRef(k));
		}

		System.out.println();
	}

}

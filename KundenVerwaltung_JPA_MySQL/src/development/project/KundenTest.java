package development.project;

import java.util.ArrayList;
import java.util.List;

import development.project.dao.DAOFactory;
import development.project.dao.model.Kunde;
import development.project.persistent.KundenCache;
import kunden.lib.CommonLib;

public class KundenTest {
	public static void main(String[] args) {
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

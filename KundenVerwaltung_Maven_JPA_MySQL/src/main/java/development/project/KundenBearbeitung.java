package development.project;

import java.util.List;

import development.project.dao.model.Kunde;

public abstract class KundenBearbeitung {
	public static List<Kunde> correctNames(List<Kunde> kunden) {
		for (Kunde k : kunden) {
			k.setVorname(new StringBuilder(k.getVorname())
					.replace(0, 1, String.valueOf(k.getVorname().charAt(0)).toUpperCase()).toString());
			k.setNachname(new StringBuilder(k.getNachname())
					.replace(0, 1, String.valueOf(k.getNachname().charAt(0)).toUpperCase()).toString());
		}

		return kunden;
	}

	public static Kunde correctName(Kunde k) {

		k.setVorname(new StringBuilder(k.getVorname())
				.replace(0, 1, String.valueOf(k.getVorname().charAt(0)).toUpperCase()).toString());
		k.setNachname(new StringBuilder(k.getNachname())
				.replace(0, 1, String.valueOf(k.getNachname().charAt(0)).toUpperCase()).toString());

		return k;
	}

}

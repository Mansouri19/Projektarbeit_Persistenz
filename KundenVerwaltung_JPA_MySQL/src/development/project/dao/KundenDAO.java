package development.project.dao;

import java.util.ArrayList;
import java.util.List;

import development.project.dao.model.Kunde;
import development.project.persistent.KundenCache;

// DAO = Data access object

public final class KundenDAO {

	private static KundenDAO instance;

	private KundenDAO() {
	}

	protected static KundenDAO getInstance() {
		if (instance == null)
			instance = new KundenDAO();

		return instance;
	}

	private Kunde retrieveKundeByDTOId(int id) {
		return KundenCache.getInstance().get(id);
	}

	@SuppressWarnings("serial")
	public List<Kunde> simulateIncomingKundenData() {
		return new ArrayList<Kunde>() {
			{
				add(new Kunde("Hanne", "Schmidt"));
				add(new Kunde("Steffen", "Meier"));
				add(new Kunde("Hans", "Glück"));
				add(new Kunde("Adam", "Smith"));
			}
		};
	}

	public void storeList(List<Kunde> kunden) {
		for (Kunde k : kunden) {
			boolean isTouched = k.isTouched();

			k.store();

			if (isTouched) {
				System.out.println(
						new StringBuilder("Speichere Kunden in DB: ").append(this.retrieveKundeByDTOId(k.getID())));
			}
		}
	}

}

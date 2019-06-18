package development.project.persistent;

import java.util.Map;
import java.util.TreeMap;

import development.project.dao.model.Kunde;

@SuppressWarnings("serial")
public final class KundenCache extends TreeMap<Integer, Kunde> {
	private static KundenCache instance;

	private KundenCache() {
	}

	public static KundenCache getInstance() {
		if (instance == null) {
			instance = new KundenCache();
		}

		return instance;
	}

	public static void showCacheEntries() {
		if (instance != null) {
			for (Map.Entry<Integer, Kunde> entry : instance.entrySet()) {
				System.out.println(entry.getValue().toString());
			}
		} else {
			System.out.println("Der Kunden-Cache ist leer!");
		}
	}

}

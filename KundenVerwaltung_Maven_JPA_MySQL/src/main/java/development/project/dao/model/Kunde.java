package development.project.dao.model;

import development.project.dto.BasicDTO;
import development.project.persistent.KundenCache;

public class Kunde extends BasicDTO implements Cloneable, KundeInterface {
	private String vorname;
	private String nachname;

	public Kunde(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}

	@Override
	public void store() {
		this.markAsTouched(false);
		KundenCache.getInstance().put(this.getID(), this);
	}

	@Override
	public void setVorname(String vorname) {
		if (!this.isTouched())
			this.markAsTouched((!this.vorname.equals(vorname)));

		this.vorname = vorname;
	}

	@Override
	public String getVorname() {
		return this.vorname;
	}

	@Override
	public void setNachname(String nachname) {
		if (!this.isTouched())
			this.markAsTouched((!this.nachname.equals(nachname)));

		this.nachname = nachname;
	}

	@Override
	public String getNachname() {
		return this.nachname;
	}

	@Override
	public Kunde getCopy() {
		try {
			return ((Kunde) this.clone());
		} catch (CloneNotSupportedException cnsex) {
			return null;
		}
	}

	@Override
	public String toString() {
		return new StringBuilder("Kunde ID ").append(this.getID()).append(": ").append(this.getVorname()).append(" ")
				.append(this.getNachname()).append(" / modifziert: ").append(this.isTouched()).toString();
	}

}

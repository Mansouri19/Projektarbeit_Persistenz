package development.project.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import development.project.dto.BasicDTO;
import development.project.persistent.KundenCache;

@Entity
public class Kunde extends BasicDTO implements Cloneable, KundeInterface {
	private String vorname;
	private String nachname;

	public Kunde() {
	}

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

	@Id
	@Column(name = "")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

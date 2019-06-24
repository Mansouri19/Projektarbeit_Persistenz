package development.project.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import development.project.dto.BasicDTO;
import development.project.persistent.KundenCache;

@Entity
@Table(name = "kunde")
public class Kunde extends BasicDTO implements Serializable, Cloneable, KundeInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "kunde_id", unique = true)
	private Integer kundeId;

	@Column(name = "vorname", nullable = false)
	private String vorname;

	@Column(name = "nachname", nullable = false)
	private String nachname;

	@Column(name = "geburtsdatum", nullable = false)
	private String geburtsdatum;

	private String adresse;
	private String telNr;
//	private byte[] bild;

	public Kunde() {
	}

	public Kunde(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public Kunde(Integer kundeId, String vorname, String nachname, String geburtsdatum, String adresse, String telNr) {
		this.kundeId = kundeId;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.adresse = adresse;
		this.telNr = telNr;
	}

	public void setKundeId(Integer kundeId) {
		this.kundeId = kundeId;
	}

	public Integer getKundeId() {
		return kundeId;
	}

	@Override
	public void setVorname(String vorname) {
//		if (!this.isTouched())
//			this.markAsTouched((!this.vorname.equals(vorname)));
		this.vorname = vorname;
	}

	@Override
	public String getVorname() {
		return this.vorname;
	}

	@Override
	public void setNachname(String nachname) {
//		if (!this.isTouched())
//			this.markAsTouched((!this.nachname.equals(nachname)));
		this.nachname = nachname;
	}

	@Override
	public String getNachname() {
		return this.nachname;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}

	@Override
	public void store() {
		this.markAsTouched(false);
		KundenCache.getInstance().put(this.getID(), this);
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
		return new StringBuilder("Kunde ID ").append(this.getID()).append(": ").append(this.getVorname()).append("  ")
				.append(this.getNachname()).append("  |").append(this.getGeburtsdatum()).append("  |")
				.append(this.getAdresse()).append("  |").append(this.getTelNr()).append("  / modifziert: ")
				.append(this.isTouched()).toString();
	}

}

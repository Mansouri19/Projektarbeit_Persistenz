package org.development;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KundeDemo {
	private Integer kundeId;
	private String kundeNachname;
	private String kundeVorname;
	private Integer kundeAlter;
	private String kundeAbteilung;

	@Id
	@Column(name = "kundedemo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getKundeId() {
		return kundeId;
	}

	public void setKundeId(Integer kundeId) {
		this.kundeId = kundeId;
	}

	public String getKundeNachname() {
		return kundeNachname;
	}

	public void setKundeNachname(String kundeNachname) {
		this.kundeNachname = kundeNachname;
	}

	public String getKundeVorname() {
		return kundeVorname;
	}

	public void setKundeVorname(String kundeVorname) {
		this.kundeVorname = kundeVorname;
	}

	public Integer getKundeAlter() {
		return kundeAlter;
	}

	public void setKundeAlter(Integer kundeAlter) {
		this.kundeAlter = kundeAlter;
	}

	public String getKundeAbteilung() {
		return kundeAbteilung;
	}

	public void setKundeAbteilung(String kundeAbteilung) {
		this.kundeAbteilung = kundeAbteilung;
	}
	
	

}

package development.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KundeDemo {
	private Integer kundeId;
	private String nachname;
	private String vorname;

	@Id
	@Column(name = "kunde_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getKundeId() {
		return kundeId;
	}

	public void setKundeId(Integer kundeId) {
		this.kundeId = kundeId;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}

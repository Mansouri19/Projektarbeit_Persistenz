package development.demo;

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

	public String getKundeVorname() {
		return kundeVorname;
	}

	public void setKundeNachname(String kundeNachname) {
		this.kundeNachname = kundeNachname;
	}

	public void setKundeVorname(String kundeVorname) {
		this.kundeVorname = kundeVorname;
	}

	


}

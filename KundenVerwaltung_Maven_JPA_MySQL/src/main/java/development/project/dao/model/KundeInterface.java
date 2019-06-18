package development.project.dao.model;

public interface KundeInterface {
	public void store();

	public void setVorname(String vorname);

	public String getVorname();

	public void setNachname(String nachname);

	public String getNachname();

	public Kunde getCopy();

}

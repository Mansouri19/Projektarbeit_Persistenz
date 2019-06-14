package development.project.dao;

public abstract class DAOFactory {
	public static KundenDAO getKundenDAO() {
		return KundenDAO.getInstance();
	}
}

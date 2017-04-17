package Database;

public class RelationalDAO implements iDAO2 {
	
	public static RelationalDAO _SINGLETON = new RelationalDAO();

	private RelationalGameDAO gameDAO;
	private RelationalUserDAO userDAO;
	
	private RelationalDAO() {}
	
	public RelationalGameDAO getGameDAO() {
		return gameDAO;
	}
	public void setGameDAO(RelationalGameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
	
	public RelationalUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(RelationalUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}

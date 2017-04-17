package Database;

public class RelationalDAO implements iDAO2 {
	
	private RelationalGameDAO gameDAO;
	private RelationalUserDAO userDAO;
	
	public RelationalDAO() {
		gameDAO = new RelationalGameDAO();
		userDAO = new RelationalUserDAO();
	}
	
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

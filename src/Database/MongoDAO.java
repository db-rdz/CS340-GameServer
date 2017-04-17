package Database;

public class MongoDAO implements iDAO2 {
	
	private MongoGameDAO gameDAO;
	private MongoUserDAO userDAO;
	
	public MongoDAO() {
		gameDAO = new MongoGameDAO();
		userDAO = new MongoUserDAO();
	}
	
	public MongoGameDAO getGameDAO() {
		return gameDAO;
	}
	
	public void setGameDAO(MongoGameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
	
	public MongoUserDAO getUserDAO() {
		return userDAO;
	}
	
	public void setUserDAO(MongoUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}

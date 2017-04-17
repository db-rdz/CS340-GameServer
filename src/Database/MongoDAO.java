package Database;

public class MongoDAO implements iDAO2 {
	
	public static MongoDAO _SINGLETON = new MongoDAO();

	private MongoGameDAO gameDAO;
	private MongoUserDAO userDAO;
	
	private MongoDAO() {}
	
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

//package MongoDatabase;
//
//import DatabaseInterfaces.iDAO2;
//
//public class MongoDAO implements iDAO2 {
//	
//	private MongoDB _db;
//	private MongoGameDAO gameDAO;
//	private MongoUserDAO userDAO;
//	
//	public MongoDAO() {
//		_db = new MongoDB();
//		gameDAO = new MongoGameDAO();
//		userDAO = new MongoUserDAO();
//	}
//	
//	@Override
//	public iDAO2 initDAO() {
//		return new MongoDAO();
//	}
//	
//	public MongoGameDAO getGameDAO() {
//		return gameDAO;
//	}
//	
//	public void setGameDAO(MongoGameDAO gameDAO) {
//		this.gameDAO = gameDAO;
//	}
//	
//	public MongoUserDAO getUserDAO() {
//		return userDAO;
//	}
//	
//	public void setUserDAO(MongoUserDAO userDAO) {
//		this.userDAO = userDAO;
//	}
//
//}

package RelationalDatabase;

import DatabaseInterfaces.iDAO2;

public class RelationalDAO implements iDAO2 {
	
	private RelationalDB _db;
	private RelationalGameDAO gameDAO;
	private RelationalUserDAO userDAO;

	
	public RelationalDAO() {
		_db = new RelationalDB(); //Set the database in the constructor
		gameDAO = new RelationalGameDAO(_db); //Each DAO needs access to the singlar created Database
		userDAO = new RelationalUserDAO(_db);
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

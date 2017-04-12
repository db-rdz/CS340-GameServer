package Database;

public class DAO2 implements iDAO2 {
	
	public static iDAO2 _SINGLETON = new DAO2();
	
	private IGameDAO gameDAO;
	private IUserDAO userDAO;
	
	public DAO2() {}

	@Override
	public IGameDAO getIGameDAO() {
		return gameDAO;
	}
	
	@Override
	public void setIGameDAO(IGameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}

	@Override
	public IUserDAO getIUserDAO() {
		return userDAO;
	}
	
	@Override
	public void setIUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}

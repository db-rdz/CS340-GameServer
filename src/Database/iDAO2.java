package Database;

/**
 * Will change the class to iDAO once everything is up to date.
 * @author natha
 *
 */

public interface iDAO2 {
	
	public IGameDAO getIGameDAO();
	public void setIGameDAO(IGameDAO gameDAO);
	public IUserDAO getIUserDAO();
	public void setIUserDAO(IUserDAO userDAO);

}

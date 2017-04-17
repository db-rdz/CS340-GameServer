package RelationalDatabase;

import java.sql.SQLException;
import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Server.IServer.UserAlreadyLoggedIn;
import UserModel.User;

public interface IUserDAO {
	
	public Boolean login(String userName, String password) throws SQLException, InvalidUsername, InvalidPassword, UserAlreadyLoggedIn;
	
	public Boolean registerUser(String userName, String password) throws SQLException;
	
    public void updateUserToken(String username, String authenticationCode);

    public User getUserByUserName(String username) throws SQLException, UserAlreadyLoggedIn;

    public User getUserByAccessToken(String token) throws SQLException;

    public List<User> getAllUsers() throws SQLException;


}

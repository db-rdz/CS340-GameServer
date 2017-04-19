package RelationalDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import DatabaseInterfaces.IUserDAO;
import Server.IServer.UserAlreadyLoggedIn;
import UserModel.User;

public class RelationalUserDAO implements IUserDAO {
	
    private static RelationalDB _db;
    private int AUTH_TOKEN_LENGTH = 15;
    
    public RelationalUserDAO(RelationalDB _db) {
		this._db = _db;
	}

	@Override
	public Boolean login(String userName, String password)
			throws SQLException, InvalidUsername, InvalidPassword, UserAlreadyLoggedIn {
        User dbUser = getUserByUserName(userName);
        Boolean isValid;
        if(dbUser == null) { isValid = false; }
        
        try {
        	if (dbUser.get_S_username().equals(userName)) { isValid = true; }
        } catch (Exception e) { throw new InvalidUsername(); }
                
        if(password.equals(dbUser.get_S_password())) {
        	String token = dbUser.get_S_token();
        	if (token == null || token.length() <= 0) { //no need to override existing authentication code
        		updateUserToken(userName, makeToken());
        		
        	}
        	isValid = true;
        }
        else { //if the password doesn't match
        	throw new InvalidPassword();
        }
        return isValid;
	}

	@Override
	public Boolean registerUser(String userName, String password) throws SQLException {
		if(userName == null || password == null){
            return false;
        }
    	_db.openConnection();

        PreparedStatement registerUserStatement = null;
        ResultSet keyRS = null;
        boolean success = false;
        String registerUserSQL = "insert into Users (userName, password, token)"
        		+ "values (?, ?, ?)";
        try {
            registerUserStatement = _db.connection.prepareStatement(registerUserSQL);
            registerUserStatement.setString(1, userName);
            registerUserStatement.setString(2, password);
            String token = makeToken();
            registerUserStatement.setString(3, token);
            
            if(registerUserStatement.executeUpdate() == 1){
                success = true;
                updateUserToken(userName, token);
            }
            else {
            	System.err.println("@registerUser");
                throw new SQLException();
            }
        } catch(SQLException e) {
        	System.err.println("@registerUser");
            System.out.println(e.getMessage());
            
        }
        finally {
            if(registerUserStatement != null) {
                registerUserStatement.close();
            }
            if (keyRS != null)
                keyRS.close();
        }
        

        return success;
	}

	@Override
	public void updateUserToken(String username, String authenticationCode) {
		_db.openConnection();

        PreparedStatement statement = null;
        try
        {
            String sql = "Update Users set token = ? where Users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, authenticationCode);
            statement.setString(2, username);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
        	System.err.println("@updateUserToken");
            e.printStackTrace();
            
        }
        finally
        {
            if(statement != null)
                statement = null;
        }
	}

	@Override
	public User getUserByUserName(String username) throws SQLException, UserAlreadyLoggedIn {
		_db.openConnection(); //This allows the database to be accessed everytime this method is called.
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User readUser = new User();
        try
        {
        	
            String sql = "select * from users where users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            readUser.set_S_username(resultSet.getString(1));
            readUser.set_S_password(resultSet.getString(2));
            readUser.set_S_token(resultSet.getString(3));
            
            if (readUser.get_S_username().length() <= 0) { throw new InvalidUsername(); }
            if (readUser.get_S_password().length() <= 0) { throw new InvalidPassword(); }
        }
        catch(SQLException e)
        {
        	System.err.println("@getUserByUserName");
            System.err.println(e.getMessage());
            
        }
        catch (Exception e) {
//        	e.printStackTrace();
        }
        
        finally
        {
            if(statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return readUser;
	}

	@Override
	public User getUserByAccessToken(String token) throws SQLException {
		_db.openConnection();

        PreparedStatement statement = null;
        ResultSet rs = null;
        User readUser = null;
        try
        {
            String sql = "select * from Users where Users.token = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, token);
            rs = statement.executeQuery();
            while(rs.next())
            {
                readUser = new User();
                readUser.set_S_username(rs.getString(1));
                readUser.set_S_password(rs.getString(2));
                readUser.set_S_token(rs.getString(3));
            }
        }
        catch(SQLException e)
        {
        	System.err.println("@getUserByAccessToken");
            System.out.println(e.getMessage());
            
        }
        finally
        {
            if(statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        }
        return readUser;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		_db.openConnection();

    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<User>();
        try {
            String getUsersSQL = "select * from Users";
            statement = _db.connection.prepareStatement(getUsersSQL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.set_S_username(resultSet.getString(1));
                user.set_S_password(resultSet.getString(2));
                user.set_S_token(resultSet.getString(3));
                userList.add(user);
            }

        } catch (SQLException e ){
        	System.err.println("@getAllUsers");
            System.out.println(e.getMessage());
            
        }
        finally {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                statement.close();
        }
        return userList;
	}
	
    private String makeToken()
    {
        return RandomGenerator.getInstance().randomUUID().substring(0, AUTH_TOKEN_LENGTH);
    }

}

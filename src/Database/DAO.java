package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import GameModels.Game;
import Server.IServer.UserAlreadyLoggedIn;
import ServerModel.GameModels.PlayerModel.Player;
import UserModel.User;

/**
 * Created by benjamin on 11/02/17.
 */
public class DAO implements iDAO {
    public static iDAO _SINGLETON = new DAO();
    private static DataBase _db;
    private int AUTH_TOKEN_LENGTH = 15;
    private static int int_gameId;

    public DAO(){
        initializeDB();
    }

    @Override
    public User getUserFromId(int userId) {
        return null;
    }

//    @Override
//    public int getUserId(String userName) {
//        return 0;
//    }

    @Override
    public Game getGameFromId(int gameId){
        return null;
    }

    @Override
    public Boolean login(String userName, String password) throws SQLException, InvalidUsername, InvalidPassword, UserAlreadyLoggedIn {
        User dbUser = getUserByUserName(userName);
        if(dbUser == null) {
            return false;
        }
        if (!dbUser.get_S_username().equals(userName)) {
        	throw new InvalidUsername();
        }
        if(password.equals(dbUser.get_S_password())) {
            updateUserToken(userName, makeToken());
            return true;
        }
        else { //if the password doesn't match
        	throw new InvalidPassword();
        }
//        return false;
    }

    @Override
    public Boolean authenticateUserWithToken(String token) throws SQLException {
        try {
            return getUserByAccessToken(token) != null;
        } catch (SQLException e) {
        	System.err.println("@authenticateUserWithToken");
            e.printStackTrace();
            
        }
        return false;
    }

    public User getUserByUserName(String username) throws SQLException, UserAlreadyLoggedIn
    {
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
            
            if (readUser.get_S_username().equals(null)) { throw new InvalidUsername(); }
            if (readUser.get_S_password().equals(null)) { throw new InvalidPassword(); }
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

    public User getUserByAccessToken(String token) throws SQLException
    {
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
    public Boolean addGame(Game game) throws SQLException {
    	if (game == null) { return false; }
    	_db.openConnection();
    	
        PreparedStatement gameStatement = null;
        ResultSet keyRS = null;
        boolean success = false;
//        String registerUserSQL = "insert into Games (gameId, numberOfPlayers, player)"
//        		+ "values (?, ?, ?)";
        String addGameSQL = "insert into Games (gameId, numberOfPlayers, player1, player2, player3, player4, player5)"
        		+ "values (?, ?, ?, ?, ?, ?, ?)";
        try {
        	gameStatement = _db.connection.prepareStatement(addGameSQL);
        	gameStatement.setInt(1, int_gameId++); //increments the int by 1 after it's added to the table
        	gameStatement.setInt(2, game.get_numberOfPlayers());
        	//Don't need to add any players to the game yet since it's brand new
            
            if(gameStatement.executeUpdate() == 1){
                success = true;
            }
            else {
            	System.err.println("@addGame");
                throw new SQLException();
            }
        } catch(SQLException e) {
        	System.err.println("@addGame");
            System.out.println(e.getMessage());
        }
        finally {
            if(gameStatement != null) {
            	gameStatement.close();
            }
            if (keyRS != null)
                keyRS.close();
        }
    	
    	return success;
    }
    
    @Override
    public void updateGameNumberOfPlayers(int gameId, int numberOfPlayersInGame) throws SQLException {
    	if (gameId == 0 || numberOfPlayersInGame > 5) {
    		return; //Get out of method
    	}
        _db.openConnection();

        PreparedStatement statement = null;
        try
        {
            String updateGamesSQL = "Update Games set numberOfPlayers = ? where Games.gameId = ?";
            statement = _db.connection.prepareStatement(updateGamesSQL);
            statement.setInt(1, numberOfPlayersInGame);
            statement.setInt(2, gameId);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
        	System.err.println("@updateGameNumberOfPlayers");
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
                statement = null;
        }
    }
    
    @Override
    public void UpdateGamePlayer(int gameId, String username, int numberOfPlayersInGame) throws SQLException {
        if(username == null || gameId == 0 || numberOfPlayersInGame > 5) {
            return;
        }
    	_db.openConnection();

    	String playerNumber = String.valueOf(numberOfPlayersInGame); //PlayerNumber == number of players in the game.
        PreparedStatement registerGameStatement = null;

//        String registerUserSQL = "insert into Games (numberOfPlayers, player1, player2, player3, player4, player5)"
//        		+ "values (?, ?, ?, ?, ?, ?)";
        String updateGamesSQL = "Update Games set player" + playerNumber +  " = ? where Games.gameId = ?";
        
        try {
        	registerGameStatement = _db.connection.prepareStatement(updateGamesSQL);
        	registerGameStatement.setString(1, username);
        	registerGameStatement.setInt(2, gameId);
        	registerGameStatement.executeUpdate();
            
        } catch(SQLException e) {
        	System.err.println("@UpdateGamePlayer");
            System.out.println(e.getMessage());
        }
        finally {
            if(registerGameStatement != null) {
            	registerGameStatement.close();
            }
        }
    }
    
    /**
     * Nathan
     * Added players to the Games table so we can add players to a game.
     * The player username (String) is stored in the table, then the DAO gets the user through it
     */
    @Override
    public Game getGamebyGameId(int gameId) throws SQLException {
    	_db.openConnection(); //This allows the database to be accessed everytime this method is called.
    	    	
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Game readGame = new Game();
        try
        {
        	
            String sql = "select * from games where Games.gameId = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            resultSet = statement.executeQuery();

            readGame.set_i_gameId(resultSet.getInt(1));
            readGame.set_numberOfPlayers(resultSet.getInt(2));
            readGame.setPlayer1(DAO._SINGLETON.getUserByUserName(resultSet.getString(3)));
            readGame.setPlayer2(DAO._SINGLETON.getUserByUserName(resultSet.getString(4)));
            readGame.setPlayer3(DAO._SINGLETON.getUserByUserName(resultSet.getString(5)));
            readGame.setPlayer4(DAO._SINGLETON.getUserByUserName(resultSet.getString(6)));
            readGame.setPlayer5(DAO._SINGLETON.getUserByUserName(resultSet.getString(7)));
        }
        catch(SQLException e)
        {
        	System.err.println("@getGamebyGameId");
            System.out.println(e.getMessage());
            
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        finally
        {
            if(statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return readGame;
    }

    @Override
    public List<Game> getGamesByUserName(String userName) throws SQLException {
    	_db.openConnection();

    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<Game>();
        try {
            String getGamesSQL = "select * from Games where Games.id in " +
                    "(select gameId from UserGames where UserGames.userName = ?)";
            statement = _db.connection.prepareStatement(getGamesSQL);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Game game = new Game();
                game.set_S_gameName(resultSet.getString(1));
                game.set_numberOfPlayers(resultSet.getInt(2));
                game.set_S_active(resultSet.getBoolean(3));
                gamesList.add(game);
            }

        } catch (SQLException e ){
        	System.err.println("@getGamesByUserName");
            System.out.println(e.getMessage());
            
        }
        finally {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                statement.close();
        }
        return gamesList;
    }

    @Override
    public List<Game> getAllGames() throws SQLException {
    	_db.openConnection();

    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Game> gamesList = new ArrayList<Game>();
        try {
            String getGamesSQL = "select * from Games";
            statement = _db.connection.prepareStatement(getGamesSQL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Game game = new Game();
                game.set_i_gameId(resultSet.getInt(1)); //Grabs the gameId
//                game.set_S_gameName(resultSet.getString(2)); //Grabs the game name
                game.set_numberOfPlayers(resultSet.getInt(2)); //Grabs the current number of players in the game
//                game.set_S_active(resultSet.getBoolean(4)); //Grabs the boolean value if it's active or not.
                gamesList.add(game);
            }

        } catch (SQLException e ){
        	System.err.println("@getAllGames");
            System.out.println(e.getMessage());
        }
        finally {
            if (statement != null)
                statement.close();
            if (resultSet != null)
                statement.close();
        }
        return gamesList;
    }
    
    public void deleteAllGames() throws SQLException {
    	_db.openConnection();

    	PreparedStatement statement = null;
    	PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        List<Game> gamesList = new ArrayList<Game>();

        try {
            String getGamesSQL = "DROP TABLE IF EXISTS Games;";
            statement = _db.connection.prepareStatement(getGamesSQL);
            statement.execute();
            
        	String createGamesTableSQL =
        			"CREATE TABLE IF NOT EXISTS Games" +
        					"(" +
//        					"id integer not null primary key autoincrement," +
        					"gameId integer not null primary key," +
        					"numberOfPlayers integer," +
        					"player1 varchar(30)," +
        					"player2 varchar(30)," +
        					"player3 varchar(30)," +
        					"player4 varchar(30)," +
        					"player5 varchar(30)," +
        					"active bit" +
        					");";
        	
	         statement2 = _db.connection.prepareStatement(createGamesTableSQL);
	         statement2.execute();


        } catch (SQLException e ){
        	System.err.println("@deleteAllGames");
            System.out.println(e.getMessage());
            
        }
        finally {
            if (statement != null || statement2 != null) {
                statement.close();
                statement2.close();
            }

            
            if (resultSet != null || resultSet2 != null) {
            	statement.close();
            	statement2.close();
            }
                
        }
//        return gamesList;
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

    @Override
    public boolean createPlayerFromUserName(String userName, int gameId) throws SQLException {
        _db.openConnection();

        PreparedStatement gameStatement = null;
        ResultSet keyRS = null;
        boolean success = false;
        String addGameSQL = "insert into Players (userName, gameId, red, white, orange, green, blue, " +
                "purple, yellow, pink, rainbow, card1, card2, card3)"
                + "values (?, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";

        try {
            gameStatement = _db.connection.prepareStatement(addGameSQL);
            gameStatement.setString(1, userName);
            gameStatement.setInt(2, gameId);

            if(gameStatement.executeUpdate() == 1){
                success = true;
            }
            else {
                System.err.println("@createPlayerFromUserName");
                throw new SQLException();
            }
        } catch(SQLException e) {
            System.err.println("@createPlayerFromUserName");
            System.out.println(e.getMessage());

        }
        finally {
            if(gameStatement != null) {
                gameStatement.close();
            }
            if (keyRS != null)
                keyRS.close();
        }
        return success;
    }

    @Override
    public Player getPlayerByUserName(String userName) throws SQLException {
        _db.openConnection(); //This allows the database to be accessed everytime this method is called.
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Player readPlayer = new Player();
        try
        {
            String sql = "select * from players where players.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();

            readPlayer.set_userName(resultSet.getString(1));
            readPlayer.set_gameId(resultSet.getInt(2));
            readPlayer.set_red(resultSet.getInt(3));
            readPlayer.set_white(resultSet.getInt(4));
            readPlayer.set_orange(resultSet.getInt(5));
            readPlayer.set_green(resultSet.getInt(6));
            readPlayer.set_blue(resultSet.getInt(7));
            readPlayer.set_purple(resultSet.getInt(8));
            readPlayer.set_yellow(resultSet.getInt(9));
            readPlayer.set_pink(resultSet.getInt(10));
            readPlayer.set_rainbow(resultSet.getInt(11));
            readPlayer.set_card1(resultSet.getInt(12));
            readPlayer.set_card2(resultSet.getInt(13));
            readPlayer.set_card3(resultSet.getInt(14));
        }
        catch(SQLException e)
        {
            System.err.println("@getPlayerByUserName");
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
        }

        finally
        {
            if(statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return readPlayer;
    }

    @Override
    public boolean updatePlayerTrainCards(String userName, String cardColor, String number) throws SQLException {
        _db.openConnection();
        PreparedStatement statement = null;
        boolean success = false;
        try
        {
            String sql = "Update Players set ? = ? + ? where Users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, cardColor);
            statement.setString(2, cardColor);
            statement.setString(3, number);
            statement.setString(4, userName);
            if(statement.executeUpdate() == 1){
                success = true;
            }
        }
        catch(SQLException e)
        {
            System.err.println("@updatePlayerTrainCards");
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
                statement = null;
        }
    	return success;
    }

    @Override
    public boolean updatePlayerDestCards(String userName, String cardColumn, Integer newCardType) throws SQLException {
        _db.openConnection();
        PreparedStatement statement = null;
        boolean success = false;
        try
        {
            String sql = "Update Players set ? = ? where Users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, cardColumn);
            statement.setInt(2, newCardType);
            statement.setString(3, userName);
            if(statement.executeUpdate() == 1){
                success = true;
            }
        }
        catch(SQLException e)
        {
            System.err.println("@updatePlayerDestCards");
            e.printStackTrace();
        }
        finally
        {
            if(statement != null)
                statement = null;
        }
        return success;
    }

    @Override
    public boolean updatePlayerToNewGame(String userName, int newGameId) {
        return false;
    }

    @Override
    public void initializeDB() {
        _db = new DataBase();
        try {
			int_gameId = getAllGames().size();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    private void updateUserToken(String userName, String token)
    {
    	_db.openConnection();

        PreparedStatement statement = null;
        try
        {
            String sql = "Update Users set token = ? where Users.userName = ?";
            statement = _db.connection.prepareStatement(sql);
            statement.setString(1, token);
            statement.setString(2, userName);
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

    private String makeToken()
    {
        return RandomGenerator.getInstance().randomUUID().substring(0, AUTH_TOKEN_LENGTH);
    }
    
    @Override
    public DataBase getDb() { return _db; }
}

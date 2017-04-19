//package RelationalDatabase;
//
///**
// * Created by raulbr on 2/12/17.
// */
//
//import java.io.File;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class RelationalDB {
//    Connection connection;
//
//    public RelationalDB(){
//        loadDriver();
//    }
//
//    /**
//     * Load the driver to talk to the database
//     */
//    public void loadDriver(){
//        try
//        {
//            final String driver = "org.sqlite.JDBC";
//            Class.forName(driver);
//        }
//        catch (ClassNotFoundException e)
//        {
//            System.out.print("Class Not Found error \n");
//        }
//    }
//
//    /**
//     * Open connection with the data base
//     */
//    public void openConnection()
//    {
////        File directory = new File("assets");
//        File directory = new File("db");
//        if(!directory.exists())
//        {
//            try
//            {
//                directory.mkdirs();
//            }
//            catch(SecurityException se)
//            {
//                System.out.println("Error creating the folder for the DB files! The server can not work correctly with out this!");
//                return;
//            }
//        }
//
//		String dbName = "db" + File.separator + "database.sqlite";
//		String connectionURL = "jdbc:sqlite:" + dbName;
////		String connectionURL = "jdbc:sqlite::resource:db";
//        connection = null;
//        try
//        {
//            connection = DriverManager.getConnection(connectionURL);
//            createIfNotExist();
//        }
//        catch(SQLException e)
//        {
//        	System.err.println("@Database OpenConnection");
//            System.out.print("SQL error\n");
//        }
//        return;
//    }
//
//    public void startTransaction()
//    {
//        openConnection();
//        try
//        {
//            connection.setAutoCommit(false);
//        }
//        catch (SQLException e)
//        {
//            System.out.print("turn off auto commit error");
//            e.printStackTrace();
//        }
//    }
//
//    public void closeTransaction(boolean commit)
//    {
//        try
//        {
//            if(commit)
//            {
//                connection.commit();
//            }
//            else
//            {
//                connection.rollback();
//            }
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//            System.out.print("Close transaction commit error\n");
//        }
//        finally
//        {
//            try
//            {
//                connection.close();
//            }
//            catch (SQLException e) {
//                System.out.println("Cant close connection");
//                e.printStackTrace();
//            }
//        }
//        connection = null;
//    }
//
//    public void createIfNotExist() throws SQLException
//    {
//    	String createGamesTableSQL =
//    			"CREATE TABLE IF NOT EXISTS Games" +
//    					"(" +
//    					"id integer not null primary key autoincrement," +
//    					"gameId integer not null primary key," +
//    					"numberOfPlayers integer," +
//    					"player1 varchar(30)," +
//    					"player2 varchar(30)," +
//    					"player3 varchar(30)," +
//    					"player4 varchar(30)," +
//    					"player5 varchar(30)," +
//    					"active bit" +
//    					");";
//    	PreparedStatement GamesStatement = this.connection.prepareStatement(createGamesTableSQL); //WORKS
//    	GamesStatement.executeUpdate();
//    	
//        String createUsersTableSQL =
//    			"CREATE TABLE IF NOT EXISTS Users" +
//                		"(" +
//                		"userName varchar(30) not null primary key," +
//                		"password varchar(255)," +
//                		"token varchar(70)" +
//                		");";
//        PreparedStatement UsersStatement = this.connection.prepareStatement(createUsersTableSQL);
//        UsersStatement.executeUpdate();
//
//        String createUserGamesTable =
//                "CREATE TABLE IF NOT EXISTS UserGames"+
//                "(" +
//                    "id integer not null primary key autoincrement," +
//                    "userName varchar(30) not null," +
//                    "gameId integer" +
//                ");";
//        PreparedStatement UserGamesStatement = this.connection.prepareStatement(createUserGamesTable);
//        UserGamesStatement.executeUpdate();
//
//        String createPlayersTable =
//                "CREATE TABLE IF NOT EXISTS Players" +
//                        "(" +
//                        "userName varchar(30) not null primary key," +
//                        "gameId integer not null," +
//                        "red integer," +
//                        "white integer," +
//                        "orange integer," +
//                        "green integer," +
//                        "blue integer," +
//                        "purple integer," +
//                        "yellow integer," +
//                        "pink integer," +
//                        "rainbow integer," +
//                        "card1 integer," +
//                        "card2 integer," +
//                        "card3 integer" +
//                        ");";
//        PreparedStatement PlayersStatement = this.connection.prepareStatement(createPlayersTable);
//        PlayersStatement.executeUpdate();
//    }
//
//    public void resetDB(boolean dropUserTable) throws SQLException
//    {
//        try {
//            dropTableSQLStatements(dropUserTable);
//            createTAbleSQLStatements(dropUserTable);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void dropTableSQLStatements(boolean dropUserTable) throws SQLException {
//        String dropTableUsers = "DROP TABLE IF EXISTS Users; ";
//        String dropTableGames = "DROP TABLE IF EXISTS Games; ";
//        String dropTableUserGames = "DROP TABLE IF EXISTS UserGames; ";
//        String dropTablePlayers = "DROP TABLE IF EXISTS Players; ";
//        if(dropUserTable)
//        {
//            PreparedStatement dropUsersStatement = this.connection.prepareStatement(dropTableUsers);
//            dropUsersStatement.executeUpdate();
//
//            PreparedStatement dropTableGamesStatement = this.connection.prepareStatement(dropTableGames);
//            dropTableGamesStatement.executeUpdate();
//
//            PreparedStatement dropTableUserGamesStatement = this.connection.prepareStatement(dropTableUserGames);
//            dropTableUserGamesStatement.executeUpdate();
//
//            PreparedStatement droptTableplayersStatement = this.connection.prepareStatement(dropTablePlayers);
//            droptTableplayersStatement.executeUpdate();
//        }
//    }
//
//    private void createTAbleSQLStatements(boolean dropUserTable) throws SQLException {
//        String createUsersTableSQL =
//    			"CREATE TABLE IF NOT EXISTS Users" +
//                		"(" +
//                		"userName varchar(30) not null primary key," +
//                		"password varchar(255)," +
//                		"token varchar(70)" +
//                		");";
//
//    	String createGamesTableSQL =
//    			"CREATE TABLE IF NOT EXISTS Games" +
//    					"(" +
//    					"id integer not null primary key autoincrement," +
//    					"gameId integer not null primary key," +
//    					"numberOfPlayers integer," +
//    					"player1 varchar(30)," +
//    					"player2 varchar(30)," +
//    					"player3 varchar(30)," +
//    					"player4 varchar(30)," +
//    					"player5 varchar(30)," +
//    					"active bit" +
//    					");";
//
//        String createUserGamesTable =
//                "CREATE TABLE IF NOT EXISTS UserGames"+
//                "(" +
//                    "id integer not null primary key autoincrement," +
//                    "userName varchar(30) not null," +
//                    "gameId integer" +
//                ");";
//
//        String createPlayersTable =
//                "CREATE TABLE IF NOT EXISTS Players" +
//                 "(" +
//                        "userName varchar(30) not null primary key," +
//                        "gameId integer not null," +
//                        "red integer," +
//                        "white integer," +
//                        "orange integer," +
//                        "green integer," +
//                        "blue integer," +
//                        "purple integer," +
//                        "yellow integer," +
//                        "pink integer," +
//                        "rainbow integer," +
//                        "card1 integer," +
//                        "card2 integer," +
//                        "card3 integer" +
//                 ");";
//
//        if(dropUserTable)
//        {
//            PreparedStatement createUsersTableStatement = this.connection.prepareStatement(createUsersTableSQL);
//            createUsersTableStatement.executeUpdate();
//            
//            PreparedStatement createGamesTableStatement = this.connection.prepareStatement(createGamesTableSQL);
//            createGamesTableStatement.executeUpdate();
//            
//            PreparedStatement createUserGamesTableStatement = this.connection.prepareStatement(createUserGamesTable);
//            createUserGamesTableStatement.executeUpdate();
//
//            PreparedStatement createPlayersTableStatement = this.connection.prepareStatement(createPlayersTable);
//            createPlayersTableStatement.executeUpdate();
//        }
//    }
//
//    public void fillReset(String username)
//    {
//        try
//        {
//            String userTable = "Delete from Users where id = ?";
//            String userGamesTable = "Delete from UserGames where user = ?";
//            PreparedStatement statement = this.connection.prepareStatement(userTable);
//            PreparedStatement statement2 = this.connection.prepareStatement(userGamesTable);
//
//            statement.setString(1, username);
//            statement2.setString(1, username);
//
//            statement.execute();
//            statement2.execute();
//        }
//        catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//
//        }
//    }
//}

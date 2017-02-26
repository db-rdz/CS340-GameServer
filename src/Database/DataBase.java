package com.example.ryanblaser.tickettoride.Database;

/**
 * Created by raulbr on 2/12/17.
 */

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    Connection connection;
    public DAO _dao = new DAO();

    public DataBase(){
        loadDriver();
    }

    /**
     * Load the driver to talk to the database
     */
    public void loadDriver(){
        try
        {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            System.out.print("Class Not Found error \n");
        }
    }

    /**
     * Open connection with the data base
     */
    public void openConnection()
    {
        //String dbName = "database.sqlite";
        File directory = new File("db");
        if(!directory.exists())
        {
            try
            {
                directory.mkdirs();
            }
            catch(SecurityException se)
            {
                System.out.println("Error creating the folder for the DB files! The server can not work correctly with out this!");
                return;
            }
        }

        String dbName = "db" + File.separator + "database.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbName;
        connection = null;
        try
        {
            connection = DriverManager.getConnection(connectionURL);
            createIfNotExist();
        }
        catch(SQLException e)
        {
            System.out.print("SQL error\n");
        }
        return;
    }

    public void startTransaction()
    {
        openConnection();
        try
        {
            connection.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            System.out.print("turn off auto commit error");
            e.printStackTrace();
        }
    }

    public void closeTransaction(boolean commit)
    {
        try
        {
            if(commit)
            {
                connection.commit();
            }
            else
            {
                connection.rollback();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.print("Close transaction commit error\n");
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (SQLException e) {
                System.out.println("Cant close connection");
                e.printStackTrace();
            }
        }
        connection = null;
    }

    public void createIfNotExist() throws SQLException
    {
        String createUsersTableSQL =
                "CREATE TABLE IF NOT EXISTS Users" +
                 "(" +
                    "userName primary key varchar(30)," +
                    "password varchar(255)," +
                    "token varchar(70)" +
                 ");";
        PreparedStatement UsersStatement = this.connection.prepareStatement(createUsersTableSQL);
        UsersStatement.executeUpdate();

        String createGamesTableSQL =
                "CREATE TABLE IF NOT EXISTS Games" +
                "(" +
                    "id integer not null primary key autoincrement," +
                    "gameName varchar(64)," +
                    "numberOfPlayers integer" +
                    "active bit" +
                ");";
        PreparedStatement GamesStatement = this.connection.prepareStatement(createGamesTableSQL);
        GamesStatement.executeUpdate();

        String createUserGamesTable =
                "CREATE TABLE IF NOT EXISTS UserGames"+
                "(" +
                    "id integer not null primary key autoincrement," +
                    "userName varchar(30) not null," +
                    "gameId integer not null," +
                ");";
        PreparedStatement UserGamesStatement = this.connection.prepareStatement(createUserGamesTable);
        UserGamesStatement.executeUpdate();
    }

    public void resetDB(boolean dropUserTable) throws SQLException
    {
        try {
            dropTableSQLStatements(dropUserTable);
            createTAbleSQLStatements(dropUserTable);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTableSQLStatements(boolean dropUserTable) throws SQLException {
        String dropTableUsers = "DROP TABLE IF EXISTS Users; ";
        String dropTableGames = "DROP TABLE IF EXISTS Games; ";
        String dropTableUserGames = "DROP TABLE IF EXISTS UserGames; ";
        if(dropUserTable)
        {
            PreparedStatement dropUsersStatement = this.connection.prepareStatement(dropTableUsers);
            dropUsersStatement.executeUpdate();

            PreparedStatement dropTableGamesStatement = this.connection.prepareStatement(dropTableGames);
            dropTableGamesStatement.executeUpdate();

            PreparedStatement dropTableUserGamesStatement = this.connection.prepareStatement(dropTableUserGames);
            dropTableUserGamesStatement.executeUpdate();
        }
    }

    private void createTAbleSQLStatements(boolean dropUserTable) throws SQLException {
        String createUsersTableSQL =
                "CREATE TABLE IF NOT EXISTS Users" +
                        "(" +
                        "userName primary key varchar(30)," +
                        "password varchar(255)," +
                        "token varchar(70)" +
                        ");";

        String createGamesTableSQL =
                "CREATE TABLE IF NOT EXISTS Games" +
                        "(" +
                        "id integer not null primary key autoincrement," +
                        "gameName varchar(64)," +
                        "numberOfPlayers integer" +
                        "active bit" +
                        ");";

        String createUserGamesTable =
                "CREATE TABLE IF NOT EXISTS UserGames"+
                        "(" +
                        "id integer not null primary key autoincrement," +
                        "userName varchar(30) not null," +
                        "gameId integer not null," +
                        ");";

        if(dropUserTable)
        {
            PreparedStatement createUsersTableStatement = this.connection.prepareStatement(createUsersTableSQL);
            createUsersTableStatement.executeUpdate();
        }
        PreparedStatement createGamesTableStatement = this.connection.prepareStatement(createGamesTableSQL);
        createGamesTableStatement.executeUpdate();

        PreparedStatement createUserGamesTableStatement = this.connection.prepareStatement(createUserGamesTable);
        createUserGamesTableStatement.executeUpdate();
    }

    public void fillReset(String username)
    {
        try
        {
            String userTable = "Delete from Users where id = ?";
            String userGamesTable = "Delete from UserGames where user = ?";
            PreparedStatement statement = this.connection.prepareStatement(userTable);
            PreparedStatement statement2 = this.connection.prepareStatement(userGamesTable);

            statement.setString(1, username);
            statement2.setString(1, username);

            statement.execute();
            statement2.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
    }
}

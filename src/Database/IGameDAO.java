package Database;

import java.sql.SQLException;
import java.util.List;

import GameModels.Game;
import ServerModel.GameModels.PlayerModel.Player;

public interface IGameDAO {
	
    public void UpdateGamePlayer(int gameId, String username, int numberOfPlayersInGame) throws SQLException;

    public List<Game> getGamesByUserName(String userName) throws SQLException;

    public void updateGameNumberOfPlayers(int gameId, int numberOfPlayersInGame) throws SQLException;

	public Boolean addGame(Game game) throws SQLException;

    public Game getGamebyGameId(int gameId) throws SQLException;

    public boolean createPlayerFromUserName(String userName, int gameId) throws SQLException;

    //By getting the player you will be getting their Train/Dest cards
    public Player getPlayerByUserName(String userName) throws SQLException;
    
    //Update the train cards of a player, increments  or decrements current value by the number provided
    // Example: update("Username", "blue", "1") or update("Username", "blue", "-1")
    //cardColor is the color of the train card which are column names on the players table   "-"
    public boolean updatePlayerTrainCards(String userName, String cardColor, String number) throws SQLException;
    
    // Updates a destination card of a player
    // card is the name of the card you want to update (column of table) options:
    // card1, card2, card3
    // newCardType is the cardtype we want to update to. Described on CardType.java (30 different optioins/ids)
    public boolean updatePlayerDestCards(String userName, String cardColumn, Integer newCardType) throws SQLException;
    
    //Moves a player to another game and resets his stats/cards
    public boolean updatePlayerToNewGame(String userName, int newGameId) throws SQLException;
    
    public List<Game> getAllGames()  throws SQLException;
    
    public void deleteAllGames() throws SQLException;



}

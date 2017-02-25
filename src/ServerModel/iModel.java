package ServerModel;

import ServerModel.GameModels.Game;
import ServerModel.GameModels.iGame;
import ServerModel.UserModel.User;
import ServerModel.UserModel.iUser;

import java.util.List;

/**
 * Created by benjamin on 6/02/17.
 * The model will be holding the next information:
 * <p>
 * -All games.
 * -List of available games.
 * -List of active games.
 * -Player's Game information. ( eg. card's owned, routes claimed, etc... )
 * -Active Player's information ( eg. games played, player's location, logged in?, etc... )
 */
public interface iModel {


    //---------------------------------------LOBBY ACTIONS------------------------------------//
    /** THESE ARE ALL THE ACTIONS YOU CAN DO IN THE LOBBY  */

    /**
     * getAvailableGames:
     * Returns a list of games that haven't started yet.
     */
    public List<Game> getAvailableGames();

    /**
     * getActiveGames:
     * Returns a list of games that have started.
     */
    public List<Game> getStartedGames();

    /**
     * getAllGames:
     * Returns a list of all games.
     */
    public List<Game> getAllGames();

    /**
     * Gets a user according with its username.
     *
     * @param username a string containing the user's username.
     * @return a User object that matches the username.
     */
    public User getUserWithUsername(String username);

    public List<User> getAllLoggedInUsers();

    /**
     * Gets the list of games of a specific player.
     */

    public Game getUserJoinedGame(String username);


    /**
     * Gets a game from the database, add it to the Game model, and adds it to the user joined games
     */
    public Boolean initializeGameFromDB(String username, int gameId);

    /**
     *  This function will send the game from the available game list to the started game list and will prepare
     *  everything for the game to start.
     * */
    public Boolean startGame(int gameId);

    /**
     *  Precondition: Game haven't started.
     *  This function will delete all instances of games and will kick all the players out of the game and
     *  return their status to "not in a game"
     * */
    public Boolean cancelGame(int gameId);

    /**
     *  Precondition: Game has to have a winner.
     *  Will delete all instances of the game and kick all players of the game and return their status to
     *  "not in a game"
     * */
    public Boolean endGame(int gameId);

    /**
     * addPlayerToGame:
     * Adds a user to a game if the game haven't started and if there's room.
     */
    public Boolean addPlayerToGame(String username, int gameID);

    /**
     * removePlayerFromGame:
     * Removes a user from game if game haven't started.
     */
    public Boolean removePlayerFromGame(String username, int gameID);

    /**
     * removeAllPlayersFromGame:
     * Removes all players from a game (useful if the user hosting the game cancels it)
     */
    public void removeAllPlayersFromGame(int gameID);

    /**
     *
     *
     */
    public Boolean addGameToLobby(iGame game);



    //______________________________________________________________________________________________//


    //-------------------------------

    /**
     * It pulls information from the database and adds the logged in user to the model.
     */
    public Boolean logIn(String username);

    /**
     * It syncs the user info with the database and deletes its instance from the model.
     */
    public Boolean logOut(String username);

    /**
     *  It gets a game from the Game model using a game id.
     * */
    public Game getGame(int intGameId);
}



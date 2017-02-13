package ServerModel;

import ServerModel.GameModels.Game;
import ServerModel.GameModels.iGame;
import ServerModel.UserModel.iUser;

import java.util.List;

/**
 * Created by benjamin on 6/02/17.
 *      The model will be holding the next information:
 *
 *      -All games.
 *      -List of available games.
 *      -List of active games.
 *      -Player's Game information. ( eg. card's owned, routes claimed, etc... )
 *      -Active Player's information ( eg. games played, player's location, logged in?, etc... )
 *
 *
 */
public interface iModel {


    //---------------------------------------LOBBY ACTIONS------------------------------------//
    /** THESE ARE ALL THE ACTIONS YOU CAN DO IN THE LOBBY  */

    /**
     * getAvailableGames:
     *      Returns a list of games that haven't started yet.
     */
    public List<Game> getAvailableGames();

    /**
     * getActiveGames:
     *      Returns a list of games that have started.
     */
    public List<Game> getStartedGames();

    /**
     *  getAllGames:
     *      Returns a list of all games.
     */
    public List<Game> getAllGames();

    /**
     *  Gets the list of games of a specific player.
     *
     */

    public List<Game> getUserJoinedGames( int userId );


    /**
     *  Gets a game from the database, add it to the Game model, and adds it to the user joined games
     */
    public Boolean initializeGameFromDB( int userId, int gameId );

    /**
     *  addPlayerToGame:
     *      Adds a user to a game if the game haven't started and if there's room.
     */
    public Boolean addPlayerToGame(int userID, int gameID);

    /**
     *  removePlayerFromGame:
     *      Removes a user from game if game haven't started.
     */
    public Boolean removePlayerFromGame(int userID, int gameID);

    /**
     *  removeAllPlayersFromGame:
     *      Removes all players from a game (useful if the user hosting the game cancels it)
     */
    public void removeAllPlayersFromGame(int gameID);

    /**
     *
     *
     * */
    public Boolean addGameToLobby(iGame game);

    //______________________________________________________________________________________________//


    //-------------------------------
    /**
     *  It pulls information from the database and adds the logged in user to the model.
     */
    public Boolean logIn(int userId);

    /**
     *  It syncs the user info with the database and deletes its instance from the model.
     */
    public Boolean logOut(int userId);

}

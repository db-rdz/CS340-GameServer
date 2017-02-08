package ServerModel;

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
    public List<iGame> getAvailableGames();

    /**
     * getActiveGames:
     *      Returns a list of games that have started.
     */
    public List<iGame> getActiveGames();

    /**
     *  getAllGames:
     *      Returns a list of all games.
     */
    public List<iGame> getAllGames();

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
    public Boolean removeAllPlayersFromGame(int gameID);

    /**
     *
     *
     * */
    public Boolean addGameToLobby(iGame game);

    //______________________________________________________________________________________________//


    //-------------------------------

    public Boolean addLoggedInUserToModel(iUser loggedUser);

}

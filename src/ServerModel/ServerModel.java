package ServerModel;

import ServerModel.GameModels.Game;
import ServerModel.GameModels.iGame;
import ServerModel.UserModel.User;
import ServerModel.UserModel.iUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class ServerModel implements iModel {

    @Override
    public List<Game> getAvailableGames() {
        return Game.get_allAvailableGames();
    }

    @Override
    public List<Game> getStartedGames() {
        return Game.get_allStartedGames();
    }

    @Override
    public List<Game> getAllGames() {
        return Game.getAllGames();
    }

    @Override
    public List<Game> getUserJoinedGames( int userId ){
        return User.getUserWithID(userId).getJoinedGames();
    }

    @Override
    public Boolean addPlayerToGame(int userID, int gameID) {
        if(Game.isUserInGame(userID, gameID)){
            return false;
        }
        if(Game.isGameFull(gameID)){
            return false;
        }
        return true;
    }

    @Override
    public Boolean removePlayerFromGame(int userID, int gameID) {
        return Game.getGameWithId(gameID).removePlayer(userID);
    }

    @Override
    public void removeAllPlayersFromGame(int gameID) {
        Game.getGameWithId(gameID).removeAllPlayer();
    }

    @Override
    public Boolean addGameToLobby(iGame game) {
        return null;
    }

    @Override
    public Boolean addLoggedInUserToModel(int userId) {
        return User.addLoggedInUser(userId);
    }
}

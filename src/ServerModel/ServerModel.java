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

    private List<iGame> _availableGames;
    private List<iGame> _activeGames;


    private Map<String, Game> _idToGame = new HashMap();
    @Override
    public List<iGame> getAvailableGames() {
        return null;
    }

    @Override
    public List<iGame> getActiveGames() {
        return null;
    }

    @Override
    public List<iGame> getAllGames() {
        return null;
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
        return null;
    }

    @Override
    public Boolean removeAllPlayersFromGame(int gameID) {
        return null;
    }

    @Override
    public Boolean addGameToLobby(iGame game) {
        return null;
    }

    @Override
    public Boolean addLoggedInUserToModel(iUser loggedUser) {
        return null;
    }
}
